/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.utils.evaluable;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronaldo
 */
public abstract class HardEvaluable<T> implements IEvaluable<T> {

    protected abstract boolean processEvaluation();

    @Override
    public final boolean evaluate() {
        
        if (this.wasEvaluated()) {
            try {
                throw new Exception("Has already been evaluated");
            } catch (Exception ex) {
                Logger.getLogger(HardEvaluable.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
        
        this.reset();
        return this.processEvaluation();
        
    }

    @Override
    public final boolean re_evaluate() {
        
        if (this.wasEvaluated()) {
            return this.evaluate();
        } else {
            try {
                throw new Exception("Not yet evaluated");
            } catch (Exception ex) {
                Logger.getLogger(HardEvaluable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        
        return false;
        
    }

}
