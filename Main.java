import java.io.File;
import java.util.Scanner;

public class Main {

	static int[][] grid;
	static boolean[][] vis;
	static int dim = 9;

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("in.txt"));

		grid = new int[dim][dim];
		vis = new boolean[dim][dim];

		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				grid[i][j] = in.nextInt();
			}
		}
		solve(0, 0);
		for (int i = 0; i < dim; i++) {
			String out = "";
			for (int j = 0; j < dim; j++) {
				out += grid[i][j] + " ";
			}
			System.out.println(out.trim());
		}
		in.close();
	}

	static boolean check(int x, int y, int choix) {
		// check la ligne
		for (int i = 0; i < dim; i++) {
			if (i != y) {
				if (grid[x][i] == choix)
					return false;
			}
		}
		// check la colonne
		for (int i = 0; i < dim; i++) {
			if (i != x) {
				if (grid[i][y] == choix)
					return false;
			}
		}

		// chcek le carré

		int rac = (int) Math.sqrt(dim);

		int a = (x / rac) * rac;
		int b = (y / rac) * rac;

		for (int i = a; i < a + rac; i++) {
			for (int j = b; j < b + rac; j++) {
				if (i == x && j == y)
					continue;
				if (grid[i][j] == choix) {
					return false;
				}
			}
		}

		return true;
	}

	public static boolean solve(int row, int col) {
		if (col == dim)
			return true;
		
		if (row == dim)
			return solve(0, col + 1);

		if (vis[row][col])
			return solve(row + 1, col);

		for (int i = 1; i <= dim; i++) {
			if (check(row, col, i)) {
				grid[row][col] = i;
				if (solve(row + 1, col)){
					return true;
				}
				grid[row][col] = 0;
			}
		}

		return false;
	}
}
