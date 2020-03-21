/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms;

import com.jrmouro.hallrooms.allocator.hungarian.HungarianAllocatorN2;
import com.jrmouro.hallrooms.allocator.naive.NaiveAllocatorN2;
import com.jrmouro.hallrooms.allocator.selection.RandomRateSelection;
import com.jrmouro.hallrooms.allocator.selection.Selection;
import com.jrmouro.hallrooms.hallroomsinstance.ISplitter;
import java.io.File;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ronaldo
 */
public class HallRoomsTest {
    
    
    /**
     * Test of getCost method, of class HallRooms.
     */
    @Test
    public void testGetCost() {
        
        System.out.println("Test\n");
        
        ISplitter splitter = new ISplitter(){
                    @Override
                    public String get() {
                        return ",";
                    }
                };
        Selection selection = new RandomRateSelection(.5);
        HallRooms instance1 = new HallRooms(new File("Inst-10salas-1374.txt"), splitter, new NaiveAllocatorN2(selection));
        HallRooms instance2 = new HallRooms(new File("Inst-10salas-1374.txt"), splitter, new HungarianAllocatorN2(selection));
        
        if(!instance1.wasEvaluated())
            instance1.evaluate();
        
        if(!instance2.wasEvaluated())
            instance2.evaluate();
        
        System.out.println("\ninstance1");
        System.out.println(instance1);
        System.out.println("\n\ninstance1");
        System.out.println(instance2);
        
    }
    
}
