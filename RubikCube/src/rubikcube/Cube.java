/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rubikcube;

/**
 *
 * @author wgranger
 */
public class Cube {
    private final static int YELLOW = 0;
    private final static int RED = 1;
    private final static int GREEN = 2;
    private final static int ORANGE = 3;
    private final static int BLUE = 4;
    private final static int WHITE = 5;
    private char[][] cubic = new char[6][9];

    private int FRONT;
    private int BACK;
    private int TOP;
    private int BOTTOM;
    private int LEFT;
    private int RIGHT;

    public Cube() {
        //parse string into Lists
        String CubeInText =
                "YYYYYYYYYRRRRRRRRRGGGGGGGGGOOOOOOOOOBBBBBBBBBWWWWWWWWW";
        char[] CubeInChar = CubeInText.toCharArray();
        int k = 0;
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 9; j++) {
                cubic[i][j] = CubeInChar[k];
                k++;
            }
        }
        TOP = 0;
        FRONT = 1;
        RIGHT = 2;
        BACK = 3;
        LEFT = 4;
        BOTTOM = 5;
    }
    public void reset() {
        String CubeInText =
                "YYYYYYYYYRRRRRRRRRGGGGGGGGGOOOOOOOOOBBBBBBBBBWWWWWWWWW";
        char[] CubeInChar = CubeInText.toCharArray();
        int k = 0;
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 9; j++) {
                cubic[i][j] = CubeInChar[k];
                k++;
            }
        }
        TOP = 0;
        FRONT = 1;
        RIGHT = 2;
        BACK = 3;
        LEFT = 4;
        BOTTOM = 5;
    }
    public void change(String CubeInText) {
        if (CubeInText.length() != 54) {
            return;
        } else {
            char[] CubeInChar = CubeInText.toCharArray();
            int k = 0;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 9; j++) {
                    cubic[i][j] = CubeInChar[k];
                    k++;
                }
            }
        }
        TOP = 0;
        FRONT = 1;
        RIGHT = 2;
        BACK = 3;
        LEFT = 4;
        BOTTOM = 5;
    }
    @Override
    public String toString() {
        String txt = new String(cubic[0]);
        for(int i = 1; i < 6; i++) {
            txt = txt + String.valueOf(cubic[i]);
        }
        return(txt);
    }
    public void print() {
        System.out.println(this.toString());
    }
    private void xcw() { //rotate clockwise about x-axis
        int temp = TOP;
        TOP = FRONT;
        FRONT = BOTTOM;
        BOTTOM = BACK;
        BACK = temp; //BACK = TOP
    }
    private void xccw() { //rotate counter-clockwise about x-axis
        int temp = TOP;
        TOP = BACK;
        BACK = BOTTOM;
        BOTTOM = FRONT;
        FRONT = temp; //FRONT = TOP
    }
    private void ycw() { //rotate clockwise about y-axis
        int temp = RIGHT;
        RIGHT = BACK;
        BACK = LEFT;
        LEFT = FRONT;
        FRONT = temp;
    }
    private void yccw() { //rotate counter-clockwise about y-axis
        int temp = RIGHT;
        RIGHT = FRONT;
        FRONT = LEFT;
        LEFT = BACK;
        BACK = temp;
    }
    private void zcw() { //rotate clockwise about z-axis
        int temp = TOP;
        TOP = LEFT;
        LEFT = BOTTOM;
        BOTTOM = RIGHT;
        RIGHT = temp;
    }
    private void zccw() { //rotate counter-clockwise about z-axis
        int temp = TOP;
        TOP = RIGHT;
        RIGHT = BOTTOM;
        BOTTOM = LEFT;
        LEFT = temp;
    }
    private void cw(int face) {
        char face0 = cubic[face][0];
        char face1 = cubic[face][1];
        char face2 = cubic[face][2];
        char face5 = cubic[face][5];
        cubic[face][0] = cubic[face][6];
        cubic[face][1] = cubic[face][3];
        cubic[face][2] = face0;
        cubic[face][3] = cubic[face][7];
        cubic[face][5] = face1;
        cubic[face][6] = cubic[face][8];
        cubic[face][7] = face5;
        cubic[face][8] = face2;
    }
    private void ccw(int face) {
        char face0 = cubic[face][0];
        char face1 = cubic[face][1];
        char face3 = cubic[face][3];
        char face6 = cubic[face][6];
        cubic[face][0] = cubic[face][2];
        cubic[face][1] = cubic[face][5];
        cubic[face][2] = cubic[face][8];
        cubic[face][3] = face1;
        cubic[face][5] = cubic[face][7];
        cubic[face][6] = face0;
        cubic[face][7] = face3;
        cubic[face][8] = face6;
    }
    private void fcw() { //rotate FRONT clockwise
        this.cw(FRONT);
        char top0 = cubic[TOP][0];
        char top3 = cubic[TOP][3];
        char top6 = cubic[TOP][6];
        cubic[TOP][0] = cubic[LEFT][8];
        cubic[TOP][3] = cubic[LEFT][5];
        cubic[TOP][6] = cubic[LEFT][2];
        cubic[LEFT][2] = cubic[BOTTOM][6];
        cubic[LEFT][5] = cubic[BOTTOM][3];
        cubic[LEFT][8] = cubic[BOTTOM][0];
        cubic[BOTTOM][6] = cubic[RIGHT][6];
        cubic[BOTTOM][3] = cubic[RIGHT][3];
        cubic[BOTTOM][0] = cubic[RIGHT][0];
        cubic[RIGHT][0] = top0;
        cubic[RIGHT][3] = top3;
        cubic[RIGHT][6] = top6;
    }
    private void fccw() { //rotate FRONT counter-clockwise
        this.ccw(FRONT);
        char top0 = cubic[TOP][0];
        char top3 = cubic[TOP][3];
        char top6 = cubic[TOP][6];
        cubic[TOP][0] = cubic[RIGHT][0];
        cubic[TOP][3] = cubic[RIGHT][3];
        cubic[TOP][6] = cubic[RIGHT][6];
        cubic[RIGHT][0] = cubic[BOTTOM][0];
        cubic[RIGHT][3] = cubic[BOTTOM][3];
        cubic[RIGHT][6] = cubic[BOTTOM][6];
        cubic[BOTTOM][6] = cubic[LEFT][2];
        cubic[BOTTOM][3] = cubic[LEFT][5];
        cubic[BOTTOM][0] = cubic[LEFT][8];
        cubic[LEFT][2] = top6;
        cubic[LEFT][5] = top3;
        cubic[LEFT][8] = top0;
    }
    private void lcw() {
        this.cw(LEFT);
        char top0 = cubic[TOP][0];
        char top1 = cubic[TOP][1];
        char top2 = cubic[TOP][2];
        cubic[TOP][0] = cubic[BACK][8];
        cubic[TOP][1] = cubic[BACK][5];
        cubic[TOP][2] = cubic[BACK][2];
        cubic[BACK][2] = cubic[BOTTOM][6];
        cubic[BACK][5] = cubic[BOTTOM][7];
        cubic[BACK][8] = cubic[BOTTOM][8];
        cubic[BOTTOM][6] = cubic[FRONT][6];
        cubic[BOTTOM][7] = cubic[FRONT][3];
        cubic[BOTTOM][8] = cubic[FRONT][0];
        cubic[FRONT][6] = top0;
        cubic[FRONT][3] = top1;
        cubic[FRONT][0] = top2;
    }
    private void lccw() {
        this.ccw(LEFT);
        char top0 = cubic[TOP][0];
        char top1 = cubic[TOP][1];
        char top2 = cubic[TOP][2];
        cubic[TOP][0] = cubic[FRONT][0];
        cubic[TOP][1] = cubic[FRONT][3];
        cubic[TOP][2] = cubic[FRONT][6];
        cubic[FRONT][0] = cubic[BOTTOM][8];
        cubic[FRONT][3] = cubic[BOTTOM][7];
        cubic[FRONT][6] = cubic[BOTTOM][6];
        cubic[BOTTOM][6] = cubic[BACK][2];
        cubic[BOTTOM][7] = cubic[BACK][5];
        cubic[BOTTOM][8] = cubic[BACK][8];
        cubic[BACK][2] = top2;
        cubic[BACK][5] = top1;
        cubic[BACK][8] = top0;
    }
    public void manipulate(String command) {
        if (command.equals("X")) {
            this.xcw();
        } else if (command.equals("X'")) {
            this.xccw();
        } else if (command.equals("Y")) {
            this.ycw();
        } else if (command.equals("Y'")) {
            this.yccw();
        } else if (command.equals("Z")) {
            this.zcw();
        } else if (command.equals("Z'")) {
            this.zccw();
        } else if (command.equals("U")) {
            //this.ucw();
        } else if (command.equals("U'")) {
            //this.uccw();
        } else if (command.equals("D")) {
            //this.dcw();
        } else if (command.equals("D'")) {
            //this.dccw();
        } else if (command.equals("F")) {
            this.fcw();
        } else if (command.equals("F'")) {
            this.fccw();
        } else if (command.equals("B")) {
            //this.bcw();
        } else if (command.equals("B'")) {
            //this.bccw();
        } else if (command.equals("R")) {
            //this.rcw();
        } else if (command.equals("R'")) {
            //this.rccw();
        } else if (command.equals("L")) {
            this.lcw();
        } else if (command.equals("L'")) {
            this.lccw();
        } else if (command.equals("RESET")) {
            this.reset();
        } else if (command.equals("OUTPUT")) {
            this.print();
        } else {
            this.change(command);
        }
    }
}
