package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersonneDAO {


	final static String URL = "jdbc:mysql://localhost:3306/budget_managment?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	final static String LOGIN = "root";
	final static String PASS = "";
	
	public PersonneDAO() {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e2) {
		System.err.println(	"Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
	}
	}
	
	public int insertPersone(Personne peronne) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;

				try {

					con = DriverManager.getConnection(URL, LOGIN, PASS);
					ps = con.prepareStatement("INSERT INTO `personne` (`ID`, `Nom`) VALUES (0,?)");
					ps.setString(1, peronne.getNom());
					retour = ps.executeUpdate();

				} catch (Exception ee) {
					ee.printStackTrace();
				} finally {
				
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
				return retour;
	
	}
	
	public Personne getPersonByName(String nom) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Personne retour = null;
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM personne WHERE Nom=?");
			ps.setString(1, nom);
			rs = ps.executeQuery();
			if (rs.next()) {
				retour = new Personne(rs.getInt("ID"), rs.getString("Nom"));
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
		return retour;
	}
	public Personne getPersonById(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Personne retour = null;
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM personne WHERE ID=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				retour = new Personne(rs.getInt("ID"), rs.getString("Nom"));
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
		return retour;
	}
}
