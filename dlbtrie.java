
/**
 */
import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Object;
import java.lang.StringBuilder;
public class dlbtrie
{
    //file read in, userHist and dictionary
    //edited
    //public StringBuilder wordsSuggest = new StringBuilder();
    //public ArrayList<String> suggestionList = new ArrayList<String>();
   // public boolean keep = false;
    //public static dlbtrie dictionarytrie = new dlbtrie();
    private node root;
    private node nextNode;
    private node currentNode;
    public dlbtrie()
    {
       this.root = null;
       this.currentNode = null;
    }
    
    
    /*
    public void addRoot(char data)
    {
        node newRoot = new node(data);
        this.root = newRoot;
    }
    public char getRootData()
    {
        return root.getData();
    }*/
   
    public static void addWord(dlbtrie trie , String word)
    {
        int count = 0;
        boolean endWord = false;
        boolean endLoop = false;
        if(trie.root == null)//if the root is null put the current node in at the root
        {
            node root = new node(Character.toLowerCase((word.charAt(count))));//set root to the first letter of the word that was inputed
            trie.root = root; 
            trie.currentNode = root;//set current node to be the root
            count++;//increment count so that program will move on to the next letter in the word
        }
        else
        {
            trie.currentNode = trie.root;//if there is alrady a root set the currentNode to be the root
            if(Character.toLowerCase(trie.currentNode.getData()) == Character.toLowerCase((word.charAt(count))))//if the node the program is on is equal to the letter at the given index increment the index
            {
                count++;
            }
        }
         while(count < word.length() && endLoop == false)
        {
          
           if(trie.currentNode.getChild() == null)//checks if currentNode has no child adds character as currentNode's child
            {
               trie.addVert(Character.toLowerCase(word.charAt(count)), trie.currentNode);
               trie.currentNode = trie.currentNode.getChild();
               count++;
            }
            else//if node already has child adds character as next
            {
            if(count > 0)
            {
               trie.currentNode = trie.currentNode.getChild();
            }   
            if(trie.currentNode.getData() == Character.toLowerCase(word.charAt(count)))
            {
               count++;
            }
                else
                {
                
                while(trie.currentNode.getNext() != null && trie.currentNode.getNext().getData() != Character.toLowerCase(word.charAt(count)))
                {
                    trie.currentNode = trie.currentNode.getNext();
                }
                
                if(trie.currentNode.getNext() == null) //&& trie.currentNode.getData() != Character.toLowerCase(word.charAt(count)))
                {

                 trie.addHoriz(trie.currentNode, Character.toLowerCase( word.charAt(count)));
                 trie.currentNode = trie.currentNode.getNext();
                 count++;
                }
                else
                {
                    trie.currentNode = trie.currentNode.getNext();
                    count++;
                }
            }
            }
            
        }

                trie.currentNode.setEnd();
                endLoop = true;
         
    }  
    public static void addHoriz(node previousNode, char nextNode)
    {
        node nNode = new node(nextNode);
        if(previousNode == null)
        {
            previousNode.setData(nextNode);
            
        }
        else
        {
            previousNode.setNext(nNode);
            nNode.setParent(previousNode);
           
        }
    }
    public static void addVert(char childNode, node currentNode)
    {
        node cNode = new node(childNode);
        if(currentNode == null)
        {
            currentNode.setData(childNode);
            //result = true;
        }
        else
        {
            currentNode.setChild(cNode);
            cNode.setParent(currentNode);
            //result = true;
        }
    }
    public ArrayList<String> findWord(dlbtrie dictionarytrie, String input)
    {
      dictionarytrie.currentNode = dictionarytrie.root;
      ArrayList<String> list = new ArrayList<String>();
       for(int i = 0; i < input.length(); i++)
       {
         char current = Character.toLowerCase(dictionarytrie.currentNode.getData());  
         char inputChar = Character.toLowerCase(input.charAt(i));
         while(current != inputChar && dictionarytrie.currentNode.getNext() != null)//finds if the first letter in the input matches a letter in the first level of the dlb
         {
           dictionarytrie.currentNode = dictionarytrie.currentNode.getNext(); 
           current = Character.toLowerCase(dictionarytrie.currentNode.getData()); 
         }
         
         if(current == inputChar && dictionarytrie.currentNode.getChild() != null)
         {
             dictionarytrie.currentNode = dictionarytrie.currentNode.getChild();
         }
         else
         {
            // System.out.println("No suggestions were found");
             return null;
         }
      }
      //System.out.println("word child " + dictionarytrie.currentNode.getData());
      if(dictionarytrie.currentNode != null)
      {
          recurseSuggest(input, dictionarytrie.currentNode, list);
        }
        else
        {
            //System.out.println("No suggestions were found");
            return null;
        }   
      return list;
    }
    
    public void recurseSuggest(String wordSuggest, node current, ArrayList<String> words )//,boolean keep)
    {
        if(current.getEnd())
        {
            words.add(wordSuggest + current.getData());
            
            //System.out.println(words);
        }
        if(5 <= words.size())
        {
            return;
        }
        if(current.getChild() != null)
        {
            recurseSuggest(wordSuggest + current.getData(), current.getChild(), words);
            if(5 > words.size())
            {
                if(current.getNext()== null)
                {
                    return;
                }
                //System.out.println("prefix " + wordSuggest + " Node " + current.getData() + " end:"  + current.getEnd());
                recurseSuggest(wordSuggest, current.getNext(), words);
            }
        }
        
    }
}
