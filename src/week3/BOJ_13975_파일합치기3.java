package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13975_파일합치기3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            PriorityQueue<Long> pq = new PriorityQueue<>();
            int K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                pq.add(Long.parseLong(st.nextToken()));
            }
            long sum= 0L;
            while(pq.size()>1) {
                long first = pq.poll();
                long second = pq.poll();
                sum += first + second;
                pq.add(first + second);
            }

            sb.append(sum);
            sb.append("\n");
        }

        System.out.println(sb);

    }

}
