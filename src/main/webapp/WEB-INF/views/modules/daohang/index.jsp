<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.my_left_category {
	width: 150px;
	font-size: 12px;
	font-family: arial, sans-serif;
	letter-spacing: 2px;
}

.my_left_category h1 {
	background-image: url(spring_06.gif);
	height: 20px;
	background-repeat: no-repeat;
	font-size: 14px;
	font-weight: bold;
	padding-left: 15px;
	padding-top: 8px;
	margin: 0px;
	color: #FFF;
}

.my_left_category .my_left_cat_list h2 {
	margin: 0px;
	padding: 3px 5px 0px 9px;
}

.my_left_category .my_left_cat_list h2 a {
	color: #d6290b;
	font-weight: bold;
	font-size: 14px;
	line-height: 22px;
}

.my_left_category .my_left_cat_list {
	width: 148px;
	border-color: #b60134;
	border-style: solid;
	border-width: 0px 1px 1px 1px;
	line-height: 13.5pt;
}

.my_left_category .my_left_cat_list h2 a:hover {
	color: #d6290b;
	font-weight: bold;
	font-size: 14px;
	line-height: 22px;
}

.my_left_category .h2_cat {
	width: 148px;
	height: 26px;
	background-image: url('${ctxStatic}/images/daohang/my_menubg.gif');
	background-repeat: no-repeat;
	line-height: 26px;
	font-weight: normal;
	color: #333333;
	position: relative;
}

.my_left_category a {
	font: 12px;
	text-decoration: none;
	color: #333333;
}

.my_left_category a:hover {
	text-decoration: underline;
	color: #ff3333;
}

.my_left_category .h2_cat_1 {
	width: 148px;
	height: 26px;
	background-image: url(${ctxStatic}/images/daohang/my_menubg_1.gif);
	background-repeat: no-repeat;
	line-height: 26px;
	font-weight: normal;
	color: #333333;
	position: relative;
}

.my_left_category h3 {
	margin: 0px;
	padding: 0px;
	height: 26px;
	font-size: 12px;
	font-weight: normal;
	display: block;
	padding-left: 8px;
}

.my_left_category h3 span {
	color: #999999;
	width: 145px;
	float: right;
}

.my_left_category h3 a {
	line-height: 26px;
}

.my_left_category .h3_cat {
	display: none;
	width: 204px;
	position: absolute;
	left: 123px;
	margin-top: -26px;
	cursor: auto;
}

.my_left_category .shadow {
	position: inherit;
	background: url(${ctxStatic}/images/daohang/shadow_04.gif) left top;
	width: 204px;
}

.my_left_category .shadow_border {
	position: inherit;
	width: 400px;
	border: 1px solid #959595;
	margin-top: 1px;
	border-left-width: 0px;
	background: url(${ctxStatic}/images/daohang/shadow_border.gif) no-repeat 0px 21px;
	background-color: #ffffff;
	margin-bottom: 3px
}

.my_left_category .shadow_border ul {
	margin: 0;
	padding: 0;
	margin-left: 15px
}

.my_left_category .shadow_border ul li {
	list-style: none;
	padding-left: 10px;
	background-image: url(${ctxStatic}/images/daohang/my_cat_sub_menu_dot.gif);
	background-repeat: no-repeat;
	background-position: 0px 8px;
	float: left;
	width: 75px;
	height: 26px;
	overflow: hidden;
	letter-spacing: 0px;
}

.my_left_category .active_cat {
	z-index: 99;
	background-position: 0 -25px;
	cursor: pointer;
}

.my_left_category .active_cat h3 {
	font-weight: bold
}

.my_left_category .active_cat h3 span {
	display: none;
}

.my_left_category .active_cat div {
	display: block;
}
</style>
</head>
<body>
	<div class="my_left_category">
		<h1>分类导航</h1>
		<div class="my_left_cat_list">
			<h2>
				<a href="#">按网站类别</a>
			</h2>
			<div class="h2_cat" onmouseover="this.className='h2_cat active_cat'"
				onmouseout="this.className='h2_cat'">
				<h3>
					<a href="#">美国空间</a>
				</h3>
				<div class="h3_cat">
					<div class="shadow">
						<div class="shadow_border">
							<ul>
								<li><a href="#">LOGO设计</a></li>
								<li><a href="#">网站设计</a></li>
								<li><a href="#">网站广告</a></li>
								<li><a href="#">推广</a></li>
								<li><a href="#">建网站</a></li>
								<li><a href="#">网站推广</a></li>
								<li><a href="#">网站建设</a></li>
								<li><a href="#">SEO</a></li>
								<li><a href="#">LOGO设计</a></li>
								<li><a href="#">网站设计</a></li>
								<li><a href="#">网站广告</a></li>
								<li><a href="#">推广</a></li>
								<li><a href="#">建网站</a></li>
								<li><a href="#">网站推广</a></li>
								<li><a href="#">网站建设</a></li>
								<li><a href="#">SEO</a></li>
								<li><a href="#">LOGO设计</a></li>
								<li><a href="#">网站设计</a></li>
								<li><a href="#">网站广告</a></li>
								<li><a href="#">推广</a></li>
								<li><a href="#">建网站</a></li>
								<li><a href="#">网站推广</a></li>
								<li><a href="#">网站建设</a></li>
								<li><a href="#">SEO</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="h2_cat" onmouseover="this.className='h2_cat active_cat'"
				onmouseout="this.className='h2_cat'">
				<h3>
					<a href="/hk.html">香港空间</a>
				</h3>
				<div class="h3_cat">
					<div class="shadow">
						<div class="shadow_border">
							<ul>
								<li><a href="#">LOGO设计</a></li>
								<li><a href="#">网站设计</a></li>
								<li><a href="#">网站广告</a></li>
								<li><a href="#">推广</a></li>
								<li><a href="#">建网站</a></li>
								<li><a href="#">网站推广</a></li>
								<li><a href="#">网站建设</a></li>
								<li><a href="#">SEO</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="h2_cat" onmouseover="this.className='h2_cat active_cat'"
				onmouseout="this.className='h2_cat'">
				<h3>
					<a href="/tz.html">防御空间</a>
				</h3>
				<div class="h3_cat">
					<div class="shadow">
						<div class="shadow_border">
							<ul>
								<li><a href="/china.html">国内空间</a></li>
								<li><a href="#">源码下载</a></li>
								<li><a href="#">最新更新</a></li>
								<li><a href="#">下载排行</a></li>
								<li><a href="#">ASP</a></li>
								<li><a href="#">PHP</a></li>
								<li><a href="#">AJAX</a></li>
								<li><a href="#">DELPHI</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>