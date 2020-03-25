/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.hallroomsqueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public interface IHallRoomsQueue extends Iterable<Integer>{
    public void add(Integer value);
    public void add(Collection<Integer> col); 
    public Integer get();
    public Integer get(int index);
    public Integer remove();
    public Integer remove(int index);
    public void clear();
    public boolean isEmpty();
    public int size();
    public void set(int index, Integer value);
    public void shuffle();
    
    public static List<Integer> getQueueList(IHallRoomsQueue hallRoomsQueue){
        List<Integer> ret = new ArrayList();
        
        for (Integer i : hallRoomsQueue) {
            ret.add(i);
        }        
        
        return ret;
        
    }
}
