package week2;

import java.util.*;
import java.io.*;
public class BOJ_1092_배 {

    public static int N, M;
    public static ArrayList<Integer> box = new ArrayList<>();
    public static ArrayList<Integer> crane = new ArrayList<>();
    public static int ans = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            crane.add(Integer.parseInt(st.nextToken()));
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            box.add(Integer.parseInt(st.nextToken()));
        }

        crane.sort(Collections.reverseOrder());
        box.sort(Collections.reverseOrder());

        System.out.println(crane);
        System.out.println(box);

        if(crane.get(0) < box.get(0)){
            System.out.println("-1");
            return;
        }

        while(!box.isEmpty()){
            int idx = 0;
            for(int i = 0; i < crane.size();){
                if(crane.get(i) >= box.get(idx)){
                    i++;
                    box.remove(idx);
                }
                else if(crane.get(i) < box.get(idx)){
                    idx++;
                }
                if(idx == box.size()) break;
            }

            ans += 1;
        }
        System.out.println(ans);

    }
}
