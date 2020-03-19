/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.utils.experiment;

import com.jrmouro.hallrooms.utils.evaluable.IEvaluable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronaldo
 */
public abstract class ListExperiment<T> extends ArrayList<IExperiment<T>> implements IExperiment<T>{
        
    private Long nanos = null;
    private IExperiment<T> best = null;
    private IExperiment<T> worse = null;
    
    @Override
    public Long nanosTime() {
        if (this.wasEvaluated()) {
            return this.nanos;
        } else {
            try {
                throw new Exception("Did not evaluated");
            } catch (Exception ex) {
                Logger.getLogger(ListExperiment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public Double nanosRate(IExperiment other) {   
        if (this.wasEvaluated()) {
            return this.best.nanosRate(other);
        } else {
            try {
                throw new Exception("Did not evaluated");
            } catch (Exception ex) {
                Logger.getLogger(ListExperiment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public boolean wasEvaluated() {
        return this.nanos != null && this.best != null && this.worse != null;
    }

    @Override
    public void evaluate() {
        
        this.best = null;
        this.worse = null;
        
        this.nanos = System.nanoTime();

        for (IExperiment<T> exp : this) {
            
            exp.evaluate();
            
            if(this.best == null)
                this.best = exp;
            else
                if(this.best.compareTo(exp) < 0)
                    this.best = exp;
            
            if(this.worse == null)
                this.worse = exp;
            else
                if(this.worse.compareTo(exp) > 0)
                    this.worse = exp;
            
        }

        this.nanos = System.nanoTime() - this.nanos;

    }

    @Override
    public T evaluation() {        
        if (this.wasEvaluated()) {
            return this.best.evaluation();
        } else {
            try {
                throw new Exception("Did not evaluated");
            } catch (Exception ex) {
                Logger.getLogger(ListExperiment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public T evaluationRate(IEvaluable<T> other) {
        if (this.wasEvaluated()) {
            return this.best.evaluationRate(other);
        } else {
            try {
                throw new Exception("Did not evaluated");
            } catch (Exception ex) {
                Logger.getLogger(ListExperiment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public Long getNanos() {
        return nanos;
    }

    public IExperiment<T> getBest() {
        return best;
    }

    public IExperiment<T> getWorse() {
        return worse;
    }

    @Override
    public String toString() {
        return "ListExperiment\n" + "  nanos=" + nanos + "\n\nbest\n" + best + "\n\nworse\n" + worse;
    }

    @Override
    public int compareTo(IEvaluable<T> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
