/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.webcrawler;

import com.webcrawler.WebCrawlerMain;
import static org.fest.swing.core.BasicRobot.robotWithNewAwtHierarchy;
import org.fest.swing.core.Robot;
import org.fest.swing.core.matcher.DialogMatcher;
import org.fest.swing.driver.JFileChooserDriver;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.finder.JFileChooserFinder;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JFileChooserFixture;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jose
 */
public class WebCrawlerUIFestTest {

    private WebCrawlerMain ui;
    private FrameFixture window;
    private JFileChooserFixture fileChooser;
    private JFileChooserDriver driver;
    private Robot robot;

    public WebCrawlerUIFestTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        FailOnThreadViolationRepaintManager.install();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        ui = GuiActionRunner.execute(new GuiQuery<WebCrawlerMain>() {
            protected WebCrawlerMain executeInEDT() {
                return new WebCrawlerMain();
            }
        });

        window = new FrameFixture(ui);
        window.show();

        robot = robotWithNewAwtHierarchy();

    }

    @After
    public void tearDown() {

        window.cleanUp();
    }

    @Test
    public void testClickDownloadButton() throws Exception {

        window.textBox("txtURL").enterText("random words");
        window.button("btnDownload").click();
        window.dialog(DialogMatcher.withTitle("Error"));

    }

    @Test
    public void testClickButtonWithWrongURL() throws Exception {

        window.textBox("txtURL").enterText("http://www.kjviuafwbfwfuwffuygfyfguygf.ph");
        window.button("btnDownload").click();
        window.dialog(DialogMatcher.withName("dialog1"));

    }

    @Test
    public void testBrowseFolder() throws Exception {

        window.button("btnBrowse").click();
        fileChooser = JFileChooserFinder.findFileChooser().using(robot);

    }


}
