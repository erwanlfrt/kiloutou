
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap , java.util.Map"%>
<%
  HashMap<String, Integer> equipmentDistribution = (HashMap<String, Integer>) request.getAttribute("equipmentDistribution") ;
  HashMap<String, Integer> mostLoanedEquipments = (HashMap<String, Integer>) request.getAttribute("mostLoanedEquipments");
  HashMap<String, Integer> bestLoaners = (HashMap<String, Integer>) request.getAttribute("bestLoaners");
  HashMap<String, Integer> loanDistribution = (HashMap<String, Integer>) request.getAttribute("loanDistribution");
  int[] loanDistributionPerMonth = (int[]) request.getAttribute("loanDistributionPerMonth");
%>
<html>
  <head>
    <title>Statistiques</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/stats/css/statistic.css" />
  </head>
  <body>
  <%@include file="../layout/header.jsp" %>
  <main>
	  <section>
	  	<div id="title">
	      <h1>Statistiques</h1>
		  <div id="line"></div>
		</div>
		
		<div id="graphs">
		
			
		    <div id="equipmentDistribution">
		      <h2>Répartition des équipements par type</h2>
		      <canvas id="equipmentDistributionCanvas" width="400" height="400"></canvas>
		    </div>
		
		    <div id="mostLoanedEquipments">
		      <h2>Équipements les plus empruntés</h2>
		      <canvas id="mostLoanedEquipmentsCanvas" width="400" height="400"></canvas>
		    </div>
		
		    <div id="bestLoaners">
		      <h2>Les utilisateurs les plus emprunteurs</h2>
		      <canvas id="bestLoanersCanvas" width="400" height="400"></canvas>
		    </div>
		
		    <div id="loanDistribution">
		      <h2>Répartition des emprunts</h2>
		      <canvas id="loanDistributionCanvas" width="400" height="400"></canvas>
		    </div>
		
		    <div id="loanDistributionPerMonth">
		      <h2>Répartition des emprunts par mois</h2>
		      <canvas id="loanDistributionPerMonthCanvas" width="400" height="400"></canvas>
		    </div>
		</div>	  
	  </section>
  </main>
  </body>

  <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
  <script>
  
  	var equipmentDistributionKeys = [
		<%
      for (Map.Entry<String, Integer> entry : equipmentDistribution.entrySet()) {
    	  String k = entry.getKey();
          %>
            '<%= k%>',
          <%
        }

      %>
	];
  
  	var equipmentDistributionValues = [
  		<%
        for (Map.Entry<String, Integer> entry : equipmentDistribution.entrySet()) {
      	  Integer v= entry.getValue();
            %>
              <%= v%>,
            <%
          }

        %>
  	];
  	
  	var equipmentDistributionColors = [];
  	for(let i = 0 ; i < equipmentDistributionValues.length ; i++) {
      let red = Math.random() * 255;
      let green = Math.random() * 255;
      let blue = Math.random() * 255;
  		equipmentDistributionColors.push('rgb(' + red +',' + green +', ' + blue + ')');
  	}
   
    var ctx = document.getElementById("equipmentDistributionCanvas");
    var myChart = new Chart(ctx, {
      type: 'doughnut',
      data: {
    	  labels: equipmentDistributionKeys,
    		  datasets: [{
    		    label: 'My First Dataset',
    		    data: equipmentDistributionValues,
    		    backgroundColor: equipmentDistributionColors,
    		    hoverOffset: 4
    		  }]
      },
      options: {
        //cutoutPercentage: 40,
        responsive: false,

      }
    });



    var mostLoanedEquipmentsKeys = [
		<%
      for (Map.Entry<String, Integer> entry : mostLoanedEquipments.entrySet()) {
    	  String k = entry.getKey();
          %>
            '<%= k%>',
          <%
        }

      %>
	];
  
  	var mostLoanedEquipmentsValues = [
    <%
      for (Map.Entry<String, Integer> entry : mostLoanedEquipments.entrySet()) {
    	  Integer v= entry.getValue();
          %>
            <%= v%>,
          <%
        }

      %>
  	];
  	
  	var colors = [];
  	for(let i = 0 ; i < mostLoanedEquipmentsValues.length ; i++) {
      let red = Math.random() * 255;
      let green = Math.random() * 255;
      let blue = Math.random() * 255;
  		colors.push('rgb(' + red +',' + green +', ' + blue + ')');
  	}
   
    var ctx = document.getElementById("mostLoanedEquipmentsCanvas");
    var myChart = new Chart(ctx, {
    	type: 'bar',
        data: {
          labels: mostLoanedEquipmentsKeys,
          datasets: [
            {
              label: "nombre d'emprunts",
              backgroundColor: colors,
              data: mostLoanedEquipmentsValues,
            }
          ]
        },
        options: {
          scales: {
              yAxes: [{
                  ticks: {
                      beginAtZero: true
                  }
              }]
          },
          legend: { display: false },
          title: {
            display: true,
            text: 'Équipements les plus empruntés'
          },
          responsive : false
        }
    });



    var bestLoanersKeys = [
		<%
      for (Map.Entry<String, Integer> entry : bestLoaners.entrySet()) {
    	  String k = entry.getKey();
          %>
            '<%= k%>',
          <%
        }

      %>
	];
  
  	var bestLoanersValues = [
    <%
      for (Map.Entry<String, Integer> entry : bestLoaners.entrySet()) {
    	  Integer v= entry.getValue();
          %>
            <%= v%>,
          <%
        }

      %>
  	];
  	
  	var colors = [];
  	for(let i = 0 ; i < bestLoanersValues.length ; i++) {
      let red = Math.random() * 255;
      let green = Math.random() * 255;
      let blue = Math.random() * 255;
  		colors.push('rgb(' + red +',' + green +', ' + blue + ')');
  	}
   
    var ctx = document.getElementById("bestLoanersCanvas");
    var myChart = new Chart(ctx, {
    	type: 'bar',
        data: {
          labels:bestLoanersKeys,
          datasets: [
            {
              label: "nombre d'emprunts",
              backgroundColor: colors,
              data: bestLoanersValues,
            }
          ]
        },
        options: {
          scales: {
              yAxes: [{
                  ticks: {
                      beginAtZero: true
                  }
              }]
          },
          legend: { display: false },
          title: {
            display: true,
            text: 'Liste des utilisateurs qui empruntent le plus'
          },
          responsive : false
        }
    });


    var loanDistributionKeys = [
		<%
      for (Map.Entry<String, Integer> entry : loanDistribution.entrySet()) {
    	  String k = entry.getKey();
          %>
            '<%= k%>',
          <%
        }

      %>
	];
  
  	var loanDistributionValues = [
  		<%
        for (Map.Entry<String, Integer> entry : loanDistribution.entrySet()) {
      	  Integer v= entry.getValue();
            %>
              <%= v%>,
            <%
          }

        %>
  	];
  	
  	colors = [];
  	for(let i = 0 ; i < equipmentDistributionValues.length ; i++) {
      let red = Math.random() * 255;
      let green = Math.random() * 255;
      let blue = Math.random() * 255;
  		colors.push('rgb(' + red +',' + green +', ' + blue + ')');
  	}
   
    var ctx = document.getElementById("loanDistributionCanvas");
    var myChart = new Chart(ctx, {
      type: 'doughnut',
      data: {
    	  labels: loanDistributionKeys,
    		  datasets: [{
    		    label: 'My first dataset',
    		    data: loanDistributionValues,
    		    backgroundColor: colors,
    		    hoverOffset: 4
    		  }]
      },
      options: {
        //cutoutPercentage: 40,
        responsive: false,

      }
    });




    var loanDistributionPerMonthValues= [
	  	<%
        for (int i = 0 ; i < loanDistributionPerMonth.length ; i++) {
          %>
            <%= loanDistributionPerMonth[i] %>,
          <%
        }
      %>
	  ];
  
  	var loanDistributionPerMonthKeys = ["Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"];
  	
  	colors = [];
  	for(let i = 0 ; i < loanDistributionPerMonthValues.length ; i++) {
      let red = Math.random() * 255;
      let green = Math.random() * 255;
      let blue = Math.random() * 255;
  		colors.push('rgb(' + red +',' + green +', ' + blue + ')');
  	}
   
    var ctx = document.getElementById("loanDistributionPerMonthCanvas");
    var myChart = new Chart(ctx, {
      type: 'line',
      data: {
    	  labels: loanDistributionPerMonthKeys,
        datasets: [{
          label: 'My First Dataset',
          data: loanDistributionPerMonthValues,
          fill: false,
          borderColor: colors,
          tension: 0.1
        }]
      },
      options: {
        //cutoutPercentage: 40,
        responsive: false,

      }
    });


    

  </script>
</html>