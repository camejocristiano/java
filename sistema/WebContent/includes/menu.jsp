<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
    
<div class="subnavbar">
  <div class="subnavbar-inner">
    <div class="container">
      <ul class="mainnav">
        <li><a href="pedidocontroller.do?acao=novo"><i class="icon-bookmark"></i><span>Novo Pedido</span> </a></li>
        <li><a href="cardapiocontroller.do?acao=listar"><i class="icon-list-alt"></i><span>Cardapios</span> </a> </li>
        <li><a href="clientecontroller.do?acao=listar"><i class="icon-user"></i><span>Clientes</span> </a> </li>
        <li><a href="caixacontroller.do?acao=listar"><i class="icon-list-alt"></i><span>Movimentação</span> </a> </li>
      </ul>
    </div>
    <!-- /container --> 
  </div>
  <!-- /subnavbar-inner --> 
</div>
<!-- /subnavbar -->