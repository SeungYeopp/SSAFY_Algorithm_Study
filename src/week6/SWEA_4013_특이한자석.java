package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4013_특이한자석 {
    static int[][] map;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            map = new int[4][8];
            int rot_cnt = Integer.parseInt(br.readLine());

            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 8; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < rot_cnt; i++) {
                arr = new int[4];
                st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken()) - 1;
                int dir = Integer.parseInt(st.nextToken());
                rotate(idx, dir);
            }

            int res = 0;

            if (map[0][0] == 1) res += 1;
            if (map[1][0] == 1) res += 2;
            if (map[2][0] == 1) res += 4;
            if (map[3][0] == 1) res += 8;
            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }
        System.out.println(sb);
    }

    static void rotate(int idx, int dir) {
        arr[idx] = dir;

        for (int i = idx + 1; i < 4; i++) {
            if (map[i][6] == map[i - 1][2]) {
                break;
            } else {
                arr[i] = -arr[i - 1];
            }
        }

        for (int i = idx - 1; i >= 0; i--) {
            if (map[i + 1][6] == map[i][2]) {
                break;
            } else {
                arr[i] = -arr[i + 1];
            }
        }

        for (int i = 0; i < 4; i++) {
            if (arr[i] == 0) continue;
            if (arr[i] == 1) {
                int temp = map[i][7];
                System.arraycopy(map[i], 0, map[i], 1, 7);
                map[i][0] = temp;
            } else if (arr[i] == -1) {
                int temp = map[i][0];
                System.arraycopy(map[i], 1, map[i], 0, 7);
                map[i][7] = temp;
            }
        }
    }
}
