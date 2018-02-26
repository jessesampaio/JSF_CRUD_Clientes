package br.com.enterprise.Bean;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.enterprise.DAO.ClienteDAO;
import br.com.enterprise.VO.ClienteVO;


@ManagedBean(name="cliBean")
public class ClienteBean extends ClienteVO{
	
	private ClienteVO clienteVO = new ClienteVO();
	private DataModel<ClienteVO> clientes;
	
	
	
	public String addUser(){
		String retorno = "erro";
		
		try {
			ClienteDAO dao = new ClienteDAO();
			if(dao.insert(clienteVO)){
				retorno = "sucesso";
			}							
		} catch (Exception ex) {

		}
		
		return retorno;
	}
	
	//Getters and Setters
	public ClienteVO getClienteVO() {
		return clienteVO;
	}
	public void setClienteVO(ClienteVO clienteVO) {
		this.clienteVO = clienteVO;
	}
	public DataModel<ClienteVO> getClientes() {
		
		ClienteDAO dao = new ClienteDAO();
		
		try {
			List<ClienteVO> lista = dao.getAll();
			clientes = new ListDataModel<ClienteVO>(lista);
		} catch (Exception e) {
			
		}
		
		return clientes;
	}
	public void setClientes(DataModel<ClienteVO> clientes) {
		this.clientes = clientes;
	}

}