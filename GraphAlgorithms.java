class GraphAlgorithms {

    static final int V = 6;
    static final int INF = 99999;

    static String[] landmarks = {
        "College", "Library", "Hostel", "Canteen", "Station", "Mall"
    };

    static void display(int[] dist) {
        System.out.println("\nLandmark\t\tDistance from College");
        for (int i = 0; i < V; i++) {
            System.out.println(landmarks[i] + "\t\t" + dist[i]);
        }
    }

    static void dijkstra(int[][] graph, int src) {
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = INF;
        }
        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {
            int min = INF, u = -1;

            for (int v = 0; v < V; v++) {
                if (!visited[v] && dist[v] <= min) {
                    min = dist[v];
                    u = v;
                }
            }

            if (u == -1) break;

            visited[u] = true;

            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 &&
                    dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        System.out.println("\nDijkstra’s Algorithm Results:");
        display(dist);
    }

    static void bellmanFord(int[][] edges, int E, int src) {
        int[] dist = new int[V];

        for (int i = 0; i < V; i++) {
            dist[i] = INF;
        }
        dist[src] = 0;

        for (int i = 1; i < V; i++) {
            for (int j = 0; j < E; j++) {
                int u = edges[j][0];
                int v = edges[j][1];
                int w = edges[j][2];
                if (dist[u] != INF && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        for (int j = 0; j < E; j++) {
            int u = edges[j][0];
            int v = edges[j][1];
            int w = edges[j][2];
            if (dist[u] != INF && dist[u] + w < dist[v]) {
                System.out.println("\nNegative weight cycle detected!");
                return;
            }
        }

        System.out.println("\nBellman-Ford Algorithm Results:");
        display(dist);
    }

    public static void main(String[] args) {
        int[][] adj = {
            {0,  2,  4,  0,  0,  0},
            {2,  0,  1,  7,  0,  0},
            {4,  1,  0,  3,  5,  0},
            {0,  7,  3,  0,  2,  6},
            {0,  0,  5,  2,  0,  1},
            {0,  0,  0,  6,  1,  0}
        };

        dijkstra(adj, 0);

        int[][] edges = {
            {0, 1, 2}, {0, 2, 4},
            {1, 2, 1}, {1, 3, 7},
            {2, 3, 3}, {2, 4, 5},
            {3, 4, 2}, {3, 5, 6},
            {4, 5, 1}
        };

        bellmanFord(edges, edges.length, 0);
    }
}
