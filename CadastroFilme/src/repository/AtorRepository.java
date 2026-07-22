package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Ator;
import model.Genero;

public class AtorRepository {

	private final List<Ator> atores = new ArrayList<>();
	private Integer proxId = 1;
	
	public void adicionarAtor(Ator ator) {
		
		ator.setId(proxId);
		
		proxId++;
		
		atores.add(ator);
		
	}
	
	public List<Ator> listarTodos() {
		
		return atores;
		
	}
	
	public void atualizar(Ator atorAtualizado) {
		
		for(int i = 0; i < atores.size(); i++) {
			Ator atorAtual = atores.get(i);
			
			if(atorAtual.getId() == atorAtualizado.getId()) {
				atores.set(i, atorAtualizado);
				break;
			}
		}
	}
	
	public void excluir(Integer id) {
		
		this.atores.removeIf(ator -> ator.getId() == id);
		
	}
	
	public Optional<Ator> buscarPorId(Integer id){
		return this.atores.stream().filter(ator -> ator.getId() == id).findFirst();
	}
	
}
