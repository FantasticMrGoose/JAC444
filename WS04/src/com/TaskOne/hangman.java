
package com.TaskOne;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


public class hangman {
	
	private static String word;
	public static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException{
		
		// Stores the list of words from hangman.txt
		ArrayList<String> wordList = new ArrayList<String>();
		
		Boolean keepPlaying = false;
		
		// Load word list into array
		try { 
			BufferedReader buffRead = new BufferedReader(
					new FileReader("hangman.txt")
					);
			
			String s; 
			while ((s = buffRead.readLine()) != null) { 
				wordList.add(s);
			} 
			buffRead.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}

		do {
			playGame(wordList);
			addWord();
			keepPlaying = keepPlaying();
			
		} while (keepPlaying);
		
		System.out.println("Thanks for playing!");
		
		input.close();

	}
	
	public static void playGame(ArrayList<String> wordList) {
		
		String hiddenWord = "";
		String used = "";
		String letter = "";
		Boolean win = false;
		int missed = 0;
		
		// select a random word using index
		int index = new Random().nextInt(wordList.size() - 1);
		word = wordList.get(index);
		
		for(int i = 0; i < word.length(); i++) {
			hiddenWord += "*";
		}
		
		while(win != true) {
			System.out.print("(Guess) Enter a letter in word " + hiddenWord + "> ");
			letter = input.next();
			
			Boolean added = false;
			
			if(word.contains(letter) && letter.length() == 1) {
				
				// checks if the letter is added already
				if(hiddenWord.contains(letter)) {
					System.out.println("The letter '" + letter + "' is already in the word");
					added = true;
				}
				
				// replaces the asterisks with the correctly guessed letter
				for(int i = 0; i < word.length(); i++) {
					if(word.charAt(i) == letter.charAt(0) && !added) {
						hiddenWord = hiddenWord.substring(0, i) + letter + hiddenWord.substring(i + 1);
					}
				}
			}
			else if(used.contains(letter)) {
				System.out.println("You have already tried the letter '" + letter + "', try a new letter");
			}
			else if(letter.length() != 1) {
				System.out.println("Please enter only one letter at a time");
			}
			else {
				missed += 1;
				used += letter;
				System.out.println("The letter '" + letter + "' is not in the word");
			}
	
			if(hiddenWord.equals(word)) {
				win = true;
				System.out.println("Congratulations, the word is'" + word + "'. You missed " + missed + " times");
			}
			System.out.println("-------------------------------------------------------");
		}
	}
	
	public static void addWord() throws IOException {
		String newWord = "";
		System.out.print("Enter a new word to be added in the memory > ");
		newWord = input.next();
		
		try {
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter("hangman.txt", true));
			buffWrite.write("\n" + newWord);
			buffWrite.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Boolean keepPlaying() {
		
		String ans = "";
		
		Boolean keepPlaying = true;
		Boolean keepGoing = true;
		
		do {
			// records if triangle is filled
			System.out.print("Do you want to guess another word? enter y or n > " );
			ans = input.next();
			
			if(ans.equals("Y") || ans.equals("y")) {
				keepPlaying = true;
				keepGoing = false;
			}
			else if(ans.equals("N") || ans.equals("n")) {
				keepPlaying = false;
				keepGoing = false;
			}
			else {
				System.out.println("Please enter 'y' or 'n'");
			}
		} while (keepGoing);

		return keepPlaying;
	}
	
}
	

	
	
	
	
	

