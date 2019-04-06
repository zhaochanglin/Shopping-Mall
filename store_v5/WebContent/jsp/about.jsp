<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>关于我们</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
	</head>

	<body>
	
		<div class="container">

			<%--包含导航条 --%>
			<%@include file="/jsp/header.jsp" %>

			<div class="container">
				<h1>本平台属于测试平台</h1>
				&emsp;&emsp;&emsp;&emsp;1.注册的邮箱服务器为易邮邮件服务器测试版,邮箱注册还未完善！<br/>
				&emsp;&emsp;&emsp;&emsp;2.购物车、订单等模块做了权限过滤设置，仅允许登录状态下访问.<br/>
				&emsp;&emsp;&emsp;&emsp;3.本商城使用：MySQL数据库、Redis缓存数据 、MD5加密、支付调用易宝支付接口对接银行（测试  注：测试金不能退回）<br/>
				<br/><br/>
				<h4>测试账户：aaa  &emsp;&emsp; 密码：1</h4>  <br/>
				<br/><br/>
				<h3>相关技术交流</h3>
				<img alt="QQ" name="QQ" src="${pageContext.request.contextPath}/img/qq1.jpg">&emsp;&emsp;
				<img alt="微信" src="${pageContext.request.contextPath}/img/WeChat.png">
			</div>

		</div>
		<%--页脚 --%>
		<%-- <%@include file="footer.jsp" %> --%>
		
			<%@ include file="/jsp/footer.jsp" %>
	</body>

</html>