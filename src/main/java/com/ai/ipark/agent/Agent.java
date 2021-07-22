package com.ai.ipark.agent;

import com.ai.ipark.trans.CustomClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author: huzhang
 * @Date: 2021/7/16 2:15 下午
 */
public class Agent {
  public static void agentmain(String args, Instrumentation inst)
      throws InvocationTargetException, IllegalAccessException {
//    Class[] classes = inst.getAllLoadedClasses();
//    Class temp = null;
//    for(int i=0;i<classes.length;i++){
//      if("java.awt.Container".equals(classes[i].getName())){
//        temp = classes[i];
//      }
//    }
//
//    Method[] methods =temp.getMethods();
//    for(int i=0;i<methods.length;i++){
//      if("getComponents".equals(methods[i].getName())){
//        Object components = methods[i].invoke("getComponents",null);
//        System.out.println(components);
//      }
//    }
    inst.addTransformer(new CustomClassFileTransformer(),true);
  }
}
