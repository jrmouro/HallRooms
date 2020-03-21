/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.localsearch.strategy;

import com.jrmouro.hallrooms.allocation.AllocationN2;
import com.jrmouro.hallrooms.allocator.HallRoomsQueue;
import com.jrmouro.hallrooms.allocator.IAllocatorN2;
import com.jrmouro.hallrooms.allocator.IHallRoomsQueue;
import com.jrmouro.hallrooms.allocator.selection.OrderSelection;
import com.jrmouro.hallrooms.allocator.selection.Selection;
import java.util.ArrayList;
import java.util.List;
import com.jrmouro.hallrooms.hallroomsinstance.IHallRoomsInstance;

/**
 *
 * @author ronaldo
 */
public abstract class SearchStrategy implements ISearchStrategy {

    protected AllocationN2 allocate(IHallRoomsInstance instance, IHallRoomsQueue queue) {

        Selection selection = new OrderSelection();

        return IAllocatorN2.allocate(instance, selection, queue);

    }

    public List<IHallRoomsQueue> queues(IHallRoomsInstance instance, IHallRoomsQueue initial) {

        List<IHallRoomsQueue> ret = new ArrayList();

        for (int i = 0; i < initial.size() - 1; i++) {

            for (int j = i + 1; j < initial.size(); j++) {

                IHallRoomsQueue queue = new HallRoomsQueue();
                for (Integer ind : initial) {
                    queue.add(ind);
                }

                Integer aux = queue.get(i);
                queue.set(i, queue.get(j));
                queue.set(j, aux);

                ret.add(queue);

            }

        }

        return ret;
    }

}
