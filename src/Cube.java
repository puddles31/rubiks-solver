import java.util.Arrays;

public class Cube {

    private CubeSide front, left, right, back, up, down;

    public Cube() {
        char[] cells = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
        
        Arrays.fill(cells, 'F');
        front = new CubeSide(cells);

        Arrays.fill(cells, 'L');
        left = new CubeSide(cells);

        Arrays.fill(cells, 'R');
        right = new CubeSide(cells);

        Arrays.fill(cells, 'B');
        back = new CubeSide(cells);

        Arrays.fill(cells, 'U');
        up = new CubeSide(cells);

        Arrays.fill(cells, 'D');
        down = new CubeSide(cells);
        
        front.setAdjacentSides(new CubeSide[]{left, up, right, down});
        left.setAdjacentSides(new CubeSide[]{back, up, front, down});
        right.setAdjacentSides(new CubeSide[]{front, up, back, down});
        back.setAdjacentSides(new CubeSide[]{right, up, left, down});
        up.setAdjacentSides(new CubeSide[]{left, back, right, front});
        down.setAdjacentSides(new CubeSide[]{left, front, right, back});

        print();

        left.rotateClockwise();
        // back.rotateCounterClockwise();
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
