import java.io.*;
import java.util.*;

/**
 * Functions manage dictionary.
 */
public class DictionnaryManagement {
  public static void insertFromCommandline() {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    String str = sc.nextLine();
    for (int i = 0; i < n; i++) {
      Word w = new Word();
      System.out.println("Add new word: ");
      str = sc.nextLine();
      String FirstLet = str.substring(0, 1);
      String RemainingLet = str.substring(1, str.length());
      str = FirstLet.toUpperCase() + RemainingLet.toLowerCase();
      w.setName(str);
      System.out.println("Add new meaning word: ");
      str = sc.nextLine();
      String FirstLet2 = str.substring(0, 1);
      String RemainingLet2 = str.substring(1, str.length());
      str = FirstLet2.toUpperCase() + RemainingLet2.toLowerCase();
      w.setMeaning(str);
      dictionary.Words.add(w);
    }
    sc.close();
  }
  /** Get data from file. */
  public static void insertFromFile(final String path) {
    try {
      FileInputStream fileinputstream = new FileInputStream(path);
      InputStreamReader inputstreamreader = new InputStreamReader(fileinputstream);
      BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
      String line = null;
      while ((line = bufferedreader.readLine()) != null) {
        String[] tokens = line.split("\t");
        Word w = new Word();
        w.setName(tokens[0]);
        w.setMeaning(tokens[1]);
        w.setPronounce(tokens[2]);
        dictionary.Words.add(w);

      }
      fileinputstream.close();
      inputstreamreader.close();
      bufferedreader.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }
  //Write data (new words) to file//
  public static void WriteDataToFile(final String path, ArrayList <Word> NewWords)
  {
	  try {
	  FileOutputStream fos = new FileOutputStream(path);
	  OutputStreamWriter  outputstreamwriter = new OutputStreamWriter(fos, "UTF-8");
	  BufferedWriter bufferedwriter = new BufferedWriter(outputstreamwriter);
	  for (Word w : NewWords) {
		  String line = w.getName() + "\t" + w.getMeaning();
		  bufferedwriter.write(line);
		  bufferedwriter.newLine();
	  }
	  fos.close();
	  outputstreamwriter.close();
	  bufferedwriter.close();
  }
	  catch (Exception e) {
		  System.out.println(e.getMessage());
	  }
  }
  /** Add a new word to dictionary list in order a-z. */
	  public static void AddNewWord (final String newword) {
		Boolean check = false ;
		for ( Word w : dictionary.Words) {
			if (w.getName().equalsIgnoreCase(newword)) {
				check = true;
			}
		}
			if (check) {
				System.out.println("This word is existed!");
			}
			else {
				Scanner sc = new Scanner (System.in) ;
				String newmeaning = sc.nextLine();
					Word NewWord = new Word();
					NewWord.setName(newword);
					NewWord.setMeaning(newmeaning);
					int positionInsert = -1, size = dictionary.Words.size(); // size of Dictionary
		            for (int i = 0; i < size - 1; i++) {
		                String wTarget1 = dictionary.Words.get(i).getName();
		                String wTarget2 = dictionary.Words.get(i + 1).getName();
		                if (newword.compareTo(wTarget1) > 0
		                        && newword.compareTo(wTarget2) < 0) {
		                    positionInsert = i + 1;
		                    break;
		                }
		            }
		            if (newword.compareTo(dictionary.Words.get(0).getName()) < 0) {
		                positionInsert = 0;
		            }
		            if (newword.compareTo(dictionary.Words.get(size - 1).getName()) > 0) {
		                dictionary.Words.add(NewWord);
		            } else {
		                dictionary.Words.add(positionInsert, NewWord);
		            }
		            System.out.println("This word is added to library.");
					
			            }		
				
				
			}
	  /** Delete a word from dictionary list. */
	  public static void DeleteWordFromFile (final String WordInput) {
		  boolean check = false ;
		  int position = 0;
		  for (int i = 0; i< dictionary.Words.size(); i++) {
			  if (dictionary.Words.get(i).getName().equalsIgnoreCase(WordInput)) {
				  check = true;
				  position = i;
			  }
		  }
		  if (check) {
			  dictionary.Words.remove(position);
		  }
		  else {
			  System.out.println(WordInput+ " is not existed");
		  }
	  }
	  /** Change data in file. */
	    public static void editFile(final String authority) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("\nSelect option: ");
	        System.out.println("1. Delete a word");
	        System.out.println("2. Add a new word");
	        System.out.println("3. Exit");
	        do {
	            System.out.print("Please select option: ");
	            String option = scanner.next();
	            if (option.equals("1")) {
	                System.out.println("Delete word: ");
	                String deleteWord = scanner.next();

	                DeleteWordFromFile(deleteWord);
	                if (authority.equals("admin")) {
	                	WriteDataToFile("src\\dictionaries.txt", dictionary.Words);
	                }
	            } else if (option.equals("2")) {
	                System.out.println("Add English word: ");
	                String newWordE = scanner.next();

	                AddNewWord(newWordE);
	                if (authority.equals("admin")) {
	                	WriteDataToFile("src\\dictionaries.txt", dictionary.Words);
	                }
	            } else if (option.equals("3")) {
	                System.out.println("End the process.");
	                break;
	            }
	        } while (true);
	    }
	  }
	  
  
