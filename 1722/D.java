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
        tt = sc.nextInt();
        while (tt-- > 0) {
            solve();
        }
        out.flush();
        out.close();
    }

    public static void solve() {
         int n = sc.nextInt();
         String s = sc.nextLine();

         int[] arr = new int[n];
         for(int i = 0; i < n; i++){
            if(s.charAt(i) == 'L'){
              arr[i] = i;
            }else{
              arr[i] = n - 1 - i;
            }
          }

          long[] prefix = new long[n+1];
          for(int i = 0; i < n; i++){
            prefix[i+1] = prefix[i] + arr[i];
          }
 
          long totalSum = prefix[n];
          List<Long> values = new ArrayList<>();
          int i = 0, e = n - 1;
          while(i <= e){
            // out.println(" tt "  + totalSum);
            long sum1 = totalSum;
            long sum2 = totalSum;

            
             while(i <= e){
              if(s.charAt(i) == 'L' && i > n - 1 - i){
                i++;
              }else if(s.charAt(i) == 'R' && n - 1 - i > i){
                i++;
              }else break;
             }

             while(i <= e){
              if(s.charAt(e) == 'L' && e > n - 1 - e){
                e--;
              }else if(s.charAt(e) == 'R' && n - 1 - e > e){
                e--;
              }else break;
             }

            //  out.println(e + " " + i +  totalSum + " " );
             if(i <= e){
              sum1 = sum1 - arr[i] + (Math.max(i, n - 1 - i));
              sum2 = sum2 - arr[e] + Math.max(e, n - 1 - e);
              if(sum1 > sum2){
                i++;
                values.add(sum1);
                totalSum = sum1;
              }else{
                e--;
                values.add(sum2);
                totalSum = sum2;
              }
             }
          }
          
          // out.println(values);
          for(int k = 0; k < n; k++){
             if(k < values.size()){
              out.print(values.get(k) + " ");
             }else{
              if(values.size() > 0){
                out.print(values.get(values.size() - 1) + " ");
              }else{
                out.print(prefix[n] + " ");
              }
             }
           }
          // out.println(values);
          out.println();
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