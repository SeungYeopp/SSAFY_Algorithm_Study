package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_3190_뱀 {
    static int[][] graph;
    static List<int[]> snake = new ArrayList<>();
    static Queue<int[]> directionChanges = new LinkedList<>();
    static int N, K, L;

    // 동 남 서 북
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        graph = new int[N][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph[a][b] = 1;
        }

        L = Integer.parseInt(br.readLine());

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            String c = st.nextToken();

            int direction;
            if (c.equals("D")) {
                direction = 1; // 오른쪽 전환
            } else {
                direction = -1; // 왼쪽 전환
            }
            directionChanges.add(new int[] { x, direction });
        }

        solve();
    }

    public static void solve(){
        int cx = 0, cy = 0;
        int time = 0;
        int d = 0; // 0: 동, 1: 남, 2: 서, 3: 북
        snake.add(new int[] { 0, 0 });

        int[] nextChange = directionChanges.poll();

        while (true) {
            time++;

            int nx = cx + dx[d];
            int ny = cy + dy[d];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) { // 범위를 벗어났을 때
                break;
            }

            boolean collision = false;
            for (int[] t : snake) { // 뱀의 몸통과 충돌했을 때
                if (nx == t[0] && ny == t[1]) {
                    collision = true;
                    break;
                }
            }
            if (collision) {
                break;
            }

            if (graph[nx][ny] == 1) {
                graph[nx][ny] = 0; // 사과 제거
                snake.add(new int[] { nx, ny }); // 뱀 길이 증가
            } else {
                snake.add(new int[] { nx, ny }); // 뱀 머리 이동
                snake.remove(0); // 꼬리 제거
            }

            if (nextChange != null && time == nextChange[0]) {
                d = (d + nextChange[1] + 4) % 4;
                nextChange = directionChanges.poll();
            }

            cx = nx;
            cy = ny;
        }

        System.out.println(time);
    }

}
