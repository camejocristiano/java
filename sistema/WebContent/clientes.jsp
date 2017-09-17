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
							<h3>Cliente</h3>
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
									<div class="tab-pane active" id="formcontrols">
										<c:url var="addAction" value=""></c:url>
										<form action="clientecontroller.do" method="post"
											id="edit-profile" class="form-horizontal">
											<fieldset>
												<c:if test="${!empty cliente.id}">
													<div class="control-group">
														<label class="control-label">Id: </label>
														<div class="controls">
															<input type="text" name="id" size="8" class="span6 disabled" id="cliente" value="${requestScope.cliente.id}" />
															<p class="help-block">Campo dezabilitado.</p>
														</div>
														<!-- /controls -->
													</div>
													<!-- /control-group -->
												</c:if>
												<div class="control-group">
													<label class="control-label">
														Nome
													</label>
													<div class="controls">
														<input name="nome" class="span6" id="nome" value="${requestScope.cliente.nome}" />
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												<div class="control-group">
													<label class="control-label">
														Endereço
													</label>
													<div class="controls">
														<input name="endereco" class="span6" id="endereco" value="${requestScope.cliente.endereco}" />
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												<div class="control-group">
													<label class="control-label">
														Telefone
													</label>
													<div class="controls">
														<input name="telefone" class="span6" id="telefone" value="${requestScope.cliente.telefone}" />
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												<div class="control-group">
													<label class="control-label">
														Empresa
													</label>
													<div class="controls">
														<input name="empresa" class="span6" id="empresa" value="${requestScope.cliente.empresa}" />
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												<div class="control-group">
													<label class="control-label">
														Telefone Empresa
													</label>
													<div class="controls">
														<input name="telefoneempresa" class="span6" id="telefoneempresa" value="${requestScope.cliente.telefoneempresa}" />
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												<div class="control-group">
													<label class="control-label">
														Observações
													</label>
													<div class="controls">
														<textarea name="observacoes" class="span6" id="observacoes">${requestScope.cliente.observacoes}</textarea>
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												
												<div class="form-actions">
													<c:if test="${!empty cliente.id}">
														<button type="submit" class="btn btn-primary">
															Editar
														</button>
													</c:if>
													<c:if test="${empty cliente.id}">
														<button type="submit" class="btn btn-primary">
															Adicionar
														</button>
													</c:if>

													<button class="btn">Cancelar</button>
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
														<h3>Clientes</h3>
													</div>
													<!-- /widget-header -->
													<div class="widget-content">
													<div class="table-responsive">
														<c:if test="${!empty lista}">
															<table class="table table-striped table-bordered">
																<thead>
																	<tr>
																		<th>Id</th>
																		<th>Nome</th>
																		<th>Endereço</th>
																		<th>Telefone</th>
																		<th class="td-actions">Editar</th>
																		<th class="td-actions">Deletar</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach var="cliente" items="${requestScope.lista}">
																		<tr>
																			<td>${cliente.id}</td>
																			<td>${cliente.nome}</td>
																			<td>${cliente.endereco}</td>
																			<td>${cliente.telefone}</td>
																			<td class="td-actions">
																			<a href="clientecontroller.do?acao=alterar&id=${cliente.id}"
																				class="btn btn-small btn-success">
																				<i class="btn-icon-only icon-ok"> </i>
																			</a>
																			</td>
																			<td class="td-actions">
																			<a href="clientecontroller.do?acao=excluir&id=${cliente.id}"
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


