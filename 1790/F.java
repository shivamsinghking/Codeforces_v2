import java.io.*;
import java.util.*;

public class F {
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
        int n = sc.nextInt(), s = sc.nextInt() - 1;
        int[] arr = sc.readArrayInt(n - 1);
        
        CentroidDecomposition cd = new CentroidDecomposition(n);
        for(int i = 0; i < n - 1; i++){
        	int u = sc.nextInt() - 1;
        	int v = sc.nextInt() - 1;
        	cd.addIndirect(u, v);
        }
        
        cd.perform_all_preprocess();
        
        cd.update_centroid_tree(s);
     
        int ans = (int)1e9;
        for(int i = 0; i < n - 1; i++){
        	ans = cd.query_centroid_tree(arr[i] - 1, ans);
        	cd.update_centroid_tree(arr[i] - 1);
        	out.print(ans + " ");
        }
        out.println();
    }

    static class Graph {
        ArrayList<ArrayList<Integer>> gr;
 
        public Graph(int n) {
            gr = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                gr.add(new ArrayList<>());
            }
        }
 
        public void addDirect(int u, int v) {
            gr.get(u).add(v);
        }
 
        public void addIndirect(int u, int v) {
            gr.get(u).add(v);
            gr.get(v).add(u);
        }
 
        public ArrayList<Integer> neighbors(int v) {
            return gr.get(v);
        }
    }
    
    static class CentroidDecomposition extends Graph {
         boolean[] vis;
         int[] best;
         // par => parent array
         int[] par, par_centeriod, lvl, size;
         int n, k;
         int[][] lcaa;
         static ArrayList<Integer>[] centeriod_tree;        
          CentroidDecomposition(int n){
            super(n);
            this.n = n;
            this.lvl = new int[n];
            this.par = new int[n];
            this.par_centeriod = new int[n];
            this.vis = new boolean[n];
            this.best = new int[n];
            Arrays.fill(best, (int)1e9);
            this.size = new int[n];
            this.centeriod_tree = new ArrayList[n+1];
            for (int i = 0; i <= n; i++) {
                centeriod_tree[i] = new ArrayList<>();
            }
        }
        
        // first this to do after making graph
        void perform_all_preprocess(){
        	go(0, -1);
        	calculate_graph_level(0, -1);
        	preprocess_lca();
        }
        
        private void go(int node, int mypar) {
            // this you can pre calcuate
            find_size(node, mypar);
            int curCenteriod = find_centeriod(node, mypar, size[node]);
            if (mypar != -1) {
                centeriod_tree[mypar].add(curCenteriod);
                centeriod_tree[curCenteriod].add(mypar);
            }
            par_centeriod[curCenteriod] = mypar;
            vis[curCenteriod] = true;
            for (int nxt : gr.get(curCenteriod)){
                if (!vis[nxt]) {
                    go(nxt, curCenteriod);
                }
            }
        }
        
         private void calculate_graph_level(int u, int p) {
            par[u] = p;
            for (int nxt : gr.get(u)) {
                if (nxt != p) {
                    lvl[nxt] = lvl[u] + 1;
                    calculate_graph_level(nxt, u);
                }
            }
        }
        
        private void preprocess_lca() {
			this.k = 17; // this can be calculate logN/log2
			lcaa = new int[k][n];
			lcaa[0] = this.par;
			for (int i = 1; i < k; i++) {
				for (int j = 0; j < n; j++) {
					lcaa[i][j] = lcaa[i - 1][j] == -1 ? -1 : lcaa[i - 1][lcaa[i - 1][j]];
				}
			}
		}
		
        private int getLca(int u, int v) {
            if (lvl[u] > lvl[v]) {
                int temp = u;
                u = v;
                v = temp;
            }
            int dif = lvl[v] - lvl[u];
            for (int i = 0; i < k; i++) {
                if ((dif & (1 << i)) != 0) {
                    v = lcaa[i][v];
                }
            }
            if (u == v) {
                return u;
            }
            for (int i = k - 1; i >= 0; i--) {
                if (lcaa[i][u] != lcaa[i][v]) {
                    u = lcaa[i][u];
                    v = lcaa[i][v];
                }
            }
            return lcaa[0][u];
        }
    
        private int getDis(int u, int v) {
            return lvl[u] + lvl[v] - 2 * lvl[getLca(u, v)];
        }
    
        private int find_size(int node, int mypar) {
            if (vis[node])
                return 0;
            size[node] = 1;
            for (int nxt : gr.get(node)){
                if (nxt != mypar) {
                    size[node] += find_size(nxt, node);
                }
            }
            return size[node];
        }
        
        private int find_centeriod(int node, int mypar, int curSize) {
            for (int nxt : gr.get(node)) {
                if (nxt != mypar) {
                    if (!vis[nxt] && size[nxt] > curSize / 2) {
                        return find_centeriod(nxt, node, curSize);
                    }
                }
            }
            return node;
        }
        
        void update_centroid_tree(int node) {
            best[node] = 0;
            int start = node;
            while (node != -1) {
                // This can be optimized according to question
                best[node] = Math.min(best[node], getDis(node, start));
                node = par_centeriod[node];
            }
        }
        
        int query_centroid_tree(int node, int ans) {
            int start = node;
            while (node != -1) {
                ans = Math.min(ans, best[node] + getDis(start, node));
                node = par_centeriod[node];
            }
            return ans;
        }
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