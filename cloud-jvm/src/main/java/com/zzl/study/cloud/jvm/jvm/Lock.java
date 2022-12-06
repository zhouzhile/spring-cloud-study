package com.zzl.study.cloud.jvm.jvm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import sun.management.counter.Units;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Lock {

    public static int count = 100;

    public static Object lock = new Object();

    static  ReentrantLock reentrantLock = new ReentrantLock(false);

    public static void main(String[] args) throws InterruptedException {


//        LocalDate localDate = LocalDate.now();
//        String s = localDate.toString();
//        System.out.println(s);

//        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
//        concurrentHashMap.put("null",null);

//        ScheduledThreadPoolExecutor scheduleThreadPoolExecutor;
//
//        ThreadPoolExecutor tr;
//        tr = new ThreadPoolExecutor(5,10,100, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
//        tr.execute(()->{
//            try {
//                System.out.println("11111111"+Thread.currentThread().getName());
//                Thread.sleep(10000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        Thread.sleep(1000L);
//        tr.execute(()->{
//            try {
//                System.out.println("22222222"+Thread.currentThread().getName());
//                Thread.sleep(10000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        Thread.sleep(1000L);
//        tr.execute(()->{
//            try {
//                System.out.println("33333333"+Thread.currentThread().getName());
//                Thread.sleep(10000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        Thread.sleep(1000L);
//        tr.execute(()->{
//            try {
//                System.out.println("44444444"+Thread.currentThread().getName());
//                Thread.sleep(10000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        Thread.sleep(1000L);
//        tr.execute(()->{
//            try {
//                System.out.println("55555555"+Thread.currentThread().getName());
//                Thread.sleep(10000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        Thread.sleep(8000L);
//        tr.execute(()->{
//            try {
//                System.out.println("66666666"+Thread.currentThread().getName());
//                Thread.sleep(10000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });


//        Semaphore semaphore = new Semaphore(2);
//        new Thread(()->{
//            try {
//                semaphore.acquire();
//                System.out.println("线程1获取到锁");
//                Thread.sleep(5000L);
//                semaphore.release();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        new Thread(()->{
//            try {
//                semaphore.acquire();
//                System.out.println("线程2获取到锁");
//                Thread.sleep(3000L);
//                semaphore.release();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        new Thread(()->{
//            try {
//                semaphore.acquire();
//                System.out.println("线程3获取到锁");
//                semaphore.release();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();

    }

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String ERROR_MESSAGE = "字符串序列化异常：";

    public static <T> T toObject(String json, Class<T> valueType) {
        if (json == null) {
            return null;
        }
        try {
            return MAPPER.readValue(json, valueType);
        } catch (Exception e) {
        }
        return null;
    }


    public static void main1(String[] args) {
        reentrantLock.unlock();
        String s = UUID.randomUUID().toString();
        String uuid = s.replaceAll("-", "");
        String uuid2 = s.replace("-", "");

        System.out.println(uuid);
        System.out.println(uuid2);


//        String a = "aa";
//
//        System.out.println();


//        Map map = new HashMap();
//        map.put("","");


//
//
//
//        reentrantLock.lock();
//        System.out.println("同步块代码");
//        new Thread(()->{
//            reentrantLock.lock();
//            System.out.println("子线程同步块代码");
//            reentrantLock.unlock();
//        }).start();
//
//        try {
//            Thread.sleep(120000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        reentrantLock.unlock();
    }



    public void remove(){
        count--;
        System.out.println(Thread.currentThread().getId()+" "+count);
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Lock.count = count;
    }
}
