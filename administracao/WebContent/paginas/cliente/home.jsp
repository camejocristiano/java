<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<c:import url="../../includes/header.jsp"></c:import>
<c:import url="../../includes/menu.jsp"></c:import>
<c:import url="../../includes/sidebar.jsp"></c:import>


<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
  </div>
<!--End-breadcrumbs-->

<!--Action boxes-->
  <div class="container-fluid">
  
<!--End-Action boxes-->    

<!--Chart-box-->    
    <div class="row-fluid">
      <div class="widget-box">
        <div class="widget-title bg_lg"><span class="icon"><i class="icon-signal"></i></span>
          <h5>Pedidos</h5>
        </div>
        <div class="widget-content" >
          <div class="row-fluid">
            <div class="span12">
	          <div class="${classeBootstrap}">
			     <h3>${msg}</h3>
			  </div>
              <ul class="site-stats">
                <li class="bg_lh"><a href="clientecontroller.do?acao=addCliente"><i class="icon-edit"></i> <strong>Cliente</strong> <small>Cadastrar Cliente</small></a></li>
                <li class="bg_lh"><a href="clientecontroller.do?acao=listClientes"><i class="icon-list"></i> <strong>Clientes</strong> <small>Listar Clientes</small></a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
<!--End-Chart-box--> 
  </div>
</div>

<!--end-main-container-part-->


<c:import url="../../includes/footer.jsp"></c:import>