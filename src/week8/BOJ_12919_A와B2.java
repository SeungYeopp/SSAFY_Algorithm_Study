package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class BOJ_12919_A와B2 {
    static String s;
    static String t;
    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        t = br.readLine();

        solve(s, t, t.length()-1);
        System.out.println(res);
    }

    static void solve(String s, String t, int idx) {
        if (s.length() == t.length()) {
            if (s.equals(t)) {
                res = 1;
            }
            return;
        }

        if(t.charAt(idx) == 'A') solve(s, t.substring(0, idx), idx - 1);

        StringBuilder sb = new StringBuilder(t).reverse();
        if(sb.charAt(idx) == 'B') solve(s, sb.substring(0, idx), idx - 1);
    }
}
