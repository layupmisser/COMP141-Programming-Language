Raymond Lee
Yu Sun
Project Phase 1.1
COMP141

>>>>>>> ----- BASIC SUMMARY AND USE ----- >>>>>>>

The purpose of this project is to isolate and categorize tokens entered line by line from a specified input file entered as the first argument in the terminal. From there, the token is outputted and written into the specified output file alongside its token type. If there is an error in reading a character, then the scanner will alert the user to the error and immediately move on to the next line of tokens if there is another one.

How to start the program and specify the input and output files:

java Scanner.java YourInputFile.txt YourOutputFile.txt

The input file should contain the lines of token(s) to be interpreted.
The output file will be written with the token(s) and their respective type(s) once an argument is entered.


>>>>>>> ----- PROGRAM USED TO CREATE AND TEST ----- >>>>>>>

This program was created and tested through replit.com.





>>>>>>> ----- PROCESS OF CREATION AND MAINTENANCE ----- >>>>>>>

> Scanner.java MAIN METHOD

The main method accepts terminal arguments as the input file and output file to use for the program.

In the event the user enters an incorrect argument, the program will catch that error.

Then, the program utilizes a loop to go line by line through the input file and passes each line as a string to the scanner method.

The scanner method then returns a data structure of the processed line of tokens that was just passed from the input file. The data structure is a list of pairs. The pairs are of type <String, String>. The first string in the pair is the parsed token, while the second string in the pair is the type of that token.

From there, getL() and getR() functions from the pair return the token and its type respectively. From there, it is written to the specified output file.

The cycle repeats and the program moves to the next line and will complete the same process specified above until there are no more lines from the input file to read and parse.

Finally, the writer and reader files are closed to avoid any issues.



> Scanner.java SCANNER METHOD (HOW THE SCANNER WORKS)

The scanner method is the method responsible for parsing the syntax found on each line of the input file. Specifically, it scans a string line given to it from the main method. 

The scanner does this by using regex searches and other if statements. 

First, the scanner splits the line by “ “ whitespace, separating each token based on the principle of longest substring. Then, using the newly created array from the aforementioned split, a for loop is used to go phrase by phrase. WIthin that loop itself is another for loop that then goes character by character for each phrase. 

Going character by character now, the scanner method uses regex strings to detect the token type of the current character.

Iterating through each character, the token type of each character is considered. If the token type of a character is the same as the last character’s token, then that character is appended onto assembledToken, a string variable that is the token being detected from the phrase. When a character of a different token type than that of the last character is detected, then logically the program will know that it has encountered a new token; the program knows that a new token has been encountered. As a result, the program stores the string value stored in assembledToken into the left element of a pair<String, String>. The assembledToken’s token type is then stored in the right element of that pair. Then, the pair is added to a list List<Pair<String, String>> finalTokenList that stores all of the tokens obtained from the list. The value of assembledToken is reset, and then the next character of the next token is scanned and the cycle continues until all characters in the string line are analyzed. Upon all characters in the line being scanned then isolated into tokens and their respective types, the data from this process stored in finalTokenList is returned back to the main method to be written into the output file.

If the event a character is not recognized by any of the if statements using regex to detect tokens, then the program signals this by first storing the token it had managed to get into another pair<String, String> before getting to this unknown character. Then, it creates another pair from which to store the unknown character and text indicating that it is unknown. Finally, the scanner method aborts the entire string line and the method returns the finalTokenList back to main method.

> Pair.java

Pair.java contains the class Pair, a custom data structure used in the program to store a string token and its token type found through the scanner method. This class contains basic set and get functions to initialize and retrieve the left and right elements of a typical pair data structure. 

In Scanner.java, the scanner method stores a list of these pairs in order to store tokens and their token type.

A list storing pairs was selected as the ideal data structure combination to use because tokens are associated with their token type. It is only logical that we use this type of data structure to get these relevant pieces of information together in one place and in a way that’s easily accessible.
