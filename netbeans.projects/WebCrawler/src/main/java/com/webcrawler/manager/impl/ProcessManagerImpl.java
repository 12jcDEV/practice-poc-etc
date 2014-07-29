/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webcrawler.manager.impl;

import com.webcrawler.WebCrawlerMain;
import com.webcrawler.manager.ImageManager;
import com.webcrawler.manager.ProcessManager;
import com.webcrawler.manager.URLManager;
import com.webcrawler.model.ResultDTO;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.StringUtils;
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

    private DownloadTask downloadTask;

    private WebCrawlerMain parentComponent;

    private ExecutorService executorService;

    @Override
    public ResultDTO processDownload(String url, String folderLocation) {

        //check first if url is valid
        if (uRLManager.validateURL(url)) {

            try {
                imageManager.setUrl(url);

                downloadTask = new DownloadTask(new StorageManagerImpl());

                downloadTask.setDownloadFolder(folderLocation);
                downloadTask.setImages(imageManager.getImageData());
                downloadTask.setUi(parentComponent);

                downloadTask.addPropertyChangeListener(new PropertyChangeListener() {

                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {

                        if (evt.getPropertyName().equals("progress")) {

                            int progress = (Integer) evt.getNewValue();
                            ProcessManagerImpl.this.parentComponent.getProgBarDownload().setValue(progress);
                        }

                    }
                });
                parentComponent.getProgBarDownload().setValue(0);
                downloadTask.execute();

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
            JOptionPane.showMessageDialog(parentComponent, "Invalid URL, please enter a valid URL");

        }

        parentComponent.enableSearching();

        return null;
    }

    public Component getParentComponent() {
        return parentComponent;
    }

    public void setParentComponent(WebCrawlerMain parentComponent) {
        this.parentComponent = parentComponent;
    }

}
