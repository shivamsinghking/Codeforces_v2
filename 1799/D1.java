	import java.io.*;
	import java.util.*;

	public class D1 {
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
	    
	    // you don't need two variable cpu1, and cpu2, because either in cpu1 or cpu2, your (i - 1)-th will be there, so you only have to track 1 cpu least recent one
	    int max = (int)1e7 + 5;
	    int[] arr;
	    int[] hot, cold;
	    Long[][] dp;
	    long helper(int i, int cpu, int n){
	    	if(i >= n) return 0L;
	    	
	    	if(dp[i][cpu] != null) return dp[i][cpu];
	    	
	    	if(i - 1 < 0){
	    		return dp[i][cpu] = (long)cold[arr[i]] + helper(i + 1, cpu, n);
	    	}
	    	
	    	if(arr[i] == arr[i - 1]){
	    		return dp[i][cpu] = (long)hot[arr[i]] + helper(i + 1, cpu, n);
	    	}
	    	
	    	if(arr[i] == cpu){
	    		return dp[i][cpu] = (long)hot[arr[i]] + helper(i + 1, arr[i - 1], n);
	    	}
	    	
	    	long a = (long)cold[arr[i]] + helper(i + 1, cpu, n);
	    	long b = (long)cold[arr[i]] + helper(i + 1, arr[i - 1], n);
	    	return dp[i][cpu] = Math.min(a, b);
	    }
	    
	    void solve(){
	       int n = sc.nextInt(), k = sc.nextInt();
	       arr = sc.readArrayInt(n);
	       cold = sc.readArrayInt(k);
	       hot = sc.readArrayInt(k);
	       for(int i = 0; i < n; i++) arr[i]--;
	       
	       dp = new Long[n + 1][k + 2];
	       long ans = helper(0, k + 1, n);
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