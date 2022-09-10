import java.io.*;
import java.util.*;

public class E {
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

    public static void solve() {
         int n = sc.nextInt(), q = sc.nextInt();
         BIT bit = new BIT(1001, 1001);
        //  long[][] arr = new long[1001][1001];
         for(int i = 0; i < n; i++){
          int x = sc.nextInt();
          int y = sc.nextInt();
          bit.update(x, y, (long)x*y);
         }

         // calculate 2-d prefix Sum
        //  out.println(bit.query(1, 1, 999, 999));
         for(int i = 0; i < q; i++){
            int hs = sc.nextInt(), ws = sc.nextInt(), hq = sc.nextInt(), wq = sc.nextInt();
            long ans = bit.query(hs+1, ws+1, hq-1, wq-1);
            out.println(ans);
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

    static class BIT {
      long[][] tree;
      int n, m;

      BIT(int n, int m) {
          this.n = n;
          this.m = m;
          tree = new long[n + 1][m + 1];
          // for (int i = 1; i < n; i++) {
          // for (int j = 1; j < m; j++) {
          // update(i, j, mat[i][j]);
          // }
          // }
      }

      void update(int x, int y, long val) {
          while (x <= n) {
              int t = y;
              while (t <= m) {
                  tree[x][t] += val;
                  t += t & -t;
              }
              x += x & -x;
          }
      }

      long query(int x1, int y1, int x2, int y2) {
          return getSum(x2, y2) - getSum(x1 - 1, y2) - getSum(x2, y1 - 1) + getSum(x1 - 1, y1 - 1);
      }

      long getSum(int x, int y) {
          long ans = 0L;
          while (x > 0) {
              int t = y;
              while (t > 0) {
                  ans += tree[x][t];
                  t -= t & -t;
              }
              x -= x & -x;
          }
          return ans;
      }
  }
}