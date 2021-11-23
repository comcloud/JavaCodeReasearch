package com.cloud.designpattern.facademodel;

import java.io.File;

/**
 * @author rayss
 */
public class AudioMixer {
    public File fix(VideoFile result){
        System.out.println("AudioMixer: fixing audio...");
        return new File("tmp");
    }
}