/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.utils.experiment;

import com.jrmouro.hallrooms.utils.Initializable;
import com.jrmouro.hallrooms.utils.evaluable.HardEvaluable;
import com.jrmouro.hallrooms.utils.evaluable.IEvaluable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronaldo
 */
public abstract class Experiment<T> extends HardEvaluable<T> implements IExperiment<T> {

    private Long nanos = null;
    protected IEvaluable<T> evaluable = null;

    public Experiment() {}

    public Experiment(IEvaluable<T> evaluable) {
        this.evaluable = evaluable;
    }

    @Override
    public Long nanosTime() {
        if (this.wasEvaluated()) {
            return this.nanos;
        } else {
            try {
                throw new Exception("Did not evaluated");
            } catch (Exception ex) {
                Logger.getLogger(Experiment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public Double nanosRate(IExperiment<T> other) {

        try {

            if (other instanceof Experiment) {

                if (((Experiment)other).wasEvaluated()) {
                    if (this.wasEvaluated()) {
                        return Double.longBitsToDouble(this.nanosTime()) / Double.longBitsToDouble(other.nanosTime());
                    }
                }

                throw new Exception("Did not evaluated");

            } else {
                throw new Exception("Experiment works only with other Experiment");
            }

        } catch (Exception ex) {
            Logger.getLogger(Experiment.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public boolean wasEvaluated() {
        return this.nanos != null;
    }

    @Override
    public void reset() {
        this.nanos = null;
    }

    @Override
    protected boolean processEvaluation() {
        
        if (this.isInitialized()) {

            this.nanos = System.nanoTime();

            this.evaluable.evaluate();

            this.nanos = System.nanoTime() - this.nanos;

            return true;

        } else {
            try {
                throw new Exception("Do not initialized");
            } catch (Exception ex) {
                Logger.getLogger(Experiment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return false;
    }

    @Override
    public T evaluation() {
        try {
            
            if (this.wasEvaluated()) {
                return this.evaluable.evaluation();
            }

            throw new Exception("Did not evaluated");

        } catch (Exception ex) {
            Logger.getLogger(Experiment.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public String toString() {

        StringBuffer str = new StringBuffer("Experiment\n");
        if (this.wasEvaluated()) {
            str.append(evaluable.toString()).append("\n\n");
            str.append("nanos: ").append(nanos);
        } else {
            str.append("Did not evaluable");
        }

        return str.toString();

    }

    @Override
    public boolean isInitialized() {
        return this.evaluable != null;
    }

    @Override
    public void initialize(Object... o) {
        if (o != null) {
            for (Object object : o) {
                if (object instanceof IEvaluable) {
                    this.evaluable = (IEvaluable<T>) object;
                }
            }
        }
    }

}
