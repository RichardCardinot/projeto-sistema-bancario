package br.com.residencia.poo.sistema_bancario_principal.menu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import br.com.residencia.poo.sistema_bancario_principal.pessoas.Cliente;
import br.com.residencia.poo.sistema_bancario_principal.pessoas.Funcionario;
import br.com.residencia.poo.sistema_bancario_principal.pessoas.Pessoa;

public class LeitorDeUsuarios {

	static final String PATH = "./temp/CargaInicial.txt";

	public static ArrayList<Pessoa> leitor() throws IOException {
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

		BufferedReader buffRead = new BufferedReader(new FileReader(PATH));
		String linha = "";

		while (true) {
			linha = buffRead.readLine();
			if (linha != null) {
				String[] atributos = linha.split(";");

				// se o tipoPessoa == 1, então é cliente
				if (atributos[0].equals("1")) {
					Pessoa cliente = new Cliente(atributos[0], atributos[1], atributos[2]);
					pessoas.add(cliente);
				} else { // senão é funcionário
					Pessoa funcionario = new Funcionario(atributos[0], atributos[1], atributos[2]);
					pessoas.add(funcionario);
				}

			} else {
				break;
			}
		}

		buffRead.close();
		return pessoas;

	}
}
