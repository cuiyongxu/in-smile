package com.facishare.util;

import java.util.Random;

/**
 * Created by cuiyongxu on 17/9/26.
 */
public class RandomUtil {

    public static int getRamdomNumer(int number){
        Random rand = new Random();
        return rand.nextInt(number);
    }
}
