import java.io.*;
import java.util.*;


public class BOJ_1969_DNA{
    private static final char[] NUCLEOTIDES = {'A', 'C', 'G', 'T'};
    public static void main(String[] args) throws IOException {
        // 1. 입력
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] dna = new String[N];
        for(int i = 0; i < N; i++){
            dna[i] = bf.readLine();
        }

        StringBuilder optimalDna = new StringBuilder();
        int totalDistance = 0;

        for(int i = 0; i < M; i++){
            int[] count = new int[4]; // A C G T

            // 각 위치별로 가장 많이 등장하는 뉴클레오티드 찾기
            for(String arr: dna){
                char nucleotide = arr.charAt(i);

                switch(nucleotide){
                    case 'A': count[0]++; break;
                    case 'C': count[1]++; break;
                    case 'G': count[2]++; break;
                    case 'T': count[3]++; break;
                }
            }

            // Hamming Distance 합이 가장 작은 DNA와 Hamming Distance 계산
            char max_nucleotide = 'A';
            int max_cnt = count[0];
            for(int j = 1; j < 4; j++){
                if(count[j] > max_cnt){
                    max_cnt = count[j];
                    max_nucleotide = NUCLEOTIDES[j];
                }
            }

            optimalDna.append(max_nucleotide);
            totalDistance += (N-max_cnt);


        }
        System.out.println(optimalDna);
        System.out.println(totalDistance);
    }
}