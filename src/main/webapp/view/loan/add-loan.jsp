<%@ page language="java" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Nouvel emprunt</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/loan/css/styleAddLoan.css" />
</head>
<body>
	<main>
		<h1>Emprunt</h1>
		<form>
			<section>
				<div>
					<h3>Nouvel emprunt</h3>
					<select name="liste1" id="liste1">
						<option value="" selected disabled hidden>Liste d�roulante 1</option>
						<option value="Choix A">Choix A</option>
					</select>
					<select name="liste2" id="liste2">
						<option value="" selected disabled hidden>Liste d�roulante 2</option>
						<option value="Choix A">Choix A</option>
					</select>
				</div>
				<div>
				
				</div>
			</section>
			<div>
				<a>Annuler</a>
				<a>Suivant</a>
			</div>
		</form>
	</main>

</body>
</html>