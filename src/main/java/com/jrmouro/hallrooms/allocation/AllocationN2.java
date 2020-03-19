/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocation;

import com.jrmouro.hallrooms.utils.evaluable.IEvaluable;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jrmouro.hallrooms.hallroomsinstance.IHallRoomsInstance;
import com.jrmouro.hallrooms.utils.Initializable;

/**
 *
 * @author ronaldo
 */
public class AllocationN2 implements IEvaluable<Double>, Initializable {

    private IHallRoomsInstance instance = null;
    private Double[][] allocMatrixN2 = null;
    private Double cost = null;

    public AllocationN2(IHallRoomsInstance instance) {
        this.instance = instance;
        if (this.instance.isInitialized()) {
            this.allocMatrixN2 = new Double[instance.getRoomsNumber()][2];
            for (Double[] a : this.allocMatrixN2) {
                a[0] = null;
                a[1] = null;
            }
        }
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
    public void evaluate() {

        try {

            if (this.isInitialized()) {
                this.cost = getTotalCost(this.instance, this, true);
            } else {
                throw new Exception("Do not initialized");
            }

        } catch (Exception ex) {
            Logger.getLogger(AllocationN2.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public static Double getPartialCost(IHallRoomsInstance instance, AllocationN2 allocation, int ind) {

        Double[] distanceVector = getDistanceVector(allocation, ind);

        Double ret = 0.0;

        int n = instance.getRoomsNumber();

        for (int j = 0; j < n; j++) {
            ret += instance.getFlow(ind, j) * distanceVector[j];
        }

        return ret;

    }

    public static Double getTotalCost(IHallRoomsInstance instance, AllocationN2 allocation, boolean updateCost) {

        Double[][] distanceMatrix = getDistanceMatrix(allocation);

        Double ret = 0.0;

        int n = instance.getRoomsNumber();

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                ret += instance.getFlow(i, j) * distanceMatrix[i][j];

            }

        }

        if (updateCost) {
            allocation.setCost(ret);
        }

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

    @Override
    public String toString() {

        StringBuffer str = new StringBuffer();

        str.append("Allocation:\n");
        for (Double[] d : this.allocMatrixN2) {

            if (d[0] != null) {
                str.append("  ").append(d[0]);
            }

            if (d[1] != null) {
                str.append("\t").append(d[1]);
            }

            str.append("\n");

        }

        if (this.wasEvaluated()) {
            str.append("\nCost: ").append(this.getCost());
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

}
