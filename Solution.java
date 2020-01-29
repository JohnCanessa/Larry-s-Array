import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation Parity of a permutation
 */
public class Solution {

  /**
   * Find the index of the specified value in the array.
   */
  static int find(int[] A, int v) {

    // **** check if value is out of range ****
    if ((v < 1) || (v > A.length)) {
      return -1;
    }

    // **** loop looking for the specified value ****
    for (int i = 0; i < A.length; i++) {
      if (A[i] == v) {
        return i;
      }
    }

    // **** value not found ****
    return -1;
  }

  /**
   * Complete the function below. The elements in the array are rotated as needed.
   */
  static String larrysArrayRotations(int[] A) {

    // **** traverse ONCE through the array ****
    for (int i = 0; i < A.length; i++) {

      // **** find the index of the specified value in the array ****
      int v = i + 1;
      int index = find(A, v);
      if (index < 0) {
        System.out.println("larrysArrayRotations <<< V: " + v + " UNEXPECTED index: " + index);
        return "NO";
      }

      // // ???? ????
      // System.out.println("larrysArray <<< i: " + i + " v: " + v + " index: " +
      // index);

      // **** loop rotating values in place ****
      while (index >= i + 2) {

        // // ???? ????
        // System.out.println("larrysArray <<< BEFORE A: " + Arrays.toString(A));

        // **** rotate ONCE to put v in its proper place ****
        A[index - 0] = A[index - 1];
        A[index - 1] = A[index - 2];
        A[index - 2] = i + 1;

        // // ???? ????
        // System.out.println("larrysArray <<< AFTER A: " + Arrays.toString(A));

        // **** update index (rotations are in groups of 3 consecutive elements) ****
        index -= 2;
      }

      // // ???? ????
      // System.out.println("larrysArray <<< index: " + index);

      // **** ****
      if (index == i + 1) {

        // **** reordering not possible (only two elements out of order) ****
        if (index == A.length - 1) {
          return "NO";
        }

        // // ???? ????
        // System.out.println("larrysArray <<< before A: " + Arrays.toString(A));

        // **** rotate TWICE to put v in its proper place ****
        A[index - 0] = A[index + 1];
        A[index + 1] = A[index - 1];
        A[index - 1] = i + 1;

        // // ???? ????
        // System.out.println("larrysArray <<< after A: " + Arrays.toString(A));
      }
    }

    // **** ****
    return "YES";
  }

  /**
   * Count the number of inversions in the specified array. An inversion is when
   * an entry precedes another entry with a lower number on it.
   */
  static int inversions(int[] A) {

    // **** count of inversions ****
    int inversion = 0;

    // **** traverse the entire array ****
    for (int i = 0; i < A.length; i++) {
      int v = A[i];
      for (int j = i + 1; j < A.length; j++) {
        if (v > A[j]) {
          inversion++;
        }
      }
    }

    // **** return the count of inversions ****
    return inversion;
  }

  /**
   * Solve WITHOUT array rotations.
   * https://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
   */
  static String larrysArray(int[] A) {

    // **** get the count of inversions ****
    int numInv = inversions(A);

    // // ???? ????
    // System.out.println("solve <<< numInv: " + numInv);

    // **** grid is odd AND inversions is even ****
    if (((A.length % 2) == 1) && ((numInv % 2) == 0)) {
      return "YES";
    }

    // **** grid is even AND inversions are odd ****
    if (((A.length % 2) == 0) && ((numInv % 2) == 0)) {
      return "YES";
    }

    // **** ****
    return "NO";
  }

  // **** scanner ****
  private static final Scanner scanner = new Scanner(System.in);

  /**
   * Test scaffolding.
   */
  public static void main(String[] args) throws IOException {

    // **** ****
    // BufferedWriter bufferedWriter = new BufferedWriter(new
    // FileWriter(System.getenv("OUTPUT_PATH")));
    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    // **** read the number of test cases ****
    int t = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    // **** loop once per test case ****
    for (int tItr = 0; tItr < t; tItr++) {

      // **** read the number of integers fo rthis case ****
      int n = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      // **** instantiate array for the integers ****
      int[] A = new int[n];

      // **** read the integers into a string array ****
      String[] AItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      // **** convert the strings to integers and populate array ****
      for (int i = 0; i < n; i++) {
        int AItem = Integer.parseInt(AItems[i]);
        A[i] = AItem;
      }

      // // **** solve WITH rotations ****
      // String result = larrysArrayRotations(A);

      // **** solve WITHOUT array rotations ****
      String result = larrysArray(A);

      // **** display the result ****
      bufferedWriter.write(result);
      bufferedWriter.newLine();
    }

    // **** close the buffered writer ****
    bufferedWriter.close();

    // **** close scanner ****
    scanner.close();
  }
}