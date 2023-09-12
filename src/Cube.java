import java.util.Arrays;

public class Cube {

    private CubeSide front, left, right, back, up, down;

    public Cube() {
        char[] cells = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
        
        // Arrays.fill(cells, 'F');
        front = new CubeSide(cells);

        // Arrays.fill(cells, 'L');
        for (int i = 0; i < cells.length; i++) {
            cells[i] += 9;
        }
        left = new CubeSide(cells);

        // Arrays.fill(cells, 'R');
        for (int i = 0; i < cells.length; i++) {
            cells[i] += 9;
        }
        right = new CubeSide(cells);

        // Arrays.fill(cells, 'B');
        for (int i = 0; i < cells.length; i++) {
            cells[i] += 9;
        }
        back = new CubeSide(cells);

        // Arrays.fill(cells, 'U');
        for (int i = 0; i < cells.length; i++) {
            cells[i] += 9;
        }
        up = new CubeSide(cells);

        // Arrays.fill(cells, 'D');
        for (int i = 0; i < cells.length; i++) {
            cells[i] += 9;
        }
        down = new CubeSide(cells);
        
        front.setAdjacentSides(new CubeSide[]{left, up, right, down});
        left.setAdjacentSides(new CubeSide[]{back, up, front, down});
        right.setAdjacentSides(new CubeSide[]{front, up, back, down});
        back.setAdjacentSides(new CubeSide[]{right, up, left, down});
        up.setAdjacentSides(new CubeSide[]{left, back, right, front});
        down.setAdjacentSides(new CubeSide[]{left, front, right, back});

        print();

        // front.rotateClockwise(new EdgeRule[] {new EdgeRule('C', 2, false), new EdgeRule('R', 2, true), new EdgeRule('C', 0, false), new EdgeRule('R', 0, true)});
        // left.rotateClockwise(new EdgeRule[] {new EdgeRule('C', 2, true), new EdgeRule('C', 0, true), new EdgeRule('C', 0, false), new EdgeRule('C', 0, false)});
        // right.rotateClockwise(new EdgeRule[] {new EdgeRule('C', 2, false), new EdgeRule('C', 2, false), new EdgeRule('C', 0, true), new EdgeRule('C', 2, true)});
        // back.rotateClockwise(new EdgeRule[] {new EdgeRule('C', 2, true), new EdgeRule('R', 0, false), new EdgeRule('C', 0, true), new EdgeRule('R', 2, false)});
        // up.rotateClockwise(new EdgeRule[] {new EdgeRule('R', 0, false), new EdgeRule('R', 0, false), new EdgeRule('R', 0, false), new EdgeRule('R', 0, false)});
        // down.rotateClockwise(new EdgeRule[] {new EdgeRule('R', 2, false), new EdgeRule('R', 2, false), new EdgeRule('R', 2, false), new EdgeRule('R', 2, false)});
    }

    public void print() {
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
}
