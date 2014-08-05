/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webcrawler.manager;

import com.webcrawler.model.ImageDTO;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 *
 * @author jose
 */
public interface DownloadManager {
    
    public void processDownload(String folderLocation, List<ImageDTO> images, UIManager ui) throws Exception;
}
