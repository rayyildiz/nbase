/*
 * nbase is a library for android application to develop base features
 * It includes data , network and tab view.
 * For more information http://code.google.com/p/nbase/
 * 
 * 
 * NBase is deliver with MIT licence
 */


package org.nbase.data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author rayyildiz
 */
public interface GenericDao <T extends EntityBase, PK extends Serializable> {
    T get(PK id);
    List<T> getAll();
    long insert(T entity);
    void update(T entity);
    void delete(T entity);
}
