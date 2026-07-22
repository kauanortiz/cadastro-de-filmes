package model;

import java.util.ArrayList;
import java.util.List;

public class Filme {

	private String titulo;
	private Genero genero;
	private Integer duracao;
	private Integer id;
	private List<Ator> atores = new ArrayList<>();
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public Genero getGenero() {
		return genero;
	}
	
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	public Integer getDuracao() {
		return duracao;
	}
	
	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}
	
	public void setId(Integer id) {
		this.id = id;;
	}
	
	public Integer getId() {
		return id;
	}

	public List<Ator> getAtores() {
		return atores;
	}

	public void setAtores(List<Ator> atores) {
		this.atores = atores;
	}
	
}
