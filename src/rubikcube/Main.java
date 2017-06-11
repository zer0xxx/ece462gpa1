/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rubikcube;

import java.util.Scanner;
import java.io.File;

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
      //System.err.println("Usage: <file>");
      //System.exit(0);
      if(args.length != 0){
        System.err.println("Usage error");
        System.exit(0);
      }
      Cube rubik = new Cube();
      Scanner keyboard = new Scanner(System.in);
      String command = null; 
      command = keyboard.nextLine();
      //assume the user will always type in correct commands or "exit" to exit.
      while(!command.equals("exit")){
        rubik.manipulate(command);
        command = keyboard.nextLine();
      }
    }else{
      Cube rubik = new Cube();
      Scanner inputStream = null;
      String command = null;
      try {
        inputStream = new Scanner(new File(args[0]));
      }catch (Exception e) {//Catch exception if any
        System.err.println("Error: " + e.getMessage());
        System.exit(0);
      }
      while(inputStream.hasNextLine()){
        command = inputStream.nextLine();
        rubik.manipulate(command);
      } 
      inputStream.close();
    }
  }
}