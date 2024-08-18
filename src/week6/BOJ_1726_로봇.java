package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1726_로봇 {
    static int m, n;
    static int[][] graph;
    static boolean[][][] isVisited;
    static int cur_x, cur_y, cur_dir;
    static int dest_x, dest_y, dest_dir;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        graph = new int[m][n];
        isVisited = new boolean[m][n][4];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        cur_x = Integer.parseInt(st.nextToken()) - 1;
        cur_y = Integer.parseInt(st.nextToken()) - 1;
        cur_dir = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        dest_x = Integer.parseInt(st.nextToken()) - 1;
        dest_y = Integer.parseInt(st.nextToken()) - 1;
        dest_dir = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{cur_x, cur_y, cur_dir, 0});
        isVisited[cur_x][cur_y][cur_dir] = true;

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            int d = tmp[2];
            int cnt = tmp[3];

            if (x == dest_x && y == dest_y && d == dest_dir) {
                return cnt;
            }

            for (int i = 1; i <= 3; i++) {
                int next_x = x + dx[d] * i;
                int next_y = y + dy[d] * i;

                if(next_x < 0 || next_x >= m || next_y < 0 || next_y >= n) break;

                if(graph[next_x][next_y] == 1) break;

                if (!isVisited[next_x][next_y][d]) {
                    isVisited[next_x][next_y][d] = true;
                    q.add(new int[]{next_x, next_y, d, cnt + 1});
                }
            }

            int left = 0;
            int right = 0;


            switch(d) {
                case 0: left = 3; right = 2; break; // 동: 왼쪽(북), 오른쪽(남)
                case 1: left = 2; right = 3; break; // 서: 왼쪽(남), 오른쪽(북)
                case 2: left = 0; right = 1; break; // 남: 왼쪽(동), 오른쪽(서)
                case 3: left = 1; right = 0; break; // 북: 왼쪽(서), 오른쪽(동)
            }

            if (!isVisited[x][y][left]) {
                isVisited[x][y][left] = true;
                q.add(new int[]{x, y, left, cnt + 1});
            }

            if (!isVisited[x][y][right]) {
                isVisited[x][y][right] = true;
                q.add(new int[]{x, y, right, cnt + 1});
            }
        }

        return -1;
    }
}
