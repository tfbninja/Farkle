package farkle;

import java.time.LocalDateTime;
import java.util.Random;

/**
 *
 * @author Tim Barber
 */
public class Dice {

    private Random random;
    private int seed;
    private int value;

    public Dice() {
        this.seed = LocalDateTime.now().toLocalTime().toSecondOfDay();
        this.random = new Random(seed);
        this.value = random.nextInt(5) + 1;
    }

    public Dice(int value) {
        this.value = value;
        this.seed = LocalDateTime.now().toLocalTime().toSecondOfDay();
        this.random = new Random(seed);
    }

    public int roll() {
        this.value = random.nextInt(5) + 1;
        return this.value;
    }

    public int[] roll(int num) {
        int[] rolls = new int[num];
        for (int i = 0; i < num; i++) {
            rolls[i] = roll();
        }
        return rolls;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public void newSeed() {
        this.seed = LocalDateTime.now().toLocalTime().toSecondOfDay();
    }

    @Override
    public String toString() {
        int unicodeVal = 2679 + this.value;
        return String.valueOf(Character.toChars(unicodeVal));
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
