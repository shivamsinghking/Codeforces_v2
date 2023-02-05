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
        init();
        while (tt-- > 0) {
            solve();
        }
        out.flush();
        out.close();
    }

    static int MAX = 100000 + 7;
    static ArrayList<Integer>[] factors;
    static void init(){
    	factors = new ArrayList[MAX+1];
    	for(int i = 0; i < MAX; i++) factors[i] = new ArrayList<>();
    	for(int i = 1; i < MAX; i++){	
    		for(int j = i; j < MAX; j += i){
    			factors[j].add(i);
    		}    		
    	}
    }
    
    public static void solve() {
        int n = sc.nextInt(), m = sc.nextInt();
        int[] arr = sc.readArrayInt(n);
        sort(arr);
        
        HashSet<Integer> set = new HashSet<>();
        for(int i: arr) set.add(i);
        	
        List<Integer> ll = new ArrayList<>(set);
        int N = ll.size();
        // out.println(ll);
        Collections.sort(ll);
        int l = 0, r = 0, cnt = 0, min = Integer.MAX_VALUE;
        int[] store = new int[MAX];
        while(r < N){
        	int v = ll.get(r);
        	for(int i: factors[v]){
        		if(i > m) break;
        		store[i]++;
        		if(store[i] == 1) cnt++;
        	}
        	
        	while(cnt == m && l <= r){
        		min = Math.min(min, ll.get(r) - ll.get(l));
        		for(int i: factors[ll.get(l)]){
        			if(i > m) break;
        			store[i]--;
        			if(store[i] == 0) cnt--;
        		}
        		l++;
        	}
        	
        	r++;
        }
        
        out.println(min == Integer.MAX_VALUE ? -1 : min);
        	
        
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