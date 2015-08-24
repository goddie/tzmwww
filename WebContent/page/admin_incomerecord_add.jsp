<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加结算</title>
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
</head>
<body class="bootstrap-admin-with-small-navbar">
	<jsp:include page="/resource/inc/top_nav.jsp"></jsp:include>

	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">选择队伍</h4>
				</div>
				<div class="modal-body">
					<iframe marginheight="0" marginwidth="0" frameborder="0"
						width="100%"
						src="${pageContext.request.contextPath}/user//sel"></iframe>
				</div>
			</div>
		</div>
	</div>
	
	
		<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">选择队伍</h4>
				</div>
				<div class="modal-body">
					<iframe marginheight="0" marginwidth="0" frameborder="0"
						width="100%"
						src="${pageContext.request.contextPath}/team/admin/sel"></iframe>
				</div>
			</div>
		</div>
	</div>


	<div class="container">
		<div class="row">
			<div class="col-md-2 bootstrap-admin-col-left">
				<jsp:include page="/resource/inc/leftmenu_userconfig.jsp"></jsp:include>
			</div>
			<div class="col-md-10">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default bootstrap-admin-no-table-panel">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">添加结算</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form
									action="${pageContext.request.contextPath}/incomerecord/add"
									name="form1" method="post" class="form-horizontal">
									<fieldset>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">用户：</label>
											<div class="col-xs-6">
												<input name="nickname" type="text"
													class="form-control col-md-6" id="nickname" /> <input
													name="uid" type="hidden" class="form-control col-md-6"
													id="uid" />
											</div>
											<div class="col-xs-4">
												<button type="button" class="btn btn-primary"
													data-toggle="modal" data-target="#myModal1">选择用户</button>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">产品：</label>
											<div class="col-xs-6">
												<input name="pname" type="text"
													class="form-control col-md-6" id="pname"> <input
													name="pid" type="hidden" class="form-control col-md-6"
													id="pid" />
											</div>
											<div class="col-xs-4">
												<button type="button" class="btn btn-primary"
													data-toggle="modal" data-target="#myModal2">选择产品</button>
											</div>
										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label"
												for="textarea-wysihtml5">收益：</label>
											<div class="col-lg-10">
												<input name="income" type="text"
													class="form-control col-md-6" id="income" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-2 control-label"
												for="textarea-wysihtml5">收益滤：</label>
											<div class="col-lg-4">
												<input name="incomePercent" type="text"
													class="form-control col-md-6" id="incomePercent" /><label
													class="col-lg-2 control-label" for="textarea-wysihtml5">%</label>
											</div>
										</div>
										<button type="submit" class="btn btn-primary">提交</button>
										<button type="reset" class="btn btn-default">取消</button>
									</fieldset>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/resource/inc/admin_script.jsp"></jsp:include>

	<script type="text/javascript">
		
	</script>
</body>
</html>