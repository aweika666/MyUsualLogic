package com.aweika.testClassload;

/**
 * @author: Michael
 * @date: 2020/1/26
 * @description:
 */
public class Test2{
    public static void main(String[] args) throws ClassNotFoundException {
     /*   Class<String> stringClass = String.class;
        ClassLoader classLoaderString = stringClass.getClassLoader();
        Class<?> caller = Test2.class;
        Class<?> aClass = caller.getClassLoader().loadClass("java.lang.String");
*/

        /*Class<?> loadedClass = new Test2().findLoadedClass("sun.misc.Launcher");
        Launcher launcher = Launcher.getLauncher();
        ClassLoader classLoader = launcher.getClassLoader();
        System.out.println(classLoader);
        String x = Fu.BB;*/

       // Launcher launcher = Launcher.getLauncher();

        Class<?> caller = new Test().getClass();

        ClassLoader parent = Test2.class.getClassLoader().getParent();
        Class<?> parentClass = parent.getClass();
        //Class<?> loadedClass = caller.getClassLoader().loadClass("sun.misc.Launcher.ExtClassLoader");
        Class<?> loadedClass2 = caller.getClassLoader().loadClass("sun.misc.Launcher$ExtClassLoader");
        Class<?> loadedClassLauncher = caller.getClassLoader().loadClass("sun.misc.Launcher");
        Class<?> loadedClassString = caller.getClassLoader().loadClass("java.lang.String");
        System.out.println(parent);
    }
}
