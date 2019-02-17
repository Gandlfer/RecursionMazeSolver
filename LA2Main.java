package edu.wmich.cs1120.LA2.Darryl_Lee;

/**
 * main class method to execute the MazeInput class and MazeSolver class
 * @author Gandlfer
 */
public class LA2Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MazeInput input=new MazeInput();
		MazeSolver maze=new MazeSolver();
		input.initializeMazeSolver(maze);
		
		for(int i=0;i<3;i++) {
			System.out.println("==================================");
			System.out.println("Maze #"+(i+1));
			maze.drawMaze();
			maze.printMaze();
			maze.printResult();
			System.out.println("==================================");
		}
	}
}
