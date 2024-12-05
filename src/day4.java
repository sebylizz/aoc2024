import java.nio.file.Files;
import java.nio.file.Paths;

public class day4 {
    public static void main(String[] args) throws Exception {
        char inp[][] = Files.readAllLines(Paths.get("input/4.txt")).stream().map(String::toCharArray).toArray(char[][]::new);
        int dir[][] = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, 1}, {-1, 0}, {-1, -1}};
        int cnt = 0, cnt2 = 0;

        for (int i = 0; i < inp.length; i++) {
            for (int j = 0; j < inp[i].length; j++) {
                if(inp[i][j] == 'X'){
                    for(int k = 0; k < dir.length; k++){
                        if(isxmas(inp, i, j, dir[k])){
                            cnt++;
                        }
                    }
                } else if (inp[i][j] == 'A') {
                    if(iscross(inp, i, j)){
                        cnt2++;
                    }
                }
            }
        }

        System.out.println("Part 1: " + cnt);
        System.out.println("Part 2: " + cnt2);
    }

    public static Boolean isxmas(char[][] inp, int i, int j, int[] dir) {
        String goal = "XMAS";
        for (int k = 0; k < goal.length(); k++) {
            if (i + k * dir[0] >= inp.length || i + k * dir[0] < 0 || j + k * dir[1] >= inp[0].length || j + k * dir[1] < 0) {
            return false;
            }
            if (inp[i + k * dir[0]][j + k * dir[1]] != goal.charAt(k)) {
            return false;
            }
        }
        return true;
    }

    public static Boolean iscross(char[][] inp, int i, int j) {
        if(i == 0 || j == 0 || i == inp.length - 1 || j == inp[0].length - 1){
            return false;
        }
        if(((inp[i-1][j-1] == 'M' && inp[i+1][j+1] == 'S') || (inp[i-1][j-1] == 'S' && inp[i+1][j+1] == 'M'))
            && ((inp[i-1][j+1] == 'M' && inp[i+1][j-1] == 'S') || (inp[i-1][j+1] == 'S' && inp[i+1][j-1] == 'M'))){
            return true;
        }
        return false;
    }
}
