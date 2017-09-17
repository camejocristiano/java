<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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
							<div class="tabbable">
								<ul class="nav nav-tabs">
									<li class="active"><a href="#formcontrols"
										data-toggle="tab">Abrir Novo</a></li>
									<li><a href="#jscontrols" data-toggle="tab">Listar</a></li>
								</ul>
								<br>
								<div class="tab-content">
									<c:if test="${!empty msg}">
										<div class="${requestScope.classeBootstrap}" role="alert">${requestScope.msg}</div>
									</c:if>
									<div class="tab-pane active" id="formcontrols">
										<c:url var="addAction" value=""></c:url>
										<form action="caixacontroller.do" method="post"
											id="edit-profile" class="form-horizontal">
											<fieldset>
											
											<div class="control-group">
													<label class="control-label">
														
													</label>
													<div class="controls">
														<h3>Usuário</h3>
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												<div class="control-group">
													<label class="control-label">
														E-mail
													</label>
													<div class="controls">
														<input name="email" class="span6" id="email" />
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												<div class="control-group">
													<label class="control-label">
														Senha
													</label>
													<div class="controls">
														<input type="password" name="senha" class="span6" id="senha" />
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->												
												<div class="form-actions">
													<c:if test="${empty caixa.id}">
														<button type="submit" class="btn btn-primary">
															Iniciar
														</button>
													</c:if>
												</div>
												<!-- /form-actions -->
											</fieldset>
										</form>
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
														<h3>Caixas</h3>
													</div>
													<!-- /widget-header -->
													<div class="widget-content">
														<c:if test="${!empty listaCaixas}">
															<table class="table table-striped table-bordered">
																<thead>
																	<tr>
																		<th>Código</th>
																		<th>Usuario</th>
																		<th>Pedidos</th>
																		<th>Valor</th>
																		<th>Data</th>
																		<th class="td-actions">Listar</th>
																		<th class="td-actions">Fechar</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach var="caixa" items="${requestScope.listaCaixas}">
																		<tr>
																			<td>${caixa.id}</td>
																			<td>${caixa.usuario}</td>
																			<td>${caixa.pedidos}</td>
																			<td>${caixa.valor}</td>
																			<td>${caixa.dia}/${caixa.mes}/${caixa.ano}</td>
																			<td class="td-actions">
																			<a href="caixacontroller.do?acao=visualizar&id=${caixa.id}"
																				class="btn btn-small btn-success">
																				<i class="btn-icon-only icon-ok"> </i>
																			</a>
																			</td>
																			<td class="td-actions">
																			<a href="impressaocaixacontroller.do?id=${caixa.id}"
																				class="btn btn-danger btn-small">
																				<i	class="btn-icon-only icon-remove"> </i>
																			</a>
																			</td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														</c:if>
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


