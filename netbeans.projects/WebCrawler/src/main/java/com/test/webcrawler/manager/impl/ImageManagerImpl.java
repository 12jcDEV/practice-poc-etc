/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.webcrawler.manager.impl;

import com.test.webcrawler.manager.ImageManager;
import com.test.webcrawler.model.ImageDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<ImageDTO> getImageData(String url) throws IOException {

        System.out.println("Retrieving image data from url " + url);

        Document document = Jsoup.connect(url).get();
        Elements media = document.select("[src]");

        List<ImageDTO> images = new ArrayList<ImageDTO>();

        System.out.println("# of images: " + media.size());

        for (Element src : media) {
            if (src.tagName().equals("img")) {
                ImageDTO dto = new ImageDTO();
                dto.setUrlAddress(src.attr("abs:src"));
                dto.setFileName(getFileName(src.attr("abs:src")));
                System.out.println(dto);
                images.add(dto);
            }
        }

        return images;
    }

    private String getFileName(String longName) {

        int indexname = longName.lastIndexOf("/");
        if (indexname == longName.length()) {
            longName = longName.substring(1, indexname);
        }

        indexname = longName.lastIndexOf("/");
        String name = longName.substring(indexname, longName.length());

        return name;
    }
}
