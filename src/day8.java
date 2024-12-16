import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

public class day8 {
    public static void main(String[] args) throws Exception {
        char[][] inp = Files.lines(Paths.get("input/8.txt")).map(String::toCharArray).toArray(char[][]::new);
        HashSet<String> nodes = new HashSet<>();

        for (int i = 0; i < inp.length; i++) {
            for (int j = 0; j < inp[i].length; j++) {
                if (inp[i][j] != '.') {
                    place(inp, i, j, nodes);
                }
            }
        }

        System.out.println("Part 1: " + nodes);

        System.out.println("Part 2: " + nodes.size());

        for (int i = 0; i < inp.length; i++) {
            for (int j = 0; j < inp[i].length; j++) {
                if(nodes.contains(i + "," + j)) {
                    inp[i][j] = 'X';
                }
            }
        }

    }

    public static void place(char[][] inp, int ii, int jj, HashSet<String> nodes) {
        char c = inp[ii][jj];
        for (int i = 0; i < inp.length; i++) {
            for (int j = 0; j < inp[i].length; j++) {
                if (inp[i][j] == c && i != ii && j != jj) {
                    nodes.add(ii + "," + jj);
                    add(inp, ii, jj, i, j, nodes, true);
                    add(inp, ii, jj, i, j, nodes, false);
                }
            }
        }
    }

    public static void add(char[][] inp, int ii, int jj, int i, int j, HashSet<String> nodes, boolean asc) {
        int newi = asc ? i + (i - ii) : ii + (ii - i);
        int newj = asc ? j + (j - jj) : jj + (jj - j);
        if (newi < inp.length && newj < inp[0].length && newi >= 0 && newj >= 0) {
            nodes.add(newi + "," + newj);
            add(inp, i, j, newi, newj, nodes, asc);
        }
    }
}
