/**
 * <p>
 * Sudoku.
 * 
 * Creates and solves a Sudoku grid through the backtracking algorithm.
 * </p>
 *
 * @author Benedict Halim
 * @version 1.0
 */
public class Sudoku {

	/**
	 * Store the Sudoku grid to solve in a 2D array.
	 */
	public static int[][] SAMPLE_BOARD = { { 9, 0, 0, 1, 0, 0, 0, 0, 5 }, { 0, 0, 5, 0, 9, 0, 2, 0, 1 },
			{ 8, 0, 0, 0, 4, 0, 0, 0, 0 }, { 0, 0, 0, 0, 8, 0, 0, 0, 0 }, { 0, 0, 0, 7, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 2, 6, 0, 0, 9 }, { 2, 0, 0, 3, 0, 0, 0, 0, 6 }, { 0, 0, 0, 2, 0, 0, 9, 0, 0 },
			{ 0, 0, 1, 9, 0, 4, 5, 7, 0 }, };
	/**
	 * An empty Sudoku board.
	 */
	private int[][] board;
	/**
	 * An empty cell.
	 */
	public static final int EMPTY = 0;
	/**
	 * The size of the Sudoku grid.
	 */
	public static final int SIZE = 9;

	/**
	 * Constructs and assigns the Sudoku board given.
	 * 
	 * @param board a 2D array that represents the Sudoku board to be solved.
	 */
	public Sudoku(int[][] board) {
		this.board = new int[SIZE][SIZE];

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				this.board[i][j] = board[i][j];
			}
		}
	}

	/**
	 * Checks if the possible number is already in the row.
	 * 
	 * @param row    an integer for the row of interest.
	 * @param number an integer for the number of interest.
	 * @return True if the row has the number, false otherwise.
	 */
	private boolean numinRow(int row, int number) {
		for (int i = 0; i < SIZE; i++)
			if (board[row][i] == number)
				return true;

		return false;
	}

	/**
	 * Checks if the possible number is already in the column.
	 * 
	 * @param column an integer for the column of interest.
	 * @param number an integer for the number of interest.
	 * @return True if the column has the number, false otherwise.
	 */
	private boolean numInCol(int col, int number) {
		for (int i = 0; i < SIZE; i++)
			if (board[i][col] == number)
				return true;

		return false;
	}

	/**
	 * Checks if the possible number is already in the sub-box.
	 * 
	 * @param row    an integer for the row of interest.
	 * @param column an integer for the column of interest.
	 * @param number an integer for the number of interest.
	 * @return True if the box has the number, false otherwise.
	 */
	private boolean numInBox(int row, int col, int number) {
		// Gets the top-left index of the box in accordance with its row.
		int r = row - row % 3;
		// Gets the top-left index of the box in accordance with its column.
		int c = col - col % 3;

		// Starts at the beginning index of the box in accordance with its row
		// and goes to its last index in accordance with its row.
		for (int i = r; i < r + 3; i++)
			// Starts at the beginning index of the box in accordance with its column
			// and goes to its last index in accordance with its column.
			for (int j = c; j < c + 3; j++)
				// Sees if the box has that number.
				if (board[i][j] == number)
					return true;
		// Otherwise, return false.
		return false;
	}

	/**
	 * Combined method to check if the number is a valid addition.
	 * 
	 * @param row    an integer for the row of interest.
	 * @param column an integer for the column of interest.
	 * @param number an integer for the number of interest.
	 * @return True if the number is a valid addition, false otherwise.
	 */
	private boolean isValid(int row, int col, int number) {
		return !numinRow(row, number) && !numInCol(col, number) && !numInBox(row, col, number);
	}

	/**
	 * Uses recursive backtracking to solve the board.
	 * 
	 * @return True if board is solved, false otherwise.
	 */
	public boolean solve() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				// Search for an empty cell.
				if (board[row][col] == EMPTY) {
					// Go through all possible numbers on that empty cell.
					for (int number = 1; number <= SIZE; number++) {
						// Check if the possible number is a valid addition.
						if (isValid(row, col, number)) {
							// If so, add it.
							board[row][col] = number;

							// Backtrack recursively after adding in that 'valid' number.
							if (solve()) {
								return true;
								// If it's not a solution, we empty the cell and continue.
							} else {
								board[row][col] = EMPTY;
							}
						}
					}
					// If there's an empty slot, we return false since it's not solveable in this
					// direction.
					// The algorithm can try another assignment for current cell.
					return false;
				}
			}
		}
		// If we've iterated over all of the slots possible and have a solution, the
		// sudoku is solved.
		return true;
	}

	/**
	 * Displays the sudoku board.
	 * 
	 */
	public void display() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print(" " + board[i][j]);
			}

			System.out.println();
		}

		System.out.println();
	}

	/**
	 * Driver for the Sudoku solver.
	 * 
	 * @param args unused
	 */
	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku(SAMPLE_BOARD);
		System.out.println("Sudoku grid before solving:");
		sudoku.display();

		// Try to solve it.
		if (sudoku.solve()) {
			System.out.println("Sudoku grid after solving with Backtracking algorithm:");
			sudoku.display();
		// Unsolveable board.
		} else {
			System.out.println("This is an unsolvable Sudoku grid.");
		}
	}

}