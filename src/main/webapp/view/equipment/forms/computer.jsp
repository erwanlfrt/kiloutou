<%@ page language="java" pageEncoding="UTF-8"%>

<div id="additionalForm" style="display : flex ; flex-direction: column; align-items: flex-start">
  <label for="brand">Marque</label>
  <input id="computerBrand" type="text" name="brand" required>

  <label for="model">Modèle</label>
  <input id="computerModel" type="text" name="model" required>

  <label for="serialNumber">n° de série</label>
  <input id="serialNumber" type="text" name="serialNumber" required>

  <label for="memorySize">Taille de la mémoire</label>
  <input id="memorySize" type="number" name="memorySize" required>

  <div>
    <label for="isLaptop">PC portable ?</label>
    <label>Oui</label>
    <input id="isLaptop" type="radio" name="isLaptop"  value="true" required>
    <label>Non</label>
    <input id="notLaptop" type="radio" name="isLaptop"  value="false" required>
  </div>

  <label for="screenSize">Taille de l'écran</label>
  <input id="screenSize" type="number" name="screenSize" required>

  <label for="purchaseDate">Date d'achat</label>
  <input id="date" type="date" name="purchaseDate" required>

  <label for="renewalDate">Date de renouvellement</label>
  <input id="renewalDate" type="date" name="renewalDate" required>

  <div>
    <h3>Processeur : </h3>
    <label class="switch" >
        <input type="checkbox" name="processorChoice" id="processorChoice">
        <span onclick="processorClick()" class="slider round" ></span>
    </label>
    <label id="processorLabel" >Choisir un processeur déjà existant</label>
    <select id="processorSelect" name="processorSelect"></select>
    <div id="processorForm">
      <label for="processorBrand">Marque</label>
      <input id="processorBrand" type="text" value="" name="processorBrand">
      
      <label for="processorName">Nom</label>
      <input id="processorName" type="text" value="" name="processorName">

      <label for="numberOfCores">Nombre de coeurs</label>
      <input  id="numberOfCores" type="number" value="" name="numberOfCores">

      <label for="processorFrequency">Fréquence</label>
      <input id="processorFrequency" type="number" value="" name="processorFrequency" step="0.1">
    </div>
  </div>

  <div>
    <h3>Carte graphique</h3>
    <label class="switch" >
        <input type="checkbox" id="graphicCardChoice" name="graphicCardChoice">
        <span onclick="graphicCardClick()" class="slider round" ></span>
    </label>
    <label id="graphicCardLabel" >Choisir une carte graphique déjà existante</label>
    <select id="graphicCardSelect" name="graphicCardSelect"></select>
    <div id="graphicCardForm">
      <label for="graphicCardBrand">Marque</label>
      <input  id="graphicCardBrand" type="text" value="" name="graphicCardBrand">

      <label for="graphicCardName">Nom</label>
      <input id="graphicCardName" type="text" value="" name="graphicCardName">

      <label for="graphicCardFrequency">Fréquence</label>
      <input id="graphicCardFrequency" type="number" value="" name="graphicCardFrequency" step="0.1">
    </div>
    
  </div>
</div>