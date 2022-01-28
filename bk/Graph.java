package kr.ac.knu.sslab.input.tsp;

import java.util.ArrayList;

public class Graph {
    private Node root = null;
    private long length = 0;

    public class Edge {
        public class Weight {
            private double weight;
        }
        private long target;
        private Weight weight;
    }

    public class Node {
        private long id;
        private ArrayList<Edge> edge;

        public Node() {

        }

        public void addNode() {
            
        }
    }

    public Graph() {
        //if (root == null) {
        //    root = Node();
        //}
    }
}
