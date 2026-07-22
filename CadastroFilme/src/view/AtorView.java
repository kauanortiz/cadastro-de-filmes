package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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

public class AtorView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomeField;
	private JTextField idField;
	private JTable atoresTable;
	private DefaultTableModel atoresTableModel;
	private JButton novoButton;   
	private JButton salvarButton;
	private JButton atualizarButton;
	private JButton excluirButton;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtorView frame = new AtorView();
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
	public AtorView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel tituloPaginaLabel = new JLabel("Cadastro de Ator");
		tituloPaginaLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tituloPaginaLabel.setBounds(136, 16, 157, 20);
		contentPane.add(tituloPaginaLabel);
		
		JLabel nomeLabel = new JLabel("Nome:");
		nomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nomeLabel.setBounds(10, 54, 54, 14);
		contentPane.add(nomeLabel);
		
		nomeField = new JTextField();
		nomeField.setBounds(64, 53, 146, 20);
		contentPane.add(nomeField);
		nomeField.setColumns(10);
		
		JLabel idLabel = new JLabel("ID:");
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		idLabel.setBounds(10, 87, 46, 14);
		contentPane.add(idLabel);
		
		idField = new JTextField();
		idField.setBounds(64, 86, 29, 20);
		contentPane.add(idField);
		idField.setColumns(10);
		
		atoresTableModel = new DefaultTableModel(new Object[] {"ID", "Nome"}, 0);
		atoresTable = new JTable(atoresTableModel);
		atoresTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		atoresTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		atoresTable.setBounds(10, 161, 414, 89);
		contentPane.add(atoresTable);
		
		novoButton = new JButton("Novo");
		novoButton.setBounds(22, 132, 89, 23);
		contentPane.add(novoButton);
		
		salvarButton = new JButton("Salvar");
		salvarButton.setBounds(121, 132, 89, 23);
		contentPane.add(salvarButton);
		
		atualizarButton = new JButton("Atualizar");
		atualizarButton.setBounds(220, 132, 89, 23);
		contentPane.add(atualizarButton);
		
		excluirButton = new JButton("Excluir");
		excluirButton.setBounds(319, 132, 89, 23);
		contentPane.add(excluirButton);

	}

	public JTextField getNomeField() {
		return nomeField;
	}

	public void setNomeField(JTextField nomeField) {
		this.nomeField = nomeField;
	}

	public JTextField getIdField() {
		return idField;
	}

	public void setIdField(JTextField idField) {
		this.idField = idField;
	}

	public JTable getAtoresTable() {
		return atoresTable;
	}

	public void setAtoresTable(JTable atoresTable) {
		this.atoresTable = atoresTable;
	}

	public DefaultTableModel getAtoresTableModel() {
		return atoresTableModel;
	}

	public void setAtoresTableModel(DefaultTableModel atoresTableModel) {
		this.atoresTableModel = atoresTableModel;
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

	public JButton getAtualizarButton() {
		return atualizarButton;
	}

	public void setAtualizarButton(JButton atualizarButton) {
		this.atualizarButton = atualizarButton;
	}

	public JButton getExcluirButton() {
		return excluirButton;
	}

	public void setExcluirButton(JButton excluirButton) {
		this.excluirButton = excluirButton;
	}

}
