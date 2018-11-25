package farkle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Tim Barber
 */
public class FarkleTerminal {

    private static ArrayList<Integer> scores = new ArrayList<>();
    private static ArrayList<String> names = new ArrayList<>();
    private static Dice dice = new Dice();

    public static void main(String[] args) {
        System.out.println("\nCopyright (c) 2018 Tim Barber under the MIT License\n"); //Header
        Scanner keyboard = new Scanner(System.in);
        setup();
        System.out.println("");

        while (Collections.max(scores) < 10000) {
            round(scores, names);
        }
        int topScore = 0;
        String topName = "";
        int topIndex = 0;
        int index = 0;
        for (Integer i : scores) {
            if (i > topScore) {
                topScore = i;
                topName = names.get(index);
                topIndex = index;
            }
            index++;
        }
        System.out.println(topName + " has " + topScore + " points! Everyone else has 1 chance to beat it.");
        ArrayList<Integer> otherScores = scores;
        otherScores.remove(topIndex);
        ArrayList<String> otherNames = names;
        otherNames.remove(topIndex);
        round(scores, names);
        int index1 = 0;
        for (Integer i : scores) {
            if (i > topScore) {
                topScore = i;
                topName = names.get(index);
                topIndex = index1;
            }
            index1++;
        }
        System.out.println("WINNER: " + topName);

    }

    public static void setup() {
        System.out.println("Welcome to Java FARKLE\n----------------------");
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the number of players: ");
        int players = keyboard.nextInt();
        for (int i = 0; i < players; i++) {
            scores.add(0);
        }
        keyboard.nextLine();
        for (int i = 0; i < players; i++) {
            System.out.print("Player name " + (i + 1) + ": ");
            String tempName = keyboard.nextLine();
            names.add(((char) (Math.random() * 100 % 100)) + tempName.replaceAll("[^a-zA-Z0-9'!''@''#'$''%''^''&''*''('')'' ']", ""));
        }
        Collections.sort(names);
        for (int i = 0; i < names.size(); i++) {
            names.set(i, names.get(i).substring(1));
        }
    }

    public static void round(ArrayList<Integer> scores, ArrayList<String> names) {
        Scanner keyboard = new Scanner(System.in);
        boolean sub10000 = false;
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i) >= 10000) {
                sub10000 = true;
            }
        }
        for (int playNum = 0; playNum < names.size(); playNum++) {

            for (int i = 0; i < scores.size(); i++) {
                if (scores.get(i) >= 10000) {
                    sub10000 = true;
                }
            }
            if (!sub10000) {

                System.out.print("Scores: ");
                for (int i = 0; i < scores.size(); i++) {
                    if (scores.get(i) > 10000) {
                        sub10000 = true;
                    }
                    System.out.print(names.get(i) + ": " + scores.get(i) + "  ");
                }
                System.out.println("\n\nTurn: " + names.get(playNum));
                boolean keepGoing = true;
                int reroll = 6;
                int tempScore = 0;
                while (keepGoing) {
                    System.out.print("Your temp score: " + tempScore + ". Press enter to roll " + reroll + " dice:");
                    String ee = keyboard.nextLine();
                    if (ee.trim().toLowerCase().equals("uuddlrlrba")) {
                        scores.set(playNum, 9950);
                    }
                    int[] rolls = dice.roll(reroll);
                    Arrays.sort(rolls);
                    for (int roll : rolls) {
                        System.out.print(roll + " ");
                    }
                    int totalScore = Score.getScore(rolls);
                    if (totalScore != 0) {
                        System.out.println("\nThis roll score: " + totalScore);
                    } else {
                        System.out.println("\nRoll score: FARKLE!");
                        tempScore = 0;
                        keepGoing = false;
                        break;
                    }
                    //action item: add capability for keeping dice and changing score
                    ArrayList<Integer> keepDice = new ArrayList<>();
                    if (Score.allUsed(rolls) == false) {
                        System.out.print("Mark the dice you want to (k)eep for points with 'k', otherwise press enter.\n");
                        System.out.println("You must pick at least one.");
                        reroll = 0;
                        for (int i : rolls) {
                            System.out.print(i + ": ");
                            String answer = keyboard.nextLine().trim().toLowerCase();
                            if (answer.contains("k")) {
                                keepDice.add(i);
                            } else {
                                reroll++;
                            }
                        }
                        if (keepDice.size() < 1) {
                            keepDice.add(rolls[0]);
                        }
                        tempScore += Score.getScore(keepDice);
                    } else {
                        System.out.print("All dice used! ");
                        reroll = 6;
                        tempScore += Score.getScore(rolls);
                    }

                    System.out.print("You have " + reroll + " dice to roll. Do you want to stop? ");
                    if (scores.get(playNum) + tempScore < 500) {
                        System.out.print("You have to keep rolling to get on the scoreboard... ");
                    }
                    String stop = keyboard.nextLine().trim().toLowerCase();
                    if (stop.contains("y")) {
                        keepGoing = false;
                        break;
                    }
                }
                scores.set(playNum, scores.get(playNum) + tempScore);
            }
        }
    }

    public static void testScores() {
        for (int a = 1; a <= 6; a++) {
            for (int b = 1; b <= 6; b++) {
                for (int c = 1; c <= 6; c++) {
                    for (int d = 1; d <= 6; d++) {
                        for (int e = 1; e <= 6; e++) {
                            for (int f = 1; f <= 6; f++) {
                                int[] temp = {a, b, c, d, e, f};
                                System.out.println(Arrays.toString(temp) + "\n" + Score.getScore(temp));
                            }
                        }
                    }
                }
            }
        }
    }
}

/*
 * The MIT License
 *
 * Copyright (c) 2018 Tim Barber.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */
