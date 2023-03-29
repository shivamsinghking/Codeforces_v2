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
       char[] c = sc.nextLine().toCharArray();
       TreeMap<Character,Integer> map = new TreeMap<>();
       for(char i: c){
       	map.put(i, map.getOrDefault(i, 0) + 1);
       }
       
       int n = c.length;
       char[] ans = new char[n];
       int l = 0, r = n - 1; 
       
       while(map.size() > 0 && l <= r){
       	char a = map.firstKey();
       	if(map.get(a) > 1){
       		ans[l] = a;
       		ans[r] = a;
       		l++;
       		r--;
       		map.put(a, map.get(a) - 2);
       		if(map.get(a) == 0) map.remove(a);
       	}else{
       		if(map.higherKey(a) != null){
       			    char b = map.higherKey(a);
       			    if(map.size() > 2){
       			    	ans[l] = b;
       			    	ans[r] = a;
       			    	map.put(b, map.get(b) - 1);
       				    map.put(a, map.get(a) - 1);
       				   if(map.get(a) == 0) map.remove(a);
       				   if(map.get(b) == 0) map.remove(b);
       				   l++;
       				   r--;
                       break;
       			    }else{
       			    	// out.println(" 00> ");
       			    	int v = map.get(b);
       			    	// System.out.println(" v " + v + " " + l + " " + r);
       			    	 while(v > 0 && l <= r){
       			    		ans[l] = b;
       			    		ans[r] = b;
       			    		v-=2;
       			    		l++;r--;
       			    	  }	
       			    	  // out.println(Arrays.toString(ans));

       			    	  ans[l] = a;
       			    	  l++;r--;
       			    	  map.remove(a);
       			    	  map.remove(b);
       			    	  break;
       			    }
       			    
       		}else{
       			ans[l] = a;
       			ans[r] = a;
       			map.put(a, map.get(a) - 1);
       			if(map.get(a) == 0) map.remove(a);
       			l++; r--;
       		}
       	}
       }
       List<Character> ll = new ArrayList<>();
       for(char i: map.keySet()){
       	 for(int j = 0; j < map.get(i); j++){
       	 	ll.add(i);
       	 }
       }
      
       Collections.sort(ll);
       // System.out.println(" l " + l + " " + r + " " + Arrays.toString(ans) + " " + map.size());
       int indx = 0;
       for(int i = l; i <= r; i++){
       	 ans[i] = ll.get(indx);
       	 indx++;
       }
       String s = String.valueOf(ans);
       out.println(s);
       
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