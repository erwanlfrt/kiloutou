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

public class ListVehicleAccessoriesController extends HttpServlet {

	private Gson gson;
	private VehicleAccessoryDAO vehicleAccessoryDAO;

	public ListVehicleAccessoriesController() {
		this.vehicleAccessoryDAO = new VehicleAccessoryDAO();
		this.gson = new Gson();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)  {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ArrayList<Equipment> listVehicleAccessories = this.vehicleAccessoryDAO.listAllIdAndName();

		// ArrayList<Equipment> allEquipments = this.eDAO.listAll();
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();

    String vehicleAccessoriesJson = this.gson.toJson(listVehicleAccessories);

    out.print(vehicleAccessoriesJson);
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
