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
        // tt = sc.nextInt();
        while (tt-- > 0) {
            solve();
        }
        out.flush();
        out.close();
    }

    public static void solve() {
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = sc.readArrayInt(n);
        
        int[] prefix = new int[n+1];
        int[] zero = new int[n+1];
        TreeMap<Integer,TreeSet<Integer>> even = new TreeMap<>();
        TreeMap<Integer,TreeSet<Integer>> odd = new TreeMap<>();
        for(int i = 0; i < n; i++){
        	prefix[i+1] = prefix[i]^arr[i];
        	if(i%2 == 0){
        		if(even.containsKey(prefix[i+1])){
        			TreeSet<Integer> ll = even.get(prefix[i+1]);
        			ll.add(i);
        			even.put(prefix[i+1], ll);
        		}else{
        			TreeSet<Integer> ll = new TreeSet<>();
        			ll.add(i);
        			even.put(prefix[i+1], ll);
        		}
        	}else{
			if(odd.containsKey(prefix[i+1])){
			        			TreeSet<Integer> ll = odd.get(prefix[i+1]);
			        			ll.add(i);
			        			odd.put(prefix[i+1], ll);
			        		}else{
			        			TreeSet<Integer> ll = new TreeSet<>();
			        			ll.add(i);
			        			odd.put(prefix[i+1], ll);
			        		}        	  	
        	}
        	
        	
        	if(arr[i] == 0){
        		zero[i+1] = zero[i] + 1;
        	}else{
        		zero[i+1] = zero[i];
        	}
        }
        
        for(int i= 0; i < q; i++){
        	int l = sc.nextInt() - 1, r = sc.nextInt() - 1;
        	int len = r - l + 1;
        	
        	int xor = prefix[r+1]^prefix[l];
        	if(zero[r+1] - zero[l] == len){
        		   out.println(0);
        		   continue;	
            }
        	
        	if(xor != 0){
        		out.println(-1);
        		continue;
        	}	
        	
        	if(len%2 != 0){
        		if(xor == 0){
        			out.println(1);
        			continue;
        		}
        	}else{
        		if(arr[l] == 0 || arr[r] == 0){
        			out.println(1);
        			continue;
        		}
        		
        		if(l%2 == 0){
        			if(even.containsKey(prefix[l]) == false){
        				out.println(-1);
        				continue;
        			}
        			
        		    TreeSet<Integer> set = even.get(prefix[l]);
        		    if(set.ceiling(l) != null && set.ceiling(l) < r){
        		    	out.println(2);
        		    }else{
        		    	out.println(-1);
        		    }
        		}else{
        			if(odd.containsKey(prefix[l]) == false){
        				out.println(-1);
        				continue;
        			}
        			
        			TreeSet<Integer> set = odd.get(prefix[l]);
        			if(set.ceiling(l) != null && set.ceiling(l) < r){
        				out.println(2);
        			}else{
        				out.println(-1);
        			}
        		}
        	}
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