/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.utils.experiment;

import com.jrmouro.hallrooms.HallRooms;
import com.jrmouro.hallrooms.allocator.naive.NaiveAllocatorN2;
import com.jrmouro.hallrooms.allocator.selection.OrderSelection;
import java.io.File;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ronaldo
 */
public class HallroomsExperimentTest {
    
    /**
     * Test of evaluation method, of class HallRoomsExperiment.
     */
    @Test
    public void testEvaluation() {
        
        System.out.println("testEvaluation\n\n");
        
        Experiment<Double> instance = new HallRoomsExperiment(
            new HallRooms(
                new File("Inst-4salas-1374.txt"), () -> ",",
                new NaiveAllocatorN2(new OrderSelection())
            )
        );
        
        if(!instance.wasEvaluated())
            instance.evaluate();
        
        System.out.println(instance);
        
    }

   
    
}
