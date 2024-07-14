import java.io.*;
import java.util.*;

public class BOJ_1260_DFS와BFS {
    public static int[][] branch;
    public static boolean[] visited;
    public static int N;
    public static int M;
    public static int V;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        branch = new int[N+1][N+1];
        visited = new boolean[N+1];

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            branch[x][y] = branch[y][x] = 1;
        }

        dfs(V);
        System.out.println();

        Arrays.fill(visited, false);
        bfs(V);
    }

    public static void dfs(int start) {
        visited[start] = true;
        System.out.print(start + " ");
        for (int i = 1; i <= N; i++) {
            if (branch[start][i] == 1 && !visited[i]) {
                dfs(i);
            }
        }
    }

    public static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        System.out.print(start + " ");

        while (!queue.isEmpty()) {
            int tmp = queue.poll();

            for (int i = 1; i < branch.length; i++) {
                if (branch[tmp][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                    System.out.print(i + " ");
                }
            }
        }
    }
}
