package L2_May29;

import java.util.Scanner;

/**
 * @author Garima Chhikara
 * @email garima.chhikara@codingblocks.com
 */

public class DecimalToBinary {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);

		int n = scn.nextInt();

		int ans = 0;

		int multiplier = 1;

		while (n != 0) {

			int rem = n % 2;

			ans = ans + (rem * multiplier);
			multiplier = multiplier * 10;
			n = n / 2;
		}

		System.out.println(ans);
	}

}