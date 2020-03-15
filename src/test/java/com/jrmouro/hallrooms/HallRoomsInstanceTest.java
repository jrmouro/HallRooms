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
public class HallRoomsInstanceTest {
    
   


    /**
     * Test of read method, of class HallRoomsInstance.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        File file = new File("Inst-10salas-1374.txt");
        String splitter = ",";
        HallRoomsInstance instance = new HallRoomsInstance();
        instance.read(file, splitter);
        
        System.out.println(instance);
        
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    
    
}
