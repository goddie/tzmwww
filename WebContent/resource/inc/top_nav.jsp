<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- small navbar -->
	<nav
		class="navbar navbar-default navbar-fixed-top bootstrap-admin-navbar-sm"
		role="navigation"> </nav>
	<nav class="navbar navbar-default navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small" role="navigation">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".main-navbar-collapse">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="#">后台管理</a>
                        </div>
                        <div class="collapse navbar-collapse main-navbar-collapse">
                            <ul class="nav navbar-nav">
                                <li class="active" style="display:none;"><a href="${pageContext.request.contextPath}/page/ucenter_userconfig.jsp">账户设置</a></li>
                                <li><a href="${pageContext.request.contextPath}/user/admin/list?p=1">用户管理</a></li>
                                <li><a href="${pageContext.request.contextPath}/product/admin/list?p=1">产品管理</a></li>
                                <li><a href="${pageContext.request.contextPath}/traderecord/admin/list?p=1">交易记录</a></li>
                                <li><a href="${pageContext.request.contextPath}/popularapply/admin/list?p=1">投资猫达人</a></li>
                                <li><a href="${pageContext.request.contextPath}/keyvalue/admin/list?p=1">规则管理</a></li>
                            </ul>
                        </div><!-- /.navbar-collapse -->
                    </div>
                </div>
            </div><!-- /.container -->
        </nav>