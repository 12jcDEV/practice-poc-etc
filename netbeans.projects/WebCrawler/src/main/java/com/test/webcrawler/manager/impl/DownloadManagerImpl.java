/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test.webcrawler.manager.impl;

import com.test.webcrawler.WebCrawlerMain;
import com.test.webcrawler.manager.DownloadManager;
import com.test.webcrawler.model.ImageDTO;
import java.util.List;
import javax.swing.SwingWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose
 */
@Service("downloadManager")
public class DownloadManagerImpl extends SwingWorker<Void, Void> implements DownloadManager {

    private static final int BUFFER_SIZE = 4096;
    private String downloadFolder;
    private WebCrawlerMain ui;
    private List<ImageDTO> images;
    
    public DownloadManagerImpl(){}
    
    @Override
    protected Void doInBackground() throws Exception {
        if(StringUtils.isBlank(downloadFolder))
            throw new IllegalArgumentException("set download folder first");
        
        if(ui == null)
            throw new IllegalArgumentException("UI is null, assign a UI first");
        
        if(images == null)
            throw new IllegalArgumentException("Set the image location first!");
        
        
    }

    public String getDownloadFolder() {
        return downloadFolder;
    }

    public void setDownloadFolder(String downloadFolder) {
        this.downloadFolder = downloadFolder;
    }

    public WebCrawlerMain getUi() {
        return ui;
    }

    public void setUi(WebCrawlerMain ui) {
        this.ui = ui;
    }

    public List<ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }
    
    
    
}
