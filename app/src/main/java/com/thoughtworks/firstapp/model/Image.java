
package com.thoughtworks.firstapp.model;

import com.google.gson.annotations.Expose;

public class Image {

    @Expose
    private String url;

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public com.thoughtworks.firstapp.model.db.Image toImage() {
        com.thoughtworks.firstapp.model.db.Image image = new com.thoughtworks.firstapp.model.db.Image();
        image.setUrl(url);
        return image;
    }

    public static Image fromImage(com.thoughtworks.firstapp.model.db.Image dbImage) {
        Image image = new Image();
        image.setUrl(image.getUrl());
        return image;
    }

}
