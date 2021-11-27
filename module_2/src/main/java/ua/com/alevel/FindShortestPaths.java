package ua.com.alevel;

import static java.lang.Math.min;

public class FindShortestPaths {

    static void run(int[][] dist, int n) {
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
    }
}
