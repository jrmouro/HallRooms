/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.utils.experiment;

import com.jrmouro.hallrooms.HallRooms;
import com.jrmouro.hallrooms.allocator.IAllocatorN2;
import com.jrmouro.hallrooms.hallroomsinstance.IHallRoomsInstance;
import com.jrmouro.hallrooms.utils.evaluable.IEvaluable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HallRoomsReapetExperiment implements Runnable {

    final int reapet;

    final double[][][] cost;
    final long[][][] time;

    final double[] bestCost;

    final double[][] meanCost;
    final long[][] meanTime;

    final IHallRoomsInstance[] instances;
    final IAllocatorN2[] allocators;
    
    final IAllocatorN2[] bestAllocators;

    public HallRoomsReapetExperiment(
            IHallRoomsInstance[] instances,
            IAllocatorN2[] allocators,
            int reapet) {
        super();

        this.instances = instances;
        this.allocators = allocators;
        this.reapet = reapet;

        this.cost = new double[instances.length][allocators.length][reapet];
        this.time = new long[instances.length][allocators.length][reapet];

        this.meanCost = new double[instances.length][allocators.length];
        this.meanTime = new long[instances.length][allocators.length];

        this.bestCost = new double[instances.length];
        
        this.bestAllocators = new IAllocatorN2[instances.length];

    }

    @Override
    public void run() {

        for (int i = 0; i < this.instances.length; i++) {

            System.out.println("Working on the instance:\n" + this.instances[i].toString());

            this.bestCost[i] = Double.MAX_VALUE;

            for (int j = 0; j < this.allocators.length; j++) {

                System.out.println("Working  on the allocator:\n" + this.allocators[j].toString());

                double sumCost = 0;
                long sumTime = 0;

                for (int k = 0; k < this.reapet; k++) {

                    System.out.println("Working  on the repetition: " + k);

                    IEvaluable<Double> hallRooms = new HallRooms(this.instances[i], this.allocators[j]);

                    long t = System.nanoTime();

                    boolean b = hallRooms.evaluate();

                    t = System.nanoTime() - t;

                    if (b) {

                        this.cost[i][j][k] = hallRooms.evaluation();
                        this.time[i][j][k] = t;

                        if (this.cost[i][j][k] < this.bestCost[i]) {
                            this.bestCost[i] = this.cost[i][j][k];
                            this.bestAllocators[i] = this.allocators[j];
                        }

                        sumCost += this.cost[i][j][k];
                        sumTime += t;

                        System.out.println("\nResult");
                        System.out.println("\tCost: " + this.cost[i][j][k]);
                        System.out.println("\tTime: " + t);

                    } else {
                        try {
                            throw new Exception("Evaluation Error");
                        } catch (Exception ex) {
                            Logger.getLogger(HallRoomsReapetExperiment.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }

                this.meanCost[i][j] = sumCost / (double) this.reapet;
                this.meanTime[i][j] = sumTime / (long) this.reapet;

            }

        }
    }

    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer("HallRoomsReapetExperiment{");
        sb.append("\nreapet=").append(reapet);

        sb.append("\nbestCost=");
        for (int i = 0; i < this.instances.length; i++) {

            sb.append("\n\t").append(String.format("%.2f", bestCost[i]));
            sb.append(" (").append(this.bestAllocators[i].getClass().getName());
            sb.append(" )");

        }

        sb.append("\nmeanCost=");
        for (int i = 0; i < this.instances.length; i++) {
            sb.append("\n");
            for (int j = 0; j < this.allocators.length; j++) {

                sb.append("\t").append(String.format("%.2f", meanCost[i][j]));
                
            }

        }

        sb.append("\n\nmeanTime=");
        for (int i = 0; i < this.instances.length; i++) {
            sb.append("\n");
            for (int j = 0; j < this.allocators.length; j++) {

                sb.append("\t").append(meanTime[i][j]);

            }

        }

        sb.append("\n}");

        return sb.toString();

        //return "HallRoomsReapetExperiment{" + "reapet=" + reapet + ", meanCost=" + meanCost + ", meanTime=" + meanTime + '}';
    }

}
