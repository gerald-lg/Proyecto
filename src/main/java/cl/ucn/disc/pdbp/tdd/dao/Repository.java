/*
 * MIT License
 *
 * Copyright (c) 2020 Gerald Lopez Gutiérrez <gerald.lopez@alumnos.ucn.cl>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package cl.ucn.disc.pdbp.tdd.dao;

import com.j256.ormlite.stmt.QueryBuilder;

import java.util.List;

/**
 * Capa de acceso a los datos
 * @author Gerald López
 * @param <T> type of object
 * @param <K> type of primary K
 */
public interface Repository<T,K> {
    /**
     * @return a List of T
     */
    List<T> findAll();

    /**
     *
     * @param K to filter
     * @param value to search
     * @return the List of T
     */
    List<T> findAll(String K, Object value);

    /**
     * @return the {@link QueryBuilder}
     */
    QueryBuilder<T,K> getQuery();


    /**
     * @param id to find by ID
     */
    T findById(K id);

    /**
     * @param obj to save
     * @return true
     */
    public boolean create(T obj);

    /**
     * @param obj to update
     * @return true
     */
    public boolean update(T obj);

    /**
     * @param id to delete
     * @return true
     */
    public boolean delete (K id);


}
