package kr.ac.knu.sslab.input.tsp;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class Generator {
    private Random rand = new Random();
    private double edgeFactor = 0.4;
    private Graph g;
    public int nodeNum;

    public Generator() {
        int maxNodeNum = 100;
        this.nodeNum = rand.nextInt(maxNodeNum);

        g = new Graph();
        g.edge = new boolean[nodeNum][nodeNum];
        g.weight = new double[nodeNum][nodeNum];
    }

    public Generator(int nodeNum) {
        this.nodeNum = nodeNum;

        g = new Graph();
        g.edge = new boolean[nodeNum][nodeNum];
        g.weight = new double[nodeNum][nodeNum];
    }

    public Generator(int nodeNum, double edgeFactor) {
        this.nodeNum = nodeNum;
        this.edgeFactor = edgeFactor;

        g = new Graph();
        g.edge = new boolean[nodeNum][nodeNum];
        g.weight = new double[nodeNum][nodeNum];
    }

    private boolean createEdge() {
        if (rand.nextDouble() < edgeFactor) {
            return true;
        }
        return false;
    }

    private double createWeight() {
        int unit = 100000;
        return (double) Math.round(rand.nextDouble() * unit) / unit ;
    }

    public void saveInputAsFile() {
        Path source = Paths.get(this.getClass().getResource("/").getPath());
        Path target = Paths.get(source.toAbsolutePath() + "/tsp");
        
        // create the directory
        try {
            Files.createDirectories(target);
        }
        // Skip, if it's existed
        catch (IOException e) {
        }
        // Save the input as file
        finally {
            Path filepath = Paths.get(target.toAbsolutePath() + "/tsp_" + System.currentTimeMillis());
            try {
                FileChannel channel = FileChannel.open(filepath, StandardOpenOption.WRITE);
                String content = "";

                for (int i = 0; i < nodeNum; i++) {
                    for (int j = 0; j < nodeNum; j++) {
                        content += g.edge[i][j] ? "1" : "0";
                        content += j < nodeNum - 1 ? " " : "";
                    }
                    content += "\n";
                }

                for (int i = 0; i < nodeNum; i++) {
                    for (int j = 0; j < nodeNum; j++) {
                        content += g.weight[i][j];
                        content += j < nodeNum - 1 ? " " : "";
                    }
                    content += "\n";
                }

                channel.write(Charset.defaultCharset().encode(content));   
            } catch (IOException e) {
                e.getStackTrace();
            }
        }        
    }

    /**
     * Generate 
     * @param nodeNum
     * @return
     */
    public Graph generate() {
        for (int i = 0; i < nodeNum; i++) {
            g.edge[i][i] = false;
            g.weight[i][i] = 0;
            for (int j = i + 1; j < nodeNum; j++) {
                // create edge information
                g.edge[i][j] = createEdge();
                g.edge[j][i] = g.edge[i][j];

                if (g.edge[i][j]) {
                    g.weight[i][j] = createWeight();
                    g.weight[j][i] = g.weight[i][j];          
                } else {
                    g.weight[i][j] = 0;
                    g.weight[j][i] = 0;   
                }                   
            }
        }
        return g;
    }
}
