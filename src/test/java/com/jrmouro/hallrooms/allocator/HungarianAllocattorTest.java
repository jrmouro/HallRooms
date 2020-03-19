/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator;

import com.jrmouro.hallrooms.allocator.hungarian.HungarianAllocatorN2;
import com.jrmouro.hallrooms.allocator.selection.OrderSelection;
import com.jrmouro.hallrooms.hallroomsinstance.HallRoomsReaderInstance;
import com.jrmouro.hallrooms.hallroomsinstance.ISplitter;
import java.io.File;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ronaldo
 */
public class HungarianAllocattorTest {
    
    

    /**
     * Test of allocate method, of class HungarianAllocatorN2.
     */
    @Test
    public void testAllocate() {
        System.out.println("allocate");
        
        SelectionAllocatorN2 allocator = new HungarianAllocatorN2();
        allocator.setSelection(new OrderSelection());
        
        
        HallRoomsReaderInstance instance = new HallRoomsReaderInstance(
                new File("Inst-10salas-1374.txt"),
                new ISplitter(){
                    @Override
                    public String get() {
                        return ",";
                    }
                }); 
        
        System.out.println(instance);
        
        System.out.println(allocator.allocate(instance));
        
    }
    
}
