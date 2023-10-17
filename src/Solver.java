import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Solver {
    


    public static void main(String[] args) {
        // Setup dummy print stream to disable println
        PrintStream originalStream = System.out;

        PrintStream dummyStream = new PrintStream(new OutputStream(){
            public void write(int b) { }
        });

        System.setOut(dummyStream);


        // Create a new cube and shuffle it
        Cube cube = new Cube();
        ArrayList<String> shuffleMoves = new ArrayList<String>();
        shuffleCube(cube, shuffleMoves);

        // Enable the original print stream
        System.setOut(originalStream);

        // Print cube, solve it
        System.out.println("Shuffle moves:\n" + shuffleMoves.toString());
        System.out.println("Initial cube state:");
        cube.printCube();
        solve(cube);

    }


    // Start with default cube (already solved), then interact with it
    private static void interactiveMode(Cube cube) {
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


    // Create a cube by using user input for colour of each cell
    private static Cube makeNewCube() {
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

        sc.close();
        return new Cube(inputColours);
    }

    private static Cube shuffleCube(Cube cube, ArrayList<String> movesMade) {
        Random rand = new Random();
        String[] possibleMoves = new String[] {
            "F",  "L",  "R",  "B",  "U",  "D",
            "F'", "L'", "R'", "B'", "U'", "D'",
            "F2", "L2", "R2", "B2", "U2", "D2"
        };

        // Make 50 random moves
        for (int i = 0; i < 25; i++) {
            int randInt = rand.nextInt(possibleMoves.length);
            cube.makeMove(possibleMoves[randInt]);
            movesMade.add(possibleMoves[randInt]);
        }

        return cube;
    }

    private static ArrayList<String> solve(Cube cube) {
        System.out.println("-------- Solving Cube --------");
        ArrayList<String> movesMade = new ArrayList<String>();

        System.out.println("--- Stage 1: White Cross ---");
        // Get the white center piece on top of cube
        if (cube.front.getCell(1, 1) == 'W') {
            cube.makeMove("X");
        }
        else if (cube.left.getCell(1, 1) == 'W') {
            cube.makeMove("Z");
        }
        else if (cube.right.getCell(1, 1) == 'W') {
            cube.makeMove("Z'");
        }
        else if (cube.back.getCell(1, 1) == 'W') {
            cube.makeMove("X'");
        }
        else if (cube.down.getCell(1, 1) == 'W') {
            cube.makeMove("X2");
        }

        
        // Get the red center piece on front of cube
        if (cube.left.getCell(1, 1) == 'R') {
            cube.makeMove("Y'");
        }
        else if (cube.right.getCell(1, 1) == 'R') {
            cube.makeMove("Y");
        }
        else if (cube.back.getCell(1, 1) == 'R') {
            cube.makeMove("Y2");
        }


        char[] sideColours = new char[] {'R', 'B', 'O', 'G'};

        // Move each white edge piece into position
        for (char colour : sideColours) {

            // Case 1: piece on bottom face
            if (cube.down.getCell(0, 1) == 'W' && cube.front.getCell(2, 1) == colour) {
                cube.makeMove("F2");
            }
            else if (cube.down.getCell(1, 0) == 'W' && cube.left.getCell(2, 1) == colour) {
                cube.makeMove("D");
                cube.makeMove("F2");
            }
            else if (cube.down.getCell(1, 2) == 'W' && cube.right.getCell(2, 1) == colour) {
                cube.makeMove("D'");
                cube.makeMove("F2");
            }
            else if (cube.down.getCell(2, 1) == 'W' && cube.back.getCell(2, 1) == colour) {
                cube.makeMove("D2");
                cube.makeMove("F2");
            }
            // Case 2: piece on bottom layer, facing outwards
            else if (cube.front.getCell(2, 1) == 'W' && cube.down.getCell(0, 1) == colour) {
                cube.makeMove("F");
                cube.makeMove("E");
                cube.makeMove("F'");
            }
            else if (cube.left.getCell(2, 1) == 'W' && cube.down.getCell(1, 0) == colour) {
                cube.makeMove("D");
                cube.makeMove("F");
                cube.makeMove("E");
                cube.makeMove("F'");
            }
            else if (cube.right.getCell(2, 1) == 'W' && cube.down.getCell(1, 2) == colour) {
                cube.makeMove("D'");
                cube.makeMove("F");
                cube.makeMove("E");
                cube.makeMove("F'");
            }
            else if (cube.back.getCell(2, 1) == 'W' && cube.down.getCell(2, 1) == colour) {
                cube.makeMove("D2");
                cube.makeMove("F");
                cube.makeMove("E");
                cube.makeMove("F'");
            }
            // Case 3: piece on middle layer
            else if (cube.front.getCell(1, 2) == 'W' && cube.right.getCell(1, 0) == colour) {
                cube.makeMove("E'");
                cube.makeMove("F");
            }
            else if (cube.left.getCell(1, 2) == 'W' && cube.front.getCell(1, 0) == colour) {
                cube.makeMove("F");
            }
            else if (cube.right.getCell(1, 2) == 'W' && cube.back.getCell(1, 0) == colour) {
                cube.makeMove("E2");
                cube.makeMove("F");
            }
            else if (cube.back.getCell(1, 2) == 'W' && cube.left.getCell(1, 0) == colour) {
                cube.makeMove("E");
                cube.makeMove("F");
            }
            else if (cube.front.getCell(1, 0) == 'W' && cube.left.getCell(1, 2) == colour) {
                cube.makeMove("E");
                cube.makeMove("F'");
            }
            else if (cube.left.getCell(1, 0) == 'W' && cube.back.getCell(1, 2) == colour) {
                cube.makeMove("E2");
                cube.makeMove("F'");
            }
            else if (cube.right.getCell(1, 0) == 'W' && cube.front.getCell(1, 2) == colour) {
                cube.makeMove("F'");
            }
            else if (cube.back.getCell(1, 0) == 'W' && cube.right.getCell(1, 2) == colour) {
                cube.makeMove("E'");
                cube.makeMove("F'");
            }
            // Case 4: piece on top layer, facing outwards
            else if (cube.front.getCell(0, 1) == 'W' && cube.up.getCell(2, 1) == colour) {
                cube.makeMove("F");
                cube.makeMove("E'");
                cube.makeMove("F");
            }
            else if (cube.left.getCell(0, 1) == 'W' && cube.up.getCell(1, 0) == colour) {
                cube.makeMove("L");
                cube.makeMove("F");
            }
            else if (cube.right.getCell(0, 1) == 'W' && cube.up.getCell(1, 2) == colour) {
                cube.makeMove("R'");
                cube.makeMove("F'");
            }
            else if (cube.back.getCell(0, 1) == 'W' && cube.up.getCell(0, 1) == colour) {
                cube.makeMove("B");
                cube.makeMove("E");
                cube.makeMove("F");
            }
            // Case 5: piece on top face
            else if (cube.up.getCell(1, 0) == 'W' && cube.left.getCell(0, 1) == colour) {
                // we can assume that this is the first colour (red), otherwise the piece couldn't be in this position
                cube.makeMove("U'");
            }
            else if (cube.up.getCell(1, 2) == 'W' && cube.right.getCell(0, 1) == colour) {
                cube.makeMove("R'");
                cube.makeMove("E'");
                cube.makeMove("F");
            }
            else if (cube.up.getCell(0, 1) == 'W' && cube.back.getCell(0, 1) == colour) {
                cube.makeMove("B");
                cube.makeMove("E2");
                cube.makeMove("F'");
            }

            cube.makeMove("Y");
        }

        // Line up edges with center pieces
        if (cube.left.getCell(1, 1) == 'R') {
            cube.makeMove("E");
        }
        else if (cube.right.getCell(1, 1) == 'R') {
            cube.makeMove("E'");
        }
        else if (cube.back.getCell(1, 1) == 'R') {
            cube.makeMove("E2");
        }

        cube.printCube();

        System.out.println("--- Stage 2: White Corners ---");

        String[] cornerCombinations = new String[] {"WGR", "WRB", "WBO", "WOG"};

        for (String cornerCombo : cornerCombinations) {
            
            // Case 1: in correct corner, wrong orientation
            if (cube.front.getCell(0, 0) == cornerCombo.charAt(0) && cube.up.getCell(2, 0) == cornerCombo.charAt(1) && cube.left.getCell(0, 2) == cornerCombo.charAt(2)) {
                cube.makeMove("F'");
                cube.makeMove("D'");
                cube.makeMove("F");
            }
            else if (cube.left.getCell(0, 2) == cornerCombo.charAt(0) && cube.front.getCell(0, 0) == cornerCombo.charAt(1) && cube.up.getCell(2, 0) == cornerCombo.charAt(2)) {
                cube.makeMove("L");
                cube.makeMove("D");
                cube.makeMove("L'");
            }
            // Case 2: bottom layer, wrong corner
            if (cornerCombo.indexOf(cube.back.getCell(2, 2)) >= 0 && cornerCombo.indexOf(cube.left.getCell(2, 0)) >= 0 && cornerCombo.indexOf(cube.down.getCell(2, 0)) >= 0) {
                cube.makeMove("D");
            }
            else if (cornerCombo.indexOf(cube.front.getCell(2, 2)) >= 0 && cornerCombo.indexOf(cube.right.getCell(2, 0)) >= 0 && cornerCombo.indexOf(cube.down.getCell(0, 2)) >= 0) {
                cube.makeMove("D'");
            }
            else if (cornerCombo.indexOf(cube.right.getCell(2, 2)) >= 0 && cornerCombo.indexOf(cube.back.getCell(2, 0)) >= 0 && cornerCombo.indexOf(cube.down.getCell(2, 2)) >= 0) {
                cube.makeMove("D2");
            }
            // Case 3: top layer, wrong corner
            else if (cornerCombo.indexOf(cube.up.getCell(0, 0)) >= 0 && cornerCombo.indexOf(cube.back.getCell(0, 2)) >= 0 && cornerCombo.indexOf(cube.left.getCell(0, 0)) >= 0) {
                cube.makeMove("B");
                cube.makeMove("D");
                cube.makeMove("B'");
            }
            else if (cornerCombo.indexOf(cube.up.getCell(2, 2)) >= 0 && cornerCombo.indexOf(cube.front.getCell(0, 2)) >= 0 && cornerCombo.indexOf(cube.right.getCell(0, 0)) >= 0) {
                cube.makeMove("R'");
                cube.makeMove("D'");
                cube.makeMove("R");
            }
            else if (cornerCombo.indexOf(cube.up.getCell(0, 2)) >= 0 && cornerCombo.indexOf(cube.right.getCell(0, 2)) >= 0 && cornerCombo.indexOf(cube.back.getCell(0, 0)) >= 0) {
                cube.makeMove("R");
                cube.makeMove("D2");
                cube.makeMove("R'");
            }
            // Case 4: bottom layer, under destination corner
            if (cube.left.getCell(2, 2) == cornerCombo.charAt(0) && cube.down.getCell(0, 0) == cornerCombo.charAt(1) && cube.front.getCell(2, 0) == cornerCombo.charAt(2)) {
                cube.makeMove("L");
                cube.makeMove("D");
                cube.makeMove("L'");
            }
            else if (cube.front.getCell(2, 0) == cornerCombo.charAt(0) && cube.left.getCell(2, 2) == cornerCombo.charAt(1) && cube.down.getCell(0, 0) == cornerCombo.charAt(2)) {
                cube.makeMove("F'");
                cube.makeMove("D'");
                cube.makeMove("F");
            }
            else if (cube.down.getCell(0, 0) == cornerCombo.charAt(0) && cube.front.getCell(2, 0) == cornerCombo.charAt(1) && cube.left.getCell(2, 2) == cornerCombo.charAt(2)) {
                cube.makeMove("L");
                cube.makeMove("B");
                cube.makeMove("D2");
                cube.makeMove("B'");
                cube.makeMove("L'");
            }

            cube.makeMove("Y");
        }

        cube.printCube();

        System.out.println("--- Stage 3: Second Layer ---");

        cube.makeMove("X2");
        cube.makeMove("Y2");

        String[] edgeCombinations = new String[] {"RB", "GR", "OG", "BO"};

        for (String edgeCombo : edgeCombinations) {
            
            // Check middle layer for edge piece
            if (cube.left.getCell(1, 2) == edgeCombo.charAt(0) && cube.front.getCell(1, 0) == edgeCombo.charAt(1)) {
                // wrong orientation - do left algorithm to get piece out of position
                cube.makeMove("U'");
                cube.makeMove("L'");
                cube.makeMove("U");
                cube.makeMove("L");
                cube.makeMove("U");
                cube.makeMove("F");
                cube.makeMove("U'");
                cube.makeMove("F'");
            }
            else if (edgeCombo.indexOf(cube.front.getCell(1, 2)) >= 0 && edgeCombo.indexOf(cube.right.getCell(1, 0)) >= 0) {
                cube.makeMove("U'");
                cube.makeMove("F'");
                cube.makeMove("U");
                cube.makeMove("F");
                cube.makeMove("U");
                cube.makeMove("R");
                cube.makeMove("U'");
                cube.makeMove("R'");
            }
            else if (edgeCombo.indexOf(cube.right.getCell(1, 2)) >= 0 && edgeCombo.indexOf(cube.back.getCell(1, 0)) >= 0) {
                cube.makeMove("U'");
                cube.makeMove("R'");
                cube.makeMove("U");
                cube.makeMove("R");
                cube.makeMove("U");
                cube.makeMove("B");
                cube.makeMove("U'");
                cube.makeMove("B'");
            }
            else if (edgeCombo.indexOf(cube.back.getCell(1, 2)) >= 0 && edgeCombo.indexOf(cube.left.getCell(1, 0)) >= 0) {
                cube.makeMove("U'");
                cube.makeMove("B'");
                cube.makeMove("U");
                cube.makeMove("B");
                cube.makeMove("U");
                cube.makeMove("L");
                cube.makeMove("U'");
                cube.makeMove("L'");
            }
            
            // Move edge piece into correct position on top layer
            if ((cube.left.getCell(0, 1) == edgeCombo.charAt(0) && cube.up.getCell(1, 0) == edgeCombo.charAt(1)) || (cube.back.getCell(0, 1) == edgeCombo.charAt(1) && cube.up.getCell(0, 1) == edgeCombo.charAt(0))) {
                cube.makeMove("U'");
            }
            else if ((cube.right.getCell(0, 1) == edgeCombo.charAt(0) && cube.up.getCell(1, 2) == edgeCombo.charAt(1)) || (cube.front.getCell(0, 1) == edgeCombo.charAt(1) && cube.up.getCell(2, 1) == edgeCombo.charAt(0))) {
                cube.makeMove("U");
            }
            else if ((cube.back.getCell(0, 1) == edgeCombo.charAt(0) && cube.up.getCell(0, 1) == edgeCombo.charAt(1)) || (cube.right.getCell(0, 1) == edgeCombo.charAt(1) && cube.up.getCell(1, 2) == edgeCombo.charAt(0))) {
                cube.makeMove("U2");
            }

            // Depending on orientation of piece on top layer, do either left/right algorithm
            if (cube.front.getCell(0, 1) == edgeCombo.charAt(0) && cube.up.getCell(2, 1) == edgeCombo.charAt(1)) {
                cube.makeMove("U'");
                cube.makeMove("L'");
                cube.makeMove("U");
                cube.makeMove("L");
                cube.makeMove("U");
                cube.makeMove("F");
                cube.makeMove("U'");
                cube.makeMove("F'");
            }
            else if (cube.left.getCell(0, 1) == edgeCombo.charAt(1) && cube.up.getCell(1, 0) == edgeCombo.charAt(0)) {
                cube.makeMove("U");
                cube.makeMove("F");
                cube.makeMove("U'");
                cube.makeMove("F'");
                cube.makeMove("U'");
                cube.makeMove("L'");
                cube.makeMove("U");
                cube.makeMove("L");
            }

            cube.makeMove("Y");

        }

        cube.printCube();

        System.out.println("--- Stage 4: Yellow Cross ---");

        if (cube.up.getCell(0, 1) != 'Y' && cube.up.getCell(1, 0) != 'Y' && cube.up.getCell(1, 2) != 'Y' && cube.up.getCell(2, 1) != 'Y') {
            // scenario 1: yellow dot
            cube.makeMove("F");
            cube.makeMove("U");
            cube.makeMove("R");
            cube.makeMove("U'");
            cube.makeMove("R'");
            cube.makeMove("F'");
        }
        if (cube.up.getCell(0, 1) == 'Y' && cube.up.getCell(2, 1) == 'Y') {
            // scenario 2: yellow line (vertical)
            cube.makeMove("Y");
        }
        if (cube.up.getCell(1, 0) == 'Y' && cube.up.getCell(1, 2) == 'Y') {
            // scenario 2: yellow line (horizontal)
            cube.makeMove("F");
            cube.makeMove("R");
            cube.makeMove("U");
            cube.makeMove("R'");
            cube.makeMove("U'");
            cube.makeMove("F'");
        }

        if (!(cube.up.getCell(0, 1) == 'Y' && cube.up.getCell(1, 0) == 'Y' && cube.up.getCell(1, 2) == 'Y' && cube.up.getCell(2, 1) == 'Y')) {
            // scenario 3: yellow L - orient into correct position first
            if (cube.up.getCell(0, 1) == 'Y' && cube.up.getCell(1, 2) == 'Y') {
                cube.makeMove("Y'");
            }
            else if (cube.up.getCell(1, 0) == 'Y' && cube.up.getCell(2, 1) == 'Y') {
                cube.makeMove("Y");
            }
            else if (cube.up.getCell(1, 2) == 'Y' && cube.up.getCell(2, 1) == 'Y') {
                cube.makeMove("Y2");
            }

            cube.makeMove("F");
            cube.makeMove("U");
            cube.makeMove("R");
            cube.makeMove("U'");
            cube.makeMove("R'");
            cube.makeMove("F'");
        }
        
        cube.printCube();

        return movesMade;
    }
}