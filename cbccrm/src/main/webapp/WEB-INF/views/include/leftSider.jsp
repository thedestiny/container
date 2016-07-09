<%--
  Created by IntelliJ IDEA.
  User: xieyue
  Date: 2016/7/7
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="/static/adminlte/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${user}</p>
            </div>
        </div>
        <ul class="sidebar-menu">
            <li class="header">导航管理</li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-dashboard"></i> <span>项目管理</span>
                        <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                        </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/static/adminlte/index.html"><i class="fa fa-circle-o"></i> 项目1</a></li>
                    <li><a href="/static/adminlte/index2.html"><i class="fa fa-circle-o"></i> 项目2</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-files-o"></i>
                    <span>公告</span>
            <span class="pull-right-container">
              <span class="label label-primary pull-right">4</span>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="../layout/top-nav.html"><i class="fa fa-circle-o"></i> 公告1</a></li>
                    <li><a href="../layout/boxed.html"><i class="fa fa-circle-o"></i> 公告2</a></li>
                    <li><a href="../layout/fixed.html"><i class="fa fa-circle-o"></i> 公告3</a></li>
                    <li><a href="../layout/collapsed-sidebar.html"><i class="fa fa-circle-o"></i>公告4</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="../widgets.html">
                    <i class="fa fa-th"></i> <span>待办事项</span>
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
                    <li><a href="../charts/morris.html"><i class="fa fa-circle-o"></i> Morris</a></li>
                    <li><a href="../charts/flot.html"><i class="fa fa-circle-o"></i> Flot</a></li>
                    <li><a href="../charts/inline.html"><i class="fa fa-circle-o"></i> Inline charts</a></li>
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
                    <li><a href="../UI/buttons.html"><i class="fa fa-circle-o"></i> Buttons</a></li>
                    <li><a href="../UI/sliders.html"><i class="fa fa-circle-o"></i> Sliders</a></li>
                    <li><a href="../UI/timeline.html"><i class="fa fa-circle-o"></i> Timeline</a></li>
                    <li><a href="../UI/modals.html"><i class="fa fa-circle-o"></i> Modals</a></li>
                </ul>
            </li>
            <li>
                <a href="../calendar.html">
                    <i class="fa fa-calendar"></i> <span>日历</span>
                        <span class="pull-right-container">
                            <small class="label pull-right bg-red">3</small>
                            <small class="label pull-right bg-blue">17</small>
                            </span>
                </a>
            </li>
            <li>
                <a href="../mailbox/mailbox.html">
                    <i class="fa fa-envelope"></i> <span>邮件</span>
                        <span class="pull-right-container">
                          <small class="label pull-right bg-yellow">12</small>
                          <small class="label pull-right bg-green">16</small>
                          <small class="label pull-right bg-red">5</small>
                        </span>
                </a>
            </li>


            <li><a href="/static/adminlte/documentation/index.html"><i class="fa fa-book"></i>
                <span>系统设置</span></a>
            </li>
            <li class="header">其他</li>
            <li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a></li>
            <li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
