package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JList;
import model.Ator;
import model.Genero;

public class FilmeView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tituloField;
	private JSpinner duracaoSpinner;
	private JTable filmesTable;
	private DefaultTableModel filmesTableModel;
	private JButton novoButton;
	private JButton salvarButton;
	private JButton excluirButton;
	private JLabel idLabel;
	private JTextField idField;
	private JLabel atoresLabel;
	private JList<Ator> atoresList;
	private DefaultListModel<Ator> atoresListModel;
	private JComboBox<Genero> generoComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FilmeView frame = new FilmeView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FilmeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel tituloPaginaLabel = new JLabel("Cadastro de Filme");
		tituloPaginaLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		tituloPaginaLabel.setBounds(119, 11, 194, 25);
		contentPane.add(tituloPaginaLabel);
		
		JLabel tituloLabel = new JLabel("Título:");
		tituloLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tituloLabel.setBounds(10, 75, 57, 14);
		contentPane.add(tituloLabel);
		
		JLabel generoLabel = new JLabel("Gênero:");
		generoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		generoLabel.setBounds(10, 100, 66, 14);
		contentPane.add(generoLabel);
		
		JLabel duraçãoLabel = new JLabel("Duração:");
		duraçãoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		duraçãoLabel.setBounds(10, 126, 66, 19);
		contentPane.add(duraçãoLabel);
		
		tituloField = new JTextField();
		tituloField.setBounds(77, 77, 237, 14);
		contentPane.add(tituloField);
		tituloField.setColumns(10);
		
		duracaoSpinner = new JSpinner();
		duracaoSpinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		duracaoSpinner.setBounds(76, 125, 47, 20);
		contentPane.add(duracaoSpinner);
		
		novoButton = new JButton("Novo");
		novoButton.setBounds(10, 247, 89, 23);
		contentPane.add(novoButton);
		
		salvarButton = new JButton("Salvar");
		salvarButton.setBounds(109, 247, 89, 23);
		contentPane.add(salvarButton);
		
		excluirButton = new JButton("Excluir");
		excluirButton.setBounds(208, 247, 89, 23);
		contentPane.add(excluirButton);
		
		filmesTableModel = new DefaultTableModel(new Object[] {"ID", "Título", "Gênero", "Duração", "Atores"}, 0);
		
		filmesTable = new JTable(filmesTableModel);
		filmesTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		filmesTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		filmesTable.setBounds(10, 298, 602, 98);
		contentPane.add(filmesTable);	
		
		idLabel = new JLabel("ID:");
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		idLabel.setBounds(10, 50, 29, 14);
		contentPane.add(idLabel);
		
		idField = new JTextField();
		idField.setEditable(false);
		idField.setEnabled(false);
		idField.setColumns(10);
		idField.setBounds(76, 52, 38, 14);
		contentPane.add(idField);
		
		generoComboBox = new JComboBox<>();	
		generoComboBox.setBounds(77, 98, 109, 22);
		contentPane.add(generoComboBox);
		
		atoresLabel = new JLabel("Ator(es):");
		atoresLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		atoresLabel.setBounds(10, 156, 89, 14);
		contentPane.add(atoresLabel);
		
		atoresListModel = new DefaultListModel<>();

		atoresList = new JList<>(atoresListModel);

		atoresList.setSelectionMode(
		    ListSelectionModel.MULTIPLE_INTERVAL_SELECTION
		);
		
		atoresList.setBounds(77, 156, 236, 14);
		
		JScrollPane atoresScrollPane = new JScrollPane(atoresList);
		atoresScrollPane.setBounds(77, 150, 236, 50);
		contentPane.add(atoresScrollPane);

	}

	public JTextField getTituloField() {
		return tituloField;
	}

	public void setTituloField(JTextField tituloField) {
		this.tituloField = tituloField;
	}

	public JSpinner getDuracaoSpinner() {
		return duracaoSpinner;
	}

	public void setDuracaoSpinner(JSpinner duracaoSpinner) {
		this.duracaoSpinner = duracaoSpinner;
	}

	public JTable getTable() {
		return filmesTable;
	}

	public void setTable(JTable table) {
		this.filmesTable = table;
	}

	public JButton getNovoButton() {
		return novoButton;
	}

	public void setNovoButton(JButton novoButton) {
		this.novoButton = novoButton;
	}

	public JButton getSalvarButton() {
		return salvarButton;
	}

	public void setSalvarButton(JButton salvarButton) {
		this.salvarButton = salvarButton;
	}

	public JButton getExcluirButton() {
		return excluirButton;
	}

	public void setExcluirButton(JButton excluirButton) {
		this.excluirButton = excluirButton;
	}

	public DefaultTableModel getFilmesTableModel() {
		return filmesTableModel;
	}

	public void setFilmesTableModel(DefaultTableModel filmesTableModel) {
		this.filmesTableModel = filmesTableModel;
	}

	public JLabel getIdLabel() {
		return idLabel;
	}

	public void setIdLabel(JLabel idLabel) {
		this.idLabel = idLabel;
	}

	public JTextField getIdField() {
		return idField;
	}

	public void setIdField(JTextField idField) {
		this.idField = idField;
	}

	public JTable getFilmesTable() {
		return filmesTable;
	}

	public void setFilmesTable(JTable filmesTable) {
		this.filmesTable = filmesTable;
	}

	public JComboBox<Genero> getGeneroComboBox() {
		return generoComboBox;
	}

	public void setGeneroComboBox(JComboBox<Genero> generoComboBox) {
		this.generoComboBox = generoComboBox;
	}

	public JList<Ator> getAtoresList() {
		return atoresList;
	}

	public void setAtoresList(JList<Ator> atoresList) {
		this.atoresList = atoresList;
	}

	public DefaultListModel<Ator> getAtoresListModel() {
		return atoresListModel;
	}

	public void setAtoresListModel(DefaultListModel<Ator> atoresListModel) {
		this.atoresListModel = atoresListModel;
	}
}
