<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
    

<c:import url="includes/header.jsp" />


<div class="account-container">
  
  <div class="content clearfix">
    <c:url var="addAction" value="logincontroller.do" ></c:url>
    <form action="${addAction}" method="post">
    
      <h1>Area de Login</h1>   
      
      <div class="login-fields">
        
        <p>Sistema de autenticação!</p>
        
        <div class="field">
          <label for="email">Username</label>
          <input type="text" id="email" name="email" value="" placeholder="Email" class="login username-field" />
        </div> <!-- /field -->
        
        <div class="field">
          <label for="senha">Senha:</label>
          <input type="password" id="senha" name="senha" value="" placeholder="Senha" class="login password-field"/>
        </div> <!-- /password -->
        
      </div> <!-- /login-fields -->
      
      <div class="login-actions">
        
        <span class="login-checkbox">
          <input id="Field" name="Field" type="checkbox" class="field login-checkbox" value="First Choice" tabindex="4" />
          <a>Esqueci minha senha!</a>
        </span>
                  
        <button class="button btn btn-success btn-large">Entrar</button>
        
      </div> <!-- .actions -->
      
      
      
    </form>
    
  </div> <!-- /content -->
  
</div> <!-- /account-container -->



<div class="login-extra">
  <a href="#">Reset Password</a>
</div> <!-- /login-extra -->






<c:import url="includes/footer.jsp" />
</body>
</html>