<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.LinkedList" import = "model.Bean.*" import = "model.DAO.ProductDAO_DS" 
    import = "java.util.Collection" import = "java.util.Iterator" import = "java.util.LinkedList" import = "java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="stileBrand.css">
<title>Brand</title>
</head>
<body>
	<%@ include file="menu.html" %>	<!-- import del menu -->  
	  
	<h1>I brand, nonchè fidati partner di MondoRex</h1>
	<% 
		ArrayList<String> brands = new ArrayList<>();
		ProductDAO_DS pdao = new ProductDAO_DS();
		try {
			brands = pdao.doRetrieveBrand();
			Iterator<String> it = brands.iterator();
			out.print("<div id='container'>");
			while (it.hasNext()) {
				out.print("<div class='brand'>" + it.next() + "</div>");
			}
			out.print("</div>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
        %>


   	<%@ include file="footer.html" %>	<!-- import del footer -->	
    
</body>
</html>