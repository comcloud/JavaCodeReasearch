package com.cloud.MainTest.jvm;

import java.io.*;

public class CustomClassLoader extends ClassLoader {
    //这个class文件的根目录，也就是没有带有com.cloud...的绝对路径
    private String root;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        final byte[] classData = loadClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    private byte[] loadClassData(String className) {
        String fileName = root + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        try (InputStream inputStream = new FileInputStream(fileName);
             final ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            int bufferSize = 1024;
            final byte[] buffer = new byte[bufferSize];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getRoot() {
        return root;
    }

    public CustomClassLoader setRoot(String root) {
        this.root = root;
        return this;
    }

    public static void main(String[] args) {
        final CustomClassLoader customClassLoader = new CustomClassLoader();
        
        customClassLoader.setRoot("/Users/rayss/IdeaProjects/JavaCodeReasearch/target/test-classes/");
        Class<?> testClass;
        try {
            testClass = customClassLoader.findClass("com.cloud.MainTest.bean.Person");
            final Object obj = testClass.newInstance();
            System.out.println(obj.getClass().getClassLoader());
            System.out.println(obj);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}