/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webcrawler.manager.impl;

import com.webcrawler.manager.ImageManager;
import com.webcrawler.model.ImageDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose
 */
@Service("imageManager")
public class ImageManagerImpl implements ImageManager {

    private ExecutorService executorService;

    public ImageManagerImpl() {
        executorService = Executors.newCachedThreadPool();
    }

    @Override
    public List<ImageDTO> getImageData(final String url) throws IOException, IllegalArgumentException, InterruptedException, ExecutionException {

        if (url == null || url.equals("")) {
            throw new IllegalArgumentException("Set URL first");
        }

        Callable<List<ImageDTO>> callable = new Callable<List<ImageDTO>>() {

            @Override
            public List<ImageDTO> call() throws Exception {
                System.out.println("Retrieving image data from url " + url);

                Document document = null;
                Elements media = null;
                 List<ImageDTO> images = new ArrayList<ImageDTO>();
                try {
                    document = Jsoup.connect(url).get();
                    media = document.select("[src]");
                } catch (Exception e) {
                    e.printStackTrace();
                    return images;
                }

               

                System.out.println("# of images: " + media.size());

                for (Element src : media) {
                    if (src.tagName().equals("img")) {
                        ImageDTO dto = new ImageDTO();
                        dto.setUrlAddress(src.attr("abs:src"));
                        dto.setFileName(getFileName(src.attr("abs:src")));
                        images.add(dto);
                    }
                }

                return images;
            }
        };

        Future<List<ImageDTO>> result = executorService.submit(callable);

        return result.get();

    }

    private String getFileName(String longName) {

        int indexname = longName.lastIndexOf("/");
        if (indexname == longName.length()) {
            longName = longName.substring(1, indexname);
        }

        indexname = longName.lastIndexOf("/");
        String name = longName.substring(indexname + 1, longName.length());

        return name;
    }

}
