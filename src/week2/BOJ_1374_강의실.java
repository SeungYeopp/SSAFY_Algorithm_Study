package week2;

import java.util.*;
import java.io.*;

public class BOJ_1374_강의실 {
    public static int N;
    public static int[][] lecture;
    public static int ans = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        lecture = new int[N][3];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            lecture[i][0] = Integer.parseInt(st.nextToken());
            lecture[i][1] = Integer.parseInt(st.nextToken());
            lecture[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(lecture, Comparator.comparingInt(x -> x[1]));

        PriorityQueue<Integer> classroom = new PriorityQueue<>();

        for(int i = 0; i < N; i++){
            if(!classroom.isEmpty() && classroom.peek() <= lecture[i][1]){
                    classroom.poll();
            } else{
                ans++;
            }
            classroom.offer(lecture[i][2]);
        }

        System.out.println(ans);
    }
}
