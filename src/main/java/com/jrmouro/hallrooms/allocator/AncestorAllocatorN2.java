/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator;

import com.jrmouro.hallrooms.utils.IAncestor;
import com.jrmouro.hallrooms.utils.Initializable;

/**
 *
 * @author ronaldo
 */
public abstract class AncestorAllocatorN2 implements IAllocatorN2, IAncestor{

    final boolean isAncestor;

    public AncestorAllocatorN2() {
        this.isAncestor = true;
    }
    
    public AncestorAllocatorN2(boolean isAncestor) {
        this.isAncestor = isAncestor;
    }    
    

    @Override
    public boolean isAncestor() {
        return this.isAncestor;
    }
    
}
