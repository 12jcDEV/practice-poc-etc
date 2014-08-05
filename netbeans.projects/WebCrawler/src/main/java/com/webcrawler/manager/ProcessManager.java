/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webcrawler.manager;

import com.webcrawler.model.ResultDTO;

/**
 *
 * @author jose
 */
public interface ProcessManager {

    public ResultDTO processDownload(String url, String folderLocation);
    public void setParentComponent(UIManager parentComponent);
    
}
