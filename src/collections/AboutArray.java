package collections;

/**
 * Bracket tells it is an array
 * Arrays are object, i.e. int is primitive, int[] is object
 * Array size must be specified unless it is initialized on declaration
 */
public class AboutArray {
    public static void main(String[] args) {
        // Array size must be specified on declaration
        // Below 4 declarations are valid & same
        int[] a = new int[5];
        int []b = new int[5];
        int c[] = new int[5];
        int d []= new int[5];

        // Arry size must not be specified if array is initialized on declaration
        int[] a1 = new int[] { 1, 2, 3, 4 };
        int[] a2 = new int[4] { 1, 2, 3, 4}; // DOES NOT COMPILE
        int[] a3 = { 1, 2, 3, 4}; // anonymous initialization, no type and no size

        // Multi-dimensional array
        // 2D array is 1 dimensional array of several 1 dimensional arrays
        // At least first dimension size must be specified
        int[][] b1 = new int[5][5];
        int[][] b2 = new int[5][];
        int[][] b3 = new int[][];       // DOES NOT COMPILE
        int[][] b4 = new int[][5];      // DOES NOT COMPILE

        int[][] ar = new int[][] {{1, 2}, {3}, {4, 5, 6}};
        ar[0] = new int[10];

        int[][] c1; // 2D array
        int[] c2[]; // 2D array
        int c3[][]; // 2D array
        int[] e1[], e2[][]; // e1 is 2D array, e2 is 3D array

        // String array is array of references to String objects
        String[] strings = {"strvalue"};
        Object[] objects = strings;
        String[] anotherStrings = (String[]) objects; // Cast is mandatory
        anotherStrings[0] = new StringBuilder();    // DOES NOT COMPILE, StringBuilder to String
        objects[0] = new StringBuilder();       // Runtime exception, StringBuilder to String

    }
}
