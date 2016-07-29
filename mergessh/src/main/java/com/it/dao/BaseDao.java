package com.it.dao;


import com.google.common.collect.Maps;
import com.it.pojo.Search;
import com.it.util.Page;
import com.it.util.SmallUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;


import javax.inject.Inject;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

public class BaseDao<T, PK extends Serializable> {

    @Inject
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * entityClass 获取T的class
     */
    private Class<?> entityClass;


    public BaseDao() {
        ParameterizedType parameterizedType =
                (ParameterizedType) this.getClass().getGenericSuperclass();
        // java lang reflect
        entityClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
    }

    /**
     * 查询所有对象
     *
     * @return 返回对象集合
     */
    public List<T> queryAll() {
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.addOrder(Order.asc("id"));
        return (List<T>) criteria.list();
    }

    /**
     * 保存
     */
    public void save(T entity) {
        getSession().saveOrUpdate(entity);
    }

    /**
     * 删除
     */
    public void delete(T entity) {
        getSession().delete(entity);
    }


    public void update(T entity) {
        getSession().saveOrUpdate(entity);
    }

    /**
     * 根据id查找
     */
    public T findById(PK id) {
        return (T) getSession().get(entityClass, id);
    }

    /**
     * 根据id删除
     */
    public void delete(PK id) {
        getSession().delete(findById(id));
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
     * 无参
     */
    public Long count() {
        Criteria criteria1 = getSession().createCriteria(entityClass);
        criteria1.setProjection(Projections.rowCount());
        return (Long) criteria1.uniqueResult();
    }


    /**
     * 获取 Criteria 对象 含有搜索条件
     */

    private Criteria buildCriteriaBySearchParams(List<Search> searchList) {

        Criteria criteria = getSession().createCriteria(entityClass);

        if (searchList.size() == 0) {
            return criteria;
        }
        Field[] fields = entityClass.getDeclaredFields();
        Map<String,String> map = Maps.newHashMap();
        for (int i = 0; i < fields.length; i++) {
            String prop = fields[i].getName();
            String typename = fields[i].getType().getSimpleName();
            map.put(prop,typename);
        }
        for (Search search : searchList) {
            String type = search.getType();
            String property = search.getProperty();
            String value = search.getObject().toString();
            if (map.containsKey(property) && map.get(property).equals("String")) {
                if ("eq".equalsIgnoreCase(type)) {
                    criteria.add(Restrictions.eq(property, value));
                } else if ("like".equalsIgnoreCase(type)) {
                    criteria.add(Restrictions.like(property, value, MatchMode.ANYWHERE));
                }
            } else if (map.containsKey(property) && map.get(property).equals("Float")) {
                if ("ge".equalsIgnoreCase(type)) {
                    criteria.add(Restrictions.ge(property, Float.valueOf(value)));
                } else if ("gt".equalsIgnoreCase(type)) {
                    criteria.add(Restrictions.gt(property, Float.valueOf(value)));
                } else if ("le".equalsIgnoreCase(type)) {
                    criteria.add(Restrictions.le(property, Float.valueOf(value)));
                } else if ("lt".equalsIgnoreCase(type)) {
                    criteria.add(Restrictions.lt(property, Float.valueOf(value)));
                }
            } else {
                if(property.contains(".")){
                    // "."分割字符串要转译
                    String temple = property.split("\\.")[0];
                    System.out.println(temple);
                    if (map.containsKey(temple)
                            && (map.get(temple).equals("Publisher")
                            || map.get(temple).equals("Type"))
                            ) {
                        criteria.add(Restrictions.eq(property, Integer.parseInt(value)));
                    }
                }
                else{
                    if(!map.containsKey(property) && !property.contains(".") ){
                        criteria.add(Restrictions.or(
                                Restrictions.like("author",value,MatchMode.ANYWHERE),
                                Restrictions.like("title",value,MatchMode.ANYWHERE)
                        ));
                    }

                }
            }
        }
        return criteria;
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

}
