package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HSAT_7_순서대로방문하기 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M;
    static int[][] map;
    static boolean[][] isVisited;
    static List<int[]> checkPoints = new ArrayList<>();
    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        isVisited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            checkPoints.add(new int[]{x, y});
        }

        isVisited[checkPoints.get(0)[0]][checkPoints.get(0)[1]] = true;
        dfs(checkPoints.get(0)[0], checkPoints.get(0)[1], 0);
        System.out.println(res);
    }

    static void dfs(int x, int y, int depth) {

        if (depth == M - 1) {
            res += 1;
            return;
        }

        if (x == checkPoints.get(depth + 1)[0] && y == checkPoints.get(depth + 1)[1]) {
            isVisited[x][y] = true;
            dfs(x, y, depth + 1);
            isVisited[x][y] = false;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if (map[nx][ny] == 1) continue;
            if (isVisited[nx][ny]) continue;

            isVisited[nx][ny] = true;
            dfs(nx, ny, depth);
            isVisited[nx][ny] = false;
        }
    }
}
