package com.wangmir.calliope.adapters.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import java.lang.reflect.ParameterizedType

abstract class BaseDao<T> {
    /**
     * Insert an object in the database.
     *
     * @param obj the object to be inserted.
     */
    @Insert(onConflict = REPLACE)
    abstract fun insert(obj: T)

    /**
     * Insert an array of objects in the database.
     *
     * @param obj the objects to be inserted.
     */
    @Insert(onConflict = REPLACE)
    abstract fun insert(vararg obj: T)

    /**
     * Update an object from the database.
     *
     * @param obj the object to be updated
     */
    @Update
    abstract fun update(obj: T)

    /**
     * Delete an object from the database
     *
     * @param obj the object to be deleted
     */
    @Delete
    abstract fun delete(obj: T)


    fun deleteAll(): Int {
        val query = SimpleSQLiteQuery(
            "delete from " + getTableName()
        )
        return doDeleteAll(query)
    }

    fun findAllValid(): List<T> {
        val query = SimpleSQLiteQuery(
            "select * from " + getTableName()
        )
        return doFindAllValid(query)
    }

    fun findById(id: Int): T? {
        val query = SimpleSQLiteQuery(
            "select * from " + getTableName() + " where id = ?", arrayOf<Any>(id))
        return doFind(query)
    }

    // NOTE: Room should not assign name of table explicitly to handle this.
    private fun getTableName(): String {
        val clazz = (javaClass.superclass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<*>
        // tableName = StringUtil.toSnakeCase(clazz.getSimpleName());
        return clazz.simpleName
    }

    @RawQuery
    protected abstract fun doFindAllValid(query: SupportSQLiteQuery): List<T>

    @RawQuery
    protected abstract fun doFind(query: SupportSQLiteQuery): T

    @RawQuery
    protected abstract fun doDeleteAll(query: SupportSQLiteQuery): Int
}
