/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms;

/**
 *
 * @author ronaldo
 */
public class NaiveAllocatorN2 implements IAllocatorN2{
    
    @Override
        public AllocationN2 allocate(IHallRoomsInstance instance) {
            
            int n = instance.getRoomsNumber();
            
            
            AllocationN2 ret = new AllocationN2(n);

            Double d1 = 0.0;
            Double d2 = 0.0;

            for (int i = 0; i < n; i++) {

                Double w = instance.getWidth(i);
                
                ret.getAllocMatrixN2()[i][0] = d1 + (w / 2.0);
                ret.getAllocMatrixN2()[i][1] = null;
                
                Double c1 = AllocationN2.getTotalCost(instance, AllocationN2.getDistanceMatrix(ret));
                
                ret.getAllocMatrixN2()[i][0] = null;
                ret.getAllocMatrixN2()[i][1] = d2 + (w / 2.0);
                Double c2 = AllocationN2.getTotalCost(instance, AllocationN2.getDistanceMatrix(ret));

                if (c1 < c2) {

                    ret.getAllocMatrixN2()[i][0] = d1 + (w / 2.0);
                    ret.getAllocMatrixN2()[i][1] = null;
                    d1 += w;

                } else {

                    
                    d2 += w;

                }

            }

            return ret;
        }

        
}
