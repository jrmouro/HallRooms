/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.naive;

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
public class NaiveAllocatorN2 extends SelectionAllocatorN2 {

    public NaiveAllocatorN2() {
    }

    public NaiveAllocatorN2(boolean isAncestor) {
        super(isAncestor);
    }

    public NaiveAllocatorN2(Selection selection) {
        super(selection);
    }

    public NaiveAllocatorN2(Selection selection, boolean isAncestor) {
        super(selection, isAncestor);
    }

    @Override
    public List<Integer> queue(IHallRoomsInstance instance) {
        if (this.isInitialized()) {
            List<Integer> ret = new ArrayList();
            for (int i = 0; i < instance.getRoomsNumber(); i++) {
                ret.add(i);
            }
            return ret;
        } else {
            try {
                throw new Exception("Do not initialized");
            } catch (Exception ex) {
                Logger.getLogger(NaiveAllocatorN2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "NaiveAllocatorN2  " + this.getSelection().toString();
    }

}
