package week2;

import java.util.*;
import java.io.*;

public class BOJ_14503_로봇청소기 {


    public static int N, M;
    public static int r, c, d;
    public static int[][] graph;
    public static boolean[][] visited;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        visited = new boolean[N][M];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 1;
        visited[r][c] = true;

        while (true) {
            boolean flag = false;
            for (int i = 0; i < 4; i++) {
                d = (d + 3) % 4; // 반시계 회전
                int nextX = r + dx[d];
                int nextY = c + dy[d];
                if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && graph[nextX][nextY] == 0 && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    cnt++;
                    r = nextX;
                    c = nextY;
                    flag = true;
                    break;
                }
            }
            if (!flag) { // 네방향 모두 청소할 수 없는 경우
                int backX = r - dx[d]; // 후진할 위치의 행 계산
                int backY = c - dy[d]; // 후진할 위치의 열 계산
                if (backX >= 0 && backX < N && backY >= 0 && backY < M && graph[backX][backY] != 1) {
                    r = backX;
                    c = backY;
                } else {
                    break; // 후진할 수 없는 경우(벽이 있는 경우) 종료
                }
            }
        }

        System.out.println(cnt);
    }
}

