package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.sql.Date;
import java.time.LocalDate;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author e1802484
 */
public class TransactionDAO {

	final static String URL = "jdbc:mysql://localhost:3306/budget_managment?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	final static String LOGIN = "root";
	final static String PASS = "";
	
	private final int LAST_MONTH = 1, SIX_MONTHS = 2, LAST_YEAR = 3, TWO_YEARS = 4;

	public TransactionDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
			System.err.println(
					"Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}

	}

	public int insert(Transaction transaction) {
		Connection con = null;
		PreparedStatement ps = null;
		int retour = 0;

		// connexion ﾃ� la base de donnﾃｩes
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// prﾃｩparation de l'instruction SQL, chaque ? reprﾃｩsente une valeur ﾃ�
			// communiquer dans l'insertion
			// les getters permettent de rﾃｩcupﾃｩrer les valeurs des attributs souhaitﾃｩs de
			// nouvArticle
			ps = con.prepareStatement(
					"INSERT INTO transaction (ID, Montant, Categorie, Date_T, Personne) VALUES (0, ?, ?, ?,?)");
			ps.setDouble(1, transaction.getMontant());
			ps.setString(2, transaction.getCategorie());
			ps.setDate(3, transaction.getDate_T());
			ps.setInt(4, transaction.getPersonneID());

			// Exﾃｩcution de la requﾃｪte
			retour = ps.executeUpdate();

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
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

	public void Delete(int id) {
		Connection con = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		int retour = 0;

		// connexion ﾃ� la base de donnﾃｩes
		try {
			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);

			// verifier si la transaction exist
			ps2 = con.prepareStatement("SELECT * FROM transaction WHERE ID=?");
			ps2.setInt(1, id);
			// Exﾃｩcution de la requﾃｪte
			rs = ps2.executeQuery();
			if (rs.next()) {
				ps1 = con.prepareStatement("DELETE FROM transaction WHERE ID=?");
				ps1.setInt(1, id);
				// Exﾃｩcution de la requﾃｪte
				ps1.executeUpdate();

			}

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps1 != null) {
					ps1.close();
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

	public Transaction getTransaction(int id) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Transaction retour = null;

		// connexion ﾃ� la base de donnﾃｩes
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM transaction WHERE transaction.ID = ?");
			ps.setInt(1, id);

			// on exﾃｩcute la requﾃｪte
			// rs contient un pointeur situﾃｩ jusute avant la premiﾃｨre ligne retournﾃｩe
			rs = ps.executeQuery();
			// passe ﾃ� la premiﾃｨre (et unique) ligne retournﾃｩe
			if (rs.next()) {
				retour = new Transaction(rs.getInt("ID"), rs.getDouble("Montant"), rs.getString("Categorie"),
						rs.getDate("Date_T"), rs.getInt("Personne"));
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du ResultSet, du PreparedStatement et de la Connection
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

	public List<Transaction> getListeTransaction() {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Transaction> retour = new ArrayList<Transaction>();

		// connexion ﾃ� la base de donnﾃｩes
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM Transaction");

			// on exﾃｩcute la requﾃｪte
			rs = ps.executeQuery();
			// on parcourt les lignes du rﾃｩsultat
			while (rs.next()) {
				retour.add(new Transaction(rs.getInt("ID"), rs.getDouble("Montant"), rs.getString("Categorie"),
						rs.getDate("Date_T"), rs.getInt("Personne")));
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
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

	public void update(Transaction t) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Transaction retour = null;

		// connexion ﾃ� la base de donnﾃｩes
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE transaction set Montant=?, Categorie=? ,Date_T=?,Personne=? WHERE ID =?");
			ps.setDouble(1, t.getMontant());
			ps.setString(2, t.getCategorie());
			ps.setDate(3, t.getDate_T());
			ps.setInt(4, t.getPersonneID());
			ps.setInt(5, t.getID());
			// on exﾃｩcute la requﾃｪte
			// rs contient un pointeur situﾃｩ jusute avant la premiﾃｨre ligne retournﾃｩe

			ps.executeUpdate();
			// passe ﾃ� la premiﾃｨre (et unique) ligne retournﾃｩe

		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}
	//le budget global
		public double BudgetGlobal() {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			double retour = 0;
			try {

				con = DriverManager.getConnection(URL, LOGIN, PASS);
				ps = con.prepareStatement("Select (SELECT sum(Montant) as 'budget' from transaction WHERE Categorie ='Revenu')- (SELECT sum(Montant) as 'budget' from transaction WHERE Categorie ='Depense') as 'budget'");
				rs = ps.executeQuery();
				if (rs.next()) {
					retour = rs.getDouble("budget");
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

	// liste des annees dans la bd
	public List<String> getYears() {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> retour = new ArrayList<String>();

		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT distinct year(Date_T) as 'years' FROM `transaction` ");
			rs = ps.executeQuery();
			while (rs.next()) {
				retour.add(rs.getString("years"));
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du ResultSet, du PreparedStatement et de la Connection
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

	// Moyenne mensuelle des revenus
	public double AVG_Revenu_M(int annee) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		double retour = 0;

		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement(
					"select sum(Montant)/12 as 'moyenne' from transaction WHERE Categorie='Revenu' and year(Date_T)= ?");
			ps.setInt(1, annee);

			rs = ps.executeQuery();

			if (rs.next()) {
				retour = rs.getDouble("moyenne");
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

	// Moyenne mensuelle des depenses
	public double AVG_Depense_M(int annee) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		double retour = 0;

		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement(
					"select sum(Montant)/12 as 'moyenne' from transaction WHERE Categorie='Depense' and year(Date_T)= ?");
			ps.setInt(1, annee);

			rs = ps.executeQuery();

			if (rs.next()) {
				retour = rs.getDouble("moyenne");
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

	// Moyenne hebdomadaire des revenus
	public double AVG_Revenu_H(int annee) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		double retour = 0;

		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement(
					"select sum(Montant)/52 as 'moyenne' from transaction WHERE Categorie='Revenu' and year(Date_T)= ?");
			ps.setInt(1, annee);

			rs = ps.executeQuery();

			if (rs.next()) {
				retour = rs.getDouble("moyenne");
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

	// Moyenne hebdomadaires des depenses
	public double AVG_Depense_H(int annee) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		double retour = 0;

		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement(
					"select sum(Montant)/52 as 'moyenne' from transaction WHERE Categorie='Depense' and year(Date_T)= ?");
			ps.setInt(1, annee);

			rs = ps.executeQuery();

			if (rs.next()) {
				retour = rs.getDouble("moyenne");
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
	
	// Somme  annuelle des revenus
		public double SUM_R(int annee) {

			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			double retour = 0;

			try {

				con = DriverManager.getConnection(URL, LOGIN, PASS);
				ps = con.prepareStatement(
						"SELECT SUM(Montant) as 'somme' FROM `transaction` WHERE Categorie ='Revenu' and year(Date_T)=?");
				ps.setInt(1, annee);

				rs = ps.executeQuery();

				if (rs.next()) {
					retour = rs.getDouble("somme");
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

		// Somme  annuelle des depenses
		public double SUM_D(int annee) {

			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			double retour = 0;

			try {

				con = DriverManager.getConnection(URL, LOGIN, PASS);
				ps = con.prepareStatement(
						"SELECT SUM(Montant) as 'somme' FROM `transaction` WHERE Categorie ='Depense' and year(Date_T)=?");
				ps.setInt(1, annee);

				rs = ps.executeQuery();

				if (rs.next()) {
					retour = rs.getDouble("somme");
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

//les 3 personne ayant plus depense
	public Map<String, String> MAX_depense(int annee) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, String> retour = new HashMap<String, String>();

		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement(
					"SELECT personne.Nom ,SUM(Montant) as m FROM transaction INNER JOIN personne on personne.ID=transaction.personne WHERE Categorie='Depense' and year(Date_T)=? ORDER by m DESC LIMIT 3");
			ps.setInt(1, annee);
			rs = ps.executeQuery();
			while (rs.next()) {
				retour.put(rs.getString("Nom"), String.valueOf(rs.getDouble("m")));
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du ResultSet, du PreparedStatement et de la Connection
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

//statistques Revenu
	public TreeMap<String, String> Stats_R(int filtre) {
		final int LAST_MONTH = 1, SIX_MONTHS = 2, LAST_YEAR = 3, TWO_YEARS = 4;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TreeMap<String, String> retour = new TreeMap<String, String>();

		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			if (filtre == LAST_MONTH) {
				ps = con.prepareStatement(
						"SELECT SUM(Montant) as 'montant', Date_T as'day' FROM `transaction` WHERE Date_T >= DATE_SUB( CURRENT_DATE, INTERVAL 1 Month )and Categorie='Revenu' GROUP by  day(Date_T) ");
				rs = ps.executeQuery();
				while (rs.next()) {
					retour.put(String.valueOf(rs.getString("day")), String.valueOf(rs.getDouble("montant")));
				}

			} else if (filtre == SIX_MONTHS) {
				ps = con.prepareStatement("SELECT SUM(Montant) as 'montant', month(Date_T) as'month', year(Date_T) as'year' FROM `transaction` WHERE Date_T >= DATE_SUB( CURRENT_DATE, INTERVAL 6 Month )and Categorie='Revenu' GROUP by  month(Date_T)");
				rs = ps.executeQuery();
				while (rs.next()) {
					retour.put(String.valueOf(rs.getString("year") + "-" + rs.getString("month")), String.valueOf(rs.getDouble("montant")));
				}
			} else if (filtre == LAST_YEAR) {
				ps = con.prepareStatement("SELECT SUM(Montant) as 'montant', month(Date_T) as'month', year(Date_T) as'year' FROM `transaction` WHERE Date_T >= DATE_SUB( CURRENT_DATE, INTERVAL 1 YEAR ) and Categorie='Revenu' GROUP by  month(Date_T)");
				rs = ps.executeQuery();
				while (rs.next()) {
					retour.put(String.valueOf(rs.getString("year") + "-" + rs.getString("month")), String.valueOf(rs.getDouble("montant")));
				}
			}
				else
				{
					ps = con.prepareStatement("SELECT SUM(Montant) as 'montant',month(Date_T) as 'month' ,year(Date_T) as 'year' FROM `transaction` WHERE Date_T >= DATE_SUB( CURRENT_DATE, INTERVAL 2 YEAR ) and Categorie='Revenu' GROUP by month(Date_T)");
					rs = ps.executeQuery();
					while (rs.next()) {
						retour.put(String.valueOf(rs.getString("year") + "-" + rs.getString("month")), String.valueOf(rs.getDouble("montant")));
				}
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du ResultSet, du PreparedStatement et de la Connection
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

	//statistques Depense
		public TreeMap<String, String> Stats_D(int filtre) {
			
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			TreeMap<String, String> retour = new TreeMap<String, String>();

			try {

				con = DriverManager.getConnection(URL, LOGIN, PASS);
				if (filtre == LAST_MONTH) {
					ps = con.prepareStatement(
							"SELECT SUM(Montant) as 'montant', Date_T as'day' FROM `transaction` WHERE Date_T >= DATE_SUB( CURRENT_DATE, INTERVAL 1 Month )and Categorie='Depense' GROUP by  day(Date_T) ");
					rs = ps.executeQuery();
					while (rs.next()) {
						retour.put(String.valueOf(rs.getString("day")), String.valueOf(rs.getDouble("montant")));
					}

				} else if (filtre == SIX_MONTHS) {
					ps = con.prepareStatement("SELECT SUM(Montant) as 'montant', month(Date_T) as'month', year(Date_T) as'year' FROM `transaction` WHERE Date_T >= DATE_SUB( CURRENT_DATE, INTERVAL 6 Month )and Categorie='Depense' GROUP by  month(Date_T)");
					rs = ps.executeQuery();
					while (rs.next()) {
						retour.put(String.valueOf(rs.getString("year")+'-'+rs.getString("month")), String.valueOf(rs.getDouble("montant")));
					}
				} else if (filtre == LAST_YEAR) {
					ps = con.prepareStatement("SELECT SUM(Montant) as 'montant', month(Date_T) as'month', year(Date_T) as'year' FROM `transaction` WHERE Date_T >= DATE_SUB( CURRENT_DATE, INTERVAL 1 YEAR ) and Categorie='Depense' GROUP by  month(Date_T)");
					rs = ps.executeQuery();
					while (rs.next()) {
						retour.put(String.valueOf(rs.getString("year")+'-'+rs.getString("month")), String.valueOf(rs.getDouble("montant")));
					}
				}
					else
					{
						ps = con.prepareStatement("SELECT SUM(Montant) as 'montant',month(Date_T) as 'month' ,year(Date_T) as 'year' FROM `transaction` WHERE Date_T >= DATE_SUB( CURRENT_DATE, INTERVAL 2 YEAR ) and Categorie='Depense' GROUP by month(Date_T)");
						rs = ps.executeQuery();
						while (rs.next()) {
							retour.put(String.valueOf(rs.getString("year"))+'-'+String.valueOf(rs.getString("month")), String.valueOf(rs.getDouble("montant")));
					}
				}

			} catch (Exception ee) {
				ee.printStackTrace();
			} finally {
				// fermeture du ResultSet, du PreparedStatement et de la Connection
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
	public void importTransaction(String fileName) throws IOException {

		String excelFilePath = fileName;

		int batchSize = 20;

		Connection connection = null;

		try {
			long start = System.currentTimeMillis();

			FileInputStream inputStream = new FileInputStream(excelFilePath);

			Workbook workbook = new XSSFWorkbook(inputStream);

			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = firstSheet.iterator();

			connection = DriverManager.getConnection(URL, LOGIN, PASS);
			connection.setAutoCommit(false);

			String sql = "INSERT INTO Transaction (Montant, Categorie,Date_T,Personne) VALUES (?, ?, ?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			int count = 0;

			rowIterator.next(); // skip the header row

			while (rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();

				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();

					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 0:
						double Montant = Double.valueOf(nextCell.getNumericCellValue());
						statement.setDouble(1, Montant);
						break;
					case 1:
						String Categorie = nextCell.getStringCellValue();
						statement.setString(2, Categorie);
						break;
					case 2:
						Date Date_T = Date.valueOf(nextCell.getLocalDateTimeCellValue().toLocalDate());
						statement.setDate(3, Date_T);
						break;
					case 3:
						int Personne = (int) nextCell.getNumericCellValue();
						statement.setInt(4, Personne);
						break;
					}

				}

				statement.addBatch();

				if (count % batchSize == 0) {
					statement.executeBatch();
				}

			}

			workbook.close();

			// execute the remaining queries
			statement.executeBatch();

			connection.commit();
			connection.close();

			long end = System.currentTimeMillis();
			System.out.printf("Import done in %d ms\n", (end - start));

		} catch (IOException ex1) {
			System.out.println("Error reading file");
			ex1.printStackTrace();
		} catch (SQLException ex2) {
			System.out.println("Database error");
			ex2.printStackTrace();
		}
	}
}
