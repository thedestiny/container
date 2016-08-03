package com.it.dao;



import com.it.pojo.Insurance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsuranceDao extends PrimaryDao<Insurance,Integer> {
    Logger logger = LoggerFactory.getLogger(InsuranceDao.class);
}
