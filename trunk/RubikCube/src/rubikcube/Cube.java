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
    private int UP;
    private int DOWN;
    private int LEFT;
    private int RIGET;

    public Cube(String CubeInText) {
        //parse string into Lists
        char[] CubeInChar = CubeInText.toCharArray();
        int k = 0;
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 9; j++) {
                cubic[i][j] = CubeInChar[k];
                k++;
            }
        }
    }

    public String toText(Cube c) {
        return null;
    }
    public void print() {
        //Write to file
    }


}
