package controller.equipment.list;

import model.dao.*;
import model.object.equipment.*;
import model.object.loan.Loan;
import model.object.user.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ListOtherController extends HttpServlet {

	private Gson gson;

	private EquipmentDAO eDAO;
  private LoanDAO loanDAO;
	private VehicleDAO vehicleDAO;
	private VehicleAccessoryDAO vehicleAccessoryDAO;
	private ComputerDAO computerDAO;
	private ComputerAccessoryDAO computerAccessoryDAO;
  private UserDAO userDAO;



	public ListOtherController() {
		this.eDAO = new EquipmentDAO();
		this.vehicleDAO = new VehicleDAO();
		this.vehicleAccessoryDAO = new VehicleAccessoryDAO();
		this.computerDAO = new ComputerDAO();
		this.computerAccessoryDAO = new ComputerAccessoryDAO();
    this.userDAO = new UserDAO();
    this.loanDAO = new LoanDAO();
		this.gson = new Gson();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)  {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    System.out.println("Start List other");
    ArrayList<Equipment> allEquipments = this.eDAO.listAllIdAndName();
    System.out.println(" 1/6 Done");
		ArrayList<Equipment> listVehicle = this.castArrayList(this.vehicleDAO.listAllIdAndName());
    System.out.println(" 2/6 Done");
		ArrayList<Equipment> listVehicleAccessory = this.castArrayList(this.vehicleAccessoryDAO.listAllIdAndName());
    System.out.println(" 3/6 Done");
		ArrayList<Equipment> listComputer = this.castArrayList(this.computerDAO.listAllIdAndName());
    System.out.println(" 4/6 Done");
		ArrayList<Equipment> listComputerAccessory = this.castArrayList(this.computerAccessoryDAO.listAllIdAndName());
    System.out.println(" 5/6 Done");
    ArrayList<Equipment> other = new ArrayList<Equipment>();
    ArrayList<User> users = this.userDAO.listAll();
    System.out.println(" 6/6 Done");
    System.out.println("début traitement");

    // list other equipments, TO IMPROVE
    for(Equipment equipment : allEquipments) {
      boolean isAVehicle = false;
      boolean isAVehicleAccessory= false;
      boolean isAComputerAccessory = false;
      boolean isAComputer = false;
      for(Equipment vehicle : listVehicle) {
        if(vehicle.getId() == equipment.getId()) {
          isAVehicle = true;
          break;
        }
      }
      for(Equipment va : listVehicleAccessory) {
        if(va.getId() == equipment.getId()) {
          isAVehicleAccessory = true;
          break;
        }
      }
      for(Equipment computer : listComputer) {
        if(computer.getId() == equipment.getId()) {
          isAComputer = true;
          break;
        }
      }

      for(Equipment computerAccessory : listComputerAccessory) {
        if(computerAccessory.getId() == equipment.getId()) {
          isAComputerAccessory = true;
          break;
        }
      }

      if(!isAComputer && !isAVehicle && !isAComputerAccessory && !isAVehicleAccessory) {
        other.add(equipment);
      }
      
    }
    System.out.println("fin traitement");

		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();

		String otherJson = this.gson.toJson(other);
    out.print(otherJson);
    System.out.println("FIN Other List");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

  private <N, O> ArrayList<N> castArrayList(ArrayList<O> list){
		ArrayList<N> newlyCastedArrayList = new ArrayList<N>();
		for(O listObject : list){
			newlyCastedArrayList.add((N)listObject);
		}
		return newlyCastedArrayList;
	}
}
