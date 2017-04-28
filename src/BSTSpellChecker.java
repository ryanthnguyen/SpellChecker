import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
public class BSTSpellChecker<E extends Comparable<E>>
{
	private ArrayList<String> myArray = new ArrayList<>();
	private Set<String> mySet = new HashSet<>();
	private static class Node<E> 
	{
		private E data;
		private Node<E> left, right;
		
		private Node(E data, Node<E> left, Node<E> right) 
		{
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
	BinarySearchTree<String> searchTree = new BinarySearchTree<>();
	private Node<E> root;
	private static int midPoint = 0;
	private static int count = 0;
	
	public void add(String s) {
		searchTree.iterationSearchAdd(s);
	}
	
	public boolean contains(String s) {
		return searchTree.iterationSearch(s) != null;
	}
	public void notEfficientAdd() {
        File words = new File("src/word.txt");
        try
        {
            Scanner in = new Scanner(words);
            
            while(in.hasNextLine())
            {
            	String s = in.nextLine();
                add(s);
            }
            
            in.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e);
        }
	}
	
	public void efficientAdd() {
		 File words = new File("src/word.txt");
	        try
	        {
	            Scanner in = new Scanner(words);
	            
	            while(in.hasNextLine())
	            {
	            	String s = in.nextLine();
	                myArray.add(s);
	            }
	            
	            in.close();
	        }
	        catch(FileNotFoundException e)
	        {
	            System.out.println(e);
	        }
	        myArray.trimToSize();
	        //System.out.println(myArray.size());
	        efficientAdd(myArray.size() - 1,0);
	}
	
	private void efficientAdd(int high, int low) {
		
		if (high >= low) {
			int middle = (high + low) / 2;
			add(myArray.get(middle));
			efficientAdd(middle - 1, low);
			efficientAdd(high, middle + 1);
			
		}
	}
	
	public Set<String> swappingLetters(String s) {
		if (contains(s)) {
			return null;
		}
		else {
			for (int i = 0; i < s.length() -1 ;i++) {
				ArrayList<String> word = new ArrayList<>();
				for (int t = 0; t < s.length();t++) {
					word.add(""+s.charAt(t));
				}
				
				String newWord = "";
				String temp = word.get(i);
				word.set(i, word.get(i+1));
				word.set(i+1, temp);
				for (int j = 0; j < word.size();j++) {
					newWord += word.get(j);
				}
				if (contains(newWord)) {
					mySet.add(newWord);
				}
			}
		}
		return mySet;
	}
	
	public Set<String> replaceCharacters(String s) {
		String[] alphabets = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q","r","s", "t", "u", "v","w","x","y","z"};
		ArrayList<String> word = new ArrayList<>();
		for (int i = 0; i < s.length();i++) {
			word.add(""+s.charAt(i));
		}
		String newWord = "";
		String temp = "";
		if (contains(s)) {
			return null;
		} 
		else {
			for(int i = 0; i < word.size(); i++)
			{
				temp = word.get(i);
				for (String j: alphabets) {
					word.remove(i);
					word.add(i, j);
					for (int y = 0; y < word.size();y++) {
						newWord += word.get(y);
					}
					if (!contains(newWord)) {
						word.remove(i);
						word.add(i, temp);
						newWord = "";
					}
					else
					{
						mySet.add(newWord);
					}
				}
			}
		}
		return mySet;
	}
	
	public Set<String> deleteCharacters(String s) {
		ArrayList<String> word = new ArrayList<>();
		for (int i = 0; i < s.length();i++) {
			word.add(""+s.charAt(i));
		}
		String newWord = "";
		String temp = "";
		if (contains(s)) {
			return null;
		} 
		else {
			for(int i = 0; i < word.size(); i++)
			{
				temp = word.get(i + 1);
				word.remove(i + 1);
				for (int j = 0; j < word.size();j++) {
					newWord += word.get(j);
				}
				System.out.println(newWord);
				if(!contains(newWord))
				{
					word.add(i + 1, temp);
					newWord = "";
				}
				else
				{
					mySet.add(newWord);
					break;
				}
			}
		}
		return mySet;
	}
	
	public Set<String> addSpace(String s) {
		String left = "";
		String right = "";
		String space = " ";
		ArrayList<String> word = new ArrayList<>();
		for (int i = 0; i < s.length();i++) {
			word.add(""+s.charAt(i));
		}
		String newWord = "";
		if (contains(s)) {
			return null;
		} 
		else {
			for(int i = 0; i < word.size();i++) {
					word.add(i + 1, space);
					for (int y = 0; y < word.size();y++) {
						newWord += word.get(y);
					}
					String[] splitted = newWord.split("\\s+");
					left = splitted[0];
					right = splitted[1];
					if (!contains(left) && !contains(right)) {
						word.remove(i + 1);
						newWord = "";
					}
					else
					{
						mySet.add(left);
						mySet.add(right);
						break;
					}
				}
			}
		return mySet;
	}
	
	public Set<String> addCharacters(String s) {
		String[] alphabets = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q","r","s", "t", "u", "v","w","x","y","z"};
		ArrayList<String> word = new ArrayList<>();
		for (int i = 0; i < s.length();i++) {
			word.add(""+s.charAt(i));
		}
		String newWord = "";
		if (contains(s)) {
			return null;
		} 
		else {
			for(int i = 0; i < word.size();i++) {
				for (String j: alphabets) {
					word.add(i + 1, j);
					for (int y = 0; y < word.size();y++) {
						newWord += word.get(y);
					}
					if (!contains(newWord)) {
						word.remove(i + 1);
						newWord = "";
					}
					else
						mySet.add(newWord);
					}
			}
		}
		return mySet;
	}

	public Set<String> closeMatches(String s) {
		//return swappingLetters(s);
		//return addCharacters(s);
		//return deleteCharacters(s);
		//return replaceCharacters(s);
		return addSpace(s);
	}
	public static void main(String[] args)
	{
		BSTSpellChecker<String> myTree = new BSTSpellChecker<>();
		Set<String> mynewSet = new HashSet<>();
		//myTree.notEfficientAdd();
		myTree.efficientAdd();
		System.out.println(myTree.contains("police"));
		System.out.println(myTree.contains("cat"));
		System.out.println(myTree.contains("dog"));
		System.out.println(myTree.contains("tree"));
		mynewSet = myTree.closeMatches("alot");			//Missed Spelled Word
		if (mynewSet == null) {
			System.out.println("word is correct");
		}
		else {
			for (String s: mynewSet) {
				System.out.println("Suggested words: " + s);
			}
		}
	}
}
