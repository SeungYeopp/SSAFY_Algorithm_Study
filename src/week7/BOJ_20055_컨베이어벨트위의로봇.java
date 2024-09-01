package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20055_컨베이어벨트위의로봇 {
    static int N;
    static int K;

    static boolean[] robot;
    static int zeroCount;

    static int[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        map = new int[2 * N];
        robot = new boolean[N];
        zeroCount = 0;

        for (int i = 0; i < 2 * N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        int stepCount = 0;

        while(zeroCount < K) {
            stepCount++;

            // 1) 로봇과 함께 한칸 이동
            step1();

            // 2) 로봇 옮길 수 있으면 한칸 이동. 없으면 가만히.(내구도 1 이상 남아있어야함)
            step2();

            // 3) 올리는 위치에 칸의 내구도가 0이 아니면 로봇을 올림
            step3();

            // 4) 내구도가 0인 칸의 개수가 K개 이상이라면 종료 아니면 1번
        }

        System.out.println(stepCount);
    }

    static void step1() {
        int tmp = map[2 * N - 1];
        for (int i = 2 * N - 1; i > 0; i--) {
            map[i] = map[i - 1];
        }
        map[0] = tmp;

        for (int i = N - 1; i > 0; i--) {
            if (i == N - 1) {
                robot[i] = false;
            } else {
                robot[i] = robot[i - 1];
            }
        }
        robot[0] = false;
    }

    static void step2() {
        for (int i = N - 2; i >= 0; i--) {
            if (robot[i] && !robot[i + 1] && map[i + 1] > 0) {
                robot[i] = false;
                if (i + 1 != N - 1) {
                    robot[i + 1] = true;
                }
                map[i + 1]--;
                if (map[i + 1] == 0) {
                    zeroCount++;
                }
            }
        }
    }

    static void step3() {
        if (map[0] > 0) {
            robot[0] = true;
            map[0]--;
            if (map[0] == 0) {
                zeroCount++;
            }
        }
    }
}