/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocation;

import com.jrmouro.hallrooms.HallRooms;
import com.jrmouro.hallrooms.allocator.hallroomsqueue.IHallRoomsQueue;
import com.jrmouro.hallrooms.allocator.selection.Selection;
import com.jrmouro.hallrooms.utils.evaluable.IEvaluable;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jrmouro.hallrooms.hallroomsinstance.IHallRoomsInstance;
import com.jrmouro.hallrooms.utils.IValidity;
import com.jrmouro.hallrooms.utils.Initializable;
import com.jrmouro.hallrooms.utils.evaluable.HardEvaluable;
import java.util.Iterator;

/**
 *
 * @author ronaldo
 */
public class AllocationN2 extends HardEvaluable<Double> implements Initializable, IValidity {

    private IHallRoomsInstance instance = null;
    private Selection selection = null;
    private IHallRoomsQueue queue = null;
    private Double[][] allocMatrixN2 = null;
    private Double cost = null;
    
    public AllocationN2(IHallRoomsInstance instance, Double[][] allocMatrixN2) {

        this.instance = instance;
        this.allocMatrixN2 = allocMatrixN2;
        this.cost = getTotalCost(instance, this);

    }

    public AllocationN2(IHallRoomsInstance instance, Selection selection, IHallRoomsQueue queue) {

        this.instance = instance;
        this.queue = queue;
        this.selection = selection;

        this.allocMatrixN2 = AllocationN2.allocateMatrixN2(instance, selection, queue);
        this.cost = getTotalCost(instance, this);

    }

    public Selection getSelection() {
        return selection;
    }

    public IHallRoomsQueue getQueue() {
        return queue;
    }

    public IHallRoomsInstance getInstance() {
        return instance;
    }

