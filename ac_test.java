import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Object;
public class ac_test
{
public static void main (String[]args) throws FileNotFoundException
{ 
    ArrayList<String> history = new ArrayList<String>();
    try{
        FileReader fr2 = new FileReader("user_history.txt");
        BufferedReader reader2 = new BufferedReader(fr2);
        String newLine;
        while((newLine = reader2.readLine()) != null)
        {
            history.add(newLine);
        }
    }
    catch(IOException e)
        {
        System.out.println(e);
        }
        
     dlbtrie dictionarytrie = new dlbtrie();   
    try{
        FileReader fr = new FileReader("dictionary.txt");
        BufferedReader reader = new BufferedReader(fr);
        String line;
        while((line = reader.readLine()) != null)
        {
            dictionarytrie.addWord(dictionarytrie,line);
        }
        reader.close();
       }
        catch(IOException e)
        {
        System.out.println(e);
        }
    ArrayList<Double> time = new ArrayList <Double>();  
    ArrayList<String> histSuggestions = new ArrayList<String>();
    String previousInput = "";
    System.out.println("Enter first character");
    String input = new Scanner(System.in).nextLine();
    long startTime = System.nanoTime();
    for(int i = 0; i < history.size(); i++)
    {
        if(history.get(i).charAt(0)== input.charAt(0))
        {
            histSuggestions.add(history.get(i));
        }
    }
    
    ArrayList<String> dictSuggestions = dictionarytrie.findWord(dictionarytrie, input);
    
  
    ArrayList<String> suggestions = new ArrayList<String>();
    for (int i = 0; i < histSuggestions.size(); i++)
    {
    if(suggestions.size() < 5)
    {
        suggestions.add(histSuggestions.get(i));
    }
    else
    {
        break;
    }
   }
   int j = suggestions.size();
   int k = 0;
   while(j < 5 && k < dictSuggestions.size())
   {
       suggestions.add(dictSuggestions.get(k));
       k++;
       j++;
   }
   long currentTime = System.nanoTime() - startTime;
   double cTime = currentTime/1000000000.0;
   time.add(cTime);
   System.out.printf(" ( %f ) \n ", cTime);
     if(suggestions.size() > 0 && !input.equals("$"))// != null)
     {
     
      System.out.println("Predictions: ");
     for(int i = 0; i < suggestions.size(); i++)
      {
         System.out.print("(" + (i+1) + ")" + suggestions.get(i) + " ");
     }
    }
    else
    {
        System.out.println("No predictions were found!");
    }
    
    StringBuilder userInput = new StringBuilder();
     if(!input.equals("$"))
     {
      userInput.append(input);
    }
      System.out.println();
      previousInput = userInput.toString();
      System.out.println("Enter next character");
      input = new Scanner(System.in).nextLine();
      if(input.equals("$"))
         {
             history.add(previousInput);
             userInput.delete(0,input.length());
             System.out.println("Enter next character");
             input = new Scanner(System.in).nextLine();
             userInput.append(input);
         }
         else if(input.equals("1") )
         {
             history.add(suggestions.get(0));
             userInput.delete(0,input.length());
             System.out.println("Enter next character");
             input = new Scanner(System.in).nextLine();
             userInput.append(input);
         }
         else if(input.equals("2"))
         {
             history.add(suggestions.get(1));
             userInput.delete(0,input.length());
             System.out.println("Enter next character");
             input = new Scanner(System.in).nextLine();
             userInput.append(input);
         }
         else if(input.equals("3"))
         {
             history.add(suggestions.get(2));
             userInput.delete(0,input.length());
             System.out.println("Enter next character");
             input = new Scanner(System.in).nextLine();
             userInput.append(input);
         }
         else if(input.equals("4"))
         {
             history.add(suggestions.get(3));
             userInput.delete(0,input.length());
             System.out.println("Enter next character");
             input = new Scanner(System.in).nextLine();
             userInput.append(input);
         }
         else if(input.equals("5"))
         {
             history.add(suggestions.get(4));
             userInput.delete(0,input.length());
             System.out.println("Enter next character");
             input = new Scanner(System.in).nextLine();
             userInput.append(input);
         }
         else
         {
             userInput.append(input);
            }
       
  
     while(!(input.equals("!")))
     {
         
      histSuggestions = new ArrayList<String>();
       
       dictSuggestions = dictionarytrie.findWord(dictionarytrie, userInput.toString());
       //System.out.println("dictionary suggestions " + dictSuggestions);
       suggestions = new ArrayList<String>();
       startTime = System.nanoTime();
      for(int i = 0; i < history.size(); i++)
      {
        //System.out.println("user input beforing making suggestion list " + userInput.toString());
        if(history.get(i).length() >= userInput.toString().length()&& history.get(i).substring(0, userInput.toString().length()).equals(userInput.toString()))
        {
            histSuggestions.add(history.get(i));
            //System.out.println("History suggestions inside if " + histSuggestions);
        }
     }
    //System.out.println("History suggestions " + histSuggestions);
     for (int i = 0; i < histSuggestions.size(); i++)
     {
         if(suggestions.size() < 5)
         {
        suggestions.add(histSuggestions.get(i));
       }
        else
      {
        break;
      }
     }
     j = suggestions.size();
     k = 0;
     if(dictSuggestions != null)
     {
     while(j < 5 && k < dictSuggestions.size())
     {
       suggestions.add(dictSuggestions.get(k));
       k++;
       j++;
     }
    }
     currentTime = System.nanoTime() - startTime;
     cTime = currentTime/1000000000.0;
     time.add(cTime);
     System.out.printf(" ( %f ) \n ", cTime);
     if(suggestions.size() > 0 && !input.equals("$"))// != null)
     {
      
      System.out.println("Predictions: ");
     for(int i = 0; i < suggestions.size(); i++)
      {
         System.out.print("(" + (i+1) + ")" + suggestions.get(i) + " ");
     }
     }
     else
     {
        System.out.println("No predictions were found!");
     }
   
        if(!input.equals("$") && userInput.charAt(userInput.length()-1)!= input.charAt(0))
        {
            userInput.append(input);
            previousInput = userInput.toString();
        }     
         
        System.out.println();
         System.out.println("Enter next character");
         input = new Scanner(System.in).nextLine();
         if(input.equals("$"))
         {
             history.add(previousInput);
             System.out.println("history after $ " + history);
             userInput.delete(0,userInput.length());
            // System.out.println("User1 " + userInput.toString());
            System.out.println("Enter next character");
             input = new Scanner(System.in).nextLine();
             userInput.append(input);
            //System.out.println("User2 " + userInput.toString());
         }
         else if(input.equals("1"))
         {
             history.add(suggestions.get(0));
             userInput.delete(0,userInput.length());
             System.out.println("Enter next character");
             input = new Scanner(System.in).nextLine();
             userInput.append(input);
         }
         else if(input.equals("2"))
         {
             history.add(suggestions.get(1));
             userInput.delete(0,userInput.length());
             System.out.println("Enter next character");
             input = new Scanner(System.in).nextLine();
             userInput.append(input);
         }
         else if(input.equals("3"))
         {
             history.add(suggestions.get(2));
             userInput.delete(0,userInput.length());
             System.out.println("Enter next character");
             input = new Scanner(System.in).nextLine();
             userInput.append(input);
         }
         else if(input.equals("4"))
         {
             history.add(suggestions.get(3));
             userInput.delete(0,userInput.length());
             System.out.println("Enter next character");
             input = new Scanner(System.in).nextLine();
             userInput.append(input);
         }
         else if(input.equals("5"))
         {
             history.add(suggestions.get(4));
             userInput.delete(0,userInput.length());
             System.out.println("Enter next character");
             input = new Scanner(System.in).nextLine();
             userInput.append(input);
         }
        
        }
       
    
    
    double avg = 0;
     /*if(input.equals("$"))
     {
           history.add(previousInput);
           userInput.delete(0,input.length());
     }*/
     double total = 0;
     if( input.equals("!"))
     {
         PrintWriter pw = new PrintWriter(new File ("user_history.txt"));
         for(int i = 0; i < time.size(); i++)
         {
             total += time.get(i);
         }
         avg = total/time.size();
         System.out.printf("Average Time: %f \n", avg );
         for(int i = 0; i < history.size(); i++)
         {
             pw.write(history.get(i) + "\n");
         }
         pw.flush();
         pw.close();
     }
        
 
      
    }
}