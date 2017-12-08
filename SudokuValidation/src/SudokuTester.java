import java.util.Arrays;
import java.util.stream.IntStream;

class SudokuTester {

  private static int[] validSequence = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	
	
  public static void main(String[] args) {
    System.out.println("valid: " + isValidSudoku(VALID));
    System.out.println("invalid: " + isValidSudoku(INVALID));
  }

  // returns true, when the argument contains a valid sudoku solution
  private static boolean isValidSudoku(int[][] a) {
	  
	  int[][] b = new int[9][9];
	  
    for (int i = 0; i < a.length; i ++) {
    	// tests lines
		if (!isValidGroup(a[i])) {
			return false;
		}
		
		// invert the multidimensional array so that rows become columns
		for (int j=0; j< a[i].length; j++) {
			b[j][i] = a[i][j];
		}
    }
//    System.out.println("==============");
    // this would test the columns from a[][]
    for (int i=0; i<b.length; i++) {
    	if (!isValidGroup(b[i])) {
    		return false;
    	}
    }
    
    // test the 3x3 clusters
    for (int i = 0; i < 3; i++) {
    	
    }
    
    return true;
  }

  private static boolean isValidGroup(int[] group) {
//	 printLine(group);
	 return  Arrays.equals(IntStream.of(group).sorted().toArray(), validSequence);
  }
  
  private static void printLine(int[] line) {
	  IntStream.of(line).forEach(a -> System.out.print(a + " "));
	  System.out.println();
  }
  
  // this array contains a valid sudoku solution
  private static final int[][] VALID = {
    {5,3,4,6,7,8,9,1,2},
    {6,7,2,1,9,5,3,4,8},
    {1,9,8,3,4,2,5,6,7},
    {8,5,9,7,6,1,4,2,3},
    {4,2,6,8,5,3,7,9,1},
    {7,1,3,9,2,4,8,5,6},
    {9,6,1,5,3,7,2,8,4},
    {2,8,7,4,1,9,6,3,5},
    {3,4,5,2,8,6,1,7,9}
  };

  // this array contains an invalid sudoku solution
  private static final int[][] INVALID = {
    {5,3,4,6,7,8,9,1,2},
    {6,7,2,1,9,5,3,4,8},
    {1,9,8,3,4,2,5,6,7},
    {8,5,9,7,6,1,4,2,3},
    {4,2,6,8,5,3,7,9,1},
    {7,1,3,9,2,4,8,5,6},
    {9,6,1,5,3,1,2,8,4},
    {2,8,7,4,1,9,6,3,5},
    {3,4,5,2,8,6,1,7,9}
  };

}