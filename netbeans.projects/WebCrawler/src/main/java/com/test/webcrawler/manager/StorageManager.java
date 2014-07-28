/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test.webcrawler.manager;

import com.test.webcrawler.model.ImageDTO;
import com.test.webcrawler.model.ResultDTO;
import java.util.List;

/**
 *
 * @author jose
 */
public interface StorageManager {

    public ResultDTO getImagesFromRemote(List<ImageDTO> list);
    
    public Boolean writeToDisk();
    
}
