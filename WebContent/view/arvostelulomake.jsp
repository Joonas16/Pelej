<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />
<title>Arvostele</title>
</head>
<body>
<c:set var="peli" value="${pelit}"></c:set>
<h1>Arvostele peli ${peli.nimi}</h1>

<form action="arvostele_peli" method="post">
			<table>
			
				<tr>
					<td>Arvostele numeroilla</td>
					<td><input type="text" 
					placeholder="0-5"
					name="arvostelu" size="50"/>
					<td>
						<input type="submit" value="Tallenna"/>
					</td>
				</td>
			</table>
			</form>
</body>
</html>