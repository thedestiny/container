package com.it.dao;


import com.it.pojo.Type;

import javax.inject.Named;

@Named
public class TypeDao extends BaseDao<Type,Integer>{

//    @Inject
//    private SessionFactory sessionFactory;
//
//    private Session getSession(){
//        return sessionFactory.getCurrentSession();
//    }
//
//    public List<Type> getType(){
//        Criteria criteria = getSession().createCriteria(Type.class);
//        return criteria.list();
//    }


}