    public Double[][] getAllocMatrixN2() {
        try {

            if (this.isInitialized()) {
                return allocMatrixN2;
            } else {
                throw new Exception("Do not initialized");
            }

        } catch (Exception ex) {
            Logger.getLogger(AllocationN2.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public boolean wasEvaluated() {
        return this.cost != null;
    }

    @Override
    public Double evaluation() {
        try {
            if (this.wasEvaluated()) {
                return this.cost;
            } else {
                throw new Exception("Did not evalueted");
            }
        } catch (Exception ex) {
            Logger.getLogger(AllocationN2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int compareTo(IEvaluable<Double> evaluable) {
        if (this.evaluation().equals(evaluable.evaluation())) {
            return 0;
        } else if (this.evaluation() > evaluable.evaluation()) {
            return 1;
        }
        return -1;
    }

    public Double getCost() {
        return this.evaluation();
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    protected boolean processEvaluation() {
        this.cost = getTotalCost(this.instance, this/*, true*/);
        return true;
    }

    @Override
    public void reset() {
        this.cost = null;
    }

    @Override
    public Double evaluationRate(IEvaluable<Double> other) {
        try {
            if (this.wasEvaluated() && other.wasEvaluated()) {
                return other.evaluation() / this.evaluation();
            } else {
                throw new Exception("Did not evalueted");
            }
        } catch (Exception ex) {
            Logger.getLogger(AllocationN2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Double evaluationRate(Double value) {
        try {
            if (this.wasEvaluated()) {
                return value / this.evaluation();
            } else {
                throw new Exception("Did not evalueted");
            }
        } catch (Exception ex) {
            Logger.getLogger(AllocationN2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Double[][] getCostMatrix() {

        if (this.isInitialized()) {

            return AllocationN2.getCostMatrix(this.instance, this);

        } else {
            try {
                throw new Exception("Do not initialized");
            } catch (Exception ex) {
                Logger.getLogger(HallRooms.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    public Double[][] getDistanceMatrix() {

        if (this.wasEvaluated()) {

            return AllocationN2.getDistanceMatrix(this);

        } else {

            try {
                throw new Exception("Did not evaluated");
            } catch (Exception ex) {
                Logger.getLogger(HallRooms.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return null;
    }


    public static Double getPartialCost(IHallRoomsInstance instance, AllocationN2 allocation, int ind) {

        Double[] distanceVector = getDistanceVector(allocation, ind);

        Double ret = 0.0;

        int n = instance.getRoomsNumber();

        for (int j = 0; j < n; j++) {
            ret += instance.getFlow(ind, j) * distanceVector[j];
        }

        return ret;

    }

    public static Double getTotalCost(IHallRoomsInstance instance, AllocationN2 allocation/*, boolean updateCost*/) {

        Double[][] distanceMatrix = getDistanceMatrix(allocation);

        Double ret = 0.0;

        int n = instance.getRoomsNumber();

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                ret += instance.getFlow(i, j) * distanceMatrix[i][j];

            }

        }

        //if (updateCost) {
        //allocation.setCost(ret);
        //}
        return ret;

    }

    public static Double getTotalCost(IHallRoomsInstance instance, Double[][] distanceMatrix) {

        Double ret = 0.0;

        int n = instance.getRoomsNumber();

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                ret += instance.getFlow(i, j) * distanceMatrix[i][j];

            }

        }

        return ret;

    }

    public static Double[][] getCostMatrix(IHallRoomsInstance instance, Double[][] distanceMatrix) {

        int n = instance.getRoomsNumber();

        Double[][] ret = new Double[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                ret[i][j] = (double) instance.getFlow(i, j) * distanceMatrix[i][j];

            }

        }

        return ret;

    }

    public static Double[][] getCostMatrix(IHallRoomsInstance instance, AllocationN2 allocation) {

        int n = instance.getRoomsNumber();

        Double[][] distanceMatrix = getDistanceMatrix(allocation);

        Double[][] ret = new Double[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                ret[i][j] = (double) instance.getFlow(i, j) * distanceMatrix[i][j];

            }

        }

        return ret;

    }

    private static Double getAllocation(AllocationN2 allocation, Integer ind) {

        /*if (ind < allocation.getAllocMatrixN2().length) {

            if (allocation.getAllocMatrixN2()[ind][0] != null) {
                return allocation.getAllocMatrixN2()[ind][0];
            } else {
                return allocation.getAllocMatrixN2()[ind][1];
            }

        } else {

            try {
                throw new Exception("Invalid indice");
            } catch (Exception ex) {
                Logger.getLogger(AllocationN2.class.getName()).log(Level.SEVERE, null, ex);
            }

        }*/
        //return 0.0;
        return getAllocation(allocation.allocMatrixN2, ind);

    }

    private static Double getAllocation(Double[][] allocationN2Matrix, Integer ind) {

        if (ind < allocationN2Matrix.length) {

            /* if ((allocationN2Matrix[ind][0] != null && allocationN2Matrix[ind][1] != null)
                    || (allocationN2Matrix[ind][0] == null && allocationN2Matrix[ind][1] == null)) {
                try {
                    throw new Exception("Invalid matrix");
                } catch (Exception ex) {
                    Logger.getLogger(AllocationN2.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {*/
            if (allocationN2Matrix[ind][0] != null) {
                return allocationN2Matrix[ind][0];
            } else {
                return allocationN2Matrix[ind][1];
            }
            //}

        } else {

            try {
                throw new Exception("Invalid indice");
            } catch (Exception ex) {
                Logger.getLogger(AllocationN2.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return 0.0;
    }

    public static Double[] getDistanceVector(AllocationN2 allocation, int ind) {

        int n = allocation.getAllocMatrixN2().length;

        Double[] ret = new Double[n];

        Double ai = getAllocation(allocation, ind);

        for (int j = 0; j < n; j++) {

            Double aj = getAllocation(allocation, j);

            if (ai != null && aj != null) {
                ret[j] = Math.abs(ai - aj);
            } else {
                ret[j] = 0.0;
            }

        }

        return ret;

    }

    public static Double[][] getDistanceMatrix(Double[][] allocationN2Matrix) {

        int n = allocationN2Matrix.length;

        Double[][] ret = new Double[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                Double ai = getAllocation(allocationN2Matrix, i);
                Double aj = getAllocation(allocationN2Matrix, j);

                if (ai != null && aj != null) {
                    ret[i][j] = Math.abs(ai - aj);
                } else {
                    ret[i][j] = 0.0;
                }

            }

        }

        return ret;

    }

    public static Double[][] getDistanceMatrix(AllocationN2 allocation) {

        int n = allocation.getAllocMatrixN2().length;

        Double[][] ret = new Double[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                Double ai = getAllocation(allocation, i);
                Double aj = getAllocation(allocation, j);

                if (ai != null && aj != null) {
                    ret[i][j] = Math.abs(ai - aj);
                } else {
                    ret[i][j] = 0.0;
                }

            }

        }

        return ret;

    }

    public static Double[][] allocateMatrixN2(IHallRoomsInstance instance, Selection selection, IHallRoomsQueue queue) {

        selection.setQueue(queue);

        Double[][] ret = new Double[instance.getRoomsNumber()][2];
        for (Double[] doubles : ret) {
            doubles[0] = null;
            doubles[1] = null;
        }

        Double d1 = 0.0;
        Double d2 = 0.0;

        int count1 = 0;
        int count2 = 0;

        for (Iterator<Integer> iterator = selection; iterator.hasNext();) {

            Integer e = iterator.next();

            Double w = instance.getWidth(e);

            ret[e][0] = d1 + (w / 2.0);
            ret[e][1] = null;
            Double c1 = AllocationN2.getTotalCost(instance, getDistanceMatrix(ret));

            ret[e][0] = null;
            ret[e][1] = d2 + (w / 2.0);
            Double c2 = AllocationN2.getTotalCost(instance, getDistanceMatrix(ret));

            int c = c1.compareTo(c2);

            if (c == 0) {

                if (count1 < count2) {

                    ret[e][0] = d1 + (w / 2.0);
                    ret[e][1] = null;
                    d1 += w;
                    //ret.setCost(c1);
                    count1++;

                } else {

                    d2 += w;
                    //ret.setCost(c2);
                    count2++;

                }

            } else if (c < 0) {

                ret[e][0] = d1 + (w / 2.0);
                ret[e][1] = null;
                d1 += w;
                //ret.setCost(c1);

            } else {

                d2 += w;
                //ret.setCost(c2);

            }

        }

        return ret;

    }

    @Override
    public String toString() {

        StringBuffer str = new StringBuffer();

        str.append("Allocation:\n");
        if(this.instance != null);
            str.append(instance.toString()).append("\n\n");
            
        str.append("allocMatrixN2:\n");
        int count = 0;
        for (Double[] d : this.allocMatrixN2) {

            str.append(" ").append(count++);

            if (d[0] != null) {
                str.append("\t").append(d[0]);
            }

            if (d[1] != null) {
                str.append("\t\t").append(d[1]);
            }

            str.append("\n");

        }
        
        if (this.wasEvaluated()) {            

            str.append("\ndistanceMatrix:\n");
            for (Double d[] : this.getDistanceMatrix()) {
                str.append(" ");
                for (Double dd : d) {
                    str.append(dd).append(" ");
                }
                str.append("\n");
            }

            str.append("\n\ncostMatrix:\n");
            for (Double d[] : this.getCostMatrix()) {
                str.append(" ");
                for (Double dd : d) {
                    str.append(dd).append(" ");
                }
                str.append("\n");
            }
            
            
            str.append("\nTotal cost: ").append(this.getCost());
        
        } else {
            str.append("\n\nDid not evalueted");
        }

        return str.toString();
    }

    @Override
    public void initialize(Object... o) {
        if (o != null) {
            for (Object object : o) {
                if (object instanceof IHallRoomsInstance) {
                    this.instance = (IHallRoomsInstance) object;
                    if (!this.instance.isInitialized()) {
                        this.instance.initialize(o);
                    }
                    if (this.instance.isInitialized()) {
                        this.allocMatrixN2 = new Double[instance.getRoomsNumber()][2];
                        for (Double[] a : this.allocMatrixN2) {
                            a[0] = null;
                            a[1] = null;
                        }
                    }

                }
            }
        }
    }

    @Override
    public boolean isInitialized() {
        return this.instance != null && this.instance.isInitialized() && this.allocMatrixN2 != null;
    }

    @Override
    public boolean isValid() {
        try {

            if (this.isInitialized()) {

                if (this.wasEvaluated()) {

                    if (this.instance.getRoomsNumber() == this.allocMatrixN2.length) {
                        for (Double[] doubles : this.allocMatrixN2) {
                            if(doubles.length == 2)
                                if ((doubles[0] != null && doubles[1] == null)||
                                        (doubles[0] == null && doubles[1] != null)) {
                                    return true;
                                }
                        }
                    }

                } else {
                    throw new Exception("Did not evalueted");
                }

            } else {
                throw new Exception("Do not initialized");
            }

        } catch (Exception ex) {
            Logger.getLogger(AllocationN2.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
