<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "model.Bean.*"%>
<%
Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
if (adminRoles == null)//l'utente non ha fatto il login
{
	response.sendRedirect("./InvalidLogin.jsp");
	return;//ferma l'esecuzione del codice jsp
}

Boolean admin = (Boolean) session.getAttribute("adminredirect");
if ((admin != null && admin.booleanValue()))
{
	response.sendRedirect("./AdminControl.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file ="stileBottomPages.css"%></style>
<style>
.bottone{
	background-color: #3D5DA4;
	color: white;
	border-radius: 50px;
	font-family: inherit;
}

.bottone:hover{
	background-color: #F78E69;;
	color: black;
	transition: ease-in-out 0.3s;
}

#logout:hover{
	background-color: red;
	color: white;
}

</style>
<title>Pagina riservata</title>
<jsp:useBean id="BeanUtente" class="model.Bean.UserBean" scope="session"/><!-- permette di utilizzare i bean importati -->
</head>
<body>
<%
	UserBean ub = (UserBean) session.getAttribute("utentesessione");
	out.print("<h1> Welcome " + ub.getNome() + " " + ub.getCognome() + " !");
%>
<br><br>
<form action="Logout" method="get">
<input class="bottone" id="logout" type="submit" value="Logout">
</form>
<a href="Catalogo2.jsp">
	<button class="bottone">Vai al catalogo</button>
</a>
</body>
</html>