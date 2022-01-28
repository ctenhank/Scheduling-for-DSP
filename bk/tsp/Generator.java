package kr.ac.knu.sslab.input;

import java.util.Random;

public abstract class Generator<W> {
    private Random rand = new Random();
    private double edgeFactor = 0.4;

    public Generator() {}

    public Generator(double edgeFactor)  {
        this.edgeFactor = edgeFactor;
    }

    private boolean createEdge() {
        if (rand.nextDouble() < edgeFactor) {
            return true;
        }
        return false;
    }

    /**
     * Generate 
     * @param nodeNum
     * @return
     */
    public Graph<W> generate(int nodeNum) {
        Graph<W> g = new Graph<W>();
        g.edge = new boolean[nodeNum][nodeNum];
        //g.weight = new W[nodeNum][nodeNum];

        for (int i = 0; i < nodeNum; i++) {
            g.edge[i][i] = false;
            g.weight[i][i] = null;
            for (int j = i + 1; j < nodeNum; j++) {
                // create edge information
                g.edge[i][j] = createEdge();
                g.edge[j][i] = g.edge[i][j];

                //g.weight[i][j] = createWeight();
                //g.weight[j][i] = g.weight[j][i];           
            }
        }
        return g;
    }
}
