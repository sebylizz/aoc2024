import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class day1 {
    public static void main(String[] args) throws Exception {
        int[][] values = Files.lines(Paths.get("input/1.txt"))
                .map(line -> Arrays.stream(line.split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .toArray(int[][]::new);

        int[] a = Arrays.stream(values).mapToInt(arr -> arr[0]).toArray();
        int[] b = Arrays.stream(values).mapToInt(arr -> arr[1]).toArray();

        Arrays.sort(a);
        Arrays.sort(b);

        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.abs(a[i] - b[i]);
        }
        System.out.println("part 1: " + sum);

        Map<Integer, Integer> m = new HashMap<>();
        for (int value : b) {
            m.put(value, m.getOrDefault(value, 0) + 1);
        }
        int prd = 0;
        for (int value : a) {
            prd += value * m.getOrDefault(value, 0);
        }
        System.out.println("part 2: " + prd);
    }
}
