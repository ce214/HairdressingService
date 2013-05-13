package demo.hairdressstudio.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import demo.hairdressstudio.Reservation;

public class MySQLAccess {

	private Connection conn = null;
	private String url = "jdbc:mysql://localhost:3306/";
	private String dbName = "hairdress";
	private String driver = "com.mysql.jdbc.Driver";
	private String userName = "root";
	private String password = "root";

	private PreparedStatement pst = null;

	private ResultSet rs = null;

	public void addReservation(Reservation res) {
		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			conn = DriverManager
					.getConnection(url + dbName, userName, password);

			Time time = new Time(res.getInitialHour().getTime().getTime());
			java.sql.Date date = new java.sql.Date(res.getInitialHour()
					.getTime().getTime());

			pst = (PreparedStatement) conn
					.prepareStatement("insert into  reservations values (default, ?, ?, ?, ?, ?, ?)");
			pst.setString(1, res.getName());
			pst.setString(2, res.getPhoneNumber());
			pst.setDate(3, date);
			pst.setTime(4, time, res.getInitialHour());
			pst.setLong(5, res.getService().getDuration());
			pst.setDouble(6, res.getService().getPrice());
			pst.executeUpdate();

			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Reservation> getListFromDataBase() {
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		Reservation res = new Reservation();

		try {
			conn = DriverManager
					.getConnection(url + dbName, userName, password);

			Statement stmt = (Statement) conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM reservations");
			while (rs.next()) {
				res.setName(rs.getString(2));
				res.setPhoneNumber(rs.getString(3));
				res.setInitialHour(DatabeseLogic.getCalendarDate(rs.getDate(4),
						rs.getTime(5)));
				res.setService(DatabeseLogic.getService(rs.getLong(6)));

				reservations.add(res);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return reservations;
	}

	/*public void clearTable() {
		try {
			conn = DriverManager
					.getConnection(url + dbName, userName, password);

			Statement stmt = (Statement) conn.createStatement();
			stmt.executeUpdate("TRUNCATE TABLE reservations");

			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	public boolean delReservationFromDataBase(Reservation res) {
		try {
			conn = DriverManager
					.getConnection(url + dbName, userName, password);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
			String date = sdf.format(res.getInitialHour().getTime());
			sdf = new SimpleDateFormat("HH:mm:ss");
			String time = sdf.format(res.getInitialHour().getTime());

			pst = (PreparedStatement) conn.prepareStatement("DELETE FROM reservations WHERE date='"
					+ date + "' AND time='" + time + "'");
			pst.executeUpdate();
			
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
