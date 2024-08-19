package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17070_파이프옮기기1 {
    static int N;
    static int[][] graph;
    static int ans;

    // 파이프의 이동 방향을 나타내는 배열 {x, y, direction}
    // {0, 1, 0} : 가로 방향 (오른쪽으로 이동)
    // {1, 0, 1} : 세로 방향 (아래쪽으로 이동)
    // {1, 1, 2} : 대각선 방향 (오른쪽 아래로 이동)
    static int[][] directions = { {0, 1, 0}, {1, 0, 1}, {1, 1, 2} };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
        BFS(0, 1, 0);

        System.out.println(ans);
    }

    public static void BFS(int x, int y, int direction) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {x, y, direction});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cur_x = current[0];
            int cur_y = current[1];
            int cur_dir = current[2];

            if (cur_x == N - 1 && cur_y == N - 1) {
                ans++;
                continue;
            }

            for (int i = 0; i < 3; i++) {
                if (cur_dir == 0 && i == 1) continue; // 가로 방향에서는 세로로 이동 불가
                if (cur_dir == 1 && i == 0) continue; // 세로 방향에서는 가로로 이동 불가

                int next_x = cur_x + directions[i][0];
                int next_y = cur_y + directions[i][1];
                int next_dir = directions[i][2];

                if (next_x >= N || next_y >= N || graph[next_x][next_y] == 1) continue;

                // 대각선 방향으로 이동할 경우 check (대각선 이동 시, 3개의 칸 모두가 빈 칸이어야 함)
                if (next_dir == 2 && (graph[cur_x + 1][cur_y] == 1 || graph[cur_x][cur_y + 1] == 1 || graph[cur_x + 1][cur_y + 1] == 1)) continue;

                queue.add(new int[] {next_x, next_y, next_dir});
            }
        }
    }
}
