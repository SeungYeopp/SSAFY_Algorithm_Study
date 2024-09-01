package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_5972_택배배송 {
    static int N, M;
    static int inf = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        int result = dijkstra(graph, 1, N);
        System.out.println(result);

    }

    static int dijkstra(List<List<Node>> graph, int startV, int destV) {
        int[] distances = new int[graph.size()];
        Arrays.fill(distances, inf);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startV, 0));
        distances[startV] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int curV = current.vertex;
            int curD = current.cost;

            for (Node neighbor : graph.get(curV)) {
                int nextV = neighbor.vertex;
                int nextD = curD + neighbor.cost;

                if (nextD < distances[nextV]) {
                    pq.add(new Node(nextV, nextD));
                    distances[nextV] = nextD;
                }
            }
        }
        return distances[destV];
    }
}

class Node implements Comparable<Node> {
    int vertex;
    int cost;

    Node(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node other) {
        return this.cost - other.cost;
    }
}
