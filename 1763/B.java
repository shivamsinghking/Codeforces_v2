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
        while (tt-- > 0) {
            solve();
        }
        out.flush();
        out.close();
    }

    static class Power implements Comparable<Power>{
    	int v;
    	int index;
    	Power(int index, int v){
    		this.index = index;
    		this.v = v;
    	}
    	
    	public int compareTo(Power o){
    		return this.v == o.v ? this.index - o.index : this.v - o.v;
    	}
    }
    
    public static void solve() {
        int n = sc.nextInt(), k = sc.nextInt();
        int[] h = sc.readArrayInt(n);
        int[] p = sc.readArrayInt(n);
                
        int[] arr = new int[n];
        List<Integer> ll = new ArrayList<>();
        	
        for(int i = 0; i < n; i++) ll.add(i);
        ll.sort((a, b) -> h[a] - h[b]);
         
        TreeMap<Integer,Integer> map = new TreeMap<>(); 
        for(int i = 0; i < n; i++) map.put(p[i], map.getOrDefault(p[i], 0) + 1);	
        int index = 0, sub = 0;
        while(k > 0 && index < n){
        	if(h[ll.get(index)] - sub <= k){
        		int v = ll.get(index);
        		map.put(p[v], map.get(p[v]) - 1);
        		if(map.get(p[v]) == 0) map.remove(p[v]);
        		index++;
        	}else{
        	   sub += k;
        	   // out.println(map);
        	   k -= map.firstEntry().getKey();
        	   // out.println(" k " + k);      	  
        	}
        }
        
        if(index >= n){
        	out.println("YES");
        }else{
        	out.println("NO");
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