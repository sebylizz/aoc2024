import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

public class day10 {
    public static void main(String[] args) throws Exception {
        int[][] inp = Files.readString(Paths.get("input/10.txt")).lines().map(s -> s.chars().map(c -> c - '0').toArray()).toArray(int[][]::new);
        int sum = 0;
        int sum2 = 0;

        for (int i = 0; i < inp.length; i++) {
            for (int j = 0; j < inp[i].length; j++) {
                if (inp[i][j] == 0) {
                    HashSet<String> p = new HashSet<>();
                    HashSet<String> p2 = new HashSet<>();
                    visit(inp, i, j, p);
                    visit2(inp, i, j, p2, "");
                    sum += p.size();
                    sum2 += p2.size();
                }
            }
        }

        System.out.println("Part one: " + sum);
        System.out.println("Part two: " + sum2);
    }

    public static void visit(int[][] inp, int i, int j, HashSet<String> p) {
        if (inp[i][j] == 9) {
            p.add(i + "," + j);
            return;
        }
        if (i < inp.length - 1 && inp[i + 1][j] == inp[i][j] + 1)
            visit(inp, i + 1, j, p);
        if (i > 0 && inp[i - 1][j] == inp[i][j] + 1)
            visit(inp, i - 1, j, p);
        if (j < inp[i].length - 1 && inp[i][j + 1] == inp[i][j] + 1)
            visit(inp, i, j + 1, p);
        if (j > 0 && inp[i][j - 1] == inp[i][j] + 1)
            visit(inp, i, j - 1, p);
    }

    public static void visit2(int[][] inp, int i, int j, HashSet<String> p2, String str) {
        str += + i + "," + j + ":";
        if (inp[i][j] == 9) {
            p2.add(str);
            return;
        }
        if (i < inp.length - 1 && inp[i + 1][j] == inp[i][j] + 1)
            visit2(inp, i + 1, j, p2, str);
        if (i > 0 && inp[i - 1][j] == inp[i][j] + 1)
            visit2(inp, i - 1, j, p2, str);
        if (j < inp[i].length - 1 && inp[i][j + 1] == inp[i][j] + 1)
            visit2(inp, i, j + 1, p2, str);
        if (j > 0 && inp[i][j - 1] == inp[i][j] + 1)
            visit2(inp, i, j - 1, p2, str);
    }
}
