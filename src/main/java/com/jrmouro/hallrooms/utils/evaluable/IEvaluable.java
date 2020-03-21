/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.utils.evaluable;

/**
 *
 * @author ronaldo
 */
public interface IEvaluable<T> extends Comparable<IEvaluable<T>> {
    public boolean wasEvaluated();
    public T evaluation();
    public boolean evaluate();
    public boolean re_evaluate();
    public T evaluationRate(IEvaluable<T> other);
    public T evaluationRate(T value);
    public void reset();
}
