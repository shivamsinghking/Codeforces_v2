import java.io.*;
import java.util.*;

public class E {
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
        long n = sc.nextLong();
        
        // out.println(a);
        long xor = 2*n;
        int[] store = new int[33];
        int[] a = new int[33];
        int[] b = new int[33];
        int carry = 0;
        boolean flag = true;
        for(int i = 0; i < 33; i++){
        	if((xor&(1 << i)) != 0){
        		store[i]++;
        	}
        }
        
        int[] store1 = new int[33];
        for(int i = 0; i < 33; i++){
        	if((n&(1 << i)) != 0){
        		store1[i]++;
        	}
        }
        
        for(int i = 0; i < 33; i++){
          if(store[i] > 0 && store1[i] > 0){
          	 if(carry > 0){
          	 	flag = false;
          	 	break;
          	 }
          	 a[i] = 0;
          	 b[i] = 1;
          }else if(store[i] > 0 && store1[i] == 0){
          	 if(carry > 0){
          	 	a[i] = 0;
          	 	b[i] = 0;
          	 	carry = 0;
          	 }else{
          	 	if(i - 1 >= 0 && a[i - 1] == 0 && b[i - 1] == 0){
          	 		a[i - 1] = 1;
          	 		b[i - 1] = 1;
          	 		a[i] = 0;
          	 		b[i] = 0;
          	 	}else{
          	 		flag = false;
          	 		break;
          	 	}
          	 }
          }else if(store[i] == 0 && store1[i] > 0){
          	if(carry > 0){
          	 	a[i] = 0;
          	 	b[i] = 1;
          	 	carry = 0;
          	 }else{
          	 	if(i - 1 >= 0 && a[i - 1] == 0 && b[i - 1] == 0){
          	 		a[i - 1] = 1;
          	 		b[i - 1] = 1;
          	 		a[i] = 0;
          	 		b[i] = 1;
          	 		carry = 1;
          	 	}else{
          	 		flag = false;
          	 		break;
          	 	}
          	 }
          }else if(store[i] == 0 && store1[i] == 0){
          	  if(carry > 0){
          	  	a[i] = 1;
          	  	b[i] = 1;
          	  	carry = 1;
          	  }else{
          	  	a[i] =  0;
          	  	b[i] = 0;
          	  }
          }	
        }
        
        
        if(!flag){
        	out.println(-1);
        	return;
        }
        
        out.println(getValue(a) + " " + getValue(b));
        // out.println(Arrays.toString(a) + " " + Arrays.toString(b));
        return;
    }

    static long getValue(int[] a){
    	long sum = 0;
    	for(int i = 0; i < a.length; i++){
    		if(a[i] == 1){
    			sum += (1L << i);
    		}
    	}
    	return sum;
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