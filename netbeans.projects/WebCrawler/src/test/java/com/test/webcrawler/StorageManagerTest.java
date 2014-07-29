/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.webcrawler;

import com.test.webcrawler.manager.StorageManager;
import com.test.webcrawler.manager.impl.StorageManagerImpl;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jose
 */
public class StorageManagerTest extends BaseTest {

    private StorageManager storageManager;

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

        assertTrue(storageManager.downloadImagesFromRemote("http://i.imgur.com/NmliaQE.jpg"));

    }

}
