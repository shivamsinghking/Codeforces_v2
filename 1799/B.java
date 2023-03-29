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
    
    void solve(){
       int n = sc.nextInt();
       int[] arr = sc.readArrayInt(n);
       
       
       boolean flag = true;
       for(int i = 1; i < n; i++){
       	if(arr[i] != arr[i - 1]) flag = false;
       }
       
       if(flag){
       	out.println(0);
       	return;
       }
       
       int[][] indices = new int[n][2];
       for(int i = 0; i < n; i++){
       	int v = arr[i];
       	indices[i][0] = v;
       	indices[i][1] = i;
       }
       
       Arrays.sort(indices, (a, b) -> a[0] - b[0]);
       
       // int min = Integer.MAX_VALUE;
       int idx = -1;
       
       List<int[]> ans = new ArrayList<>();
              List<int[]> ll = new ArrayList<>();
       for(int i = 1; i < n; i++){
       	if(indices[i][0] == indices[i - 1][0]) continue;
       	int val1 = indices[i][0];
       	int val2 = indices[i - 1][0];
       	
       	if(val2 == 1) continue;
       	int cnt = 0;
       	List<int[]> l = new ArrayList<>();
       	
       	int mxI = indices[i][1];
       	int mnI =  indices[i - 1][1];
       	int max = val1;
       	int min = val2;
       	out.println(" m " + max + " " + min);
       	while(max > 2){
       		max = (int)Math.ceil(max/Double.valueOf(min));
       		l.add(new int[]{mxI + 1, mnI + 1});
       		if(max == 1 || max == 2) break;
       		if(max > min){
       		} else{
       			int temp = max;
       			max = min;
       			min = temp;
       			
       			int temp1 = mnI;
       			mnI = mxI;
       			mxI = temp1;
       		}
       		// out.println(max + " " + min);
       		cnt++;
       	}
       	
       	if(max == 2){
        out.println(l.size() + " - ");
       	arr[mxI] = max;
       	arr[mnI] = min;
       	idx = mxI;
       	ll.addAll(l);
       	break;	
       	}
       }
       
       System.out.println(" idx " + idx + " "  + ll.size());
       
       if(idx == -1){
       	out.println(-1);
       	return;
       }
       
       for(int i = 0; i < n; i++){
       	if(i == idx) continue;
       	
       	int val1 = arr[i];
       	int val2 = arr[idx];
       	
       	if(val2 == 1) continue;
       	int cnt = 0;
       	while(val1 > 2){
       		val1 = (int)Math.ceil(val1/Double.valueOf(val2));
       		cnt++;
       	}
       	
       	if(val1 == 1){
       		out.println(-1);
       		return;
       	}
       	
       	arr[i] = 2;
       	ans.add(new int[]{i, idx, cnt});
       }
      
       
       for(int i = 0; i < ans.size(); i++){
       	int[] v = ans.get(i);
       	for(int j = 0; j < v[2]; j++){
       		ll.add(new int[]{v[0] + 1, v[1] + 1});
       	}
       }
       
       out.println(ll.size());
       for(int i = 0; i < ll.size(); i++){
       	out.println(ll.get(i)[0]+ " " + ll.get(i)[1]);
       }
       // idx1/idx2
       
       
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