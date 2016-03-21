package com.morgan.assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by nadeem on 3/18/16.
 */ class DealCalculator {

    public void calculate(String filePath) throws FileNotFoundException {
        Map<String, Deal> rawDeals = new HashMap<>(500);
        readFile(filePath, rawDeals);
        computeDealValues(rawDeals);
    }
    void computeDealValues(Map<String, Deal> rawDeals) {

        Map<String, Double> dealsWithValues = new HashMap<String, Double>();
        rawDeals.forEach((k, v) -> {
            Map<String, List<String>> alreadyVisitedDeals = new HashMap<>();
            List<String> alreadyVisitedDealsForThisDeal = new ArrayList<>();
            alreadyVisitedDealsForThisDeal.add(k);
            alreadyVisitedDeals.put(k, alreadyVisitedDealsForThisDeal);
            System.out.println("Key : " + k +" value : " + computeSingleDeal(k, k, v, dealsWithValues, alreadyVisitedDeals, rawDeals));
        });
    }

    private Double computeSingleDeal(String parentDeal, String dealKey, Deal deal, Map<String, Double> dealsWithValues , Map<String, List<String>> alreadyVisitedDeals, Map<String, Deal> rawDeals) {
        // We could optimize this solution by keeping track of fully computed deals.
        if(dealsWithValues.get(dealKey) != null) {
            Double dealAmtAlreadyCalculated = dealsWithValues.get(dealKey);
            System.out.println("Deal already calculated with value " + dealAmtAlreadyCalculated);
            return dealAmtAlreadyCalculated;
        }
        Double dealAmount = deal.getAmount();
        if(deal.getDependentDeals() != null && !deal.getDependentDeals().isEmpty()) {
            for (String dependentDeal : deal.getDependentDeals()){

                List<String> alreadyVisitedDealsForThisDeal = alreadyVisitedDeals.get(dealKey);
                List<String> alreadyVisitedDealsForParentDeal = alreadyVisitedDeals.get(parentDeal);

                if((alreadyVisitedDealsForThisDeal != null && alreadyVisitedDealsForThisDeal.contains(dependentDeal))
                        || (alreadyVisitedDealsForParentDeal != null && alreadyVisitedDealsForParentDeal.contains(dependentDeal))) {
                    //If this deal is already been visited for the dependent deal or visited for parent deal... Do nothing!
                }
                else {
                    if(alreadyVisitedDealsForThisDeal == null) {
                        alreadyVisitedDealsForThisDeal = new ArrayList<>();
                        alreadyVisitedDeals.put(dealKey, alreadyVisitedDealsForThisDeal);
                    }
                    //Keep track what has been visited. We are visiting this deal in the context of parent deal (that we are calculating) as well as dependent deal.
                    alreadyVisitedDealsForThisDeal.add(dependentDeal);
                    alreadyVisitedDealsForParentDeal.add(dependentDeal);
                    //Recursively calculated the dependent deal
                    dealAmount = dealAmount + computeSingleDeal(parentDeal, dependentDeal, rawDeals.get(dependentDeal), dealsWithValues, alreadyVisitedDeals, rawDeals);
                }
            }
        }
        return dealAmount;
    }

    private void readFile(String path, Map<String, Deal> rawDeals) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line == null || line.isEmpty()) {
                continue;
            }
            String[] columns = line.split("\t");
            Deal deal = new Deal();
            rawDeals.put(columns[0], deal);
            deal.setAmount(Double.valueOf(columns[1]));
            List<String> dependentDeals = new ArrayList<>(500);
            if(columns.length>1) {
                for (int i = 2; i < columns.length; i++) {
                    dependentDeals.add(columns[i]);
                }
            }
            deal.setDependentDeals(dependentDeals);
        }
    }
}
