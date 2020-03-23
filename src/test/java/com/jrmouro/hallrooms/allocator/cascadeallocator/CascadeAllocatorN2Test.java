/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.cascadeallocator;

import com.jrmouro.hallrooms.allocation.AllocationN2;
import com.jrmouro.hallrooms.allocator.AncestorAllocatorN2;
import com.jrmouro.hallrooms.allocator.SelectionAllocatorN2;
import com.jrmouro.hallrooms.allocator.genetic.GenenticAllocatorN2;
import com.jrmouro.hallrooms.allocator.hungarian.HungarianAllocatorN2;
import com.jrmouro.hallrooms.allocator.localsearch.LocalSearchAllocatorN2;
import com.jrmouro.hallrooms.allocator.localsearch.strategy.BestSearchStrategy;
import com.jrmouro.hallrooms.allocator.selection.OrderSelection;
import com.jrmouro.hallrooms.hallroomsinstance.HallRoomsReaderInstance;
import com.jrmouro.hallrooms.hallroomsinstance.ISplitter;
import java.io.File;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ronaldo
 */
public class CascadeAllocatorN2Test {

    /**
     * Test of append method, of class CascadeAllocatorN2.
     */
    @Test
    public void testAppend() {
        System.out.println("append");

        HallRoomsReaderInstance instance = new HallRoomsReaderInstance(
                new File("Inst-10salas-1374.txt"),
                new ISplitter() {
            @Override
            public String get() {
                return ",";
            }
        });

        GenenticAllocatorN2 geneticAllocator0 = new GenenticAllocatorN2(100, 20, 5, 1000, .5, .2, .5);
        GenenticAllocatorN2 geneticAllocator1 = new GenenticAllocatorN2(50, 10, 3, 5000, .5, .5, .5);
        SelectionAllocatorN2 hungarianAllocator = new HungarianAllocatorN2(new OrderSelection());
        AncestorAllocatorN2 localSearch = new LocalSearchAllocatorN2(new BestSearchStrategy());

        CascadeAllocatorN2 cascadeAllocator = new CascadeAllocatorN2();
        AllocationN2 alloc = cascadeAllocator
                .append(hungarianAllocator)
                .append(geneticAllocator0)
                .append(geneticAllocator1)
                .append(localSearch).allocate(instance);
        
        System.out.println(cascadeAllocator);
        System.out.println(alloc);
    }

}
