import java.io.*;
import java.util.*;

public class Main1 {
    public static void main(String[] args) throws FileNotFoundException {
        Solution s = new Solution();
        s.solver();
    }
}

class Solution {
    PrintWriter out;
    Kioken sc;
    boolean checkOnlineJudge = System.getProperty("ONLINE_JUDGE") == null;
    void solver() throws FileNotFoundException{
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
    
    static long solve(int N, int M, int[] A, int[][] E){
       Set<Integer> set = new HashSet<>();
       for(int i = 1; i <= N; i++) set.add(i);
       
       int[][] arr = new int[N + 1][2];
       arr[0][0] = (int)1e7 + 5;
       arr[0][1] = (int)1e7 + 5;
       
       for(int i = 1; i <= N; i++){
        arr[i][0] = A[i];
        arr[i][1] = i;
       }    
       
       Arrays.sort(arr, (a, b) -> a[0] - b[0]);
       
       List<Set<Integer>> ll = new ArrayList<>();
              List<Set<Integer>> l1 = new ArrayList<>();
       for(int i = 0; i <= N + 1; i++){
        ll.add(new HashSet<>());
        l1.add(new HashSet<>());
     }
       
       for(int i = 0; i < M; i++){
        int u = E[i][0];
        int v = E[i][1];
        ll.get(u).add(v);
        ll.get(v).add(u);
       }
       
       int max = (int)1e7 + 5;
       long ans = 0L, prev = max;
       
       for(int i = 0; i < N; i++){
         if(set.size() == 0) break;
         int node = arr[i][1];
         int val = arr[i][0];
         // System.out.println(" node " + node + " " + val + " " + ans);
         HashSet<Integer> newSet = new HashSet<>();
         long sum = 0;
         // if(prev != max){
         //    if(set.contains(node)){
         //        sum += prev*val;
         //    }
         // }
        
         // set.remove(node);
         for(int j: set){
           if(j == node) continue;
           if(ll.get(j).size() > 0 && ll.get(j).contains(node)){
             continue;
           }else{
             sum += (l1.get(j).contains(node)) ? 0 : A[j];
             l1.get(node).add(j);
             System.out.println(l1.get(j) + " j " + j + " " + node);
             // prev = Math.min(prev, A[j]);
             newSet.add(j);
           }
         }
         
         System.out.println(newSet + " -- " + sum*val + " " + set);
         for(int j: newSet) set.remove(j);
         ans += sum*(long)val;
       }
       
       return (set.size() == 0) ? ans : -1;
    }
    
    void solve(){
       int n = sc.nextInt();
       int m = sc.nextInt();
       int[] arr = new int[n + 1];
       for(int i = 1; i <= n; i++) arr[i] = sc.nextInt();
       
       int[][] brr = new int[m][2];
       for(int i = 0; i < m; i++){
        int u = sc.nextInt();
        int v = sc.nextInt();
        brr[i][0] = u;
        brr[i][1] = v;
       }
       
       long ans = solve(n, m, arr, brr);
       out.println(ans);
    }
    
    long gcd(long a, long b) {
        while (b != 0) {
            long rem = a % b;
            a = b;
            b = rem;
        }
        return a;
    }

    long MOD = 1000000007;
    void reverseSort(int[] arr){List<Integer> list = new ArrayList<>();for (int i=0; i<arr.length; i++){list.add(arr[i]);}Collections.sort(list, Collections.reverseOrder());for (int i = 0; i < arr.length; i++){arr[i] = list.get(i);}}
    void sort(int[] a) {
        ArrayList<Integer> l=new ArrayList<>();
        for (int i:a) l.add(i);
        Collections.sort(l);
        for (int i=0; i<a.length; i++) a[i]=l.get(i);
    }
    void sort(long[] a){
        ArrayList<Long> l=new ArrayList<>();
        for (long i:a) l.add(i);
        Collections.sort(l);
        for (int i=0; i<a.length; i++) a[i]=l.get(i);
    }
}
class Kioken {
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