import java.io.*;
import java.util.*;

public class F {
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
         char[] c = sc.nextLine().toCharArray();
         int n = c.length;
         int w = sc.nextInt(), m = sc.nextInt();

         // Since divisible by 9 is sum should be divisible by 9, we will maintain a prefix sum of it for 
         // finding remainder

         int[] prefix = new int[n+1];

         for(int i = 0; i < n; i++){
            prefix[i+1] = prefix[i] + (c[i] -  '0');
         }

         HashMap<Integer,TreeSet<Integer>> map = new HashMap<>();
         for(int i = 0; i <= n - w; i++){
            int sum = prefix[i+w] - prefix[i];
            int rem = sum%9;
            
            if(map.containsKey(rem)){
                TreeSet<Integer> l = map.get(rem);
                l.add(i);
                map.put(rem, l);
            }else{
                TreeSet<Integer> l = new TreeSet<>();
                l.add(i);
                map.put(rem, l);
            }
         }

         while(m-- > 0){
            int li = sc.nextInt() - 1;
            int ri = sc.nextInt() - 1;
            int k = sc.nextInt();
            int sum = prefix[ri+1] - prefix[li];
            int rem = sum%9;
            
            int L = Integer.MAX_VALUE, R = Integer.MAX_VALUE;
            // we will go through all possible ans and store min L and then min R
            for(int i = 0; i < 9; i++){
               if(map.containsKey(i)){
                int nxtRem = k - ((i*rem)%9) + 9;
                nxtRem = nxtRem%9;
                TreeSet<Integer> set = map.get(i);
                int x = set.pollFirst();
                map.put(i, set);
                
                TreeSet<Integer> nxtSet = map.getOrDefault(nxtRem, new TreeSet<>());
                if(nxtSet.size() > 0){
                    int r = nxtSet.first();
                    if(x < L){
                        L = x;
                        R = r;
                    }else if(x == L){
                        if(r < R){
                            R = r;
                        }
                    }
                }
                set.add(x);
                map.put(i, set);
               }
            }

            if(L == Integer.MAX_VALUE || R == Integer.MAX_VALUE || (L == R)){
                out.println(-1 + " " + -1);
            }else{
                out.println((L+1)  + " " + (R+1));
            }
         }
         return;

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