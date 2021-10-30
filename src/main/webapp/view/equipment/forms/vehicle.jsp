<%@ page language="java" pageEncoding="UTF-8"%>
<div id="vehicleForm" style="display : flex ; flex-direction: column; align-items: flex-start" >

  <label for="kilometers">Kilométrage : </label>
  <input type="number" value="" name="kilometers" required>

  <label for="renewalKilometers">Révision kilométrage</label>
  <input type="number" value="" name="renewalKilometers" required>

  <label for="brand">Marque</label>
  <input type="text" value="" name="brand" required>

  <label for="model">Modèle</label>
  <input type="text" value="" name="model" required>

  <label for="registrationNumber">Plaque d'immatriculation</label>
  <input type="text" value="" name="registrationNumber" required>

  <div>
    <label for="state">Bon état</label>
    <input type="radio" value="GOOD" name="state" required>

    <label for="state">Légère usure</label>
    <input type="radio" value="AVERAGE" name="state" required>

    <label for="state">Mauvais état</label>
    <input type="radio" value="BAD" name="state" required>
  </div>

  <label for="maxSpeed">Vitesse maximale</label>
  <input type="number" value="" name="maxSpeed" required>

  <label for="numberOfSpeeds">Nombre de vitesse</label>
  <input type="number" value="" name="numberOfSpeeds" required>

  <label for="power">Puissance (CH)</label>
  <input type="number" value="" name="power" required>

  <div>
    <select id="selectTypeVehicle" onchange="addInput()">
      <option value="other">Autre</option>
      <option value="bike">Moto</option>
      <option value="car">Voiture</option>
    </select>
  </div>

  <div id="spec"></div>
</div>