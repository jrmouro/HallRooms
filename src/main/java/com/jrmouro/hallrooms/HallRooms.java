/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronaldo
 */
public class HallRooms {

    final IHallRoomsInstance instance;
    final Double[][] distanceMatrix;
    final Allocation allocation;

    public HallRooms(File file, String splitter, IAllocator allocator) {
        this.instance = new HallRoomsInstance(file, splitter);
        this.allocation = allocator.allocate(instance);
        this.distanceMatrix = Allocation.getDistanceMatrix(allocation);
    }
    
    public HallRooms(File file, String splitter) {
        this.instance = new HallRoomsInstance(file, splitter);
        this.allocation = new NaiveAllocator().allocate(instance);
        this.distanceMatrix = Allocation.getDistanceMatrix(allocation);
    }

    public HallRooms(IHallRoomsInstance instance) {
        this.instance = instance;
        this.allocation = new NaiveAllocator().allocate(instance);
        this.distanceMatrix = Allocation.getDistanceMatrix(allocation);
    }
    
    private static Double getAllocation(Double[][] allocation, Integer ind) {

        if (ind < allocation.length) {

            if (allocation[ind][0] != null) {
                return allocation[ind][0];
            } else {
                return allocation[ind][1];
            } 

        } else {

            try {
                throw new Exception("Invalid indice");
            } catch (Exception ex) {
                Logger.getLogger(HallRooms.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return 0.0;
    }

    public static Double[][] getDistanceMatrix(Double[][] allocation) {

        Double[][] ret = new Double[allocation.length][allocation.length];

        for (int i = 0; i < allocation.length; i++) {

            for (int j = 0; j < allocation.length; j++) {
                
                Double ai = getAllocation(allocation, i);
                Double aj = getAllocation(allocation, j);
                
                if(ai != null && aj != null)

                    ret[i][j] = Math.abs(ai - aj);
                
                else
                    
                    ret[i][j] = 0.0;

            }

        }

        return ret;

    }
    
    public Double getCost(){
        return Allocation.getCost(this.instance, this.distanceMatrix);
    }

    
}
