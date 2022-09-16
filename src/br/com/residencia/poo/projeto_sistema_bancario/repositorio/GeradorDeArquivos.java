package br.com.residencia.poo.projeto_sistema_bancario.repositorio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.com.residencia.poo.projeto_sistema_bancario.movimentacao.Movimentacao;

public class GeradorDeArquivos {

	public static void escreverArquivo(Movimentacao movimentacao, String nomeArquivo) throws IOException {
		FileWriter arquivo = new FileWriter(new File("./temp/" + nomeArquivo + ".txt"), true);
		arquivo.write(movimentacao + "\n");
		arquivo.close();
	}
}
