import java.io.*;
import java.util.*;

public class D1 {
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

    static Integer[][][] dp;
    static int helper(int i, int j, String s, StringBuilder bob, StringBuilder alice, boolean flag){
      if(i > j){
        // out.println(bob + " " + alice + " " + i + " "  + j);
        int v = bob.compareTo(alice);
        if(v == 0) return 0;
        else if(v > 0) return 1; // alice wins
        else return -1; // bob wins
      }

      // falg == true alice, flag == false, bob
      int idx = flag ? 1 : 0;
      if(dp[i][j][idx] != null) return dp[i][j][idx];

      if(flag){
        alice.insert(0, s.charAt(i));
        int a = helper(i+1, j, s, bob, alice, !flag);
        alice.deleteCharAt(0);
        if(a > 0) return dp[i][j][idx] = 1;

        alice.insert(0, s.charAt(j));
        int b = helper(i, j - 1, s, bob, alice, !flag);
        alice.deleteCharAt(0);
        if(b > 0) return dp[i][j][idx] = 1;
        
        if(a == 0 || b == 0) return dp[i][j][idx] = 0;
        else return dp[i][j][idx] = -1;
      }else{
        bob.insert(0, s.charAt(i));
        int a = helper(i+1, j, s, bob, alice, !flag);
        bob.deleteCharAt(0);
        if(a < 0) return dp[i][j][idx] = -1;

        bob.insert(0, s.charAt(j));
        int b = helper(i, j - 1, s, bob, alice, !flag);
        bob.deleteCharAt(0);
        if(b < 0) return dp[i][j][idx] = -1;
        

        if(a == 0 || b == 0) return dp[i][j][idx] = 0;
        else return dp[i][j][idx] = 1;
      }
    }
    public static void solve() {
         String s = sc.nextLine();

         int n = s.length();
         dp = new Integer[n+1][n+1][2];
         StringBuilder bob = new StringBuilder();
         StringBuilder alice = new StringBuilder();
         int ans = helper(0, n-1, s, bob, alice, true);
         if(ans > 0){
          out.println("Alice");
         }else if(ans == 0){
          out.println("Draw");
         }else{
          out.println("Bob");
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