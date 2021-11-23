/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.designpattern.combination;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :Client.java v1.0 2021/8/21 6:15 下午 zhangyulei Exp $
 */
public class Client {

    public static void main(String[] args) {
        /*
         * /
         * /wz/
         * /wz/a.txt
         * /wz/b.txt
         * /wz/movies/
         * /wz/movies/c.avi
         * /xzg/
         * /xzg/docs/
         * /xzg/docs/d.txt
         */
        Directory fileSystemTree = new Directory("/");
        Directory node_wz = new Directory("/wz/");
        Directory node_xzg = new Directory("/xzg/");
        fileSystemTree.addSubNode(node_wz);
        fileSystemTree.addSubNode(node_xzg);

        File node_wz_a = new File("/wz/a.txt");
        File node_wz_b = new File("/wz/b.txt");
        Directory node_wz_movies = new Directory("/wz/movies/");
        node_wz.addSubNode(node_wz_a);
        node_wz.addSubNode(node_wz_b);
        node_wz.addSubNode(node_wz_movies);

        File node_wz_movies_c = new File("/wz/movies/c.avi");
        node_wz_movies.addSubNode(node_wz_movies_c);

        Directory node_xzg_docs = new Directory("/xzg/docs/");
        node_xzg.addSubNode(node_xzg_docs);

        File node_xzg_docs_d = new File("/xzg/docs/d.txt");
        node_xzg_docs.addSubNode(node_xzg_docs_d);

        System.out.println("/ files num:" + fileSystemTree.countNumOfFiles());
        System.out.println("/wz/ files num:" + node_wz.countNumOfFiles());
    }

}
