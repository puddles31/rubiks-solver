import java.util.Scanner;

public class Solver {
    


    public static void main(String[] args) {
        newCubeMode();
    }


    private static void interactiveMode() {
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


    private static void newCubeMode() {
        char[] inputColours = new char[54];

        Scanner sc = new Scanner(System.in);
        String input = "";

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                input = "";

                while (!input.equals("W") && !input.equals("G") && !input.equals("R") && !input.equals("B") && !input.equals("O") && !input.equals("Y")) {
                    System.out.println("Enter the colour of cell " + j + " on face " + (i + 1) + ":");
                    input = sc.nextLine();
                }
                
                inputColours[(9 * i) + j] = input.charAt(0);
            }
        }

        Cube cube = new Cube(inputColours);

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