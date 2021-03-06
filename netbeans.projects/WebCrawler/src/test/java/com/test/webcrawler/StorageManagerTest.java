/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.webcrawler;

import com.webcrawler.manager.StorageManager;
import com.webcrawler.manager.impl.StorageManagerImpl;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jose
 */
public class StorageManagerTest extends BaseTest {

    private StorageManager storageManager;
    
    private static final String IMG_URL = "http://www.visitnorwich.co.uk/assets/Uploads/Events-images/_resampled/CroppedImage160122-12.-NMS-Birds-Feathered-Cloche-Hat.jpg";

    public StorageManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        storageManager = new StorageManagerImpl();
    }

    @After
    public void tearDown() {
        storageManager = null;
    }

    @Test
    public void testDownloadImagesFromRemote() throws Exception {

        assertNotNull(storageManager.downloadImagesFromRemote(IMG_URL));

    }
    
    @Test(expected = IOException.class)
    public void testErrorUrl() throws Exception {
    
        assertNotNull(storageManager.downloadImagesFromRemote("qwdwqdwqdwqdwqdqw"));
    }
    
    @Test(expected = IOException.class) 
    public void testURLNonExistent() throws Exception {
        assertNotNull(storageManager.downloadImagesFromRemote("http://wwwqwdwqdwqdwqdwqdqw.com/image.jpg"));
    }
    

}
