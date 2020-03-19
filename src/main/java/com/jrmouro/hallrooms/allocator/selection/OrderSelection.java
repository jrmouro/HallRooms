/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.selection;

import java.util.List;

/**
 *
 * @author ronaldo
 */
public class OrderSelection extends Selection {

    public OrderSelection(List<Integer> queue) {
        super(queue);
    }

    public OrderSelection() { }

    @Override
    public int ruleSelection() {
        return 0;
    }

    @Override
    public String toString() {
        return "OrderSelection";
    }

}
