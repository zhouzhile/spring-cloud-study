//package com.zzl.study.cloud.jvm.jvm;
//
//import java.text.NumberFormat;
//import java.util.Calendar;
//import java.util.Date;
//
//public class StringTest {
//    public static void main(String[] args) {
//        Calendar startCal = Calendar.getInstance();
//
//        startCal.setTime(DateUtil.str2Date(startTime,"yyyy-MM-dd HH:mm:ss"));
//        Calendar endCal = Calendar.getInstance();
//        endCal.setTime(DateUtil.str2Date(endTime,Constants.JAVA_DATETIME));
//        // 判断相隔几个小时,相减为0，按照一小时计算
//        int startHour = startCal.get(Calendar.HOUR_OF_DAY);
//        int endHour = endCal.get(Calendar.HOUR_OF_DAY);
//        int hourNum = endHour -startHour;
//        return hourNum;
//    }
//}
