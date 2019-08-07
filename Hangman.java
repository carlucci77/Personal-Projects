import java.io.*;
import java.util.*;
public class Hangman {
	public static String choose(File f) throws FileNotFoundException {
		String result = null;
		Random rand = new Random();
		int n = 0;
		for(Scanner sc = new Scanner(f); sc.hasNext(); ){
			++n;
			String line = sc.nextLine();
			if(rand.nextInt(n) == 0) {
				result = line;    
			}
		}
		return result;      
	}
	public static void hangman(String word) {
		Scanner scanner = new Scanner(System.in);
		char[] guessed = new char[26];
		char[] guessed2 = new char[26];
		int track = 0;
		char l = 'a';
		for(int k = 0; k < guessed.length; k++) {
			guessed[k] = l;
			l++;
		}
		l = 'a';
		for(int k = 0; k < guessed2.length; k++) {
			guessed2[k] = l;
			l++;
		}
		char[] temp = new char[word.length()];
		for(int k = 0; k < temp.length; k++) {
			if(word.charAt(k) == ' ') {
				temp[k] = ' ';
			}else if((word.charAt(k) < 'a' || word.charAt(k) > 'z') && (word.charAt(k) < 'A' || word.charAt(k) > 'Z')){
				temp[k] = word.charAt(k);
			}else {
				temp[k] = '-';
			}
		}
		String letters = "";
		char letter = ' ';
		boolean hanged = false;
		int hang = 0;
		while(!hanged) {
			boolean check2 = false;
			for(int k = 0; k < temp.length; k++) {
				if(k == temp.length - 1) {
					System.out.println(temp[k]);
					break;
				}else {
					System.out.print(temp[k]);
				}
			}
			switch(hang) {
			case 0:
				System.out.println("Your man is perfectly healthy right now. Keep it that way!");
				break;
			case 1:
				System.out.println("Your man's head is now hanging. You have five chances left. Don't kill him!");
				System.out.println("  O  ");
				break;
			case 2:
				System.out.println("Your man's head and body are hanging from the gallows now!"
						+ " You have four chances left. Don't kill him!");
				System.out.println("  O  ");
				System.out.println("  |  ");
				System.out.println("  |  ");
				break;
			case 3:
				System.out.println("Your man's left arm is hanging with his head and body. "
						+ "You have three chances left. Don't kill him!");
				System.out.println("  O  ");
				System.out.println(" /|  ");
				System.out.println("  |  ");
				break;
			case 4:
				System.out.println("He's looking pretty bad right about now. I'd hate to have both of my arms hang too."
						+ "You have two chances left. Don't kill him!");
				System.out.println("  O  ");
				System.out.println(" /|\\");
				System.out.println("  |  ");
				break;
			case 5:
				System.out.println("And there goes his left leg. You only have one chance left. "
						+ "If you mess up again, your man hangs! Don't kill him!");
				System.out.println("  O  ");
				System.out.println(" /|\\ ");
				System.out.println("  |  ");
				System.out.println(" /   ");
				break;
			}
			System.out.println("Letter Bank: ");
			System.out.print(" ");
			for(int k = 0; k < 9; k++) {
				System.out.print("_");
			}
			System.out.println();
			boolean trackCheck = false;
			for(int k = 0; k < guessed.length; k++) {
				if(guessed[k] == '0') {
					if(trackCheck) {
						System.out.println();
						track = 0;
						trackCheck = false;
					}
					if(track == 0) {
						System.out.print("|");
					}
					System.out.print(guessed2[k]);
					track++;
					if(track != 5) {
						System.out.print(" ");
					}
				}
				if(track == 5) {
					System.out.print("|");
					track = 0;
					trackCheck = true;
				}
			}
			track = 0;
			System.out.println();
			System.out.print(" ");
			for(int k = 0; k < 9; k++) {
				System.out.print("¯");
			}
			System.out.println();
			while(true) {
				boolean check = false;
				if(!check2) {
					System.out.println("Please input a letter: ");
					check2 = true;
				}
				letters = scanner.nextLine();
				if(letters.length() > 1) {
					System.out.println("Please enter only one letter.");
					continue;	
				}else if(letters.length() < 1){
					System.out.println("Please enter a letter.");
					continue;
				}else {
					letter = letters.charAt(0);
					if(letter < 'a' || letter > 'z') {
						if(letter >= 'A' && letter <= 'Z') {
							System.out.println("Please enter a non-capital letter.");
							continue;
						}else {
							System.out.println("Please enter a letter.");
							continue;
						}
					}
				}
				for(int k = 0; k < guessed.length; k++) {
					if(guessed[k] == letter) {
						guessed[k] = '0';
						check = true;
					}
				}
				if(check) {
					break;
				}else {
					System.out.println("You have already guessed this letter. Please guess again.");
				}
			}
			boolean correct = false;
			int counter = 0;
			for(int k = 0; k < word.length(); k++) {
				if(word.charAt(k) == letter) {
					temp[k] = letter;
					counter++;
					correct = true;
				}else if(word.toLowerCase().charAt(k) == letter) {
					temp[k] = word.charAt(k);
					counter++;
					correct = true;
				}
			}
			if(correct) {
				if(counter == 1) {
					System.out.println("There was one \"" + letter + "\" in the sentence.");
				}else{
					System.out.println("There were " + counter + " \"" + letter + "\"'s in the sentence.");
				}	
				boolean continued = false;
				for(int k = 0; k < temp.length; k++) {
					if(temp[k] == '-') {
						continued = true;
						break;
					}
				}
				if(!continued) {
					System.out.println("Congratulations, Partner! You have solved the riddle! "
							+ "This man will not be hanging today! The word or sentence was \"" + word + "\".");
					return;
				}else {
					continue;
				}
			}else {
				System.out.println("Blast it! That letter is not in this sentence, Partner! Another body part must hang!");
				hang++;
				if(hang == 6) {
					hanged = true;
					break;
				}
			}
		}
		if(hanged) {
			System.out.println("You've yeed your last haw and lost. Your man has hanged. "
					+ "Better luck next time. The correct word or sentence was \"" + word + "\".");
			System.out.println("  O  ");
			System.out.println(" /|\\ ");
			System.out.println("  |  ");
			System.out.println(" / \\ ");
		}
	}
	public static void main(String[] args) throws FileNotFoundException{
		Scanner scanner = new Scanner(System.in);
		int option = 0;
		String word = "";
		while(true) {
			System.out.println("Welcome to Hangman! What would you like to do?");
			System.out.println("[1] Play Custom Game");
			System.out.println("[2] Play Randomized Game");
			System.out.println("[3] Rules");
			System.out.println("[4] Quit");
			while(true) {
				option = scanner.nextInt();
				word = scanner.nextLine();
				if(option != 1 && option != 2 && option != 3 && option != 4) {
					System.out.println("Invalid input. Please enter a valid option from the menu.");
					continue;
				}
				break;
			}
			if(option == 1) {
				System.out.println("Please enter the word or sentence you would like someone to guess: ");
				word = scanner.nextLine();
				for (int i = 0; i < 50; ++i) {
					System.out.println();
				}
				hangman(word);
				System.out.println("Would you like to quit? [1] for yes, [2] for no");
				while(true) {
					option = scanner.nextInt();
					if(option != 1 && option != 2) {
						System.out.println("Please input a valid option.");
						continue;
					}else if(option == 1) {
						System.out.println("Thank you for playing Hangman! Have a wonderful day!");
						scanner.close();
						return;
					}else {
						break;
					}
				}
			}else if(option == 2) {
				File file = new File("Randomized Hangman");
				word = choose(file);
				hangman(word);
				System.out.println("Would you like to quit? [1] for yes, [2] for no");
				while(true) {
					option = scanner.nextInt();
					if(option != 1 && option != 2) {
						System.out.println("Please input a valid option.");
						continue;
					}else if(option == 1) {
						System.out.println("Thank you for playing Hangman! Have a wonderful day!");
						scanner.close();
						return;
					}else {
						break;
					}
				}
			}else if(option == 3){
				System.out.println("Welcome to Hangman! Your goal is to guess the "
						+ "word or sentence correctly by entering in different letters that you think might be "
						+ "inside of the sentence! But be careful! If you guess six incorrect letters, then your man hangs!"
						+ "Only undercase letters will be used, and no punctuation please.");
			}else {
				System.out.println("Thank you for playing Hangman! Have a wonderful day!");
				break;
			}
		}
		scanner.close();
	}
}