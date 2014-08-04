/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test.webcrawler;

import com.webcrawler.WebCrawlerMain;
import com.webcrawler.manager.DownloadManager;
import com.webcrawler.manager.ImageManager;
import com.webcrawler.manager.StorageManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jose
 */
public class DownloadManagerTest extends BaseTest {
    
    @Autowired
    private StorageManager storageManager;
    
    @Autowired
    private ImageManager imageManager;
    
    @Autowired
    private DownloadManager downloadManager;
    
    private WebCrawlerMain ui;
    
    private static final String FOLDER_LOC = "/home/jose/Desktop/crap/thevergeisdead";
    
    public DownloadManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ui = new WebCrawlerMain();
    }
    
    @After
    public void tearDown() {
        ui = null;
    }
    
    @Test
    public void testProcessDownload() throws Exception {
    
       // downloadManager.processDownload(FOLDER_LOC,imageManager.getImageData(FOLDER_LOC), );
        
    }

}
