package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16234_인구이동 {

    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int res = 0;

        while (true) {
            visited = new boolean[N][N];
            boolean isMoved = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        boolean flag = bfs(i, j);
                        if (flag) { // 연합 형성
                            isMoved = true; // 인구이동 발생
                        }
                    }
                }
            }

            if (!isMoved) {
                break;
            }

            res++;
        }

        System.out.println(res);
    }

    static boolean bfs(int x, int y) {
        List<int[]> united = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[] {x, y}); // 시작점 큐에 추가
        visited[x][y] = true; // visited
        united.add(new int[] {x, y}); // 시작점 연합 추가

        int sumPopulation = map[x][y]; // 연합 총 인구수 초기화

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) { // 경계 내에 있고 방문하지 않은 나라라면
                    int diff = Math.abs(map[cx][cy] - map[nx][ny]); // 인구 차이 계산
                    if (diff >= L && diff <= R) {
                        queue.offer(new int[] {nx, ny});
                        visited[nx][ny] = true;
                        united.add(new int[] {nx, ny});
                        sumPopulation += map[nx][ny];
                    }
                }
            }
        }

        if (united.size() > 1) { // 연합에 둘 이상의 나라 존재
            int average = sumPopulation / united.size(); // 평균
            for (int[] pos : united) {
                map[pos[0]][pos[1]] = average; // 인구수 평균으로 update
            }
            return true; // 연합 형성
        }

        return false; // 연합 실패
    }
}
