import java.io.*;
import java.util.*;

public class Main {
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
        int[] arr = sc.readArrayInt(n);

        Stack<Integer> st = new Stack<>();

        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            while (st.size() > 0 && arr[st.peek()] < arr[i]) {
                right[st.pop()] = i;
            }
            st.push(i);
        }

        while (st.size() > 0) {
            right[st.pop()] = n;
        }

        int[] left = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (st.size() > 0 && arr[st.peek()] < arr[i]) {
                left[st.pop()] = i;
            }
            st.push(i);
        }

        while (st.size() > 0) {
            left[st.pop()] = -1;
        }

        int[] ans = new int[n];

        int[] rr = new int[n];
        int[] ll = new int[n];

        for(int i = n - 1; i >= 0; i--){
            int nxtG = right[i];
            if(nxtG < n){
                rr[i] = rr[nxtG] + 1;
            }   
        }

        for(int i = 0; i < n; i++){
            int nxtG = left[i];
            if(nxtG >= 0){
                ll[i] = ll[nxtG] + 1;
            }
        }

        // For each element, we have to find the number of elements to the left and right of it which are greater than it.
        for(int i = 0; i < n; i++){
            if(i - 1 >= 0){
                ans[i]++;
                ans[i] += ll[i-1];
            }

            if(i+1 < n){
                ans[i]++;
                ans[i] += rr[i+1];
            }
        }
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

    static void reverseSort(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        Collections.sort(list, Collections.reverseOrder());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
    }

    static void sort(int[] a) {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i : a)
            l.add(i);
        Collections.sort(l);
        for (int i = 0; i < a.length; i++)
            a[i] = l.get(i);
    }

    static void sort(long[] a) {
        ArrayList<Long> l = new ArrayList<>();
        for (long i : a)
            l.add(i);
        Collections.sort(l);
        for (int i = 0; i < a.length; i++)
            a[i] = l.get(i);
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

        public int[] readArrayInt(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }

        public long[] readArrayLong(int n) {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextLong();
            }
            return arr;
        }
    }
}