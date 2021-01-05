package binarySearch;

public class BinarySearchExample {
    public static void main(String[] args) {
        Integer[] arr = {1,2,3,4,5,6,8,9,12,23,45};
        System.out.println(binarySearch(arr, 4));
        System.out.println(binarySearch(arr, 10));
    }

    public static Integer binarySearch(Integer[] array, Integer key) {
        Integer left = 0;
        Integer right = array.length - 1;
        Integer middle = 0;

        while (left <= right) {
            middle = (right + left) / 2;
            Integer guess = array[middle];
            if (key == guess) {
                return middle;
            }
            if (key < guess) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        };

        return -1 * (middle + 1);
    }
}
