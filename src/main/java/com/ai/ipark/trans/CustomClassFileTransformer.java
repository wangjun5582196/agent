package com.ai.ipark.trans;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * @Author: huzhang
 * @Date: 2021/7/16 5:39 下午
 */
public class CustomClassFileTransformer implements ClassFileTransformer {
  @Override
  public byte[] transform(ClassLoader loader, String className,
      Class classBeingRedefined, ProtectionDomain protectionDomain,
      byte[] classfileBuffer){
    System.out.println(className);
    byte[] byteCode = classfileBuffer;
    if (className.equals("com/dongguangming/Agent/AgentTest")) {
      System.err.println("被拦截的类是："+className);

      ClassPool classPool = ClassPool.getDefault();
      try {
        CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(
            classfileBuffer));
        CtMethod[] methods = ctClass.getDeclaredMethods();
        //System.err.println(methods);

        for (CtMethod method : methods) {
          System.err.println(method.getName());

          if (method.getName().equals("print")) {
            System.out.println("被拦截的方法是 : "
                + className+"."+method.getName());
            method.insertAt(1,
                "System.out.println(\"注入代码，打日志，计时等\");");
          }
        }
        byteCode = ctClass.toBytecode();
        ctClass.detach();
      } catch (Throwable e) {
        e.printStackTrace();
      }
    }
    return byteCode;
  }
}
