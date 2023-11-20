package Exercises;

public class Second {
    //A method to determine if the elements in the seq array appear in the same order within the "array" array.
    //So the one with more numbers should be located in the method body as "array" instead of "seq".

    public static void main(String[] args) {
        int[]x = {2,4,6,8,10};
        int[]y = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(determine(y,x));
    }

    static boolean determine(int[] array, int[] seq) {
        int arrayIndex = 0;
        int seqIndex = 0;

        while (arrayIndex < array.length && seqIndex < seq.length) {
            if (array[arrayIndex] == seq[seqIndex]) {
                seqIndex++;
            }
            arrayIndex++;
        }
        return seqIndex == seq.length;
    }
}
