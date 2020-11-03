/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.selection;

import com.jrmouro.hallrooms.allocator.hallroomsqueue.IHallRoomsQueue;


public abstract class GiveBackSelection extends Selection {
    
    Integer back = null;

    public GiveBackSelection() { }

    public GiveBackSelection(IHallRoomsQueue queue) {
        super(queue);
    }

    @Override
    public Integer next() {
        
        this.back = super.next();
        
        return this.back;
        
    }
    
    
    void giveBack(){
        if( this.back != null )
            this.add(back);
    }
    
    
}
