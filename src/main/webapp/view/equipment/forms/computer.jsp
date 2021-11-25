<%@ page language="java" pageEncoding="UTF-8"%>

<div class="form" id="additionalForm">
  <div class="form-item input-group">
    <input id="computerBrand" type="text" name="brand" required>
    <label for="brand">Marque</label>
  </div>
  <div class="form-item input-group">
    <input id="computerModel" type="text" name="model" required>
    <label for="model">Modèle</label>
  </div>
  
  <div class="form-item input-group">
    <input id="serialNumber" type="text" name="serialNumber" required>
    <label for="serialNumber">n° de série</label>
  </div>
  
  <div class="form-item input-group">  
    <input id="memorySize" type="number" name="memorySize" required>
    <label for="memorySize">Taille de la mémoire</label>
  </div>
  
  <div class="form-item dispo-item">
    <p>PC portable ?</p>
    <div>
      <label>Non</label>
      <input id="isLaptop" type="radio" name="isLaptop"  value="true" required>
      <label>Oui</label>
      <input id="notLaptop" type="radio" name="isLaptop"  value="false" required>
    </div>
  </div>
  <div class="form-item input-group">  
    <input id="screenSize" type="number" name="screenSize" required>
    <label for="screenSize">Taille de l'écran</label>
  </div>  
  <label for="purchaseDate">Date d'achat</label>
  <input id="date" type="date" name="purchaseDate" required>
 
  <label for="renewalDate">Date de renouvellement</label>
  <input id="renewalDate" type="date" name="renewalDate" required>

  <div class="form">
    <h3>Processeur : </h3>
    <label id="processorLabel" >Choisir un processeur déjà existant</label>
    
    <div class="processor-slider" title="Choisir un processeur déjà existant">
	    <label class="switch">
	        <input type="checkbox" name="processorChoice" id="processorChoice">
	        <span onclick="processorClick()" class="slider round" ></span>
	    </label>
    </div>
    
    <div  id="processorDivSelect" class="search-type processorItem">
      <select  id="processorSelect" name="processorSelect"></select>
    </div>
    <div id="processorForm" style="display:none">
	  <div class="form-item input-group">     
        <input id="processorBrand" type="text" value="" name="processorBrand">
        <label for="processorBrand">Marque</label>
      </div>
      
      <div class="form-item input-group"> 
        <input id="processorName" type="text" value="" name="processorName">
        <label for="processorName">Nom</label>
	  </div>
	  
	  <div class="form-item input-group"> 
        <input  id="numberOfCores" type="number" value="" name="numberOfCores">
        <label for="numberOfCores">Nombre de coeurs</label>
	  </div>
      <div class="form-item input-group"> 
        <input id="processorFrequency" type="number" value="" name="processorFrequency" step="0.1">
        <label for="processorFrequency">Fréquence</label>
      </div>
    </div>
  </div>

  <div class="form">
    <h3>Carte graphique</h3>
    <label id="graphicCardLabel" >Choisir une carte graphique déjà existante</label>
    
    <div class="graphic-slider" title="Choisir une carte graphique déjà existant">
	    <label class="switch" >
	        <input type="checkbox" id="graphicCardChoice" name="graphicCardChoice">
	        <span onclick="graphicCardClick()" class="slider round" ></span>
	    </label>
	</div>
	
	<div  id="graphicCardDivSelect" class="search-type graphicCardItem">
    	<select id="graphicCardSelect" name="graphicCardSelect"></select>
    </div>
    
    <div id="graphicCardForm" style="display:none">
      <div class="form-item input-group"> 
        <input  id="graphicCardBrand" type="text" value="" name="graphicCardBrand">
        <label for="graphicCardBrand">Marque</label>
	  </div>
	  
	  <div class="form-item input-group"> 
        <input id="graphicCardName" type="text" value="" name="graphicCardName">
        <label for="graphicCardName">Nom</label>
	  </div>
	  
	  <div class="form-item input-group"> 
        <input id="graphicCardFrequency" type="number" value="" name="graphicCardFrequency" step="0.1">
        <label for="graphicCardFrequency">Fréquence</label>
      </div>
    </div>
 
  </div>
</div>