<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editor</title>
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
<!-- bootstrap wysihtml5 - text editor -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css"
	rel="stylesheet" type="text/css" />

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
		<h1></h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
			<li><a href="#">Category</a></li>
			<li class="active">Editors</li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">

		<div class='row'>
			<div class='col-md-12'>
				<div class='box box-info'>
					<div class='box-header'>
						<h3 class='box-title'>Thêm bài viết</h3>
						<!-- tools box -->

						<!-- /. tools -->
					</div>
					<!-- /.box-header -->
					<form action="" method="POST">
					<div class='box-body pad'>
							<div class="box-body">
								<div class="form-group">
									<h4>Module</h4>
									<div class="form-group">
										<select class="form-control">
											<option value="1">Chọn module</option>
											<option value="2">Game</option>
											<option value="3">Film</option>
											<option value="4">Noval</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<h4>Title</h4>
									<textarea id="editor1" name="editor1" rows="10" cols="80">           
                            </textarea>
								</div>
								<div class="form-group">
									<h4>Summary</h4>
									<textarea id="editor2" name="editor1" rows="10" cols="80">           
                            </textarea>
								</div>
								<div class="form-group">
									<h4>Review</h4>
									<textarea id="editor3" name="editor1" rows="10" cols="80">           
                            </textarea>
								</div>
								<div class="form-group">
									<h4>Ngày tạo</h4> 
									<input class="form-control" type="date">
								</div>
							</div>
					</div>
					<!-- /.box-body -->



					<div class="box-footer">
						<a href="#" class="btn btn-primary">Lưu</a> <a
							href="${pageContext.request.contextPath}/pages/category/posts"
							class="btn btn-primary">Thoát</a>
					</div>
					</form>
				</div>
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col-->
		<!-- ./row -->



	</section>
	<!-- /.content -->



	<!-- jQuery 2.0.2 -->
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
	<!-- CK Editor -->
	<script
		src="${pageContext.request.contextPath}/resources/js/plugins/ckeditor/ckeditor.js"
		type="text/javascript"></script>
	<!-- Bootstrap WYSIHTML5 -->
	<script
		src="${pageContext.request.contextPath}/resources/js/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">
									$(function() {
										// Replace the <textarea id="editor1"> with a CKEditor
										// instance, using default configuration.
										CKEDITOR.replace('editor1');
										CKEDITOR.replace('editor2');
										CKEDITOR.replace('editor3');
										//bootstrap WYSIHTML5 - text editor
										$(".textarea").wysihtml5();
									});
								</script>

</body>
</html>