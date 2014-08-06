/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webcrawler.manager.impl;

import com.webcrawler.manager.StorageManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose
 */
@Service("storageManager")
public class StorageManagerImpl implements StorageManager {

    private String folderPath;

    private HttpURLConnection httpConnection;

    private InputStream inputStream;

    private int contentLength;

    @Override
    public InputStream downloadImagesFromRemote(String imageURL) throws FileNotFoundException, IOException {
        contentLength = 0;
        URL url = new URL(imageURL);
        httpConnection = (HttpURLConnection) url.openConnection();
        int responseCode = httpConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {

            inputStream = httpConnection.getInputStream();
            contentLength = httpConnection.getContentLength();

        } else {

            throw new IOException("Error with url " + imageURL + ": " + responseCode);

        }
        return inputStream;
    }

    public void disconnect() throws IOException {

        inputStream.close();
        httpConnection.disconnect();

    }

    @Override
    public InputStream getInputStream() {

        return inputStream;

    }

    @Override
    public int getContentLength() {
        return contentLength;
    }

}
