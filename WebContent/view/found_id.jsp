<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />
<title>Löydetty</title>
</head>
<body>
<c:set var="peli" value="${pelit}"></c:set>
<div class="container" style="margin-top: 2rem">
	<table class="table table_striped">
		 <thead class="thead-dark">
			<tr>
				<th>Id</th>
				<th>Nimi</th>
				<th>Kuvaus</th>
				<th>Kehittäjä</th>
				<th>Julkaisija</th>
				<th>Julkaisuvuosi</th>
				<th>Arvostelut</th>
				<th>Hinta</th>
			</tr>
		</thead>
			<tr>
				<td><c:out value="${peli.id}" /></td> 
				<td><c:out value="${peli.nimi}" /></td>  
				<td><c:out value="${peli.kuvaus}" /></td> 
				<td><c:out value="${peli.kehittaja}" /></td> 
				<td><c:out value="${peli.julkaisija}" /></td>
				<td><c:out value="${peli.julkaisuvuosi}" /></td>
				<td><c:out value="${peli.arvostelu}" /></td>  
				<td><c:out value="${peli.hinta}" /></td>
			</tr>		
	</table>
	<c:if test="${empty pelit}"><h3>Listalta ei löytynyt mitään</h3></c:if><br>
	<a href="listaa-pelit"><button type="button" class="btn btn-success">Pelilista</button></a>
</div>
</body>
</html>