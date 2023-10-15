import java.util.Scanner;

public class Solver {
    


    public static void main(String[] args) {
        // newCubeMode();
        interactiveMode();
    }


    // Start with default cube (already solved), then interact with it
    private static void interactiveMode() {
        Cube cube = new Cube();

        Scanner sc = new Scanner(System.in);
        String input = "";

        while (!input.equals("QUIT")) {
            System.out.println("Enter a move (or 'QUIT' to quit):");
            input = sc.nextLine().toUpperCase();

            if (!input.equals("QUIT")) {
                cube.makeMove(input);
            }
        }

        sc.close();
    }


    // First ask user to create a cube by entering colours of each cell, then interact with it
    private static void newCubeMode() {
        char[] inputColours = new char[54];

        Scanner sc = new Scanner(System.in);
        String input = "";

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                input = "";

                while (!input.equals("W") && !input.equals("G") && !input.equals("R") && !input.equals("B") && !input.equals("O") && !input.equals("Y")) {
                    System.out.println("Enter the colour of cell " + j + " on face " + (i + 1) + ":");
                    input = sc.nextLine().toUpperCase();
                }
                
                inputColours[(9 * i) + j] = input.charAt(0);
            }
        }

        Cube cube = new Cube(inputColours);

        while (!input.equals("QUIT")) {
            System.out.println("Enter a move (or 'QUIT' to quit):");
            input = sc.nextLine().toUpperCase();

            if (!input.equals("QUIT")) {
                cube.makeMove(input);
            }
        }

        sc.close();
    }
}