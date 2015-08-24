<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品列表</title>
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
</head>
<body class="bootstrap-admin-with-small-navbar">
	<jsp:include page="/resource/inc/top_nav.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<div class="col-md-2 bootstrap-admin-col-left">
				<jsp:include page="/resource/inc/leftmenu_userconfig.jsp"></jsp:include>
			</div>
			<div class="col-md-10">

				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">产品列表</div>

							</div>

							<div class="bootstrap-admin-panel-content">
								<div id="example_wrapper" class="dataTables_wrapper form-inline"
									role="grid">

									<table class="table table-striped table-bordered dataTable"
										id="example" aria-describedby="example_info">
										<thead>
											<tr role="row">
												<th style="width: 30px;">序号
												</th>
												<th>编号</th>
												<th>名称</th>
												<th>简称</th>
												<th>期限</th>
												<th>收益率</th>
												<th>风险等级</th>
												<th>付息方式</th>
											</tr>
										</thead>
										<tbody role="alert" aria-live="polite" aria-relevant="all">
											<c:forEach var="m" varStatus="status" items="${list}">
												<tr class="gradeA odd">
													<td class="sorting_1">${status.index+1}</td>
													<td class="">${m.CPBH}</td>
													<td class="">${m.CPMC}</td>
													<td class="">${m.CPJC}</td>
													<td class="">${m.CPQX}</td>
													<td class="">${m.SYLZD}-${m.SYLZG}</td>
													<td class="">${m.FXDJ}</td>
													<td class="">${m.FXFS}</td>
													 
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="row">
										 
										<div class="col-md-12">
											<div class="dataTables_paginate paging_bootstrap">
												<ul class="pagination">
													<li class="prev disabled"><a href="#">← Previous</a></li>
													<li class="active"><a href="#">1</a></li>
													<li><a href="#">2</a></li>
													<li><a href="#">3</a></li>
													<li><a href="#">4</a></li>
													<li><a href="#">5</a></li>
													<li class="next"><a href="#">Next → </a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	</div>
	</div>
	</div>
	<jsp:include page="/resource/inc/admin_script.jsp"></jsp:include>
</body>

</html>