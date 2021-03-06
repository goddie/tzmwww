<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/resource/inc/admin_style.jsp"></jsp:include>
<title></title>
</head>
<body class="bootstrap-admin-with-small-navbar">

	<jsp:include page="/resource/inc/top_nav.jsp"></jsp:include>



	<div class="container">
		<div class="row">
			<div class="col-md-2 bootstrap-admin-col-left">
				<jsp:include page="/resource/inc/product_left.jsp"></jsp:include>
			</div>
			<div class="col-md-10">




				<div class="row">
					<div class="col-lg-12">
						<c:if test="${msg!=null}">
							<div class="alert alert-info">
								<a class="close" data-dismiss="alert" href="#">&times;</a>
								${msg}
							</div>

						</c:if>
						<div class="panel panel-default bootstrap-admin-no-table-panel">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">新增产品</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form
									action="${pageContext.request.contextPath}/product/action/add"
									name="form1" method="post" class="form-horizontal">
									<fieldset>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">产品编号</label>
											<div class="col-lg-4">
												<input name="CPBH" type="text" class="form-control"
													id="CPBH" value="0" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">产品简称</label>
											<div class="col-lg-4">
												<input name="CPJC" type="text" class="form-control"
													id="CPJC" value="" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">产品名</label>
											<div class="col-lg-4">
												<input name="CPMC" type="text" class="form-control"
													id="CPMC" value="" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">产品期限</label>
											<div class="col-lg-4">
												<input name="CPQX" type="text" class="form-control"
													id="CPJC" value="0" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">产品系列</label>
											<div class="col-lg-4">
												<input name="CPXL" type="text" class="form-control"
													id="CPXL" value="0" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead"></label>
											<div class="col-lg-4"></div>

										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">发行费用最低</label>
											<div class="col-lg-4">
												<input name="FXFYZD" type="text" class="form-control"
													id="FXFYZD" value="0" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">发行费用最高</label>
											<div class="col-lg-4">
												<input name="FXFYZG" type="text" class="form-control"
													id="FXFYZG" value="0" />

											</div>

										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">收益率最低</label>
											<div class="col-lg-4">
												<input name="SYLZD" type="text" class="form-control"
													id="SYLZD" value="0" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">收益率最高</label>
											<div class="col-lg-4">
												<input name="SYLZG" type="text" class="form-control"
													id="SYLZG" value="0" />

											</div>

										</div>



										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">收益率说明</label>
											<div class="col-lg-4">
												<input name="SYLSM" type="text" class="form-control"
													id="SYLSM" value="" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">
											</label>
											<div class="col-lg-4"></div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">收益类型说明</label>
											<div class="col-lg-4">
												<input name="SYLXSM" type="text" class="form-control"
													id="SYLXSM" value="" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">收益类型</label>
											<div class="col-lg-4">
												<input name="SYLX" type="text" class="form-control"
													id="SYLX" value="0" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">付息方式</label>
											<div class="col-lg-4">
												<input name="FXFS" type="text" class="form-control"
													id="FXFS" value="0" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">投资领域</label>
											<div class="col-lg-4">
												<input name="TZLY" type="text" class="form-control"
													id="TZLY" value="0" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">所在区域</label>
											<div class="col-lg-4">
												<input name="SZQY" type="text" class="form-control"
													id="SZQY" value="0" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">大小配比
											</label>
											<div class="col-lg-4">
												<input name="DXPB" type="text" class="form-control"
													id="DXPB" value="0" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">所在区域</label>
											<div class="col-lg-4">
												<input name="SZQY" type="text" class="form-control"
													id="SZQY" value="" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">大小配比
											</label>
											<div class="col-lg-4">
												<input name="DXPB" type="text" class="form-control"
													id="DXPB" value="" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">大小配比说明</label>
											<div class="col-lg-4">
												<input name="DXPBSM" type="text" class="form-control"
													id="DXPBSM" value="" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">
											</label>
											<div class="col-lg-4"></div>

										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">质押物</label>
											<div class="col-lg-4">
												<input name="ZYW" type="text" class="form-control" id="ZYW"
													value="" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">风险等级
											</label>
											<div class="col-lg-4">
												<input name="FXDJ" type="text" class="form-control"
													id="FXDJ" value="0" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">发行机构</label>
											<div class="col-lg-4">
												<input name="FXJG" type="text" class="form-control"
													id="FXJG" value="0" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">规模
											</label>
											<div class="col-lg-4">
												<input name="GUIMO" type="text" class="form-control"
													id="GUIMO" value="0" />

											</div>

										</div>





										<div class="form-group">


											<label class="col-lg-2 control-label" for="typeahead">投资门槛额度
											</label>
											<div class="col-lg-4">
												<input name="TZMKED" type="text" class="form-control"
													id="TZMKED" value="0" />

											</div>

										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">已进款金额</label>
											<div class="col-lg-4">
												<input name="YJKJE" type="text" class="form-control"
													id="YJKJE" value="0" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">募集账号
											</label>
											<div class="col-lg-4">
												<input name="MJZH" type="text" class="form-control"
													id="MJZH" value="" />

											</div>

										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">是否推荐</label>
											<div class="col-lg-4">
												<input name="SFTJ" type="text" class="form-control"
													id="SFTJ" value="0" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">是否包销
											</label>
											<div class="col-lg-4">
												<input name="SFBX" type="text" class="form-control"
													id="SFBX" value="0" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">推荐星级</label>
											<div class="col-lg-4">
												<input name="TJXJ" type="text" class="form-control"
													id="TJXJ" value="0" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">结款时点
											</label>
											<div class="col-lg-4">
												<input name="JKSD" type="text" class="form-control"
													id="JKSD" value="0" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">结款时点说明</label>
											<div class="col-lg-4">
												<input name="JKSDSM" type="text" class="form-control"
													id="JKSDSM" value="" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">起息日
											</label>
											<div class="col-lg-4">
												<input name="QXRQ" type="text" class="form-control"
													id="QXRQ" value="" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">到期日</label>
											<div class="col-lg-4">
												<input name="DQRQ" type="text" class="form-control"
													id="DQRQ" value="" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">进度说明
											</label>
											<div class="col-lg-4">
												<input name="JDSM" type="text" class="form-control"
													id="JDSM" value="" />

											</div>

										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">融资方介绍</label>
											<div class="col-lg-4">
												<input name="DQRQ" type="text" class="form-control"
													id="DQRQ" value="" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">进度说明
											</label>
											<div class="col-lg-4">
												<input name="JDSM" type="text" class="form-control"
													id="JDSM" value="" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">融资方介绍</label>
											<div class="col-lg-4">
												<input name="DQRQ" type="RZFJS" class="form-control"
													id="RZFJS" value="" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">进度说明
											</label>
											<div class="col-lg-4">
												<input name="JDSM" type="text" class="form-control"
													id="JDSM" value="" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">短信模板</label>
											<div class="col-lg-4">
												<input name="DXMB" type="RZFJS" class="form-control"
													id="DXMB" value="" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">资金用途
											</label>
											<div class="col-lg-4">
												<input name="ZJYT" type="text" class="form-control"
													id="ZJYT" value="" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">短信模板</label>
											<div class="col-lg-4">
												<input name="DXMB" type="RZFJS" class="form-control"
													id="DXMB" value="" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">资金用途
											</label>
											<div class="col-lg-4">
												<input name="ZJYT" type="text" class="form-control"
													id="ZJYT" value="" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">风控措施</label>
											<div class="col-lg-4">
												<input name="FKCS" type="RZFJS" class="form-control"
													id="FKCS" value="" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">还款来源
											</label>
											<div class="col-lg-4">
												<input name="HKLY" type="text" class="form-control"
													id="HKLY" value="" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">项目亮点</label>
											<div class="col-lg-4">
												<input name="XMLD" type="text" class="form-control"
													id="XMLD" value="" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">产品说明
											</label>
											<div class="col-lg-4">
												<input name="CPSM" type="text" class="form-control"
													id="CPSM" value="" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">产品文档url</label>
											<div class="col-lg-4">
												<input name="CPWDURL" type="text" class="form-control"
													id="CPWDURL" value="" />

											</div>



										</div>



										<div class="form-group">
											<input name="CPZT" type="hidden" class="form-control"
												id="CPZT" value="1" /> <input name="SFYX" type="hidden"
												class="form-control" id="SFYX" value="1" />
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
</body>
</html>
