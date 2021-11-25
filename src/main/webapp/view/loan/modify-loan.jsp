
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
    <link  rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet"  href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
  </head>
  <body>
  
  <%@include file="../layout/header.jsp" %>
  
    <h1>Modifier un emprunt</h1>

    <div id="info">
      <%
        if(LocalDate.now().isAfter(loan.getBeginningDate())) {
          %>
            <p><b>Date d'emprunt : </b><%= loan.getStringBeginningDate()%></p>
          <%
        }

        if(LocalDate.now().isAfter(loan.getEndDate())) {
          %>
            <p><b>Date de rendu : </b><%= loan.getStringEndDate()%></p>
          <%
        }
      %>
     
      
    </div>


    <div id="user">
      <h2>Emprunteur</h2>

      <p><b>Nom : </b> <%= user.getName()%></p>
      <p><b>Prénom : </b><%= user.getFirstname()%></p>
      <p><b>Adresse mail : </b><%= user.getMail()%></p>
      <p><b>N° de téléphone : </b><%= user.getPhoneNumber()%></p>
      <p><b>Adresse : </b><%= user.getAddress()%></p>
    </div>

    <div id="equipment">
      <h2>Équipement : </h2>
      <p><b>Nom : </b><%= equipment.getName()%></p>
      <p><b>Illustration : </b></p>
      <img src="<%= equipment.getImageUrl()%>" style="width : 300px ; height : 300px">
    </div>


    <%
      if (LocalDate.now().isBefore(loan.getBeginningDate()) || LocalDate.now().isBefore(loan.getEndDate()) ) {
    %>
      <form method="POST">
        <%
          if(LocalDate.now().isBefore(loan.getBeginningDate())) {
            %>
            <input class="datepicker" data-provide="datepicker" name="beginningDate" id="beginningDate" required>
            <%
          }

          if(LocalDate.now().isBefore(loan.getEndDate())) {
            %>
              <input class="datepicker" data-provide="datepicker" name="endDate" id="endDate" required>
            <%
          }
        %>
      <input type="submit"> 
      </form>
    <%
    }
    %>

  
  </body>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>  
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