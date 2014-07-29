/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test.webcrawler.manager;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author jose
 */
public interface StorageManager {

    public void downloadImagesFromRemote(String imageURL) throws IOException;

     public void disconnect() throws IOException;
     
     public InputStream getInputStream();
     
     public int getContentLength();
    
}
