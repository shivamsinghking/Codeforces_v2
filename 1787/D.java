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

    static ArrayList<ArrayList<Integer>> ll;
    static int n;
    static int invalid;
    static int linear;
    static void dfs(int i, int nodes, boolean[] visited){
    	if(visited[i]) return;
    	visited[i] = true;
    	if(i >= n){
    		
    		linear += nodes - 1;
    		// invalid += (nodes*(nodes - 1))>>1;
    		return;
    	} 
    	
    	
    	for(int j : ll.get(i)){
    		dfs(j, nodes + 1, visited);
    	}
    }
    
    public static void solve() {
        n = sc.nextInt();
        int[] arr = sc.readArrayInt(n);
        
        ll = new ArrayList<>();
        invalid = 0;
        linear = 0;
        for(int i = 0; i <= n+1; i++){
        	ll.add(new ArrayList<>());
        }
        
        for(int i = 0; i < n; i++){
        	int u = i;
        	int v = i + arr[i];
        	v = (v >= n || v < 0) ? n : v;
        	out.println(" vv =-> " + v + " " + u);
        	ll.get(u).add(v);
        }
        
        boolean[] visited = new boolean[n + 1];
        boolean is_cycle = true;
        int first_liner_length = 0;
        int visitedCnt = 0;
        for(int i = 0; i < n; i++){
        	if(!visited[i]){
        		dfs(i, 1, visited);
        		if(i == 0){
        			if(visited[n]){
        				is_cycle = false;
        				first_liner_length = linear;
        			}else{
        				for(int j = 0; j < n; j++){
        					if(visited[j]) visitedCnt++;
        				}
        			}
        		}
        	}
        }
        
        long total = (long)n*(2*n + 1);
        int circular = n - linear;
        out.println(" is_cycle " + is_cycle + " " + first_liner_length  + " " + circular);
        if(!is_cycle){
        	total -= first_liner_length*(long)(first_liner_length + 1)>>1;
        	total -= first_liner_length*(long)circular;
        	out.println(total);
        	return;
        }
        
        long ans = visitedCnt*linear;
        out.println(ans);
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