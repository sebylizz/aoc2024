import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day13 {

    public static void main(String[] args) throws Exception {
        String[] inp = Files.readString(Paths.get("input/13.txt")).split("\n\n");
        long sum = 0, sum2 = 0;

        for (String s : inp) {
            Pattern p = Pattern.compile("(\\d+), Y\\+(\\d+)\\n.*X\\+(\\d+), Y\\+(\\d+)\\n.*X=(\\d+), Y=(\\d+)");
            Matcher m = p.matcher(s);
            m.find();
            long a[] = new long[] { Long.parseLong(m.group(1)), Long.parseLong(m.group(2)) };
            long b[] = new long[] { Long.parseLong(m.group(3)), Long.parseLong(m.group(4)) };
            long price[] = new long[] { Long.parseLong(m.group(5)), Long.parseLong(m.group(6)) };

            long numX = price[0] * b[1] - price[1] * b[0];
            long denX = a[0] * b[1] - a[1] * b[0];

            long numY = price[0] * a[1] - price[1] * a[0];
            long denY = a[1] * b[0] - a[0] * b[1];

            if(numX % denX == 0 && numY % denY == 0)
                sum += 3 * (numX / denX) + (numY / denY);

            price[0] += 10000000000000L;
            price[1] += 10000000000000L;

            numX = price[0] * b[1] - price[1] * b[0];
            denX = a[0] * b[1] - a[1] * b[0];

            numY = price[0] * a[1] - price[1] * a[0];
            denY = a[1] * b[0] - a[0] * b[1];

            if(numX % denX == 0 && numY % denY == 0)
                sum2 += 3 * (numX / denX) + (numY / denY);
        }


        System.out.println("Part one: " + sum);
        System.out.println("Part two: " + sum2);
    }
}
