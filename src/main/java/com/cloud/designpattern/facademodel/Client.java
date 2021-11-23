package com.cloud.designpattern.facademodel;

import java.io.File;

/**
 * @version v1.0
 * @ClassName Client
 * @Author rayss
 * @Datetime 2021/8/7 9:25 下午
 */

public class Client {

    public static void main(String[] args) {
        VideoConversionFacade converter = new VideoConversionFacade();
        File mp4Video = converter.convertVideo("youtubevideo.ogg", "mp4");
    }
}
