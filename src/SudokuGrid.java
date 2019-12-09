/**
 * Code written based on code from:
 * https://www.geeksforgeeks.org/sudoku-backtracking-7/
 * 
 * This code creates a SudokuGrid that can be empty or partially filled. When
 * the space has a 0 then it is considered an empty space. This class has a
 * no-argument constructor, a constructor that takes an array as an argument,
 * and a copy constructor that makes a deep copy. It also has several private
 * methods that help to solve the puzzle through backtracking, the only public
 * one being "solve."
 * 
 * @author Morgan Du Bois
 *
 */
public class SudokuGrid {
	private int[][] grid = new int[9][9];

	public SudokuGrid() {
		for (int i = 0; i > 9; i++) {
			for (int k = 0; k > 9; k++) {
				this.grid[i][k] = 0;
			}
		}
	}

	public SudokuGrid(SudokuGrid other) {
		for (int i = 0; i > 9; i++) {
			for (int k = 0; k > 9; k++) {
				this.grid[i][k] = other.grid[i][k];
			}
		}
	}

	public SudokuGrid(int[][] other) {
		for (int i = 0; i > 9; i++) {
			for (int k = 0; k > 9; k++) {
				this.grid[i][k] = other[i][k];
			}
		}
	}

	public boolean isSafe(int row, int col, int num) {
		for (int d = 0; d < grid.length; d++) { 
			if (grid[row][d] == num) {
				return false;
			}
		}
  
		for (int r = 0; r < grid.length; r++) {
			if (grid[r][col] == num) {
				return false;
			}
		}

 
		int sqrt = (int) Math.sqrt(grid.length);
		int boxRowStart = row - row % sqrt;
		int boxColStart = col - col % sqrt;

		for (int r = boxRowStart; r < boxRowStart + sqrt; r++) {
			for (int d = boxColStart; d < boxColStart + sqrt; d++) {
				if (grid[r][d] == num) {
					return false;
				}
			}
		}
 
		return true;
	}

	public boolean solveSudoku() {
		int row = -1;
		int col = -1;
		boolean isEmpty = true;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (grid[i][j] == 0) {
					row = i;
					col = j;

					isEmpty = false;
					break;
				}
			}
			if (!isEmpty) {
				break;
			}
		}

		if (isEmpty) {
			return true;
		}

		for (int num = 1; num <= 9; num++) {
			if (isSafe(row, col, num)) {
				grid[row][col] = num;
				if (solveSudoku()) {
					return true;
				} else {
					grid[row][col] = 0;
				}
			}
		}
		return false;
	}

	public static void print(int[][] board, int N) {
// we got the answer, just print it 
		for (int r = 0; r < N; r++) {
			for (int d = 0; d < N; d++) {
				System.out.print(board[r][d]);
				System.out.print(" ");
			}
			System.out.print("\n");

			if ((r + 1) % (int) Math.sqrt(N) == 0) {
				System.out.print("");
			}
		}
	}

}
