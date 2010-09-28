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


}
