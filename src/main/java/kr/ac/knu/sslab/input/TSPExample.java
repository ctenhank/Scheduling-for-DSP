package kr.ac.knu.sslab.input;

import java.util.Random;

public class TSPExample {
    private Random rand = new Random();

    private int n;
    private int precision = 1000;
    private double edgeFactor = 0.4;
    
    public boolean[][] edge;
    public double[][] weight;
    
    public TSPExample(int n) {
        this.n = n;
        initEdge();
        initWeight();
    }
    
    private boolean getEdge() {
        if (rand.nextDouble() < edgeFactor) {
            return true;
        }
        return false;
    }
    
    private double getWeight() {
        return (double) Math.round(rand.nextDouble() * precision) / precision;
    }

    private void initEdge() {
        this.edge = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            edge[i][i] = false;
            for (int j = i + 1; j < n; j++) {
                edge[i][j] = getEdge();
                edge[j][i] = edge[i][j];
            }
        }
    }

    private void initWeight() {
        this.weight = new double[n][n];
        for (int i = 0; i < n; i++) {
            weight[i][i] = 0.0;
            for (int j = i + 1; j < n; j++) {
                weight[i][j] = 0.0;
                weight[j][i] = 0.0;

                if (edge[i][j]) {
                    weight[i][j] = getWeight();
                    weight[j][i] = weight[i][j];
                }                
            }
        }
    }

    @Override
    public String toString() {
        String ret = "Number of Edge: " + n + "\n"
                + "The weight -1 means disconnect between node\n"
                + "The weights is below:\n";

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ret += (edge[i][j] ? weight[i][j] : -1) + " ";
            }
            ret += "\n";
        }
        
        return ret;
    }
}
