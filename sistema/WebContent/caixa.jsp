<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<% Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); %>

<!--
    IE8 support, see AngularJS Internet Explorer Compatibility http://docs.angularjs.org/guide/ie
    For Firefox 3.6, you will also need to include jQuery and ECMAScript 5 shim
  -->
  <!--[if lt IE 9]>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/es5-shim/2.2.0/es5-shim.js"></script>
    <script>
      document.createElement('ui-select');
      document.createElement('match');
      document.createElement('choices');
    </script>
  <![endif]-->

  <!-- Links CDN para Select Folder
  <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.15/angular.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.15/angular-sanitize.js"></script>
  <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.css">
  -->
  <script src="resources/ui-select/angular.js"></script>
  <script src="resources/ui-select/angular-sanitize.js"></script>
  <link rel="stylesheet" href="resources/ui-select/bootstrap.css">
  

  <!-- ui-select files -->
  <script src="resources/select.js"></script>
  <link rel="stylesheet" href="resources/select.css">

  <script src="resources/pedido.js"></script>

  <!-- Link CDN Select2 theme 
  <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/select2/3.4.5/select2.css">
  -->
  <link rel="stylesheet" href="resources/ui-select/select2.css">

  <!--
    Selectize theme
    Less versions are available at https://github.com/brianreavis/selectize.js/tree/master/dist/less
  -->
  
  <!-- Link CDN Select Folder
  <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.8.5/css/selectize.default.css">
  -->
  <link rel="stylesheet" href="resources/ui-select/selectize.default.css">
  
  <!-- <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.8.5/css/selectize.bootstrap2.css"> -->
  <!-- <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.8.5/css/selectize.bootstrap3.css"> -->


<c:import url="includes/header.jsp" />
<c:import url="includes/menu.jsp" />

<div class="main">
	<div class="main-inner">
		<div class="container">
			<div class="row">
				<div class="span12">
					<div class="widget ">
						<div class="widget-header">
							<i class="icon-user"></i>
							<h3>Caixa</h3>
						</div>
						<!-- /widget-header -->
						<div class="widget-content">
							
										<form id="edit-profile2" class="form-vertical">
											<fieldset>
												<div class="control-group">
													<div class="controls"></div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												<div class="widget widget-table action-table">
													<div class="widget-header">
														<i class="icon-th-list"></i>
														<h3>Pedidos</h3>
													</div>
													<!-- /widget-header -->
													<div class="widget-content">
													<div class="table-responsive">
															<table class="table table-striped table-bordered">
																<thead>
																	<tr>
																		<th>Número</th>
																		<th>Cliente</th>
																		<th>Valor</th>
																		<th class="td-actions">Editar</th>
																		<th class="td-actions">Deletar</th>
																	</tr>
																</thead>
																  <% 
																  	String id = request.getParameter("id");
															        Connection conexao = null;
																	try{
																		Class.forName("com.mysql.jdbc.Driver");
																		String url = "jdbc:mysql://127.0.0.1:3306/restaurantevilaprudente";
																		String usuario = "root";
																		String senha = "";
																		
																		conexao = (Connection) DriverManager.getConnection(url, usuario, senha);
																	}
																	catch(SQLException e)
																	{	
																		
																		System.out.println("Ocorreu um erro de SQL. Erro: " + e.getMessage());
																	
																	} catch (ClassNotFoundException e) {
																		System.out.println("Driver não encontrado!");
																	}
															
															
															            Statement statement = conexao.createStatement();
															            String query = "SELECT cliente.nome, pedido.id, pedido.valor, pedido.numero, pedido.caixa " +
															              "FROM cliente INNER JOIN pedido ON cliente.id = pedido.cliente WHERE pedido.caixa = "+id+" ORDER BY id DESC";
															
															            ResultSet resultset = 
															                statement.executeQuery(query) ; 
															        %>
																<tbody>
																		<% while(resultset.next()){ %>
																		<tr>
																			<td><%= resultset.getString(4) %></td>
																			<td><%= resultset.getString(1) %></td>
																			<td><%= resultset.getString(3) %></td>
																			<td class="td-actions">
																			<a href="pedidocontroller.do?acao=alterar&id=<%= resultset.getString(2) %>"
																				class="btn btn-small btn-success">
																				<i class="btn-icon-only icon-ok"> </i>
																			</a>
																			</td>
																			<td class="td-actions">
																			<a href="pedidocontroller.do?acao=excluir&id=<%= resultset.getString(2) %>"
																				class="btn btn-danger btn-small">
																				<i	class="btn-icon-only icon-remove"> </i>
																			</a>
																			</td>
																		</tr>
																		<% } %>
																</tbody>
															</table>
													</div>
													</div>
													<!-- /widget-content -->
												</div>
												<!-- /widget -->
											</fieldset>
										</form>
					
						</div>
						<!-- /widget-content -->
					</div>
					<!-- /widget -->
				</div>
				<!-- /span12 -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /main-inner -->
</div>
<!-- /main -->

<c:import url="includes/footer.jsp" />


