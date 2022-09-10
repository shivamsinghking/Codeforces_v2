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

    /**
     * 1. remove same values
     * 2. If a value is not common and len > 1, it is on no use, to convert it to it's len and store it for both arrays
     * 3. remove same values
     * 4. both arrays must be same right now, if not "some Error"
     * 5. print ans
     */
    public static void solve() {
         int n = sc.nextInt();
         int[] arr = sc.readArrayInt(n);
         int[] brr = sc.readArrayInt(n);

         HashMap<Integer,Integer> map1 = new HashMap<>();
         for(int i = 0; i < n; i++){
          map1.put(arr[i], map1.getOrDefault(arr[i], 0) + 1);
         }

         long cnt = 0L;
         List<Integer> _brr = new ArrayList<>();
         List<Integer> _arr = new ArrayList<>();

         for(int i = 0; i < n; i++){
            if(map1.containsKey(brr[i])){
              map1.put(brr[i], map1.getOrDefault(brr[i], 0) - 1);
              if(map1.get(brr[i]) == 0) map1.remove(brr[i]);
            }else{
               int len = String.valueOf(brr[i]).length();
               if(len > 1){
                  cnt++;
                 _brr.add(len);
               }else{
                _brr.add(brr[i]);
               }
            }
          }

          for(int i: map1.keySet()){
            int v = map1.get(i);
            for(int j = 0; j < v; j++){
                int len = String.valueOf(i).length();
                if(len > 1){
                     cnt++;
                    _arr.add(len);
                }else{
                    _arr.add(i);
                }
            }
          }

          // again checking for same value
          map1.clear();
          for(int i = 0; i < _arr.size(); i++){
            map1.put(_arr.get(i), map1.getOrDefault(_arr.get(i), 0) + 1);
          }

          List<Integer> _brr2 = new ArrayList<>();
          for(int i = 0; i < _brr.size(); i++){
            int v = _brr.get(i);
            if(map1.containsKey(v)){
               map1.put(v, map1.getOrDefault(v, 0) - 1);
               if(map1.get(v)  == 0) map1.remove(v);
            }else{
               _brr2.add(v);
            }
          }

          List<Integer> _arr2 = new ArrayList<>();
          for(int i: map1.keySet()){
            int v = map1.get(i);
            for(int j = 0; j < v; j++){
                _arr2.add(i);
            }
          }


          if(_arr2.size() != _brr2.size()){
            out.println(" Error " + _arr2.size() + " " + _brr2.size());
          }

        //   out.println(_arr2 + " " + _brr2);
          cnt += _arr2.size() + _brr2.size();

          int ones = 0;
          for(int i: _arr2){
            if(i == 1) ones++;
          }

          for(int i: _brr2){
            if(i == 1) ones++;
          }
          out.println((cnt - ones));
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