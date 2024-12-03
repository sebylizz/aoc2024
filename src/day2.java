import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class day2 {
    public static void main(String[] args) throws Exception {
        String inp = Files.readString(Paths.get("input/2.txt"));
        String[] lines = inp.split("\n");

        int cnt = 0;

        for (String l : lines) {
            int[] a = Arrays.stream(l.split(" ")).mapToInt(Integer::parseInt).toArray();
            Boolean asc = true;
            for (int i = 1; i < a.length; i++) {
                if (a[i] <= a[i - 1] || a[i] > a[i - 1] + 3) {
                    asc = false;
                    break;
                }
            }

            Boolean desc = true;
            for (int i = 1; i < a.length; i++) {
                if (a[i] >= a[i - 1] || a[i] < a[i - 1] - 3) {
                    desc = false;
                    break;
                }
            }
            cnt += asc || desc ? 1 : 0;
        }

        System.out.println("part 1: " + cnt);

        int cnt2 = 0;
        for (String l : lines) {
            List<Integer> aa = Arrays.stream(l.split(" ")).map(Integer::parseInt).toList();
            for (int ii = 0; ii < aa.size(); ii++) {
                List<Integer> a = new ArrayList<>(aa);
                a.remove(ii);

                Boolean asc = true;
                for (int i = 1; i < a.size(); i++) {
                    if (a.get(i) <= a.get(i - 1) || a.get(i) > a.get(i - 1) + 3) {
                        asc = false;
                        break;
                    }
                }

                Boolean desc = true;
                for (int i = 1; i < a.size(); i++) {
                    if (a.get(i) >= a.get(i - 1) || a.get(i) < a.get(i - 1) - 3) {
                        desc = false;
                        break;
                    }
                }

                if (asc || desc) {
                    cnt2++;
                    break;
                }
            }
        }

        System.out.println("part 2: " + cnt2);
    }
}
