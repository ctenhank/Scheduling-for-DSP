package kr.ac.knu.sslab;

import java.util.Random;

import kr.ac.knu.sslab.input.tsp.Generator;
import kr.ac.knu.sslab.input.tsp.Graph;

/**
 * Hello world!
 */
public final class App {

    private App() {
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Generator gen = new Generator(100);
        Graph g = gen.generate();
        int range = gen.nodeNum;
        System.out.println(gen.nodeNum);
        String line = "";
        for (int i = 0; i < range; i++) {
            for (int j = 0; j < range; j++) {
                line += g.edge[i][j] ? 0 : 1;
                line += ' ';
            }
            line += "\n";
        }
        line = "";
        for (int i = 0; i < range; i++) {
            for (int j = 0; j < range; j++) {
                line += g.weight[i][j];
                line += ' ';
            }
            line += "\n";
        }
        System.out.println(line);
    }
}
