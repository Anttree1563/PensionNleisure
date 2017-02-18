package com.truemind.pensionnleisure;

import java.io.File;

/**
 * Created by 현석 on 2017-02-17.
 */
public class FileData {
    File image;

    public FileData(File image) {
        this.image = image;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
}
