package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Filme;

public class FilmeRepository {

	private final List<Filme> filmes = new ArrayList<>();
	private Integer proxId = 1;
	
	public void adicionar(Filme filme) {
		
		filme.setId(proxId);
		
		proxId++;
		
		filmes.add(filme);
		
	}
	
	public List<Filme> listarTodos(){
		return filmes;
	}
	
	public Optional<Filme> buscarPorId(Integer id) {
		return this.filmes.stream().filter(filme->filme.getId() == id).findFirst();
	}
	
	public void atualizar(Filme filmeAtualizado) {
		
		for(int i = 0; i < filmes.size(); i++) {		
			Filme filmeAtual = filmes.get(i);
			
			if(filmeAtualizado.getId() == filmeAtual.getId()) {
				filmes.set(i, filmeAtualizado);
				break;
			}
		}
		
	}

	public void excluir(Integer id) {
		
		this.filmes.removeIf(filme -> filme.getId() == id);
		
	}
	
}
