package controller;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JOptionPane;

import view.GeneroView;
import model.Filme;
import model.Genero;
import repository.FilmeRepository;
import repository.GeneroRepository;

public class GeneroController {
	
	private GeneroView view;
	private GeneroRepository repository;
	private FilmeRepository filmeRepository;
	
	public GeneroController(GeneroView view, GeneroRepository repository, FilmeRepository filmeRepository) {
		this.view = view;
		this.repository = repository;
		this.filmeRepository = filmeRepository;
		configurarEventos();
	}
	
	public void configurarEventos() {
		
		view.getExcluirButton().setEnabled(false);
		
		this.view.getNovoButton().addActionListener(e -> novoGenero());
		
		this.view.getSalvarButton().addActionListener(e -> salvarGenero());
		
		this.view.getExcluirButton().addActionListener(e -> excluirGenero());
		
		this.view.getAtualizarButton().addActionListener(e -> atualizarGenero());
			
		this.view.getGenerosTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				carregarGeneroSelecionado();
			}
		});
		
	}
	
	public void carregarGeneroSelecionado() {
		
		int linhaSelecionada = view.getGenerosTable().getSelectedRow();
		
		if(linhaSelecionada == -1) {
			return;
		}
		
		view.getExcluirButton().setEnabled(true);
		
		//converte p linha correspondente no modelo
		int linhaModelo = view.getGenerosTable().convertRowIndexToModel(linhaSelecionada);
		
		//pega o valor do id presente na linha convertida e na coluna 0
		Integer id = (Integer) view.getGenerosTableModel().getValueAt(linhaModelo, 0);
		
		//se o id existir no repositorio, preenche as labels
		this.repository.buscarPorId(id).ifPresent(this::preencherCampos);
		
	}
	
	
	public void preencherCampos(Genero genero) {
		
		view.getNomeField().setText(genero.getNome());
		view.getIdField().setText(genero.getId().toString());	
		
	}
	
	public void salvarGenero() {
		
		String nome = view.getNomeField().getText();
		String id = view.getIdField().getText();
		
		if(nome.isBlank()) {
	        JOptionPane.showMessageDialog(
	                view,
	                "Informe o nome do gênero.",
	                "Nome inválido",
	                JOptionPane.ERROR_MESSAGE
	            );
	        
	        return;
		}
		
		for(Genero g : repository.listarTodos()) {
			if(g.getNome().equals(nome)) {
	            JOptionPane.showMessageDialog(
	                    view,
	                    "Já existe um gênero cadastrado com esse nome.",
	                    "Gênero duplicado",
	                    JOptionPane.WARNING_MESSAGE
	                );
	            
	            return;
			}
		}
		
		Genero genero = new Genero(nome);
		
		if(id.equals("")) {
			
			repository.adicionarGenero(genero);
			

	        JOptionPane.showMessageDialog(
	            view,
	            "Gênero cadastrado com sucesso!",
	            "Cadastro realizado",
	            JOptionPane.INFORMATION_MESSAGE
	        );
			
		}else {
			
			genero.setId(Integer.parseInt(id));
			repository.atualizar(genero);
			
	        JOptionPane.showMessageDialog(
	                view,
	                "Gênero atualizado com sucesso!",
	                "Atualização realizada",
	                JOptionPane.INFORMATION_MESSAGE
	            );
			
		}
		
		limparCampos();
		
		montarTabela();
		
		
	}
	
	
	public void montarTabela() {
		
		view.getGenerosTableModel().setRowCount(0);
		
		for(Genero g : repository.listarTodos()) {
			view.getGenerosTableModel().addRow(new Object[] {
					
					g.getId(),
					g.getNome(),
					
			});
		}
		
	}
	
	public void limparCampos() {
		
		this.view.getNomeField().setText("");
		this.view.getIdField().setText("");
		
		this.view.getExcluirButton().setEnabled(false);
		
	}
	
	public void novoGenero() {
		
	    JOptionPane.showMessageDialog(
	            view,
	            "Campos preparados para um novo cadastro.",
	            "Novo gênero",
	            JOptionPane.INFORMATION_MESSAGE
	        );
	    
		limparCampos();
		
	}
	
	public void excluirGenero() {
		
		String idTexto = view.getIdField().getText();
		Integer id = Integer.parseInt(idTexto);

		    for (Filme filme : filmeRepository.listarTodos()) {

		        if (filme.getGenero().getId().equals(id)) {

		            JOptionPane.showMessageDialog(
		                    view,
		                    "O gênero está associado ao filme \""
		                        + filme.getTitulo()
		                        + "\".\nExclua o filme primeiro.",
		                    "Exclusão não permitida",
		                    JOptionPane.WARNING_MESSAGE
		                );

		                return;
		        }
		    }
		
		repository.excluir(id);
		
	    JOptionPane.showMessageDialog(
	            view,
	            "Gênero excluído com sucesso!",
	            "Exclusão realizada",
	            JOptionPane.INFORMATION_MESSAGE
	        );
		
		limparCampos();
		montarTabela();
			
	}
	
	public void atualizarGenero() {
		
		String nome = view.getNomeField().getText();
		String id = view.getIdField().getText();
		Integer idAux = Integer.parseInt(id);
		
		if(nome.isBlank()) {
	        JOptionPane.showMessageDialog(
	                view,
	                "Informe um nome válido para o gênero.",
	                "Nome inválido",
	                JOptionPane.ERROR_MESSAGE
	            );
	        return;
		}
		
		for(Genero g : repository.listarTodos()) {
			if(g.getNome().equals(nome)) {
	            JOptionPane.showMessageDialog(
	                    view,
	                    "Já existe outro gênero cadastrado com esse nome.",
	                    "Gênero duplicado",
	                    JOptionPane.WARNING_MESSAGE
	                );

	                return;
			}
		}
		
		Genero genero = new Genero(nome);
		genero.setId(idAux);
		repository.atualizar(genero);
		
		limparCampos();
		
		montarTabela();
		
	}

}
