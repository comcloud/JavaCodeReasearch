package com.cloud.MainTest.datastructure;

import java.util.Arrays;

/**
 * @version v1.0
 * @ClassName LinkListDemo
 * @Author rayss
 * @Datetime 2021/5/9 8:45 上午
 */

public class SingleLinkListDemo {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(new HeroNode(2, "林冲", "豹子头", null));
        list.add(new HeroNode(1, "宋江", "及时雨", null));
        list.add(new HeroNode(4, "吴用", "智多星", null));
        list.add(new HeroNode(3, "鲁智深", "林冲弟弟", null));
        list.del(3);
        list.list();
//        list.list();
//        list.updateNode(new HeroNode(3, "武松", "林冲哥哥", null));
//        list.list();
//        System.out.println(list.findLastNode(3));
//        HeroNode reverse = list.reverse();
//        list.list(reverse);
//        list.printLinkedListFromRear();
    }


    private static class LinkedList {
        private final HeroNode HEAD = new HeroNode(0, "", "", null);

        public void add(HeroNode node) {
            HeroNode temp = HEAD;
            while (temp.next != null && temp.next.no < node.no) {
                temp = temp.next;
            }
            HeroNode next = temp.next;
            temp.next = node;
            temp.next.next = next;
        }

        public void list() {
            HeroNode temp = HEAD;
            while (temp.next != null) {
                System.out.println(temp.next);
                temp = temp.next;
            }
        }

        public void list(HeroNode head) {
            HeroNode temp = head;
            while (temp.next != null) {
                System.out.println(temp.next);
                temp = temp.next;
            }
        }

        public void updateNode(HeroNode node) {
            HeroNode temp = HEAD;
            boolean exist = false;
            while (temp.next != null) {
                temp = temp.next;
                if (temp.no == node.no) {
                    exist = true;
                    break;
                }
            }
            if (exist) {
                temp.name = node.name;
                temp.nickname = node.nickname;
            } else {
                System.out.println("给定的no不存在");
            }
        }

        public void del(int no) {
            HeroNode temp = HEAD;
            while (temp.next != null) {
                if(temp.next.no == no){
                    temp.next = temp.next.next;
                }
                temp = temp.next;
            }
        }

            public int getLength () {
                HeroNode temp = HEAD;
                int length = 0;
                while (temp.next != null) {
                    length++;
                    temp = temp.next;
                }
                return length;
            }

            //倒数index个节点
            public HeroNode findLastNode ( int index){
                int length = getLength();
                if (index <= 0 || index >= length) {
                    return null;
                }
                int ascIndex = length - index;
                HeroNode currentNode = HEAD.next;
                for (int i = 0; i < ascIndex; i++) {
                    currentNode = currentNode.next;
                }
                return currentNode;
            }

            //倒置链表
            public HeroNode reverse () {
                HeroNode newHead = new HeroNode(0, "", "", null);
                HeroNode temp = HEAD.next;
                while (temp != null) {
                    //保留原有链表的下一个节点
                    HeroNode justNext = temp.next;
                    //原链表节点的下个指向改为新链表首部的下个指向
                    temp.next = newHead.next;
                    //将构建好的下个指向改为原有节点
                    newHead.next = temp;
                    //让此时改为原有节点下个指向内容
                    temp = justNext;
                }
                return newHead;
            }

            //从尾到头打印链表信息，这里我们使用链表方式
            public void printLinkedListFromRear () {
                recursion(HEAD.next);
            }

            private void recursion (HeroNode head){
                if (head == null) {
                    return;
                }
                recursion(head.next);
                System.out.println(head.name);
            }
        }


        private static class HeroNode {
            private int no;
            private String name;
            private String nickname;
            private HeroNode next;

            public HeroNode(int no, String name, String nickname, HeroNode next) {
                this.no = no;
                this.name = name;
                this.nickname = nickname;
                this.next = next;
            }

            @Override
            public String toString() {
                return "HeroNode{" +
                        "no=" + no +
                        ", name='" + name + '\'' +
                        ", nickname='" + nickname + '\'' +
                        '}';
            }
        }
    }
