import java.io.*;
import java.util.*;

public class D {
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
    
    void xor(char[] original, char[] b){
    	for(int i = 0; i < b.length; i++){
    		if(original[i] == b[i]){
    			original[i] = '0';
    		}else{
    			original[i] = '1';
    		}
    	}
    }
    
    void operation(int shift, boolean left, char[] arr){
    	// if true => left, false => right
    	int n = arr.length;
    	char[] c = new char[n];
    	Arrays.fill(c, '0');
    	if(left){
    		for(int i = shift; i < n; i++){
    			c[i - shift] = arr[i];
    		}
    		
    		xor(arr, c);
    	}else{
    		for(int i = 0; i < n - shift; i++){
    			c[i + shift] = arr[i];
    		}
    		
    		xor(arr, c);
    	}
    }
    
    void solve(){
       int n = sc.nextInt();
       char[] arr = sc.nextLine().toCharArray();
       char[] brr = sc.nextLine().toCharArray();
       
       boolean isSame = true;
       for(int i = 0; i < n; i++){
       	if(arr[i] != brr[i]) isSame = false;
       }
       
       if(isSame){
       	out.println(0);
       	return;
       }
       
       boolean containsOne = false;
       for(int i = 0; i < n; i++){
       	if(brr[i] == '1') containsOne = true;
       }
       
       if(containsOne == false){
       	out.println(-1);
       	return;
       }
       
       List<Integer> ans = new ArrayList<>();
       for(int i = 0; i < n; i++){
       	if(brr[i] == '1'){
       		if(arr[i] == '1') break;
       		else{
       			// match the first '1' in brr to arr
       			boolean flag = true; 
       			for(int j = n - 1; j >= 0; j--){
       				if(arr[j] == '1'){
       					if(j < i){
       						// 1 is before 1 in brr -> right shift -ve
       						// out.println(" this is ");
       						ans.add(j - i);
       						operation(i - j, false, arr);
       				
       					}else{
       						// left shift -> +ve
       						ans.add(j - i);
       						operation(j - i, true, arr);
       					}
       					flag = false;
       					break;
       				}
       			}
       			
       			if(flag){
       				out.println(-1);
       				return;
       			}
       			break;
       		}
       	}
       }
       
       
       // out.println(Arrays.toString(arr) + Arrays.toString(brr) + " --> " + ans);
       // first 
       int idx = -1;
       for(int i = 0; i < n; i++){
       	if(brr[i] == '1'){
       		idx = i;
       		break;
       	}
       }
       
       int idx2 = -1;
       for(int i = n - 1; i >= 0; i--){
       	if(arr[i] == '1'){
       		idx2 = i;
       		break;
       	}
       }
       for(int i = 0; i < idx; i++){
       	if(arr[i] != brr[i]){
       		ans.add(idx2 - i);
       		operation(idx2 - i, true, arr);
       		// arr[i] = brr[i];
       	}
       }
       // out.println(Arrays.toString(arr) + Arrays.toString(brr) + " left " + " " + idx);
              
       // right shift       
       for(int i = idx + 1; i < n; i++){
       	if(arr[i] != brr[i]){
       		// brr is 1 or 0, same process will be there
       		ans.add(idx - i);
       		int diff = i - idx;
       		operation(diff, false, arr);
       		// out.println(Arrays.toString(arr) + " " + i + " "  + diff);
       	}
       }
       
       // out.println(Arrays.toString(arr) + " " + Arrays.toString(brr));
       out.println(ans.size());
       for(int i : ans) out.print(i + " ");
       out.println();
       
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