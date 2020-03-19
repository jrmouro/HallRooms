/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.selection;

import java.util.List;
import java.util.Random;

/**
 *
 * @author ronaldo
 */
public class RandomRateSelection extends Selection {

    final double rate;

    public RandomRateSelection(List<Integer> queue, double rate) {
        super(queue);
        this.rate = rate;
    }

    public RandomRateSelection(double rate) {
        this.rate = rate;
    }

    @Override
    public int ruleSelection() {
        return new Random().nextInt((int) (this.rate * (double) queue.size()) + 1);
    }

    @Override
    public String toString() {
        return "RandomRateSelection" + " rate: " + rate;
    }

}
