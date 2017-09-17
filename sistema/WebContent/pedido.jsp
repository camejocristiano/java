<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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
										data-toggle="tab">Visualização</a></li>
								</ul>
								<br>
								<div class="tab-content">
									<div class="tab-pane active" id="formcontrols">
										<c:url var="addAction" value=""></c:url>
										<form action="impressaocontroller.do" method="post"
											id="edit-profile" class="form-horizontal">
											<fieldset>
													<div class="control-group">
														<div class="controls">
															<input type="hidden" name="id" size="8" class="span6 disabled" id="pedido" value="${requestScope.pedido.id}" />
														</div>
														<!-- /controls -->
													</div>
													<!-- /control-group -->
												<div class="control-group">
													<label class="control-label">
														Número
													</label>
													<div class="controls">
														<input name="numero" class="span6 form-control" id="numero" value="${requestScope.pedido.numero}" />
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												<div class="control-group">
													<label class="control-label">
														Clientes
													</label>
													<div class="controls">
														<input type="text" name="cliente" class="span6 form-control" id="cliente" value="${requestScope.cliente.nome}" />
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												<div class="control-group">
													<label class="control-label">
														Itens do Pedido
													</label>
													<div class="controls">
																											
													<!-- /widget-header -->
													<div class="widget-content">
														<c:if test="${!empty itens}">
															<table class="table table-striped table-bordered">
																<thead>
																	<tr>
																		<th>Código</th>
																		<th>Título</th>
																		<th>Valor</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach var="item" items="${requestScope.itens}">
																		<tr>
																			<td>${item.id}</td>
																			<td>${item.titulo}</td>
																			<td>${item.valor}</td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														</c:if>
													</div>
													<!-- /widget-content -->
												  </div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
													
																									
												<div class="control-group">
													<label class="control-label">
														Valor
													</label>
													<div class="controls">
														<input name="valor" class="span2 form-control" id="valor" value="${requestScope.pedido.valor}" />
														<input name="pagamento" class="span2 form-control" id="pagamento" value="${requestScope.pedido.pagamento}" />
														<input name="troco" class="span2 form-control" id="troco" value="${requestScope.pedido.troco}" />
														<input name="entrega" class="span2 form-control" id="entrega" value="${requestScope.pedido.entrega}" />
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												
												<div class="form-actions">
													<button type="submit" class="btn btn-primary">Imprimir</button>
												</div>
												<!-- /form-actions -->
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


