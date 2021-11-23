package com.cloud.MainTest.datastructure;

/**
 * @version v1.0
 * @ClassName DoubleLinkListDemo
 * @Author rayss
 * @Datetime 2021/5/10 3:22 下午
 */

public class DoubleLinkListDemo {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(new HeroNode(2, "林冲", "豹子头", null, null));
        list.add(new HeroNode(1, "宋江", "及时雨", null, null));
        list.add(new HeroNode(4, "吴用", "智多星", null, null));
        list.add(new HeroNode(3, "鲁智深", "林冲弟弟", null, null));
        list.list();
    }

    private static class LinkedList {

        private final HeroNode HEAD = new HeroNode(0, "", "", null, null);

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

        public void add(HeroNode node) {
            HeroNode temp = HEAD;
            while (temp.next != null && temp.next.no < node.no) {
                temp = temp.next;
            }
            HeroNode next = temp.next;
            temp.next = node;
            node.prev = temp;
            if (next != null) {
                node.next = next;
                next.prev = node;
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
            HeroNode temp = HEAD.next;
            while (temp != null) {
                if (temp.no == no) {
                    temp.prev = temp.next;
                    if (temp.next != null) {
                        temp.next.prev = temp.prev;
                    }
                    break;
                }
                temp = temp.next;
            }
        }
    }

    private static class HeroNode {
        private int no;
        private String name;
        private String nickname;
        private HeroNode next;
        private HeroNode prev;

        public HeroNode(int no, String name, String nickname, HeroNode next, HeroNode prev) {
            this.no = no;
            this.name = name;
            this.nickname = nickname;
            this.next = next;
            this.prev = prev;
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
