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
                //System.out.println(strLine);
            	rubik.manipulate(strLine);
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
            //e.printStackTrace();
        }

    }
}
