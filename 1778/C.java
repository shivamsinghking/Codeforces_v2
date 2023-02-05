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
       String a = sc.nextLine(), b = sc.nextLine();
       
       // imp: atmost 10 diff char in a
       // k <= 10
       
       TreeSet<Character> set = new TreeSet<>();
       for(int i = 0; i < n; i++){
       	  if(a.charAt(i) != b.charAt(i)) set.add(a.charAt(i));
       }
       
       int m = set.size();
       if(m <= k){
       	long ans = n*(long)(n+1)>>1;
       	out.println(ans);
       	return;
       }
       
       // give Unique code to 10 char
       int[] uniCode = new int[26];
       int idx = 0;
       // out.println(set);
       for(char i: set){
       	 uniCode[i - 'a'] = idx++;
       }
       
       // out.println(Arrays.toString(uniCode));
       long ans = 0L;
       for(int i = 0; i < (1 << m); i++){
       	 if(Integer.bitCount(i) == k){
       	 	// out.println(" -> " + Integer.toBinaryString(i));
       	 	boolean[] visited = new boolean[n];
       	 	for(int j = 0; j < n; j++){
       	 		if(a.charAt(j) == b.charAt(j) || (i&(1 << uniCode[a.charAt(j) - 'a'])) > 0) visited[j] = true;
       	 	}
       	 	
       	 	// out.println(Arrays.toString(visited));
       	 	int cnt = 0;
       	 	long val = 0;
       	 	for(int j = 0; j < n; j++){
       	 		if(visited[j]){
       	 			cnt++;
       	 		}else{
       	 			val += (cnt)*(long)(cnt + 1)>>1;
       	 			cnt = 0;
       	 		}
       	 	}
       	 	
       	 	val += (cnt)*(long)(cnt + 1)>>1;
       	 	ans = Math.max(ans, val);
       	 }
       }
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