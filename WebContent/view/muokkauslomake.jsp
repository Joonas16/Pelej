<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />
<title>Muokkaa</title>
</head>
<body>
<h1>Muokkaa peliä</h1>
<c:set var="peli" value="${pelit}"></c:set>
<form action="muokkaa_peli" method="post">
			<table>
			
				<tr>
					<td>Nimi:</td>
					<td><input type="text" 
					value="${peli.nimi}"
					name="nimi" size="50" />
				</td>
				</tr>
				<tr>
					<td>Kuvaus:</td>
					<td><input type="text" 
					value="${peli.kuvaus}"
					name="kuvaus" size="50"  />
					</td>
				</tr>
				<tr>	
					<td>Kehittaja:</td>
					<td><input type="text" 
					value="${peli.kehittaja}"
					name="kehittaja" size="50"  />
					</td>
				</tr>
				<tr>	
					<td>Julkaisija:</td>
					<td><input type="text" 
					value="${peli.julkaisija}"
					name="julkaisija" size="50"  />
					</td>
				</tr>
				<tr>	
					<td>Julkaisuvuosi:</td>
					<td><input type="text" 
					value="${peli.julkaisuvuosi}"
					name="julkaisuvuosi" size="50"  />
					</td>
				</tr>
				<tr>	
					<td>Hinta:</td>
					<td><input type="text" 
					value="${peli.hinta}"
					name="hinta" size="50"  />
					</td>
				</tr>
					<td><div class ="button"><a href="listaa-pelit">Peruuta</a></div></td>
					<td>
						<input type="submit" value="Tallenna"/>
					</td>
				</tr>
		
			</table>
			</form>
			
</body>
</html>