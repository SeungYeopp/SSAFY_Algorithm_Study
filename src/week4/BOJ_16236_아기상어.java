package week4;
import java.util.*;
import java.io.*;
public class BOJ_16236_아기상어 {
    static int N;
    static int[][] graph;
    static int sharkSize = 2;
    static int curX, curY;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];

        // 맵 저장 및 상어 위치 설정
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 9) {
                    curX = i;
                    curY = j;
                    graph[i][j] = 0;
                }
            }
        }

        // ans: 총 시간 / foodEat: 상어가 먹은 물고기 수
        int ans = 0;
        int foodEat = 0;

        while (true) {
            // BFS -> 현재 위치에서 가장 가까운 먹이 찾기
            int[] result = solve(bfs());

            if (result == null) {
                System.out.println(ans);
                break;
            } else {
                // 가장 가까운 먹이 위치로 이동 + 먹이까지 거리(총 시간 업데이트) + 먹은 물고기 수 증가
                curX = result[0];
                curY = result[1];
                ans += result[2];
                graph[curX][curY] = 0;
                foodEat++;

                // 먹은 물고기 수 >= 상어 크기, 상어 크기 증가/먹은 물고기 수 초기화
                if (foodEat >= sharkSize) {
                    sharkSize++;
                    foodEat = 0;
                }
            }
        }
    }
    public static int[][] bfs() {
        int[][] visited = new int[N][N];

        // 모든 위치 -1로 초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], -1);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{curX, curY});
        visited[curX][curY] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                //범위 && 방문X && 상어가 지나갈 수 O
                if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
                    if (sharkSize >= graph[nextX][nextY] && visited[nextX][nextY] == -1) {
                        visited[nextX][nextY] = visited[x][y] + 1;
                        queue.add(new int[]{nextX, nextY});
                    }
                }
            }
        }
        return visited;
    }

    // BFS -> 가장 가까운 먹이 위치
    public static int[] solve(int[][] visited) {
        int minDistance = INF;
        int x = -1, y = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 방문 && 상어보다 작은 물고기 O
                if (visited[i][j] != -1 && graph[i][j] >= 1 && graph[i][j] < sharkSize) {
                    // 현재까지 최소거리보다 가까울 경우
                    if (visited[i][j] < minDistance) {
                        minDistance = visited[i][j];
                        x = i;
                        y = j;
                    }
                }
            }
        }

        if (minDistance == INF) {
            return null;
        } else {
            return new int[]{x, y, minDistance};
        }
    }

}
