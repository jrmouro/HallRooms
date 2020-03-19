/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator;

import com.jrmouro.hallrooms.allocation.AllocationN2;
import com.jrmouro.hallrooms.allocator.selection.Selection;
import java.util.Iterator;
import java.util.List;
import com.jrmouro.hallrooms.hallroomsinstance.IHallRoomsInstance;
import com.jrmouro.hallrooms.utils.Initializable;

/**
 *
 * @author ronaldo
 */
public interface IAllocatorN2 extends Initializable{
    
    public AllocationN2 allocate(IHallRoomsInstance instance);
    
    public static AllocationN2 allocate(IHallRoomsInstance instance, Selection selection, List<Integer> queue) {

        selection.setQueue(queue);

        AllocationN2 ret = new AllocationN2(instance);

        Double d1 = 0.0;
        Double d2 = 0.0;

        for (Iterator<Integer> iterator = selection; iterator.hasNext();) {

            Integer e = iterator.next();

            Double w = instance.getWidth(e);

            ret.getAllocMatrixN2()[e][0] = d1 + (w / 2.0);
            ret.getAllocMatrixN2()[e][1] = null;

            Double c1 = AllocationN2.getTotalCost(instance, AllocationN2.getDistanceMatrix(ret));

            ret.getAllocMatrixN2()[e][0] = null;
            ret.getAllocMatrixN2()[e][1] = d2 + (w / 2.0);
            Double c2 = AllocationN2.getTotalCost(instance, AllocationN2.getDistanceMatrix(ret));

            int c = c1.compareTo(c2);

            if (c < 0) {

                ret.getAllocMatrixN2()[e][0] = d1 + (w / 2.0);
                ret.getAllocMatrixN2()[e][1] = null;
                d1 += w;
                ret.setCost(c1);

            } else {

                d2 += w;
                ret.setCost(c2);

            }

        }
        
        return ret;

    }

}
