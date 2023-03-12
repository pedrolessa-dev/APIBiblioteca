package br.com.empresateste.biblioteca.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import br.com.empresateste.biblioteca.model.Livro;

public interface LivroRepo extends CrudRepository<Livro, Integer> {
	public ArrayList<Livro> findByTituloContaining(String palavra);
}
