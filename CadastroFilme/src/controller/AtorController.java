package controller;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JOptionPane;

import view.AtorView;
import model.Ator;
import model.Filme;
import repository.AtorRepository;
import repository.FilmeRepository;

public class AtorController {

	private AtorView view;
	private AtorRepository repository;
	private FilmeRepository filmeRepository;
	
	public AtorController(AtorView view, AtorRepository repository, FilmeRepository filmeRepository) {
		this.view = view;
		this.repository = repository;
		this.filmeRepository = filmeRepository;
		configurarEventos();
	}
	
	public void configurarEventos() {
		
		view.getExcluirButton().setEnabled(false);
		
		this.view.getNovoButton().addActionListener(e -> novoAtor());
		
		this.view.getSalvarButton().addActionListener(e -> salvarAtor());
		
		this.view.getExcluirButton().addActionListener(e -> excluirAtor());
		
		this.view.getAtualizarButton().addActionListener(e -> atualizarAtor());
			
		this.view.getAtoresTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				carregarAtorSelecionado();
			}
		});
		
	}
	
	public void carregarAtorSelecionado() {
		
		int linhaSelecionada = view.getAtoresTable().getSelectedRow();
		
		if(linhaSelecionada == -1) {
			return;
		}
		
		view.getExcluirButton().setEnabled(true);
		
		//converte p linha correspondente no modelo
		int linhaModelo = view.getAtoresTable().convertRowIndexToModel(linhaSelecionada);
		
		//pega o valor do id presente na linha convertida e na coluna 0
		Integer id = (Integer) view.getAtoresTableModel().getValueAt(linhaModelo, 0);
		
		//se o id existir no repositorio, preenche as labels
		this.repository.buscarPorId(id).ifPresent(this::preencherCampos);
		
	}
	
	
	public void preencherCampos(Ator ator) {
		
		view.getNomeField().setText(ator.getNome());
		view.getIdField().setText(ator.getId().toString());	
		
	}
	
	public void salvarAtor() {
		
		String nome = view.getNomeField().getText();
		String id = view.getIdField().getText();
		
		if(nome.isBlank()) {
	        JOptionPane.showMessageDialog(
	                view,
	                "Informe o nome do ator.",
	                "Nome inválido",
	                JOptionPane.ERROR_MESSAGE
	            );

	            return;
		}
		
		for(Ator a : repository.listarTodos()) {
			if(a.getNome().equals(nome)) {
	            JOptionPane.showMessageDialog(
	                    view,
	                    "Já existe um ator cadastrado com esse nome.",
	                    "Ator duplicado",
	                    JOptionPane.WARNING_MESSAGE
	                );

	                return;
			}
		}
		
		Ator ator = new Ator(nome);
		
		if(id.equals("")) {
			
			repository.adicionarAtor(ator);
			
	        JOptionPane.showMessageDialog(
	                view,
	                "Ator cadastrado com sucesso!",
	                "Cadastro realizado",
	                JOptionPane.INFORMATION_MESSAGE
	            );
			
		}else {
			
			ator.setId(Integer.parseInt(id));
			repository.atualizar(ator);
			
	        JOptionPane.showMessageDialog(
	                view,
	                "Ator atualizado com sucesso!",
	                "Atualização realizada",
	                JOptionPane.INFORMATION_MESSAGE
	            );
		}
		
		limparCampos();
		
		montarTabela();
		
		
	}
	
	
	public void montarTabela() {
		
		view.getAtoresTableModel().setRowCount(0);
		
		for(Ator a : repository.listarTodos()) {
			view.getAtoresTableModel().addRow(new Object[] {
					
					a.getId(),
					a.getNome(),
					
			});
		}
		
	}
	
	public void limparCampos() {
		
		this.view.getNomeField().setText("");
		this.view.getIdField().setText("");
		
		this.view.getExcluirButton().setEnabled(false);
		
	}
	
	public void novoAtor() {
		
	    JOptionPane.showMessageDialog(
	            view,
	            "Campos preparados para um novo cadastro.",
	            "Novo ator",
	            JOptionPane.INFORMATION_MESSAGE
	        );
	    
		limparCampos();
		
	}
	
	public void excluirAtor() {
		
		String idTexto = view.getIdField().getText();
		Integer id = Integer.parseInt(idTexto);
		
		for(Filme filme : filmeRepository.listarTodos()) {
			
			for(Ator ator: filme.getAtores()) {
				
				if(ator.getId().equals(id)) {
					
	                JOptionPane.showMessageDialog(
	                        view,
	                        "O ator está associado ao filme \""
	                            + filme.getTitulo()
	                            + "\".\nExclua o filme primeiro.",
	                        "Exclusão não permitida",
	                        JOptionPane.WARNING_MESSAGE
	                    );
	                
	                return;
					
				}
				
			}

		}
		
		repository.excluir(id);
		
	    JOptionPane.showMessageDialog(
	            view,
	            "Ator excluído com sucesso!",
	            "Exclusão realizada",
	            JOptionPane.INFORMATION_MESSAGE
	        );
		
		limparCampos();
		montarTabela();
			
	}
	
	public void atualizarAtor() {
		
		String nome = view.getNomeField().getText();
		String id = view.getIdField().getText();
		Integer idAux = Integer.parseInt(id);
		
		if(nome.isBlank()) {
	        JOptionPane.showMessageDialog(
	                view,
	                "Informe um nome válido para o ator.",
	                "Nome inválido",
	                JOptionPane.ERROR_MESSAGE
	            );
	        return;
		}
		
		for(Ator a : repository.listarTodos()) {
			if(a.getNome().equals(nome)) {
	            JOptionPane.showMessageDialog(
	                    view,
	                    "Já existe outro ator cadastrado com esse nome.",
	                    "Ator duplicado",
	                    JOptionPane.WARNING_MESSAGE
	                );

	                return;
			}
		}
		
		Ator ator = new Ator(nome);
		ator.setId(idAux);
		repository.atualizar(ator);
		
	    JOptionPane.showMessageDialog(
	            view,
	            "Ator atualizado com sucesso!",
	            "Atualização realizada",
	            JOptionPane.INFORMATION_MESSAGE
	        );
		
		limparCampos();
		
		montarTabela();
		
	}
	
	
}
