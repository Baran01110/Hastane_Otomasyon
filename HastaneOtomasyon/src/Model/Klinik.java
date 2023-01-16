package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Klinik {
	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	Connection con = conn.conDb();
	PreparedStatement preparedStatement = null;
	
	private int id;
	private String name;
	
	public Klinik() {}
	
	public Klinik(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public ArrayList<Klinik> getList() throws SQLException{
		ArrayList<Klinik> list = new ArrayList<>();
		
		Klinik obj;
		try {
		st = con.createStatement();
		rs = st.executeQuery("SELECT * FROM klinik");
		while(rs.next()) {
			obj = new Klinik();
			obj.setId(rs.getInt("id"));
			obj.setName(rs.getString("name"));
			list.add(obj);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {} 
		
		
		return list;
		}		
	
	public Klinik getFetch(int id) {
		Klinik c = new Klinik();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM klinik WHERE id =" + id);
			while(rs.next()) {
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	
	public boolean addKlinik(String name) throws SQLException {	
		
		String query = "INSERT INTO klinik" +"(name) VALUES"+ "(?)"; 
		boolean key = false;
		
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name); 
			
			preparedStatement.executeUpdate();
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(key)
		return true;
		else
			return false;
	}
	
	public boolean deleteKlinik(int id) throws SQLException {	
		
		String query = "DELETE FROM klinik WHERE id=?"; 
		boolean key = false;
		
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id); 
			preparedStatement.executeUpdate();
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(key)
		return true;
		else
			return false;
	}

	public boolean updateKlinik(String name, int id) throws SQLException {
		
		String query = "UPDATE klinik SET name=? WHERE id=?"; 
		boolean key = false;
		
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name); 	 
			preparedStatement.setInt(2, id); 
			 
			preparedStatement.executeUpdate();
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(key)
		return true;
		else
			return false;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
			

}
