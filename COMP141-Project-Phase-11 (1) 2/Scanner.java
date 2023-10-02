// Raymond Lee
// Yu Sun
// Project Phase 1.1 Scanner

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

import pair.Pair;
// import javafx.util.Pair;


public class Scanner {

  public static List<Pair<String,String>> scanner(String token) {
    
    Pattern searchKey;
    Matcher searchThis;

    String identifierKey = "^([a-z]|[A-Z])([a-z]|[A-Z]|[0-9])*$";
    String numberKey = "^[0-9]+";
    String symbolKey = "^\\+|\\-|\\*|/|\\(|\\(|\\)$";

    String assembledToken;
    List<Pair<String,String>> finalTokenList = new ArrayList<Pair<String,String>>();

    boolean errorEncountered = false;
    int currToken = 0;
    
    // Token is split up according to whitespace & symbols
    // " |(?<=-)|(?=-)|(?<=\\+)|(?=\\+)|(?<=\\-)|(?=\\-)|(?<=\\*)|(?=\\*)|(?<=/)|(?=/)|(?<=\\()|(?=\\()|(?<=\\))|(?=\\))"
    List<String> tokenList = Arrays.asList(token.split(" "));
    
    for (int i = 0; i < tokenList.size(); i++) {

      if (errorEncountered) {
        break;
      }
      
      assembledToken = "";
      currToken = 0;
      
      token = tokenList.get(i);
      
      System.out.println("Token: " + token);
      for (int j = 0; j < token.length(); j++) {

        String tokenChar = "" + token.charAt(j);

        // -------- SYMBOL --------
        searchKey = Pattern.compile(symbolKey, Pattern.CASE_INSENSITIVE);
        searchThis = searchKey.matcher(tokenChar); 
        // matcher() searches the token for the searchKey
        
        if (searchThis.find()) {

          if (currToken == 1) {
            finalTokenList.add(new Pair<String, String>(assembledToken,"IDENTIFIER"));
            assembledToken = "" + tokenChar;
          }
          else if (currToken == 2) {
            finalTokenList.add(new Pair<String, String>(assembledToken,"NUMBER"));
            assembledToken = "" + tokenChar;
          }

          currToken = 0;
          
          System.out.println(tokenChar + " : SYMBOL");
          assembledToken = tokenChar;
          finalTokenList.add(new Pair<String, String>(assembledToken,"SYMBOL"));
          assembledToken = "";
          
          continue;
        }
      
        // -------- IDENTIFIER --------
        searchKey = Pattern.compile(identifierKey, Pattern.CASE_INSENSITIVE);

        searchThis = searchKey.matcher(tokenChar);     
        if (currToken == 1) {
          searchThis = searchKey.matcher(assembledToken + tokenChar);     
        }
        
        if (searchThis.find()) {

          if (currToken == 2) {
            finalTokenList.add(new Pair<String, String>(assembledToken,"NUMBER"));
            assembledToken = "" + tokenChar;
            continue;
          }
          
          currToken = 1;
          
          assembledToken = assembledToken + "" + tokenChar;
          
          System.out.println(tokenChar + " : IDENTIFIER");
          continue;
        } 

        // -------- NUMBER --------
        searchKey = Pattern.compile(numberKey, Pattern.CASE_INSENSITIVE);
        searchThis = searchKey.matcher(tokenChar); 
        
        if (searchThis.find()) {

          if (currToken == 1) {
            finalTokenList.add(new Pair<String, String>(assembledToken,"IDENTIFIER"));
            assembledToken = "" + tokenChar;
          }

          currToken = 2;
          assembledToken = assembledToken + "" + tokenChar;
          
          System.out.println(tokenChar + " : NUMBER");
          continue;
        }


        // Executed if character is not recognized
        errorEncountered = true;
        
        if (assembledToken != "") {
          if (currToken == 1) {
                finalTokenList.add(new Pair<String, String>(assembledToken,"IDENTIFIER"));
          }
          else if (currToken == 2) {
                finalTokenList.add(new Pair<String, String>(assembledToken,"NUMBER"));
          }
        }
        
        finalTokenList.add(new Pair<String, String>(tokenChar,"ERROR READING THIS CHARACTER"));
        System.out.println("ERROR READING \"" + tokenChar + "\"");
        break;
      }

      if (assembledToken != "" && !errorEncountered) {
        if (currToken == 1) {
              finalTokenList.add(new Pair<String, String>(assembledToken,"IDENTIFIER"));
        }
        else if (currToken == 2) {
              finalTokenList.add(new Pair<String, String>(assembledToken,"NUMBER"));
        }
      }
      
    } 

    System.out.println("Done");
    return finalTokenList;
  }
  
  public static void main(String[] args) {

      try { 
      
      FileReader reader = new FileReader(args[0]);
      FileWriter writer = new FileWriter(args[1]);

      BufferedReader bufferedReader = new BufferedReader(reader);
      String line;
  
      while ((line = bufferedReader.readLine()) != null) {
        System.out.println(line);
        
        List<Pair<String,String>> tokenList = scanner(line);

        System.out.println("\n-------Final token list results: -------");

        writer.write("Line: " + line + "\n");
        for (int i = 0; i < tokenList.size();i++) {
          Pair<String,String> tokenPair = tokenList.get(i);
          System.out.println(tokenPair.getL() + " : " + tokenPair.getR());
          writer.write(tokenPair.getL() + " : " + tokenPair.getR() + "\n");
        }

        writer.write("\n");
        System.out.println();
      }
      writer.close();
      reader.close();
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}