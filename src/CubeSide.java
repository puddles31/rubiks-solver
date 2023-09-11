public class CubeSide {
    private char[][] face;
    private CubeSide[] adjacentSides;


    public CubeSide(char[] cells) {
        if (cells.length != 9) {
            throw new IllegalArgumentException("The cells array must have 9 elements.");
        }

        face = new char[3][3];
        adjacentSides = new CubeSide[4];

        for (int i = 0; i < 9; i++) {
            face[i / 3][i % 3] = cells[i];
        }
    }

    public void setAdjacentSides(CubeSide[] sides) {
        if (sides.length != 4) {
            throw new IllegalArgumentException("The sides array must have 4 elements.");
        }

        adjacentSides = sides;
    }


    public void rotateClockwise() {
        char[][] faceCopy = new char[3][3];
        for (int i = 0; i < 3; i++) {
            faceCopy[i] = face[i].clone();
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face[j][2-i] = faceCopy[i][j];
            }
        }


        char[] saveNew = new char[3];
        char[] saveOld = adjacentSides[3].getRow(0);
        int sideNo;


        // TODO: fix this
        // only works when rotating front
        // note that perspective is important! the closest row to the side might be the furthest row overall
        for (int i = 0; i < adjacentSides.length; i++) {
            if (i < 2) {
                sideNo = 2;
            }
            else {
                sideNo = 0;
            }

            if (i % 2 == 0) {
                saveNew = adjacentSides[i].getCol(sideNo);
                System.out.println("Setting column " + sideNo + " in side " + i + " from [" + saveNew[0] + ", " + saveNew[1] + ", " + saveNew[2] + "] to [" + saveOld[0] + ", " + saveOld[1] + ", " + saveOld[2] + "]");
                adjacentSides[i].setCol(sideNo, saveOld);
                saveOld = saveNew.clone();
            }
            else {
                saveNew = adjacentSides[i].getRow(sideNo);
                System.out.println("Setting row " + sideNo + " in side " + i + " from [" + saveNew[0] + ", " + saveNew[1] + ", " + saveNew[2] + "] to [" + saveOld[2] + ", " + saveOld[1] + ", " + saveOld[0] + "]");
                adjacentSides[i].setRow(sideNo, reverse(saveOld));
                saveOld = saveNew.clone();
            }
        }
    }

    public void rotateCounterClockwise() {
        char[][] faceCopy = new char[3][3];
        for (int i = 0; i < 3; i++) {
            faceCopy[i] = face[i].clone();
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                face[2-j][i] = faceCopy[i][j];
            }
        }
    }


    private char[] reverse(char[] arr) {
        return new char[] {arr[2], arr[1], arr[0]};
    }


    public char[] getRow(int rowNo) {
        return face[rowNo].clone();
    }

    public void setRow(int rowNo, char[] newRow) {
        if (newRow.length != 3) {
            throw new IllegalArgumentException("The newRow array must have 3 elements.");
        }

        face[rowNo] = newRow.clone();
    }


    public char[] getCol(int colNo) {
        return new char[] {face[0][colNo], face[1][colNo], face[2][colNo]};
    }

    public void setCol(int colNo, char[] newCol) {
        if (newCol.length != 3) {
            throw new IllegalArgumentException("The newCol array must have 3 elements.");
        }

        for (int i = 0; i < 3; i++) {
            face[i][colNo] = newCol[i];
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
