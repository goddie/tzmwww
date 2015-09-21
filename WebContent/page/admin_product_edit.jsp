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
								<div class="text-muted bootstrap-admin-box-title">编辑产品</div>
							</div>
							<div
								class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
								<form
									action="${pageContext.request.contextPath}/product/action/add"
									name="form1" method="post" class="form-horizontal">
									<input type="hidden" name="pid" value="${entity.id }" />
									<fieldset>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">产品编号</label>
											<div class="col-lg-4">
												<input name="CPBH" type="text" class="form-control"
													id="CPBH" value="${entity.CPBH }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">产品简称</label>
											<div class="col-lg-4">
												<input name="CPJC" type="text" class="form-control"
													id="CPJC" value="${entity.CPJC }" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">产品名</label>
											<div class="col-lg-4">
												<input name="CPMC" type="text" class="form-control"
													id="CPMC" value="${entity.CPMC }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">产品期限</label>
											<div class="col-lg-4">
												<input name="CPQX" type="text" class="form-control"
													id="CPJC" value="${entity.CPQX }" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">产品系列</label>
											<div class="col-lg-4">
												<input name="CPXL" type="text" class="form-control"
													id="CPXL" value="${entity.CPXL }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead"></label>
											<div class="col-lg-4"></div>

										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">发行费用最低</label>
											<div class="col-lg-4">
												<input name="FXFYZD" type="text" class="form-control"
													id="FXFYZD" value="${entity.FXFYZD }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">发行费用最高</label>
											<div class="col-lg-4">
												<input name="FXFYZG" type="text" class="form-control"
													id="FXFYZG" value="${entity.FXFYZG }" />

											</div>

										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">收益率最低</label>
											<div class="col-lg-4">
												<input name="SYLZD" type="text" class="form-control"
													id="SYLZD" value="${entity.SYLZD }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">收益率最高</label>
											<div class="col-lg-4">
												<input name="SYLZG" type="text" class="form-control"
													id="SYLZG" value="${entity.SYLZG }" />

											</div>

										</div>



										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">收益率说明</label>
											<div class="col-lg-4">
												<input name="SYLSM" type="text" class="form-control"
													id="SYLSM" value="${entity.SYLSM }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">
											</label>
											<div class="col-lg-4"></div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">收益类型说明</label>
											<div class="col-lg-4">
												<input name="SYLXSM" type="text" class="form-control"
													id="SYLXSM" value="${entity.SYLXSM }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">收益类型</label>
											<div class="col-lg-4">
												<input name="SYLX" type="text" class="form-control"
													id="SYLX" value="${entity.SYLX }" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">付息方式</label>
											<div class="col-lg-4">
												<input name="FXFS" type="text" class="form-control"
													id="FXFS" value="${entity.FXFS }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">投资领域</label>
											<div class="col-lg-4">
												<input name="TZLY" type="text" class="form-control"
													id="TZLY" value="${entity.TZLY }" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">所在区域</label>
											<div class="col-lg-4">
												<input name="SZQY" type="text" class="form-control"
													id="SZQY" value="${entity.SZQY }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">大小配比
											</label>
											<div class="col-lg-4">
												<input name="DXPB" type="text" class="form-control"
													id="DXPB" value="${entity.DXPB }" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">所在区域</label>
											<div class="col-lg-4">
												<input name="SZQY" type="text" class="form-control"
													id="SZQY" value="${entity.SZQY }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">大小配比
											</label>
											<div class="col-lg-4">
												<input name="DXPB" type="text" class="form-control"
													id="DXPB" value="${entity.DXPB }" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">大小配比说明</label>
											<div class="col-lg-4">
												<input name="DXPBSM" type="text" class="form-control"
													id="DXPBSM" value="${entity.DXPBSM }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">
											</label>
											<div class="col-lg-4"></div>

										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">质押物</label>
											<div class="col-lg-4">
												<input name="ZYW" type="text" class="form-control" id="ZYW"
													value="${entity.ZYW }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">风险等级
											</label>
											<div class="col-lg-4">
												<input name="FXDJ" type="text" class="form-control"
													id="FXDJ" value="${entity.FXDJ }" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">发行机构</label>
											<div class="col-lg-4">
												<input name="FXJG" type="text" class="form-control"
													id="FXJG" value="${entity.FXJG }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">规模
											</label>
											<div class="col-lg-4">
												<input name="GUIMO" type="text" class="form-control"
													id="GUIMO" value="${entity.GUIMO }" />

											</div>

										</div>





										<div class="form-group">


											<label class="col-lg-2 control-label" for="typeahead">投资门槛额度
											</label>
											<div class="col-lg-4">
												<input name="TZMKED" type="text" class="form-control"
													id="TZMKED" value="${entity.TZMKED }" />

											</div>

										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">已进款金额</label>
											<div class="col-lg-4">
												<input name="YJKJE" type="text" class="form-control"
													id="YJKJE" value="${entity.YJKJE }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">募集账号
											</label>
											<div class="col-lg-4">
												<input name="MJZH" type="text" class="form-control"
													id="MJZH" value="${entity.MJZH }" />

											</div>

										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">是否推荐</label>
											<div class="col-lg-4">
												<input name="SFTJ" type="text" class="form-control"
													id="SFTJ" value="${entity.SFTJ }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">是否包销
											</label>
											<div class="col-lg-4">
												<input name="SFBX" type="text" class="form-control"
													id="SFBX" value="${entity.SFBX }" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">推荐星级</label>
											<div class="col-lg-4">
												<input name="TJXJ" type="text" class="form-control"
													id="TJXJ" value="${entity.TJXJ }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">结款时点
											</label>
											<div class="col-lg-4">
												<input name="JKSD" type="text" class="form-control"
													id="JKSD" value="${entity.JKSD }" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">结款时点说明</label>
											<div class="col-lg-4">
												<input name="JKSDSM" type="text" class="form-control"
													id="JKSDSM" value="${entity.JKSDSM }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">起息日
											</label>
											<div class="col-lg-4">
												<input name="QXRQ" type="text" class="form-control"
													id="QXRQ" value="${entity.QXRQ }" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">到期日</label>
											<div class="col-lg-4">
												<input name="DQRQ" type="text" class="form-control"
													id="DQRQ" value="${entity.DQRQ }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">进度说明
											</label>
											<div class="col-lg-4">
												<input name="JDSM" type="text" class="form-control"
													id="JDSM" value="${entity.JDSM }" />

											</div>

										</div>

										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">融资方介绍</label>
											<div class="col-lg-4">
												<input name="RZFJS" type="text" class="form-control"
													id="RZFJS" value="${entity.RZFJS }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">进度说明
											</label>
											<div class="col-lg-4">
												<input name="JDSM" type="text" class="form-control"
													id="JDSM" value="${entity.JDSM }" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">融资方介绍</label>
											<div class="col-lg-4">
												<input name="RZFJS" type="RZFJS" class="form-control"
													id="RZFJS" value="${entity.RZFJS }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">进度说明
											</label>
											<div class="col-lg-4">
												<input name="JDSM" type="text" class="form-control"
													id="JDSM" value="${entity.JDSM }" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">短信模板</label>
											<div class="col-lg-4">
												<input name="DXMB" type="RZFJS" class="form-control"
													id="DXMB" value="${entity.DXMB }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">资金用途
											</label>
											<div class="col-lg-4">
												<input name="ZJYT" type="text" class="form-control"
													id="ZJYT" value="${entity.ZJYT }" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">短信模板</label>
											<div class="col-lg-4">
												<input name="DXMB" type="text" class="form-control"
													id="DXMB" value="${entity.DXMB }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">资金用途
											</label>
											<div class="col-lg-4">
												<input name="ZJYT" type="text" class="form-control"
													id="ZJYT" value="${entity.ZJYT }" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">风控措施</label>
											<div class="col-lg-4">
												<input name="FKCS" type="text" class="form-control"
													id="FKCS" value="${entity.FKCS }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">还款来源
											</label>
											<div class="col-lg-4">
												<input name="HKLY" type="text" class="form-control"
													id="HKLY" value="${entity.HKLY }" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">项目亮点</label>
											<div class="col-lg-4">
												<input name="XMLD" type="text" class="form-control"
													id="XMLD" value="${entity.XMLD }" />

											</div>

											<label class="col-lg-2 control-label" for="typeahead">产品说明
											</label>
											<div class="col-lg-4">
												<input name="CPSM" type="text" class="form-control"
													id="CPSM" value="${entity.CPSM }" />

											</div>

										</div>


										<div class="form-group">
											<label class="col-lg-2 control-label" for="typeahead">产品文档url</label>
											<div class="col-lg-4">
												<input name="CPWDURL" type="text" class="form-control"
													id="CPWDURL" value="${entity.CPWDURL }" />

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
</body>
</html>
