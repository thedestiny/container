<%--
  Created by IntelliJ IDEA.
  User: xieyue
  Date: 2016/7/7
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="/static/adminlte/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p><shiro:principal property="realname"/></p>
            </div>
        </div>
        <ul class="sidebar-menu">
            <shiro:hasAnyRoles name="manager,employee">
                <li class="header">导航管理</li>
                <li class="treeview <c:if test="${param.menu == 'home'}">active</c:if>">
                    <a href="/home">
                        <i class="fa fa-home"></i> <span>首页</span>
                    </a>
                </li>
                <li class="treeview">
                    <a href="/program">
                        <i class="fa fa-dashboard"></i> <span>项目管理</span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="/static/adminlte/index.html"><i class="fa fa-circle-o"></i> 项目1</a></li>
                        <li><a href="/static/adminlte/index2.html"><i class="fa fa-circle-o"></i> 项目2</a></li>
                    </ul>
                </li>

                <li>
                    <a href="#">
                        <i class="fa fa-th"></i>
                        <span>待办事项</span>
                    <span class="pull-right-container">
                    <small class="label pull-right bg-green">急hot</small>
                    </span>
                    </a>
                </li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-pie-chart"></i>
                        <span>统计表</span>
                        <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="../charts/chartjs.html"><i class="fa fa-circle-o"></i> ChartJS</a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-users"></i>
                        <span>客户管理</span>
                        <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="../UI/general.html"><i class="fa fa-circle-o"></i> General</a></li>
                        <li><a href="../UI/icons.html"><i class="fa fa-circle-o"></i> Icons</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-calendar"></i> <span>日历</span>
                        <span class="pull-right-container">
                            <small class="label pull-right bg-red">3</small>
                            <small class="label pull-right bg-blue">17</small>
                            </span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-envelope"></i> <span>邮件</span>
                        <span class="pull-right-container">
                          <small class="label pull-right bg-yellow">12</small>
                          <small class="label pull-right bg-green">16</small>
                          <small class="label pull-right bg-red">5</small>
                        </span>
                    </a>
                </li>

                <li class="treeview <c:if test="${param.menu == 'notice'}">active</c:if>">
                    <a href="/notice">
                        <i class="fa fa-files-o"></i>
                        <span>公告</span>
                    </a>
                </li>

                <li class="treeview <c:if test="${param.menu == 'document'}">active</c:if>">
                    <a href="/document">
                        <i class="fa fa-file-text"></i>
                        <span>文档管理</span>
                    </a>
                </li>
            </shiro:hasAnyRoles>
            <shiro:hasRole name="administrator">
                <li class="treeview">
                    <a href="#"><i class="fa fa-cogs"></i> <span>系统管理</span> <i class="fa fa-angle-left pull-right"></i></a>
                    <ul class="treeview-menu">
                        <li><a href="/admin/manage">员工管理</a></li>
                        <li><a href="#">系统设置</a></li>
                    </ul>
                </li>
            </shiro:hasRole>
            <li class="header">其他</li>
            <li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a></li>
            <li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
