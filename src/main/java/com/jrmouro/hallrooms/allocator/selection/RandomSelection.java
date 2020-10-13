/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.selection;

import com.jrmouro.hallrooms.allocator.hallroomsqueue.IHallRoomsQueue;
import java.util.Random;

/**
 *
 * @author ronaldo
 */
public class RandomSelection extends Selection {

    long seed;

    public RandomSelection(IHallRoomsQueue queue, long seed) {
        super(queue);
        this.seed = seed;
    }

    public RandomSelection(long seed) {
        this.seed = seed;
    }
    
    public RandomSelection() {
        this.seed = System.nanoTime();
    }

    @Override
    public int ruleSelection() {
        return new Random(this.seed++).nextInt(queue.size());
    }

    @Override
    public String toString() {
        return "RandomSelection";
    }

}
