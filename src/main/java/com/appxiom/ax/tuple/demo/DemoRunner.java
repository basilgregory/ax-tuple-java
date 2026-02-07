/**
 * @author Robin Panicker
 * @version 1.0.4
 * @since 1.0.4
 */
package com.appxiom.ax.tuple.demo;

/**
 * A runner class to execute all Tuple and NamedTuple demos.
 */
public class DemoRunner {

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   Ax-Tuple Demonstration Runner");
        System.out.println("========================================\n");

        NestedMapComparison.main(args);

        System.out.println("========================================");
        System.out.println("   Ax-NamedTuple Demonstration Runner");
        System.out.println("========================================\n");

        NamedTupleComparison.main(args);

        System.out.println("\n========================================");
        System.out.println("   Demonstration Completed");
        System.out.println("========================================");
    }
}
