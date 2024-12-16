import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class day9 {
    public static void main(String[] args) throws Exception {
        int[] inp = Files.readString(Paths.get("input/9.txt")).trim().chars().map(c -> c - '0').toArray();
        long res = 0, res2 = 0;

        ArrayList<Integer> data = new ArrayList<>();

        for (int i = 0; i < inp.length; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < inp[i]; j++) {
                    data.add(i / 2);
                }
            } else {
                for (int j = 0; j < inp[i]; j++) {
                    data.add(-1);
                }
            }
        }

        ArrayList<Integer> data2 = new ArrayList<>(data);

        int i = 0, j = data.size() - 1;
        while (i < j) {
            if (data.get(j) == -1) {
                j--;
            } else if (data.get(i) != -1) {
                i++;
            } else {
                data.set(i, data.get(j));
                data.set(j, -1);
                j--;
                i++;
            }
        }

        i = 0;
        for (int d : data) {
            if (d != -1)
                res += (long) i * d;
            i++;
        }

        System.out.println("Part one: " + res);

        j = data2.size() - 1;
        while (j > 0) {
            if (data2.get(j) == -1) {
                j--;
                continue;
            }

            int jl = 1;
            while ((j-jl) >= 0 && data2.get(j - jl).equals(data2.get(j))) {
                jl++;
            }

            boolean go = false;
            i = 0;
            while (i < j) {
                if(data2.get(i) != -1) {
                    i++;
                    continue;
                }
                int il = 1;
                while ((i+il) < data2.size() && data2.get(i + il).equals(-1)) {
                    il++;
                }
                if (il >= jl) {
                    go = true;
                    break;
                } else{
                    i += il;
                }
            }

            if (go) {
                while (jl > 0) {
                    data2.set(i, data2.get(j));
                    data2.set(j, -1);
                    j--;
                    i++;
                    jl--;
                }
            } else {
                j -= jl;
            }
        }

        System.out.println(data2);

        i = 0;
        for (int d : data2) {
            if (d != -1)
                res2 += (long) i * d;
            i++;
        }

        System.out.println("Part two: " + res2);
    }
}
