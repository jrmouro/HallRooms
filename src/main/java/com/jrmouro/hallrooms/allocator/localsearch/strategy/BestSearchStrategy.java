/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.localsearch.strategy;

import com.jrmouro.hallrooms.allocation.AllocationN2;
import com.jrmouro.hallrooms.allocator.hallroomsqueue.IHallRoomsQueue;
import java.util.List;
import com.jrmouro.hallrooms.hallroomsinstance.IHallRoomsInstance;

/**
 *
 * @author ronaldo
 */
public class BestSearchStrategy extends SearchShuffeListQueuesStrategy {

    @Override
    public AllocationN2 search(IHallRoomsInstance instance, IHallRoomsQueue atualQueue) {

        AllocationN2 atualAllocation = this.allocate(instance, atualQueue);

        IHallRoomsQueue queueSave = atualQueue;

        Double atualCost = atualAllocation.getCost();

        List<IHallRoomsQueue> queues = queues(instance, atualQueue);

        boolean flag = false;

        for (IHallRoomsQueue queue : queues) {

            AllocationN2 neighborAllocation = this.allocate(instance, queue);

            Double neighborCost = AllocationN2.getTotalCost(instance, neighborAllocation/*, true*/);

            if (neighborCost < atualCost) {
                atualCost = neighborCost;
                atualAllocation = neighborAllocation;
                queueSave = queue;
                flag = true;
            }

        }

        if (flag) {
            return search(instance, queueSave);
        }

        return atualAllocation;

    }

    @Override
    public String toString() {
        return "BestSearchStrategy";
    }
    
    

}
