/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webcrawler.manager.impl;

import com.webcrawler.WebCrawlerMain;
import com.webcrawler.manager.StorageManager;
import com.webcrawler.model.ImageDTO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author jose
 */
public class DownloadTask extends SwingWorker<Void, Integer> {

    private static final int BUFFER_SIZE = 4096;
    private String downloadFolder;
    private WebCrawlerMain ui;
    private List<ImageDTO> images;

    private StorageManager storageManager;

    public DownloadTask(StorageManager storageManager) {
        this.storageManager = storageManager;
    }

    @Override
    protected Void doInBackground() throws Exception {
       
        System.out.println("Calling doInBackground...");
        
        if (StringUtils.isBlank(downloadFolder)) {
            cancel(true);
            throw new IllegalArgumentException("set download folder first");
        }

        if (ui == null) {
             cancel(true);
            throw new IllegalArgumentException("UI is null, assign a UI first");
        }

        if (images == null || images.size() == 0) {
             cancel(true);
            throw new IllegalArgumentException("No Images Found!");
        }

        InputStream is = null;
        FileOutputStream fos = null;
        byte[] buffer = null;

        try {

            for (ImageDTO dto : images) {
                System.out.println("Retrieving file " + dto.getFileName());
                storageManager.downloadImagesFromRemote(dto.getUrlAddress());
                ui.setFileInfo(dto.getFileName(), storageManager.getContentLength());

                is = storageManager.getInputStream();
                fos = new FileOutputStream(downloadFolder + File.separator + dto.getFileName());
                buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                int totalBytesRead = 0;
                int percentCompleted = 0;

                while ((bytesRead = is.read(buffer)) != -1) {

                    fos.write(buffer, 0, bytesRead);
                    totalBytesRead += bytesRead;
                    percentCompleted = (int) (totalBytesRead * 100 / storageManager.getContentLength());

                    //setProgress(percentCompleted);
                     publish(percentCompleted);
                }

                fos.close();
                storageManager.disconnect();

            }
        } catch (IOException ex) {

            ex.printStackTrace();
            JOptionPane.showMessageDialog(ui, "Error downloading image: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            publish(0);
            cancel(true);
        }

        return null;
    }

    @Override
    protected void done() {
        if (isCancelled()) {
           
             JOptionPane.showMessageDialog(ui,
                    "Download Failed!!", "Message",
                    JOptionPane.ERROR_MESSAGE);
        } else {
        
             JOptionPane.showMessageDialog(ui,
                    "Images have been downloaded successfully!", "Message",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        
        ui.enableSearching();
    }

    @Override
    protected void process(List<Integer> chunks) {
        for (Integer chunk : chunks) {
            System.out.println("The chuck is " + chunk);
            ui.getProgBarDownload().setValue(chunk);
        }
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
