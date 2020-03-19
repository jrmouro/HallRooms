/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.hallroomsinstance;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jrmouro.hallrooms.utils.Readable;

/**
 *
 * @author ronaldo
 */
public class HallRoomsReaderInstance implements IHallRoomsInstance, Readable {

    private Integer[][] flow = null;
    private Double[] width = null;
    private ISplitter splitter = null;
    private File file = null;

    public HallRoomsReaderInstance() {}

    public HallRoomsReaderInstance(File file, ISplitter splitter) {
        this.file = file;
        this.splitter = splitter;
        this.read();
    }

    @Override
    public Integer[][] getFlow() {

        if (this.isInitialized()) {

            return flow;

        } else {
            try {
                throw new Exception("Instance does not initialized");
            } catch (Exception ex) {
                Logger.getLogger(HallRoomsReaderInstance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    @Override
    public Integer getRoomsNumber() {
        if (this.isInitialized()) {
            return this.width.length;
        } else {
            try {
                throw new Exception("Instance does not initialized");
            } catch (Exception ex) {
                Logger.getLogger(HallRoomsReaderInstance.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(HallRoomsReaderInstance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                throw new Exception("Instance does not initialized");
            } catch (Exception ex) {
                Logger.getLogger(HallRoomsReaderInstance.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(HallRoomsReaderInstance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                throw new Exception("Instance does not initialized");
            } catch (Exception ex) {
                Logger.getLogger(HallRoomsReaderInstance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return 0;

    }

    @Override
    public void read() {

        if (this.isInitialized()) {

            Scanner myReader;

            this.flow = null;
            this.width = null;

            try {

                myReader = new Scanner(file);

                Integer n = Integer.valueOf(myReader.nextLine().trim());

                if (n != null) {

                    this.width = new Double[n];

                    String line = myReader.nextLine().trim();
                    String[] elem = line.split(splitter.get());

                    if (elem.length >= n) {

                        int c = 0;
                        for (String str : elem) {
                            this.width[c++] = Double.valueOf(str);
                        }

                        this.flow = new Integer[n][];

                        int l = 0;
                        while (myReader.hasNextLine()) {
                            line = myReader.nextLine().trim();
                            elem = line.split(splitter.get());
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
                Logger.getLogger(HallRoomsReaderInstance.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(HallRoomsReaderInstance.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else{
            try {
                throw new Exception("Do not initialized");
            } catch (Exception ex) {
                Logger.getLogger(HallRoomsReaderInstance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void initialize(Object... o) {

        this.file = null;
        this.splitter = null;

        if (o != null) {
            for (Object object : o) {
                if (object instanceof File) {
                    this.file = (File) object;
                }
                if (object instanceof ISplitter) {
                    this.splitter = (ISplitter) object;
                }
            }
        }

        if (this.file != null && this.splitter != null) {
            this.read();
        }

    }

    @Override
    public boolean isInitialized() {
        return /*this.flow != null && this.width != null &&*/ this.file != null && this.splitter != null;
    }

    @Override
    public String toString() {

        StringBuffer str = new StringBuffer("HallRoomsInstance\n\n");

        str.append("width: ");
        for (Double d : this.width) {
            str.append(d).append(" ");
        }

        str.append("\n\nflow:\n");
        for (Integer d[] : this.flow) {
            str.append(" ");
            for (Integer dd : d) {
                str.append(dd).append(" ");
            }
            str.append("\n");
        }

        return str.toString();

    }

}
