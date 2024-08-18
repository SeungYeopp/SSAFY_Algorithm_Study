package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3234_준환이의양팔저울{
    static int N, res;
    static int[] weight, arr;
    static boolean[] isSelected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            weight = new int[N];
            arr = new int[N];
            isSelected = new boolean[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                weight[i] = Integer.parseInt(st.nextToken());
            }

            res = 0;
            perm(0);
            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }

        System.out.println(sb.toString());

    }
    static void perm(int cnt){
        if (cnt == N) {
            subset(0, 0, 0);
        }

        for(int i = 0 ; i < N ; i++) {
            if(isSelected[i]) continue;
            arr[cnt] = weight[i];
            isSelected[i] = true;
            perm(cnt+1);
            isSelected[i] = false;
        }
    }

    static void subset(int cnt, int left, int right) {
        if(left < right) return;
        if (cnt == N) {
            res++;
            return;
        }
        subset(cnt + 1, left, right + arr[cnt]);
        subset(cnt+1, left + arr[cnt], right);
    }

}
