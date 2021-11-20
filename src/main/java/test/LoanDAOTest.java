package test;

import org.junit.Test;

import model.dao.EquipmentDAO;
import model.dao.LoanDAO;
import model.dao.UserDAO;
import model.object.equipment.Equipment;
import model.object.loan.Loan;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import model.object.user.User;

public class LoanDAOTest {

	@Test
	public void getTest() {
		int equipmentId = 1;
		String userMail = "root@gmail.com";
		String beginningDateString = "2021-11-12";
		LocalDate beginningDate = LocalDate.of(2021, 11, 12);
		String endDate = "2021-11-30";
		boolean isBorrowed = false;

		LoanDAO loanDAO = new LoanDAO();

		Object[] id = { equipmentId, userMail, beginningDate };

		Loan loan = loanDAO.get(id);

		assertEquals(loan != null, true);
		assertEquals(loan.getEquipment().getId(), equipmentId);
		assertEquals(loan.getUser().getMail(), userMail);
		assertEquals(loan.getStringBeginningDate(), beginningDateString);
		assertEquals(loan.getStringEndDate(), endDate);
		assertEquals(loan.isBorrowed(), isBorrowed);

	}

	@Test
	public void addTest() {
		int id = 0;
		int equipmentId = 1;
		String userMail = "root@gmail.com";
		LocalDate beginningDate = LocalDate.of(2022, 11, 12);
		String beginningDateString = "2022-11-12";
		LocalDate endDate = LocalDate.of(2027, 11, 30);
		String endDateString = "2027-11-30";
		boolean isBorrowed = false;

		LoanDAO loanDAO = new LoanDAO();

		if (loanDAO.get(id) != null) {
			loanDAO.deleteById(id);
		}

		EquipmentDAO equipmentDAO = new EquipmentDAO();
		Equipment equipment = equipmentDAO.get(equipmentId);

		UserDAO userDAO = new UserDAO();
		User user = userDAO.get(userMail);

		Loan loan = new Loan(id, equipment, user, beginningDate, endDate, isBorrowed);

		loanDAO.add(loan);

		loan = loanDAO.get(id);

		assertEquals(loan != null, true);
		assertEquals(loan.getEquipment().getId(), equipmentId);
		assertEquals(loan.getUser().getMail(), userMail);
		assertEquals(loan.getStringBeginningDate(), beginningDateString);
		assertEquals(loan.getStringEndDate(), endDateString);
		assertEquals(loan.isBorrowed(), isBorrowed);

		loanDAO.deleteById(id);
	}

	@Test
	public void deleteTest() {
		int id = 0;
		int equipmentId = 1;
		String userMail = "root@gmail.com";
		LocalDate beginningDate = LocalDate.of(2022, 11, 12);
		LocalDate endDate = LocalDate.of(2027, 11, 30);
		boolean isBorrowed = false;

		LoanDAO loanDAO = new LoanDAO();

		EquipmentDAO equipmentDAO = new EquipmentDAO();
		Equipment equipment = equipmentDAO.get(equipmentId);

		UserDAO userDAO = new UserDAO();
		User user = userDAO.get(userMail);

		Loan loan = new Loan(id, equipment, user, beginningDate, endDate, isBorrowed);

		if (loanDAO.get(id) == null) {

			loanDAO.add(loan);
		}

		loanDAO.delete(loan);

		loan = loanDAO.get(id);

		assertEquals(loan == null, true);
	}

	@Test
	public void deleteByIdTest() {
		int id = 0;
		int equipmentId = 1;
		String userMail = "root@gmail.com";
		LocalDate beginningDate = LocalDate.of(2022, 11, 12);
		LocalDate endDate = LocalDate.of(2027, 11, 30);
		boolean isBorrowed = false;

		LoanDAO loanDAO = new LoanDAO();

		EquipmentDAO equipmentDAO = new EquipmentDAO();
		Equipment equipment = equipmentDAO.get(equipmentId);

		UserDAO userDAO = new UserDAO();
		User user = userDAO.get(userMail);

		Loan loan = new Loan(id, equipment, user, beginningDate, endDate, isBorrowed);

		if (loanDAO.get(id) == null) {

			loanDAO.add(loan);
		}

		loanDAO.deleteById(id);

		loan = loanDAO.get(id);

		assertEquals(loan == null, true);
	}

	@Test
	public void listAllTest() {
		LoanDAO loanDAO = new LoanDAO();
		ArrayList<Loan> list = loanDAO.listAll();
		assertEquals(list.size(), 1);
	}

	@Test
	public void update() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		String newEndDateString = "2030-11-30";
		boolean newIsBorrowed = true;
		params.put("endDate", newEndDateString);
		params.put("isBorrowed", newIsBorrowed);

		int id = 0;
		int equipmentId = 1;
		String userMail = "root@gmail.com";
		LocalDate beginningDate = LocalDate.of(2022, 11, 12);
		String beginningDateString = "2022-11-12";
		LocalDate endDate = LocalDate.of(2027, 11, 30);
		boolean isBorrowed = false;

		LoanDAO loanDAO = new LoanDAO();

		if (loanDAO.get(id) != null) {
			loanDAO.deleteById(id);
		}

		EquipmentDAO equipmentDAO = new EquipmentDAO();
		Equipment equipment = equipmentDAO.get(equipmentId);

		UserDAO userDAO = new UserDAO();
		User user = userDAO.get(userMail);

		Loan loan = new Loan(id, equipment, user, beginningDate, endDate, isBorrowed);

		loanDAO.add(loan);

		loanDAO.update(loan, params);

		loan = loanDAO.get(id);

		assertEquals(loan != null, true);
		assertEquals(loan.getEquipment().getId(), equipmentId);
		assertEquals(loan.getUser().getMail(), userMail);
		assertEquals(loan.getStringBeginningDate(), beginningDateString);
		assertEquals(loan.getStringEndDate(), newEndDateString);
		assertEquals(loan.isBorrowed(), newIsBorrowed);

		loanDAO.deleteById(id);

	}

}