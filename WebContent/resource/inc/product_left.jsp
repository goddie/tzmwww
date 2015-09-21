<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
	<li><a href="#"><i class="glyphicon glyphicon-chevron-down"></i>产品管理</a>
		<ul class="nav navbar-collapse bootstrap-admin-navbar-side">
			<li><a href="${pageContext.request.contextPath}/product/admin/list?p=1"><i
					class="glyphicon glyphicon-chevron-right"></i>产品列表</a></li>
			<li><a href="${pageContext.request.contextPath}/product/admin/add"><i
					class="glyphicon glyphicon-chevron-right"></i>新增产品</a></li>

		</ul></li>


</ul>