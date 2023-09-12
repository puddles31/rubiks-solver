import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cube {

    private CubeSide front, left, right, back, up, down;

    private static final EdgeRule[] F_EDGE_RULES = new EdgeRule[] {new EdgeRule('C', 2, false), new EdgeRule('R', 2, true), new EdgeRule('C', 0, false), new EdgeRule('R', 0, true)};
    private static final EdgeRule[] L_EDGE_RULES = new EdgeRule[] {new EdgeRule('C', 2, true), new EdgeRule('C', 0, true), new EdgeRule('C', 0, false), new EdgeRule('C', 0, false)};
    private static final EdgeRule[] R_EDGE_RULES = new EdgeRule[] {new EdgeRule('C', 2, false), new EdgeRule('C', 2, false), new EdgeRule('C', 0, true), new EdgeRule('C', 2, true)};
    private static final EdgeRule[] B_EDGE_RULES = new EdgeRule[] {new EdgeRule('C', 2, true), new EdgeRule('R', 0, false), new EdgeRule('C', 0, true), new EdgeRule('R', 2, false)};
    private static final EdgeRule[] U_EDGE_RULES = new EdgeRule[] {new EdgeRule('R', 0, false), new EdgeRule('R', 0, false), new EdgeRule('R', 0, false), new EdgeRule('R', 0, false)};
    private static final EdgeRule[] D_EDGE_RULES = new EdgeRule[] {new EdgeRule('R', 2, false), new EdgeRule('R', 2, false), new EdgeRule('R', 2, false), new EdgeRule('R', 2, false)};

    private Pattern inputPattern = Pattern.compile("^([FLRBUD])(['2])?$");
    private Matcher inputMatcher;

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


    public void makeMove(String input) {
        CubeSide selectedSide = null;
        EdgeRule[] selectedEdgeRules = null;

        inputMatcher = inputPattern.matcher(input);

        if (!inputMatcher.matches()) {
            throw new IllegalArgumentException("Invalid move syntax.");
        }

        switch (inputMatcher.group(1)) {
            case "F":
                selectedSide = front;
                selectedEdgeRules = F_EDGE_RULES;
                break;

            case "L":
                selectedSide = left;
                selectedEdgeRules = L_EDGE_RULES;
                break;

            case "R":
                selectedSide = right;
                selectedEdgeRules = R_EDGE_RULES;
                break;
            
            case "B":
                selectedSide = back;
                selectedEdgeRules = B_EDGE_RULES;
                break;

            case "U":
                selectedSide = up;
                selectedEdgeRules = U_EDGE_RULES;
                break;
            
            case "D":
                selectedSide = down;
                selectedEdgeRules = D_EDGE_RULES;
                break;
        }

        if (inputMatcher.group(2) == null) {
            selectedSide.rotateClockwise(selectedEdgeRules);
        }
        else switch (inputMatcher.group(2)) {
            case "2":
                selectedSide.rotateClockwise(selectedEdgeRules);
                selectedSide.rotateClockwise(selectedEdgeRules);
                break;
            
            case "'":
                selectedSide.rotateCounterClockwise(selectedEdgeRules);
                break;
        }

        printCube();
    }
}
