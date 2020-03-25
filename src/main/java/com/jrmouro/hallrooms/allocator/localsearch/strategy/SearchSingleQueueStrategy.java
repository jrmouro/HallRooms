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
public abstract class SearchSingleQueueStrategy extends SearchStrategy {
    
    final private double moveRate;

    public SearchSingleQueueStrategy(double moveRate) {
        this.moveRate = moveRate;
    }
          
   
    public IHallRoomsQueue queue(IHallRoomsInstance instance, IHallRoomsQueue initial) {

        List<Integer> indexList = new ArrayList();
        for (int i = 0; i < initial.size(); i++) {
            indexList.add(i);
        }
        Collections.shuffle(indexList);       

        IHallRoomsQueue queue = new HallRoomsQueue();
        for (Integer ind : initial) {
            queue.add(ind);
        }
        
        int q = (int)(this.moveRate * (double)initial.size());
        
        for (int i = 0; i < q; i++) {
            
            int ind = indexList.get(0);
            indexList.remove(0);
            
            if(ind < queue.size() - 1){            
                Integer aux = queue.get(ind);
                queue.set(ind, queue.get(ind+1));
                queue.set(ind+1, aux);
            }else{
                Integer aux = queue.get(queue.size() - 1);
                queue.set(queue.size() - 1, queue.get(0));
                queue.set(0, aux);
            }
                            
        }                              

        return queue;
        
    }

}
