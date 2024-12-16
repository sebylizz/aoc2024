import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

public class day6 {
    private static HashSet<String> visited = new HashSet<>();

    public static void main(String[] args) throws Exception {
        char[][] inp = Files.lines(Paths.get("input/6.txt")).map(String::toCharArray).toArray(char[][]::new);

        System.out.println("Part 1: " + run(inp, true));

        visited.clear();
        visited.add("100,74");
        int part2cnt = 0;
        for (int i = 0; i < visited.size(); i++) {
            int x = Integer.parseInt(visited.toArray()[i].toString().split(",")[0]);
            int y = Integer.parseInt(visited.toArray()[i].toString().split(",")[1]);
            char tmpchar = inp[x][y];
            inp[x][y] = 'Z';
            if (run(inp, false) == 0) {
                // System.out.println("(" + x + ", " + y + ")");
                part2cnt++;
            }
            // inp[x][y] = tmpchar;
        }

        System.out.println("Part 2: " + part2cnt);

        for (int i = 0; i < inp.length; i++) {
            for (int j = 0; j < inp[i].length; j++) {
                System.out.print(inp[i][j]);
            }
            System.out.println();
        }
    }

    private static int run(char[][] inp, boolean first) {
        int[] guy = { 0, 0 };
        HashSet<String> visitedDir = new HashSet<>();

        for (int i = 0; i < inp.length; i++) {
            for (int j = 0; j < inp[i].length; j++) {
                if (inp[i][j] == '^') {
                    guy[0] = i;
                    guy[1] = j;
                }
            }
        }

        char dir = inp[guy[0]][guy[1]];

        while (guy[0] >= 0 && guy[0] < inp.length && guy[1] >= 0 && guy[1] < inp[0].length) {
            if (visitedDir.contains(dir + "," + guy[0] + "," + guy[1])) {
                return 0;
            }
            if (first)
                visited.add(guy[0] + "," + guy[1]);
            else
                inp[guy[0]][guy[1]] = 'X';
            visitedDir.add(dir + "," + guy[0] + "," + guy[1]);
            switch (dir) {
                case '^':
                    if (guy[0] - 1 >= 0 && inp[guy[0] - 1][guy[1]] == '#') {
                        guy[1]++;
                        dir = '>';
                    } else
                        guy[0]--;
                    break;
                case '>':
                    if (guy[1] + 1 < inp[guy[0]].length && inp[guy[0]][guy[1] + 1] == '#') {
                        guy[0]++;
                        dir = 'v';
                    } else
                        guy[1]++;
                    break;
                case 'v':
                    if (guy[0] + 1 < inp.length && inp[guy[0] + 1][guy[1]] == '#') {
                        guy[1]--;
                        dir = '<';
                    } else
                        guy[0]++;
                    break;
                case '<':
                    if (guy[1] - 1 >= 0 && inp[guy[0]][guy[1] - 1] == '#') {
                        guy[0]--;
                        dir = '^';
                    } else
                        guy[1]--;
                    break;
                default:
                    break;
            }
        }
        return visited.size();
    }
}
