/**
 * Game of Life
 *
 * This program creates a life simulator of size n*m using arrays.
 *
 * The pattern of living and dead cells at any given time on the grid
 * is called a generation.
 *
 * To determine the next generation, we use the following rules:
 *
 * [1] If a living cell has few than two living neighbors, it dies
 * from loneliness.
 * [2] If a living cell has more than three neighbors, it dies because
 * of overcrowding.
 * [3] If a living cell has exactly two or three living neighbors,
 * it lives to the next generation.
 * [4] If a dead cell has exactly three live neighbors, it becomes
 * a living cell.
 *
 * These four simple rules allow for surprisingly complex interactions.
 *
 * Instead of using GUI, we print an asterisk '*'
 * (or any other non-space character) for a living cell and a space ' '
 * for a dead one.
 *
 * The suggestion of the height of the simulation is 24 rows and
 * the width is 80 columns.
 *
 * In order to give the appearance of smooth transitions, we print out
 * 100 blank lines before printing the next generation.
 *
 * We wait a short period of time (100ms, namely 0.1s) so that the user
 * can see each configuration before it is cleared away and replaced
 * by the next one. We use the following code:
 * try { Thread.sleep(100); }
 * catch( InterruptedException e ) {}
 *
 * This program begins by randomly making 10% of all the cells
 * living and the rest dead.
 *
 * @author Haixuan Huang, CS180, Start Concurrent, Chapter 6, Arrays
 *
 * @version 09/17/2018
 *
 */

public class GameOfLife {
    public static void main(String[] args) {

        // Constant variables
        final int SIZE_ROWS = 25;
        final int SIZE_COLUMNS = 80;
        final double PROPORTION_ALIVE = 0.15;

        final char ALIVE = 'O';
        final char DEAD = ' ';

        // Create the array to store lives and counter to count living neighbors
        int lifeCounter;
        char[][] generation = new char[SIZE_ROWS][SIZE_COLUMNS];

        // Create space for new generation
        char[][] nextGeneration = new char[generation.length][generation[0].length];

        // Assign dead or alive
        for (int row = 0; row < generation.length; row++) {
            for (int col = 0; col < generation[row].length; col++) {
                if (Math.random() <= PROPORTION_ALIVE) {
                    generation[row][col] = ALIVE;
                } else {
                    generation[row][col] = DEAD;
                }
            }
        }

        /*
        // Another initial state
        for (int row = 0; row < generation.length; row++) {
            for (int col = 0; col < generation[row].length; col++) {
                    generation[row][col] = DEAD;
            }
        }
        generation[1][0] = ALIVE;
        generation[1][1] = ALIVE;
        generation[1][2] = ALIVE;
        */

        while (true) {

            // Print current generation
            for (int row = 0; row < generation.length; row++) {
                for (int col = 0; col < generation[row].length; col++) {
                    System.out.print(generation[row][col] + " ");
                }
                System.out.println();
            }

            // Sleep and catch exception
            try {Thread.sleep(1000);}
            catch(InterruptedException e) {}


            // Print 100 blank lines
            for (int i = 0; i < 100; i++)
                System.out.println();


            // For each cell in current generation...
            for (int row = 0; row < generation.length; row++) {
                for (int col = 0; col < generation[row].length; col++) {
                    lifeCounter = 0;

                    // How many living neighbors does it have?
                    // Upper Left
                    if ((row - 1 >= 0) && (col - 1 >= 0) && (generation[row-1][col-1] == ALIVE))
                        lifeCounter++;
                    // Upper
                    if ((row - 1 >= 0) && (generation[row-1][col] == ALIVE))
                        lifeCounter++;
                    // Upper Right
                    if ((row - 1 >= 0) && (col + 1 < generation[row-1].length) && (generation[row-1][col+1] == ALIVE))
                        lifeCounter++;
                    // Left
                    if ((col - 1 >= 0) && generation[row][col-1] == ALIVE)
                        lifeCounter++;
                    // Right
                    if ((col + 1 < generation[row].length) && (generation[row][col+1] == ALIVE))
                        lifeCounter++;
                    // Lower Left
                    if ((row + 1 < generation.length) && (col - 1 >= 0) && (generation[row+1][col-1] == ALIVE))
                        lifeCounter++;
                    // Lower
                    if ((row + 1 < generation.length) && generation[row+1][col] == ALIVE)
                        lifeCounter++;
                    // Lower Right
                    if ((row + 1 < generation.length) && (col + 1 < generation[row+1].length) && generation[row+1][col+1] == ALIVE)
                        lifeCounter++;

                    // Now, let's apply rules...
                    // [1] If a living cell has few than two living neighbors, it dies from loneliness.
                    if ((generation[row][col] == ALIVE) && (lifeCounter < 2))
                        nextGeneration[row][col] = DEAD;
                    // [2] If a living cell has more than three neighbors, it dies because of overcrowding.
                    if ((generation[row][col] == ALIVE) && (lifeCounter > 3))
                        nextGeneration[row][col] = DEAD;
                    // [3] If a living cell has exactly two or three living neighbors, it lives to the next generation.
                    if ((generation[row][col] == ALIVE) && (lifeCounter == 2 || lifeCounter == 3))
                        nextGeneration[row][col] = ALIVE;
                    // [4] If a dead cell has exactly three live neighbors, it becomes a living cell.
                    if ((generation[row][col] == DEAD) && (lifeCounter == 3))
                        nextGeneration[row][col] = ALIVE;
                    if ((generation[row][col] == DEAD) && (lifeCounter != 3))
                        nextGeneration[row][col] = DEAD;
                }
            }

            // Switch current generation and next generation
            for (int row = 0; row < generation.length; row++) {
                for (int col = 0; col < generation[row].length; col++) {
                    generation[row][col] = nextGeneration[row][col];
                }
            }
        }
    }
}
