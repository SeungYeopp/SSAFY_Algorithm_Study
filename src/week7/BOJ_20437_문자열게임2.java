package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ_20437_문자열게임2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String w = br.readLine();
            int k = Integer.parseInt(br.readLine());

            if (k == 1) {
                sb.append("1 1").append("\n");
                continue;
            }

            Map<Character, List<Integer>> map = new HashMap<>();
            int minLength = Integer.MAX_VALUE;
            int maxLength = Integer.MIN_VALUE;

            for (int j = 0; j < w.length(); j++) {
                char s = w.charAt(j);
                map.putIfAbsent(s, new ArrayList<>());
                map.get(s).add(j);
            }

            for (char key : map.keySet()) {
                List<Integer> indices = map.get(key);
                if (indices.size() < k) continue;

                for (int j = 0; j <= indices.size() - k; j++) {
                    int length = indices.get(j + k - 1) - indices.get(j) + 1;
                    minLength = Math.min(minLength, length);
                    maxLength = Math.max(maxLength, length);
                }
            }

            if (minLength == Integer.MAX_VALUE || maxLength == Integer.MIN_VALUE) {
                sb.append("-1").append("\n");
            } else {
                sb.append(minLength).append(" ").append(maxLength).append("\n");
            }
        }
        System.out.println(sb);
    }
}
