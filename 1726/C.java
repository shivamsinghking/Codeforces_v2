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
        tt = sc.nextInt();
        while (tt-- > 0) {
            solve();
        }
        out.flush();
        out.close();
    }

    static class Pair{
      char i;
      int idx;
      Pair(char i, int idx){
        this.i = i;
        this.idx = idx;
      }
    }

    static void dfs(int i, ArrayList<ArrayList<Integer>> arr, boolean[] visited){
         visited[i] = true;
         for(int j: arr.get(i)){
           if(!visited[j]){
            dfs(j, arr, visited);
           }
         }
    }
    public static void solve() {
         int n = sc.nextInt();
         char[] c = sc.nextLine().toCharArray();

         ArrayList<ArrayList<Integer>> arr = new ArrayList<>();

         for(int i = 0; i < 2*n; i++){
          arr.add(new ArrayList<>());
         }

         Stack<Pair> st = new Stack<>();

        

         int prev = -1;
         int m = c.length;

         for(int i = 0; i < m; i++){
             if(c[i] == '('){
              st.push(new Pair(c[i], i));
             }else{
              while(st.size() > 0 && st.peek().i == '('){
                 Pair p = st.pop();
                 arr.get(p.idx).add(i);
                 arr.get(i).add(p.idx);
                 if(st.size() == 0){
                  if(prev != -1){
                    arr.get(prev).add(i);
                    arr.get(i).add(prev);
                    prev = p.idx;
                  }else{
                    prev = p.idx;
                  }
                 }
              }
             }
         }

        //  out.println(st.size() + " --< ");
         if(st.size() == 0 && prev == -1){
          arr.get(0).add(n - 1);
          arr.get(n - 1).add(0);
         }

        //  while(st.size() > 0){

        //  }


        //  for(int i = 0; i < m; i++){
        //     out.println(i + " " + arr.get(i));
        //  }

         boolean[] visited = new boolean[m];
         int cnt = 0;
         for(int i = 0; i < m; i++){
           if(visited[i] == false){
             dfs(i, arr, visited);
             cnt++;
           }
         }

         out.println(cnt);
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