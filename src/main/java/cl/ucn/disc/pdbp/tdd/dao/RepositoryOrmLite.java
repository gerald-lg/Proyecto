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

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Implementación genérica del repositorio
 * @param <K> type of primary K
 * @param <T> type of object
 */
public final class RepositoryOrmLite<T,K> implements Repository<T, K> {

    /**
     * The Dao
     */
    private final Dao<T,K> theDao;

    /**
     * The constructor
     * @param connectionSource to connect to ORM
     * @param theClass to use as source
     */

    public RepositoryOrmLite(ConnectionSource connectionSource, Class<T> theClass){
        try{
            theDao = DaoManager.createDao(connectionSource,theClass);
        }catch (SQLException throwables){
            throw new RuntimeException(throwables);
        }
    }

    /**
     * @return a List of T
     */
    @Override
    public List<T> findAll() {
        try {
            return theDao.queryForAll();
        } catch (SQLException throwables) {
            throw  new RuntimeException(throwables);
        }
    }

    /**
     *
     * @param id to search
     * @return the object T with id.
     */
    @Override
    public T findById(K id) {
        try{
            return theDao.queryForId(id);
        } catch (SQLException throwables) {
            throw  new RuntimeException(throwables);
        }
    }

    /**
     * @param obj to save
     * @return true
     */
    @Override
    public boolean create(T obj) {
        try {
            return theDao.create(obj) == 1;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    /**
     * @param obj
     * @return true
     */
    @Override
    public boolean update(T obj) {
        try {
            return theDao.update(obj) == 1;
        } catch(SQLException throwables){
            throw new RuntimeException(throwables);
        }
    }

    /**
     * @param id to delete
     * @return true
     */
    @Override
    public boolean delete(K id) {
        try {
            return theDao.deleteById(id) == 1;
        } catch(SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}
