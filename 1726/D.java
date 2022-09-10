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

    static ArrayList<ArrayList<Integer>> arr;
    static ArrayList<ArrayList<Integer>> red;
    static ArrayList<ArrayList<Integer>> blue;
    
    static void dfs(int i, boolean[] visited){
      visited[i] = true;
      for(int j: arr.get(i)){
        if(visited[j] == false){
          red.get(i).add(j);
          red.get(j).add(i);
          dfs(j, visited);
        }
      }
    }

    static void dfs1(int i, boolean[] visited){
      visited[i] = true;

      ArrayList<Integer> r = red.get(i);
      for(int j: arr.get(i)){
         if(r.contains(j) == false && visited[j] == false){
          blue.get(i).add(j);
          blue.get(j).add(i);
          dfs1(j, visited);
         }
      }
    }

    static void dfs2(int i, boolean[] visited){
      visited[i] = true;
      for(int j: blue.get(i)){
        if(visited[j] == false){
          dfs2(j, visited);
        }
      }
    }

    public static void solve() {
         int n = sc.nextInt(), m = sc.nextInt();
         
         arr = new ArrayList<>();
         red = new ArrayList<>();
         blue = new ArrayList<>();

         for(int i = 0; i <= n; i++){
          arr.add(new ArrayList<>());
          red.add(new ArrayList<>());
          blue.add(new ArrayList<>());
         }

         int[][] edges = new int[m][2];
         for(int i = 0; i < m; i++){
          int u = sc.nextInt(), v = sc.nextInt();
          edges[i][0] = u;
          edges[i][1] = v;
          arr.get(u).add(v);
          arr.get(v).add(u);
         }

         boolean[] visited = new boolean[n+1];
         dfs(1, visited);
 
         boolean[] visited1 = new boolean[n+1];
         for(int i = 1; i <= n; i++){
          if(visited1[i] == false){
            dfs1(i, visited1);
          }
         }

         int cnt = 0;
         boolean[] visited2 = new boolean[n+1];
         for(int i = 1; i <= n; i++){
          if(visited2[i] == false){
            dfs2(i, visited2);
            cnt++;
          }
         }

         out.println(" cnt " + (1+cnt));
        //  for(int i = 1; i <= n; i++){
        //     out.println(" i " + i + " " + blue.get(i));
        //   }
        //   out.println(" --> ");
        //   for(int i = 1; i <= n; i++){
        //     out.println(" i " + i + " " + red.get(i));
        //   }

        for(int i = 0; i < m; i++){
          int u = edges[i][0], v = edges[i][1];
          if(red.get(u).contains(v)){
            out.print("1");
          }else{
            out.print("0");
          }
        }
         out.println();
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