package com.jrmouro.hallrooms;

import java.io.File;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ronaldo
 */
public interface IHallRoomsInstance extends Initializable{
    public Integer getRoomsNumber();
    public Integer getFlow(int ind1, int ind2);
    public Double getWidth(int ind);
    public void read(File file, String splitter);
    public void write(File file, String splitter);
}
