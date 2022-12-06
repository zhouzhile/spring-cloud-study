//package com.zzl.study.cloud.jvm.jvm;
//
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
//public class DateUtil {
//
//
//    /**
//     * 获取当前年月
//     * @return
//     */
//    public static String getCurYearMonth(Calendar calendar) {
//        int monthInt = calendar.get(Calendar.MONTH)+1;
//        String year = String.valueOf(calendar.get(Calendar.YEAR));
//        String month = "";
//        if (monthInt < 10) {
//            month = String.valueOf(0 + "" + monthInt);
//        } else {
//            month = String.valueOf(monthInt);
//        }
//        return year + month;
//
//    }
//
//    public static List<String> getBeforeYearMonths() {
//        List<String> lists = new ArrayList<>(12);
//        Calendar calendar = Calendar.getInstance();
//        int length = calendar.get(Calendar.MONTH);
//        for (int i = length; i >length-12; i--) {
//            if (i == -1) {
//                calendar.add(Calendar.YEAR, -1);
//            }
//            if (i < 0) {
//                calendar.set(Calendar.MONTH, 12 + i);
//            }else{
//                if (i==5){
//                    calendar.set(Calendar.MONTH, 5);
//                }else {
//                    calendar.set(Calendar.MONTH, i);
//                }
//            }
//            lists.add(getCurYearMonth(calendar));
//        }
//        return lists;
//    }
//
//    public static void main(String[] args) {
//
////        Calendar calendar = Calendar.getInstance();
////        for (int i =1 ;i<=12;i++){
////            calendar.set(Calendar.DAY_OF_MONTH,1);
////            calendar.set(Calendar.MONTH, i);
////            int monthInt = calendar.get(Calendar.MONTH);
////            System.out.println(i+"-----"+monthInt);
////        }
//        System.out.println(getBeforeYearMonths());
//    }
//}
