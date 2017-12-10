package br.edu.ufabc.MAPA.Service;

public interface SecurityService{
	String findLoggedInUsername();
	void autologin(String username, String password);
}

