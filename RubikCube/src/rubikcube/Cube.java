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
	//The order of color numbers must be the same as required print order,
	//which is YRGOBW
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
        this.reset(); // Blaine - Code reuse
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
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 9; j++) {
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
      //System.out.println("Face: " + face + " Dir: " + dir );
      
      if( dir.equals("cw") )
      {
        char t2 = cubic[face][1];
        char t3 = cubic[face][2];
        cubic[face][2] = cubic[face][0];
        cubic[face][1] = cubic[face][3];
        cubic[face][0] = cubic[face][6];
        cubic[face][3] = cubic[face][7];
        cubic[face][6] = cubic[face][8];
        cubic[face][7] = cubic[face][5];
        cubic[face][8] = t3;
        cubic[face][5] = t2;
      }
      else if( dir.equals("ccw") )
      {
        char t1 = cubic[face][0];
        char t2 = cubic[face][1];
        cubic[face][0] = cubic[face][2];
        cubic[face][1] = cubic[face][5];
        cubic[face][2] = cubic[face][8];
        cubic[face][5] = cubic[face][7];
        cubic[face][8] = cubic[face][6];
        cubic[face][7] = cubic[face][3];
        cubic[face][6] = t1;
        cubic[face][3] = t2;
      }
    }
    
    private void rotate( int face, String dir )
    {
      this.rotateFace( face, dir ); // This calls the rotate face method
      
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
    	
    	char t1 = cubic[BLUE][0];
        char t2 = cubic[BLUE][1];
        char t3 = cubic[BLUE][2];
    	
    	if( dir.equals("cw") )
        {
          cubic[BLUE][0] = cubic[RED][0];
          cubic[BLUE][1] = cubic[RED][1];
          cubic[BLUE][2] = cubic[RED][2];
          cubic[RED][0] = cubic[GREEN][0];
          cubic[RED][1] = cubic[GREEN][1];
          cubic[RED][2] = cubic[GREEN][2];
          cubic[GREEN][0] = cubic[ORANGE][0];
          cubic[GREEN][1] = cubic[ORANGE][1];
          cubic[GREEN][2] = cubic[ORANGE][2];
          cubic[ORANGE][0] = t1;
          cubic[ORANGE][1] = t2;
          cubic[ORANGE][2] = t3;
        }
        else if( dir.equals("ccw") )
        {
          cubic[BLUE][2] = cubic[ORANGE][2];
          cubic[BLUE][1] = cubic[ORANGE][1];
          cubic[BLUE][0] = cubic[ORANGE][0];
          cubic[ORANGE][2] = cubic[GREEN][2];
          cubic[ORANGE][1] = cubic[GREEN][1];
          cubic[ORANGE][0] = cubic[GREEN][0];
          cubic[GREEN][2] = cubic[RED][2];
          cubic[GREEN][1] = cubic[RED][1];
          cubic[GREEN][0] = cubic[RED][0];
          cubic[RED][2] = t3;
          cubic[RED][1] = t2;
          cubic[RED][0] = t1;
        }
    }
    
    private void rotateRed( String dir )
    {
    	//System.out.println("Rotate Red " + dir );
    	
    	char t1 = cubic[YELLOW][0];
        char t4 = cubic[YELLOW][3];
        char t7 = cubic[YELLOW][6];
    	
    	if( dir.equals("cw") )
        {
    		cubic[YELLOW][6] = cubic[BLUE][2];
    		cubic[YELLOW][3] = cubic[BLUE][5];
    		cubic[YELLOW][0] = cubic[BLUE][8];
    		cubic[BLUE][2] = cubic[WHITE][6];
    		cubic[BLUE][5] = cubic[WHITE][3];
    		cubic[BLUE][8] = cubic[WHITE][0];
    		cubic[WHITE][6] = cubic[GREEN][6];
    		cubic[WHITE][3] = cubic[GREEN][3];
    		cubic[WHITE][0] = cubic[GREEN][0];
    		cubic[GREEN][6] = t7;
    		cubic[GREEN][3] = t4;
    		cubic[GREEN][0] = t1;
        }
        else if( dir.equals("ccw") )
        {
        	cubic[YELLOW][0] = cubic[GREEN][0];
        	cubic[YELLOW][3] = cubic[GREEN][3];
        	cubic[YELLOW][6] = cubic[GREEN][6];
        	cubic[GREEN][0] = cubic[WHITE][0];
        	cubic[GREEN][3] = cubic[WHITE][3];
        	cubic[GREEN][6] = cubic[WHITE][6];
        	cubic[WHITE][0] = cubic[BLUE][8];
        	cubic[WHITE][3] = cubic[BLUE][5];
        	cubic[WHITE][6] = cubic[BLUE][2];
        	cubic[BLUE][8] = t1;
        	cubic[BLUE][5] = t4;
        	cubic[BLUE][2] = t7;
        }
    }
    
    private void rotateGreen( String dir )
    {
    	//System.out.println("Rotate Green " + dir );
    	
    	char t7 = cubic[YELLOW][6];
        char t8 = cubic[YELLOW][7];
        char t9 = cubic[YELLOW][8];
    	
    	if( dir.equals("cw") )
        {
    		cubic[YELLOW][8] = cubic[RED][2];
    		cubic[YELLOW][7] = cubic[RED][5];
    		cubic[YELLOW][6] = cubic[RED][8];
    		cubic[RED][2] = cubic[WHITE][0];
    		cubic[RED][5] = cubic[WHITE][1];
    		cubic[RED][8] = cubic[WHITE][2];
    		cubic[WHITE][0] = cubic[ORANGE][6];
    		cubic[WHITE][1] = cubic[ORANGE][3];
    		cubic[WHITE][2] = cubic[ORANGE][0];
    		cubic[ORANGE][6] = t9;
    		cubic[ORANGE][3] = t8;
    		cubic[ORANGE][0] = t7;
        }
        else if( dir.equals("ccw") )
        {
        	cubic[YELLOW][6] = cubic[ORANGE][0];
        	cubic[YELLOW][7] = cubic[ORANGE][3];
        	cubic[YELLOW][8] = cubic[ORANGE][6];
        	cubic[ORANGE][0] = cubic[WHITE][2];
        	cubic[ORANGE][3] = cubic[WHITE][1];
        	cubic[ORANGE][6] = cubic[WHITE][0];
        	cubic[WHITE][2] = cubic[RED][8];
        	cubic[WHITE][1] = cubic[RED][5];
        	cubic[WHITE][0] = cubic[RED][2];
        	cubic[RED][8] = t7;
        	cubic[RED][5] = t8;
        	cubic[RED][2] = t9;
        }
    }
    
    private void rotateOrange( String dir )
    {
    	//System.out.println("Rotate Orange " + dir );
    	
    	char t3 = cubic[YELLOW][2];
        char t6 = cubic[YELLOW][5];
        char t9 = cubic[YELLOW][8];
    	
    	if( dir.equals("cw") )
        {
    		cubic[YELLOW][2] = cubic[GREEN][2];
    		cubic[YELLOW][5] = cubic[GREEN][5];
    		cubic[YELLOW][8] = cubic[GREEN][8];
    		cubic[GREEN][2] = cubic[WHITE][2];
    		cubic[GREEN][5] = cubic[WHITE][5];
    		cubic[GREEN][8] = cubic[WHITE][8];
    		cubic[WHITE][2] = cubic[BLUE][6];
    		cubic[WHITE][5] = cubic[BLUE][3];
    		cubic[WHITE][8] = cubic[BLUE][0];
    		cubic[BLUE][6] = t3;
    		cubic[BLUE][3] = t6;
    		cubic[BLUE][0] = t9;
        }
        else if( dir.equals("ccw") )
        {
        	cubic[YELLOW][8] = cubic[BLUE][0];
        	cubic[YELLOW][5] = cubic[BLUE][3];
        	cubic[YELLOW][2] = cubic[BLUE][6];
        	cubic[BLUE][0] = cubic[WHITE][8];
        	cubic[BLUE][3] = cubic[WHITE][5];
        	cubic[BLUE][6] = cubic[WHITE][2];
        	cubic[WHITE][8] = cubic[GREEN][8];
        	cubic[WHITE][5] = cubic[GREEN][5];
        	cubic[WHITE][2] = cubic[GREEN][2];
        	cubic[GREEN][8] = t9;
        	cubic[GREEN][5] = t6;
        	cubic[GREEN][2] = t3;
        }
    }
    
    private void rotateBlue( String dir )
    {
    	//System.out.println("Rotate Blue " + dir );
    	
    	char t1 = cubic[YELLOW][0];
        char t2 = cubic[YELLOW][1];
        char t3 = cubic[YELLOW][2];
    	
    	if( dir.equals("cw") )
        {
    		cubic[YELLOW][0] = cubic[ORANGE][2];
    		cubic[YELLOW][1] = cubic[ORANGE][5];
    		cubic[YELLOW][2] = cubic[ORANGE][8];
    		cubic[ORANGE][2] = cubic[WHITE][8];
    		cubic[ORANGE][5] = cubic[WHITE][7];
    		cubic[ORANGE][8] = cubic[WHITE][6];
    		cubic[WHITE][8] = cubic[RED][6];
    		cubic[WHITE][7] = cubic[RED][3];
    		cubic[WHITE][6] = cubic[RED][0];
    		cubic[RED][6] = t1;
    		cubic[RED][3] = t2;
    		cubic[RED][0] = t3;
        }
        else if( dir.equals("ccw") )
        {
        	cubic[YELLOW][2] = cubic[RED][0];
        	cubic[YELLOW][1] = cubic[RED][3];
        	cubic[YELLOW][0] = cubic[RED][6];
        	cubic[RED][0] = cubic[WHITE][6];
        	cubic[RED][3] = cubic[WHITE][7];
        	cubic[RED][6] = cubic[WHITE][8];
        	cubic[WHITE][6] = cubic[ORANGE][8];
        	cubic[WHITE][7] = cubic[ORANGE][5];
        	cubic[WHITE][8] = cubic[ORANGE][2];
        	cubic[ORANGE][8] = t3;
        	cubic[ORANGE][5] = t2;
        	cubic[ORANGE][2] = t1;
        }
    }
    
    private void rotateWhite( String dir )
    {
    	//System.out.println("Rotate White " + dir );
    	
    	char t7 = cubic[GREEN][6];
        char t8 = cubic[GREEN][7];
        char t9 = cubic[GREEN][8];
    	
    	if( dir.equals("cw") )
        {
    		cubic[GREEN][8] = cubic[RED][8];
    		cubic[GREEN][7] = cubic[RED][7];
    		cubic[GREEN][6] = cubic[RED][6];
    		cubic[RED][8] = cubic[BLUE][8];
    		cubic[RED][7] = cubic[BLUE][7];
    		cubic[RED][6] = cubic[BLUE][6];
    		cubic[BLUE][8] = cubic[ORANGE][8];
    		cubic[BLUE][7] = cubic[ORANGE][7];
    		cubic[BLUE][6] = cubic[ORANGE][6];
    		cubic[ORANGE][8] = t9;
    		cubic[ORANGE][7] = t8;
    		cubic[ORANGE][6] = t7;
        }
        else if( dir.equals("ccw") )
        {
        	cubic[GREEN][6] = cubic[ORANGE][6];
        	cubic[GREEN][7] = cubic[ORANGE][7];
        	cubic[GREEN][8] = cubic[ORANGE][8];
        	cubic[ORANGE][6] = cubic[BLUE][6];
        	cubic[ORANGE][7] = cubic[BLUE][7];
        	cubic[ORANGE][8] = cubic[BLUE][8];
        	cubic[BLUE][6] = cubic[RED][6];
        	cubic[BLUE][7] = cubic[RED][7];
        	cubic[BLUE][8] = cubic[RED][8];
        	cubic[RED][6] = t7;
        	cubic[RED][7] = t8;
        	cubic[RED][8] = t9;
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