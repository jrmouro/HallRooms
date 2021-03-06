/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.localsearch.strategy;

import com.jrmouro.hallrooms.allocator.hallroomsqueue.HallRoomsQueue;
import com.jrmouro.hallrooms.allocator.hallroomsqueue.IHallRoomsQueue;
import java.util.ArrayList;
import java.util.List;
import com.jrmouro.hallrooms.hallroomsinstance.IHallRoomsInstance;
import java.util.Collections;

/**
 *
 * @author ronaldo
 */
public abstract class SearchShuffeListQueuesStrategy extends SearchStrategy {
    
    final int size;

    public SearchShuffeListQueuesStrategy(int size) {
        this.size = size;
    }
    
    
    
    public List<IHallRoomsQueue> queues(IHallRoomsInstance instance, IHallRoomsQueue ref) {

        List<IHallRoomsQueue> ret = new ArrayList();

        for (int i = 0; i < Math.min(size,ref.size() - 1); i++) {

            for (int j = i + 1; j < Math.min(size,ref.size()); j++) {

                IHallRoomsQueue queue = new HallRoomsQueue();
                for (Integer ind : ref) {
                    queue.add(ind);
                }

                Integer aux = queue.get(i);
                queue.set(i, queue.get(j));
                queue.set(j, aux);

                ret.add(queue);

            }

        }
        
        Collections.shuffle(ret);

        return ret;
    }

}
