/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.localsearch;

import com.jrmouro.hallrooms.allocator.localsearch.strategy.ISearchStrategy;
import com.jrmouro.hallrooms.allocation.AllocationN2;
import com.jrmouro.hallrooms.allocator.AncestorAllocatorN2;
import com.jrmouro.hallrooms.allocator.SelectionAllocatorN2;
import com.jrmouro.hallrooms.allocator.IAllocatorN2;
import java.util.List;
import com.jrmouro.hallrooms.hallroomsinstance.IHallRoomsInstance;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronaldo
 */
public final class LocalSearchAllocatorN2 extends AncestorAllocatorN2 {

    private SelectionAllocatorN2 initialAllocator = null;
    private ISearchStrategy strategy = null;  

    public LocalSearchAllocatorN2() { }
    
    public LocalSearchAllocatorN2(boolean isAncestor) {
        super(isAncestor);
    }
    
    public LocalSearchAllocatorN2(SelectionAllocatorN2 initialAllocator, ISearchStrategy strategy) {
        this.initialAllocator = initialAllocator;
        this.strategy = strategy;
        
    }

    public LocalSearchAllocatorN2(SelectionAllocatorN2 initialAllocator, ISearchStrategy strategy, boolean isAncestor) {
        super(isAncestor);
        this.initialAllocator = initialAllocator;
        this.strategy = strategy;
    }

    public SelectionAllocatorN2 getInitialAllocator() {
        return initialAllocator;
    }

    public void setInitialAllocator(SelectionAllocatorN2 initialAllocator) {
        this.initialAllocator = initialAllocator;
    }

    public ISearchStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(ISearchStrategy strategy) {
        this.strategy = strategy;
    }

        
    @Override
    public AllocationN2 allocate(IHallRoomsInstance instance) {
        
        if(this.isInitialized()){

            List<Integer> initial = this.initialAllocator.queue(instance);

            return this.strategy.search(instance, initial);
        
        }else{
            
            try {
                throw new Exception("Do not initialized");
            } catch (Exception ex) {
                Logger.getLogger(LocalSearchAllocatorN2.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

        return null;
    }

    @Override
    public String toString() {
        return "LocalSearchAllocatorN2"
                + "\n  allocator initial: " + initialAllocator.toString()
                + "\n  strategy: " + strategy.toString();
    }

    @Override
    public void initialize(Object... o) {
        
        if (o != null) {
            
            for (Object object : o) {
                    

                if (object instanceof ISearchStrategy) {
                    this.strategy = (ISearchStrategy) object;
                }

                if (object instanceof AncestorAllocatorN2) {
                    if(((AncestorAllocatorN2)object).isAncestor()){
                        this.initialAllocator = (SelectionAllocatorN2) object;
                        if (!this.initialAllocator.isInitialized()) {
                            this.initialAllocator.initialize(o);
                        }
                    }
                }

            }
        }
        
    }

    @Override
    public boolean isInitialized() {
        return this.initialAllocator != null && this.initialAllocator.isInitialized() && this.strategy != null;
    }

}
