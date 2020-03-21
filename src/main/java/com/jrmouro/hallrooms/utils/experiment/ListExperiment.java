/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.utils.experiment;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronaldo
 */
public abstract class ListExperiment<T> extends Experiment<T> {

    List<Experiment<T>> experiments = new ArrayList();
    private Long nanos = null;
    private Experiment<T> best = null;
    private Experiment<T> worse = null;
    
    public void add(Experiment<T> exp){
        this.experiments.add(exp);
    }

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
    protected boolean processEvaluation() {
        
        if (this.isInitialized()) {

            this.best = null;
            this.worse = null;
            this.nanos = System.nanoTime();

            for (Experiment<T> exp : this.experiments) {
                
                if(!exp.wasEvaluated())
                    exp.evaluate();

                if (this.best == null) {
                    this.best = exp;
                } else if (this.best.compareTo(exp) < 0) {
                    this.best = exp;
                }

                if (this.worse == null) {
                    this.worse = exp;
                } else if (this.worse.compareTo(exp) > 0) {
                    this.worse = exp;
                }

            }

            this.nanos = System.nanoTime() - this.nanos;

            return true;

        }else{
            
            try {
                throw new Exception("Do not initialized");
            } catch (Exception ex) {
                Logger.getLogger(ListExperiment.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        return false;
    }

    @Override
    public void reset() {
        this.nanos = null;
        this.best = null;
        this.worse = null;
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
    public boolean isInitialized() {
        return true;
    }
    
    

}
