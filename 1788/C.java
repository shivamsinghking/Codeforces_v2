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
       int n = sc.nextInt();
       
       int mid = (n + 1)/2;
       boolean[] visited = new boolean[2*n + 1];
       List<Integer> ans = new ArrayList<>();
       List<int[]> ll = new ArrayList<>(); 
       int temp = 1;
       for(int i = 2*n; i > n; i -= 2){
       	 // out.println(i + " " + temp);
       	 visited[i] = true;
       	 visited[temp] = true;
       	 ans.add(i + temp);
       	 ll.add(new int[]{i, temp});
       	 temp++;
       }
       
       List<Integer> v = new ArrayList<>();
       for(int i = 1; i <= 2*n; i++) if(visited[i] == false) v.add(i);
       Collections.sort(v);
       
       // out.println(v + " - v ");
       int l = 0, r = v.size() - 1;
       while(l <= r){
       	// out.println(l.get(l) + " " + l.get(r));
       	int vv = v.get(l) + v.get(r);
       	ans.add(vv);
        ll.add(new int[]{v.get(l), v.get(r)});
       	l++;
       	r--;
       }
       
       Collections.sort(ans);
       // out.println(ans);
       for(int i = 1; i < ans.size(); i++){
       	 if(ans.get(i) - ans.get(i - 1) != 1){
       	 	out.println("NO");
       	 	return;
       	 }
       }
       
       // out.println(" n " + n + " " + ans);
       out.println("YES");
       for(int[] i: ll) out.println(i[0] + " " + i[1]);
       
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