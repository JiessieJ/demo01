package com.jiessie.test01.utils;

import com.alibaba.fastjson.JSON;
import com.jiessie.test01.utils.entity.ElasticEntity;

import java.util.*;

public class MianshiTest {

    public static void main(String[] args) {

//        short s1 = 1;
//        s1 = (short) (s1 + 1);

        short s1 = 1;
        s1 += 1;

        System.out.println(s1);


        int a = 10;
        long b = 10L;
        double c = 10.0;

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(b == c);


        int[] ints = new int[]{1, 3, 3};
        int[] ints1 = new int[3];
        System.out.println(ints);
        System.out.println(ints1);


        ElasticEntity elasticEntity = new ElasticEntity("1", "2");
        System.out.println(JSON.toJSONString(elasticEntity));

        try {
            elasticEntity.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String ii = new String();


        byte a1 = 127;
        byte b1 = 127;
        b1 = (byte) (a1 + b1); // 报编译错误:cannot convert from int to byte
//        b1 += a1;

        System.out.println(b1);


        List list = new ArrayList();


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }, "dsafasdf");
        thread.start();


        Thread thread2 = new Thread(()->{
            //写入具体方法
        },"thread2");
        thread2.start();
    }
}
