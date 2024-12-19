import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class day11 {
    public static void main(String[] args) throws Exception {
        String[] inp = Files.readString(Paths.get("input/11.txt")).trim().split(" ");
        HashMap<Long, Long> arr = new HashMap<>();
        long sum = 0;

        for(String s : inp) {
            arr.put(Long.parseLong(s), 1L);
        }

        for (int i = 0; i < 25; i++) {
            arr = push(arr);
        }

        for(long i : arr.keySet()) {
            sum += arr.get(i);
        }

        System.out.println("Part one: " + sum);

        for (int i = 0; i < 50; i++) {
            arr = push(arr);
        }

        sum = 0;
        for(long i : arr.keySet()) {
            sum += arr.get(i);
        }

        System.out.println("Part two: " + sum);
    }

    public static HashMap<Long, Long> push(HashMap<Long, Long> arr) {
        HashMap<Long, Long> temp = new HashMap<>();
        for(Long i : arr.keySet()) {
            if (i == 0L) {
                temp.put(1L, temp.getOrDefault(1L, 0L) + arr.get(i));
            } else if (new String(i + "").length() % 2 == 0) {
                String str = new String(i + "");
                temp.put(Long.parseLong(str.substring(0, str.length()/2)), temp.getOrDefault(Long.parseLong(str.substring(0, str.length()/2)), 0L) + arr.get(i));
                temp.put(Long.parseLong(str.substring(str.length()/2)), temp.getOrDefault(Long.parseLong(str.substring(str.length()/2)), 0L) + arr.get(i));
            } else {
                temp.put(i * 2024, temp.getOrDefault(i*2024, 0L) + arr.get(i));
            }
        }
        return temp;
    }
}
