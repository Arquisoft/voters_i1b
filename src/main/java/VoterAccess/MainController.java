package VoterAccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mysql.jdbc.PreparedStatement;

import DBManagement.VoterService;
import model.Voter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class MainController {

	//@Autowired
	private VoterService vService;
	// @RequestMapping(value = "/user", method = RequestMethod.POST, produces =
	// { MediaType.APPLICATION_JSON_VALUE})
	// connect to db and try to find user
	private static String hostname = "jdbc:mysql://digital-skill.com:3306/andrei_voters";
	private static String user = "andrei_vot";
	private static String pass = "votersgonnavote";

	private static Connection con;

	public static void connToDb() {
		try {
			con = DriverManager.getConnection(hostname, user, pass);
			System.out.println("Connected");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public ResponseEntity<UserInfo> getVoterInfo(@RequestBody UserInfo userInfo) {
		connToDb();
		try {
			java.sql.PreparedStatement checkUser = con
					.prepareStatement("SELECT * FROM Voters WHERE email = ? AND password =  ?");
			checkUser.setString(1, userInfo.getEmail());
			checkUser.setString(2, userInfo.getPassword());
			ResultSet rs = checkUser.executeQuery();
			Voter dbuser = new Voter(null, null, null, null, null);
			while (rs.next()) {
				dbuser.setName(rs.getString("Name"));
				dbuser.setEmail(rs.getString("Email"));
				dbuser.setPassword(rs.getString("Password"));
				dbuser.setPollingStationCode(rs.getString("Station"));
				dbuser.setNIF(rs.getString("NIF"));
			}
			if (userInfo.getPassword().equals(dbuser.getPassword()) && userInfo.getEmail().equals(dbuser.getEmail())) {
				return new ResponseEntity<UserInfo>(userInfo, HttpStatus.OK);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// If the user on the input is the same as the one in the db and
		// credentials are ok
		// return user info and HTTP response
		return new ResponseEntity<UserInfo>(HttpStatus.NOT_FOUND);
		// return
		// this.vService.findByEmailAndPassword(userInfo.getEmail(),userInfo.getPassword());
	}

	@RequestMapping("/")
	public String landing() {
		return "User Management Service";
	}

	public static Voter findByEmail(String email, String password) {
		Voter voter = null;
		connToDb();
		try {
			java.sql.PreparedStatement checkUser = con
					.prepareStatement("SELECT * FROM Voters WHERE email = ? AND password =  ?");
			checkUser.setString(1, email);
			checkUser.setString(2, password);
			ResultSet rs = checkUser.executeQuery();
			while(rs.next()) {
				voter = new Voter(rs.getString("Name"), rs.getString("Email"), rs.getString("Password"), rs.getString("Station"), rs.getString("NIF"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return voter;
	}

	public static Voter updatePassword(String email, String password) {
		Voter voter = null;
		connToDb();
		try {
			java.sql.PreparedStatement checkUser = con
					.prepareStatement("UPDATE Voters SET password = ? WHERE email = ?");
			checkUser.setString(1, email);
			checkUser.setString(1, password);
			ResultSet rs = checkUser.executeQuery();
			while(rs.next()) {
				voter = new Voter(rs.getString("Name"), rs.getString("Email"), rs.getString("Password"), rs.getString("Station"), rs.getString("NIF"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return voter;
	}
}