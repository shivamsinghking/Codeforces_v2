import java.io.*;
import java.util.*;

public class Main {
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

    static ArrayList<ArrayList<Integer>> ans;
    static void dfs(int i, int parent, char[] arr, ArrayList<ArrayList<Integer>> ll, int[] parentList){
        int p = parentList[arr[i] - 'a'];
        parentList[arr[i] - 'a'] = i;
        
        if(p != -1){
            ans.get(p).add(i);
            ans.get(i).add(p);
        }
        
        for(int j: ll.get(i)){
            if(j == parent) continue;
            dfs(j, i, arr, ll, parentList);
        }
        parentList[arr[i] - 'a'] = p;
    }
    
    static int[] subtree;
    static int dfs1(int i, int parent, boolean[] visited){
        if(visited[i]) return 0;
        
        visited[i] = true;
        int sum = 1;
        for(int j: ans.get(i)){
            if(j == parent) continue;
            sum += dfs1(j, i, visited);
        }
        return subtree[i] = sum;
    }
    
    public static void solve() {
        int n = sc.nextInt();
        char[] arr = new char[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.next().toCharArray()[0];
        }
        
        ArrayList<ArrayList<Integer>> ll = new ArrayList<>();
        ans = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            ll.add(new ArrayList<>());
            ans.add(new ArrayList<>());
        }
            
        for(int i = 0; i < n-1; i++){
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            
            ll.get(u).add(v);
            ll.get(v).add(u);
        }
        
        int[] parentList = new int[26];
        Arrays.fill(parentList, -1);
        dfs(0, -1, arr, ll, parentList);
        boolean[] visited = new boolean[n+1];
        subtree = new int[n+1];
        for(int i = 0; i < n; i++){
            if(visited[i] == false){
                dfs1(i, -1, visited);
            }
        }
        
        
        for(int i = 0; i < n; i++){
            out.println(subtree[i]);
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