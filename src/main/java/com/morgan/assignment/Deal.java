package com.morgan.assignment;
import lombok.Data;

import java.util.List;

/**
 * Created by nadeem on 3/18/16.
 */

@Data
public class Deal {
    private double amount;
    List<String> dependentDeals;
}
