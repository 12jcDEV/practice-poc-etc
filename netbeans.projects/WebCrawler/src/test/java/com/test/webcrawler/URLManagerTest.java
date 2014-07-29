/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test.webcrawler;

import com.webcrawler.manager.URLManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jose
 */

public class URLManagerTest extends BaseTest {
    
    
    @Autowired
    private URLManager uRLManager;
    
    public URLManagerTest() {
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
    public void testValidateURL() throws Exception {
    
        String url1 = "blablabalb";
    
        assertFalse(uRLManager.validateURL(url1));
        
        url1 = "http://www.imgur.com";
        
        assertTrue(uRLManager.validateURL(url1));
        
    }
    
    
}
