package com.it.dao;



import com.it.pojo.Department;
import com.it.pojo.Disease;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DepartmentDao extends PrimaryDao<Department,Integer> {
    Logger logger = LoggerFactory.getLogger(DepartmentDao.class);
}
