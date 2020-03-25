/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.localsearch.strategy;

import com.jrmouro.hallrooms.allocation.AllocationN2;
import com.jrmouro.hallrooms.allocator.hallroomsqueue.IHallRoomsQueue;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import com.jrmouro.hallrooms.hallroomsinstance.IHallRoomsInstance;

/**
 *
 * @author ronaldo
 */
public class RandomSearchStrategy extends SearchShuffeListQueuesStrategy {

    @Override
    public AllocationN2 search(IHallRoomsInstance instance, IHallRoomsQueue atualQueue) {

        AllocationN2 atualAllocation = this.allocate(instance, atualQueue);

        Double atualCost = atualAllocation.getCost();

        List<IHallRoomsQueue> queues = queues(instance, atualQueue);

        Random random = new Random();

        Collections.shuffle(queues, random);

        for (IHallRoomsQueue queue : queues) {

            AllocationN2 neighborAllocation = this.allocate(instance, queue);

            Double neighborCost = AllocationN2.getTotalCost(instance, neighborAllocation/*, true*/);

            if (neighborCost < atualCost) {
                return search(instance, queue);
            }

        }

        return atualAllocation;

    }
    
    @Override
    public String toString() {
        return "RandomSearchStrategy";
    }

}
