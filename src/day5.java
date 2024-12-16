import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class day5 {
    public static void main(String[] args) throws Exception {
        String[] inp = Files.readString(Paths.get("input/5.txt")).split("\n");
        int[][] data = new int[inp.length][2];
        boolean state = true;
        int sum = 0, sum2 = 0;

        for (int i = 0; i < inp.length; i++) {
            while (state) {
                if (inp[i].length() == 0) {
                    state = false;
                    i++;
                    break;
                }
                String nrs[] = inp[i].split("\\|");
                data[i][0] = Integer.parseInt(nrs[0]);
                data[i][1] = Integer.parseInt(nrs[1]);
                i++;
            }

            int[] checknrs = Arrays.stream(inp[i].split(",")).mapToInt(Integer::parseInt).toArray();

            boolean valid = true;
            for (int j = 0; j < checknrs.length; j++) {
                for (int[] d : data) {
                    if (checknrs[j] == d[0] && Arrays.stream(checknrs).anyMatch(x -> x == d[1]) && j > indexOf(checknrs, d[1])) {
                        valid = false;
                        int tempidx = indexOf(checknrs, d[1]);
                        checknrs[j] = checknrs[j] ^ checknrs[tempidx];
                        checknrs[tempidx] = checknrs[j] ^ checknrs[tempidx];
                        checknrs[j] = checknrs[j] ^ checknrs[tempidx];
                        j = 0;
                    }
                }
            }

            System.out.println(valid + ": " + Arrays.toString(checknrs));
            if (valid) {
                sum += checknrs[checknrs.length / 2];
            } else {
                sum2 += checknrs[checknrs.length / 2];
            }
        }

        System.out.println("Part 1: " + sum);
        System.out.println("Part 2: " + sum2);
    }

    public static int indexOf(int[] arr, int el) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == el) {
                return i;
            }
        }
        return -1;
    }
}
