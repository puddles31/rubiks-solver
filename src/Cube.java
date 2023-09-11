public class Cube {

    private CubeSide front, left, right, back, up, down;

    public Cube() {
        char[] cells = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};

        front = new CubeSide(cells);
        left = new CubeSide(cells);
        right = new CubeSide(cells);
        back = new CubeSide(cells);
        up = new CubeSide(cells);
        down = new CubeSide(cells);
        
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
