/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocation;

import com.jrmouro.hallrooms.HallRooms;
import com.jrmouro.hallrooms.hallroomsinstance.HallRoomsReaderInstance;
import com.jrmouro.hallrooms.hallroomsinstance.ISplitter;
import java.io.File;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ronaldo
 */
public class AllocationN2Test {
    
    
    /**
     * Test of getSelection method, of class AllocationN2.
     */
    @Test
    public void testInstance10() {
        
        System.out.println("testInstance10");
        
        HallRoomsReaderInstance instance = new HallRoomsReaderInstance(
                new File("Inst-10salas-1374.txt"),
                new ISplitter() {
            @Override
            public String get() {
                return ",";
            }
        });
        
        Double[][] alloc ={
            {19.0, null}, //1
            { 7.5, null}, //2
            {26.5, null}, //3            
            {null, 11.0}, //4
            {null, 14.0}, //5           
            { 3.0, null}, //6
            {null, 19.0}, //7
            {null,  4.5}, //8            
            {null, 26.0}, //9
            {12.5, null}, //10
        };
        
        Double[][] allocLit ={
            {19.0, null}, //1
            { 7.5, null}, //2
            {26.5, null}, //3            
            {null, 11.0}, //4
            {null, 14.0}, //5           
            { 3.0, null}, //6
            {null, 19.0}, //7
            {null,  4.5}, //8            
            {null, 26.0}, //9
            {12.5, null}, //10
        };
        
        AllocationN2 allocation = new AllocationN2(instance, alloc);
        HallRooms hall = new HallRooms(allocation);
        
        System.out.println(hall);
        
        System.out.println("Metade do custo: "+allocation.getCost()/2.0);
        
        
    }

    
}
