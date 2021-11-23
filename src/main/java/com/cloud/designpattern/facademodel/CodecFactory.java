package com.cloud.designpattern.facademodel;

/**
 * @author rayss
 */
public class CodecFactory {
    public static Codec extract(VideoFile file) {
        String type = file.getCodecType();
        if ("mp4".equals(type)) {
            System.out.println("CodecFactory: extracting mpeg audio...");
            return new MPEG4CompressionCodec();
        }
        else {
            System.out.println("CodecFactory: extracting ogg audio...");
            return new OggCompressionCodec();
        }
    }
}