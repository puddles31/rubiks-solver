import java.util.Arrays;

public class Cube {

    private CubeSide front, left, right, back, up, down;

    private static final EdgeRule[] F_EDGE_RULES = new EdgeRule[] {new EdgeRule('C', 2, false), new EdgeRule('R', 2, true), new EdgeRule('C', 0, false), new EdgeRule('R', 0, true)};
    private static final EdgeRule[] L_EDGE_RULES = new EdgeRule[] {new EdgeRule('C', 2, true), new EdgeRule('C', 0, true), new EdgeRule('C', 0, false), new EdgeRule('C', 0, false)};
    private static final EdgeRule[] R_EDGE_RULES = new EdgeRule[] {new EdgeRule('C', 2, false), new EdgeRule('C', 2, false), new EdgeRule('C', 0, true), new EdgeRule('C', 2, true)};
    private static final EdgeRule[] B_EDGE_RULES = new EdgeRule[] {new EdgeRule('C', 2, true), new EdgeRule('R', 0, false), new EdgeRule('C', 0, true), new EdgeRule('R', 2, false)};
    private static final EdgeRule[] U_EDGE_RULES = new EdgeRule[] {new EdgeRule('R', 0, false), new EdgeRule('R', 0, false), new EdgeRule('R', 0, false), new EdgeRule('R', 0, false)};
    private static final EdgeRule[] D_EDGE_RULES = new EdgeRule[] {new EdgeRule('R', 2, false), new EdgeRule('R', 2, false), new EdgeRule('R', 2, false), new EdgeRule('R', 2, false)};

    public Cube() {
        char[] cells = new char[9];
        
        Arrays.fill(cells, 'R');
        front = new CubeSide(cells);

        Arrays.fill(cells, 'G');
        left = new CubeSide(cells);

        Arrays.fill(cells, 'B');
        right = new CubeSide(cells);

        Arrays.fill(cells, 'O');
        back = new CubeSide(cells);

        Arrays.fill(cells, 'W');
        up = new CubeSide(cells);

        Arrays.fill(cells, 'Y');
        down = new CubeSide(cells);
        
        front.setAdjacentSides(new CubeSide[]{left, up, right, down});
        left.setAdjacentSides(new CubeSide[]{back, up, front, down});
        right.setAdjacentSides(new CubeSide[]{front, up, back, down});
        back.setAdjacentSides(new CubeSide[]{right, up, left, down});
        up.setAdjacentSides(new CubeSide[]{left, back, right, front});
        down.setAdjacentSides(new CubeSide[]{left, front, right, back});

        printCube();
    }

    public void printCube() {
        up.printSide(7);
        
        for (int i = 0; i < 3; i++) {
            left.printRow(i);
            front.printRow(i);
            right.printRow(i);
            back.printRow(i);
            System.out.println();
        }
        
        down.printSide(7);
    }


    public void makeMove(String move) {
        switch (move) {
            case "F":
                front.rotateClockwise(F_EDGE_RULES);
                break;
            
            case "L":
                left.rotateClockwise(L_EDGE_RULES);
                break;
            
            case "R":
                right.rotateClockwise(R_EDGE_RULES);
                break;
            
            case "B":
                back.rotateClockwise(B_EDGE_RULES);
                break;
            
            case "U":
                up.rotateClockwise(U_EDGE_RULES);
                break;
            
            case "D":
                down.rotateClockwise(D_EDGE_RULES);
                break;
            
        }

        printCube();
    }
}
