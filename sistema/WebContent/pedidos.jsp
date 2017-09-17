<%@page import="org.apache.tomcat.util.http.fileupload.RequestContext"%>
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

<div class="main" ng-app="demo">
	<div class="main-inner" ng-controller="DemoCtrl">
		<div class="container">
			<div class="row">
				<div class="span12">
					<div class="widget ">
						<div class="widget-header">
							<i class="icon-user"></i>
							<h3>Pedido</h3>
						</div>
						<!-- /widget-header -->
						<div class="widget-content">
							<div class="tabbable">
								<ul class="nav nav-tabs">
									<li class="active"><a href="#formcontrols"
										data-toggle="tab">Inserir</a></li>
									<li><a href="#jscontrols" data-toggle="tab">Listar</a></li>
								</ul>
								<br>
								<div class="tab-content">
									<div class="${classeBootstrap}">
					                    <h3>${msg}</h3>
					                </div>
									<div class="tab-pane active" id="formcontrols">
										<c:url var="addAction" value=""></c:url>
										<form action="pedidocontroller.do" method="post"
											id="edit-profile" class="form-horizontal">
											<fieldset>
												<c:if test="${!empty pedido.id}">
													<div class="control-group">
														<label class="control-label">Id: </label>
														<div class="controls">
															<input type="text" name="id" size="8" class="span6 disabled" id="pedido" value="${requestScope.pedido.id}" />
															<p class="help-block">Campo dezabilitado.</p>
														</div>
														<!-- /controls -->
													</div>
													<!-- /control-group -->
												</c:if>
													<div class="control-group">
													<label class="control-label">
														Clientes
													</label>
													<div class="controls">
													<p> 
														Nome: {{cliente.selected.nome}} | 
														Telefone: {{cliente.selected.telefone}} |
														Endereço: {{cliente.selected.endereco}} <br />
													</p>
													<input type="hidden" name="cliente" class="span6 form-control" id="cliente" value="{{cliente.selected.id}}" />
													  <ui-select ng-model="cliente.selected" theme="select2" ng-disabled="disabled" style="min-width: 300px;">
													    <match placeholder="Selecione um cliente na lista...">{{$select.selected.nome}}</match>
													    <choices repeat="cliente in clientes | propsFilter: {nome: $select.search, id: $select.search, telefone: $select.search, endereco: $select.search}">
													      <div ng-bind-html="cliente.nome | highlight: $select.search"></div>
													      <small>
													     nome: {{cliente.nome}}
													 endereco: {{cliente.endereco}}
													 telefone: <span ng-bind-html="''+cliente.telefone | highlight: $select.search"></span>
													      </small>
													    </choices>
													  </ui-select>
  													  <input name="entrega" class="span3 form-control" id="entrega" value="" placeholder="Hora de entrega" style="float:right" />
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												<div class="control-group">
													<label class="control-label">
														Itens Selecionados
													</label>
													<div class="controls">
													
													<div class="table-responsive">
															<table class="table table-striped table-bordered">
																<thead>
																	<tr>
																		<th>Código</th>
																		<th>Título</th>
																		<th>Valor</th>
																		<th>Quantidade</th>
																		<th>Excluir</th>
																	</tr>
																</thead>
																<tbody>
																		<tr ng-repeat="itemSelecionado in itensSelecionados">
																			<td><input name="itens" class="span1 form-control" id="numero" value="{{itemSelecionado.id}}" /></td>
																			<td>{{itemSelecionado.titulo}}</td>
																			<td>{{itemSelecionado.valor}}</td>
																			<td><input name="quantidades" class="span1 form-control" id="quantidades" value="1" /></td>
																			<td><button type="button" class="span1 btn btn-danger">X</button></td>
																		</tr>
																</tbody>
															</table>
													</div>								
																									
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												<div class="control-group">
													<label class="control-label">
														Valor
													</label>
													<div class="controls">
														<input name="valor" class="span3 form-control" id="valor" value="{{total}}" />
														<input name="troco" class="span3 form-control" id="troco" value="0.00" style="float:right" />
														<select name="pagamento" class="span3 form-control" style="float:right" >
															<option value="DINHEIRO">DINHEIRO</option>
															<option value="DEBITO">DEBITO</option>
															<option value="CREDITO">CREDITO</option>
															<option value="SODEXO">SODEXO</option>
															<option value="TICKET">TICKET</option>
															<option value="ALELO">ALELO</option>
														</select>
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												<div class="control-group">
													<label class="control-label">
														Valor
													</label>
													<div class="controls">
														<textarea name="observacoes" class="span5 form-control" id="observacoes"></textarea>
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												
												<div class="form-actions">
													<c:if test="${!empty pedido.id}">
														<button type="submit" class="btn btn-primary">
															Imprimir
														</button>
													</c:if>
													<c:if test="${empty pedido.id}">
														<button type="submit" class="btn btn-primary">
															Finalizar Pedido
														</button>
													</c:if>

													<button class="btn">Cancelar</button>
												</div>
												<!-- /form-actions -->
											</fieldset>
										</form>
											<div class="control-group">
													<label class="control-label">
														Cardápios
													</label>
													<div class="controls">
													<p>Selecionado: {{cardapio.selected}}</p>
													  <ui-select ng-model="cardapio.selected" theme="select2" ng-disabled="disabled" style="min-width: 300px;">
													    <match placeholder="Selecione um cardapio na lista...">{{$select.selected.titulo}}</match>
													    <choices repeat="cardapio in cardapios | propsFilter: {titulo: $select.search, id: $select.search}">
													      <div ng-bind-html="cardapio.titulo | highlight: $select.search"></div>
													      <small>
													         Valor: {{cardapio.valor}} <br />
													        Código: <span ng-bind-html="''+cardapio.id | highlight: $select.search"></span>
													      </small>
													    </choices>
													  </ui-select>
  													  <button class="btn btn-primary brn-block" ng-click="adicionarItem(cardapio.selected)">Adicionar Item</button>
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												
												<div style="height:300px"></div>
									</div>
									<div class="tab-pane" id="jscontrols">
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
														<c:if test="${!empty listaPedidos}">
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
															              "FROM cliente INNER JOIN pedido ON cliente.id = pedido.cliente WHERE pedido.caixa=(SELECT MAX(id) FROM caixa) ORDER BY id DESC";
															
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
														</c:if>
													</div>
													</div>
													<!-- /widget-content -->
												</div>
												<!-- /widget -->
											</fieldset>
										</form>
									</div>
								</div>
							</div>
						</div>
						<!-- /widget-content -->
					</div>
					<!-- /widget -->
				</div>
				<!-- /span8 -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /main-inner -->
</div>
<!-- /main -->

<c:import url="includes/footer.jsp" />