package com.xiaokai.threadtest.lesson01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class WebPicDownloader2 implements Runnable{
    private String url, name;

    public WebPicDownloader2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebPicDownloader2 downloader = new WebPicDownloader2(url,name);
        downloader.downloader(url,name);
        System.out.println(name);
    }
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            System.out.println("文件下载失败"+e);
        }
    }
    public static void main(String[] args) {
        WebPicDownloader2 downloader01 = new WebPicDownloader2("https://img2.baidu.com/it/u=3355464299,584008140&fm=26&fmt=auto&gp=0.jpg","dlam1mm.jpg");
        WebPicDownloader2 downloader02 = new WebPicDownloader2("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Ffriendoprod.blob.core.windows.net%2Fmissionpics%2Fimages%2F4334%2Fmember%2Fe3656de4-9b49-4eac-a642-0b28bb9238e4.jpg&refer=http%3A%2F%2Ffriendoprod.blob.core.windows.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1625648951&t=84fef74b02d34ea30fea5373350a4618","DLAM2mm.jpg");
        WebPicDownloader2 downloader03 = new WebPicDownloader2("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg2.woyaogexing.com%2F2017%2F07%2F26%2F28fc292ed1b614a9%21600x600.jpg&refer=http%3A%2F%2Fimg2.woyaogexing.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1625648951&t=42d92134cfe5193a7a8c76e5ec28516c","DLAMmm3.jpg");

//        downloader01.start();
//        downloader02.start();
//        downloader03.start();
        new Thread(downloader01).start();
        new Thread(downloader02).start();
        new Thread(downloader03).start();
    }
}
