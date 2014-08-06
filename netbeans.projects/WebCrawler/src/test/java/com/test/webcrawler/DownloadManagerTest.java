/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.webcrawler;

import com.webcrawler.manager.DownloadManager;
import com.webcrawler.manager.ImageManager;
import com.webcrawler.manager.UIManager;
import mockit.Mocked;
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

    private static final String URL = "http://www.stableapps.com";

    private static final String FOLDER_LOC = "/home/jose/CrapHole";

    @Autowired
    private ImageManager imageManager;

    @Autowired
    private DownloadManager downloadManager;

    @Mocked
    UIManager ui;

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
    }

    @After
    public void tearDown() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUINull() throws Exception {

        downloadManager.processDownload(FOLDER_LOC, imageManager.getImageData(URL), null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testFolderLocEmpty() throws Exception {

        downloadManager.processDownload("", imageManager.getImageData(URL), ui);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testImageEmpty() throws Exception {
    downloadManager.processDownload(FOLDER_LOC,null, ui);
    }
    
    public void testDownload() throws Exception {
    
        downloadManager.processDownload(URL, imageManager.getImageData(URL), ui);
    
    }

}
