/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.utils.experiment;

import com.jrmouro.hallrooms.HallRooms;
import com.jrmouro.hallrooms.utils.evaluable.IEvaluable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronaldo
 */
public class HallRoomsExperiment extends Experiment<Double> {

    public HallRoomsExperiment() {}

    public HallRoomsExperiment(HallRooms hallRooms) {
        super(hallRooms);
    }

    @Override
    public int compareTo(IEvaluable<Double> evaluable) {

        if (this.isInitialized()) {
            
            if (this.evaluation().equals(evaluable.evaluation())) {
                return 0;
            } else if (this.evaluation() > evaluable.evaluation()) {
                return 1;
            }
            
        } else {
            
            try {
                throw new Exception("Do not initialized");
            } catch (Exception ex) {
                Logger.getLogger(HallRoomsExperiment.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        return -1;

    }

    @Override
    public Double evaluationRate(IEvaluable<Double> other) {

        try {

            if (this.wasEvaluated() && other.wasEvaluated()) {
                return other.evaluation() / this.evaluation();
            } else {
                throw new Exception("Did not evalueted");
            }

        } catch (Exception ex) {

            Logger.getLogger(HallRoomsExperiment.class.getName()).log(Level.SEVERE, null, ex);

        }

        return null;

    }
    
    @Override
    public Double evaluationRate(Double value) {

        try {

            if (this.wasEvaluated()) {
                return value / this.evaluation();
            } else {
                throw new Exception("Did not evalueted");
            }

        } catch (Exception ex) {

            Logger.getLogger(HallRoomsExperiment.class.getName()).log(Level.SEVERE, null, ex);

        }

        return null;

    }

    @Override
    public void initialize(Object... o) {
        if (o != null) {
            for (Object object : o) {
                if (object instanceof HallRooms) {
                    super.initialize(object);
                    if (!((HallRooms) object).isInitialized()) {
                        ((HallRooms) object).initialize(o);
                    }
                }
            }
        }
    }

}
