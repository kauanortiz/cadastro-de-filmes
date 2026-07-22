package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TelaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JButton filmesButton;
    private JButton generosButton;
    private JButton atoresButton;

    public TelaPrincipal() {

        setTitle("Sistema de Filmes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(null);

        setContentPane(contentPane);

        JLabel tituloLabel = new JLabel("Sistema de Cadastro");
        tituloLabel.setBounds(150, 30, 180, 30);
        contentPane.add(tituloLabel);

        filmesButton = new JButton("Filmes");
        filmesButton.setBounds(140, 90, 150, 30);
        contentPane.add(filmesButton);

        generosButton = new JButton("Gêneros");
        generosButton.setBounds(140, 140, 150, 30);
        contentPane.add(generosButton);

        atoresButton = new JButton("Atores");
        atoresButton.setBounds(140, 190, 150, 30);
        contentPane.add(atoresButton);
    }

    public JButton getFilmesButton() {
        return filmesButton;
    }

    public JButton getGenerosButton() {
        return generosButton;
    }

    public JButton getAtoresButton() {
        return atoresButton;
    }
}