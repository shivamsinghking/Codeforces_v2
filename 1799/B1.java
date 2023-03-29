import java.io.*;
import java.util.*;


public class B1 {
    public static void main(String[] args) throws FileNotFoundException {
        Solution s = new Solution();
        s.solver();
    }
}

class Pair implements Comparable<Pair>{
	int index, val;
	Pair(int index, int val){
		this.index = index;
		this.val = val;
	}
	
	public int compareTo(Pair o){
		return this.val == o.val ? this.index - o.index :this.val - o.val;
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
       	if(arr[i] != arr[i - 1]){
       		flag = false;
       		break;
       	}
       }
       
       if(flag){
       	out.println(0);
       	return;
       }
       
       TreeMap<Integer,List<Integer>> map = new TreeMap<>();
       for(int i = 0; i < n; i++){
       	int v = arr[i];
       	if(map.containsKey(v) == false){
       		map.put(v, new ArrayList<>());
       	}
       	map.get(v).add(i+1);
       } 
       
       // for(Pair i: set){
       // 	out.println(i.val + " " + i.index);
       // }
       
       List<int[]> ans = new ArrayList<>();
       while(true){
       	int p = map.firstKey();
       	if(p == 1){
       		out.println(-1);
       		return;
       	}
       	List<Integer> l1 = map.get(p);
       	int uIdx = l1.get(l1.size() - 1);
       	if(map.higherKey(p) != null){
       		int u = map.higherKey(p);
       		List<Integer> ll = map.get(u);
       		int newV = (int)Math.ceil(u/Double.valueOf(p));
       		// out.println(" u " + newV + " "  + p.val + " " + u.index + " " + p.index);
       		int indx = ll.get(ll.size() - 1);
       		map.get(u).remove(map.get(u).size() - 1);
       		if(map.get(u).size() == 0){
       			map.remove(u);
       		}
       		if(map.containsKey(newV) == false) map.put(newV, new ArrayList<>());
       		map.get(newV).add(indx);
       		ans.add(new int[]{indx, uIdx});
       		// u.val = newV;
       	}else{
       		break;
       	}
       }
       
         // for(Pair i: set){
       	 //  out.println(i.val + " - " + i.index);
         // }
         
         out.println(ans.size());
         for(int[] i: ans){
         	out.println(i[0] + " " + i[1]);
         }
         
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