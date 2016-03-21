package com.morgan.assignment;

import java.io.FileNotFoundException;

/**
 * Created by nadeem on 3/18/16.
 */
public class DealCalculatorMain {
    public static void main(String[] args) throws FileNotFoundException {
        String arg = args[0];
        if(arg == null || arg.isEmpty()) {
            System.out.println("Must provide a valid file to compute deal");
        }
        DealCalculator dealCalculator = new DealCalculator();
        dealCalculator.calculate(arg);
    }
}

