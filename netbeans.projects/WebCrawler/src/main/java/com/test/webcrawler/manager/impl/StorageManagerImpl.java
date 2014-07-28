/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.webcrawler.manager.impl;

import com.test.webcrawler.manager.StorageManager;
import com.test.webcrawler.model.ImageDTO;
import com.test.webcrawler.model.ResultDTO;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose
 */
@Service("storageManager")
public class StorageManagerImpl implements StorageManager {

    private String folderPath;

    @Override
    public ResultDTO downloadImagesFromRemote(List<ImageDTO> list) throws FileNotFoundException, IOException {

        URL url = null;
        InputStream in = null;
        OutputStream out = null;

        try {
            for (ImageDTO dto : list) {
                url = new URL(dto.getUrlAddress());
                in = url.openStream();

                out = new BufferedOutputStream(new FileOutputStream(folderPath + "/" + dto.getFileName()));
                for (int b; (b = in.read()) != -1;) {
                    out.write(b);
                    
                }
            }
        } finally {
            in.close();
            out.close();
        }
        
        System.out.println("Downloading complete!");
        return null;
    }

    @Override
    public void setStoragePath(String path) {
        folderPath = path;
    }

}
