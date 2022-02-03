package kr.ac.knu.sslab.algo.aco;

import java.lang.Math;
import java.util.Arrays;

public class Environment {
    private int n;
    private int m;
    private int nest = 0;

    private Ant[] ants;

    private double alpha = 0.5;
    private double beta = 0.3;

    private double Q = 500;
    private double evaporation = 0.5;

    private boolean[][] edge;
    private double[][] weight;
    private double[][] pheromone;

    private int maxIteration = 20;

    public Environment(int n, int m, boolean[][] edge, double[][] weight) {
        this.n = n;
        this.m = m;
        this.edge = edge;
        this.weight = weight;

        ants = new Ant[m];
        pheromone = new double[n][n];

        for (int i = 0; i < n; i++) {
            pheromone[i][i] = 0;
            for (int j = i + 1; j < n; j++) {
                pheromone[i][j] = 1.0;
                pheromone[j][i] = 1.0;
            }
        }

        for (int i = 0; i < m; i++) {
            ants[i] = new Ant(n);
        }
    }

    public void start() {
        for (int i = 0; i < maxIteration; i++) {
            deamonActions();
            pheromoneUpdate();

            //System.out.println("Iteration " + i + ": ");
        }
        //printResult();
    }

    public void printResult() {
        System.out.println("Total Iteration: " + maxIteration);
        System.out.println("Best Path from " + nest);
        System.out.println(Arrays.deepToString(pheromone));
    }

    /**
     * Trails are usually updated when all ants have completed their solution, 
     * increasing or decreasing the level of trails corresponding to moves that 
     * were part of "good" or "bad" solutions, respectively. 
     * This function updates a global pheromone.
     * 
     * Mathematical equation(Latex Form):
     * \tau_{xy} \larr (1 - \rho)\tau_{xy} + \sum^m_k \delta\tau^k_{xy}
     * 
     * {xy}: A trail level of the path between x and y
     * k: Kth Ant
     * \tau_{xy}: The amount of pheromone a path between x and y.
     * (1 - \rho): The remain amount of pheromone after evaporating.
     * \delta\tau^k_{xy}: The amount of pheromone deposited by kth ant.
     * 
     * @param n the number of ants in the ACO algorithm
     * @param x a vertex x for a path
     * @param y other vertex y for the path 
     */
    public void update(int n, int x, int y) {
        double deposited = 0.0;
        for (int i = 0; i < n; i++) {
            deposited += updatByAnAnt(x, y, i);
        }
        pheromone[x][y] = (1 - evaporation) * pheromone[x][y] + deposited;
    }
    
    /**
     * This function updates the level of trail by only a ant.
     * It is a part of the function @since update that the \delta\tau^k_{xy}.
     * It can be different depending on the specific problem, it's typically given for a TSP.
     * 
     * @param x
     * @param y
     * @param k
     * @return
     */
    private double updatByAnAnt(int x, int y, int k) {
        if (pheromone[x][y] == -1) {
            return 0;
        }
        return Q / pheromone[x][y];
    }

    private void deamonActions() {
        for (int k = 0; k < m; k++) {
            int current = ants[k].getCurrent();
            for (int i = 0; i < n; i++) {
                if (edge[current][i]) {
                    ants[k].setCost(i, Math.pow(pheromone[current][i], alpha));
                    ants[k].setAttractiveness(i, Math.pow(1 / weight[current][i], beta));
                }
            }
            ants[k].move();
        }
    }

    private void pheromoneUpdate() {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pheromone[i][j] = (1 - evaporation) * pheromone[i][j];
                pheromone[j][i] = pheromone[i][j];
            }
        }

        for (Ant ant : ants) {
            int j = ant.getCurrent();
            int i = ant.getPrevious();
            pheromone[i][j] += Q / (1 / weight[i][j]);
            pheromone[j][i] = pheromone[i][j];
        }
    }
}
