package kr.ac.knu.sslab;

import kr.ac.knu.sslab.input.KPExample;
import kr.ac.knu.sslab.input.TSPExample;
import kr.ac.knu.sslab.algo.aco.Environment;
/**
 * Hello world!
 */
public final class App {
    public static void main(String[] args) {
        int n = 500;
        int m = 5;
        System.out.println("Generate Random Input...");
        TSPExample input = new TSPExample(n);

        System.out.println("Find best soltuion using metaheuristics...");
        Environment env = new Environment(n, m, input.edge, input.weight);
        env.start();

        System.out.println("Compare Results...");
    }
}
