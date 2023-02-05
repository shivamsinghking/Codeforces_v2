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
         int n = sc.nextInt();
         StringBuilder st = new StringBuilder(sc.nextLine());

         int ones1 = 0;
         int zero1 = 0;
         for(char i: st.toString().toCharArray()){
          if(i == '1') ones1++;
          else zero1++;
         }

         if(ones1 == 0 || zero1 == 0){
          out.println(st.length() + " " + 0);
          return;
         }

         List<int[]> ll = new ArrayList<>();
         while(st.length() > 1){
          // out.println(" newS " + st);
          int len = st.length();
          int ones = 0, zero = 0;
          for(char i: st.toString().toCharArray()){
            if(i == '1') ones++;
            else zero++;
          }

          // finding max subarray
          char[] c = st.toString().toCharArray();
          // System.out.println("===> " + Arrays.toString(c));

          int[] arr = maxLen(c, c.length);
          // System.out.println(" --> ");
          StringBuilder newS = new StringBuilder();
          
          int o = 0, z = 0;
          int l = arr[0], r = arr[1]+1;
          for(int i = l; i < r; i++){
              if(c[i] == '1') o++;
              else z++;
          }

          newS.append(st.substring(0, l));
          if(ones - o < zero - z){
            // 1 is less
            newS.append('1');
            ll.add(new int[]{l, r-1, 1});
          }else{
            // 0 is less
            newS.append('0');
            ll.add(new int[]{l, r-1, 0});
          }
          newS.append(st.substring(r, len));
          st = newS;
          }
         

         out.println(1 + " " + ll.size());
         for(int[] i: ll){
          out.println((i[0]+1) + " " + (i[1] + 1) + " " + (i[2]));
         }

         return;
    }

    static int[] maxLen(char arr1[], int n)
    {
      int[] arr = new int[n];
       for(int i = 0; i < n; i++){
        arr[i] = (arr1[i] == '1') ? 1 : 0;
       }

        // Creates an empty hashMap hM
 
        HashMap<Integer, Integer> hM
            = new HashMap<Integer, Integer>();
 
        // Initialize sum of elements
        int sum = 0;
 
        // Initialize result
        int max_len = 0;
        int ending_index = -1;
        int start_index = 0;
 
        for (int i = 0; i < n; i++) {
            arr[i] = (arr[i] == 0) ? -1 : 1;
        }
 
        // Traverse through the given array
 
        for (int i = 0; i < n; i++) {
            // Add current element to sum
 
            sum += arr[i];
 
            // To handle sum=0 at last index
 
            if (sum == 0) {
                max_len = i + 1;
                ending_index = i;
            }
 
            // If this sum is seen before,
            // then update max_len if required
            if (hM.containsKey(sum)) {
                if (max_len < i - hM.get(sum)) {
                    max_len = i - hM.get(sum);
                    ending_index = i;
                }
            }
            else // Else put this sum in hash table
                hM.put(sum, i);
        }
 
        for (int i = 0; i < n; i++) {
            arr[i] = (arr[i] == -1) ? 0 : 1;
        }
 
        int end = ending_index - max_len + 1;
        return new int[]{end, ending_index};
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