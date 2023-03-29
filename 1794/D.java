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
        // tt = sc.nextInt();
        sieve(1000000 + 7);
        precompFacts();
        while (tt-- > 0) {
            solve();
        }
        out.flush();
        out.close();
    }
    
    boolean[] isPrime;
    public void sieve(int n) {
        isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (!isPrime[i])
                continue;
            for (int j = i * 2; j <= n; j = j + i) {
                isPrime[j] = false;
            }
        }
        return;
    }
    
    long exp(long base, long exp) {
        if (exp==0) return 1;
        long half=exp(base, exp/2);
        if (exp%2==0) return mul(half, half);
        return mul(half, mul(half, base));
    }
    long mul(long x, long y) {long res = x * y; return (res >= MOD ? res % MOD : res);}
    long[] factorials=new long[2_000_001];
    long[] invFactorials=new long[2_000_001];
    void precompFacts() {
        factorials[0]=invFactorials[0]=1;
        for (int i=1; i<factorials.length; i++) factorials[i]=mul(factorials[i-1], i);
        invFactorials[factorials.length-1]=exp(factorials[factorials.length-1], MOD-2);
        for (int i=invFactorials.length-2; i>=0; i--)
            invFactorials[i]=mul(invFactorials[i+1], i+1);
    }
    


    void solve(){
     int n = sc.nextInt();
     int[] arr = sc.readArrayInt(2*n);
     HashMap<Integer,Integer> primes = new HashMap<>();
     HashMap<Integer,Integer> others = new HashMap<>();
     
     int cnt = 0;
     for(int i = 0; i < 2*n; i++){
        if(isPrime[arr[i]])
            primes.put(arr[i], primes.getOrDefault(arr[i], 0) + 1);
        else 
            others.put(arr[i], others.getOrDefault(arr[i], 0) + 1);
    }
    
    int[][] brr  = new int[primes.size()][2];
    int idx = 0;
    for(int i : primes.keySet()){
        brr[idx][0] = i;
        brr[idx][1] = primes.get(i);
        idx++;
    }
    
    long ans = factorials[n];
    ans = ans%MOD;
    for(int i: others.keySet()){
        ans = (ans*1L*invFactorials[others.get(i)])%MOD;
    }
    
    dp = new Long[brr.length + 1][n + 1];
    ans = (ans*1L*helper(0, n, brr))%MOD;
    ans = ans%MOD;
    out.println(ans);
}

Long[][] dp;
long helper(int i, int cnt, int[][] arr){
    if(cnt < 0){
        return 0;
    }
    
    if(i == arr.length){
        if(cnt == 0) return 1L;
        return 0L;
    }
    
    if(dp[i][cnt] != null) return dp[i][cnt];
    
    long ans = 0L;
    ans =  (ans + ((long)invFactorials[arr[i][1] - 1]*1L*helper(i + 1, cnt - 1, arr))%MOD)%MOD;
    ans = (ans + ((long)invFactorials[arr[i][1]]*1L*helper(i + 1, cnt, arr))%MOD)%MOD;
    return dp[i][cnt] = ans;
}

long gcd(long a, long b) {
    while (b != 0) {
        long rem = a % b;
        a = b;
        b = rem;
    }
    return a;
}

long MOD = 998244353L;
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