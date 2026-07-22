package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Genero;

public class GeneroRepository {
	
	private final List<Genero> generos = new ArrayList<>();
	private Integer proxId = 1;
	
	public void adicionarGenero(Genero genero) {
		
		genero.setId(proxId);
		
		proxId++;
		
		generos.add(genero);
		
	}
	
	public List<Genero> listarTodos() {
		
		return generos;
		
	}
	
	public void atualizar(Genero generoAtualizado) {
		
		for(int i = 0; i < generos.size(); i++) {
			Genero generoAtual = generos.get(i);
			
			if(generoAtual.getId() == generoAtualizado.getId()) {
				generos.set(i, generoAtualizado);
				break;
			}
		}
	}
	
	public void excluir(Integer id) {
		
		this.generos.removeIf(genero -> genero.getId() == id);
		
	}
	
	public Optional<Genero> buscarPorId(Integer id){
		return this.generos.stream().filter(genero -> genero.getId() == id).findFirst();
	}

}
