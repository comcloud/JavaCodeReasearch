package com.cloud.tree;

import java.io.*;
import java.util.*;

/**
 * @version v1.0
 * @ClassName HuffmanCode
 * @Author rayss
 * @Datetime 2021/5/31 3:43 下午
 */

public class HuffmanCode {

    public static void main(String[] args) {
        String srcFilePath = "/Users/rayss/IdeaProjects/JavaCodeReasearch/src/test/java/com/cloud/MainTest/TestAllMethod.java";
        String dstFilePath = "/Users/rayss/IdeaProjects/JavaCodeReasearch/src/test/java/com/cloud/MainTest/TestAllMethod.zip";
        zipFile(srcFilePath, dstFilePath);

        unzipFile(dstFilePath,"/Users/rayss/IdeaProjects/JavaCodeReasearch/src/test/java/com/cloud/MainTest/TestAllMethod1.java");


//        String content = "i like like like java do you like a java";
//        byte[] zip = huffmanZip(content.getBytes());
//        System.out.println(Arrays.toString(zip));
//
//        byte[] decode = decode(HUFFMAN_CODES, zip);
//        System.out.println(new String(decode));
    }

    /**
     * 压缩文件
     *
     * @param srcFilePath 文件源路径
     * @param dstFilePath 文件的输出路径
     */
    private static void zipFile(String srcFilePath, String dstFilePath) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(srcFilePath));
             BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(dstFilePath));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            byte[] huffmanByte = huffmanZip(b);
            //使用对象输出流将哈夫曼编码写出，为了以后恢复文件使用
            objectOutputStream.writeObject(huffmanByte);
            //将哈夫曼编码写出
            objectOutputStream.writeObject(HUFFMAN_CODES);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void unzipFile(String srcFilePath, String dstFilePath) {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(dstFilePath));
             BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(srcFilePath));
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            byte[] huffmanByte = (byte[]) objectInputStream.readObject();
            @SuppressWarnings("unchecked") Map<Byte, String> huffmanCodes = (Map<Byte, String>) objectInputStream.readObject();
            byte[] decode = decode(huffmanCodes, huffmanByte);
            outputStream.write(decode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 对字节数组进行哈夫曼编码
     *
     * @param contentBytes 原字节数组
     * @return 经过哈夫曼编码过的字节数组
     */
    private static byte[] huffmanZip(byte[] contentBytes) {
        //将每个字节数据封装位node节点
        List<Node> nodeList = getNodeList(contentBytes);
        //根据这些node节点创建哈夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodeList);
        //设置哈夫曼编码到HUFFMAN_CODES中
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);

        //压缩
        return zip(contentBytes, huffmanCodes);
    }

    /*
     * --------------------------- unzip-----------------------
     */

    /**
     * @param huffmanCodes 哈夫曼编码表
     * @param huffmanBytes 哈夫曼编码值
     * @return 结果字节数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < huffmanBytes.length; i++) {
            builder.append(byteToBitString(i != huffmanBytes.length - 1, huffmanBytes[i]));
        }
        //反转原先的哈夫曼编码对应表,字符-编码->编码-编码
        Map<String, Byte> map = new HashMap<>(huffmanCodes.size());
        huffmanCodes.forEach((k, v) -> map.put(v, k));

        List<Byte> byteList = new ArrayList<>();
        for (int i = 0; i < builder.length(); ) {
            int count = 0;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //每次尝试获取
                String key = builder.substring(i, count + i);
                //尝试获取
                b = map.get(key);
                if (b != null) {
                    flag = false;
                } else {
                    count++;
                }
            }
            byteList.add(b);
            i += count;
        }
        byte[] b = new byte[byteList.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = byteList.get(i);
        }
        return b;
    }


    /**
     * @param flag 标记是否需要被补高位，如果是最后一个字节不用进行补高位，因为当初压缩时候对于最后一个字节数据没有进行确保一定是8个字节进行存储
     * @param b    字节数据
     * @return 转换字节数据之后的二进制
     */
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            //是否需要进行补高位
            temp |= 256;
        }
        //转换为二进制
        String binaryString = Integer.toBinaryString(temp);
        return flag ? binaryString.substring(binaryString.length() - 8) : binaryString;
    }

    /*
     * --------------------------- zip-----------------------
     */

    private static final Map<Byte, String> HUFFMAN_CODES = new HashMap<>();
    private static final StringBuilder BUILDER = new StringBuilder();

    /**
     * @param b            哈夫曼编码字节数组
     * @param huffmanCodes 哈夫曼编码表
     * @return 压缩后的字节数组
     */
    private static byte[] zip(byte[] b, Map<Byte, String> huffmanCodes) {
        StringBuilder builder = new StringBuilder();
        for (byte b1 : b) {
            builder.append(huffmanCodes.get(b1));
        }
        int builderLength = builder.length();
        int len = builder.length() % 8 == 0 ? builderLength / 8 : builderLength / 8 + 1;
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        for (int i = 0, count = 0; i < builderLength; i += 8, count++) {
            //每次取8位，但是因为不可以保证最后一次不一定是8位，所以这里做一个判断
            String strByte = i + 8 > builderLength ? builder.substring(i) : builder.substring(i, i + 8);
            //将strByte转换byte存储
            huffmanCodeBytes[count] = (byte) Integer.parseInt(strByte, 2);

        }
        return huffmanCodeBytes;
    }

    /**
     * 获取哈弗曼编码的重载
     *
     * @param root 根节点
     */
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        return getCodes(root, "", BUILDER);
    }

    /**
     * 获取对应的路径值
     *
     * @param node    获取的节点
     * @param code    路径，1表示向右，0表示向左
     * @param builder 存储路径值的缓冲区
     */
    private static Map<Byte, String> getCodes(Node node, String code, StringBuilder builder) {
        StringBuilder stringBuilder = new StringBuilder(builder);
        //添加此时的code值
        stringBuilder.append(code);
        //如果是null不处理
        if (node != null) {
            //非叶子节点
            if (node.data == null) {
                //向左递归
                getCodes(node.left, "0", stringBuilder);
                //向右递归
                getCodes(node.right, "1", stringBuilder);
            } else {
                //说明此时是一个叶子节点
                HUFFMAN_CODES.put(node.data, stringBuilder.toString());
            }
        }
        return HUFFMAN_CODES;
    }

    /**
     * 将字节数组转换为对应的节点集合，这里会计算每个字节出现的次数并封装为node存储
     *
     * @param contentBytes 字节数组
     * @return 将字节数组转换为对应的node集合
     */
    private static List<Node> getNodeList(byte[] contentBytes) {
        List<Node> nodes = new ArrayList<>();
        Map<Byte, Integer> map = new HashMap<>(12);
        for (byte contentByte : contentBytes) {
            map.merge(contentByte, 1, Integer::sum);
        }
        map.forEach((k, v) -> nodes.add(new Node(k, v)));
        return nodes;
    }

    /**
     * 返回哈夫曼树头节点
     *
     * @param nodes 节点集合
     * @return 哈夫曼树头节点
     */
    public static Node createHuffmanTree(List<Node> nodes) {

        while (nodes.size() > 1) {
            //排序
            Collections.sort(nodes);

            //取出最小权值的两颗二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //构建一个新的父节点
            Node parent = new Node(leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //移除list中处理过的节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //新建的节点添加到集合中
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * 节点类
     */
    private static class Node implements Comparable<Node> {
        //字节数据
        private Byte data;
        //权重
        private final int weight;
        private Node left;
        private Node right;

        public Node(int weight) {
            this.weight = weight;
        }

        public Node(Byte data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }

        public void preOrder() {
            System.out.println(this);
            if (this.left != null) {
                this.left.preOrder();
            }
            if (this.right != null) {
                this.right.preOrder();
            }
        }
    }
}
