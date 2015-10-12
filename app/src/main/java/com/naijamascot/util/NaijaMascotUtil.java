package com.naijamascot.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bfashola on 10/10/15.
 */
public class NaijaMascotUtil {
    private static int NUMBER_OF_MASCOTS;

    public static List<Integer> SHUFFLE_ARRAY = new ArrayList<Integer>();

    public NaijaMascotUtil(int numberOfMascots) {
        NUMBER_OF_MASCOTS = numberOfMascots;
    }

    public List<Integer> getShuffleArray(){
        for (int i = 1; i <= NUMBER_OF_MASCOTS; i++ ){
            SHUFFLE_ARRAY.add(i);
        }
        Collections.shuffle(SHUFFLE_ARRAY);
        return SHUFFLE_ARRAY;
    }

}
