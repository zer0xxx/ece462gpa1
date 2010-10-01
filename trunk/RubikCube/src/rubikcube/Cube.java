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
    private char[][] cubic = new char[6][10]; // Blaine - 1 unused location cubic[color][0] 
                                              // but allows addressing as cubic[color][1-9]
                                              // corresponding directly to cube model we have.
                                              // Effectively makes it easier to visualize
                                              // the array exactly as the paper cube appears.

    private int FRONT;
    private int BACK;
    private int TOP;
    private int BOTTOM;
    private int LEFT;
    private int RIGHT;

    public Cube() {
        reset(); // Blaine - Code reuse
    }
    public void reset() {
        String CubeInText =
                "YYYYYYYYYRRRRRRRRRGGGGGGGGGOOOOOOOOOBBBBBBBBBWWWWWWWWW";
        char[] CubeInChar = CubeInText.toCharArray();
        int k = 0;
        for(int i = 1; i <= 6; i++) {
            for(int j = 1; j <= 9; j++) {
                cubic[i][j] = CubeInChar[k];
                k++;
            }
        }
        TOP = YELLOW;
        FRONT = RED;
        RIGHT = GREEN;
        BACK = ORANGE;
        LEFT = BLUE;
        BOTTOM = WHITE;
    }
    public void change(String CubeInText) {
        if (CubeInText.length() != 54) {
            return;
        } else {
            char[] CubeInChar = CubeInText.toCharArray();
            int k = 0;
            for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 9; j++) {
                    cubic[i][j] = CubeInChar[k];
                    k++;
                }
            }
        }
        TOP = YELLOW;
        FRONT = RED;
        RIGHT = GREEN;
        BACK = ORANGE;
        LEFT = BLUE;
        BOTTOM = WHITE;
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
    
    // Blaine - Yes, I deleted the code you had so far.  The rotate by face works correctly,
    // but the rotations on the bordering cube regions fall apart once the entire cube is
    // rotated.  It is impossible to generalize the cube rotations without complex algorithms
    // or a plethora of case statements to rotate based upon "front", "right", etc.
    // as the constraint.  The easiest way will be to rotate based upon side color because
    // every single time, the border of any given side will be the same.  This means that 
    // multiple cases for each "side" will not have to be created.  I will take charge of 
    // generating the algoritm for the cube rotations.  I appreciate the initiative, however.
    // I don't mean to come off as an ass.  The face rotations were great.  I rewrote them as
    // practice and in the format I chose, but we can alter some things later if needed.
    
    private void rotateFace( int face, String dir )
    {
      if( dir.equals("cw") )
      {
        char t2 = cubic[face][2];
        char t3 = cubic[face][3];
        cubic[face][3] = cubic[face][1];
        cubic[face][2] = cubic[face][4];
        cubic[face][1] = cubic[face][7];
        cuibc[face][4] = cubic[face][8];
        cubic[face][7] = cubic[face][9];
        cubic[face][8] = cubic[face][6];
        cubic[face][9] = t3;
        cubic[face][6] = t2; 
      }
      else if( dir.equals("ccw") )
      {
        char t1 = cubic[face][1];
        char t2 = cubic[face][2];
        cubic[face][1] = cubic[face][3];
        cubic[face][2] = cubic[face][6];
        cubic[face][3] = cubic[face][9];
        cubic[face][6] = cubic[face][8];
        cubic[face][9] = cubic[face][7];
        cubic[face][8] = cubic[face][4];
        cubic[face][7] = t1;
        cubic[face][4] = t2;
      }
    }
    
    private void rotate( int face, String dir );
    {
      rotate( face, dir ); // This calls the rotate face method
      
      switch( face )
      {
        case YELLOW:
          System.out.println("Rotate Yellow " + dir );
          break;
        case RED:
          System.out.println("Rotate Red " + dir );
          break;
        case GREEN:
          System.out.println("Rotate Green " + dir );
          break;
        case ORANGE:
          System.out.println("Rotate Orange " + dir );
          break;
        case BLUE:
          System.out.println("Rotate Blue " + dir );
          break;
        case WHITE:
          System.out.println("Rotate White " + dir );
          break;
      }
      
    }
    
    
    public void manipulate(String command) {
        if (command.equals("X")) {
            xcw();
        } else if (command.equals("X'")) {
            xccw();
        } else if (command.equals("Y")) {
            ycw();
        } else if (command.equals("Y'")) {
            yccw();
        } else if (command.equals("Z")) {
            zcw();
        } else if (command.equals("Z'")) {
            zccw();
        } else if (command.equals("U")) {
            rotate(TOP,"cw");
        } else if (command.equals("U'")) {
            rotate(TOP,"ccw");
        } else if (command.equals("D")) {
            rotate(BOTTOM,"cw");
        } else if (command.equals("D'")) {
            rotate(BOTTOM,"ccw");
        } else if (command.equals("F")) {
            rotate(FRONT,"cw");
        } else if (command.equals("F'")) {
            rotate(FRONT,"ccw");
        } else if (command.equals("B")) {
            rotate(BACK,"cw");
        } else if (command.equals("B'")) {
            rotate(BACK,"ccw");
        } else if (command.equals("R")) {
            rotate(RIGHT,"cw");
        } else if (command.equals("R'")) {
            rotate(RIGHT,"ccw");
        } else if (command.equals("L")) {
            rotate(LEFT,"cw");
        } else if (command.equals("L'")) {
            rotate(LEFT,"ccw");
        } else if (command.equals("RESET")) {
            reset();
        } else if (command.equals("OUTPUT")) {
            print();
        } else {
            change(command);
        }
    }
}
