<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload</title>
</head>
<body>

<form enctype="multipart/form-data" action="./uploadcontroller.do" method="post">
	<input type="file" name="f" />
	<input type="submit" class="btn" value="Salvar" />
</form>

Uploaded:

<img src="logos/${request.msg}" />

</body>
</html>