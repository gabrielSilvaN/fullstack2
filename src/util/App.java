package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.ufg.inf.fs.ctrl.HospedeCtrl;
import br.ufg.inf.fs.ctrl.HotelCtrl;
import br.ufg.inf.fs.ctrl.QuartoCtrl;
import br.ufg.inf.fs.entities.Hotel;
import br.ufg.inf.fs.entities.Hospede;
import br.ufg.inf.fs.entities.Quarto;
import br.ufg.inf.fs.enums.CategoriaQuarto;

public class App {

	public static void testeSystem() {

		System.out.println("Hello World");
		System.err.println("Hello World com erro");

	}

	public static void main(String[] args) {

		// testeCrudQuarto();
		// testeConexao();
		try {
			testeCrudHospede();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	public static void testeConexao() {
		try {
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/db_hotel", "root",
					"docker");
			System.out.println("Conexão funcionou");
		} catch (SQLException e) {
			System.err.println("Conexão não funcionou");
			System.out.println(e.getMessage());
		}
	}

	public static void testeCrudHospede() throws ParseException {
		HospedeCtrl ctrl = new HospedeCtrl();

		System.out.println("Lista de Hóspedes Cadastrados");
		for(Hospede h: ctrl.findAll()) {
			System.out.println(h);
		}

		System.out.println("Buscar pelo #ID 1");
		Hospede hospede = ctrl.findById(1);
		System.err.println(hospede);

		System.out.println("Cadastar novo hóspede");
		

		Date date = new SimpleDateFormat("yyyy-MM-dd").parse("1977-11-25");
		
		java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 

		Hospede hospede1 = new Hospede(null, "Lucas Silva", sqlDate, 123456);
		Hospede h1 = ctrl.insert(hospede1);
		System.out.println(h1);

	}

	public static void testeCrudHotel() {

		HotelCtrl ctrl = new HotelCtrl();

		System.out.println("Lista de Hoteis Cadastrados");
		for (Hotel h : ctrl.findAll()) {
			System.out.println(h);
		}

		System.out.println("Buscar pelo #ID 1");
		Hotel hotel = ctrl.findById(1);
		System.out.println(hotel);

		System.out.println("Cadastrar novo Hotel");
		Hotel h1 = new Hotel(null, "Hotel Goiânia", "Goiânia", 3);
		// h1 = ctrl.insert(h1);
		System.out.println(h1);

		Hotel h2 = ctrl.findById(3);
		System.out.println("UPDATE");
		System.out.println("#ID original: " + h2);
		h2.setNmHotel(h2.getNmHotel() + " ALTERADO");
		h2 = ctrl.update(h2);
		System.out.println("#ID alterado: " + h2);

		System.out.println("Lista de Hoteis Cadastrados");
		for (Hotel h : ctrl.findAll()) {
			System.out.println(h);
		}
		System.out.println("Deletar #ID 4");
		ctrl.delete(4);

		System.out.println("Lista de Hoteis Atualizado");
		for (Hotel h : ctrl.findAll()) {
			System.out.println(h);
		}

	}

	public static void testeCrudQuarto() {

		QuartoCtrl ctrl = new QuartoCtrl();

		HotelCtrl hotelCtrl = new HotelCtrl();

		System.out.println("Lista de Quartos Cadastrados");
		for (Quarto q : ctrl.findAll()) {
			System.out.println(q);
		}

		System.out.println("Buscar pelo #ID 1");
		Quarto quarto = ctrl.findById(1);
		System.out.println(quarto);

		System.out.println("Cadastrar novo Hotel");

		Quarto q1 = new Quarto(null, hotelCtrl.findById(1), CategoriaQuarto.APARTAMENTO, 3, 120, 240.0);
		// q1 = ctrl.insert(q1);
		System.out.println(q1);

		Quarto q2 = ctrl.findById(3);
		System.out.println("UPDATE");
		System.out.println("#ID original: " + q2);
		q2.setPrDiaria(q2.getPrDiaria() + 20);
		q2 = ctrl.update(q2);
		System.out.println("#ID alterado: " + q2);

		System.out.println("Lista de Quartos Cadastrados");
		for (Quarto h : ctrl.findAll()) {
			System.out.println(h);
		}
		System.out.println("Deletar #ID 3");
		ctrl.delete(3);

		System.out.println("Lista de Quartos Atualizado");
		for (Quarto q : ctrl.findAll()) {
			System.out.println(q);
		}

	}

}
