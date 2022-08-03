package com.zzl.study.cloudshardingservice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzl.study.cloudshardingservice.entry.Course;
import com.zzl.study.cloudshardingservice.mapper.CourseMapper;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Wrapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CloudShardingServiceApplicationTests {

    @Autowired
    CourseMapper courseMapper;

    @Test
    public void addCourse(){
//        for (int i=0;i<10;i++){
//            Course course = new Course();
//            course.setCname("java");
//            course.setUserId(Long.valueOf(""+(1000+i)));
//            course.setCstatus("1");
//            courseMapper.insert(course);
//        }
    }

    @Test
    public void selectCourseByInCid(){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();

        wrapper.between("cid",738163800422748160L ,738163800829595649L);
        List<Course> courses = courseMapper.selectList(wrapper);
        courses.forEach(e->{
            System.out.println(e);
        });

    }


    @Test
    public void complexQuery(){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();

        wrapper.between("cid",738163800422748160L ,738163800829595649L);
        wrapper.eq("user_id",1008L);
        List<Course> courses = courseMapper.selectList(wrapper);
        courses.forEach(e->{
            System.out.println(e);
        });
    }

    @Test
    public void queryCourseByHint(){
        HintManager hintManager = HintManager.getInstance();
        hintManager.addTableShardingValue("course",2);
        List<Course> courses = courseMapper.selectList(null);
        courses.forEach(course -> System.out.println(course));
        hintManager.close();
    }


    @Test
    public void test(){
        String str = "abcdef";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=str.length()-1;i>=0;i--){
            stringBuilder.append(str.charAt(i));
        }
        System.out.println(stringBuilder.toString());
    }


    @Test
    public void test1() throws ParseException {
        String time = "2022-05-31 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = sdf.parse(time);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parse);

        int i = calendar.get(Calendar.DAY_OF_YEAR);
        System.out.println(i);

        String starttime = "2022-01-01 00:00:00";
        Date parse1 = sdf.parse(starttime);

        long startTimeS = parse1.getTime();
        long endTimeS = parse.getTime();

        Long res = (endTimeS-startTimeS)/(60*60*24*1000);

        System.out.println(res);

    }

    @Test
    public void test2(){
        Integer[] integers = new Integer[]{101,22,3214,43,5};
        for (int i=0; i<integers.length; i++){
            for (int j=0; j<integers.length-1-i; j++){
                if (integers[j] < integers[j+1]){
                    int big = integers[j];
                    integers[j] = integers[j+1];
                    integers[j+1] = big;
                }
            }
        }
        for (Integer i : integers){
            System.out.println(i);
        }
    }

    @Test
    public void test3(){
       String str1 = "aabaacaa";
       String str2 = "aa";
       int count = 0;
       int i;
       while ((i =str1.indexOf(str2)) >=0){
           count++;
           str1 = str1.substring(i+str2.length());
       }
        System.out.println(count);
    }

    @Test
    public void test4(){
        Integer[] integers = new Integer[]{101,22,3214,43,5,-1,32323423,-121,343};
        Integer integer = Arrays.stream(integers).max(Integer::compare).get();
        Integer integer2 = Arrays.stream(integers).min(Integer::compare).get();

        Integer maxValue = integers[0];
        Integer minValue = integers[0];
        for (int i=1;i<integers.length;i++){
            Integer temp = integers[i];
            if (temp > maxValue){
                maxValue = temp;
            }
            if (temp < minValue){
                minValue = temp;
            }
        }
        Integer startValue = integers[0];
        for (int i=0;i<integers.length;i++){
            if (integers[i] == maxValue){
                integers[i] = startValue;
                continue;
            }
            if (integers[i] == minValue){
                integers[i] = integers[integers.length-1];
                continue;
            }
        }
        integers[0] = maxValue;
        integers[integers.length-1] = minValue;

        Arrays.stream(integers).forEach(e->{
            System.out.println(e);
        });

    }
}
