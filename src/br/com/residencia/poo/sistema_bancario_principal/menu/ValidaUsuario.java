package br.com.residencia.poo.sistema_bancario_principal.menu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import br.com.residencia.poo.sistema_bancario_principal.pessoas.Pessoa;

public class ValidaUsuario {
	static ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

	public static Pessoa validarUsuario(String usuario, String senha) {
		
		try {
			pessoas = LeitorDeDados.leitorUsuarios();
		} catch (FileNotFoundException e) {
			System.out.println("O sistema não pode encontrar o arquivo especificado no caminho.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < pessoas.size(); i++) {
			if (pessoas.get(i).getCpf().equals(usuario) && pessoas.get(i).getSenha().equals(senha)) {
				Pessoa pessoaLocalizada = pessoas.get(i);
				return pessoaLocalizada;
			}
		}
		
		return null;

	}
}
