package com.cloud.MainTest.datastructure;

/**
 * @version v1.0
 * @ClassName AnnularLinkedListDemo
 * @Author rayss
 * @Datetime 2021/5/11 5:20 下午
 */

public class AnnularLinkedListDemo {

    public static void main(String[] args) {
        AnnularLinkedList list = new AnnularLinkedList();
        list.add(5);
        list.josephProblem(1,2,5);
    }

    private static class AnnularLinkedList {
        private Boy first;

        public void add(int num) {
            if (num < 1) {
                System.out.println("创建数量不正确");
            }

            //这个是记录最后一个节点
            Boy currBoy = null;
            for (int i = 1; i <= num; i++) {
                Boy boy = new Boy(i);
                if (i == 1) {
                    first = boy;
                    //构建环状，此时是第一个节点，所以也是指向自己
                    first.next = first;
                    currBoy = first;
                } else {
                    currBoy.next = boy;
                    boy.next = first;
                    currBoy = boy;
                }
            }
        }

        public void showBoy() {
            if (first == null) {
                System.out.println("空环形链表");
            } else {
                for (Boy curr = first; ; ) {
                    System.out.print(curr.no + "\t");
                    if ((curr = curr.next) == first) {
                        break;
                    }
                }
            }
        }

        /**
         * 约瑟夫环问题
         * 这里定义一个辅助指针：helper：用来指向first上一个节点，
         *
         * @param startNo  从第几个位置开始
         * @param countNum 每次查几个
         * @param sum      总共多少个
         */
        public void josephProblem(int startNo, int countNum, int sum) {
            if (startNo < 0 || sum < 0) {
                System.out.println("传入的数据不正确");
            } else if (first == null) {
                return;
            }

            Boy helper = first;
            while (helper.next != first) {
                helper = helper.next;
            }

            //先移到要开始的位置
            for (int i = 0; i < startNo - 1; i++) {
                first = first.next;
                helper = helper.next;
            }
            while (helper != first) {
                for (int i = 0; i < countNum - 1; i++) {
                    first = first.next;
                    helper = helper.next;
                }
                System.out.print(first.no + "\t");
                first = first.next;
                helper.next = first;
            }
            System.out.println("\n留在最后面的编号是helper.no = " + "留在最后面的编号是" + helper.no);
        }
    }

    private static class Boy {
        private int no;
        private Boy next;

        public Boy(int no) {
            this.no = no;
        }
    }
}
