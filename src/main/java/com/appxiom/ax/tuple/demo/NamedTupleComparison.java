/**
 * @author Robin Panicker
 * @version 1.0.4
 * @since 1.0.4
 */
package com.appxiom.ax.tuple.demo;

import com.appxiom.ax.tuple.NamedTuple;
import java.util.HashMap;
import java.util.Map;

/**
 * This class demonstrates the usage of NamedTuple as a HashMap key.
 * It shows how named keys provide better self-documentation compared to
 * standard Tuples.
 */
public class NamedTupleComparison {

    public static void main(String[] args) {
        System.out.println("--- Scenario 1: Nested HashMaps ---");
        demonstrateNestedMap();

        System.out.println("\n--- Scenario 2: Flat HashMap with NamedTuple Key ---");
        demonstrateNamedTupleMap();
    }

    /**
     * Demonstrates the use of nested HashMaps to manage sales data.
     */
    private static void demonstrateNestedMap() {
        // Data Structure: Region -> Year -> Product -> Sales
        Map<String, Map<Integer, Map<String, Double>>> salesData = new HashMap<>();

        // 1. Populating the data
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
     * Demonstrates the use of a flat HashMap with NamedTuple keys to manage sales
     * data.
     */
    private static void demonstrateNamedTupleMap() {
        // Data Structure: NamedTuple(Region, Year, Product) -> Sales
        Map<NamedTuple, Double> salesData = new HashMap<>();

        // 1. Populating the data
        salesData.put(createNamedKey("North", 2023, "Widget A"), 1500.0);
        salesData.put(createNamedKey("North", 2023, "Widget B"), 2000.0);
        salesData.put(createNamedKey("South", 2023, "Widget A"), 1200.0);
        salesData.put(createNamedKey("North", 2024, "Widget A"), 1800.0);

        // 2. Iterating to get final data
        for (Map.Entry<NamedTuple, Double> entry : salesData.entrySet()) {
            NamedTuple key = entry.getKey();
            String region = key.get("Region");
            Integer year = key.get("Year");
            String product = key.get("Product");
            Double sales = entry.getValue();

            System.out.printf("Region: %s, Year: %d, Product: %s -> Sales: $%.2f%n",
                    region, year, product, sales);
        }
    }

    /**
     * Helper to create a NamedTuple key for the demo.
     */
    private static NamedTuple createNamedKey(String region, Integer year, String product) {
        Map<String, Object> fields = new HashMap<>();
        fields.put("Region", region);
        fields.put("Year", year);
        fields.put("Product", product);
        return NamedTuple.of(fields);
    }
}
