/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.genetic;

import com.jrmouro.genetic.chromosome.ChromosomeAbstract;
import com.jrmouro.genetic.fitnessfunction.FitnessFunction;
import com.jrmouro.genetic.integer.IntegerChromosome;
import com.jrmouro.genetic.integer.IntegerPopulation;
import com.jrmouro.genetic.integer.indexed.IndexedChromosome;
import com.jrmouro.genetic.integer.indexed.IndexedGeneticAlgorithm;
import com.jrmouro.hallrooms.allocation.AllocationN2;
import com.jrmouro.hallrooms.allocator.AncestorAllocatorN2;
import com.jrmouro.hallrooms.allocator.HallRoomsQueue;
import com.jrmouro.hallrooms.allocator.IAllocatorN2;
import com.jrmouro.hallrooms.allocator.IHallRoomsQueue;
import com.jrmouro.hallrooms.allocator.selection.OrderSelection;
import com.jrmouro.hallrooms.hallroomsinstance.IHallRoomsInstance;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class GenenticAllocatorN2 extends AncestorAllocatorN2{
    
    final int populationSize;
    final int populationReuse; 
    final int aritySelection;
    final int generations;
    final double crossoverRate;
    final double mutationRate;
    final double mutationRateGene;

    public GenenticAllocatorN2(
            int populationSize, 
            int populationReuse,
            int aritySelection, 
            int generations, 
            double crossoverRate, 
            double mutationRate, 
            double mutationRateGene) {
        this.populationSize = populationSize;
        this.populationReuse = populationReuse;
        this.aritySelection = aritySelection;
        this.generations = generations;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.mutationRateGene = mutationRateGene;
    }

    public GenenticAllocatorN2(
            int populationSize, 
            int populationReuse,  
            int aritySelection, 
            int generations, 
            double crossoverRate, 
            double mutationRate, 
            double mutationRateGene, 
            boolean isAncestor) {
        super(isAncestor);
        this.populationSize = populationSize;
        this.populationReuse = populationReuse;
        this.aritySelection = aritySelection;
        this.generations = generations;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.mutationRateGene = mutationRateGene;
    }

      
        
    public class AllocatorN2FitnessFunction implements FitnessFunction<Integer>{

        final IHallRoomsInstance instance;

        public AllocatorN2FitnessFunction(IHallRoomsInstance instance) {
            this.instance = instance;
        }
               
        @Override
        public double fitness(ChromosomeAbstract<Integer> chromosome) {
            IHallRoomsQueue queue = new HallRoomsQueue(chromosome.getGenotype());
            AllocationN2 allocation = IAllocatorN2.allocate(instance, new OrderSelection(), queue);
            if(!allocation.wasEvaluated()){
                allocation.evaluate();
            }
            return -allocation.evaluation();
        }
        
    }
    

    @Override
    public AllocationN2 allocate(IHallRoomsInstance instance) {
        
        IndexedGeneticAlgorithm ga = new IndexedGeneticAlgorithm(
                this.populationSize,
                this.populationReuse,
                instance.getRoomsNumber(), 
                instance.getRoomsNumber()-1, 
                this.aritySelection,
                this.generations, 
                new AllocatorN2FitnessFunction(instance),                
                this.crossoverRate,
                this.mutationRate,
                this.mutationRateGene
        );
        
        IntegerPopulation pop = (IntegerPopulation) ga.run();
        
        IntegerChromosome chrom = (IntegerChromosome) pop.getFittestChromosome();
        
        IHallRoomsQueue queue = new HallRoomsQueue(chrom.getGenotype());
        
        return IAllocatorN2.allocate(instance, new OrderSelection(), queue);
        
    }

    @Override
    public AllocationN2 allocate(AllocationN2 allocation) {
        
        IndexedChromosome fitnessChromosome = new IndexedChromosome(
                new AllocatorN2FitnessFunction(allocation.getInstance()), 
                IHallRoomsQueue.getQueueList(allocation.getQueue()));
        
        IndexedGeneticAlgorithm ga = new IndexedGeneticAlgorithm(
                fitnessChromosome,
                this.populationSize,
                this.populationReuse,
                allocation.getInstance().getRoomsNumber()-1,
                this.aritySelection,
                this.generations,                
                this.crossoverRate,
                this.mutationRate,
                this.mutationRateGene
        );
        
        IntegerPopulation pop = (IntegerPopulation) ga.run();
        
        IntegerChromosome chrom = (IntegerChromosome) pop.getFittestChromosome();
        
        IHallRoomsQueue queue = new HallRoomsQueue(chrom.getGenotype());
        
        return IAllocatorN2.allocate(allocation.getInstance(), new OrderSelection(), queue);
        
    }
    
    

    @Override
    public boolean isAncestor() {
        return false;
    }

    @Override
    public void initialize(Object... o) {
        
    }

    @Override
    public boolean isInitialized() {
        return true;
    }

    @Override
    public String toString() {
        return  "GenenticAllocatorN2" + 
                "\n  populationSize: " + populationSize + 
                "\n  populationReuse: " + populationReuse + 
                "\n  aritySelection: " + aritySelection + 
                "\n  generations: " + generations + 
                "\n  crossoverRate: " + crossoverRate + 
                "\n  mutationRate: " + mutationRate + 
                "\n  mutationRateGene: " + mutationRateGene;
    }

    
    
}
