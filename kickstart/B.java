import java.io.*;
import java.util.*;

public class B {
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
        int testCase = 1;
        while (tt-- > 0) {
            solve(testCase);
            testCase++;
        }
        out.flush();
        out.close();
    }

    static TreeMap<Integer,Integer> map;
    static int[] levelCotainer;

    static void dfs(int i, int parent, ArrayList<ArrayList<Integer>> ll, int level){
      map.put(level, map.getOrDefault(level, 0) + 1);
      levelCotainer[i] = level;
      for(int j: ll.get(i)){
        if(j == parent) continue;
        dfs(j, i, ll, level + 1);
      }
    }

    public static void solve(int t) {
        //  map = new TreeMap<>();
        //  map.put(1, 1);
        //  map.put(0, 2);
        //  out.println(map.firstKey());
         int n = sc.nextInt();
         int q = sc.nextInt();

         levelCotainer = new int[n+3];
         map = new TreeMap<>();

         ArrayList<ArrayList<Integer>> ll = new ArrayList<>();
         for(int i = 0; i <= n+2; i++) ll.add(new ArrayList<>());

         for(int i = 0; i < n - 1; i++){
           int u = sc.nextInt(), v = sc.nextInt();
           ll.get(u).add(v);
           ll.get(v).add(u);
         }


         int[] requiredWater = new int[n+3];
         Arrays.fill(requiredWater, -1);
         dfs(1, -1, ll, 0);
         for(int i: map.keySet()){
          requiredWater[i] = map.get(i);
         }

          // root is 1
          int[] filled = new int[n+3];
          // 0 -> empty, 1 filled, -1 half filled 
          for(int i = 0; i < q; i++){
            int container = sc.nextInt();
            int level = levelCotainer[container];
            int leftLevel = map.firstKey();
            // out.println(" level " + leftLevel + " " + requiredWater[leftLevel]);
            int needWater = requiredWater[leftLevel];
            filled[leftLevel]++;
            if(filled[leftLevel] == needWater){
              map.remove(leftLevel);
            }
          }
          // out.println(Arrays.toString(filled) + " " + Arrays.toString(requiredWater));

          int cnt = 0;
          for(int i = 0; i < n+3; i++){
             if(requiredWater[i] != -1 && filled[i] == requiredWater[i]) cnt += requiredWater[i];
          }

          printKickStart(t, cnt);


    }

    static void printKickStart(int t, int val){
      out.println("Case #"+t+": " + val);
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