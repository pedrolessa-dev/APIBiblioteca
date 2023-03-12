package br.com.empresateste.biblioteca.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.empresateste.biblioteca.model.Livro;
import br.com.empresateste.biblioteca.repo.LivroRepo;

@Component
public class LivroServiceImpl implements ILivroService {

	@Autowired
	public LivroRepo repo;

	@Override
	public ArrayList<Livro> exibirTodos() {
		return (ArrayList<Livro>) repo.findAll();
	}

	@Override
	public Livro exibirPeloCodigo(int codigo) {
		return repo.findById(codigo).orElse(null);
	}

	@Override
	public ArrayList<Livro> exibirPeloNome(@RequestParam(name = "palavra") String palavraChave) {
		return (ArrayList<Livro>) repo.findByTituloContaining(palavraChave);
	}

	@Override
	public Livro cadastrarLivro(Livro l) {
		return repo.save(l);
	}

	@Override
	public Livro alterarLivro(Livro l) {
		if (l.getCodigo() > 0) {
			return repo.save(l);
		}
		return null;
	}

	@Override
	public boolean excluirLivro(int codigo) {
		if (repo.existsById(codigo)) {
			repo.deleteById(codigo);
			return true;
		}
		return false;
	}

}
