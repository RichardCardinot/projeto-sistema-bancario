package br.com.residencia.poo.sistema_bancario_principal.menu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import br.com.residencia.poo.sistema_bancario_principal.contas.Conta;
import br.com.residencia.poo.sistema_bancario_principal.contas.ContaCorrente;
import br.com.residencia.poo.sistema_bancario_principal.contas.ContaPoupanca;
import br.com.residencia.poo.sistema_bancario_principal.pessoas.Cliente;
import br.com.residencia.poo.sistema_bancario_principal.pessoas.Diretor;
import br.com.residencia.poo.sistema_bancario_principal.pessoas.Gerente;
import br.com.residencia.poo.sistema_bancario_principal.pessoas.Pessoa;
import br.com.residencia.poo.sistema_bancario_principal.pessoas.Presidente;

public class LeitorDeDados {

	public static ArrayList<Pessoa> leitorUsuarios() throws IOException {
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

		BufferedReader buffRead = new BufferedReader(new FileReader("./temp/CargaInicialUsuarios.txt"));
		String linha = "";

		try {
			while (true) {
				linha = buffRead.readLine();
				if (linha != null) {
					String[] atributos = linha.split(";");

					switch (atributos[0]) {
					case "CLIENTE": {
						//String tipoPessoa, String cpf, String senha, String nome
						Pessoa cliente = new Cliente(atributos[0], atributos[1], atributos[2], atributos[3]);
						pessoas.add(cliente);
						break;
					}
					case "GERENTE": {
						//String tipoPessoa, String cpf, String senha, String nome, int agenciaGerenciad
						Pessoa gerente = new Gerente(atributos[0], atributos[1], atributos[2], atributos[3],
								Integer.valueOf(atributos[4]).intValue());
						pessoas.add(gerente);
						break;
					}
						//String tipoPessoa, String cpf, String senha, String nome
					case "DIRETOR": {
						Pessoa diretor = new Diretor(atributos[0], atributos[1], atributos[2], atributos[3]);
						pessoas.add(diretor);
						break;
					}
					case "PRESIDENTE": {
						//String tipoPessoa, String cpf, String senha, String nome
						Pessoa presidente = new Presidente(atributos[0], atributos[1], atributos[2], atributos[3]);
						pessoas.add(presidente);
						break;
					}

					}
				} else {
					break;
				}

			}

		} catch (Exception e) {
			//System.out.println("Quantidade de parâmetros inválidos no arquivo.");
		}

		buffRead.close();
		return pessoas;

	}

	public static ArrayList<Conta> leitorContas() throws IOException {
		ArrayList<Conta> contas = new ArrayList<Conta>();

		BufferedReader buffRead = new BufferedReader(new FileReader("./temp/CargaInicialContas.txt"));
		String linha = "";

		try {
			while (true) {
				linha = buffRead.readLine();
				if (linha != null) {
					String[] atributos = linha.split(";");

					if (atributos[3].equals("CORRENTE")) {
						Conta conta = new ContaCorrente(atributos[0], Double.valueOf(atributos[1]).doubleValue(),
								Integer.valueOf(atributos[2]).intValue(), atributos[3],
								Integer.valueOf(atributos[4]).intValue());
						contas.add(conta);
					} else if (atributos[3].equals("POUPANCA")) {
						Conta conta = new ContaPoupanca(atributos[0], Double.valueOf(atributos[1]).doubleValue(),
								Integer.valueOf(atributos[2]).intValue(), atributos[3],
								Integer.valueOf(atributos[4]).intValue());
						contas.add(conta);
					}

				} else {
					break;
				}
			}

		} catch (Exception e) {
			System.out.println("Quantidade de parâmetros inválidos no arquivo.");
		}

		buffRead.close();
		return contas;

	}
	
	public static Conta buscaContaPorCpf(String cpf) {
		
		try {
			for(int i = 0; i < leitorContas().size(); i++) {
				if(leitorContas().get(i).getCpfTitular().equals(cpf)) {
					return leitorContas().get(i);
				};	
			}
		} catch (Exception e) {
			System.out.println("Formato inválido!");
		}
		return null;

		
	}
}