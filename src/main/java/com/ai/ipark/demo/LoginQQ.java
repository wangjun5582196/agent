package com.ai.ipark.demo;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author: huzhang
 * @Date: 2021/7/13 1:53 下午
 */
public class LoginQQ {

  //  入口主函数

  public static void main(String[] args)
      throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
    // 实例化showFrameqq类的对象
    LoginQQ showqq = new LoginQQ();
    //调用qq界面的方法
    showqq.initGUI();

  }

  //定义一个qq界面的方法
  public void initGUI()
      throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
    //实例化一个JFrame类的对象
    javax.swing.JFrame jf = new javax.swing.JFrame();
    //设置窗体的标题属性
    jf.setTitle("QQ登录界面");
    //设置窗体的大小属性
    jf.setSize(300, 200);
    //设置窗体的位置属性
    jf.setLocation(450, 200);
    //设置窗体关闭时退出程序
    jf.setDefaultCloseOperation(3);
    //设置禁止调整窗体的大小
    jf.setResizable(false);

    //实例化一个布局类的对象
    java.awt.FlowLayout fl = new java.awt.FlowLayout();
    //设置窗体的布局方式为流式布局
    jf.setLayout(fl);
    //实例化一个JLabel标签类的对象
    javax.swing.JLabel jlaName = new javax.swing.JLabel(
        "                                          ");
    jlaName.setSize(2, 3);
    //将jlaName对象添加到容器JFrame对象上
    jf.add(jlaName);
    //实例化一个JTextField对象
    javax.swing.JTextField jteName = new javax.swing.JTextField("账号", 20);

    //将jteName对象添加到容器JFrame对象上
    jf.add(jteName);
    System.out.println("测试---" + jteName);
    //实例化一个JLabel对象
    javax.swing.JLabel jlaName2 = new javax.swing.JLabel("注册账号");
    //将jlaName2对象添加到容器JFrame对象上
    jf.add(jlaName2);
    //实例化一个JPasswordField对象
    javax.swing.JPasswordField jpaName = new javax.swing.JPasswordField("请输入密码", 20);
    //将jpaName对象添加到容器JFrame对象上
    jpaName.setAlignmentX(236);
    jpaName.setAlignmentY(338);
    jf.add(jpaName);
    //实例化一个JLabel对象
    javax.swing.JLabel jlaName3 = new javax.swing.JLabel("找回密码");
    //将jlaName3对象添加到容器JFrame对象上
    jf.add(jlaName3);
    //实例化一个JCheckBox对象
    javax.swing.JCheckBox jchName = new javax.swing.JCheckBox("记住密码");
    //将jchName3对象添加到容器JFrame对象上
    jf.add(jchName);
    //实例化一个JCheckBox对象
    javax.swing.JCheckBox jchName2 = new javax.swing.JCheckBox("自动登录");
    //将jchName3对象添加到容器JFrame对象上
    jf.add(jchName2);
    //实例化一个JCheckBox对象
    javax.swing.JButton jbuName = new javax.swing.JButton("           登             录          ");
    //将jButton对象添加到容器JFrame对象上
    jf.add(jbuName);

    jf.setVisible(true);

    for (Component co : jf.getRootPane().getContentPane().getComponents()) {
      System.out.println("加载后---" + co);
      System.out.println("坐标 x---" + co.getLocation().x + "y---" + co.getLocation().y);
    }

  }


}
