package edu.wmich.cs1120.LA2.Darryl_Lee;

import java.util.Scanner;
/**
 * A class to accept user input for validation and pass the value to MazeSolver class
 * @author Gandlfer
 */
public class MazeInput {
	Scanner scan= new Scanner(System.in);
	/**
	*This method initializes/sets the numRows and numCols of the solver (instance of MazeSolver Class) with the numRows and numCols provided by the user. 
	*@param solver : Initialize an object from MazeSolver class
	*/
	public void initializeMazeSolver(MazeSolver solver) {
		solver.setNumRows(getNumRows());
		solver.setNumCols(getNumCols(solver.getNumRows()));
	}
	/**
	* This method asks for a number within the range [5, 10] from the user as the number of rows. The method is also responsible for validating the input and printing corresponding messages to the screen if the input is not valid.
	*@return Integer.parseInt(input) : integer value for rows
	*/
	private int getNumRows() {
		boolean flag=false;
		String input="";
		while (flag==false) {
			System.out.println("Please enter a number of rows from range [5,10].");
			input=scan.nextLine();
			if(isNumber(input)==false) {
				flag=false;
			}
			else {
				if(Integer.parseInt(input)>=5 && Integer.parseInt(input)<=10) {
					return Integer.parseInt(input);
				}
				else {
					System.out.println("Number of rows have to be between [5,10]!\n(Note:5 and 10 are included.)");
					flag=false;
				}
			}
			System.out.println("Retrying...");
		}
		return 0;
	}
	/**
	*This method asks for a number within the range [5, 10] from the user as the number of rows - this number cannot be the same as the number of rows denoted by parameter numRows. 
	*This method is also responsible for validating the input and printing corresponding messages to the screen if the input is not valid. 
	*@param numRows: used to make sure the number of columns and rows will not be the same
	*@return Integer.parseInt(input) : integer value for column
	*/
	private int getNumCols(int numRows) {
		boolean flag=false;
		String input="";
		while (flag==false) {
			System.out.println("Please enter a number of column from range [5,10].");
			input=scan.nextLine();
			if(isNumber(input)==false) {
				flag=false;
			}
			else {
				if(Integer.parseInt(input)>=5 && Integer.parseInt(input)<=10) {
					if(Integer.parseInt(input)==numRows) {
						System.out.println("Number of column cannot be the same with number of rows");
						flag=false;
					}
					else {
						return Integer.parseInt(input);
					}
				}
				else {
					System.out.println("Number of column have to be between [5,10]!\n(Note:5 and 10 are included.\nNumber of column cannot be the same with number of rows)");
					flag=false;
				}
			}
			System.out.println("Retrying...");
		}
		return 0;
	}
	/**
	 * This method is to check if the string is integer number or characters
	 * @param str: string in the 2d array
	 * @return true if it is integer, false for anything other than integers
	 */
	private boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
			return true;
		}
		catch(NumberFormatException e){
			System.out.println("Only an Integer is allowed!");
			return false;
		}
	}
}
