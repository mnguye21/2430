package lab1; 

import java.util.Scanner;
import java.util.StringTokenizer; 

public class Lab1 {

    public static void displayMenu(){

        System.out.println("(1) Enter a full sentence.\n" +
        "(2) Print out all sentences entered so far in the given order.\n" +
        "(3) Print out all sentences entered thus far in the reverse order.\n" +
        "(4) Print out the number of sentences that have been entered so far.\n" +
        "(5) Print out the number of characters in all sentences combined.\n" +
        "(6) Calculate the total number of vowels contained in all stored sentences.\n" +
        "(7) Perform search for a given word using casesensitive comparisons. \n" +
        "(8) Perform search for a given word using case insensitivec omparisons.\n" +
        "(9) End program");
    }

    public static boolean isVowel(char a){

        boolean vowel = false; 
        
        if (a == 'a'){
            
            vowel = true;
        } else if (a == 'e'){

            vowel = true;
        } else if (a == 'i'){
     
            vowel = true;
        } else if (a == 'o'){
    
            vowel = true;
        } else if (a == 'u'){
   
            vowel = true;
        } else if (a == 'A'){

            vowel = true;
        } else if (a == 'E'){
     
            vowel = true;
        } else if (a == 'I'){
    
            vowel = true;
        } else if (a == 'O'){
   
            vowel = true;
        } else if (a == 'U'){
   
            vowel = true;
        } 

        return vowel; 
    }
    
    public static void main (String[] args){

        Scanner scannerObj = new Scanner(System.in);
        String[] sentences = new String[10]; 
        int numSentences = 0;
        int numCharacters = 0; 
        int numVowels = 0;
        String userIn = ""; 
        StringTokenizer st; 

        displayMenu(); 
        System.out.println("Please enter a single digit to select an option from the menu: ");
        userIn = scannerObj.next(); 
        scannerObj.nextLine();     
        
        while (!userIn.equals("9")){ 

            if (userIn.equals("1")){
               
                System.out.println("Option 1 selected\n");

                if (numSentences < 10) {
                    
                    System.out.println("Enter the sentence: ");
                    userIn = scannerObj.nextLine();

                    sentences[numSentences++] = userIn; 
                    numCharacters += userIn.length(); 
                    
                    for (int i = 0; i < userIn.length(); i++){

                        if (isVowel(userIn.charAt(i))){
                            numVowels++;
                        }
                    }

                    System.out.println("Sentence entered. \n");
                } else {

                    System.out.println("Sentence array full. Cannot enter anymore sentences.\n");
                }

            } else if (userIn.equals("2")){

                System.out.println("Option 2 selected\n");

                for (int i = 0; i < numSentences; i++){

                    System.out.println(sentences[i] + "\n"); 
                }

            } else if (userIn.equals("3")){

                System.out.println("Option 3 selected\n");

                for (int i = numSentences - 1; i > -1; i--){

                    System.out.println(sentences[i] + "\n"); 
                }

            } else if (userIn.equals("4")){

                System.out.println("Option 4 selected\n");

                System.out.println("Number of sentences: " + numSentences + "\n");

            } else if (userIn.equals("5")){

                System.out.println("Option 5 selected\n");

                System.out.println("Number of characters: " + numCharacters + "\n");

            } else if (userIn.equals("6")){

                System.out.println("Option 6 selected\n");

                System.out.println("Number of vowels: " + numVowels + "\n");
                
            } else if (userIn.equals("7")){

                System.out.println("Option 7 selected\n");

                System.out.println("Enter the word you would like to search: ");
                userIn = scannerObj.nextLine();

                for (int i = 0; i < numSentences; i++){

                    st = new StringTokenizer(sentences[i], " ,.?!;-");

                    while(st.hasMoreTokens()){
                        if (userIn.equals(st.nextToken())){
                            System.out.println(sentences[i]);
                        }
                    }
                }

                
            } else if (userIn.equals("8")){

                System.out.println("Option 8 selected\n");

                System.out.println("Enter the word you would like to search: ");
                userIn = scannerObj.nextLine();

                for (int i = 0; i < numSentences; i++){

                    st = new StringTokenizer(sentences[i], " ,.?!;-");

                    while(st.hasMoreTokens()){
                        if (userIn.equalsIgnoreCase(st.nextToken())){
                            System.out.println(sentences[i]);
                        }
                    }
                }
                
            } else {

                System.out.println("Invalid input\n");
            }

            System.out.println("Action completed. To view the menu again, press enter.");
            userIn = scannerObj.nextLine();
            displayMenu(); 
            System.out.println("Please enter a single digit to select an option from the menu: ");

            userIn = scannerObj.next(); 
            scannerObj.nextLine(); 
        }
        
        scannerObj.close();
    }
}