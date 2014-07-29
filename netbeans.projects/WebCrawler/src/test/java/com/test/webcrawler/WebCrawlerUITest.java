/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.webcrawler;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;

/**
 *
 * @author jose
 */
public class WebCrawlerUITest {

    JFrameOperator main;

    public WebCrawlerUITest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() {
        main = null;
    }

    @Test
    public void testCLickDownloadItWithoutURL() throws Exception {

        new ClassReference("com.test.webcrawler.WebCrawlerMain").startApplication();

        main = new JFrameOperator("Image Downloader");

        //start application
        new JButtonOperator(main, "Download Images!").push();
        //error dialog box appears
        new JDialogOperator(main, "Error");

    }
    
    @Test
    public void testEnterURL() throws Exception {
    
        
        new ClassReference("com.test.webcrawler.WebCrawlerMain").startApplication();

        main = new JFrameOperator("Image Downloader");

        
        new JTextFieldOperator(main, 0).setText("safsfdsaffads");
         new JButtonOperator(main, "Download Images!").push();
         new JDialogOperator(main, "Error");
    
    }

}
