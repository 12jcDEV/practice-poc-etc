/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webcrawler.manager.impl;

import com.webcrawler.manager.DownloadManager;
import com.webcrawler.manager.ImageManager;
import com.webcrawler.manager.ProcessManager;
import com.webcrawler.manager.UIManager;
import com.webcrawler.manager.URLManager;
import com.webcrawler.model.ResultDTO;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose
 */
@Service("processManager")
public class ProcessManagerImpl implements ProcessManager, PropertyChangeListener {

    @Autowired
    private URLManager uRLManager;

    @Autowired
    private ImageManager imageManager;

    @Autowired
    private DownloadManager downloadManager;

    private UIManager parentComponent;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (evt.getPropertyName().equals("progress")) {
            this.parentComponent.setProgressBarValue((Integer) evt.getNewValue());
        }

    }

    @Override
    public ResultDTO processDownload(String url, String folderLocation) {

        //check first if url is valid
        if (uRLManager.validateURL(url)) {

            try {
                parentComponent.resetProgressBar();
                downloadManager.processDownload(folderLocation, imageManager.getImageData(url), parentComponent);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            parentComponent.showErrorMessageDialog("Invalid URL, please enter a valid URL", "Error");
        }

        parentComponent.enableSearching();

        return null;
    }

    public UIManager getParentComponent() {
        return parentComponent;
    }

    public void setParentComponent(UIManager parentComponent) {
        this.parentComponent = parentComponent;
    }

}
