package controller.loan;

import model.dao.*;
import model.object.equipment.*;

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

public class AddLoanController extends HttpServlet {

	private Gson gson;

	private EquipmentDAO eDAO;

	private VehicleDAO vehicleDAO;
	private VehicleAccessoryDAO vehicleAccessoryDAO;
	private ComputerDAO computerDAO;
	private ComputerAccessoryDAO computerAccessoryDAO;


	public AddLoanController() {
		this.eDAO = new EquipmentDAO();
		this.vehicleDAO = new VehicleDAO();
		this.vehicleAccessoryDAO = new VehicleAccessoryDAO();
		this.computerDAO = new ComputerDAO();
		this.computerAccessoryDAO = new ComputerAccessoryDAO();
		this.gson = new Gson();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)  {
		
		if(request.getParameter("idEquipment").trim() != null) {
			response.setContentType("text/plain");
			try {
				response.getWriter().write("ça fonctiooooooone");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
		HashMap<String, ArrayList<Equipment>> listEquipments = new HashMap<String, ArrayList<Equipment>>();

		ArrayList<Equipment> listVehicle = this.castArrayList(this.vehicleDAO.listAll());
		ArrayList<Equipment> listVehicleAccessory = this.castArrayList(this.vehicleAccessoryDAO.listAll());
		ArrayList<Equipment> listComputer = this.castArrayList(this.computerDAO.listAll());
		ArrayList<Equipment> listComputerAccessory = this.castArrayList(this.computerAccessoryDAO.listAll());

		listEquipments.put("vehicle", listVehicle);
		listEquipments.put("vehicleAccessory", listVehicleAccessory);
		listEquipments.put("computer", listComputer);
		listEquipments.put("computerAccessory", listComputerAccessory);

		request.setAttribute("equipments", listEquipments);

		String pageName="/view/loan/add-loan.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(pageName);

		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//if(req.getAttribute("idEquipment") != null) {
			//String equipment = this.gson.toJson(this.eDAO.get(request.getParameter("idEquipment")));
			/*
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        PrintWriter out = response.getWriter();

	        out.print(equipment);
	        out.flush();
			 */

			String json = new Gson().toJson(this.eDAO.get(req.getParameter("idEquipment")));
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write("test");
		//}

//		this.doProcess(req, resp);
	}

	private <N, O> ArrayList<N> castArrayList(ArrayList<O> list){
		ArrayList<N> newlyCastedArrayList = new ArrayList<N>();
		for(O listObject : list){
			newlyCastedArrayList.add((N)listObject);
		}
		return newlyCastedArrayList;
	}
}
