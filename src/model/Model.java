package model;

public class Model {

	private static TransactionDAO transactionDAO;
	private static PersonneDAO personneDAO;
	private static UserDAO userDAO;
	
	public static TransactionDAO getTransactionInstance() {
		
		if(Model.transactionDAO == null) {
			
			Model.transactionDAO = new TransactionDAO();
		}
		
		return Model.transactionDAO;
	}
	
	public static PersonneDAO getPersonneInstance() {
		
		if(Model.personneDAO == null) {
			
			Model.personneDAO = new PersonneDAO();
		}
		
		return Model.personneDAO;
	}
	
public static UserDAO getUserInstance() {
		
		if(Model.userDAO == null) {
			
			Model.userDAO = new UserDAO();
		}
		
		return Model.userDAO;
	}
}
