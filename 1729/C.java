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
        while (tt-- > 0) {
            solve();
        }
        out.flush();
        out.close();
    }

    public static void solve() {
        //  int n = sc.nextInt();
         char[] c = sc.nextLine().toCharArray();

         int n = c.length;
         char start = c[0];
         char end = c[n - 1];

        //  start = (char)Math.min(c[0], c[n - 1]);
        //  end = (char)Math.max(c[0], c[n - 1]);

         List<int[]> ll = new ArrayList<>();
         for(int i = 0; i < n; i++){
           ll.add(new int[]{c[i] - 'a', i});
         }

         ll.sort((a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

         if(start > end){
          ll.sort((a,b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
         }

        //  for(int[] i: arr){
        //   out.println(Arrays.toString(i));
        //  }

         int startIdx = -1, endIdx = -1;

         for(int i = 0; i < n; i++){
          if(ll.get(i)[0] == (start - 'a')){
            // searching for end
            startIdx = i;
            int ee = end - 'a';

            for(int j = i + 1; j < n; j++){
              if(ll.get(j)[0] == ee){
                for(int k = j; k < n; k++){
                  if(ll.get(k)[0] == ee){
                    endIdx = Math.max(endIdx, k);
                  }else break;
                }
                break;
              }
            }
            break;
         }
        }

        if(startIdx == -1 || endIdx == -1){
          out.println("Error");
          return;
        }
        int ee = end - 'a';
        int ss = start - 'a';
        // out.println(" start " + startIdx + " " + endIdx + "  "  + start + " " + end);
        out.println(Math.abs((ee - ss)) + " " + (endIdx - startIdx + 1));

        List<Integer> ans = new ArrayList<>();
        for(int i = startIdx; i <= endIdx; i++){
          ans.add((ll.get(i)[1]+1));
        }

        // if(c[0] > c[n - 1]){
        //   Collections.reverse(ans);
        // }

        for(int i: ans){
          out.print(i + " ");
        }
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