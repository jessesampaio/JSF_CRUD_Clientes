package br.com.enterprise.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.enterprise.VO.ClienteVO;

//Todos os métodos que servirão para fazer a persistência no Banco de Dados.
public class ClienteDAO {
	
	private Connection getConn() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		
		return DriverManager.getConnection("jdbc:mysql://localhost/jsf_crud", "root", "");
	}
	
	
	public boolean insert(ClienteVO vo) throws ClassNotFoundException, SQLException{
		String sql = "INSERT INTO clientes (nome, email, idade)";
		sql += " VALUES (?, ?, ?)";
		
		PreparedStatement pstm = getConn().prepareStatement(sql);
		pstm.setString(1,vo.getNome());
		pstm.setString(2,vo.getEmail());
		pstm.setInt(3,vo.getIdade());
		
		if (pstm.executeUpdate() > 0){
			return true;
		}
		else {
			return false;
		}			
	}
	
	public ClienteVO getById(int ID) throws ClassNotFoundException, SQLException{
		String sql = "SELECT * FROM clientes WHERE id = ? ";
		
		PreparedStatement pstm = getConn().prepareStatement(sql);
		pstm.setInt(1,ID);
		
		ResultSet rs = pstm.executeQuery();
		
		ClienteVO vo = new ClienteVO();
		
		if(rs.first()){
			vo.setId(rs.getInt("id"));
			vo.setNome(rs.getString("nome"));
			vo.setEmail(rs.getString("email"));
			vo.setIdade(rs.getInt("idade"));
		}
		
		return vo;		
	}
	
	public List<ClienteVO> getAll() throws ClassNotFoundException, SQLException{
		String sql = "SELECT * FROM clientes";		
		PreparedStatement pstm = getConn().prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<ClienteVO> lista = new ArrayList<ClienteVO>();
		
		while(rs.next()){
			ClienteVO vo = new ClienteVO();
			vo.setId(rs.getInt("id"));
			vo.setNome(rs.getString("nome"));
			vo.setEmail(rs.getString("email"));
			vo.setIdade(rs.getInt("idade"));
			lista.add(vo);
		}		
		return lista;		
	}
	
	public boolean update(ClienteVO vo) throws ClassNotFoundException, SQLException{
		String sql = "UPDATE clientes SET nome = ?, email = ?, idade = ? )";
		sql += " WHERE id = ? ";
		
		PreparedStatement pstm = getConn().prepareStatement(sql);
		pstm.setString(1,vo.getNome());
		pstm.setString(2,vo.getEmail());
		pstm.setInt(3,vo.getIdade());
		pstm.setInt(4,vo.getId());
		
		if (pstm.executeUpdate() > 0){
			return true;
		}
		else {
			return false;
		}			
	}
	
	public boolean delete(ClienteVO vo) throws ClassNotFoundException, SQLException{
		String sql = "DELETE FROM clientes WHERE id=?";
		
		PreparedStatement pstm = getConn().prepareStatement(sql);
		pstm.setInt(1,vo.getId());
		
		if (pstm.executeUpdate() > 0){
			return true;
		}
		else {
			return false;
		}			
	}
	
}