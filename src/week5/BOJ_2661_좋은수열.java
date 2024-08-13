package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2661_좋은수열 {

    private static int N;
    private static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        backtrack("");
    }

    private static void backtrack(String res) {
        if(flag) return;
        if(res.length() == N){
            System.out.println(res);
            flag = true;
            return;
        }else{
            for(int i = 1; i <= 3; i++){
                if(check(res + i)) backtrack(res+i);
            }
        }
    }

    private static boolean check(String s){
        int len = s.length() / 2;

        for(int i = 1; i <= len; i++){
            String last = s.substring(s.length() - i);
            String prev = s.substring(s.length() - 2 * i, s.length() - i);
            if(last.equals(prev)) return false;
        }
        return true;
    }
}

