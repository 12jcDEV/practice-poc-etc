/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test.webcrawler.manager;

import com.test.webcrawler.model.ImageDTO;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author jose
 */
public interface ImageManager {

    public List<ImageDTO> getImageData(String url) throws IOException;
    
}
