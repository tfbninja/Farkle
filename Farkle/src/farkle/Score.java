package farkle;

import java.util.ArrayList;

/**
 *
 * @author Tim Barber
 */
public class Score {

    private static int[] oneScores = {0, 100, 200, 300, 1000, 2000, 3000};
    private static int[] twoScores = {0, 0, 0, 200, 1000, 2000, 3000};
    private static int[] threeScores = {0, 0, 0, 300, 1000, 2000, 3000};
    private static int[] fourScores = {0, 0, 0, 400, 1000, 2000, 3000};
    private static int[] fiveScores = {0, 50, 100, 500, 1000, 2000, 3000};
    private static int[] sixScores = {0, 0, 0, 600, 1000, 2000, 3000};

    public static int getScore(int[] rolls) {
        if (rolls.length < 6) {
            return getRawScore(rolls);
        } else {
            ArrayList<Integer> rollList = new ArrayList<>();
            for (int i : rolls) {
                rollList.add(i);
            }
            int[] count = new int[6];
            for (int i = 0; i < 6; i++) {
                count[i] = count(rollList, i + 1);
            }
            ArrayList<Integer> countList = new ArrayList<>();
            for (int i : count) {
                countList.add(i);
            }

            if (count(countList, 1) == 6) { // 1-6 straight
                //System.out.print("\n1-6 straight");
                return 1500;
            } else if (countList.contains(6)) { // 6 of a kind
                //System.out.print("\n6 of a kind");
                return 3000;
            } else if (countList.contains(4) && countList.contains(2)) { // four of a kind and a pair
                //System.out.print("\nfour of a kind and a pair");
                return 1500;
            } else if (count(countList, 2) == 3) { // three pairs
                //System.out.print("\nthree pairs");
                return 1500;
            } else if (count(countList, 3) == 2) { // two triplets
                //System.out.print("\ntwo triplets");
                return 2500;
            } else {
                //System.out.print("\nno specials");
                return rawScore(countList);
            }
        }
    }

    public static int getScore(ArrayList<Integer> list) {
        int[] newList = new int[list.size()];
        int index = 0;
        for (int i : list) {
            newList[index] = i;
            index++;
        }
        return getScore(newList);
    }

    public static boolean allUsed(int[] list) {
        ArrayList<Integer> newList = new ArrayList<Integer>();
        for (int i : list) {
            newList.add(i);
        }
        return allUsed(newList);
    }

    public static boolean allUsed(ArrayList<Integer> list) {
        for (int i : list) {
            ArrayList<Integer> tempList = new ArrayList<>();
            for (int temp : list) {
                if (temp != i) {
                    tempList.add(temp);
                }
            }
            if (getScore(list) == getScore(tempList)) {
                return false;
            }
        }
        return true;
    }

    private static int count(ArrayList<Integer> list, Integer value) {
        int count = 0;
        for (Integer i : list) {
            if (i == value) {
                count++;
            }
        }
        return count;
    }

    private static int scoreSingles(ArrayList<Integer> list) {
        return oneScores[list.get(0)] + fiveScores[list.get(4)];
    }

    private static int rawScore(ArrayList<Integer> countList) {
        return oneScores[countList.get(0)]
                + twoScores[countList.get(1)]
                + threeScores[countList.get(2)]
                + fourScores[countList.get(3)]
                + fiveScores[countList.get(4)]
                + sixScores[countList.get(5)];
    }

    public static int getRawScore(ArrayList<Integer> list) {
        int[] countList = {count(list, 1), count(list, 2), count(list, 3), count(list, 4), count(list, 5), count(list, 6)};

        return oneScores[countList[0]]
                + twoScores[countList[1]]
                + threeScores[countList[2]]
                + fourScores[countList[3]]
                + fiveScores[countList[4]]
                + sixScores[countList[5]];
    }

    public static int getRawScore(int[] list) {
        ArrayList<Integer> newList = new ArrayList<>();
        for (int i : list) {
            newList.add(i);
        }
        return getRawScore(newList);
    }
}


/*
 * The MIT License
 *
 * Copyright (c) 2018 Tim Barber.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
