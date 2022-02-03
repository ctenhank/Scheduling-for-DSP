package kr.ac.knu.sslab.algo.aco;

 
/**
 *  * default
 * https://github.com/RonitRay/Ant-Colony-Optimization/blob/master/src/AntColonyOptimization.java
 * private double c = 1.0;             //number of trails
 * private double alpha = 1;           //pheromone importance
 * private double beta = 5;            //distance priority
 * private double evaporation = 0.5;
 * private double Q = 500;             //pheromone left on trail per ant
 * private double antFactor = 0.8;     //no of ants per node
 * private double randomFactor = 0.01; //introducing randomness
 * private int maxIterations = 1000;
 * @param n
 * @param alpha the influence of the level of pheromone, usually set over than 0.
 * @param beta the influence of the attractiveness of the path, usually set over than 1.
 * @param visited the visited node
 */
public class Ant{
    static int n;

    private int cur;
    private int prev;

    private double[] cost;
    private double[] attractiveness;
    private boolean[] visited;

    /**
     * @param n Number of nodes for searching
     */
    public Ant(int n) {
        visited = new boolean[n];
        cost = new double[n];
        attractiveness = new double[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
    }

    public int getCurrent() {
        return cur;
    }

    public int getPrevious() {
        return prev;
    }

    public void setCost(int i, double cost) {
        this.cost[i] = cost;
    }

    public void setAttractiveness(int i, double attr) {
        this.attractiveness[i] = attr;
    }

    public void move() {
        double total = 0.0;
        for (int i = 0; i < n; i++) {
            total += cost[i] * attractiveness[i];
        }

        int next = -1;
        double best = 0.0;
        for (int i = 0; i < n; i++) {
            double probability = cost[i] * attractiveness[i] / total;
            if (!visited[i] && best < probability) {
                best = probability;
                next = i;
            }
        }

        if (next != -1) {
            prev = cur;
            cur = next;
        }
    }
}
