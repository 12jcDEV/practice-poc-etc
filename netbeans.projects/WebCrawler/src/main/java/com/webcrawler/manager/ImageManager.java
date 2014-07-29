/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webcrawler.manager;

import com.webcrawler.model.ImageDTO;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author jose
 */
public interface ImageManager {

    public List<ImageDTO> getImageData() throws IOException, IllegalArgumentException,  InterruptedException, ExecutionException;
    
    public void setUrl(String url);
    
}
