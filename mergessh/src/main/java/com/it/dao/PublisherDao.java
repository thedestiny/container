package com.it.dao;

import com.it.pojo.Publisher;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class PublisherDao extends BaseDao<Publisher,Integer>{


}
