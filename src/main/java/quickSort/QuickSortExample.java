package quickSort;

import java.util.Arrays;
import java.util.stream.Stream;

public class QuickSortExample {
    public static void main(String[] args) {
        Integer[] arr = {10,2,5,7,10,12, 34,3,14,4,4,6};
        System.out.println(Arrays.toString(quickSort(arr)));
    }


    public static Integer[] quickSort(Integer[] array) {
        if (array.length < 2) {
            return array;
        }
        Integer num = array[0];
        Integer[] left = Arrays.stream(array).skip(1).filter(x-> x < num).toArray(Integer[]::new);
        Integer[] right = Arrays.stream(array).skip(1).filter(x-> x >= num).toArray(Integer[]::new);

        Stream<Integer> sortedLeft = Stream.concat(Arrays.stream(quickSort(left)), Stream.of(num));

        return Stream.concat(sortedLeft, Arrays.stream(quickSort(right))).toArray(Integer[]::new);
    }

}
