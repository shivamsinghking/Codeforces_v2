// practice with kaiboy
import java.io.*;
import java.util.*;

public class CF916C extends PrintWriter {
	CF916C() { super(System.out); }
	Scanner sc = new Scanner(System.in);
	public static void main(String[] $) {
		CF916C o = new CF916C(); o.main(); o.flush();
	}

	boolean prime(int n) {
		for (int a = 2; a <= n / a; a++)
			if (n % a == 0)
				return false;
		return true;
	}
	void main() {
		int n = sc.nextInt();
		int m = sc.nextInt();
		int p = n;
		while (!prime(p))
			p++;
		println("2 " + p);
		println("1 " + n + " 2");
		if (n >= 3) {
			println("2 3 " + (1 + p - n));
			for (int i = 3; i < n; i++)
				println(i + " " + (i + 1) + " 1");
		}
		m -= n - 1;
		for (int i = 1; i <= n && m > 0; i++)
			for (int j = i + 1; j <= n && m > 0; j++) {
				if (i == 1 && j == n)
					continue;
				if (i > 1 && j == i + 1)
					continue;
				println(i + " " + j + " " + p);
				m--;
			}
	}
}
