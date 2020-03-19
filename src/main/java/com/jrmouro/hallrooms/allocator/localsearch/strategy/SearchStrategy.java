/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.localsearch.strategy;

import com.jrmouro.hallrooms.allocation.AllocationN2;
import com.jrmouro.hallrooms.allocator.IAllocatorN2;
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

    protected AllocationN2 allocate(IHallRoomsInstance instance, List<Integer> queue) {

        Selection selection = new OrderSelection();

        return IAllocatorN2.allocate(instance, selection, queue);

    }

    public List<List<Integer>> queues(IHallRoomsInstance instance, List<Integer> initial) {

        List<List<Integer>> ret = new ArrayList();

        for (int i = 0; i < initial.size() - 1; i++) {

            for (int j = i + 1; j < initial.size(); j++) {

                List<Integer> list = new ArrayList();
                for (Integer ind : initial) {
                    list.add(ind);
                }

                Integer aux = list.get(i);
                list.set(i, list.get(j));
                list.set(j, aux);

                ret.add(list);

            }

        }

        return ret;
    }

}
