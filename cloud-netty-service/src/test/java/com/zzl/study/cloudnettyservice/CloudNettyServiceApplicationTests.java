package com.zzl.study.cloudnettyservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CloudNettyServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test() throws Exception{

       int x=5;
       switch (x){
           case 1:
           case 2:
           case 3:
               System.out.println("1");
               break;
           case 4:
           case 5:
           case 6:
               System.out.println("2");
           default:
               System.out.println("3");
       }
    }

}
