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
        
        Collections.shuffle(ret);

        return ret;
    }

}
