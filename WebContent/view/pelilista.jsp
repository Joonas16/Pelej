<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />
<title>Pelit</title>
</head>
<style>
.nappula {
  -webkit-transition-duration: 0.4s;
  transition-duration: 0.4s;
}

.nappula:hover {
  background-color: #4CAF50;
  color: white;
}
</style>
<body>

<div class="container" style="margin-top: 2rem">
	<h1 style="text-align: center;margin-bottom: 3rem">Pelit</h1>
	<form action="etsi_peli">
		<input type="text" name="peliid" placeholder="Syötä pelin ID..." value="">
		<a href="found_id?peliid=<c:out value='${peli.id}'/>"><input class="nappula" type="submit" value="Etsi"></a>
    </form>
	<table class="table table_striped" style="width: 90%">
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
			<th>Arvostele</th>
			<th>Muokkaa</th>
			<th>Poista</th>
		</tr>
		</thead>
		<c:forEach items="${pelit}" var="peli"> <!-- käy pelit-listan kaikki oliot läpi -->
			
			<tr>
				<td><c:out value="${peli.id}" /></td> 
				<td><c:out value="${peli.nimi}" /></td>  
				<td><c:out value="${peli.kuvaus}" /></td> 
				<td><c:out value="${peli.kehittaja}" /></td> 
				<td><c:out value="${peli.julkaisija}" /></td>
				<td><c:out value="${peli.julkaisuvuosi}" /></td>
				<td><c:out value="${peli.arvostelu}" /></td>  
				<td><c:out value="${peli.hinta}" /></td>
				<td><a href="arvostele_peli?peliid=<c:out value='${peli.id}' />"><button type="button" class="btn btn-success btn-sm">Arvostele</button></a></td>
				<td><a href="muokkaa_peli?peliid=<c:out value='${peli.id}' />"><button type="button" onclick="return muokkaa()" class="btn btn-dark btn-sm">Muokkaa</button></a></td>
				<td><a href="poista_peli?peliid=<c:out value='${peli.id}' />"><button type="button" onclick="return poista()" class="btn btn-danger btn-sm">Poista</button></a></td>
			</tr>
		</c:forEach>
		<tr><td><a href="lisaa-peli"><button type="button" class="btn btn-success">Lisää peli</button></a></td></tr>
	</table>
</div>

<script>
<!-- Varmistus, haluaako käyttäjä poistaa pelin, mikäli käyttäjä painaa peruuta, peli ei poistu tietokannasta -->
function poista() { 
	if (confirm("Haluatko varmasti poistaa pelin?")) {
	    return true;
	  } else {
	    return false;
	  }
}
<!-- Varmistus, haluaako käyttäjä muokata peliä, mikäli käyttäjä painaa peruuta, selain ei siirry muokkauslomakkeelle -->
function muokkaa() {
	if (confirm("Haluatko muokata peliä?")) {
	    return true;
	  } else {
	    return false;
	  }
}
</script>
</body>
</html>