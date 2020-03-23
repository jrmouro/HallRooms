/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.cascadeallocator;

import com.jrmouro.hallrooms.allocation.AllocationN2;
import com.jrmouro.hallrooms.allocator.AncestorAllocatorN2;
import com.jrmouro.hallrooms.allocator.IAllocatorN2;
import com.jrmouro.hallrooms.hallroomsinstance.IHallRoomsInstance;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronaldo
 */
public class CascadeAllocatorN2 extends AncestorAllocatorN2 {

    final List<IAllocatorN2> cascade = new ArrayList();
    final List<Double> resultCascade = new ArrayList();

    public CascadeAllocatorN2() {}

    public CascadeAllocatorN2(IAllocatorN2 begin) {
        cascade.add(begin);
    }

    public CascadeAllocatorN2(IAllocatorN2 begin, boolean isAncestor) {
        super(isAncestor);
        cascade.add(begin);
    }

    public CascadeAllocatorN2 append(IAllocatorN2 other) {
        this.cascade.add(other);
        return this;
    }

    @Override
    public AllocationN2 allocate(IHallRoomsInstance instance) {

        AllocationN2 ret = null;
        

        if (this.isInitialized()) {
            
            this.resultCascade.clear();
            
            for (IAllocatorN2 iAllocatorN2 : this.cascade) {
                if (ret == null) {
                    ret = iAllocatorN2.allocate(instance);                    
                } else {
                    ret = iAllocatorN2.allocate(ret);
                }
                this.resultCascade.add(ret.evaluation());
            }

        } else {
            try {
                throw new Exception("Do not initialized");
            } catch (Exception ex) {
                Logger.getLogger(CascadeAllocatorN2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;
    }

    @Override
    public AllocationN2 allocate(AllocationN2 allocation) {

        AllocationN2 ret = null;

        if (this.isInitialized()) {
            
            this.resultCascade.clear();
            
            for (IAllocatorN2 iAllocatorN2 : this.cascade) {
                if (ret == null) {
                    ret = iAllocatorN2.allocate(allocation);
                } else {
                    ret = iAllocatorN2.allocate(ret);
                }
                this.resultCascade.add(ret.evaluation());
            }

        } else {
            try {
                throw new Exception("Do not initialized");
            } catch (Exception ex) {
                Logger.getLogger(CascadeAllocatorN2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;

    }

    @Override
    public void initialize(Object... o) {
        if (o != null) {
            for (Object object : o) {
                if (object instanceof IAllocatorN2) {
                    this.cascade.add((IAllocatorN2) object);
                }
            }
        }
    }

    @Override
    public boolean isInitialized() {
        return this.cascade.size() > 0;
    }

    @Override
    public String toString() {
        
        StringBuffer strB = new StringBuffer("CascadeAllocatorN2");
        
        for (int i = 0; i < cascade.size(); i++) {
            strB.append("\n  ").append(cascade.get(i).getClass().getSimpleName());
            if(i < this.resultCascade.size())
                strB.append(": ").append(this.resultCascade.get(i));
        }
        
        return strB.toString();
    }
    
    

}
