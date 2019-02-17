package edu.wmich.cs1120.LA2.Darryl_Lee;
import java.util.Random;
/**
 * This is the class that process and execution for the maze
 * @author Gandlfer
 */
public class MazeSolver {
	private int numRows=0, numCols=0,points=0;
	private String maze[][];
	private boolean visited[][];
	private int triedPath[][];
	private Random rand=new Random();
	
	/** The setter for the private attribute: numRows. 
	 * @param assign parameter's value to numRows
	 */
	public void setNumRows(int numRows) {this.numRows=numRows;}
	/** The setter for the private attribute: numCols. 
	 * @param assignet parameters value to numCols
	 */
	public void setNumCols(int numCols) {this.numCols=numCols;}
	/** The getter for the private attribute: numRows. 
	 * @return value for numRows
	 */
	public int getNumRows() {return numRows;}
	/** The getter for the private attribute: numCols. 
	 * @return value for numCols
	 */
	public int getNumCols() {return numCols;}

	/** Draw the maze (a 2-d matrix, type String) by randomly filling it with "#" (block) and numbers (e.g., "45"). Set the top left cell to "S" (starting point), and the bottom right cell to "D" (destination). Numbers are randomly generated within the range [1, 100], while the total number of "#" cannot exceed 1/3 of the total number of cells in the matrix. */
	public void drawMaze() {
		int counter=0;
		System.out.println("Drawing Maze....");
		maze=new String[numRows][numCols];
		visited=new boolean[numRows][numCols];
		triedPath=new int[numRows][numCols];
		for(int i=0;i<numRows;i++) {
			for(int j=0;j<numCols;j++) {
				if((i==0 && j==0)||(i==numRows-1 && j==numCols-1)) {
					if(i==0 && j==0) {
						maze[i][j]="S";
					}
					else {
						maze[i][j]="D";
					}
				}
				else {
					if(rand.nextInt(3)!=1) {
						maze[i][j]=Integer.toString(rand.nextInt(100)+1);
					}
					else {
						if(counter<=((numRows*numCols)/3)) {
							maze[i][j]="#";
							counter++;
						}
						else {
							maze[i][j]=Integer.toString(rand.nextInt(100)+1);
						}
					}
				}
			}
		}
	}

	/** Print out the original maze. Each column must be properly aligned. */
	public void printMaze() {
		for(int i=0;i<numRows;i++) {
			for(int j=0;j<numCols;j++) {
				System.out.printf("%6s",maze[i][j]);
			}
			System.out.println();
		}
	}
	/** 
	 * This method should only call the method “solveMazeRecursively(int row, int col)” – the recursive method - and return the result received from it. 
	 * @return return value of recursion solveMazeRecursively(0,0)*/
	public boolean isSolvable() {return solveMazeRecursively(0,0);}
	 
	/**
	 *This method is the core part to solve the maze. It tries to solve the maze using recursion. It returns true if there is at least one solution. Otherwise, it returns false. 
	 * @param row: rows for 2d array 
	 * @param col: column for 2d array
	* @return true if path is found, false if no path is found
	*/
	private boolean solveMazeRecursively(int row, int col) {
		boolean solved=false;
		if(isNotOutOfBound(row,col)==true) {
			triedPath[row][col]=2;
			if(row==numRows-1 && col==numCols-1 ) {
				solved=true;
			}
			else {
				if(solved==false) {
					if(isNotOutOfBound(row,col+1)==true && isNotOutOfBound(row+1,col+1)==true) {
						solved=solveMazeRecursively(row,col+1);
					}
					else {
						solved=solveMazeRecursively(row+1,col);
					}
				}
				if(solved==false) {
					solved=solveMazeRecursively(row,col+1);
				}
				if(solved==false) {
					solved=solveMazeRecursively(row-1,col);
				}
				if(solved==false) {
					solved=solveMazeRecursively(row,col-1);
				}
			}
			if(solved==true) {
				triedPath[row][col]=1;
			}
		}
		return solved;
	}
	/** convert integer array to boolean array if path is found.*/
	private void convertToBoolean() {
		for(int i=0; i<triedPath.length;i++) {
			for(int j=0; j<triedPath[i].length;j++) {
				if(triedPath[i][j]==1) {
					visited[i][j]=true;
				}
				else {
					visited[i][j]=false;
				}
			}
		}
	}
	/** If there is a solution, this method will be called to print out the maze with the path position replaced by “+”. 
	 * The amount of coins collected on the path is also calculated and printed in this method. Each column must be properly aligned. */
	public void printResult() {
		if(isSolvable()==true) {
			convertToBoolean();
			for(int i=0;i<maze.length;i++) {
				for(int j=0;j<maze[i].length;j++) {
					if(isNumber(maze[i][j])==true && visited[i][j]==true) {
						points+=Integer.parseInt(maze[i][j]);
						maze[i][j]="+";
					}
				}
			}
			System.out.println("Hooray! I found a solution out of here!");
			System.out.println("Printing Solution...");
			printMaze();
			System.out.printf("I received %d of coins on my way out!\n",points);
		}
		else {
			System.out.println("Sorry I am stuck in this maze(T^T)!");
		}
	}
	/** 
	 * To check if index for 2d array is out of bound
	 * @param r:row for 2d arrays
	 * @param c: column for 2d arrays
	 * @return true if it is not out of bound, false if it is out of bound
	 */
	private boolean isNotOutOfBound(int r, int c) {
		if((r>=0 && r<numRows) && (c>=0 && c<numCols)){
			if(triedPath[r][c]==0) {
				if(maze[r][c].equals("S")||maze[r][c].equals("D")) {
					return true;
				}
				if(isNumber(maze[r][c])==true) {
					return true;
				}
			}
		}
		return false;
	}
	/** 
	 * Checking if string is number
	 * @param str: String value from maze 2d array position
	 * @return true if String is a number, false if it is not a number*/
	private boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
			return true;
		}
		catch(NumberFormatException e){
			return false;
		}
	}
}
