package com.zzl.study.cloud.jvm.jvm;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class SemaphoreRunner {

    public static void main(String[] args) {

        Map<Integer,Integer> map = new ConcurrentHashMap<>(8);
        map.put(1,1);
        map.put(9,1);
        map.put(17,1);
        map.put(25,1);
        map.put(33,1);
        map.put(41,1);
        map.put(2,1);





//        String put1 = map.put("a", "a");
//        // System.out.println("第一次输出返回值"+put1);
//        String put = map.put("a", "bb");
//        String put2 = map.put("A", "bb");
//        String put3 = map.put("C", "bb");
//        String put4 = map.put("b", "b1");
//        String put5 =map.put("c", "b2");
//        String put6 =map.put("d", "b3");
//        String put7 =map.put("B", "b4");
//        String put8 =map.put("D", "b5");
//        String put9 =map.put("e", "b6");
//        String put10 =map.put("E", "b7");
//        String put11 =map.put("f", "b8");
//        String put12 =map.put("F", "b9");
//        String put13 =map.put(null, "b10");
        //System.out.println("第二次输出返回值"+put);
//        System.out.println(map.get("a"));


//        map.putAll((Map) new HashMap<>());

//        Semaphore semaphore = new Semaphore(2);

//        Semaphore semaphore = new Semaphore(2,true);
//
//
//        semaphore.acquire();

//        semaphore.release();




//        for (int i = 0;i< 5 ;i++){
//            Task task = new Task(semaphore,  ""+ i);
//            new Thread(task).start();
//        }

    }

    static class Task extends Thread{
        Semaphore semaphore;
        public Task(Semaphore semaphore,String tName){
            this.semaphore = semaphore;
            this.setName(tName);
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+"获得锁:aquire() at time:"+System.currentTimeMillis());
                Thread.sleep(1000);
                semaphore.release();
                System.out.println(Thread.currentThread().getName()+"释放锁:aquire() at time:"+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
