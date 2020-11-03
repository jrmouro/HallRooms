/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.hallrooms.allocator.hallroomsqueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronaldo
 */
public class HallRoomsQueue implements IHallRoomsQueue {

    final List<Integer> list = new ArrayList();
    final Set<Integer> set = new HashSet();

    public HallRoomsQueue(Collection<Integer> col) {
        this.add(col);
    }

    public HallRoomsQueue() {
    }

    @Override
    public Iterator<Integer> iterator() {
        return this.list.iterator();
    }

    @Override
    public void add(Integer value) {
        if (this.set.add(value)) {
            this.list.add(value);
        } else {
            try {
                throw new Exception("Already exist item");
            } catch (Exception ex) {
                Logger.getLogger(HallRoomsQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Integer get() {
        if (!this.list.isEmpty()) {
            return this.list.get(0);
        }
        return null;
    }

    @Override
    public Integer remove() {
        Integer ret = null;
        if (!this.list.isEmpty()) {
            ret = this.list.get(0);
            this.list.remove(0);
            this.set.remove(ret);
        }
        return ret;
    }

    @Override
    public void clear() {
        this.list.clear();
        this.set.clear();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public Integer get(int index) {
        if (!this.list.isEmpty()) {
            if (index >= 0 && index < this.list.size()) {
                return this.list.get(index);
            } else {
                try {
                    throw new Exception("Invalid index");
                } catch (Exception ex) {
                    Logger.getLogger(HallRoomsQueue.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                throw new Exception("HallRoomsQueue is empty");
            } catch (Exception ex) {
                Logger.getLogger(HallRoomsQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public Integer remove(int index) {
        Integer ret = null;
        if (!this.list.isEmpty()) {
            if (index >= 0 && index < this.list.size()) {
                ret = this.list.get(index);
                this.list.remove(index);
                this.set.remove(ret);
            } else {
                try {
                    throw new Exception("Invalid index");
                } catch (Exception ex) {
                    Logger.getLogger(HallRoomsQueue.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                throw new Exception("HallRoomsQueue is empty");
            } catch (Exception ex) {
                Logger.getLogger(HallRoomsQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ret;
    }

    @Override
    public void add(Collection<Integer> col) {
        for (Iterator<Integer> iterator = col.iterator(); iterator.hasNext();) {
            Integer next = iterator.next();
            this.add(next);
        }
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public void set(int index, Integer value) {

        if (!this.list.isEmpty()) {
            if (index >= 0 && index < this.list.size()) {
                Integer v = this.list.get(index);
                this.set.remove(v);
                this.list.set(index, value);
                this.set.add(value);
            } else {
                try {
                    throw new Exception("Invalid index");
                } catch (Exception ex) {
                    Logger.getLogger(HallRoomsQueue.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                throw new Exception("HallRoomsQueue is empty");
            } catch (Exception ex) {
                Logger.getLogger(HallRoomsQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void shuffle() {
        Collections.shuffle(this.list);
    }

    @Override
    public void circle(int n) {
        
        if (!this.list.isEmpty()) {
            
            for (int i = 0; i < n; i++) {
                
                Integer v = this.list.remove(0);
                this.list.add(v);

            }
        }
    }

    @Override
    public String toString() {

        StringBuffer str = new StringBuffer("HallRoomsQueue\n  list: ");
        for (Integer integer : this.list) {
            str.append(integer).append(", ");
        }

        return str.toString();
    }

}
