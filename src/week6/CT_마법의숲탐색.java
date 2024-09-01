package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CT_마법의숲탐색 {

    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, -1, 0, 1};
    private static int n, m, k;
    private static int[][] forest;
    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 행
        m = Integer.parseInt(st.nextToken()); // 열
        k = Integer.parseInt(st.nextToken()); // 골렘 수

        forest = new int[n][m]; // 숲

        for (int no = 1; no <= k; no++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken()) - 1;   // 골렘이 시작하는 열 (0부터 시작하도록 조정)
            int d = Integer.parseInt(st.nextToken());       // 골렘의 출구 방향 (0: 북, 1: 동, 2: 남, 3: 서)

            // 골렘 이동 후 최종 위치를 반환
            int[] res = move(c, d, no);
            boolean inForest;

            if (res[0] == 1)  inForest = true;
            else inForest = false;

            int x = res[1];
            int y = res[2];

            // 골렘이 숲 안에 제대로 위치했으면 BFS를 통해 점수 계산
            if (inForest) {
                ans += bfs(x, y, no);
            } else {
                // 골렘이 숲 밖에 위치하면 보드를 초기화
                forest = new int[n][m];
            }
        }

        System.out.println(ans);
    }

    // 골렘의 출구 위치를 계산
    private static int[] getExit(int x, int y, int d) {
        if (d == 0) {           // 북
            return new int[]{x - 1, y};
        } else if (d == 1) {    // 동
            return new int[]{x, y + 1};
        } else if (d == 2) {    // 남
            return new int[]{x + 1, y};
        } else {                // 서
            return new int[]{x, y - 1};
        }
    }

    // 주어진 좌표가 숲 안에 있는지
    private static boolean inBoard(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m;
    }

    // 골렘이 주어진 좌표로 이동할 수 있는지
    private static boolean check(int x, int y) {
        if (!inBoard(x, y)) { // 좌표가 숲 밖에 위치하면
            if (x < n && y >= 0 && y < m) {
                return true; // 특수 Case: 숲 밖이지만 유효한 위치일 경우
            }
        } else { // 좌표가 숲 안에 위치하면
            if (forest[x][y] == 0) { // 다른 골렘이 없는지 확인
                return true;
            }
        }
        return false;
    }

    // 골렘을 이동시키고 최종 위치와 상태를 반환
    private static int[] move(int c, int d, int no) {
        int x = -2; // 숲의 맨 위 두 칸 위에서 시작 (x = -2)
        int y = c; /// 주어진 열에서 시작
        while (true) {
            // 골렘을 아래로 이동
            if (check(x + 2, y) && check(x + 1, y - 1) && check(x + 1, y + 1)) {
                x += 1;
            }
            // 골렘 왼쪽 이동
            else if (check(x + 1, y - 1) && check(x - 1, y - 1) && check(x, y - 2) && check(x + 1, y - 2) && check(x + 2, y - 1)) {
                x += 1;
                y -= 1;
                d = (d - 1 + 4) % 4; // 방향 반시계 방향 회전
            }
            // 골렘 오른쪽 이동
            else if (check(x + 1, y + 1) && check(x - 1, y + 1) && check(x, y + 2) && check(x + 1, y + 2) && check(x + 2, y + 1)) {
                x += 1;
                y += 1;
                d = (d + 1) % 4; // 방향 시계 방향 회전
            } else { // 골렘이 더 이상 이동할 수 없으면 이동 중단
                break;
            }
        }

        // 골렘 지도에 표시
        if (!inBoard(x, y) || !inBoard(x + 1, y) || !inBoard(x - 1, y) || !inBoard(x, y + 1) || !inBoard(x, y - 1)) {
            return new int[]{0, -1, -1};    // 골렘이 범위를 벗어남
        } else {
            forest[x][y] = forest[x + 1][y] = forest[x - 1][y] = forest[x][y + 1] = forest[x][y - 1] = no;   // 골렘 번호를 각 맵 위치에 넣어둗(다른 골렘이랑 구분)
            int[] exit = getExit(x, y, d); // 출구 위치 x,y : 정령의 위치, d : 출구 방향
            int ex = exit[0];
            int ey = exit[1];
            forest[ex][ey] = -no;    // 출구를 -골렘번호 로 설정
            return new int[]{1, x, y};
        }
    }

    // 정령이 이동할 수 있는 최대 위치를 탐색하여 점수를 계산
    private static int bfs(int sx, int sy, int no) {
        List<Integer> cand = new ArrayList<>(); // 정령이 방문할 수 있는 y좌표를 저장할 리스트
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy});
        boolean[][] visit = new boolean[n][m];
        visit[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (!inBoard(nx, ny) || visit[nx][ny] || forest[nx][ny] == 0) {  // 새로운 위치가 보드 범위를 벗어나거나 이미 방문했거나 골렘이 없는 빈 칸이면 무시
                    continue;
                }
                // 같은 골렘의 부분이거나 출구인 경우 또는 현재 위치가 출구이고 다음 위치가 다른 골렘의 부분인 경우
                if (Math.abs(forest[x][y]) == Math.abs(forest[nx][ny]) || (forest[x][y] < 0 && Math.abs(forest[nx][ny]) != Math.abs(forest[x][y]))) {
                    q.add(new int[]{nx, ny});
                    visit[nx][ny] = true;
                    cand.add(nx);
                }
            }
        }

        // 정령이 도달할 수 있는 모든 y좌표를 수집 후 내림차순으로 정렬
        Collections.sort(cand, Collections.reverseOrder());
        return cand.get(0) + 1; // 가장 큰 y좌표를 반환 (+1을 하는 이유: 0-based index 보정)
    }
}