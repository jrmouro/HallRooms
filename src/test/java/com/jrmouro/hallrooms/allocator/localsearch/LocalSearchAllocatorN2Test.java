/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.localsearch;

import com.jrmouro.hallrooms.HallRooms;
import com.jrmouro.hallrooms.allocator.localsearch.strategy.BestSearchStrategy;
import com.jrmouro.hallrooms.allocator.localsearch.strategy.SimulatedAnnealingSearchStrategy;
import com.jrmouro.hallrooms.allocator.naive.NaiveAllocatorN2;
import com.jrmouro.hallrooms.allocator.selection.OrderSelection;
import com.jrmouro.hallrooms.hallroomsinstance.HallRoomsReaderInstance;
import java.io.File;
import org.junit.jupiter.api.Test;
import com.jrmouro.hallrooms.hallroomsinstance.ISplitter;
import com.jrmouro.hallrooms.utils.experiment.Experiment;
import com.jrmouro.hallrooms.utils.experiment.HallRoomsExperiment;

/**
 *
 * @author ronaldo
 */
public class LocalSearchAllocatorN2Test {
   

    /**
     * Test of allocate method, of class LocalSearchAllocatorN2.
     */
    @Test
    public void testAllocateBestSearchStrategy() {
        
        System.out.println("\nBestSearchStrategy");
        
        Object[] o = {
            new File("Inst-10salas-1374.txt"),
            new ISplitter(){
                @Override
                public String get() {
                    return ",";
                }            
            },
            new HallRoomsReaderInstance(),
            new NaiveAllocatorN2(),
            new OrderSelection(),
            new LocalSearchAllocatorN2(false),
            new BestSearchStrategy(10),
            new HallRooms()
        };
        
        
        Experiment<Double> exp = new HallRoomsExperiment();
        
        exp.initialize(o);
        
        if(!exp.wasEvaluated())
            exp.evaluate();
        
        System.out.println(exp);        
        
    }
    
    /**
     * Test of allocate method, of class LocalSearchAllocatorN2.
     */
    @Test
    public void testAllocateSimulatedAnnealingSearchStrategy() {
        
        System.out.println("\nSimulatedAnnealingSearchStrategy");
        
        Object[] o = {
            new File("Inst-56salas-296220.txt"),
            new ISplitter(){
                @Override
                public String get() {
                    return " ";
                }            
            },
            new HallRoomsReaderInstance(),
            new NaiveAllocatorN2(),
            new OrderSelection(),
            new LocalSearchAllocatorN2(false),
            new SimulatedAnnealingSearchStrategy(100, 0.3),
            new HallRooms()
        };
        
        
        Experiment<Double> exp = new HallRoomsExperiment();
        
        exp.initialize(o);
        
        if(!exp.wasEvaluated())
            exp.evaluate();
        
        System.out.println(exp);        
        
    }

    
}
