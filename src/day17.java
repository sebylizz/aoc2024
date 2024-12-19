import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class day17 {
    static long a, b, c;

    public static void main(String[] args) throws Exception {
        String[] inp = Files.readString(Paths.get("input/17.txt")).split("\n");
        a = Long.parseLong(inp[0].split(": ")[1]);
        b = Long.parseLong(inp[1].split(": ")[1]);
        c = Long.parseLong(inp[2].split(": ")[1]);
        String goal = inp[4].split(": ")[1] + ",";
        int[] p = Arrays.stream(goal.split(",")).mapToInt(Integer::parseInt).toArray();

        System.out.println("Part one: " + run(a, b, c, p));
        // Program: 2,4,1,7,7,5,4,1,1,4,5,5,0,3,3,0
        long cnt = 03074103300000000L;
        while (true) {
            String out = run(cnt, 0, 0, p);
            if (out.equals(goal)) {
                break;
            }
            cnt += 01L;
        }

        System.out.println("Part two: " + cnt);
    }

    public static String run(long a, long b, long c, int[] p) {
            String out = "";
            int i = 0;
            while (i < p.length) {
                switch (p[i]) {
                    case 0: // adv
                        a = a >> combo(p[i + 1], a, b, c);
                        break;
                    case 1: // bxl
                        b = b ^ p[i + 1];
                        break;
                    case 2:
                        b = combo(p[i + 1], a, b, c) % 8;
                        break;
                    case 3: // jnz
                        if (a == 0)
                            break;
                        i = p[i + 1];
                        i -= 2;
                        break;
                    case 4:
                        b = b ^ c;
                        break;
                    case 5:
                        out += (combo(p[i + 1], a, b, c) % 8) + ",";
                        break;
                    case 6:
                        b = a >> combo(p[i + 1], a, b, c);
                        break;
                    case 7:
                        c = a >> combo(p[i + 1], a, b, c);
                        break;
                    default:
                        System.out.println("err");
                        break;
                }
                i += 2;
            }
        return out;
    }

    public static long combo(int i, long a, long b, long c) {
        switch (i) {
            case 4:
                return a;
            case 5:
                return b;
            case 6:
                return c;
            default:
                return i;
        }
    }
}
