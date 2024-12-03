import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day3 {
    public static void main(String[] args) throws Exception {
        String inp = Files.readString(Paths.get("input/3.txt"));
        Pattern p = Pattern.compile("mul\\(\\d*,\\d*\\)");
        Matcher m = p.matcher(inp);

        int sum = 0;
        while(m.find()){
            Integer x = Integer.parseInt(m.group().substring(4, m.group().indexOf(",")));
            Integer y = Integer.parseInt(m.group().substring(m.group().indexOf(",") + 1, m.group().length() - 1));
            sum += x * y;
        }

        System.out.println("Part 1: " + sum);

        p = Pattern.compile("(?<=do\\(\\)).*?(?<!don't\\(\\)).*?(mul\\(\\d*,\\d*\\))");
        m = p.matcher(inp);

        int sum2 = 0;
        while(m.find()){
            String group = m.group(1) == null ? m.group(2) : m.group(1);
            System.out.println(group);
            if(group.length() < 5){
                continue;
            }
            Integer x = Integer.parseInt(group.substring(4, group.indexOf(",")));
            Integer y = Integer.parseInt(group.substring(group.indexOf(",") + 1, group.length() - 1));
            sum2++;
            //sum2 += x * y;
        }

        System.out.println(sum2);
    }
}
