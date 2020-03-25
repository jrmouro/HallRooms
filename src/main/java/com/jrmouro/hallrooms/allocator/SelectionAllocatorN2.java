/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator;

import com.jrmouro.hallrooms.allocator.hallroomsqueue.IHallRoomsQueue;
import com.jrmouro.hallrooms.allocator.selection.Selection;
import com.jrmouro.hallrooms.allocation.AllocationN2;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jrmouro.hallrooms.hallroomsinstance.IHallRoomsInstance;

/**
 *
 * @author ronaldo
 */
public abstract class SelectionAllocatorN2 extends AncestorAllocatorN2{

    private Selection selection = null;
    
    public SelectionAllocatorN2() { }

    public SelectionAllocatorN2(boolean isAncestor) {
        super(isAncestor);
    }
    
    public SelectionAllocatorN2(Selection selection) {
        this.selection = selection;
    }

    public SelectionAllocatorN2(Selection selection, boolean isAncestor) {
        super(isAncestor);
        this.selection = selection;
    }

    public Selection getSelection() {
        return selection;
    }

    public void setSelection(Selection selection) {
        this.selection = selection;
    }

    
    @Override
    public AllocationN2 allocate(IHallRoomsInstance instance) {

        if (this.isInitialized()) {

            IHallRoomsQueue queue = queue(instance);

            return IAllocatorN2.allocate(instance, this.selection, queue);

        }else{
            
            try {
                throw new Exception("Do not initialized");
            } catch (Exception ex) {
                Logger.getLogger(SelectionAllocatorN2.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        return null;
    }
    
    @Override
    public AllocationN2 allocate(AllocationN2 allocation) {
        return this.allocate(allocation.getInstance());
    }

    @Override
    public void initialize(Object... o) {
        if(o != null)
            for (Object object : o) {
                if(object instanceof Selection)
                    this.selection = (Selection) object;
            }
    }

    @Override
    public boolean isInitialized() {
        return this.selection != null;
    }

    public abstract IHallRoomsQueue queue(IHallRoomsInstance instance);

}
