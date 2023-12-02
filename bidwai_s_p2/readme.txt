*********************************************************                     |
*  CS-575                                               *                     |
*  Author: SUKRUT RAHUL BIDWAI - sbidwai@binghamton.edu *                     |
*  Programming Language- Java                           *                     |
*  Java                                                 *                     |
*  Compiler: javac                                      *                     |
*  DAA - Programming Assignment 02                      *                     |                                     
*********************************************************                     |
______________________________________________________________________________|
Instruction to compile the Following Programs:                                |
______________________________________________________________________________|
Method 01: Using Command Line Arguments as follows:                           |
______________________________________________________________________________|
For Lim.java                                                                  |
Please use the following Trace:                                               |
1. Open the terminal;                                                         |
                                                                              |
2. Compile Lim.java Program -> javac Lim.java                                 |
                            -> java Lim (Number Required) => java Lim 6       |
______________________________________________________________________________|
______________________________________________________________________________|
For Strassen.java                                                             |
Please use the following Trace:                                               |
1. Open the terminal;                                                         |
                                                                              |
2. Compile Strassen.java Program                                              |
    -> javac Strassen.java                                                    |
    -> java Strassen (Number Required) => java Strassen 4                     |
______________________________________________________________________________|
______________________________________________________________________________|
Method 02: Using makefile (make) Command as follows:                          |
______________________________________________________________________________|
For Lim.java                                                                  |
Please use the following Trace:                                               |
1. Open the terminal;                                                         |
                                                                              |
2. Write make and hit Enter                                                   |
                                                                              |
3. Just make sure that for Lim.java the Arguments has to be edited from the   |
   make file such as editing the value of n and the name of the program,      |  
   has to be done from the makefile                                           | 
   eg: Copy this to make the structure of make file should be: (1 tab Space)  |        
        all:                                                                  |
	        javac Lim.java                                                    |
	        java Lim 12                                                       |    
______________________________________________________________________________|
______________________________________________________________________________|
For Strassen.java                                                             |
Please use the following Trace:                                               |                                           |
1. Open the terminal;                                                         |
                                                                              |
2. Write make and hit Enter                                                   |
                                                                              |
3. Just make sure that for Strassens.java the Arguments has to be edited      | 
   from the make file such as editing the value of n and the                  |
   name of the program, has to be done from the makefile                      | 
   eg: Copy this to make the structure of make file should be: (1 tab Space)  |        
        all:                                                                  |
	        javac Strassen.java                                               |
	        java Strassen 4                                                   |                                                                   |
______________________________________________________________________________|