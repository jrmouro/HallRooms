/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronaldo
 */
public class Allocation {
    
    private final Double[][] allocMatrixN2;
    
    public Allocation(int n) {
        this.allocMatrixN2 = new Double[n][2];
        for (Double[] a : this.allocMatrixN2) {
            a[0] = null;
            a[1] = null;
        }
    }

    public Allocation(Double[][] alloc) {
        this.allocMatrixN2 = alloc;
    }

    public Double[][] getAllocMatrixN2() {
        return allocMatrixN2;
    } 
    
    public static Double getCost(IHallRoomsInstance instance, Double[][] distanceMatrix) {

        Double ret = 0.0;

        int n = instance.getRoomsNumber();

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                ret += instance.getFlow(i, j) * distanceMatrix[i][j];

            }

        }

        return ret;

    }
    
    private static Double getAllocation(Allocation allocation, Integer ind) {

        if (ind < allocation.getAllocMatrixN2().length) {

            if (allocation.getAllocMatrixN2()[ind][0] != null) {
                return allocation.getAllocMatrixN2()[ind][0];
            } else {
                return allocation.getAllocMatrixN2()[ind][1];
            } 

        } else {

            try {
                throw new Exception("Invalid indice");
            } catch (Exception ex) {
                Logger.getLogger(Allocation.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return 0.0;
    }
    
    public static Double[][] getDistanceMatrix(Allocation allocation) {
        
        int n = allocation.getAllocMatrixN2().length;

        Double[][] ret = new Double[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                
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
    
}
