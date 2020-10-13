/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.utils.experiment;

import com.jrmouro.hallrooms.allocator.IAllocatorN2;
import com.jrmouro.hallrooms.allocator.genetic.GenenticAllocatorN2;
import com.jrmouro.hallrooms.allocator.localsearch.LocalSearchAllocatorN2;
import com.jrmouro.hallrooms.allocator.localsearch.strategy.BestSearchStrategy;
import com.jrmouro.hallrooms.allocator.localsearch.strategy.FirstSearchStrategy;
import com.jrmouro.hallrooms.allocator.localsearch.strategy.RandomSearchStrategy;
import com.jrmouro.hallrooms.allocator.naive.NaiveAllocatorN2;
import com.jrmouro.hallrooms.allocator.selection.RandomSelection;
import com.jrmouro.hallrooms.hallroomsinstance.HallRoomsReaderInstance;
import com.jrmouro.hallrooms.hallroomsinstance.IHallRoomsInstance;
import com.jrmouro.hallrooms.hallroomsinstance.ISplitter;
import java.io.File;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ronaldo
 */
public class HallRoomsReapetExperimentTest {

    GenenticAllocatorN2 geneticAllocator = new GenenticAllocatorN2(
            60,
            20,
            5,
            100,
            .5,
            .2,
            .5
    );

    NaiveAllocatorN2 naiveAllocatorN2 = new NaiveAllocatorN2(new RandomSelection());

    LocalSearchAllocatorN2 bestLocalSearchAllocatorN2 = new LocalSearchAllocatorN2(
            new NaiveAllocatorN2(new RandomSelection()),
            new BestSearchStrategy()
    );

    LocalSearchAllocatorN2 firstLocalSearchAllocatorN2 = new LocalSearchAllocatorN2(
            new NaiveAllocatorN2(new RandomSelection()),
            new FirstSearchStrategy()
    );

    LocalSearchAllocatorN2 randomLocalSearchAllocatorN2 = new LocalSearchAllocatorN2(
            new NaiveAllocatorN2(new RandomSelection()),
            new RandomSearchStrategy(System.nanoTime())
    );

    public HallRoomsReapetExperimentTest() {
    }

    /**
     * Test of run method, of class HallRoomsReapetExperiment.
     */
    @Test
    public void testRun() {
        System.out.println("run");

        IHallRoomsInstance[] instances = {
            new HallRoomsReaderInstance(
                new File("Inst-10salas-1374.txt"),
                new ISplitter() {
                    @Override
                    public String get() {
                        return ",";
                    }
                }
            ), 
            new HallRoomsReaderInstance(
                new File("Inst-11salas-3439.txt"),
                new ISplitter() {
                    @Override
                    public String get() {
                        return ",";
                    }
                }
            ), 
//            new HallRoomsReaderInstance(
//                new File("Inst-56salas-296220.txt"),
//                new ISplitter() {
//                    @Override
//                    public String get() {
//                        return " ";
//                    }
//                }
//            )
        };

        IAllocatorN2[] allocators = {
            geneticAllocator,
            naiveAllocatorN2,
            bestLocalSearchAllocatorN2,
            firstLocalSearchAllocatorN2,
            randomLocalSearchAllocatorN2
        };

        HallRoomsReapetExperiment experiment = new HallRoomsReapetExperiment(
                instances, 
                allocators, 5);

        experiment.run();

        System.out.println(experiment);

    }

}
