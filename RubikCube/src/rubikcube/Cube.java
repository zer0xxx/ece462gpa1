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
        this.reset(); // Blaine - Code reuse
    }
    public void reset() {
        String CubeInText =
                "YYYYYYYYYRRRRRRRRRGGGGGGGGGOOOOOOOOOBBBBBBBBBWWWWWWWWW";
        char[] CubeInChar = CubeInText.toCharArray();
        int k = 0;
        for(int i = 0; i < 6; i++) {
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
    
    private void rotateFace( int face, String dir )
    {
      if( dir.equals("cw") )
      {
        char t2 = cubic[face][2];
        char t3 = cubic[face][3];
        cubic[face][3] = cubic[face][1];
        cubic[face][2] = cubic[face][4];
        cubic[face][1] = cubic[face][7];
        cubic[face][4] = cubic[face][8];
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
    
    private void rotate( int face, String dir )
    {
      this.rotateFace( face, dir ); // This calls the rotate face method
      
      rotateFace(face,dir);
      
      switch( face )
      {
        case YELLOW:
          rotateYellow(dir);
          break;
        case RED:
          rotateRed(dir);
          break;
        case GREEN:
          rotateGreen(dir);
          break;
        case ORANGE:
          rotateOrange(dir);
          break;
        case BLUE:
          rotateBlue(dir);
          break;
        case WHITE:
          rotateWhite(dir);
          break;
      }
      
    }
    
    private void rotateYellow( String dir )
    {
    	//System.out.println("Rotate Yellow " + dir );
    	
    	char t1 = cubic[BLUE][1];
        char t2 = cubic[BLUE][2];
        char t3 = cubic[BLUE][3];
    	
    	if( dir.equals("cw") )
        {
          cubic[BLUE][1] = cubic[RED][1];
          cubic[BLUE][2] = cubic[RED][2];
          cubic[BLUE][3] = cubic[RED][3];
          cubic[RED][1] = cubic[GREEN][1];
          cubic[RED][2] = cubic[GREEN][2];
          cubic[RED][3] = cubic[GREEN][3];
          cubic[GREEN][1] = cubic[ORANGE][1];
          cubic[GREEN][2] = cubic[ORANGE][2];
          cubic[GREEN][3] = cubic[ORANGE][3];
          cubic[ORANGE][1] = t1;
          cubic[ORANGE][2] = t2;
          cubic[ORANGE][3] = t3;
        }
        else if( dir.equals("ccw") )
        {
          cubic[BLUE][3] = cubic[ORANGE][3];
          cubic[BLUE][2] = cubic[ORANGE][2];
          cubic[BLUE][1] = cubic[ORANGE][1];
          cubic[ORANGE][3] = cubic[GREEN][3];
          cubic[ORANGE][2] = cubic[GREEN][2];
          cubic[ORANGE][1] = cubic[GREEN][1];
          cubic[GREEN][3] = cubic[RED][3];
          cubic[GREEN][2] = cubic[RED][2];
          cubic[GREEN][1] = cubic[RED][1];
          cubic[RED][3] = t3;
          cubic[RED][2] = t2;
          cubic[RED][1] = t1;
        }
    }
    
    private void rotateRed( String dir )
    {
    	//System.out.println("Rotate Red " + dir );
    	
    	char t1 = cubic[YELLOW][1];
        char t4 = cubic[YELLOW][4];
        char t7 = cubic[YELLOW][7];
    	
    	if( dir.equals("cw") )
        {
    		cubic[YELLOW][7] = cubic[BLUE][3];
    		cubic[YELLOW][4] = cubic[BLUE][6];
    		cubic[YELLOW][1] = cubic[BLUE][9];
    		cubic[BLUE][3] = cubic[WHITE][7];
    		cubic[BLUE][6] = cubic[WHITE][4];
    		cubic[BLUE][9] = cubic[WHITE][1];
    		cubic[WHITE][7] = cubic[GREEN][7];
    		cubic[WHITE][4] = cubic[GREEN][4];
    		cubic[WHITE][1] = cubic[GREEN][1];
    		cubic[GREEN][7] = t7;
    		cubic[GREEN][4] = t4;
    		cubic[GREEN][1] = t1;
        }
        else if( dir.equals("ccw") )
        {
        	cubic[YELLOW][1] = cubic[GREEN][1];
        	cubic[YELLOW][4] = cubic[GREEN][4];
        	cubic[YELLOW][7] = cubic[GREEN][7];
        	cubic[GREEN][1] = cubic[WHITE][1];
        	cubic[GREEN][4] = cubic[WHITE][4];
        	cubic[GREEN][7] = cubic[WHITE][7];
        	cubic[WHITE][1] = cubic[BLUE][9];
        	cubic[WHITE][4] = cubic[BLUE][6];
        	cubic[WHITE][7] = cubic[BLUE][3];
        	cubic[BLUE][9] = t1;
        	cubic[BLUE][6] = t4;
        	cubic[BLUE][3] = t7;
        }
    }
    
    private void rotateGreen( String dir )
    {
    	//System.out.println("Rotate Green " + dir );
    	
    	char t7 = cubic[YELLOW][7];
        char t8 = cubic[YELLOW][8];
        char t9 = cubic[YELLOW][9];
    	
    	if( dir.equals("cw") )
        {
    		cubic[YELLOW][9] = cubic[RED][3];
    		cubic[YELLOW][8] = cubic[RED][6];
    		cubic[YELLOW][7] = cubic[RED][9];
    		cubic[RED][3] = cubic[WHITE][1];
    		cubic[RED][6] = cubic[WHITE][2];
    		cubic[RED][9] = cubic[WHITE][3];
    		cubic[WHITE][1] = cubic[ORANGE][7];
    		cubic[WHITE][2] = cubic[ORANGE][4];
    		cubic[WHITE][3] = cubic[ORANGE][1];
    		cubic[ORANGE][7] = t9;
    		cubic[ORANGE][4] = t8;
    		cubic[ORANGE][1] = t7;
        }
        else if( dir.equals("ccw") )
        {
        	cubic[YELLOW][7] = cubic[ORANGE][1];
        	cubic[YELLOW][8] = cubic[ORANGE][4];
        	cubic[YELLOW][9] = cubic[ORANGE][7];
        	cubic[ORANGE][1] = cubic[WHITE][3];
        	cubic[ORANGE][4] = cubic[WHITE][2];
        	cubic[ORANGE][7] = cubic[WHITE][1];
        	cubic[WHITE][3] = cubic[RED][9];
        	cubic[WHITE][2] = cubic[RED][6];
        	cubic[WHITE][1] = cubic[RED][3];
        	cubic[RED][9] = t7;
        	cubic[RED][6] = t8;
        	cubic[RED][3] = t9;
        }
    }
    
    private void rotateOrange( String dir )
    {
    	//System.out.println("Rotate Orange " + dir );
    	
    	char t3 = cubic[YELLOW][3];
        char t6 = cubic[YELLOW][6];
        char t9 = cubic[YELLOW][9];
    	
    	if( dir.equals("cw") )
        {
    		cubic[YELLOW][3] = cubic[GREEN][3];
    		cubic[YELLOW][6] = cubic[GREEN][6];
    		cubic[YELLOW][9] = cubic[GREEN][9];
    		cubic[GREEN][3] = cubic[WHITE][3];
    		cubic[GREEN][6] = cubic[WHITE][6];
    		cubic[GREEN][9] = cubic[WHITE][9];
    		cubic[WHITE][3] = cubic[BLUE][7];
    		cubic[WHITE][6] = cubic[BLUE][4];
    		cubic[WHITE][9] = cubic[BLUE][1];
    		cubic[BLUE][7] = t3;
    		cubic[BLUE][4] = t6;
    		cubic[BLUE][1] = t9;
        }
        else if( dir.equals("ccw") )
        {
        	cubic[YELLOW][9] = cubic[BLUE][1];
        	cubic[YELLOW][6] = cubic[BLUE][4];
        	cubic[YELLOW][3] = cubic[BLUE][7];
        	cubic[BLUE][1] = cubic[WHITE][9];
        	cubic[BLUE][4] = cubic[WHITE][6];
        	cubic[BLUE][7] = cubic[WHITE][3];
        	cubic[WHITE][9] = cubic[GREEN][9];
        	cubic[WHITE][6] = cubic[GREEN][6];
        	cubic[WHITE][3] = cubic[GREEN][3];
        	cubic[GREEN][9] = t9;
        	cubic[GREEN][6] = t6;
        	cubic[GREEN][3] = t3;
        }
    }
    
    private void rotateBlue( String dir )
    {
    	//System.out.println("Rotate Blue " + dir );
    	
    	char t1 = cubic[YELLOW][1];
        char t2 = cubic[YELLOW][2];
        char t3 = cubic[YELLOW][3];
    	
    	if( dir.equals("cw") )
        {
    		cubic[YELLOW][1] = cubic[ORANGE][3];
    		cubic[YELLOW][2] = cubic[ORANGE][6];
    		cubic[YELLOW][3] = cubic[ORANGE][9];
    		cubic[ORANGE][3] = cubic[WHITE][9];
    		cubic[ORANGE][6] = cubic[WHITE][8];
    		cubic[ORANGE][9] = cubic[WHITE][7];
    		cubic[WHITE][9] = cubic[RED][7];
    		cubic[WHITE][8] = cubic[RED][4];
    		cubic[WHITE][7] = cubic[RED][1];
    		cubic[RED][7] = t1;
    		cubic[RED][4] = t2;
    		cubic[RED][1] = t3;
        }
        else if( dir.equals("ccw") )
        {
        	cubic[YELLOW][3] = cubic[RED][1];
        	cubic[YELLOW][2] = cubic[RED][4];
        	cubic[YELLOW][1] = cubic[RED][7];
        	cubic[RED][1] = cubic[WHITE][7];
        	cubic[RED][4] = cubic[WHITE][8];
        	cubic[RED][7] = cubic[WHITE][9];
        	cubic[WHITE][7] = cubic[ORANGE][9];
        	cubic[WHITE][8] = cubic[ORANGE][6];
        	cubic[WHITE][9] = cubic[ORANGE][3];
        	cubic[ORANGE][9] = t3;
        	cubic[ORANGE][6] = t2;
        	cubic[ORANGE][3] = t1;
        }
    }
    
    private void rotateWhite( String dir )
    {
    	//System.out.println("Rotate White " + dir );
    	
    	char t7 = cubic[GREEN][7];
        char t8 = cubic[GREEN][8];
        char t9 = cubic[GREEN][9];
    	
    	if( dir.equals("cw") )
        {
    		cubic[GREEN][9] = cubic[RED][9];
    		cubic[GREEN][8] = cubic[RED][8];
    		cubic[GREEN][7] = cubic[RED][7];
    		cubic[RED][9] = cubic[BLUE][9];
    		cubic[RED][8] = cubic[BLUE][8];
    		cubic[RED][7] = cubic[BLUE][7];
    		cubic[BLUE][9] = cubic[ORANGE][9];
    		cubic[BLUE][8] = cubic[ORANGE][8];
    		cubic[BLUE][7] = cubic[ORANGE][7];
    		cubic[ORANGE][9] = t9;
    		cubic[ORANGE][8] = t8;
    		cubic[ORANGE][7] = t7;
        }
        else if( dir.equals("ccw") )
        {
        	cubic[GREEN][7] = cubic[ORANGE][7];
        	cubic[GREEN][8] = cubic[ORANGE][8];
        	cubic[GREEN][9] = cubic[ORANGE][9];
        	cubic[ORANGE][7] = cubic[BLUE][7];
        	cubic[ORANGE][8] = cubic[BLUE][8];
        	cubic[ORANGE][9] = cubic[BLUE][9];
        	cubic[BLUE][7] = cubic[RED][7];
        	cubic[BLUE][8] = cubic[RED][8];
        	cubic[BLUE][9] = cubic[RED][9];
        	cubic[RED][7] = t7;
        	cubic[RED][8] = t8;
        	cubic[RED][9] = t9;
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
            rotate(BOTTOM,"6ccw");
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
