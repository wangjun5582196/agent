package com.ai.ipark.agent;

import com.ai.ipark.trans.DefineTransformer;
import java.lang.instrument.Instrumentation;

/**
 * @Author: huzhang
 * @Date: 2021/7/17 9:55 上午
 *
 */
public class PreMainTraceAgent {

  public static void premain(String agentArgs, Instrumentation inst) {
    System.out.println("agentArgs : " + agentArgs);
    inst.addTransformer(new DefineTransformer(), true);
  }

}
