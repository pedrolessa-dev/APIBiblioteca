package br.com.empresateste.biblioteca.service;

import java.util.ArrayList;

import br.com.empresateste.biblioteca.model.Livro;

public interface ILivroService {
	public ArrayList<Livro> exibirTodos();
	public Livro exibirPeloCodigo(int codigo);
	public ArrayList<Livro> exibirPeloNome(String palavraChave);
	public Livro cadastrarLivro(Livro l);
	public Livro alterarLivro(Livro l);
	public boolean excluirLivro(int codigo);
}
