/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rubikcube;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 *
 * @author wgranger
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: <file>");
            System.exit(0);
        }
        try {
            // Open the file that is the first 
            // command line parameter
            FileInputStream fstream = new FileInputStream(args[0]);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            Cube rubik = new Cube();
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                if(strLine.equals("X")) {
                    //Call the X function
                } else if(strLine.equals("X'")) {
                    //Call the X' function
                } else if(strLine.equals("Y")) {
                    //call the Y function
                } else if(strLine.equals("Y'")) {
                    //call the Y' function
                } else if(strLine.equals("Z")) {
                    //call the Z function
                } else if(strLine.equals("Z'")) {
                    //call the Z' function
                } else if(strLine.equals("U")) {
                    //turn the top face
                } else if(strLine.equals("U'")) {
                    //turn the top face
                } else if(strLine.equals("D")) {
                    //turn the bottom face
                } else if(strLine.equals("D'")) {
                    //turn the bottom face
                } else if(strLine.equals("F")) {
                    //turn the front face
                } else if(strLine.equals("F'")) {
                    //turn the front face
                } else if(strLine.equals("B")) {
                    //turn the back face
                } else if(strLine.equals("B'")) {
                    //turn the back face
                } else if(strLine.equals("R")) {
                    //turn the right face
                } else if(strLine.equals("R'")) {
                    //turn the right face
                } else if(strLine.equals("L")) {
                    //turn the left face
                } else if(strLine.equals("L'")) {
                    //turn the left face
                } else if(strLine.equals("RESET")) {
                    rubik.reset();
                } else if(strLine.equals("OUTPUT")) {
                    rubik.print();
                } else {
                    rubik.change(strLine);
                }
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

    }
}
