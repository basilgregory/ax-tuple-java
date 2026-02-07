/**
 * @author Robin Panicker
 * @version 1.0.4
 * @since 1.0.4
 */
package com.appxiom.ax.tuple.demo;

import com.appxiom.ax.tuple.Tuple;
import java.util.HashMap;
import java.util.Map;

/**
 * This class demonstrates the comparison between nested HashMaps and a flat
 * HashMap
 * with Tuple keys for managing sales data.
 */
public class NestedMapComparison {

    public static void main(String[] args) {
        System.out.println("--- Scenario 1: Nested HashMaps ---");
        demonstrateNestedMap();

        System.out.println("\n--- Scenario 2: Flat HashMap with Tuple Key ---");
        demonstrateTupleMap();
    }

    /**
     * Demonstrates the use of nested HashMaps to manage sales data.
     */
    private static void demonstrateNestedMap() {
        // Data Structure: Region -> Year -> Product -> Sales
        Map<String, Map<Integer, Map<String, Double>>> salesData = new HashMap<>();

        // 1. Populating the data (Nested maps require checking for existence at each
        // level)
        addDataNested(salesData, "North", 2023, "Widget A", 1500.0);
        addDataNested(salesData, "North", 2023, "Widget B", 2000.0);
        addDataNested(salesData, "South", 2023, "Widget A", 1200.0);
        addDataNested(salesData, "North", 2024, "Widget A", 1800.0);

        // 2. Iterating to get final data (Requires 3 nested for-loops)
        for (Map.Entry<String, Map<Integer, Map<String, Double>>> regionEntry : salesData.entrySet()) {
            String region = regionEntry.getKey();
            for (Map.Entry<Integer, Map<String, Double>> yearEntry : regionEntry.getValue().entrySet()) {
                Integer year = yearEntry.getKey();
                for (Map.Entry<String, Double> productEntry : yearEntry.getValue().entrySet()) {
                    String product = productEntry.getKey();
                    Double sales = productEntry.getValue();
                    System.out.printf("Region: %s, Year: %d, Product: %s -> Sales: $%.2f%n",
                            region, year, product, sales);
                }
            }
        }
    }

    private static void addDataNested(Map<String, Map<Integer, Map<String, Double>>> salesData,
            String region, Integer year, String product, Double sales) {
        salesData.computeIfAbsent(region, k -> new HashMap<>())
                .computeIfAbsent(year, k -> new HashMap<>())
                .put(product, sales);
    }

    /**
     * Demonstrates the use of a flat HashMap with Tuple keys to manage sales data.
     */
    private static void demonstrateTupleMap() {
        // Data Structure: Tuple(Region, Year, Product) -> Sales
        Map<Tuple, Double> salesData = new HashMap<>();

        // 1. Populating the data (Flat map is much simpler)
        salesData.put(Tuple.of("North", 2023, "Widget A"), 1500.0);
        salesData.put(Tuple.of("North", 2023, "Widget B"), 2000.0);
        salesData.put(Tuple.of("South", 2023, "Widget A"), 1200.0);
        salesData.put(Tuple.of("North", 2024, "Widget A"), 1800.0);

        // 2. Iterating to get final data (Single loop!)
        for (Map.Entry<Tuple, Double> entry : salesData.entrySet()) {
            Tuple key = entry.getKey();
            String region = key.get(0);
            Integer year = key.get(1);
            String product = key.get(2);
            Double sales = entry.getValue();

            System.out.printf("Region: %s, Year: %d, Product: %s -> Sales: $%.2f%n",
                    region, year, product, sales);
        }
    }
}
