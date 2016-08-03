package com.it.dao;


import com.google.common.collect.Maps;
import com.it.pojo.Search;
import com.it.util.Page;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

public class PrimaryDao<T, PK extends Serializable> {
    Logger logger = LoggerFactory.getLogger(PrimaryDao.class);


    @Inject
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * entityClass 获取T的class
     */
    private Class<?> entityClass;


    public PrimaryDao() {
        ParameterizedType parameterizedType =
                (ParameterizedType) this.getClass().getGenericSuperclass();
        // java lang reflect
        entityClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
    }

    /**
     * 查找所有
     */
    public List<T> queryAll() {
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.addOrder(Order.asc("id"));
        return (List<T>) criteria.list();
    }

    /**
     * 保存对象
     */
    public void save(T entity) {
        getSession().saveOrUpdate(entity);
    }

    /**
     * 更新对象
     */
    public void update(T entity) {
        save(entity);
    }

    /**
     * 删除对象
     */
    public void delete(T entity) {
        getSession().delete(entity);
    }

    /**
     * 根据id 查找对象
     * @param id  id值
     * @return T
     */
    public T findById( PK id) {
        return (T)getSession().get(entityClass,id);
    }

    /**
     * 根据id删除
     */
    public void delete(PK id){
        delete(findById(id));
    }


    private Criterion buildCondition(String type, String property, Object value) {

        Class<?> obj = Restrictions.class;
        Method[] methods = obj.getMethods();
        Map<String, Method> map = Maps.newHashMap();
        for (Method method : methods) {
            System.out.println(method.getName());
            map.put(method.getName(), method);
        }

        if ("eq".equalsIgnoreCase(type)) {
            return Restrictions.eq(property, value);
        } else if ("like".equalsIgnoreCase(type)) {
            return Restrictions.like(property, value.toString(), MatchMode.ANYWHERE);
        } else if("ge".equalsIgnoreCase(type)){
            return Restrictions.ge(property,value);
        }else if("gt".equalsIgnoreCase(type)){
            return Restrictions.gt(property,value);
        }else if("le".equalsIgnoreCase(type)){
            return Restrictions.le(property,value);
        }else if("lt".equalsIgnoreCase(type)){
            return Restrictions.lt(property,value);
        }
        return null;
    }

    /**
     * 查询
     */
    public Page<T> findByPageNumber(Integer pageNo, Integer pageSzie) {
        Integer total = count().intValue();
        // 记录总数、每页记录条数、起始页
        Page<T> page = new Page<>(total, pageSzie, pageNo);
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.setFirstResult(page.getStart());
        criteria.setMaxResults(page.getBars());
        List<T> result = criteria.list();
        page.setItems(result);
        return page;
    }
    /**
     * 无参条件查询总数
     */
    public Long count() {
        Criteria criteria1 = getSession().createCriteria(entityClass);
        criteria1.setProjection(Projections.rowCount());
        return (Long) criteria1.uniqueResult();
    }


    /**
     * 条件查询总数
     */
    public Long count(Criteria criteria) {

        if (criteria == null) {
            Criteria criteria1 = getSession().createCriteria(entityClass);
            criteria1.setProjection(Projections.rowCount());
            return (Long) criteria1.uniqueResult();
        }
        ResultTransformer resultTransformer = criteria.ROOT_ENTITY;
        criteria.setProjection(Projections.rowCount());
        Long count = (Long) criteria.uniqueResult();
        criteria.setProjection(null);
        criteria.setResultTransformer(resultTransformer);
        return count;
    }
    /**
     * 条件查询
     */

    public Page<T> findByPageNumber(Integer pageNo, Integer pageSzie, List<Search> searchList) {
        Criteria criteria = buildCriteriaBySearchParams(searchList);
        Integer total = count(criteria).intValue();

        Page<T> page = new Page<>(total, pageSzie, pageNo);
        criteria.setFirstResult(page.getStart());
        criteria.setMaxResults(page.getBars());
        List<T> result = criteria.list();
        page.setItems(result);
        return page;

    }

    /**
     * 获取 Criteria 对象 含有搜索条件
     */

    private Criteria buildCriteriaBySearchParams(List<Search> searchList) {

        Criteria criteria = getSession().createCriteria(entityClass);
        if (searchList.size() == 0) {
            return criteria;
        }
        for (Search search : searchList) {
            String type = search.getType();
            String property = search.getProperty();
            Object value = search.getObject();
            if(value == null){
                continue;
            }
            if (property.contains("_or_")) {
                String[] array = property.split("_or_");
                Disjunction disjunction = Restrictions.disjunction();
                for (String str : array) {
                    disjunction.add(buildCondition(type,str,value));
                }
                criteria.add(disjunction);
            } else {
                criteria.add(buildCondition(type,property,value));

            }
        }
        return criteria;
    }



}
