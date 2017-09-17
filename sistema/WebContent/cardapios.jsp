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
							<h3>Cardápio</h3>
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
										<form action="cardapiocontroller.do" method="post"
											id="edit-profile" class="form-horizontal">
											<fieldset>
												<c:if test="${!empty cardapio.id}">
													<div class="control-group">
														<label class="control-label">Id: </label>
														<div class="controls">
															<input type="text" name="id" size="8" class="span6 disabled" id="cardapio" value="${requestScope.cardapio.id}" />
															<p class="help-block">Campo dezabilitado.</p>
														</div>
														<!-- /controls -->
													</div>
													<!-- /control-group -->
												</c:if>
												<div class="control-group">
													<label class="control-label">
														Título
													</label>
													<div class="controls">
														<input name="titulo" class="span6" id="titulo" value="${cardapio.titulo}" />
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												<div class="control-group">
													<label class="control-label">
														Descrição
													</label>
													<div class="controls">
														<input name="descricao" class="span6" id="descricao" value="${cardapio.descricao}" />
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												<div class="control-group">
													<label class="control-label">
														Valor
													</label>
													<div class="controls">
														<input name="valor" class="span6" id="valor" value="${cardapio.valor}" />
													</div>
													<!-- /controls -->
												</div>
												<!-- /control-group -->
												
												<div class="form-actions">
													<c:if test="${!empty cardapio.id}">
														<button type="submit" class="btn btn-primary">
															Editar
														</button>
													</c:if>
													<c:if test="${empty cardapio.id}">
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
														<h3>Cardápios</h3>
													</div>
													<!-- /widget-header -->
													<div class="widget-content">
														<c:if test="${!empty lista}">
															<table class="table table-striped table-bordered">
																<thead>
																	<tr>
																		<th>Código</th>
																		<th>Título</th>
																		<th>Descrição</th>
																		<th>Valor</th>
																		<th class="td-actions">Editar</th>
																		<th class="td-actions">Deletar</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach var="cardapio" items="${requestScope.lista}">
																		<tr>
																			<td>${cardapio.id}</td>
																			<td>${cardapio.titulo}</td>
																			<td>${cardapio.descricao}</td>
																			<td>${cardapio.valor}</td>
																			<td class="td-actions">
																			<a href="cardapiocontroller.do?acao=alterar&id=${cardapio.id}"
																				class="btn btn-small btn-success">
																				<i class="btn-icon-only icon-ok"> </i>
																			</a>
																			</td>
																			<td class="td-actions">
																			<a href="cardapiocontroller.do?acao=excluir&id=${cardapio.id}"
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


