
<%@ page language="java" pageEncoding="UTF-8"%>

<div class="form" id="additionalForm" >
  <div class="form-item input-group">
    <input id="kilometers" type="number" value="" name="kilometers" required>
    <label for="kilometers">Kilométrage : </label>
  </div>
  
  <div class="form-item input-group">
    <input id="renewalKilometers" type="number" value="" name="renewalKilometers" required>
    <label for="renewalKilometers">Révision kilométrage</label>
  </div>
  
  <div class="form-item input-group">
    <input id="vehicleBrand" type="text" value="" name="brand" required>
    <label for="brand">Marque</label>
  </div>

  <div class="form-item input-group">  
    <input id="vehicleModel" type="text" value="" name="model" required>
    <label for="model">Modèle</label>
  </div>
  
  <div class="form-item input-group">
    <input id="registrationNumber" type="text" value="" name="registrationNumber" required>
    <label for="registrationNumber">Plaque d'immatriculation</label>
  </div>

  <div class="form-item">
    <label for="state">Bon état</label>
    <input id="GOOD" type="radio" value="GOOD" name="state" required>

    <label for="state">Légère usure</label>
    <input id="AVERAGE" type="radio" value="AVERAGE" name="state" required>

    <label for="state">Mauvais état</label>
    <input id="BAD" type="radio" value="BAD" name="state" required>
  </div>

  <div class="form-item input-group">
    <input id="maxSpeed" type="number" value="" name="maxSpeed" required>
    <label for="maxSpeed">Vitesse maximale</label>
  </div>
  
  <div class="form-item input-group">
    <input id="numberOfSpeeds" type="number" value="" name="numberOfSpeeds" required>
    <label for="numberOfSpeeds">Nombre de vitesse</label>
  </div>
  
  <div class="form-item input-group">
    <input id="power" type="number" value="" name="power" required>
    <label for="power">Puissance (CH)</label>
  </div>
  
  <div class="search-type">
    <select id="selectTypeVehicle" onchange="addInput()">
      <option value="other">Autre</option>
      <option value="bike">Moto</option>
      <option value="car">Voiture</option>
    </select>
  </div>

  <div id="spec" class="form-item input-group"></div>
</div>