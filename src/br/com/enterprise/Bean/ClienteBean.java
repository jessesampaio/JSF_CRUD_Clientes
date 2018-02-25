package br.com.enterprise.Bean;

import javax.faces.bean.ManagedBean;

import br.com.enterprise.DAO.ClienteDAO;
import br.com.enterprise.VO.ClienteVO;


@ManagedBean(name="cliBean")
public class ClienteBean extends ClienteVO{
	
	public String addUser(){
		String retorno = "erro";
		
		try {
			ClienteDAO dao = new ClienteDAO();
			if(dao.insert(this)){
				retorno = "sucesso";
			}							
		} catch (Exception ex) {

		}
		
		return retorno;
	}	 

}