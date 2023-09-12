import java.util.Scanner;

public class Solver {
    
    public static void main(String[] args) {
        
        Cube cube = new Cube();

        Scanner sc = new Scanner(System.in);
        String input = "";

        while (!input.equals("X")) {
            System.out.println("Enter a move:");
            input = sc.nextLine();

            if (!input.equals("X")) {
                cube.makeMove(input);
            }
        }

        sc.close();
    }
}