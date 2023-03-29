import java.io.*;
import java.util.*;

public class C {
    public static void main(String[] args) throws FileNotFoundException {
        Solution s = new Solution();
        s.solver();
    }
}

class Pair{
	int start, len;
	Pair(){
		start = -1;
		len = 0;
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
    
    void solve(){
       int n = sc.nextInt(), m = sc.nextInt();
       int[] arr = sc.readArrayInt(n);
       int[][] ss = new int[m][2];
       for(int i = 0; i < m; i++){
        ss[i][0] = sc.nextInt() - 1;
        ss[i][1] = sc.nextInt();
       }
       
       // int MAXN = 2*100000 + 7;
       Pair[] store = new Pair[n + m + 7];
       for(int i = 0; i < store.length; i++){
        store[i] = new Pair();
       }
       
       HashSet<Integer> set = new HashSet<>();
       for(int i = 0; i < n; i++){
        int v = arr[i];
        store[v].start = -1;
        set.add(v);
       }
       
       for(int i = 0; i < m; i++){
        int idx = ss[i][0];
        int v = ss[i][1];
        int prev = arr[idx];
        int l = i - store[prev].start;
        store[prev].len += l;
        store[prev].start = -1;
        store[v].start = i;
        arr[idx] = v;
        set.add(v);
       }
       
       for(int i = 0; i < n; i++){
        int v = arr[i];
        store[v].len += m - store[v].start;
        store[v].start = -1;
       }
       
       long sum = 0;
       for(int i : set){
        int occur = store[i].len;
        // out.println(" i " + i + " " + occur);
        int noOccur = m+1 - occur;
        long t = occur*(long)noOccur;
        t += ((long)(occur)*(occur - 1))/2;
        sum += t;
       }
       
       out.println(sum);
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