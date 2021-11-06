<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="model.object.equipment.*"%>
<%@page import="java.util.ArrayList"%>
<%
HashMap<String, ArrayList<Equipment>> listEquipments = (HashMap<String, ArrayList<Equipment>>) request
		.getAttribute("equipments");
%>

<html>
<head>
<title>Nouvel emprunt</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/view/loan/css/styleAddLoan.css" />

</head>
<body onload="main()">
	<main>
		<h1>Emprunt</h1>
		<form>
			<section>
				<div class="panel">
					<h3>Nouvel emprunt</h3>
					<select name="liste1" id="liste1">
						<option value="" selected disabled hidden>Type de
							matériel</option>
						<option value="vehicle">Véhicule</option>
						<option value="vehicleAccessory">Accessoire de véhicule</option>
						<option value="computer">Ordinateur</option>
						<option value="computerAccessory">Accessoire d'ordinateur</option>
					</select> <select name="liste2" id="liste2">
						<option value="" selected disabled hidden>Matériel</option>
						<%
						for (Map.Entry<String, ArrayList<Equipment>> entry : listEquipments.entrySet()) {
							String key = entry.getKey();
							ArrayList<Equipment> value = entry.getValue();
							for (Equipment e : value) {
						%>
						<option value="<%=e.getId()%>"><%=e.getName()%> :
							<%=key%></option>
						<%
						}
						}
						%>
					</select>
				</div>
				<div class="panel">
					<p>LES INFOS OEOEOEOOE !</p>
				</div>
			</section>
			<div class="section-button">
				<a href="#">Annuler</a> <a href="#">Suivant</a>
			</div>
		</form>
	</main>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
		crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/view/loan/js/main.js">
		
	</script>
</body>
</html>