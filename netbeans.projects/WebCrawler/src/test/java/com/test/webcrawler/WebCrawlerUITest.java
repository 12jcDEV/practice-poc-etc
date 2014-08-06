/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.webcrawler;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.netbeans.jemmy.ClassReference;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JFileChooserOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;

/**
 *
 * @author jose
 */
public class WebCrawlerUITest {

    JFrameOperator main;

    private static final String WEB_CRAWLER_MAIN_PACKAGE = "com.webcrawler.WebCrawlerMain";
    private static final String IMAGE_DOWNLOADER = "Image Downloader";
    private static final String IMAGE_FOLDER_LOC = "/home/jose/CrapHole";
    
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

        new ClassReference(WEB_CRAWLER_MAIN_PACKAGE).startApplication();

        main = new JFrameOperator(IMAGE_DOWNLOADER);

        //start application
        new JButtonOperator(main, "Download Images!").push();
        //error dialog box appears
        new JDialogOperator(main, "Error");

    }
    
    @Test
    public void testPressDownloadNoSelectLocation() throws Exception {
    
        new ClassReference(WEB_CRAWLER_MAIN_PACKAGE).startApplication();

        main = new JFrameOperator(IMAGE_DOWNLOADER);
         new JTextFieldOperator(main, 0).setText("http://www.imgur.com");
        new JButtonOperator(main, "Download Images!").push();
        new JDialogOperator(main, "Error");
    
    }

    @Test
    public void testEnterURL() throws Exception {

        new ClassReference(WEB_CRAWLER_MAIN_PACKAGE).startApplication();

        main = new JFrameOperator(IMAGE_DOWNLOADER);

        new JTextFieldOperator(main, 0).setText("safsfdsaffads");
        new JButtonOperator(main, "Download Images!").push();
        new JDialogOperator(main, "Error");

    }

    @Test
    public void testOpenFileChooser() throws Exception {

        new ClassReference(WEB_CRAWLER_MAIN_PACKAGE).startApplication();
        main = new JFrameOperator(IMAGE_DOWNLOADER);
        new JButtonOperator(main, "Browse").push();
        
        new JFileChooserOperator().chooseFile(IMAGE_FOLDER_LOC);
        new JTextFieldOperator(main, IMAGE_FOLDER_LOC);
        
    }

}
