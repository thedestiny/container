package com.qteng.mapper;

import com.qteng.pojo.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by xieyue on 2016/6/28.
 * AdminMapper
 */
public interface AdminMapper {

    Admin queryById(Integer id);
    List<Admin> queryAll();
    void delById(Integer id);
    void insertAdmin(Admin admin);
    void updateAdmin(Admin admin);
    // 默认返回受影响行数
    Integer insertA(Admin admin);
    List<Admin> queryByMap(Map<String,Object> map);
    List<Admin> queryByParams(String account,String email);
    List<Admin> queryByAno(@Param("account") String account, @Param("email")String email);
    Integer insertAdmins(List<Admin> adminList);
    List<Admin> findAdminByParams(Map<String,Object> map);
    List<Admin> findAdminByIds(List<Integer> id);



}
