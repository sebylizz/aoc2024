import java.nio.file.Files;
import java.nio.file.Paths;

public class day15 {
    public static void main(String[] args) throws Exception {
        String inp = Files.readString(Paths.get("input/15.txt"));
        char[][] map = inp.split("\n\n")[0].lines().map(String::toCharArray).toArray(char[][]::new);
        char[] dirs = inp.split("\n\n")[1].replace("\n", "").lines().findFirst().get().toCharArray();
        int[] pos = new int[2], pos2 = new int[2];
        int sum = 0, sum2 = 0;

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == '@') {
                    pos[0] = x;
                    pos[1] = y;
                }
            }
        }

        char[][] newMap = new char[map.length][map[0].length*2];
        for(int y = 0; y < map.length; y++) {
            for(int x = 0; x < map[y].length; x++) {
                if(map[y][x] == 'O') {
                    newMap[y][x*2] += '[';
                    newMap[y][x*2+1] += ']';
                }
                else if(map[y][x] == '#') {
                    newMap[y][x*2] += '#';
                    newMap[y][x*2+1] += '#';
                }
                else if(map[y][x] == '@') {
                    newMap[y][x*2] += '@';
                    newMap[y][x*2+1] += '.';
                }
                else {
                    newMap[y][x*2] += '.';
                    newMap[y][x*2+1] += '.';
                }
            }
        }

        for (int y = 0; y < newMap.length; y++) {
            for (int x = 0; x < newMap[y].length; x++) {
                if (newMap[y][x] == '@') {
                    pos2[0] = x;
                    pos2[1] = y;
                }
            }
        }

        for (char dir : dirs) {
            int[] dirMap = switch (dir) {
                case '^' -> new int[] { 0, -1 };
                case '>' -> new int[] { 1, 0 };
                case 'v' -> new int[] { 0, 1 };
                case '<' -> new int[] { -1, 0 };
                default -> throw new IllegalArgumentException();
            };
            if (canMove(map, pos[0], pos[1], dirMap[0], dirMap[1])) {
                move(map, pos[0], pos[1], dirMap[0], dirMap[1]);
                pos[0] += dirMap[0];
                pos[1] += dirMap[1];
            }
            
            if (canMove(newMap, pos2[0], pos2[1], dirMap[0], dirMap[1])) {
                move(newMap, pos2[0], pos2[1], dirMap[0], dirMap[1]);
                pos2[0] += dirMap[0];
                pos2[1] += dirMap[1];
            }
        }
        for(int y = 0; y < map.length; y++) {
            for(int x = 0; x < map[y].length; x++) {
                if(map[y][x] == 'O') {
                    sum += y*100+x;
                }
            }
        }
        for(int y = 0; y < newMap.length; y++) {
            for(int x = 0; x < newMap[y].length; x++) {
                if(newMap[y][x] == '[') {
                    sum2 += y*100+x;
                }
            }
        }
        printMap(map);
        printMap(newMap);
        System.out.println("Part one: " + sum);
        System.out.println("Part two: " + sum2);
    }

    public static boolean canMove(char[][] map, int x, int y, int vx, int vy) {
        if (map[y + vy][x + vx] == '#') {
            return false;
        }
        if (map[y + vy][x + vx] == 'O') {
            return canMove(map, x + vx, y + vy, vx, vy);
        }
        if( map[y+vy][x+vx] == '[') {
            if(vx == 0)
                return canMove(map, x + vx, y + vy, vx, vy) && canMove(map, (x + vx) + 1, y + vy, vx, vy);
            return canMove(map, x + vx, y + vy, vx, vy);
        }
        if( map[y+vy][x+vx] == ']') {
            if(vx == 0)
                return canMove(map, (x + vx) - 1, y + vy, vx, vy) && canMove(map, x + vx, y + vy, vx, vy);
            return canMove(map, x + vx, y + vy, vx, vy);
        }
        return true;
    }

    public static void move(char[][] map, int x, int y, int vx, int vy) {
        if( map[y + vy][x + vx] == 'O') {
            move(map, x + vx, y + vy, vx, vy);
        }
        if( map[y + vy][x + vx] == '[') {
            if(vx == 0)
                move(map, (x + vx) + 1, y + vy, vx, vy);
            move(map, x + vx, y + vy, vx, vy);
        }
        if( map[y + vy][x + vx] == ']') {
            if(vx == 0)
                move(map, (x + vx) - 1, y + vy, vx, vy);
            move(map, x + vx, y + vy, vx, vy);
        }
        map[y + vy][x + vx] = map[y][x];
        map[y][x] = '.';
    }

    public static void printMap(char[][] map) {
        for (char[] row : map) {
            System.out.println(row);
        }
        System.out.println();
    }
}
