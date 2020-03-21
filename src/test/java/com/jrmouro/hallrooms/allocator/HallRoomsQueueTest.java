/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator;

import com.jrmouro.hallrooms.allocation.AllocationN2;
import com.jrmouro.hallrooms.allocator.naive.NaiveAllocatorN2;
import com.jrmouro.hallrooms.allocator.selection.OrderSelection;
import com.jrmouro.hallrooms.hallroomsinstance.HallRoomsReaderInstance;
import com.jrmouro.hallrooms.hallroomsinstance.ISplitter;
import java.io.File;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ronaldo
 */
public class HallRoomsQueueTest {
    
       
    /**
     * Test of toString method, of class HallRoomsQueue.
     */
    @Test
    public void testToString() {
        
        System.out.println("toString");
        
        SelectionAllocatorN2 allocator = new NaiveAllocatorN2(new OrderSelection());
                
        
        HallRoomsReaderInstance instance = new HallRoomsReaderInstance(
                new File("Inst-10salas-1374.txt"),
                new ISplitter(){
                    @Override
                    public String get() {
                        return ",";
                    }
                }); 
        
        System.out.println(instance);
        
        System.out.println(allocator.queue(instance));
        
        AllocationN2 allocation0 = allocator.allocate(instance);        
        
        System.out.println(allocation0);
        
        
    }
    
}
