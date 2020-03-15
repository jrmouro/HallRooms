/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms;

import java.io.File;

/**
 *
 * @author ronaldo
 */
public class HallRooms {

    final IHallRoomsInstance instance;
    final AllocationN2 allocation;

    public HallRooms(File file, String splitter, IAllocatorN2 allocator) {
        this.instance = new HallRoomsInstance(file, splitter);
        this.allocation = allocator.allocate(instance);
    }
    
    public HallRooms(File file, String splitter) {
        this.instance = new HallRoomsInstance(file, splitter);
        this.allocation = new NaiveAllocatorN2().allocate(instance);
    }

    public HallRooms(IHallRoomsInstance instance) {
        this.instance = instance;
        this.allocation = new NaiveAllocatorN2().allocate(instance);
    }
       
    public Double getCost(){
        return AllocationN2.getCost(this.instance, this.allocation);
    }
    
    public Double[][] getCostMatrix(){
        return AllocationN2.getCostMatrix(this.instance, this.allocation);
    }

    public Double[][] getDistanceMatrix() {
        return AllocationN2.getDistanceMatrix(allocation);
    }

    public AllocationN2 getAllocation() {
        return allocation;
    }

    public IHallRoomsInstance getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        
        StringBuffer str = new StringBuffer("HallRooms\n");
        str.append(instance.toString()).append("\n");
        str.append(allocation.toString()).append("\n");
        
        str.append("\ndistanceMatrix:\n");
        for (Double d[] : this.getDistanceMatrix()) {
            str.append(" ");
            for (Double dd : d) {
                str.append(dd).append(" ");
            } 
            str.append("\n");
        }
        
        str.append("\ncostMatrix:\n");
        for (Double d[] : this.getCostMatrix()) {
            str.append(" ");
            for (Double dd : d) {
                str.append(dd).append(" ");
            } 
            str.append("\n");
        }
        
        str.append("\ntotalCost: ").append(this.getCost());
        
        return str.toString();
    }
    
    
    
    
    
}
