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
												<div class="form-actions">
												<div class="control-group">
													<label class="control-label">
														Selecione um item na lista abaixo e adicione ao pedidos
													</label>
													<div class="controls">
													<p>{{cardapio.selected.titulo}}  .......  {{cardapio.selected.valor}}</p>
													  <ui-select ng-model="cardapio.selected" theme="select2" style="min-width: 300px;" ng-required="true">
													    <match placeholder="Selecione um cardapio na lista...">{{$select.selected.titulo}}</match>
													    <choices repeat="cardapio in cardapios | propsFilter: {titulo: $select.search, id: $select.search}">
													      <div ng-bind-html="cardapio.titulo | highlight: $select.search"></div>
													      <small>
													         Valor: {{cardapio.valor}} <br />
													        Código: <span ng-bind-html="''+cardapio.id | highlight: $select.search"></span>
													      </small>
													    </choices>
													  </ui-select>
  													  <button class="btn btn-primary brn-block" ng-click="adicionarItem(cardapio.selected)" ng-disabled="!cardapio.selected">Adicionar Item</button>
													  <a href="clientecontroller.do?acao=listar" style="float:right"><i class="icon-user"></i> - Novo Cliente</a>
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												</div>

										<c:url var="addAction" value=""></c:url>
										<form action="pedidocontroller.do" method="post" name="formPedido"
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
													<p> 
														Cliente:
													</p>
													</label>
													<div class="controls">
													
													<input type="hidden" name="cliente" class="span6 form-control" id="cliente" value="{{cliente.selected.id}}" />
													  <ui-select ng-model="cliente.selected" theme="select2" ng-disabled="disabled" style="min-width: 300px;" ng-required="true">
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
													  <p> 
														{{cliente.selected.nome}} <br />
														{{cliente.selected.telefone}} <br />
														{{cliente.selected.endereco}} <br />
													   </p>
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
																		<th>Excluir</th>
																	</tr>
																</thead>
																<tbody>
																		<tr ng-class="classe"  ng-repeat="itemSelecionado in itensSelecionados">
																			<td><input name="itens" class="span1 form-control" id="numero" value="{{itemSelecionado.id}}" /></td>
																			<td>{{itemSelecionado.titulo}}</td>																			
																			<td>{{itemSelecionado.valor}}</td>
																			<td><input type="hidden" name="quantidades" class="span1 form-control" id="quantidades" value="1" />
																			<button type="button" ng-click="apagarItem(itemSelecionado)" class="span1 btn btn-danger">X</button></td>
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
														<input type="hidden" name="valor" class="span3 form-control" id="valor" value='{{total}}' />
														<span style="float:right; margin:7px;"> Total - {{total | currency:"R$ "}}</span>
														<select name="pagamento" class="span3 form-control" ng-required="true">
															<option value="">Pagamento</option>
															<option value="DINHEIRO">DINHEIRO</option>
															<option value="DEBITO">DEBITO</option>
															<option value="CREDITO">CREDITO</option>
															<option value="SODEXO">SODEXO</option>
															<option value="TICKET">TICKET</option>
															<option value="ALELO">ALELO</option>
														</select>
														<input type="text" ng-change="fazerTroco(troco)" ng-model="troco" name="troco" class="span3 form-control" id="troco" value='{{troco}}' style="float:right" />
														<span style="float:right; margin:7px;"> Troco - </span>
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												<div class="control-group">
													<label class="control-label">
														Observações
													</label>
													<div class="controls">
														<textarea name="observacoes" class="span5 form-control" id="observacoes"></textarea>
														<input type="hidden" type="text" style="float:right" name="diferenca" class="span3 disabled form-control" id="diferenca" value='{{diferenca}}' />
														<span style="float:right; margin:7px"> Difirença - {{diferenca | currency:"R$ "}}</span>
														<span style="float:right; margin:7px;"> Troco - {{troco | currency:"R$ "}}</span>
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												
												<div class="form-actions">
														<button type="submit" class="btn btn-primary" style="float:right;margin-left:7px" ng-disabled="formPedido.$invalid">
															Finalizar Pedido
														</button>
													<a href="maincontroller.do" class="btn btn-danger" style="float:right;margin-left:7px">Cancelar</a>
												</div>
												<!-- /form-actions -->
											</fieldset>
										</form>
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