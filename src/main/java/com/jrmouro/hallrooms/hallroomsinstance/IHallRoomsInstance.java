package com.jrmouro.hallrooms.hallroomsinstance;

import com.jrmouro.hallrooms.utils.Initializable;

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
    public Integer[][] getFlow();
    public Double getWidth(int ind);
    //public void read();
}
