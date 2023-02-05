import java.io.*;
import java.util.*;

public class C {
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
        int testCase = 1;
        tt = sc.nextInt();
        while (tt-- > 0) {
            solve(testCase);
            testCase++;
        }
        out.flush();
        out.close();
    }

    public static void solve(int testCase) {
         long d = sc.nextLong();
         int n = sc.nextInt();
         long x = sc.nextLong();
         long[] qi = new long[n];
         long[] li = new long[n];
         long[] vi = new long[n];

         for(int i = 0; i < n; i++){
           qi[i] = sc.nextLong();
           li[i] = d - sc.nextLong();
           vi[i] = sc.nextLong();
         }

         long[][] arr = new long[n][3];
         for(int i = 0; i < n; i++){
           arr[i][0] = qi[i];
           arr[i][1] = li[i];
           arr[i][2] = vi[i];
         }

         Arrays.sort(arr, (a, b) -> a[1] == b[1] ? (int)(b[2] - a[2]): (int)(a[1] - b[1]));
         PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> (int)(a[0] - b[0]));
         long total = 0L;

         long planted = 0;
         for(int i = 0; i < n; i++){
            long[] u = arr[i];
            long q = u[0], l = u[1],  m = u[2];
            pq.add(new long[]{m, q});
            planted += q;
            if(planted > x*l){
              // we need to remove some from planted
              long diff = planted - x*(long)l;
              while(pq.size() > 0 && diff > 0){
                long[] v = pq.poll();
                if(diff >= v[1]){
                    diff -= v[1];
                    planted -= v[1];
                }else{
                    v[1] -= diff;
                    planted -= diff;
                    diff = 0;
                    // money, seeds
                    pq.add(new long[]{v[2], v[1]});
                }
              }
            }
         }


         while(pq.size() > 0){
            long[] u = pq.poll();
            total += u[0]*(long)u[1];
         }

         out.println("Case #"+testCase+": "+total);
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