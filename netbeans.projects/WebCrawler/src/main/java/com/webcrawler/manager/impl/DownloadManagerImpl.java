/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webcrawler.manager.impl;

import com.webcrawler.manager.DownloadManager;
import com.webcrawler.manager.StorageManager;
import com.webcrawler.manager.UIManager;
import com.webcrawler.model.ImageDTO;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.swing.SwingWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose
 */
@Service("downloadManager")
public class DownloadManagerImpl implements DownloadManager {

    @Autowired
    private StorageManager storageManager;

    private String folderLocation;
    private List<ImageDTO> images;
    private UIManager ui;
    private ImageDownloaderTask task;

    @Override
    public void processDownload(String folderLocation, List<ImageDTO> images, UIManager ui, PropertyChangeListener listener) throws Exception {

        this.folderLocation = folderLocation;
        this.images = images;
        this.ui = ui;

        task = new ImageDownloaderTask();
        task.addPropertyChangeListener(listener);

        task.execute();

    }

    private class ImageDownloaderTask extends SwingWorker<Void, Integer> {

        private static final int BUFFER_SIZE = 4096;

        @Override
        protected Void doInBackground() throws Exception {

            System.out.println("Calling doInBackground...");

            if (StringUtils.isBlank(folderLocation)) {
                cancel(true);
                throw new IllegalArgumentException("set download folder first");
            }

            if (ui == null) {
                cancel(true);
                throw new IllegalArgumentException("UI is null, assign a UI first");
            }

            if (images == null || images.isEmpty()) {
                cancel(true);
                throw new IllegalArgumentException("No Images Found!");
            }

            //check if folder exists
            File path = new File(folderLocation);
            if (!path.exists() || !path.isDirectory()) {
                throw new IllegalArgumentException("Folder location does not exist!");
            }

            InputStream is = null;
            FileOutputStream fos = null;
            byte[] buffer = null;

            try {

                imagetraverse:
                for (ImageDTO dto : images) {
                    System.out.println("Retrieving file " + dto.getUrlAddress());
                    storageManager.downloadImagesFromRemote(dto.getUrlAddress());
                    ui.setUIFileInfo(dto.getFileName(), storageManager.getContentLength());

                    is = storageManager.getInputStream();

                    try {
                        fos = new FileOutputStream(folderLocation + File.separator + dto.getFileName());
                    } catch (IOException ie) {
                        ie.printStackTrace();
                        System.out.println("Error with the file " + dto.getFileName() + " skipping this file....");
                        break imagetraverse;
                    }

                    buffer = new byte[BUFFER_SIZE];
                    int bytesRead = -1;
                    int totalBytesRead = 0;
                    int percentCompleted = 0;

                    while ((bytesRead = is.read(buffer)) != -1) {

                        fos.write(buffer, 0, bytesRead);
                        totalBytesRead += bytesRead;
                        percentCompleted = (int) (totalBytesRead * 100 / storageManager.getContentLength());

                        publish(percentCompleted);
                    }

                    fos.close();
                    storageManager.disconnect();

                }
            } catch (IOException ex) {

                ex.printStackTrace();
                ui.showErrorMessageDialog("Error downloading image: " + ex.getMessage(), "Error");

                publish(0);
                cancel(true);
            }

            return null;
        }

        @Override
        protected void done() {

            if (isCancelled()) {

                ui.showErrorMessageDialog("Download Failed!", "Error");
            } else {

                ui.showMessageDialog("Images have been downloaded successfully!", "Success!");
            }

            ui.enableSearching();
        }

        @Override
        protected void process(List<Integer> chunks) {
            for (Integer chunk : chunks) {
                ui.setProgressBarValue(chunk);
            }
        }
    }
}
