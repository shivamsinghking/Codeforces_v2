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
    
     int LongestCommonSubstr(String X, String Y) {
        
        int m=X.length();
        int n=Y.length();
        int dp[][] = new int[m + 1][n + 1];
        
        // initialization
        for(int i=0;i<=m;i++)
            dp[i][0]=0;   // Eg LCS of "abc" & "" = 0
        for(int j=0;j<=n;j++)
            dp[0][j]=0;   // Eg LCS of "" & "abc" = 0
        
        for(int i=1;i<=m;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(X.charAt(i - 1)==Y.charAt(j-1))
                    dp[i][j]=1+dp[i-1][j-1];
                else dp[i][j]=0;                   // ONLY THIS CHANGE
            }
        }
    	 int maxLen=0;              // Now finding the max element
         for(int i=1;i<=m;i++)
        {
            for(int j=1;j<=n;j++)
               maxLen=Math.max(maxLen,dp[i][j]);
        }
        return maxLen;
		}
    void solve(){
       String a = sc.nextLine();
       String b = sc.nextLine();
       
         
       if(a.charAt(0) == b.charAt(0)){
       	out.println("YES");
       	out.println(a.charAt(0) + "*");
       	return;
       }
       
       if(a.charAt(a.length() - 1) == b.charAt(b.length() - 1)){
       	out.println("YES");
       	out.println("*"  + a.charAt(a.length() - 1));
       	return;
       }
       
       if(a.length() < b.length()){
       	String temp = a;
       	a = b;
       	b = temp;
       }
       
       for(int i = 0; i < a.length(); i++){
       	int end = i + 2;
       	if(end > a.length()) break;
       	String sub = a.substring(i, end);
       	// out.println(" --> " + sub + " " + i); 
       	if(b.indexOf(sub) != -1){
       		out.println("YES");
       		out.println("*" + sub + "*");
       		return;
       	}
       }
       
       out.println("NO");
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