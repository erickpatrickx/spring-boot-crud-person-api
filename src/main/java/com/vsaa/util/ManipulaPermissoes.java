package com.vsaa.util;

import java.util.ArrayList;
import java.util.List;

import com.vsaa.modelo.Role;


public class ManipulaPermissoes {
	public List<Role> checaPapelContato(String papel){
		List<Role> papeis = new ArrayList<>();
		Role papelAdmin = new Role(); 
		Role papelUser = new Role();
		
		papelAdmin.setId(1);
		papelAdmin.setNome("ROLE_ADMIN");
		papelUser.setId(2);
		papelUser.setNome("ROLE_USER");
		
		switch (papel) {
		case "admin":
			papeis.add(papelAdmin);
			break;
		case "contato":
			papeis.add(papelUser);
			break;
		default:
			break;
		}
				
		return papeis;
	}
}
