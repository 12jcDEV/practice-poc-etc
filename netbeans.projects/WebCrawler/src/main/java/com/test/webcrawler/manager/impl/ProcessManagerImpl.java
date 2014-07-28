/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.webcrawler.manager.impl;

import com.test.webcrawler.manager.ImageManager;
import com.test.webcrawler.manager.ProcessManager;
import com.test.webcrawler.manager.StorageManager;
import com.test.webcrawler.manager.URLManager;
import com.test.webcrawler.model.ResultDTO;
import java.awt.Component;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    @Autowired
    private ImageManager imageManager;
    
    @Autowired
    private StorageManager storageManager;
    
    private Component parentComponent;
    
    private ExecutorService executorService;

    @Override
    public ResultDTO processDownload(String url, String folderLocation) {

        //check first if url is valid
        if(uRLManager.validateURL(url)) {
        
            try {
                imageManager.setUrl(url);                
                storageManager.setStoragePath("/home/jose/Desktop/crap/crappy");
                storageManager.downloadImagesFromRemote(imageManager.getImageData());
                
            } catch (IOException ex) {
                Logger.getLogger(ProcessManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ProcessManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProcessManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(ProcessManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            JOptionPane.showMessageDialog(parentComponent, "Invalid URL, please enter a valid URL");
        }
        
        return null;
    }

    public Component getParentComponent() {
        return parentComponent;
    }

    public void setParentComponent(Component parentComponent) {
        this.parentComponent = parentComponent;
    }
    
    
    
    

}
