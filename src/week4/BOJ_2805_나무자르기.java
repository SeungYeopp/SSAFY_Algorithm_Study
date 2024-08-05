package week4;
import java.util.*;
import java.io.*;

public class BOJ_2805_나무자르기 {
    public static int N;
    public static int M;
    public static int min;
    public static int max;
    public static int[] trees;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trees = new int[N];
        min = 0;
        max = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            trees[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, trees[i]);
        }

        while (min <= max) {
            int mid = (min + max) / 2;
            long tree = 0;
            for(int i = 0; i < N; i++){
                if(trees[i] >= mid) tree += trees[i] - mid;
            }
            if(tree >= M) min = mid + 1;
            else max = mid-1;
        }
        System.out.println(max);
    }
}
