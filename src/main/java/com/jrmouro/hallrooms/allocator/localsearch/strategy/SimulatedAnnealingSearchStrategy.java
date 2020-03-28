/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.localsearch.strategy;

import com.jrmouro.hallrooms.allocation.AllocationN2;
import com.jrmouro.hallrooms.allocator.hallroomsqueue.IHallRoomsQueue;
import com.jrmouro.hallrooms.hallroomsinstance.IHallRoomsInstance;
import java.util.Random;

/**
 *
 * @author ronaldo
 */
public class SimulatedAnnealingSearchStrategy extends SearchSingleQueueStrategy {
    
    private static final Random random = new Random();
    private final int iterations;

    public SimulatedAnnealingSearchStrategy(int iterations, double moveRate) {
        super(moveRate);
        this.iterations = iterations;
    }
    
    @Override
    public AllocationN2 search(IHallRoomsInstance instance, IHallRoomsQueue initialQueue) {
        
        AllocationN2 atualAllocation = this.allocate(instance, initialQueue);
        
        AllocationN2 fitnessAllocation = atualAllocation;

        Double atualCost = atualAllocation.getCost(); 
        Double fitnessCost = fitnessAllocation.getCost();
        
        for (int i = 0; i < this.iterations; i++) {
            
            double temp = 1.0 - ((double)i/(double)this.iterations);
            
            IHallRoomsQueue neighbor = queue(instance, atualAllocation.getQueue());
            
            AllocationN2 neighborAllocation = this.allocate(instance, neighbor);
                    
            Double neighborCost = neighborAllocation.getCost();
            
            
            if(neighborCost < fitnessCost){
                fitnessAllocation = neighborAllocation;
                fitnessCost = neighborCost;
                i = 0;
            }
            
            double deltaPerTemp = (atualCost - neighborCost)/temp;
            
            double p = Math.min(Math.exp(deltaPerTemp), 1);
            
            double r = random.nextDouble();
            
            if(p > r){
                atualAllocation = neighborAllocation;
                atualCost = neighborCost;
            }            
            
        }

        return fitnessAllocation;

    }

        
    
    
    @Override
    public String toString() {
        return "SimulatedAnnealingSearchStrategy";
    }

}
