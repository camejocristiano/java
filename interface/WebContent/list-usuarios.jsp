<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<div>
		<i></i>
		<h3>Usuários</h3>
	</div>
		<div>
			<c:if test="${!empty listaUsuarios}">
				<table>
						<thead>
							<tr>
								<th>Id</th>
								<th>Nome</th>
								<th>Editar</th>
								<th>Deletar</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="usuario" items="${requestScope.listaUsuarios}">
								<tr>
									<td>${usuario.id}</td>
									<td>${usuario.nome}</td>
									<td>
										<a href="usuariocontroller.do?acao=alterar&id=${usuario.id}" class="btn btn-small btn-success">Editar</a>
									</td>
									<td>
										<a href="usuariocontroller.do?acao=excluir&id=${usuario.id}" class="btn btn-danger btn-small">Excluir</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>


</body>
</html>