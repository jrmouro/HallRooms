/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms;

import java.io.File;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ronaldo
 */
public class HallRoomsTest {
    
    
    /**
     * Test of getCost method, of class HallRooms.
     */
    @Test
    public void testGetCost() {
        
        System.out.println("Test\n");
        
        HallRooms instance = new HallRooms(new File("Inst-4salas-1374.txt"), ",");
        
        System.out.println(instance);
        
    }
    
}
