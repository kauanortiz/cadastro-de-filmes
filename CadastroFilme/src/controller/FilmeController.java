package controller;

import view.FilmeView;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JOptionPane;

import model.Ator;
import model.Filme;
import model.Genero;
import repository.AtorRepository;
import repository.FilmeRepository;
import repository.GeneroRepository;

public class FilmeController {
	
	private FilmeView view;
	private FilmeRepository repository;
	private GeneroRepository generoRepository;
	private AtorRepository atorRepository;

	public FilmeController(FilmeView view, FilmeRepository repository, GeneroRepository repository2, AtorRepository repository3) {
		this.view = view;
		this.repository = repository;
		this.generoRepository = repository2;
		this.atorRepository = repository3;
		configurarEventos();
		carregarGeneros();
		carregarAtores();
	}
	
	public void configurarEventos() {
		
		view.getExcluirButton().setEnabled(false);
		
		this.view.getSalvarButton().addActionListener(e -> salvarFilme());
		
		this.view.getNovoButton().addActionListener(e -> novoFilme());
		
		this.view.getExcluirButton().addActionListener(e -> excluirFilme());
		
		this.view.getFilmesTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				carregarFilmeSelecionado();
			}
		});
		

		view.addWindowFocusListener(new WindowAdapter() {

	        @Override
	        public void windowGainedFocus(WindowEvent e) {
	            carregarGeneros();
	            carregarAtores();
	        }
	    });
	}
	
	public void carregarGeneros() {

	    view.getGeneroComboBox().removeAllItems();

	    for (Genero genero : generoRepository.listarTodos()) {
	        view.getGeneroComboBox().addItem(genero);
	    }

	    view.getGeneroComboBox().setSelectedIndex(-1);
	}
	
	public void carregarAtores() {

	    view.getAtoresListModel().clear();

	    for (Ator ator : atorRepository.listarTodos()) {
	        view.getAtoresListModel().addElement(ator);
	    }

	    view.getAtoresList().clearSelection();
	}
	
	public void carregarFilmeSelecionado() {
		int linhaSelecionada = view.getFilmesTable().getSelectedRow();
		
		if(linhaSelecionada == -1) {
			return;
		}
		
		view.getExcluirButton().setEnabled(true);
		int linhaModelo = view.getFilmesTable().convertRowIndexToModel(linhaSelecionada);
		Integer id = (Integer) view.getFilmesTableModel().getValueAt(linhaModelo, 0);
		
		this.repository.buscarPorId(id).ifPresent(this::preencherCampos);
	}
	
	public void preencherCampos(Filme filme) {
		view.getTituloField().setText(filme.getTitulo());
		
		view.getGeneroComboBox().setSelectedItem(filme.getGenero());
		
		view.getDuracaoSpinner().setValue(filme.getDuracao());
		
		view.getIdField().setText(filme.getId().toString());
		
		selecionarAtoresDoFilme(filme);
	}
	
	private void selecionarAtoresDoFilme(Filme filme) {

	    List<Integer> indicesSelecionados = new ArrayList<>();

	    ListModel<Ator> modelo = view.getAtoresList().getModel();

	    for (int i = 0; i < modelo.getSize(); i++) {

	        Ator atorDaLista = modelo.getElementAt(i);

	        for (Ator atorDoFilme : filme.getAtores()) {

	            if (atorDaLista.getId().equals(atorDoFilme.getId())) {
	                indicesSelecionados.add(i);
	                break;
	            }
	        }
	    }

	    int[] indices = new int[indicesSelecionados.size()];

	    for (int i = 0; i < indicesSelecionados.size(); i++) {
	        indices[i] = indicesSelecionados.get(i);
	    }

	    view.getAtoresList().setSelectedIndices(indices);
	}
	
	public void salvarFilme() {		
		
		String titulo = view.getTituloField().getText();
		Genero genero = (Genero) view.getGeneroComboBox().getSelectedItem();
		Integer duracao = (Integer) view.getDuracaoSpinner().getValue();
		String id = view.getIdField().getText();
		List<Ator> atoresSelecionados = view.getAtoresList().getSelectedValuesList();
		
		if(titulo.isBlank()) {
	        JOptionPane.showMessageDialog(
	                view,
	                "Informe o título do filme.",
	                "Título inválido",
	                JOptionPane.ERROR_MESSAGE
	            );
	        
	        return;
		}
		
		if(genero == null) {
	        JOptionPane.showMessageDialog(
	                view,
	                "Selecione um gênero para o filme.",
	                "Gênero obrigatório",
	                JOptionPane.WARNING_MESSAGE
	            );
	        
	        return;
		}
		
		if (atoresSelecionados.isEmpty()) {
	        JOptionPane.showMessageDialog(
	                view,
	                "Selecione pelo menos um ator.",
	                "Atores obrigatórios",
	                JOptionPane.WARNING_MESSAGE
	            );
	        
	        return;
		}
		
		Filme filme = new Filme();
		filme.setTitulo(titulo);
		filme.setGenero(genero);
		filme.setDuracao(duracao);
		filme.setAtores(atoresSelecionados);
		
		if(id.equals("")){
			
			repository.adicionar(filme);
			
	        JOptionPane.showMessageDialog(
	                view,
	                "Filme cadastrado com sucesso!",
	                "Cadastro realizado",
	                JOptionPane.INFORMATION_MESSAGE
	            );
			
		}else{		
			
			filme.setId(Integer.parseInt(id));
			repository.atualizar(filme);
			
			JOptionPane.showMessageDialog(
		            view,
		            "Filme atualizado com sucesso!",
		            "Atualização realizada",
		            JOptionPane.INFORMATION_MESSAGE
				);
		}
		
		limparCampos();
		
		montarTabela();
	
	}
	
	public void limparCampos() {
		this.view.getTituloField().setText("");
		this.view.getGeneroComboBox().setSelectedItem(null);
		this.view.getDuracaoSpinner().setValue(1);
		this.view.getIdField().setText("");
		this.view.getAtoresList().clearSelection();
		
		this.view.getExcluirButton().setEnabled(false);
	}
	
	public void montarTabela() {
		view.getFilmesTableModel().setRowCount(0);
		
		for(Filme f : repository.listarTodos()) {
			view.getFilmesTableModel().addRow(new Object[] {
					f.getId(),
					f.getTitulo(),
					f.getGenero().getNome(),
					f.getDuracao(),
					f.getAtores()
			});
		}
	}
	
	public void novoFilme() {	
		limparCampos();
		
	    JOptionPane.showMessageDialog(
	            view,
	            "Campos preparados para um novo cadastro.",
	            "Novo filme",
	            JOptionPane.INFORMATION_MESSAGE
	        );
	}
	
	public void excluirFilme() {
		String id = view.getIdField().getText();
		
		repository.excluir(Integer.parseInt(id));
		
        JOptionPane.showMessageDialog(
                view,
                "Filme excluído com sucesso!",
                "Exclusão realizada",
                JOptionPane.INFORMATION_MESSAGE
            );
		
		limparCampos();
		montarTabela();
			
	}

}
