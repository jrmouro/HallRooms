/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.selection;

import com.jrmouro.hallrooms.allocator.hallroomsqueue.HallRoomsQueue;
import com.jrmouro.hallrooms.allocator.hallroomsqueue.IHallRoomsQueue;
import java.util.Iterator;

/**
 *
 * @author ronaldo
 */
public abstract class Selection implements Iterator<Integer> {

    final protected IHallRoomsQueue queue = new HallRoomsQueue();

    public Selection() { }

    public Selection(IHallRoomsQueue queue) {
        for (Integer i : queue) {
            this.queue.add(i);
        }
    }

    public void setQueue(IHallRoomsQueue queue) {
        this.queue.clear();
        for (Integer i : queue) {
            this.queue.add(i);
        }
    }

    @Override
    public boolean hasNext() {
        return !this.queue.isEmpty();
    }

    @Override
    public Integer next() {
        int ind = ruleSelection();
        Integer ret = this.queue.get(ind);
        this.queue.remove(ind);
        return ret;
    }

    public abstract int ruleSelection();

}
