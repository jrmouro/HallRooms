/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.utils.experiment;

import com.jrmouro.hallrooms.utils.Initializable;

/**
 *
 * @author ronaldo
 */
public interface IExperiment<T> extends Initializable{
    
    public Long nanosTime();
    public Double nanosRate(IExperiment<T> other);
    
    
}
