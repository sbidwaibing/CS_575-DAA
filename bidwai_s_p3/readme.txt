*********************************************************                     |
*  CS-575                                               *                     |
*  Author: SUKRUT RAHUL BIDWAI - sbidwai@binghamton.edu *                     |
*  Programming Language- Java                           *                     |
*  Java                                                 *                     |
*  Compiler: javac                                      *                     |
*  DAA - Programming Assignment 03                      *                     |                                     
*********************************************************                     |
______________________________________________________________________________|
Instruction to compile the Following Programs:                                |
______________________________________________________________________________|
Method 01: Using Command Line Arguments as follows:                           |
______________________________________________________________________________|
For lcs.java                                                                  |
Please use the following Trace:                                               |
1. Open the terminal;                                                         |
                                                                              |
2. Compile lcs.java Program -> javac lcs.java                                 |
                            -> (2 String Required as) => java lcs S1 S2       |
______________________________________________________________________________|
______________________________________________________________________________|
For floyd.java                                                                |
Please use the following Trace:                                               |
1. Open the terminal;                                                         |
                                                                              |
2. Compile floyd.java Program                                                 |
    -> javac floyd.java                                                       |
    -> java floyd (Input.txt Required) => java floyd input.txt                |
______________________________________________________________________________|
______________________________________________________________________________|
Method 02: Using makefile (make) Command as follows:                          |
______________________________________________________________________________|
For lcs.java                                                                  |
Please use the following Trace:                                               |
1. Open the terminal;                                                         |
                                                                              |
2. Write make and hit Enter                                                   |
                                                                              |
3. Just make sure that for lcs.java the Arguments has to be edited from the   |
   make file such as editing the value of String 1 and String 2, and the      |
   name of the program, has to be edited from the makefile                    |                                          
   eg: Copy this to make the structure of make file should be: (1 tab Space)  |        
        all:                                                                  |
	        javac lcs.java                                                |
	        java lcs ABCDEfghi AcbDedghaq                                 |    
______________________________________________________________________________|
______________________________________________________________________________|
For floyd.java                                                                |
Please use the following Trace:                                               |                                           
1. Open the terminal;                                                         |
                                                                              |
2. Write make and hit Enter                                                   |
                                                                              |
3. Just make sure that for floyds.java the Arguments has to be edited         | 
   from the make file such as editing the value of input file.txt and the     |
   name of the program, has to be done from the makefile                      | 
   eg: Copy this to make the structure of make file should be: (1 tab Space)  |        
        all:                                                                  |
	        javac floyd.java                                              |
	        java floyd input.txt                                          |                                                                   
______________________________________________________________________________|
