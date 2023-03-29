import java.io.*;
import java.util.*;

public class B {
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
       int n = sc.nextInt();
       if(n%2 == 0){
       	out.println(n/2 +  " " + n/2);
       }else{
       	int v = n/2;
       	int u = n - v;
       	
       	// u > v
       	// element humko v nikala hai
       	int sum1 = 0;
       	int sum2 = 0;
       	int vv = v;
       	int uu = u;
       	while(vv > 0){
       		sum1 += vv%10;
       		vv = vv/10;
       	}
       	
       	while(uu > 0){
       		sum2 += uu%10;
       		uu = uu/10;
       	}
       	
       	// out.println(" --> " + (sum1 - sum2) + " ----> " + v + " " + u);
       	if(sum1 - sum2 > 1){
       		int m = 1;
       		int t = v;
       		while(sum1 - sum2 > 1){
       			int temp =  t%10;
       			// System.out.println(" sum ->" + sum1 + " " + sum2 + " " + temp + " vv --> " + v + " " + u);
       			if(sum1 - sum2 > temp){
       				u += m*temp;
       				v -= m*temp;
       				sum1 -= temp;
       			    sum2 += temp;
       			}else{
       				int mid = temp/2;
       				u += m*mid;
       				v -= m*mid;
       				sum1 -= mid;
       		     	sum2 += mid;
       			}
       			// System.out.println(" --> " + sum1 + " " + sum2 + " " + m);
       			t = t/10;
       			m *= 10;
       		}
       	}
       	
       	if(u + v != n){
       		out.println(" something is wrong ");
       	}
       	out.println(u + " " + (v));
       }
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