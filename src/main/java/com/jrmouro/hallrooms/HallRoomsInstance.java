/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronaldo
 */
public class HallRoomsInstance implements IHallRoomsInstance {

    private Integer[][] flow = null;
    private Double[] width = null;
    
    public HallRoomsInstance() {}

    public HallRoomsInstance(Integer[][] flow, Double[] width) {
        this.flow = flow;
        this.width = width;
    }
    
    public HallRoomsInstance(File file, String splitter) {
        this.read(file, splitter);
    }

    @Override
    public Integer getRoomsNumber() {
        if (this.isInitialized()) {
            return this.width.length;
        }else{
            try {
                throw new Exception("Instance does not initialized");
            } catch (Exception ex) {
                Logger.getLogger(HallRoomsInstance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
    
    

    @Override
    public Double getWidth(int ind) {
        if (this.isInitialized()) {
            if (ind < this.width.length) {
                return this.width[ind];
            } else {
                try {
                    throw new Exception("Invalid indice");
                } catch (Exception ex) {
                    Logger.getLogger(HallRoomsInstance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                throw new Exception("Instance does not initialized");
            } catch (Exception ex) {
                Logger.getLogger(HallRoomsInstance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0.0;
    }

    @Override
    public Integer getFlow(int r1, int r2) {

        if (this.isInitialized()) {
            if (r1 < this.flow.length && r1 < this.flow.length) {
                return this.flow[r1][r2];
            } else {
                try {
                    throw new Exception("Invalid indice");
                } catch (Exception ex) {
                    Logger.getLogger(HallRoomsInstance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                throw new Exception("Instance does not initialized");
            } catch (Exception ex) {
                Logger.getLogger(HallRoomsInstance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return 0;

    }

    @Override
    public void read(File file, String splitter) {

        Scanner myReader;

        this.flow = null;
        this.width = null;

        try {

            myReader = new Scanner(file);

            Integer n = Integer.valueOf(myReader.nextLine().trim());

            if (n != null) {

                this.width = new Double[n];

                String line = myReader.nextLine().trim();
                String[] elem = line.split(splitter);

                if (elem.length >= n) {

                    int c = 0;
                    for (String str : elem) {
                        this.width[c++] = Double.valueOf(str);
                    }

                    this.flow = new Integer[n][];

                    int l = 0;
                    while (myReader.hasNextLine()) {
                        line = myReader.nextLine().trim();
                        elem = line.split(splitter);
                        if (elem.length >= n) {
                            this.flow[l] = new Integer[n];
                            c = 0;
                            for (String str : elem) {
                                this.flow[l][c++] = Integer.valueOf(str);
                            }
                        } else {
                            throw new Exception("Instance read fail");
                        }
                        l++;
                    }

                } else {
                    throw new Exception("Instance read fail");
                }

            } else {
                throw new Exception("Instance read fail");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HallRoomsInstance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(HallRoomsInstance.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void write(File file, String splitter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(Object... o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isInitialized() {
        return this.flow != null && this.width != null;
    }

    @Override
    public String toString() {
        return "HallRoomsInstance{\n" + "flow=\n" + flow + "\nwidth=" + width + "\n}";
    }
    
    

}
