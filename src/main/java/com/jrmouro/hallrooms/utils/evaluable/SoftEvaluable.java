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
public abstract class SoftEvaluable<T> implements IEvaluable<T> {

    protected abstract boolean processEvaluation();

    @Override
    public final boolean evaluate() {

        this.reset();
        return this.processEvaluation();

    }

    @Override
    public final boolean re_evaluate() {

        return this.evaluate();

    }

}
