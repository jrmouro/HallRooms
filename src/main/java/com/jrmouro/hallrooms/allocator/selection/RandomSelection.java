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
public class RandomSelection extends Selection{
    
    public RandomSelection(List<Integer> queue) {
            super(queue);
        }
        
        public RandomSelection() {} 

        @Override
        public int ruleSelection() {                        
            return new Random().nextInt(queue.size());
        }

        @Override
        public String toString() {
            return "RandomSelection";
        }
        
        
}
