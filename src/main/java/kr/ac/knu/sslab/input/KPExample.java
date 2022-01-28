package kr.ac.knu.sslab.input;

import java.util.Random;

public class KPExample {
    /**
     * @param n number of bin
     * @param mtx the total amount of the bins
     * @param occ the current accupied amount of the bins
     */

    private int n;
    private Random rand = new Random();

    public double[] mtx;
    public double[] occ;

    private void initBin() {
        this.mtx = new double[n];
        for (int i = 0; i < n; i++) {
            mtx[i] = rand.nextDouble();
        }
    }
    
    private void initOcc() {
        this.occ = new double[n];
        for (int i = 0; i < n; i++) {
            occ[i] = 0.0;
        }
    }

    public KPExample(int n) {
        this.n = n;
        initBin();
        initOcc();
    }

    // getters
    public int getN() {
        return this.n;
    }

    public double get(int n) {
        return this.occ[n];
    }

    public boolean set(int bin, double cost) {
        if (this.mtx[n] > get(bin) + cost) {
            this.occ[n] += cost;
            return true;
        }
        return false;
    }

}
