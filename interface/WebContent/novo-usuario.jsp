<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Interface</title>
</head>
<body>

	<c:url var="addAction" value=""></c:url>
	<form action="usuariocontroller.do" method="post"
			id="edit-profile" class="form-horizontal">
			<c:if test="${!empty usuario.id}">
			<div>
				<label>Id: </label>
				<div class="controls">
					<input type="text" name="id" size="8" class="span6 disabled" id="usuario" value="${requestScope.usuario.id}" />
					<p class="help-block">Campo dezabilitado.</p>
				</div>
				<!-- /controls -->
			</div>
			<!-- /control-group -->
			</c:if>
				<label>Nome	</label>
					<input name="nome" class="span6" id="nome" value="${requestScope.usuario.nome}" />
						<button type="submit" class="btn btn-primary">
							Adicionar
						</button>
						<button class="btn">Cancelar</button>
		</form>


</body>
</html>