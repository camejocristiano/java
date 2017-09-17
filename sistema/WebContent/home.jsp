<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
    
<c:import url="includes/header.jsp" />
<c:import url="includes/menu.jsp" />

<div class="main">                                                                                                                    
  <div class="main-inner">
    <div class="container">
      <div class="row">
        <div class="span12">

          <div class="widget">
            <div class="widget-header"> <i class="icon-bookmark"></i>
              <h3>Home Sistema</h3>
            </div>
            <!-- /widget-header -->
            <div class="widget-content">
            	<div class="${classeBootstrap}">
			       <h3>${msg}</h3>
			    </div>
              <div class="shortcuts"> 
                <a href="pedidocontroller.do?acao=novo" class="shortcut">
                  <i class="shortcut-icon icon-bookmark"></i>
                    <span class="shortcut-label">Novo Pedido</span> 
                </a>
                <a href="cardapiocontroller.do?acao=listar" class="shortcut">
                  <i class="shortcut-icon icon-list-alt"></i>
                  <span class="shortcut-label">Cardápios</span>
                </a>
                <a href="clientecontroller.do?acao=listar" class="shortcut">
                  <i class="shortcut-icon icon-user"></i>
                    <span class="shortcut-label">Clientes</span> 
                </a>
                <a href="caixacontroller.do?acao=listar" class="shortcut">
                  <i class="shortcut-icon icon-list-alt"></i>
                  <span class="shortcut-label">Movimentações</span>
                </a>
              </div>
              <!-- /shortcuts --> 
            </div>
            <!-- /widget-content --> 
          </div>
          <!-- /widget -->


        </div>
        <!-- /span6 --> 
      </div>
      <!-- /row --> 
    </div>
    <!-- /container --> 
  </div>
  <!-- /main-inner --> 
</div>
<!-- /main -->

 
<c:import url="includes/footer.jsp" />