/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.hungarian;

import com.jrmouro.hallrooms.allocation.AllocationN2;
import com.jrmouro.hallrooms.allocator.HallRoomsQueue;
import com.jrmouro.hallrooms.allocator.IHallRoomsQueue;
import com.jrmouro.hallrooms.allocator.SelectionAllocatorN2;
import com.jrmouro.hallrooms.allocator.selection.Selection;
import java.util.ArrayList;
import java.util.List;
import com.jrmouro.hallrooms.hallroomsinstance.IHallRoomsInstance;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronaldo
 */
public class HungarianAllocatorN2 extends SelectionAllocatorN2 {

    public HungarianAllocatorN2() {
    }

    public HungarianAllocatorN2(boolean isAncestor) {
        super(isAncestor);
    }

    public HungarianAllocatorN2(Selection selection) {
        super(selection);
    }

    public HungarianAllocatorN2(Selection selection, boolean isAncestor) {
        super(selection, isAncestor);
    }

    public IHallRoomsQueue queue(IHallRoomsInstance instance) {

        if (this.isInitialized()) {

            String sumType = "max";

            Integer[][] flowMatrix = instance.getFlow();

            //Hard-coded example.
            int n = flowMatrix.length;

            List<Integer> ret = new ArrayList();

            double[][] array = new double[n][n];
            int i = 0;
            for (Integer[] dd : flowMatrix) {
                int j = 0;
                for (Integer d : dd) {

                    if (i == j) {
                        array[i][j++] = -(Double.MAX_VALUE - 1.0);
                    } else {
                        array[i][j++] = (double) d;
                    }
                }
                i++;
            }

            int ind = 0;

            for (int j = 0; j < n - 1; j++) {

                int aux[][] = Hungarian.hgAlgorithm(array, sumType);

                for (int[] ii : aux) {
                    ret.add(ii[0]);
                    ret.add(ii[1]);
                    array[ii[0]][ii[1]] = -(Double.MAX_VALUE - (double) (j + 2));
                }

            }

            return reduce(ret, n);

        } else {
            try {
                throw new Exception("Do not initialized");
            } catch (Exception ex) {
                Logger.getLogger(HungarianAllocatorN2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;

    }

    private IHallRoomsQueue reduce(List<Integer> orig, int n) {
        
        IHallRoomsQueue ret = new HallRoomsQueue();

        boolean[] mark = new boolean[n];
        for (int i = 0; i < n; i++) {
            mark[i] = false;
        }

        for (Integer i : orig) {
            if (!mark[i]) {
                mark[i] = true;
                ret.add(i);
            }
        }

        return ret;
    }

    @Override
    public String toString() {
        return "HungarianAllocatorN2  " + this.getSelection().toString();
    }

    

}
