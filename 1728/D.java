import java.io.*;
import java.util.*;

public class D {
    static PrintWriter out;
    static Kioken sc;
    static boolean checkOnlineJudge = System.getProperty("ONLINE_JUDGE") == null;

    public static void main(String[] args) throws FileNotFoundException {
        if (checkOnlineJudge) {
            out = new PrintWriter("E:/CF_V2/output.txt");
            sc = new Kioken(new File("E:/CF_V2/input.txt"));
        } else {
            out = new PrintWriter((System.out));
            sc = new Kioken();
        }

        int tt = 1;
        tt = sc.nextInt();
        while (tt-- > 0) {
            solve();
        }
        out.flush();
        out.close();
    }

    static Integer[][] dp;
    static int helper(int i, int j, String s){
        if(i > j) return 0;


        if(dp[i][j] != null) return dp[i][j];
        // if alice choose i-th
        // bob -> i+1, j
        boolean nxtWin1 = false, nxtDraw1 = false;
        char c1 = s.charAt(i);

        char c2 = s.charAt(i+1);
        int a1 = helper(i+2,j,s);
        // if from 0 --- i, if this is draw, then this is deciding factor, only
        if(a1 == 0){
            if(c1 == c2) nxtDraw1 = true;
            else if(c1 < c2){}
            else if(c1 > c2){ nxtWin1 = true; } 
        }else if(a1 < 0){
            nxtWin1 = true;
        }

        c2 = s.charAt(j);
        a1 = helper(i+1, j-1, s);
        if(a1 == 0){
            if(c1 == c2) nxtDraw1 = true;
            else if(c1 < c2){}
            else if(c1 > c2){ nxtWin1 = true; } 
        }else if(a1 < 0){
            nxtWin1 = true;
        }

        if(nxtWin1 == false && nxtDraw1 == false) return dp[i][j] = 1;

        boolean nxtWin2 = false, nxtDraw2 = false;
        c1 = s.charAt(j);
        c2 = s.charAt(i);

        a1 = helper(i+1, j-1, s);
        if(a1 == 0){
            if(c1 == c2) nxtDraw2 = true;
            else if(c1 < c2){}
            else if(c1 > c2){ nxtWin2 = true; } 
        }else if(a1 < 0){
            nxtWin2 = true;
        }

        c2 = s.charAt(j-1);
        a1 = helper(i, j - 2, s);
        if(a1 == 0){
            if(c1 == c2) nxtDraw2 = true;
            else if(c1 < c2){}
            else if(c1 > c2){ nxtWin2 = true; } 
        }else if(a1 < 0){
            nxtWin2 = true;
        }

        if(nxtWin2 == false && nxtDraw2 == false) return dp[i][j] = 1;

        if(nxtWin1 == false && nxtDraw1 || (!nxtWin2 && nxtDraw2)) return dp[i][j] = 0;
        return dp[i][j] = -1;
    }
    public static void solve() {
         String s = sc.nextLine();
         
         int n = s.length();

         dp = new Integer[n+1][n+1];
         int ans = helper(0, n - 1, s);
        //  out.println("--> " + ans);
         if(ans < 0){
            out.println("Bob");
         }else if(ans > 0){
            out.println("Alice");
         }else{
            out.println("Draw");
         }

    }

    public static long gcd(long a, long b) {
        while (b != 0) {
            long rem = a % b;
            a = b;
            b = rem;
        }
        return a;
    }

    static long MOD = 1000000007;
    static void reverseSort(int[] arr){List<Integer> list = new ArrayList<>();for (int i=0; i<arr.length; i++){list.add(arr[i]);}Collections.sort(list, Collections.reverseOrder());for (int i = 0; i < arr.length; i++){arr[i] = list.get(i);}}
    static void sort(int[] a) {
        ArrayList<Integer> l=new ArrayList<>();
        for (int i:a) l.add(i);
        Collections.sort(l);
        for (int i=0; i<a.length; i++) a[i]=l.get(i);
    }
    static void sort(long[] a){
        ArrayList<Long> l=new ArrayList<>();
        for (long i:a) l.add(i);
        Collections.sort(l);
        for (int i=0; i<a.length; i++) a[i]=l.get(i);
    }
    
    static class Kioken {
        // FileInputStream br = new FileInputStream("input.txt");
        BufferedReader br;
        StringTokenizer st;

        Kioken(File filename) {
            try {
                FileReader fr = new FileReader(filename);
                br = new BufferedReader(fr);
                st = new StringTokenizer("");

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }

        Kioken() {
            try {
                br = new BufferedReader(new InputStreamReader(System.in));
                st = new StringTokenizer("");

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }

        public String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public String nextLine() {
            try {
                return br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public boolean hasNext() {
            String next = null;
            try {
                next = br.readLine();
            } catch (Exception e) {
            }
            if (next == null || next.length() == 0) {
                return false;
            }
            st = new StringTokenizer(next);
            return true;
        }

        public int[] readArrayInt(int n){
            int[] arr = new int[n];
            for(int i = 0; i < n; i++){
                arr[i] = nextInt();
            }
            return arr;
        }

        public long[] readArrayLong(int n){
            long[] arr = new long[n];
            for(int i = 0; i < n; i++){
                arr[i] = nextLong();
            }
            return arr;
        }
    }
}