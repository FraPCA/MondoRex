<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
<%@ include file="stileMenu.css" %>		
<%@ include file="stileFooter.css" %>
<%@ include file="stileErrorpage.css" %>
</style>
<meta charset="ISO-8859-1">
</head>
<body>
<%@ include file="menu.html" %>
<div class= pictureAndText>
<%
out.print("<div class=\"text\">");
out.print("<div class=\"picture\">");
if (response.getStatus() == 301){
	
	out.print("<h1 id=\"errormsg\">...Ooops something went wrong!</h1><br><h2 id=\"errorvalue\">"+response.getStatus()+ " Moved Permanently</h2>");
	out.print("<img src=\"/ConsegnaFinaleTSW/img/errorImages/errorImages/301.webp\"> heigh=480 width=480");
	out.print("<a href='/ConsegnaFinaleTSW/index.jsp'><button type='button'>Torna alla Home</button></a></div>");
	out.print("</div>");
	}

	else if (response.getStatus() == 400){
		
		out.print("<h1 id=\"errormsg\">...Ooops something went wrong!</h1><br><h2 id=\"errorvalue\">"+response.getStatus()+ " Bad Request</h2>");
		out.print("<img src=\"/ConsegnaFinaleTSW/img/errorImages/400.jpg\" heigh=480 width=480>");
		out.print("<a href='/ConsegnaFinaleTSW/index.jsp'><button type='button'>Torna alla Home</button></a></div>");
		out.print("</div>");	
		}
		else if (response.getStatus() == 401){
		
			out.print("<h1 id=\"errormsg\">...Ooops something went wrong!</h1><br><h2 id=\"errorvalue\">"+response.getStatus()+ " Unauthorized</h2>");
			out.print("<img src=\"/ConsegnaFinaleTSW/img/errorImages/401.jpg\" heigh=480 width=480>");
			out.print("<a href='/ConsegnaFinaleTSW/index.jsp'><button type='button'>Torna alla Home</button></a></div>");
			out.print("</div>");
			}
		
			else if (response.getStatus() == 403){
				
				out.print("<h1 id=\"errormsg\">...Ooops something went wrong!</h1><br><h2 id=\"errorvalue\">"+response.getStatus()+ " Forbidden</h2>");
				out.print("<img src=\"/ConsegnaFinaleTSW/img/errorImages/403.png\" height=480 width=480>");
				out.print("<a href='/ConsegnaFinaleTSW/index.jsp'><button type='button'>Torna alla Home</button></a></div>");
				out.print("</div>");
				
				}
				
			else if (response.getStatus() == 404){
				
				out.print("<h1 id=\"errormsg\">...Ooops something went wrong!</h1><br><h2 id=\"errorvalue\">"+response.getStatus()+ " Not Found</h2>");
				out.print("<img src=\"/ConsegnaFinaleTSW/img/errorImages/404.png\" height=480 width=480>");
				out.print("<a href='/ConsegnaFinaleTSW/index.jsp'><button type='button'>Torna alla Home</button></a></div>");
				out.print("</div>");
				}
					
					else if (response.getStatus() == 500){
						
						out.print("<h1 id=\"errormsg\">...Ooops something went wrong!</h1><br><h2 id=\"errorvalue\">"+response.getStatus()+ " Internal Server Error</h2>");
						out.print("<img src=\"/ConsegnaFinaleTSW/img/errorImages/500.jpg\" height=480 width=480>");
						out.print("<a href='/ConsegnaFinaleTSW/index.jsp'><button type='button'>Torna alla Home</button></a></div>");
						out.print("</div>");
					}

						else if (response.getStatus() == 501){
							
							out.print("<h1 id=\"errormsg\">...Ooops something went wrong!</h1><br><h2 id=\"errorvalue\">"+response.getStatus()+ " Not Implemented</h2>");
							out.print("<img src=\"/ConsegnaFinaleTSW/img/errorImages/501.jpeg\" heigh=480 width=480>");
							out.print("<a href='/ConsegnaFinaleTSW/index.jsp'><button type='button'>Torna alla Home</button></a></div>");
							out.print("</div>");
					}
%>
</div>
<%@include file="footer.html"%>
</body>
</html>