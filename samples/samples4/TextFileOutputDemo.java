/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class TextFileOutputDemo {

    public static void main(String[] args) {
        PrintWriter outputStream = null;
        try {
           outputStream
                    = new PrintWriter(new FileOutputStream("stuff.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the file stuff.txt.");
            System.exit(0);
        }

        System.out.println("Writing to file.");

        outputStream.println("The quick brown fox");
        outputStream.println("jumped over the lazy dog.");

        outputStream.close();

        System.out.println("End of program.");
    }
}

