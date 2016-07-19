<%--
  Created by IntelliJ IDEA.
  User: xieyue
  Date: 2016/7/7
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Home</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/adminlte/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="/static/adminlte/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/adminlte/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="/static/adminlte/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="/static/adminlte/plugins/fullcalendar/fullcalendar.min.css">
    <link rel="stylesheet" href="/static/adminlte/plugins/fullcalendar/fullcalendar.print.css" media="print">
    <link rel="stylesheet" href="/static/adminlte/plugins/colorpicker/bootstrap-colorpicker.css">
    <style>
        #add {
            width: 402px;
            height: 238px;
            z-index: 999;
            /*attr("z-index",999)*/
        }
    </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/mainHeader.jsp" %>
    <jsp:include page="../include/leftSider.jsp">
        <jsp:param name="menu" value="shedule"/>
    </jsp:include>

    <div class="content-wrapper" >
        <section class="content-header" id="header">
            <h1>
                待办事项
                <small>安排好当天的工作</small>
            </h1>
        </section>
        <section class="content">
            <div class="row">
                <div class="col-md-9">
                    <div class="box box-primary">
                        <div class="box-body no-padding">
                            <div id="calendar" class="fc fc-ltr fc-unthemed">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="box box-danger">
                        <div class="box-header with-border">
                            <h3 class="box-title">已经延期的事项</h3>
                        </div>
                        <div class="box-body">
                            <ul class="todo-list">
                                <c:forEach items="${taskList}" var="task">
                                    <li>
                                        <input type="checkbox" class="done" value="${task.id}">
                                        <span class="text">${task.title}</span>
                                        <div class="tools">
                                            <a href="javascript:;" rel="${task.id}" class="del"><i class="fa fa-trash-o"></i></a>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

        </section>
        <div id="add" style="visibility: hidden">
            <section class="content">
                <div class="box box-primary" style="border: 1px solid cyan;position: absolute;">
                    <div class="box-header with-border" style="background-color:#3c8dbc">
                        <h3 class="box-title">新增待办事项</h3>
                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                    title="Collapse">
                                <i class="fa fa-minus"></i></button>
                            <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip"
                                    title="Remove">
                                <i class="fa fa-times"></i></button>
                        </div>
                    </div>
                    <div class="box-body">
                        <form id="addForm">
                           <%--// <input type="text" name="userid" value="" hidden >--%>
                            <div class="form-group">
                                <label>事项内容</label>
                                <input autofocus type="text" id="title" name="title" class="form-control"
                                       placeholder="例如:晚上一起吃饭">
                            </div>
                            <div class="form-group">
                                <label>起始时间</label>
                                <div class="form-inline">
                                    <input type="text" id="start_time" name="start" style="width: 120px;height: 25px" >
                                    <label>-</label>
                                    <input type="text" id="end_time" name="end" style="width: 120px;height: 25px" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label>提醒时间</label>
                                <div>
                                    <select name="hour" style="width: 120px;height: 25px">
                                        <option value=""></option>
                                        <option value="0">0</option>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                        <option value="6">6</option>
                                        <option value="7">7</option>
                                        <option value="8">8</option>
                                        <option value="9">9</option>
                                        <option value="10">10</option>
                                        <option value="11">11</option>
                                        <option value="12">12</option>
                                        <option value="13">13</option>
                                        <option value="14">14</option>
                                        <option value="15">15</option>
                                        <option value="16">16</option>
                                        <option value="17">17</option>
                                        <option value="18">18</option>
                                        <option value="19">19</option>
                                        <option value="20">20</option>
                                        <option value="21">21</option>
                                        <option value="22">22</option>
                                        <option value="23">23</option>
                                    </select>时
                                    :
                                    <select name="min" style="width: 120px;height: 25px">
                                        <option value=""></option>
                                        <option value="0">0</option>
                                        <option value="5">5</option>
                                        <option value="10">10</option>
                                        <option value="15">15</option>
                                        <option value="20">20</option>
                                        <option value="25">25</option>
                                        <option value="30">30</option>
                                        <option value="35">35</option>
                                        <option value="40">40</option>
                                        <option value="45">45</option>
                                        <option value="50">50</option>
                                        <option value="55">55</option>
                                    </select>分
                                </div>
                            </div>
                            <div class="form-group">
                                <label>显示颜色</label>
                                <input type="text" class="form-control" name="color" id="color" value="#61a5e8">
                            </div>
                        </form>
                    </div>
                    <div class="box-footer">
                        <button id="addBtn" class="btn btn-sm btn-primary pull-right">创建</button>
                    </div>
                </div>
            </section>
        </div>

        <div class="modal fade" id="edit">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">查看待办事项</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="editForm">
                            <input type="text" id="event_id" hidden>
                            <div class="form-group">
                                <label>待办内容</label>
                                <div id="event_title"></div>
                            </div>
                            <div class="form-group">
                                <label>起止日期</label>
                                <div>
                                    <span id="event_start"></span> ——
                                    <span id="event_end"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>提醒时间</label>
                                <div id="event_remindtime"></div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default " data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-danger " data-dismiss="modal" id="delBtn"><i class="fa fa-trash"></i>删除</button>
                        <button type="button" class="btn btn-primary" id="editBtn"><i class="fa fa-check"></i>完成</button>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>


