<%@ page language="java" pageEncoding="UTF-8"%>

<div style="display : flex ; flex-direction: column; align-items: flex-start">
  <label for="brand">Marque</label>
  <input type="text" name="brand" required>

  <label for="model">Modèle</label>
  <input type="text" name="model" required>

  <label for="serialNumber">n° de série</label>
  <input type="text" name="serialNumber" required>

  <label for="memorySize">Taille de la mémoire</label>
  <input type="number" name="memorySize" required>

  <div>
    <label for="isLaptop">PC portable ?</label>
    <label>Oui</label>
    <input type="radio" name="isLaptop"  value="true" required>
    <label>Non</label>
    <input type="radio" name="isLaptop"  value="false" required>
  </div>

  <label for="screenSize">Taille de l'écran</label>
  <input type="number" name="screenSize" required>

  <label for="purchaseDate">Date d'achat</label>
  <input type="date" name="purchaseDate" required>

  <label for="renewalDate">Date de renouvellement</label>
  <input type="date" name="renewalDate" required>

  <div>
    <h3>Processeur : </h3>
    <label for="processorBrand">Marque</label>
    <input type="text" value="" name="processorBrand" required>
    
    <label for="processorName">Nom</label>
    <input type="text" value="" name="processorName" required>

    <label for="numberOfCores">Nombre de coeurs</label>
    <input type="number" value="" name="numberOfCores" required>

    <label for="processorFrequency">Fréquence</label>
    <input type="number" value="" name="processorFrequency">
  </div>

  <div>
    <h3>Carte graphique</h3>
    <label for="graphicCardBrand">Marque</label>
    <input type="text" value="" name="graphicCardBrand" required>

    <label for="graphicCardName">Nom</label>
    <input type="text" value="" name="graphicCardName" required>

    <label for="graphicCardFrequency">Fréquence</label>
    <input type="number" value="" name="graphicCardFrequency">
  </div>
</div>