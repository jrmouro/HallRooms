/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.localsearch.strategy;

import com.jrmouro.hallrooms.allocation.AllocationN2;
import com.jrmouro.hallrooms.allocator.IAllocatorN2;
import com.jrmouro.hallrooms.allocator.hallroomsqueue.IHallRoomsQueue;
import com.jrmouro.hallrooms.allocator.selection.OrderSelection;
import com.jrmouro.hallrooms.allocator.selection.Selection;
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

}
