package com.ai.ipark.trans;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;

/**
 * @Author: huzhang
 * @Date: 2021/7/17 9:56 上午
 */
public class DefineTransformer implements ClassFileTransformer {

  private static final String START_TIME = "\nlong startTime = System.currentTimeMillis();\n";
  private static final String END_TIME = "\nlong endTime = System.currentTimeMillis();\n";
  private static final String METHOD_RETURN_VALUE_VAR = "__time_monitor_result";


  @Override
  public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
    className = className.replace("/", ".");
    CtClass ctClass = null;
    //拦截JFrame这个类

    if("java.awt.Container".equals(className)){
      try {
        ctClass = ClassPool.getDefault().get(className);
        CtMethod[] ctMethods = ctClass.getDeclaredMethods();
        //遍历每一个方法
        for(CtMethod ctMethod:ctMethods){
          //修改方法的字节码
          if(ctMethod.getName().equals("add")){
            transformMethod2(ctMethod, ctClass);
          }
        }
        //重新返回修改后的类
        return ctClass.toBytecode();
      }  catch (Throwable e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  /**
   * 为每一个拦截到的方法 执行一个方法的耗时操作
   * @param ctMethod
   * @param ctClass
   * @throws Exception
   */
  private void transformMethod(CtMethod ctMethod,CtClass ctClass) throws Exception{
    //抽象的方法是不能修改的 或者方法前面加了final关键字
    if((ctMethod.getModifiers()& Modifier.ABSTRACT)>0){
      return;
    }
    //获取原始方法名称
    String methodName = ctMethod.getName();
    String monitorStr = "\nSystem.out.println(\"method " + ctMethod.getLongName() + " cost:\" +(endTime - startTime) +\"ms.\");";

//    String monitorStr = "\nSystem.out.println(comp.getLocation().x);";

    //实例化新的方法名称
    String newMethodName = methodName + "$impl";

    //设置新的方法名称
    ctMethod.setName(newMethodName);
    //创建新的方法，复制原来的方法 ，名字为原来的名字
    CtMethod newMethod = CtNewMethod.copy(ctMethod,methodName, ctClass, null);

    StringBuilder bodyStr = new StringBuilder();
    //拼接新的方法内容
    bodyStr.append("{");

    //返回类型
    CtClass returnType = ctMethod.getReturnType();

    //是否需要返回
    boolean hasReturnValue = (CtClass.voidType != returnType);

    if (hasReturnValue) {
      String returnClass = returnType.getName();
      bodyStr.append("\n").append(returnClass + " " + METHOD_RETURN_VALUE_VAR + ";");
    }


    bodyStr.append(START_TIME);

    if (hasReturnValue) {
      bodyStr.append("\n").append(METHOD_RETURN_VALUE_VAR + " = ($r)" + newMethodName + "($$);");
    } else {
      bodyStr.append("\n").append(newMethodName + "($$);");
    }

    bodyStr.append(END_TIME);
    bodyStr.append(monitorStr);

    if (hasReturnValue) {
      bodyStr.append("\n").append("return " + METHOD_RETURN_VALUE_VAR +" ;");
    }

    bodyStr.append("}");
    //获取原方法字节码


    //替换新方法
    System.out.println("------:"+bodyStr.toString()+"-----------");
    newMethod.setBody(bodyStr.toString());
    //增加新方法
    ctClass.addMethod(newMethod);
  }

   private void  transformMethod2(CtMethod ctMethod,CtClass ctClass)
       throws CannotCompileException, NotFoundException, IOException, IllegalAccessException, InstantiationException {
     //解冻文件让其能修改
     ctClass.defrost();
     ctMethod.insertAfter("{ System.out.println($1);}");
     ctClass.writeFile();
   }

}



