/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.webcrawler.manager.impl;

import com.test.webcrawler.manager.ProcessManager;
import com.test.webcrawler.manager.URLManager;
import com.test.webcrawler.model.ResultDTO;
import java.awt.Component;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose
 */
@Service("processManager")
public class ProcessManagerImpl implements ProcessManager {

    @Autowired
    private URLManager uRLManager;
    
    private Component parentComponent;

    @Override
    public ResultDTO processDownload(String url, String folderLocation) {

        //check first if url is valid
        JOptionPane.showMessageDialog(parentComponent, "Is url valid? " + uRLManager.validateURL(url));
        
        return null;
    }

    public Component getParentComponent() {
        return parentComponent;
    }

    public void setParentComponent(Component parentComponent) {
        this.parentComponent = parentComponent;
    }
    
    
    
    

}
