/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geck;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * @author ktwice
 */
public class Geck {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<Integer> list = Arrays.asList(4,5,10);
        System.out.println(getResult(list,3));
    }
    
    public static int getResult1(List<Integer> grounds) {
        int hmin = grounds.stream()
                .mapToInt(Integer::intValue)
                .min().getAsInt();
        int hmax = grounds.stream()
                .mapToInt(Integer::intValue)
                .max().getAsInt();
        return IntStream.rangeClosed(hmin, hmax)
                .map((h)->grounds.stream()
                        .mapToInt((x)->Math.abs(h-x))
                        .sum()
                )
                .min().getAsInt();
    }
    public static int getResult(List<Integer> chests, int t) {
        int size0 = (t + 1) / 2; // half - move, half - take, nothing skip
        if(chests.size() <= size0) // take all chests
            return chests.stream()
                    .mapToInt(Integer::intValue)
                    .sum();
        int skip2 = t - 1; // one far take, max skip
        if(chests.size() < skip2)
            skip2 = 2 * chests.size() - t + 1;
        return IntStream.rangeClosed(0, skip2)
                .map((skip)->chests.stream()
                        .limit((skip + t + 1) / 2)
                        .mapToInt(Integer::intValue)
                        .sorted()
                        .skip(skip)
                        .sum()
                )
                .max().getAsInt();
    }
    
}
