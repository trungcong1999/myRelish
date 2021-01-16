<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Category</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="${pageContext.request.contextPath}/resources/css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- DATA TABLES -->
        <link href="${pageContext.request.contextPath}/resources/css/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="${pageContext.request.contextPath}/resources/css/AdminLTE.css" rel="stylesheet" type="text/css" />

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
		<h1>
			Category
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
			<li><a href="#">Category</a></li>
			<li class="active">Game</li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-header" style="padding-top: 10px;">
						<a  href="${pageContext.request.contextPath}/pages/game/addGame" class="btn btn-primary"><i class="fa fa-fw fa-plus"></i>Thêm mới</a>
					</div>
					<!-- /.box-header -->
					<div class="box-body table-responsive">
						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th style="text-align:center;width: 15px;">STT</th>
									<th style="text-align:center;width: 100px;">Ảnh đại diện</th>
									<th style="text-align:center;width: 180px;">Tên</th>
									<th style="text-align:center;width: 190px;">Chi tiết</th>
									<th style="text-align:center;width: 80px;">Điểm đánh giá</th>
									<th style="text-align:center;width: 100px;">Ngày phát hành</th>
									<th style="text-align:center;width: 80px;">Người đăng</th>
									<th style="text-align:center;width: 80px;">Người phát triển</th>
									<th style="text-align:center;">Tác vụ</th>
								</tr>
							</thead>
							<tbody style="text-align:center;">
								<c:forEach items="${listGame}" var="game">
									<tr>
										<td>${game.id}</td>
										<td><img src="<c:url value="${game.header_img}"/>" width="100"/></td>
										<td>${game.name}</td>
										<td>${game.description}</td>
										<td>${game.metacritic_score}</td>
										<td>${game.release_date}</td>
										<td>${game.user_name}</td>
										<td>${game.user_name}</td>
										<td><a href="${pageContext.request.contextPath}/pages/game/editGame/${game.id}"><i class="fa fa-fw fa-pencil"></i></a>&ensp;
										<a href="${pageContext.request.contextPath}/pages/game/delete/${game.id}"><i class="fa fa-fw fa-trash-o"></i></a></td>
									</tr>
								</c:forEach>
							</tbody>
							
						</table>
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->
			</div>
		</div>

	</section>
	<!-- /.content -->



	<!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- DATA TABES SCRIPT -->
        <script src="${pageContext.request.contextPath}/resources/js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/app.js" type="text/javascript"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="${pageContext.request.contextPath}/resources/js/AdminLTE/demo.js" type="text/javascript"></script>
        <!-- page script -->
        <script type="text/javascript">
            $(function() {
                $("#example1").dataTable();
                $('#example2').dataTable({
                    "bPaginate": true,
                    "bLengthChange": false,
                    "bFilter": false,
                    "bSort": true,
                    "bInfo": true,
                    "bAutoWidth": false
                });
            });
        </script>

    </body>
</html>