package com.xiaokai.inettest.lesson03;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLDemo02 {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://127.0.0.1:8080/testxiaokai/test.txt");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            FileOutputStream fos = new FileOutputStream(new File("testURL.txt"));
            byte[] buffer = new byte[1024];
            int len;
            while ((len=is.read(buffer))!=-1){
                fos.write(buffer,0,len);
            }

            fos.close();
            is.close();
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
