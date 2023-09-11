public class CubeSide {
    private char[][] face;

    public CubeSide(char[] cells) {
        if (cells.length != 9) {
            throw new IllegalArgumentException("The cells array must have 9 elements.");
        }

        face = new char[3][3];

        for (int i = 0; i < 9; i++) {
            face[i / 3][i % 3] = cells[i];
        }
    }

    public void printRow(int rowNo) {
        System.out.print(face[rowNo][0] + " " + face[rowNo][1] + " " + face[rowNo][2] + "  ");
    }

    public void printSide(int spacing) {
        System.out.println();

        for (int i = 0; i < 3; i++) {
            for (int x = 0; x < spacing; x++) {
                System.out.print(" ");
            }
            printRow(i);
            System.out.println();
        }
        System.out.println();
    }
}
