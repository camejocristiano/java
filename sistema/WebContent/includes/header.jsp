<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Restaurante Vila Prudente</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">

<link href="resources/css/bootstrap.min.css"	rel="stylesheet" type="text/css" />
<link
	href="resources/css/bootstrap-responsive.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="resources/css/font-awesome.css"
	rel="stylesheet" />
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600"
	rel="stylesheet" />

<link href="resources/css/style.css"
	rel="stylesheet" type="text/css" />
<link
	href="resources/css/pages/dashboard.css"
	rel="stylesheet" type="text/css" />
<link
	href="resources/css/pages/signin.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="maincontroller.do"> Restaurante Vila Prudente </a>
				<div class="nav-collapse">
					<ul class="nav pull-right">
						<li class=""><a href="usuariocontroller.do?acao=listar" class=""> Usuário </a></li>
						<li class=""><a href="logoutcontroller.do" class=""> <i
								class="icon-chevron-left"></i> Logout
						</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!-- /container -->
		</div>
		<!-- /navbar-inner -->
	</div>
	<!-- /navbar -->	
