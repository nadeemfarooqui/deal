package com.morgan.assignment;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nadeem on 3/18/16.
 */
public class DealCalculatorTest {

    @Test
    public void testMySample() {
        System.out.println("Test 0");
        Map<String, Deal> rawDeals = new HashMap<>();
        Deal deal1 = new Deal();
        deal1.setAmount(1);

        Deal deal2 = new Deal();
        deal2.setAmount(5);

        Deal deal3 = new Deal();
        deal3.setAmount(10);

        Deal deal4 = new Deal();
        deal4.setAmount(15);

        List<String> deal1DependentDeals = new ArrayList<>();
        deal1DependentDeals.add("2");
        deal1DependentDeals.add("3");
        deal1.setDependentDeals(deal1DependentDeals);

        List<String> deal2DependentDeals = new ArrayList<>();
        deal2DependentDeals.add("3");
        deal2DependentDeals.add("4");
        deal2DependentDeals.add("1");
        deal2.setDependentDeals(deal2DependentDeals);

        List<String> deal3DependentDeals = new ArrayList<>();
        deal3DependentDeals.add("4");
        deal3.setDependentDeals(deal3DependentDeals);

        rawDeals.put("1", deal1);
        rawDeals.put("2", deal2);
        rawDeals.put("3", deal3);
        rawDeals.put("4", deal4);

        DealCalculator underTest = new DealCalculator();

        underTest.computeDealValues(rawDeals);
    }

    @Test
    public void testDeal() {
        System.out.println("Test 1");
        Map<String, Deal> rawDeals = new HashMap<>();
        Deal deal1 = new Deal();
        deal1.setAmount(1);

        Deal deal2 = new Deal();
        deal2.setAmount(2);

        Deal deal3 = new Deal();
        deal3.setAmount(3);

        List<String> deal1DependentDeals = new ArrayList<>();
        deal1DependentDeals.add("2");
        deal1DependentDeals.add("3");
        deal1.setDependentDeals(deal1DependentDeals);

        rawDeals.put("1", deal1);
        rawDeals.put("2", deal2);
        rawDeals.put("3", deal3);

        DealCalculator underTest = new DealCalculator();

        underTest.computeDealValues(rawDeals);
    }

    @Test
    public void testRepeatedDeals() {
        System.out.println("Test 2");

        Map<String, Deal> rawDeals = new HashMap<>();
        Deal deal1 = new Deal();
        deal1.setAmount(1);

        Deal deal2 = new Deal();
        deal2.setAmount(2);

        Deal deal3 = new Deal();
        deal3.setAmount(3);

        Deal deal4 = new Deal();
        deal4.setAmount(4);

        List<String> deal1DependentDeals = new ArrayList<>();
        deal1DependentDeals.add("2");
        deal1DependentDeals.add("3");
        deal1.setDependentDeals(deal1DependentDeals);

        List<String> deal2DependentDeals = new ArrayList<>();
        deal2DependentDeals.add("4");
        deal2.setDependentDeals(deal2DependentDeals);

        List<String> deal3DependentDeals = new ArrayList<>();
        deal3DependentDeals.add("4");
        deal3.setDependentDeals(deal3DependentDeals);

        rawDeals.put("1", deal1);
        rawDeals.put("2", deal2);
        rawDeals.put("3", deal3);
        rawDeals.put("4", deal4);

        DealCalculator underTest = new DealCalculator();

        underTest.computeDealValues(rawDeals);
    }

    @Test
    public void testDealCycle() throws FileNotFoundException {

        System.out.println("Test 3");
        Map<String, Deal> rawDeals = new HashMap<>();
        Deal deal1 = new Deal();
        deal1.setAmount(1);

        Deal deal2 = new Deal();
        deal2.setAmount(2);


        List<String> deal1DependentDeals = new ArrayList<>();
        deal1DependentDeals.add("2");
        deal1.setDependentDeals(deal1DependentDeals);

        List<String> deal2DependentDeals = new ArrayList<>();
        deal2DependentDeals.add("1");
        deal2.setDependentDeals(deal2DependentDeals);

        rawDeals.put("1", deal1);
        rawDeals.put("2", deal2);
        DealCalculator underTest = new DealCalculator();

        underTest.computeDealValues(rawDeals);
    }
}


