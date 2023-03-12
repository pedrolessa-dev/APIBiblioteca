package br.com.empresateste.biblioteca.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresateste.biblioteca.dto.MensagemErro;
import br.com.empresateste.biblioteca.model.Livro;
import br.com.empresateste.biblioteca.service.ILivroService;

@RestController
public class LivroController {
	@Autowired
	public ILivroService service;

	@GetMapping("/livros")
	public ArrayList<Livro> exibirTodos() {
		return service.exibirTodos();
	}

	@GetMapping("/livros/{codigo}")
	public ResponseEntity<?> exibirPeloCodigo(@PathVariable int codigo) {
		Livro l = service.exibirPeloCodigo(codigo);
		if (l != null) {
			return ResponseEntity.ok(l);
		}
		return ResponseEntity.status(404).body(new MensagemErro(1234, "Código " + codigo + " não encontrado."));
	}

	@GetMapping("/livros/busca")
	public ResponseEntity<?> exibirPeloNome(@RequestParam(name = "palavra") String palavraChave) {
		ArrayList<Livro> lista = service.exibirPeloNome(palavraChave);
		if (lista.size() > 0) {
			return ResponseEntity.ok(lista);
		}
		return ResponseEntity.status(404).body(new MensagemErro(1235, "Título " + palavraChave + " não encontrado."));
	}

	@PostMapping("/livros")
	public ResponseEntity<?> adicionarLivro(@RequestBody Livro l) {
		Livro res = service.cadastrarLivro(l);
		if (res != null) {
			return ResponseEntity.status(201).body(res);
		}
		return ResponseEntity.status(400).body(new MensagemErro(1236, "Erro ao adicionar o livro."));
	}

	@PutMapping("/livros")
	public ResponseEntity<?> alterarLivro(@RequestBody Livro l) {
		Livro res = service.alterarLivro(l);
		if (res != null) {
			return ResponseEntity.ok(res);
		}
		return ResponseEntity.status(400).body(new MensagemErro(1237, "Erro ao atualizar o livro."));
	}

	@DeleteMapping("/livros/{codigo}")
	public ResponseEntity<?> excluirLivro(@PathVariable int codigo) {
		if (service.excluirLivro(codigo)) {
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.status(400).body(new MensagemErro(1238, "Erro ao excluir este livro."));
	}
}
