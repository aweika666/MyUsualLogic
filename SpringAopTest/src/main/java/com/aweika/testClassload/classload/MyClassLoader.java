package com.aweika.testClassload.classload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author: Michael
 * @date: 2020/1/28
 * @description:
 */
public class MyClassLoader extends ClassLoader {
    /**
     * 类加载器名称
     */
    private String name;

    /**
     * 自定义加载路径/Users/fangqizhe/Desktop/ProJects/MyUsualLogic/SpringAopTest/target/classes/
     */
    private String path = "/Users/fangqizhe/Desktop/ProJects/nacostest/nacosprovider/target/classes/";
    /**
     * 自定义加载文件后缀类型
     */
    private final String fileType = ".class";

    public MyClassLoader(ClassLoader parent,String name){
        //自己指定父类加载器
        super(parent);
        this.name = name;
    }

    public MyClassLoader(String name){
        //让系统类加载器(AppClassLoader)成为该类加载器的父类加载器
        super();
        this.name = name;
    }

    /**
     * 加载我们自己定义的类，通过我们自己定义的类加载器
     * @param name 二进制的文件名称
     * @return Class实例对象
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //获取class文件的字节数组
        byte[] resultData = this.loadByteClassData(name);

        return super.defineClass(name, resultData, 0, resultData.length);
    }
    /**
     * 加载指定路径下面的class文件的字节数组
     * @param name 二进制文件名称 ，例如：com.learn.classloader.Demo
     * @return 二进制字节数组
     */
    private byte[] loadByteClassData(String name) {
        byte[] classData = null;
        InputStream in = null;
        ByteArrayOutputStream os = null;
        try {
            // 比如 有包名 二进制文件名：com.learn.classloader.Demo
            // 转换为本地路径 com/learn/classloader/Demo.class
            String classpath = this.path + name.replace(".", "/") + fileType;
            File file = new File(classpath);
            os = new ByteArrayOutputStream();
            in = new FileInputStream(file);

            int tmp = 0;
            while ((tmp = in.read()) != -1){
                os.write(tmp);
            }
            // 文件流转为二进制字节流
            classData = os.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                // 关闭流
                if(in != null){
                    in.close();
                }
                if(os != null){
                    os.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return classData;
    }

    /*public class loadClass {
    public static String name = "loadClass";
    public static void method(){
        System.out.println("loadClass的静态方法");
    }
}*/
}
