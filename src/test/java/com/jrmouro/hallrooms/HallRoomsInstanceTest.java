/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms;

import com.jrmouro.hallrooms.hallroomsinstance.HallRoomsReaderInstance;
import com.jrmouro.hallrooms.hallroomsinstance.ISplitter;
import java.io.File;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ronaldo
 */
public class HallRoomsInstanceTest {
    
   


    /**
     * Test of read method, of class HallRoomsReaderInstance.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        
        HallRoomsReaderInstance instance = new HallRoomsReaderInstance(
                new File("Inst-56salas-296220.txt"),
                new ISplitter(){
                    @Override
                    public String get() {
                        return " ";
                    }
                });        
        
        System.out.println(instance);
    }

    
    
}
