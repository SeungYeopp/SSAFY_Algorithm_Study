package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HSAT_7_자동차테스트 {
    static int n, q;
    static int[] cars;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        cars = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cars[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cars);

//        System.out.println(Arrays.toString(cars));

        for (int i = 0; i < q; i++) {
            int find = Integer.parseInt(br.readLine());
            int idx = biSearch(find);

            if (find == cars[0] || find == cars[n - 1]) {
                sb.append(0).append("\n");
            }
            else if(idx == -1) sb.append(0).append("\n");
            else sb.append(idx * (n-idx-1)).append("\n");
        }
        System.out.println(sb);
    }

    static int biSearch(int find) {
        int s = 0;
        int e = n;

        while(s < e) {
            int m = (s+e)/2;

            if(cars[m] > find) {
                e = m;
            }
            else if(cars[m] < find) {
                s = m+1;
            }
            else {
                return m;
            }
        }

        return -1;
    }
}
