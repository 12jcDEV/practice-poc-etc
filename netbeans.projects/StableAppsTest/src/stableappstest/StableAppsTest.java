/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stableappstest;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author jose
 */
public class StableAppsTest {

    public static void main(String[] blalbalba) throws IOException, InterruptedException, ExecutionException {
        //Validate.isTrue(args.length == 1, "usage: supply url to fetch");

        Callable<String> c1 = new Callable<String>() {

            @Override
            public String call() throws Exception {
                String url = "http://www.huffingtonpost.com/";
                print("Fetching %s...", url);

                Document doc = Jsoup.connect(url).get();
                Elements links = doc.select("a[href]");
                Elements media = doc.select("[src]");
                Elements imports = doc.select("link[href]");

                print("\nMedia: (%d)", media.size());
                for (Element src : media) {
                    if (src.tagName().equals("img")) {

                        System.out.println("tag name: " + src.tagName());
                        System.out.println("abs:src: " + src.attr("abs:src"));
                        System.out.println("alt: " + src.attr("alt"));
                        System.out.println(Thread.currentThread().getName() + "-----");
                    }
                }

                return "hello";
            }
        };

        



        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> result = executorService.submit(new Democ());
        Future<String> r2 = executorService.submit(c1);
        Future<String> r3 = executorService.submit(c1);
        Future<String> r4 = executorService.submit(c1);
        Future<String> r5 = executorService.submit(c1);
        

        System.out.println(
                "result: " + r2.get());
                System.out.println(
                "result: " + r3.get());
                        System.out.println(
                "result: " + r4.get());
                                System.out.println(
                "result: " + r5.get());
                                        System.out.println(
                "result:!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1 " + result.get());
               executorService.shutdown();
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width) {
            return s.substring(0, width - 1) + ".";
        } else {
            return s;
        }
    }

    private static void writeToFileSystem(byte[] fileToWrite, String fileName, String location) {

    }
}
