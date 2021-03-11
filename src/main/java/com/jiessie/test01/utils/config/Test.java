package com.jiessie.test01.utils.config;

import com.jiessie.test01.utils.entity.ElasticEntity;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    public static void main(String[] args) throws IOException {


//        CaffeineUtils.cache.put("张三", "浙江");
//        System.out.println(CaffeineUtils.cache.getIfPresent("张三"));
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        CaffeineUtils.cache.put("李四", "黑龙江");
//        System.out.println(CaffeineUtils.cache.getIfPresent("李四"));
//
//        try {
//            Thread.sleep(4900);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(CaffeineUtils.cache.getIfPresent("张三"));
//        System.out.println(CaffeineUtils.cache.getIfPresent("李四"));


//        ExecutorService service = Executors.newFixedThreadPool(10);
//
//        for (int i = 0; i < 20; i++) {
//            service.execute(()->{
//                System.out.println(DateUtil.dateStrFormat("2019-12-26 12:02:12"));
//            });
//        }
//        service.shutdown();
//
//        ThreadLocal<String> localName = new ThreadLocal();
//        localName.set("张三");
//        String name = localName.get();
//        localName.remove();
//        System.out.println(localName);
//        System.out.println(name);


        //mmap
//        File file = new File("/Users/huangmingjie/Downloads/restart.sh");
//        long len = file.length();
//        byte[] ds = new byte[(int) len];
//
//        try {
//            MappedByteBuffer mappedByteBuffer = new RandomAccessFile(file, "r")
//                    .getChannel()
//                    .map(FileChannel.MapMode.READ_ONLY, 0, len);
//            for (int offset = 0; offset < len; offset++) {
//                byte b = mappedByteBuffer.get();
//                ds[offset] = b;
//            }
//
//            Scanner scan = new Scanner(new ByteArrayInputStream(ds)).useDelimiter(" ");
//            while (scan.hasNext()) {
//                System.out.print(scan.next() + " ");
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        //sendfile
//        FileChannel channelI = new FileInputStream("/Users/huangmingjie/Downloads/restart.sh").getChannel();
//
//        FileChannel channelO = new FileOutputStream("/Users/huangmingjie/Downloads/restart1.sh").getChannel();
////        try {
////            channelI.transferTo(1, channelI.size(), channelO);
////        } finally {
////            channelI.close();
////            channelO.close();
////        }
//
//        try {
//            channelO.transferFrom(channelI, 0, channelI.size());
//        } finally {
//            channelI.close();
//            channelO.close();
//        }
//
//
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        //fdgsdfgsdfgsdfg
       lock.unlock();




        Semaphore semaphore = new Semaphore(10);
        try {
            semaphore.acquire();
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        HashMap hashMap = new HashMap(4);


        Integer.valueOf(100);

        ElasticEntity.x = "20";

        System.out.println(ElasticEntity.x);
    }
}
