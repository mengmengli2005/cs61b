public class Sort {

    public static void Sort(String[] x) {
        // find the smallest item
        String smallestValue = findMinValue(x);
        int smallestIndex = findMinIndex(x);

        // move it to the front
        String change = x[0];
        x[0] = smallestValue;
        x[smallestIndex] = change;
        // selection sort the rest using recursion
        int len = x.length;
        while (len > 1) {
            String[] newX = new String[len - 1];
            System.arraycopy(x, 1, newX, 0, len - 1);
            Sort(newX);
            System.arraycopy(newX, 0, x, 0, len - 1);
        }
    }

    public static String findMinValue(String[] x) {
        String minX = x[0];
        for (int i = 1; i < x.length; i ++) {
            int result = x[i].compareTo(minX);
            if (result < 0) {
                minX = x[i];
            }
        }
        return minX;
    }
    public static int findMinIndex(String[] x) {
        String minX = x[0];
        int minIndex = 0;
        for (int i = 1; i < x.length; i ++) {
            int result = x[i].compareTo(minX);
            if (result < 0) {
                minX = x[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        String[] x = new String[]{"6", "3", "7", "2", "8", "1"};

    }
}




