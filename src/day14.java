import java.awt.Point;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day14 {
    public static void main(String[] args) throws Exception {
        List<String> inp = Files.readAllLines(Paths.get("input/14.txt"));
        List<List<Point>> robots = new ArrayList<>();
        int boardX = 101, boardY = 103;
        long q1 = 0, q2 = 0, q3 = 0, q4 = 0;

        for (int i = 0; i < inp.size(); i++) {
            Pattern p = Pattern.compile("([-]?\\d+).([-]?\\d+).*=([-]?\\d+),([-]?\\d+)");
            Matcher m = p.matcher(inp.get(i));
            m.find();
            int[] start = { Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)) };
            int[] dir = { Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4)) };
            robots.add(new ArrayList<>(Arrays.asList(new Point(start[0], start[1]), new Point(dir[0], dir[1]))));
        }

        int part2 = 0;
        for(int i = 0; i < 100; i++) {
            move(1, boardX, boardY, robots);
            part2++;
        }

        for(List<Point> bot : robots) {
            int[] start = {bot.get(0).x, bot.get(0).y};
            if (start[0] == boardX / 2 || start[1] == boardY / 2) continue;
            if (start[0] < boardX / 2 && start[1] < boardY / 2) q1++;
            else if (start[0] > boardX / 2 && start[1] < boardY / 2) q2++;
            else if (start[0] < boardX / 2 && start[1] > boardY / 2) q3++;
            else q4++;
        }

        while(true) {
            part2++;
            if(move(1, boardX, boardY, robots)) {
                printBoard(boardX, boardY, robots);
                break;
            }
        }

        System.out.println("Part one: " + q1 * q2 * q3 * q4);
        System.out.println("Part two: " + part2);
    }

    public static boolean move(int steps, int boardX, int boardY, List<List<Point>> robots) {
        HashSet<Point> uniques = new HashSet<>();
        for(List<Point> bot : robots) {
            int[] start = {bot.get(0).x, bot.get(0).y};
            int[] dir = {bot.get(1).x, bot.get(1).y};
            start[0] = Math.floorMod((start[0] + dir[0] * steps), boardX);
            start[1] = Math.floorMod((start[1] + dir[1] * steps), boardY);
            bot.set(0, new Point(start[0], start[1]));
            uniques.add(new Point(start[0], start[1]));
        }

        return uniques.size() == robots.size();
    }
       

    public static void printBoard(int boardX, int boardY, List<List<Point>> robots) {
        char[][] board = new char[boardY][boardX];
        for (int i = 0; i < boardY; i++)
            for (int j = 0; j < boardX; j++)
                board[i][j] = ' ';
        for(List<Point> bot : robots) {
            int[] start = {bot.get(0).x, bot.get(0).y};
            board[start[1]][start[0]] = '1';
        }
        for (int i = 0; i < boardY; i++) {
            for (int j = 0; j < boardX; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
