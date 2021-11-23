package com.cloud.MainTest.jvm;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * @version v1.0
 * @ClassName MetaspaceOOM
 * @Author rayss
 * @Datetime 2021/5/5 5:14 下午
 */

public class MetaspaceOOM extends ClassLoader {

    private String fieldName = "123";

    public static void main(String[] args) {
        MetaspaceOOM oom = new MetaspaceOOM();
        int count = 0;

        for (int i = 0; i < 10000; i++) {
            ClassWriter classWriter = new ClassWriter(0);
            classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
            byte[] code = classWriter.toByteArray();
            oom.defineClass("Class" + i, code, 0, code.length);
            count++;
        }
        System.out.println("总共加载了" + count + "个类");
    }
}
