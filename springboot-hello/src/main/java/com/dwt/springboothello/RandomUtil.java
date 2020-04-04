package com.dwt.springboothello;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Package: com.dwt.springboothello
 * @Description:
 * @Author: Sammy
 * @Date: 2020/4/2 09:48
 */

public class RandomUtil {

	public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static int randomNum(int length){
		Random r = new Random();
        return r.nextInt(length);
	}

	public static String generateMixString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(letterChar.charAt(random.nextInt(letterChar.length())));
        }
        return sb.toString();
    }

    public static String formatDate(){
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS" );
        Date d= new Date();
        return sdf.format(d);
        // System.out.println("当前时间通过 yyyy-MM-dd HH:mm:ss SSS 格式化后的输出: "+str);
	}
}
