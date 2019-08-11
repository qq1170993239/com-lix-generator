package com.lix.generator.temp;

import java.util.List;

/**
 * Created by lixiang on 2019/8/8 0008.
 */
public interface BaseDAO<T> {

    /**
     * 新增一条数据
     *
     * @param entity
     * @return
     */
    Integer insert(T entity);

    /**
     * 删除一条数据
     *
     * @param entity
     * @return
     */
    Integer remove(T entity);

    /**
     * 更新一条数据
     * @param entity
     * @return
     */
    Integer update(T entity);

    /**
     * 查询一条数据
     * @return
     */
    List<T> select(T entity);

}
