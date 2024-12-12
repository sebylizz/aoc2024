import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.awt.Point;

public class day12 {
    public static void main(String[] args) throws Exception {
        char inp[][] = Files.readAllLines(Paths.get("input/12.txt")).stream().map(String::toCharArray).toArray(char[][]::new);
        HashSet<Point> visited = new HashSet<>();
        int sum = 0, sum2 = 0;

        for (int i = 0; i < inp.length; i++) {
            for (int j = 0; j < inp[i].length; j++) {
                if (!visited.contains(new Point(i, j))) {
                    Answer tmp = run(inp, i, j, visited, 0, 0, 0);
                    int corners = tmp.corners;
                    sum += tmp.area * tmp.perimeter;
                    sum2 += tmp.area * corners;
                }
            }
        }

        System.out.println("Part one: " + sum);
        System.out.println("Part two: " + sum2);
    }

    public static Answer run(char[][] inp, int i, int j, HashSet<Point> visited, int area, int perimeter, int corners) {
        visited.add(new Point(i, j));
        area++;
        char c = inp[i][j];

        boolean left = false, right = false, up = false, down = false;
        int cnt = 0;
        if(j > 0 && inp[i][j-1] == c) {
            left = true;
            cnt++;
        }
        if(i > 0 && inp[i-1][j] == c) {
            up = true;
            cnt++;
        }
        if(j < inp[0].length-1 && inp[i][j+1] == c) {
            right = true;
            cnt++;
        }
        if(i < inp.length-1 && inp[i+1][j] == c) {
            down = true;
            cnt++;
        }

        if(cnt == 0)
            corners += 4;
        else if(cnt == 1)
            corners += 2;
        else if(cnt == 2) {
            if(!(left && right) && !(up && down)) {
                if(left && up && inp[i-1][j-1] == c
                || left && down && inp[i+1][j-1] == c
                || right && up && inp[i-1][j+1] == c
                || right && down && inp[i+1][j+1] == c)
                    corners += 1;
                else
                    corners += 2;
            }
        } else if(cnt == 3) {
            if(left && right && up) {
                if(inp[i-1][j-1] != c)
                    corners++;
                if(inp[i-1][j+1] != c)
                    corners++;
            } else if(left && right && down) {
                if(inp[i+1][j-1] != c)
                    corners++;
                if(inp[i+1][j+1] != c)
                    corners++;
            } else if(left && up && down) {
                if(inp[i-1][j-1] != c)
                    corners++;
                if(inp[i+1][j-1] != c)
                    corners++;
            } else if(right && up && down) {
                if(inp[i-1][j+1] != c)
                    corners++;
                if(inp[i+1][j+1] != c)
                    corners++;
            }
        } else {
            if(inp[i-1][j-1] != c)
                corners++;
            if(inp[i-1][j+1] != c)
                corners++;
            if(inp[i+1][j-1] != c)
                corners++;
            if(inp[i+1][j+1] != c)
                corners++;
        }

        if (i - 1 < 0 || inp[i - 1][j] != c) {
            perimeter++;
        } else if (!visited.contains(new Point(i - 1, j))) {
            Answer result = run(inp, i - 1, j, visited, area, perimeter, corners);
            area = result.area;
            perimeter = result.perimeter;
            corners = result.corners;
        }

        if (j - 1 < 0 || inp[i][j - 1] != c) {
            perimeter++;
        } else if (!visited.contains(new Point(i, j - 1))) {
            Answer result = run(inp, i, j - 1, visited, area, perimeter, corners);
            area = result.area;
            perimeter = result.perimeter;
            corners = result.corners;
        }

        if (i + 1 >= inp.length || inp[i + 1][j] != c) {
            perimeter++;
        } else if (!visited.contains(new Point(i + 1, j))) {
            Answer result = run(inp, i + 1, j, visited, area, perimeter, corners);
            area = result.area;
            perimeter = result.perimeter;
            corners = result.corners;
        }

        if (j + 1 >= inp[0].length || inp[i][j + 1] != c) {
            perimeter++;
        } else if (!visited.contains(new Point(i, j + 1))) {
            Answer result = run(inp, i, j + 1, visited, area, perimeter, corners);
            area = result.area;
            perimeter = result.perimeter;
            corners = result.corners;
        }

        return new Answer( area, perimeter, corners );
    }
}

class Answer {
    public int area;
    public int perimeter;
    public int corners;

    public Answer(int area, int perimeter, int corners) {
        this.area = area;
        this.perimeter = perimeter;
        this.corners = corners;
    }
}
