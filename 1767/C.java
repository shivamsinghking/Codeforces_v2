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
        // tt = sc.nextInt();
        while (tt-- > 0) {
            solve();
        }
        out.flush();
        out.close();
    }
    
    static boolean check(int[] arr, int i, int j, int value){
    	int ones = 0, zero = 0;
    	for(int k = i; k <= j; k++){
    		if(arr[k] == 0) zero++;
    		else ones++;
    	}
    	
    	if(value == 1){
    		if((ones == 0 && zero == (j  - i + 1)) || (zero == 0 && ones == (j - i + 1))) return true;
    		else return false;
    	}else if(value == 2){
    		if(ones > 0 && zero >0) return true;
    		else return false;
    	}else{
    		return true;
    	}
    }
    
    static long MOD = 998244353L;
    static HashMap<Integer,HashMap<String,Long>> map;
        static List<Integer>[] ll ;

    // static long helper(int i, int n, int[] arr, int ones, int zero){
    // 	if(i >= n){
    // 		// checking the array for n - 1 condition
    		
    // 		for(int j = 1; j < n; j++){
    // 			for(int k = 0; k < n - j; k++){
    // 				boolean ans = check(arr, j, j+k, ll[j].get(k));
    // 				if(!ans) return 0L;
    // 			}
    // 		}
    		
    // 		// out.println(" ans " + Arrays.toString(arr));
    // 		return 1L;
    // 	}
    // 	String s = String.valueOf(arr);
    // 	out.println(" ss --> " + s + " " + Arrays.toString(arr));
    // 	if(map.containsKey(s) && map.get(s).containsKey(i)) return map.get(s).get(i);
    	
    // 	long cnt = 0;
    // 	if(ll[0].get(i) == 1){
    // 		// either all zero or all one
    // 		if(zero == 0 && ones == i){
    // 			arr[i] = 1;
    // 			cnt += helper(i+1, n, arr, ones + 1, zero)%MOD;
    // 			arr[i] = 0;
    // 		}
    		
    // 		if(ones == 0 && zero == i){
    // 		  arr[i] = 0;
    // 		  cnt += helper(i+1, n, arr, ones, zero + 1)%MOD;
    // 		  arr[i] = 0;	
    // 		}
    		
    // 		if(zero > 0 && ones > 0) return 0L;
    // 	}else if(ll[0].get(i) == 2){
    		
    // 		if(zero == 0 && ones > 0){
    // 			arr[i] = 0;
    // 		    cnt += helper(i + 1, n, arr, ones, zero + 1)%MOD;
    // 		    arr[i] = 0;
    // 		}else if(ones == 0 && zero > 0){
    // 			arr[i] = 1;
    // 		    cnt += helper(i+1, n, arr, ones + 1, zero)%MOD;
    // 		    arr[i] = 0;
    // 		}else if(ones > 0 && zero > 0){
    // 			arr[i] = 0;
    // 		    cnt += helper(i + 1, n, arr, ones, zero + 1)%MOD;
    // 		    arr[i] = 0;
    // 		    arr[i] = 1;
    // 		    cnt += helper(i+1, n, arr, ones + 1, zero)%MOD;
    // 		    arr[i] = 0;
    // 		}
    // 	}else{
    // 		// it is 0;
    // 		arr[i] = 0;
    // 		cnt += helper(i + 1, n, arr, ones, zero + 1)%MOD;
    // 		arr[i] = 0;
    		
    // 		arr[i] = 1;
		  //   cnt += helper(i+1, n, arr, ones + 1, zero)%MOD;
		  //   arr[i] = 0;
		    
    // 	}
    	
    // 	// String s = String.valueOf(arr);
    // 	if(map.containsKey(s)){
    // 		HashMap<Integer,Long> m = map.get(s);
    // 		m.put(i, cnt%MOD);
    // 		map.put(s, m);
    // 	}else{
    // 		HashMap<Integer,Long> m = new HashMap<>();
    // 		m.put(i, cnt%MOD);
    // 		map.put(s, m);
    // 	}
    	
    // 	return cnt%MOD;
    // }
    
    
    static Long[][][] dp;
    static long helper(int idx, int lastOne, int lastZero, int n){
    	if(idx >= n) return 1L;
    	
    	 if(dp[idx][lastOne+1][lastZero+1] != null) return dp[idx][lastOne+1][lastZero+1];
    	 
    	 // System.out.println(" i " + idx + " " + lastOne + " " + lastZero);
    	 boolean one = true, zero = true;
    	 for(int i = 0; i <= idx; i++){
    	 	int v = ll[i].get(idx - i);
    	 	if(i == idx){
    	 		if(v == 2){
    	 			one = false;
    	 			zero = false;
    	 		}
    	 		continue;
    	 	}
    	 	
    	 	if(v == 1){
    	 		// i - idx all same
    	 		// lastOne or lastZero < i
    	 		if(lastOne == idx - 1){
    	 			// all 1
    	 			zero = false;
    	 			if(lastZero >= i){
    	 				one = false;
    	 			}
    	 		}
    	 		
    	 		if(lastZero == idx - 1){
    	 			// all 0
    	 			one = false;
    	 			if(lastOne >= i){
    	 				zero = false;
    	 			}
    	 		}
    	 		
    	 	}else if(v == 2){
    	 		// lastZero < i then one = false
    	 		// lastOne < i then zero = false
    	 		// if(Math.min(lastOne, lastZero) < i){
    	 		// 	if(lastZero < i){
    	 		// 		one = false;
    	 		// 	}
    	 			
    	 		// 	if(lastOne < i){
    	 		// 		zero = false;
    	 		// 	}
    	 		// }
    	 		if(lastZero < i){
    	 			one = false;
    	 		}
    	 		
    	 		if(lastOne < i){
    	 			zero = false;
    	 		}
    	 	}
    	 }
    	 
    	 long cnt = 0L;
    	 if(one){
    	 	cnt += helper(idx+1, idx, lastZero, n);
    	 } 
    	 
    	 cnt = cnt%MOD;
    	 if(zero){
    	 	cnt += helper(idx+1, lastOne, idx, n);
    	 }
    	 
    	 return dp[idx][lastOne+1][lastZero+1] = cnt%MOD;
    }
    
    public static void solve() {
        int n = sc.nextInt();
        ll = new ArrayList[n];
        
        for(int i = 0; i < n; i++) ll[i] = new ArrayList<>();
        	
        for(int i = n; i >= 1; i--){
        	for(int j = 0; j < i; j++){
        	  ll[n - i].add(sc.nextInt());	
        	}
        }
        
        dp = new Long[n+2][n+3][n+3];
        
        long ans = helper(0, -1, -1, n);
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

    // static long MOD = 1000000007;
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