/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.genetic;

import com.jrmouro.hallrooms.HallRooms;
import com.jrmouro.hallrooms.allocator.naive.NaiveAllocatorN2;
import com.jrmouro.hallrooms.allocator.selection.RandomSelection;
import com.jrmouro.hallrooms.allocator.selection.Selection;
import com.jrmouro.hallrooms.hallroomsinstance.HallRoomsReaderInstance;
import com.jrmouro.hallrooms.hallroomsinstance.ISplitter;
import java.io.File;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ronaldo
 */
public class GenenticAllocatorN2Test {
      

    /**
     * Test of allocate method, of class GenenticAllocatorN2.
     */
    @Test
    public void testAllocate() {
        
        System.out.println("allocate");
        
        HallRoomsReaderInstance instance = new HallRoomsReaderInstance(
                new File("Inst-10salas-1374.txt"),
                new ISplitter(){
                    @Override
                    public String get() {
                        return ",";
                    }
                });
        
        GenenticAllocatorN2 geneticAllocator = new GenenticAllocatorN2(
            100, 
            20,             
            5, 
            1000, 
            .5, 
            .2, 
            .5         
        );
        
        Selection selection = new RandomSelection();
        HallRooms naiveHall = new HallRooms(instance, new NaiveAllocatorN2(selection));
        HallRooms genecticHall = new HallRooms(instance, geneticAllocator);
        naiveHall.evaluate();
        
        genecticHall.evaluate();
        
        System.out.println("\ninstance1");
        System.out.println(naiveHall);
        System.out.println("\n\ninstance1");
        System.out.println(genecticHall);
        
        
    }
}
