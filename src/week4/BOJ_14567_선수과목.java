package week4;
import java.util.*;
import java.io.*;

public class BOJ_14567_선수과목 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer> arr[] = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<Integer>();
        }

        int[] cnt = new int[N+1];

        for(int i = 1; i <= M; i++) {
            st= new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a].add(b);
        }

        for(int i = 1; i <= N; i++) {
            for(int a: arr[i]) {
                cnt[a] = Math.max(cnt[a], cnt[i] + 1);
            }
        }

        for(int i = 1; i <= N; i++) {
            System.out.print(cnt[i]+1 + " ");
        }

    }
}
