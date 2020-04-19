package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLSyntaxErrorException;

public class UserDAO {

	final static String URL = "jdbc:mysql://localhost:3306/budget_managment?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	final static String LOGIN = "root";
	final static String PASS = "";
	
	
	
	public UserDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
			System.err.println(	"Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}
	}
	
	public boolean verifyUser() throws SQLSyntaxErrorException, Exception {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean exist=false;
		
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM utilisateur");
			rs = ps.executeQuery();
			if (rs.next()) {
				exist=true;
			}

		} catch (SQLSyntaxErrorException ssee) {
			throw ssee;
		} catch (Exception ee) {
			throw ee;
		} finally {
			
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception t) {
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception t) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception t) {
			}
		}
		
		
		
		
		return exist;
	}
	
public void addUser(String mdp) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean exist=false;
		int retour = 0;
		try {
				con = DriverManager.getConnection(URL, LOGIN, PASS);
				ps = con.prepareStatement(
								"INSERT INTO utilisateur (Passeword) VALUES (?)");
				ps.setString(1,mdp);

				retour = ps.executeUpdate();

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception t) {
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception t) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception t) {
			}
		}
		
		
		
	}
	
public boolean verifyPass(String mdp) {
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	boolean exist=false;
	
	try {

		con = DriverManager.getConnection(URL, LOGIN, PASS);
		ps = con.prepareStatement("SELECT * FROM utilisateur WHERE Passeword = ?");
		ps.setString(1,mdp);
		rs = ps.executeQuery();
		if (rs.next()) {
			exist=true;
		}

	} catch (Exception ee) {
		ee.printStackTrace();
	} finally {
		
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception t) {
		}
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (Exception t) {
		}
		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception t) {
		}
	}
	
	
	
	
	return exist;
}
	
	
	
}
