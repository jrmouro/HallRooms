/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms;

import com.jrmouro.hallrooms.allocation.AllocationN2;
import com.jrmouro.hallrooms.allocator.AncestorAllocatorN2;
import com.jrmouro.hallrooms.hallroomsinstance.HallRoomsReaderInstance;
import com.jrmouro.hallrooms.allocator.IAllocatorN2;
import com.jrmouro.hallrooms.utils.evaluable.IEvaluable;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jrmouro.hallrooms.hallroomsinstance.ISplitter;
import com.jrmouro.hallrooms.utils.Initializable;
import com.jrmouro.hallrooms.hallroomsinstance.IHallRoomsInstance;
import com.jrmouro.hallrooms.utils.evaluable.HardEvaluable;

/**
 *
 * @author ronaldo
 */
public class HallRooms extends HardEvaluable<Double> implements Initializable {

    IHallRoomsInstance instance = null;
    AllocationN2 allocation = null;
    IAllocatorN2 allocator = null;

    public HallRooms(File file, ISplitter splitter, IAllocatorN2 allocator) {
        this.instance = new HallRoomsReaderInstance(file, splitter);
        this.allocator = allocator;
    }

    public HallRooms(IHallRoomsInstance instance, IAllocatorN2 allocator) {
        this.instance = instance;
        this.allocator = allocator;
    }
    
    public HallRooms() { }

    @Override
    public boolean wasEvaluated() {
        return this.allocation != null && this.allocation.wasEvaluated();
    }

    @Override
    public int compareTo(IEvaluable<Double> evaluable) {

        try {

            if (this.wasEvaluated()) {
                if (this.evaluation().equals(evaluable.evaluation())) {
                    return 0;
                } else if (this.evaluation() > evaluable.evaluation()) {
                    return 1;
                }
            } else {
                throw new Exception("Did not evalueted");
            }

        } catch (Exception ex) {
            Logger.getLogger(HallRooms.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public Double evaluation() {
        try {

            if (this.wasEvaluated()) {
                return this.allocation.evaluation();
            } else {
                throw new Exception("Did not evalueted");
            }

        } catch (Exception ex) {
            Logger.getLogger(HallRooms.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Double evaluationRate(IEvaluable<Double> other) {

        try {

            if (this.wasEvaluated() && other.wasEvaluated()) {
                return  other.evaluation() / this.evaluation();
            } else {
                throw new Exception("Did not evalueted");
            }

        } catch (Exception ex) {

            Logger.getLogger(HallRooms.class.getName()).log(Level.SEVERE, null, ex);

        }

        return null;

    }

    @Override
    protected boolean processEvaluation() {
        if (this.isInitialized()) {

            this.allocation = allocator.allocate(instance);
            if(!this.allocation.wasEvaluated()){
                this.allocation.evaluate();
            }
            return true;

        } else {
            try {
                throw new Exception("Do not initialized");
            } catch (Exception ex) {
                Logger.getLogger(HallRooms.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public void reset() {
        this.allocation = null;
    }
    
    


    public Double[][] getCostMatrix() {

        if (this.isInitialized()) {

            return AllocationN2.getCostMatrix(this.instance, this.allocation);

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

            return AllocationN2.getDistanceMatrix(allocation);

        } else {

            try {
                throw new Exception("Did not evaluated");
            } catch (Exception ex) {
                Logger.getLogger(HallRooms.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return null;
    }

    public AllocationN2 getAllocation() {

        if (this.wasEvaluated()) {

            return allocation;

        } else {

            try {
                throw new Exception("Did not evaluated");
            } catch (Exception ex) {
                Logger.getLogger(HallRooms.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return null;
    }

    public IHallRoomsInstance getInstance() {

        if (this.isInitialized()) {

            return instance;

        } else {
            try {
                throw new Exception("Do not initialized");
            } catch (Exception ex) {
                Logger.getLogger(HallRooms.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    @Override
    public String toString() {

        StringBuffer str = new StringBuffer("HallRooms\n\n");
        str.append(instance.toString()).append("\n\n");
        str.append(allocator.toString()).append("\n\n");

        if (this.wasEvaluated()) {
            str.append(allocation.toString()).append("\n\n");

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
        } else {
            str.append("\n\nDid not evalueted");
        }

        return str.toString();
    }

    @Override
    public void initialize(Object... o) {
        
        this.allocator = null;
        this.instance = null;

        if (o != null) {

            for (Object object : o) {

                if (object instanceof IHallRoomsInstance) {
                    this.instance = (IHallRoomsInstance) object;
                    if (!this.instance.isInitialized()) {
                        this.instance.initialize(o);
                    }
                }

                if (object instanceof AncestorAllocatorN2) {
                    
                    if (!((AncestorAllocatorN2) object).isAncestor()) {
                        this.allocator = (AncestorAllocatorN2) object;
                        if (!this.allocator.isInitialized()) {
                            this.allocator.initialize(o);
                        }
                    }
                    
                    if (((AncestorAllocatorN2) object).isAncestor() && this.allocator == null) {
                        this.allocator = (AncestorAllocatorN2) object;
                        if (!this.allocator.isInitialized()) {
                            this.allocator.initialize(o);
                        }
                    }
                    
                }
            }
        }
    }

    @Override
    public boolean isInitialized() {
        return this.instance != null && this.instance.isInitialized()
                //&& this.allocation != null && this.allocation.isInitialized()
                && this.allocator != null && this.allocator.isInitialized();
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

            Logger.getLogger(HallRooms.class.getName()).log(Level.SEVERE, null, ex);

        }

        return null;
    }
    
    
    

}
