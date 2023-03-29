import java.io.*;
import java.util.*;

public class C {
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
    
    void solve(){
       int n = sc.nextInt(), k = sc.nextInt();
       
       
       if(k == 1){
       	out.print(1 + " ");
       	for(int i = 1; i < n; i++){
       		out.print(-1*(i + 1) + " ");
       	}
       	out.println();
       	return;
       }
       
       if(k == 2){
       	out.print(2 + " ");
       	for(int i = 1; i < n; i++){
       		out.print(-i + " ");
       	}
       	out.println();
       	return;
       }
       
       int l = 0, r = n;
       int ans = -1;
       while(l <= r){
       	int mid = l+r>>1;
       	if((mid*(mid + 1))/2 <= k){
       		ans = mid;
       		l = mid + 1;
       	}else r = mid - 1;
       }
       
       
       int req = k - ((ans)*(ans + 1))/2;
       
       // out.println(" req "+ req + " " + ans);
       int m = ans - req;
       int val = (m*(m + 1))/2;
       val++;
       // out.println(" m " + m);
       int[] store = new int[n + 2];
       for(int i = ans; i >= 1; i--) store[ans - i + 1] = i;
       
       if(m == 0){
       	store[ans] = 2;
       }
       
       store[ans + 1] = -1*val;
       
       int sum = (ans*(ans + 1))/2;
       int nxtVal = sum + 1;
       for(int i = ans + 2; i <= n; i++){
       	store[i] = -1*nxtVal;
       	nxtVal++;
       }
       
       for(int i = 1; i <= n; i++) out.print(store[i] +  " ");
       out.println();
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