
<%@ page language="java" pageEncoding="UTF-8"%>

<div id="additionalForm" style="display : flex ; flex-direction: column; align-items: flex-start" >

  <label for="kilometers">Kilométrage : </label>
  <input id="kilometers" type="number" value="" name="kilometers" required>

  <label for="renewalKilometers">Révision kilométrage</label>
  <input id="renewalKilometers" type="number" value="" name="renewalKilometers" required>

  <label for="brand">Marque</label>
  <input id="vehicleBrand" type="text" value="" name="brand" required>

  <label for="model">Modèle</label>
  <input id="vehicleModel" type="text" value="" name="model" required>

  <label for="registrationNumber">Plaque d'immatriculation</label>
  <input id="registrationNumber" type="text" value="" name="registrationNumber" required>

  <div>
    <label for="state">Bon état</label>
    <input id="GOOD" type="radio" value="GOOD" name="state" required>

    <label for="state">Légère usure</label>
    <input id="AVERAGE" type="radio" value="AVERAGE" name="state" required>

    <label for="state">Mauvais état</label>
    <input id="BAD" type="radio" value="BAD" name="state" required>
  </div>

  <label for="maxSpeed">Vitesse maximale</label>
  <input id="maxSpeed" type="number" value="" name="maxSpeed" required>

  <label for="numberOfSpeeds">Nombre de vitesse</label>
  <input id="numberOfSpeeds" type="number" value="" name="numberOfSpeeds" required>

  <label for="power">Puissance (CH)</label>
  <input id="power" type="number" value="" name="power" required>

  <div>
    <select id="selectTypeVehicle" onchange="addInput()">
      <option value="other">Autre</option>
      <option value="bike">Moto</option>
      <option value="car">Voiture</option>
    </select>
  </div>

  <div id="spec"></div>
</div>