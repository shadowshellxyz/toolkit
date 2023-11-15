package xyz.shadowshell.toolkit.template.store;

import xyz.shadowshell.toolkit.standard.exception.AppException;

/**
 * Storer
 *
 * @author shadow
 * @Date 2016/12/19
 */
public interface Storer<T> {

    void store(T element) throws AppException;
}
