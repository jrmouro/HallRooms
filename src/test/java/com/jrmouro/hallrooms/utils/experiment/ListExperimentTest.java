/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.utils.experiment;

import com.jrmouro.hallrooms.HallRooms;
import com.jrmouro.hallrooms.allocator.IAllocatorN2;
import com.jrmouro.hallrooms.allocator.genetic.GenenticAllocatorN2;
import com.jrmouro.hallrooms.allocator.hungarian.HungarianAllocatorN2;
import com.jrmouro.hallrooms.allocator.localsearch.LocalSearchAllocatorN2;
import com.jrmouro.hallrooms.allocator.localsearch.strategy.BestSearchStrategy;
import com.jrmouro.hallrooms.allocator.localsearch.strategy.FirstSearchStrategy;
import com.jrmouro.hallrooms.allocator.localsearch.strategy.ISearchStrategy;
import com.jrmouro.hallrooms.allocator.localsearch.strategy.RandomSearchStrategy;
import com.jrmouro.hallrooms.allocator.naive.NaiveAllocatorN2;
import com.jrmouro.hallrooms.allocator.selection.OrderSelection;
import com.jrmouro.hallrooms.allocator.selection.RandomRateSelection;
import com.jrmouro.hallrooms.allocator.selection.RandomSelection;
import com.jrmouro.hallrooms.allocator.selection.Selection;
import com.jrmouro.hallrooms.hallroomsinstance.HallRoomsReaderInstance;
import com.jrmouro.hallrooms.hallroomsinstance.ISplitter;
import com.jrmouro.hallrooms.utils.evaluable.IEvaluable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ronaldo
 */
public class ListExperimentTest {

    ListExperiment<Double> listExperiment = new ListExperiment<Double>() {
        @Override
        public Double evaluationRate(Double value) {
            try {

                if (this.wasEvaluated()) {
                    return value / this.evaluation();
                } else {
                    throw new Exception("Did not evalueted");
                }

            } catch (Exception ex) {

                Logger.getLogger(ListExperimentTest.class.getName()).log(Level.SEVERE, null, ex);

            }

            return null;
        }

        @Override
        public Double evaluationRate(IEvaluable<Double> other) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int compareTo(IEvaluable<Double> t) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        

    };

    HallRoomsReaderInstance instance = null;

    List<Selection> listSelection = new ArrayList();

    List<ISearchStrategy> listSearchStrategy = new ArrayList();

    public ListExperimentTest() {
    }

    @BeforeEach
    public void setUp() {

        ISplitter splitter = new ISplitter() {
            @Override
            public String get() {
                return ",";
            }
        };

        instance = new HallRoomsReaderInstance(new File("Inst-10salas-1374.txt"), splitter);

        this.listSelection.add(new OrderSelection());
        this.listSelection.add(new RandomSelection());
        this.listSelection.add(new RandomRateSelection(.5));
        this.listSelection.add(new RandomRateSelection(.2));

        this.listSearchStrategy.add(new BestSearchStrategy(10));
        this.listSearchStrategy.add(new FirstSearchStrategy(10));
        this.listSearchStrategy.add(new RandomSearchStrategy(10, System.nanoTime()));

        for (ISearchStrategy strategy : this.listSearchStrategy) {
            for (Selection selection : this.listSelection) {
                IAllocatorN2 alloc = new LocalSearchAllocatorN2(new NaiveAllocatorN2(selection), strategy);
                HallRooms hr = new HallRooms(instance, alloc);
                
                HallRoomsExperiment exp = new HallRoomsExperiment(hr);
                listExperiment.add(exp);
            }
        }

        for (Selection selection : this.listSelection) {

            if (!(selection instanceof RandomSelection)) {

                IAllocatorN2 alloc = new HungarianAllocatorN2(selection);
                HallRooms hr = new HallRooms(instance, alloc);
                HallRoomsExperiment exp = new HallRoomsExperiment(hr);
                listExperiment.add(exp);

            }

            IAllocatorN2 alloc = new NaiveAllocatorN2(selection);
            HallRooms hr = new HallRooms(instance, alloc);
            HallRoomsExperiment exp = new HallRoomsExperiment(hr);
            listExperiment.add(exp);

        }

        IAllocatorN2 alloc = new GenenticAllocatorN2(
                100,
                20,
                10,
                1000,
                .5,
                .2,
                .5
        );

        HallRooms hr = new HallRooms(instance, alloc);
        HallRoomsExperiment exp = new HallRoomsExperiment(hr);
        listExperiment.add(exp);

    }

    /**
     * Test of wasEvaluated method, of class ListExperiment.
     */
    @Test
    public void testEvaluate() {

        System.out.println("testEvaluate");

        this.setUp();

        if(!this.listExperiment.wasEvaluated())
            this.listExperiment.evaluate();

        System.out.println(this.listExperiment);

    }

}
