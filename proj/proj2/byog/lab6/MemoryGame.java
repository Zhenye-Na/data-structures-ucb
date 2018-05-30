package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
//        if (args.length < 1) {
//            System.out.println("Please enter a seed");
//            return;
//        }
//
//        int seed = Integer.parseInt(args[0]);
        int seed = 5;
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
        rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n

        StringBuilder builder = new StringBuilder();

        for(int i = 1; i < n; i += 1) {
            builder.append(CHARACTERS[this.rand.nextInt(CHARACTERS.length)]);
        }

        return builder.toString();
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen

        // Clear canvas
        StdDraw.clear(Color.black);

        // Position settings
        int midWidth = (int) Math.round(this.width * 0.5);
        int midHeight = (int) Math.round(this.height * 0.5);

        if (!gameOver) {
            Font textfont = new Font("sans serif", Font.BOLD, 15);
            StdDraw.setFont(textfont);
            StdDraw.setPenColor(Color.cyan);

            StdDraw.textLeft(0.0, height - 1, "Round: " + round);
            StdDraw.textRight(width - 1, height - 1, ENCOURAGEMENT[this.rand.nextInt(ENCOURAGEMENT.length)]);
            StdDraw.text(midWidth, height - 1, playerTurn ? "Type!" : "Watch!");
            StdDraw.line(0, height - 2, width, height - 2);
        }

        Font font = new Font("sans serif", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.green);
        StdDraw.text(midWidth, midHeight, s);

        StdDraw.enableDoubleBuffering();
        StdDraw.show();

    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters

        for (int i = 0; i < letters.length(); i += 1) {
            drawFrame(letters.substring(i, i + 1));
            StdDraw.pause(750);
            drawFrame(" ");
            StdDraw.pause(750);
        }

    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input

        String input = "";
        drawFrame(input);

        while (input.length() < n) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char key = StdDraw.nextKeyTyped();
            input += String.valueOf(key);
            drawFrame(input);
        }
        StdDraw.pause(500);
        return input;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        gameOver = false;
        playerTurn = false;
        round = 1;

        //TODO: Establish Game loop
        while (!gameOver) {
            playerTurn = false;
            drawFrame("Round " + round + "! Good luck!");
            StdDraw.pause(750);


            String roundString = generateRandomString(round);
            flashSequence(roundString);

            playerTurn = true;
            String userInput = solicitNCharsInput(round);

            if (!userInput.equals(roundString)) {
                gameOver = true;
                drawFrame("Game Over! Final level: " + round);
            } else {
                drawFrame("Correct, well done!");
                StdDraw.pause(750);
                round += 1;
            }
        }
    }

}
