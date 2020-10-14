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
import java.util.ArrayList;

/**
 *
 * @author ronaldo
 */
public class RandomSearchStrategy extends SearchShuffeListQueuesStrategy {

    long seed;

    public RandomSearchStrategy(int size, long seed) {
        super(size);
        this.seed = seed;
    }

    @Override
    public AllocationN2 search(IHallRoomsInstance instance, IHallRoomsQueue atualQueue) {

        AllocationN2 atualAllocation = this.allocate(instance, atualQueue);

        Double atualCost = atualAllocation.getCost();

        List<IHallRoomsQueue> queues = queues(instance, atualQueue);

        List<IHallRoomsQueue> aux = new ArrayList();

        for (IHallRoomsQueue queue : queues) {

            AllocationN2 neighborAllocation = this.allocate(instance, queue);

            Double neighborCost = AllocationN2.getTotalCost(instance, neighborAllocation/*, true*/);

            if (neighborCost < atualCost) {

                aux.add(queue);
                
            }

        }

        if (aux.isEmpty()) {

            return atualAllocation;

        }

        Collections.shuffle(aux, new Random(this.seed++));

        return search(instance, aux.get(0));

    }

    @Override
    public String toString() {
        return "RandomSearchStrategy";
    }

}
