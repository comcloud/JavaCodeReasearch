package com.cloud.designpattern.facademodel;

import java.io.File;

/**
 * @author rayss
 */
public class VideoConversionFacade {
    public File convertVideo(String fileName, String format) {
        //门面模式，封装了视频转换的复杂逻辑
        System.out.println("VideoConversionFacade: conversion started.");
        VideoFile file = new VideoFile(fileName);
        Codec sourceCodec = CodecFactory.extract(file);
        Codec destinationCodec;
        if ("mp4".equals(format)) {
            destinationCodec = new OggCompressionCodec();
        } else {
            destinationCodec = new MPEG4CompressionCodec();
        }
        VideoFile buffer = BitrateReader.read(file, sourceCodec);
        VideoFile intermediateResult = BitrateReader.convert(buffer, destinationCodec);
        File result = (new AudioMixer()).fix(intermediateResult);
        System.out.println("VideoConversionFacade: conversion completed.");
        return result;
    }
}