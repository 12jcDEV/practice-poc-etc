/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test.webcrawler.manager;

import com.test.webcrawler.model.ImageDTO;
import com.test.webcrawler.model.ResultDTO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author jose
 */
public interface StorageManager {

    public ResultDTO downloadImagesFromRemote(List<ImageDTO> list) throws FileNotFoundException, IOException;
    
    public void setStoragePath(String path);
    
}
