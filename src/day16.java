import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.awt.Point;

public class day16 {
    public static void main(String[] args) throws Exception {
        char inp[][] = Files.readAllLines(Paths.get("input/16.txt")).stream().map(String::toCharArray).toArray(char[][]::new);
        Point start = new Point(0, 0);
        Point end = new Point(0, 0);

        for (int y = 0; y < inp.length; y++) {
            for (int x = 0; x < inp[y].length; x++) {
                if (inp[y][x] == 'S') {
                    start.setLocation(x, y);
                }
                if (inp[y][x] == 'E') {
                    end.setLocation(x, y);
                }
            }
        }

        dijk(start, end, inp);
    }

    public static void dijk(Point start, Point end, char[][] map) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        pq.add(new Node(start, 0, new Point(1, 0), new ArrayList<>()));

        Map<String, Integer> visited = new HashMap<>();
        HashSet<Point> bestPaths = new HashSet<>();
        int minCost = Integer.MAX_VALUE;

        Point[] directions = { new Point(0, 1), new Point(1, 0), new Point(0, -1), new Point(-1, 0) };

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (visited.containsKey(current.point + "," + current.dir) && visited.get(current.point + "," + current.dir) < current.cost) {
                continue;
            }
            
            visited.put(current.point + "," + current.dir, current.cost);

            if (current.point.equals(end)) {
                if (current.cost < minCost) {
                    bestPaths.clear();
                    minCost = current.cost;
                }

                if (current.cost == minCost) {
                    bestPaths.addAll(current.path);
                }
            }

            for (Point dir : directions) {
                Point next = new Point(current.point.x + dir.x, current.point.y + dir.y);
                if (map[next.y][next.x] == '#')
                    continue;

                int newCost = current.cost + (current.dir.equals(dir) ? 1 : 1001);

                pq.add(new Node(next, newCost, dir, current.path));
            }
        }

        System.out.println("Part one: " + minCost);
        System.out.println("Part two: " + bestPaths.size());
    }

    static class Node {
        Point point;
        int cost;
        Point dir;
        List<Point> path = new ArrayList<>();

        Node(Point point, int cost, Point dir, List<Point> path) {
            this.point = point;
            this.cost = cost;
            this.dir = dir;
            this.path = new ArrayList<>(path);
            this.path.add(point);
        }
    }
}