<script src="/static/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/adminlte/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/js/jquery-ui.min.js"></script>
<script src="/static/adminlte/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="/static/adminlte/plugins/fastclick/fastclick.js"></script>
<script src="/static/adminlte/dist/js/app.min.js"></script>
<script src="/static/adminlte/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/adminlte/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="/static/adminlte/dist/js/demo.js"></script>
<script src="/static/moment/moment.min.js"></script>
<script src="/static/adminlte/plugins/fullcalendar/fullcalendar.min.js"></script>
<script src="/static/adminlte/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>

<script>
    $(function () {

        var _event = null;
        var $calendar = $("#calendar");
        $("[title ='Collapse']").click(function(){
            $(this).css({"visibility": "hide"});
        });

        $calendar.fullCalendar({
            lang:'zh-CN',
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay'
            },
            buttonText: {
                today: '今天',
                month: '月',
                week: '周',
                day: '天'
            },
            events:"schedule/load",
            dayClick: function(date, jsEvent, view) {
               // console.log('Current view: ' + view.name);
               // $(this).css('background-color', 'cyan');


                $("#addForm")[0].reset();
                $("#start_time").val(date.format());
                $("#end_time").val(date.format());
                var x = jsEvent.pageX - 201;
                var y = jsEvent.pageY - 119;
                y = y > 220 ? y : 220;
                y = y > 500 ? 500 : y;
                x = x < 900 ? x : 900;
                $("#time").html(date.format());
                $("#add").css({"visibility": "visible"});
                $("#add").css({"position": "absolute", "left": x  + "px", "top": y  + "px"});
            },
            eventClick:function(calEvent, jsEvent, view){
                _event = calEvent;
                console.log(calEvent);
                $("#event_id").val(calEvent.id);
                $("#event_title").text(calEvent.title);
                $("#event_start").text(moment(calEvent.start).format("YYYY-MM-DD"));
                $("#event_end").text(moment(calEvent.end).format("YYYY-MM-DD"));
                if(calEvent.remindtime){
                    $("#event_remindtime").text(calEvent.remindtime);
                }else{
                    $("#event_remindtime").text("无");
                }

                $("#edit").modal({
                    "show":true,
                    "backdrop":"static",
                    "keyboard":false
                });
            },

            editable: true,
            droppable: true, // this allows things to be dropped onto the calendar !!!
            drop: function (date, allDay) { // this function is called when something is dropped

                var originalEventObject = $(this).data('eventObject');

                // we need to copy it, so that multiple events don't have a reference to the same object
                var copiedEventObject = $.extend({}, originalEventObject);

                // assign it the date that was reported
                copiedEventObject.start = date;
                copiedEventObject.allDay = allDay;
                copiedEventObject.backgroundColor = $(this).css("background-color");
                copiedEventObject.borderColor = $(this).css("border-color");

                // render the event on the calendar
                // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
                $calendar.fullCalendar('renderEvent', copiedEventObject, true);

                // is the "remove after drop" checkbox checked?
                if ($('#drop-remove').is(':checked')) {
                    // if so, remove the element from the "Draggable Events" list
                    $(this).remove();
                }

            }
        });

        // 背景颜色
        $("#color").colorpicker({
            color:'#33FFFF'
        });
        $("#start_time,#end_time").datepicker({
            format: 'yyyy-mm-dd',
            autoclose:true,
            language:'zh-CN',
            todayHighlight:true
        });

        // 保存事项
        $("#addBtn").click(function(){
            // 事项不为空
           if(!$("#title").val()){
               $("#title").focus();
               return;
           }
            if(moment($("#start_time").val()).isAfter(moment($("#end_time").val()))){
                alert("结束时间需要大于开始时间");
                return;
            }
            $.post("/schedule/add",$("#addForm").serialize())
                    .done(function(result){
                        if(result.state == 'success'){
                            // 隐藏窗口
                            $("#add").css({"visibility": "hidden"});
                            // 获取最新结果
                            $calendar.fullCalendar('renderEvent', result.data);
                            // 显示结果
                            $("#header").after("<div class='alert alert-success alert-dismissible'>" +
                                    "<button type='button' class='close' data-dismiss='alert' >" +
                                    "<span aria-hidden='true'>&times;</span>" +
                                    "</button><strong>Tips:</strong>新增" + result.state + "</div>");
                        }

                    })
                    .fail(function(){
                        alert("服务器异常");
                    });
        });

        // 删除事项
        $("#delBtn").click(function(){
            var $id = $("#event_id").val();
            if(confirm("真的要删除该事项吗？")){
                $.get("/schedule/del/"+$id)
                        .done(function(data){
                            $calendar.fullCalendar('removeEvents',$id);
                            $("#edit").modal("hide");
                            // 显示结果
                            $("#header").after("<div class='alert alert-success alert-dismissible'>" +
                                    "<button type='button' class='close' data-dismiss='alert' >" +
                                    "<span aria-hidden='true'>&times;</span>" +
                                    "</button><strong>Tips:</strong>删除" + data + "</div>");
                        })
                        .fail(function(){
                            alert("服务器异常");
                        });
            }
        });
        $(".del").click(function(){
            var $id = $(this).attr("rel");
            if(confirm("真的要删除该事项吗？")){
                $.get("/schedule/del/"+$id)
                        .done(function(data){
                            $calendar.fullCalendar('removeEvents',$id);
                            $("#edit").modal("hide");
                            // 显示结果
                            $("#header").after("<div class='alert alert-success alert-dismissible'>" +
                                    "<button type='button' class='close' data-dismiss='alert' >" +
                                    "<span aria-hidden='true'>&times;</span>" +
                                    "</button><strong>Tips:</strong>删除" + data + "</div>");
                        })
                        .fail(function(){
                            alert("服务器异常");
                        });
            }
        });

        // 标记事项已完成
        $("#editBtn").click(function(){
            var $id = $("#event_id").val();
            $.get("/schedule/done/"+ $id)
                    .done(function(data){
                        $("#edit").modal("hide");
                        _event.color = "#cccccc";
                        $calendar.fullCalendar('updateEvents',_event);
                        // 显示结果
                        $("#header").after("<div class='alert alert-success alert-dismissible'>" +
                                "<button type='button' class='close' data-dismiss='alert' >" +
                                "<span aria-hidden='true'>&times;</span>" +
                                "</button><strong>Tips:</strong>修改" + data + "</div>");
                    })
                    .fail(function(){
                        alert("服务器异常");
                    });
        });

        $(".done").click(function(){
            var $id = $(this).val();
            if(!confirm("确定做完了吗？")){
              return;
            }
            $.get("/schedule/done/"+ $id)
                    .done(function(data){
                        $("#edit").modal("hide");
                        _event.color = "#cccccc";
                        $calendar.fullCalendar('updateEvents',_event);
                        // 显示结果
                        $("#header").after("<div class='alert alert-success alert-dismissible'>" +
                                "<button type='button' class='close' data-dismiss='alert' >" +
                                "<span aria-hidden='true'>&times;</span>" +
                                "</button><strong>Tips:</strong>修改" + data + "</div>");
                    })
                    .fail(function(){
                        alert("服务器异常");
                    });
        });

        $("#ad").on("mousedown",function(ev){
            var x;
            var y;
            var x0 = event.pageX -201;
            var y0 = event.pageY -119;
            y0 = y0 > 220 ? y0 : 220;
            y0 = y0 > 500 ? 500 : y0;
            x0 = x0 < 900 ? x0 : 900;

            $(this).on("mousemove",function(event){
                console.log(event.pageX);
                console.log(event.pageY);
                x = event.pageX - x0;
                y = event.pageY - y0;
                $(this).css({"position": "absolute", "left": x  + "px", "top": y  + "px"});
            });
            $(this).on("mouseup",function(){
                $(this).css({"position": "absolute", "left": x  + "px", "top": y  + "px"});
            })
        });




        /* ADDING EVENTS */
        var currColor = "#3c8dbc"; //Red by default
        //Color chooser button
        var colorChooser = $("#color-chooser-btn");
        $("#color-chooser > li > a").click(function (e) {
            e.preventDefault();
            //Save color
            currColor = $(this).css("color");
            //Add color effect to button
            $('#add-new-event').css({"background-color": currColor, "border-color": currColor});
        });
        $("#add-new-event").click(function (e) {
            e.preventDefault();
            //Get value and make sure it is not null
            var val = $("#new-event").val();
            if (val.length == 0) {
                return;
            }

            //Create events
            var event = $("<div/>");
            event.css({
                "background-color": currColor,
                "border-color": currColor,
                "color": "#fff"
            }).addClass("external-event");
            event.html(val);
            $('#external-events').prepend(event);

            ini_events(event);

            //Remove event from text input
            $("#new-event").val("");
        });


    });
</script>


</body>
</html>

