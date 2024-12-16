import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day3 {
    public static void main(String[] args) throws Exception {
        String inp = Files.readString(Paths.get("input/3.txt"));
        Pattern p = Pattern.compile("mul\\((\\d*),(\\d*)\\)");
        Matcher m = p.matcher(inp);
        int sum = 0;
        while (m.find()) {
            System.out.println(m.group(1) + " " + m.group(2));
            sum += Integer.parseInt(m.group(1)) * Integer.parseInt(m.group(2));
        }

        System.out.println("Part 1: " + sum);

        p = Pattern.compile("mul\\((\\d*),(\\d*)\\)|do\\(\\)|don't\\(\\)");
        m = p.matcher(inp);

        int sum2 = 0;
        Boolean state = true;
        while (m.find()) {
            if (m.group().equals("do()")) {
                state = true;
            } else if (m.group().equals("don't()")) {
                state = false;
            }
            else if (state) {
                sum2 += Integer.parseInt(m.group(1)) * Integer.parseInt(m.group(2));
            }
        }

        System.out.println("Part 2: " + sum2);
    }
}
