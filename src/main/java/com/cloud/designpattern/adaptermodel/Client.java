package com.cloud.designpattern.adaptermodel;

import java.util.ArrayList;

/**
 * @version v1.0
 * @ClassName Client
 * @Author rayss
 * @Datetime 2021/5/28 6:53 下午
 */

public class Client {
    public static void main(String[] args) {
        System.out.println("适配器模式测试：");
        Motor motor = (Motor) ReadXML.getObject();
        motor.drive();
    }

    //目标：发动机
    interface Motor {
        public void drive();
    }

    //适配者1：电能发动机
    static class ElectricMotor {
        public void electricDrive() {
            System.out.println("电能发动机驱动汽车！");
        }
    }

    //适配者2：光能发动机
    static class OpticalMotor {
        public void opticalDrive() {
            System.out.println("光能发动机驱动汽车！");
        }
    }

    //电能适配器
    static class ElectricAdapter implements Motor {
        private ElectricMotor emotor;

        public ElectricAdapter() {
            emotor = new ElectricMotor();
        }

        @Override
        public void drive() {
            emotor.electricDrive();
        }
    }

    //光能适配器
    static class OpticalAdapter implements Motor {
        private OpticalMotor omotor;

        public OpticalAdapter() {
            omotor = new OpticalMotor();
        }

        @Override
        public void drive() {
            omotor.opticalDrive();
        }
    }

    static class ReadXML {
        public static Object getObject() {
            return new OpticalMotor();
        }
    }
}
