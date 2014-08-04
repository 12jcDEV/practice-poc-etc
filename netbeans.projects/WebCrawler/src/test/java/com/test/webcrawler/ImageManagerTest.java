/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.webcrawler;

import com.webcrawler.manager.ImageManager;
import com.webcrawler.model.ImageDTO;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

    @Test
    public void testGetImageData() throws Exception {

        List<ImageDTO> images = imageManager.getImageData("http://www.imgur.com");
        assertNotNull(images);
        assertTrue(images.size() > 0);

        images = imageManager.getImageData("blablablalb");
        assertNotNull(images);
        assertTrue(images.size() == 0);

        
        //test with non existent url
        images = imageManager.getImageData("http://www.sdfuiohsadfiouhasdfuhadiufsahfd.com");
        assertNotNull(images);
        assertTrue(images.isEmpty());
        
        //test with problematic headers
        images = imageManager.getImageData("http://www.theverge.com");
        assertNotNull(images);
        assertTrue(!images.isEmpty());
    }

}
