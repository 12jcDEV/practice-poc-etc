/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.webcrawler.model;

import java.util.Objects;

/**
 *
 * @author jose
 */
public class ImageDTO {

    private String fileName;
    private String urlAddress;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.fileName);
        hash = 79 * hash + Objects.hashCode(this.urlAddress);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ImageDTO other = (ImageDTO) obj;
        if (!Objects.equals(this.fileName, other.fileName)) {
            return false;
        }
        if (!Objects.equals(this.urlAddress, other.urlAddress)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ImageDTO{" + "fileName=" + fileName + ", urlAddress=" + urlAddress + '}';
    }

}
