/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test.webcrawler.manager;

import com.test.webcrawler.WebCrawlerMain;
import com.test.webcrawler.model.ResultDTO;

/**
 *
 * @author jose
 */
public interface ProcessManager {

    public ResultDTO processDownload(String url, String folderLocation);
    public void setParentComponent(WebCrawlerMain parentComponent);
    
}
