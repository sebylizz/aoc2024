import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class day7 {
    public static void main(String[] args) throws Exception {
        String[] inp = Files.readString(Paths.get("input/7.txt")).split("\n");
        long sum = 0, sum2 = 0;

        for (String l : inp) {
            long result = Long.parseLong(l.split(":")[0]);
            long[] nrs = Arrays.stream(l.split(":")[1].trim().split(" ")).mapToLong(Long::parseLong).toArray();

            if (runAll1(nrs, 1, nrs[0], result))
                sum += result;

            if (runAll2(nrs, 1, nrs[0], result))
                sum2 += result;

        }

        System.out.println("Part 1: " + sum);
        System.out.println("Part 2: " + sum2);
    }

    public static boolean runAll1(long[] nrs, int i, long sum, long target) {
        if (i >= nrs.length) {
            return sum == target;
        }

        return runAll1(nrs, i + 1, sum + nrs[i], target)
                || runAll1(nrs, i + 1, sum * nrs[i], target);
    }

    public static boolean runAll2(long[] nrs, int i, long sum, long target) {
        if (i >= nrs.length) {
            return sum == target;
        }

        return runAll2(nrs, i + 1, sum + nrs[i], target)
                || runAll2(nrs, i + 1, sum * nrs[i], target)
                || runAll2(nrs, i + 1, Long.parseLong(sum + "" + nrs[i]), target);
    }
}
