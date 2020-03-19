/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.localsearch.strategy;

import com.jrmouro.hallrooms.allocation.AllocationN2;
import java.util.List;
import com.jrmouro.hallrooms.hallroomsinstance.IHallRoomsInstance;

/**
 *
 * @author ronaldo
 */
public class FirstSearchStrategy extends SearchStrategy {

    @Override
    public AllocationN2 search(IHallRoomsInstance instance, List<Integer> atualQueue) {

        AllocationN2 atualAllocation = this.allocate(instance, atualQueue);

        Double atualCost = atualAllocation.getCost();

        List<List<Integer>> queues = queues(instance, atualQueue);

        for (List<Integer> queue : queues) {

            AllocationN2 neighborAllocation = this.allocate(instance, queue);

            Double neighborCost = AllocationN2.getTotalCost(instance, neighborAllocation, true);

            if (neighborCost < atualCost) {
                return search(instance, queue);
            }

        }

        return atualAllocation;

    }
    
    @Override
    public String toString() {
        return "FirstSearchStrategy";
    }

}
