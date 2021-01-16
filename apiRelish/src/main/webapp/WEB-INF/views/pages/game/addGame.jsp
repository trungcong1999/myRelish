<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Film</title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<!-- bootstrap 3.0.2 -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<!-- font Awesome -->
<link
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<!-- Ionicons -->
<link
	href="${pageContext.request.contextPath}/resources/css/ionicons.min.css"
	rel="stylesheet" type="text/css" />
<!-- Theme style -->
<link
	href="${pageContext.request.contextPath}/resources/css/AdminLTE.css"
	rel="stylesheet" type="text/css" />
<!-- daterange picker -->
<link
	href="${pageContext.request.contextPath}/resources/css/daterangepicker/daterangepicker-bs3.css"
	rel="stylesheet" type="text/css" />
<!-- iCheck for checkboxes and radio inputs -->
<link
	href="${pageContext.request.contextPath}/resources/css/iCheck/all.css"
	rel="stylesheet" type="text/css" />
<!-- Bootstrap Color Picker -->
<link
	href="${pageContext.request.contextPath}/resources/css/colorpicker/bootstrap-colorpicker.min.css"
	rel="stylesheet" />
<!-- Bootstrap time Picker -->
<link
	href="${pageContext.request.contextPath}/resources/css/timepicker/bootstrap-timepicker.min.css"
	rel="stylesheet" />

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.${pageContext.request.contextPath}/resources/js/1.3.0/respond.min.js"></script>
        <![endif]-->
</head>
<body>

	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Thêm phim</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
			<li><a href="#">Category</a></li>
			<li class="active">AddFilm</li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="row">
			<!-- left column -->
			<div class="col-md-6">
				<!-- general form elements -->
				<div class="box box-primary">

					<!-- /.box-header -->
					<!-- form start -->
					<form action="${pageContext.request.contextPath}/pages/game/addGame" modelAttribute="addGame" enctype="mutipart/form-data" method="POST">
						<div class="box-body">
							<div class="form-group">
								<label>Tên Game</label> <input name="name" type="text" class="form-control"/>
							</div>
							<div class="form-group">
								<label>Ảnh Header</label> <input name="header_img"
									type="file" id="exampleInputFile">
								<p class="help-block">Chọn ảnh đại diện.</p>
							</div>
							<div class="form-group">
								<label>Thông tin chi tiết</label>
								<textarea name="description" class="form-control" rows="3" placeholder="Enter ..."></textarea>
							</div>
							<div class="form-group">
								<label>Điểm đánh giá </label> <input name="metacritic_score" type="text" class="form-control"/>
							</div>
							<div class="form-group">
								<label>Người đăng</label>
								<select name="publisher_id" class="form-control">
									<c:forEach items="${listGame}" var="game">
									<option value="${game.publisher_id}">${game.user_name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label>Người phát triển</label>
								<select name="developer_id" class="form-control">
									<c:forEach items="${listGame}" var="game">
									<option value="${game.developer_id}">${game.user_name}</option>
									</c:forEach>
								</select>
								
							</div>
							<div class="form-group">
								<label>Ngày tạo</label>
								<div class="input-group">
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
									<input name="release_date" type="date" class="form-control"
										data-inputmask="'alias': 'dd/mm/yyyy'" data-mask />
								</div>
								<!-- /.input group -->
							</div>
							


						</div>
						<!-- /.box-body -->

						<div class="box-footer">
							<button type="submit" class="btn btn-primary">Thêm</button>  
							<a href="${pageContext.request.contextPath}/pages/game/list" class="btn btn-primary">Thoát</a>
						</div>
					</form>
				</div>
				<!-- /.box -->



			</div>
		</div>
		<!-- /.row -->
	</section>
	<!-- /.content -->
	<!-- jQuery 2.0.2 -->
	<script type="text/javascript">
            $(function() {
                //Datemask dd/mm/yyyy
                $("#datemask").inputmask("dd/mm/yyyy", {"placeholder": "dd/mm/yyyy"});
                //Datemask2 mm/dd/yyyy
                $("#datemask2").inputmask("mm/dd/yyyy", {"placeholder": "mm/dd/yyyy"});
                //Money Euro
                $("[data-mask]").inputmask();

                //Date range picker
                $('#reservation').daterangepicker();
                //Date range picker with time picker
                $('#reservationtime').daterangepicker({timePicker: true, timePickerIncrement: 30, format: 'MM/DD/YYYY h:mm A'});
                //Date range as a button
                $('#daterange-btn').daterangepicker(
                        {
                            ranges: {
                                'Today': [moment(), moment()],
                                'Yesterday': [moment().subtract('days', 1), moment().subtract('days', 1)],
                                'Last 7 Days': [moment().subtract('days', 6), moment()],
                                'Last 30 Days': [moment().subtract('days', 29), moment()],
                                'This Month': [moment().startOf('month'), moment().endOf('month')],
                                'Last Month': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
                            },
                            startDate: moment().subtract('days', 29),
                            endDate: moment()
                        },
                function(start, end) {
                    $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                }
                );

                //iCheck for checkbox and radio inputs
                $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
                    checkboxClass: 'icheckbox_minimal',
                    radioClass: 'iradio_minimal'
                });
                //Red color scheme for iCheck
                $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
                    checkboxClass: 'icheckbox_minimal-red',
                    radioClass: 'iradio_minimal-red'
                });
                //Flat red color scheme for iCheck
                $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
                    checkboxClass: 'icheckbox_flat-red',
                    radioClass: 'iradio_flat-red'
                });

                //Colorpicker
                $(".my-colorpicker1").colorpicker();
                //color picker with addon
                $(".my-colorpicker2").colorpicker();

                //Timepicker
                $(".timepicker").timepicker({
                    showInputs: false
                });
            });
        </script>
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"
		type="text/javascript"></script>
	<!-- AdminLTE App -->
	<script
		src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js"
		type="text/javascript"></script>
	<!-- AdminLTE for demo purposes -->
	<script
		src="${pageContext.request.contextPath}/resources/js/AdminLTE/demo.js"
		type="text/javascript"></script>
</body>
</html>