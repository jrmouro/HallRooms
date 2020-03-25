/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator;

import com.jrmouro.hallrooms.allocator.hallroomsqueue.IHallRoomsQueue;
import com.jrmouro.hallrooms.allocation.AllocationN2;
import com.jrmouro.hallrooms.allocator.selection.Selection;
import com.jrmouro.hallrooms.hallroomsinstance.IHallRoomsInstance;
import com.jrmouro.hallrooms.utils.Initializable;

/**
 *
 * @author ronaldo
 */
public interface IAllocatorN2 extends Initializable {

    public AllocationN2 allocate(IHallRoomsInstance instance);
    public AllocationN2 allocate(AllocationN2 allocation);    
    
    public static AllocationN2 allocate(IHallRoomsInstance instance, Selection selection, IHallRoomsQueue queue) {
       
        return new AllocationN2(instance, selection, queue);

    }   

}
