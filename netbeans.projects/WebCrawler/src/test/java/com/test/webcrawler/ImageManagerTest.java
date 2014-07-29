/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.webcrawler;

import com.test.webcrawler.manager.ImageManager;
import com.test.webcrawler.model.ImageDTO;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jose
 */
public class ImageManagerTest extends BaseTest {

    @Autowired
    private ImageManager imageManager;

    public ImageManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test(expected =  ExecutionException.class)
    public void testGetImageData() throws Exception {

        imageManager.setUrl("http://www.imgur.com");
        List<ImageDTO> images = imageManager.getImageData();
        assertNotNull(images);
        assertTrue(images.size() > 0);

        imageManager.setUrl("blablablalb");
        images = imageManager.getImageData();
        assertNotNull(images);
        assertTrue(images.size() == 0);

    }

}
