package com.it.utils;

import org.joda.time.DateTime;

/**
 * Created by xieyue on 2016/7/16.
 * TimeAgo
 */


public class TimeAgo {

//    public static void transTime(String time) {
//        DateTime dateTime = new DateTime();
//        DateTime obj = new DateTime(time);
//
//    }

    public static void main(String[] args) {

        DateTime dateTime = new DateTime();
        String time = "2016-07-16 15:26:32";
        String[] array = time.split("[- :]");
        int[] arr = new int[array.length];
        for (int i = 0 ; i < array.length; i++){
            arr[i]= Integer.parseInt(array[i]);
        }
       DateTime obj = new DateTime(arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],0);


    }


}
