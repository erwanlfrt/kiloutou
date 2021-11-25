
<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="model.object.loan.Loan , model.object.user.* , model.object.equipment.* , java.time.LocalDate"%>
<%
  Loan loan = (Loan)request.getAttribute("loan");
  Equipment equipment = loan.getEquipment();
  User user = loan.getUser();
%>
<html>
  <head>
    <title>Modifier un emprunt</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/loan/css/header.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/loan/css/modify-loan.css" />
    <link  rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet"  href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
  </head>
  <body>
  
  <%@include file="../layout/header.jsp" %>
  
	<main>
	  <div>
	    <h1>Modifier un emprunt</h1>
	  </div>
	  <section>
	    <%
	      if (LocalDate.now().isBefore(loan.getBeginningDate()) || LocalDate.now().isBefore(loan.getEndDate()) ) {
	    %>
	    <form method="POST">
		<% 
	      }
		%>
	      <div class="info">
	          <%
	            if(LocalDate.now().isBefore(loan.getBeginningDate())) {
	              %>
	                <input class="datepicker" data-provide="datepicker" name="beginningDate" id="beginningDate" required>
	              <%
	            }else {
		          %>
		            <p><b>Date d'emprunt : </b><%= loan.getStringBeginningDate()%></p>
		          <%
		        } 
	
	          %>
	           <p>-</p>
	          <%
	            if(LocalDate.now().isBefore(loan.getEndDate())) {
	              %>
	                <input class="datepicker" data-provide="datepicker" name="endDate" id="endDate" required>
	              <%
	            }else {
		          %>
		            <p><b>Date de rendu : </b><%= loan.getStringEndDate()%></p>
		          <%
		        } 
	      %>
	      
	      </div>
	
	
	    <div class="user">
  		    <div class="user-name">
  				<img src="${pageContext.request.contextPath}/images?name=profil.png" alt="User" width="70px" height="70px">
  				<h2><%= user.getFirstname() %> <%= user.getName() %></h2>
  			</div>
      		<p>Mail : <%= user.getMail()%></p>
      		<p>Téléphone : <%= user.getPhoneNumber()%></p>
      		<p>Adresse : <%= user.getAddress()%></p>
  		</div>
	
	    <div class="equipment">
  			<img src="<%= equipment.getImageUrl() %>" alt="Equipement" width="70px" height="auto">
  			<h2><%= equipment.getName()%></h2>
  		</div>
		
		
	    <%
	      if (LocalDate.now().isBefore(loan.getBeginningDate()) || LocalDate.now().isBefore(loan.getEndDate()) ) {
	    %>
	         <div class="button-section">
	           <button type="submit">Envoyer</button> 
	      	 </div>
	      </form>
	    <%
	    }
	    %>
      </section>
	</main>  
  </body>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>  
  <script src="${pageContext.request.contextPath}/view/loan/js/header.js"></script>
  <script>
    var equipment;

    var currentLoanBeginningDate = '<%= loan.getStringBeginningDate()%>';
    var currentLoanEndDate = '<%= loan.getStringEndDate() %>';

    loadInfoEquipment();

    function setDatePickers(isInit) {
      let periods = equipment.loanedDates;
      let dates = [];
      for(let period of periods) {
        let beginningPeriod = new Date(period[0]);
        let endPeriod = new Date(period[1]);

        if(period[0] !== currentLoanBeginningDate) {
          while(beginningPeriod.getTime() <= endPeriod.getTime()) {
            let pFormat = beginningPeriod.toLocaleDateString('en-GB');
            dates.push(pFormat);
            beginningPeriod.setDate(beginningPeriod.getDate() + 1);
          }
        }

        
      }
      if(isInit) {
        $('#beginningDate').datepicker({
          format: 'dd/mm/yyyy',
          maxViewMode: 1,
          datesDisabled: dates,
          startDate : "today"
        });
        $('#beginningDate').datepicker('update', new Date(currentLoanBeginningDate));

        //on beginningDate change, set the minimum of endDate
        $('#beginningDate').datepicker().on('changeDate', function() {
          setDatePickers(false);
        });

        $('#endDate').datepicker({
          format: 'dd/mm/yyyy',
          maxViewMode: 1,
          datesDisabled: dates,
          startDate: new Date(currentLoanBeginningDate),
        }); 
        $('#endDate').datepicker('update', new Date(currentLoanEndDate));
      }
      else {
        var dateString = $('#beginningDate').val();
        var dateParts = dateString.split("/");
        var validString = dateParts[2] + '-' + dateParts[1] + '-' + dateParts[0];
        // var dateObject = new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]);
        
        
        //check for max date, infinity if no loan to come or the beginning date of the next loan to come.
        let beginningDateOfLoan = undefined;
        let nextLoan = undefined
        let now = new Date();
        let chosenBeginning =  new Date(currentLoanEndDate);

        if(new Date(validString).getTime() > new Date(currentLoanEndDate).getTime()) {
          chosenBeginning = new Date(validString);
        }

        for(let period of periods) {
          if(period[0] !== currentLoanBeginningDate) {
            beginningDateOfLoan = new Date(period[0]);
            if (beginningDateOfLoan.getTime() > chosenBeginning && (nextLoan === undefined || beginningDateOfLoan.getTime() < nextLoan.getTime())) { // toChange
              nextLoan = beginningDateOfLoan;
            }
          }
        }

        $('#endDate').datepicker({
          format: 'dd/m/yyyy',
          maxViewMode: 1,
          datesDisabled: dates,
          startDate: chosenBeginning,
        }); 
        $('#endDate').datepicker('setStartDate', chosenBeginning);
        $('#endDate').datepicker('update', chosenBeginning);
        if(nextLoan !== undefined) {
          $('#endDate').datepicker('setEndDate', nextLoan);
        }
        else {
          $('#endDate').datepicker('setEndDate',new Date(8640000000000000)); //infinity
        }

        


      }
      
    }

    function loadInfoEquipment() {
      $.ajax({
        //L'URL de la requête 
        url: "/kiloutou/loan/get/others?id=<%= loan.getEquipment().getId()%>",

        //La méthode d'envoi (type de requête)
        method: "GET",

        //Le format de réponse attendu
        dataType : "json",
      })
      .done(function(response){
        equipment = response;
        setDatePickers(true);
      });
    }
  </script>
</html>