package core;

import controller.TelaPrincipalController;
import repository.AtorRepository;
import repository.FilmeRepository;
import repository.GeneroRepository;
import view.TelaPrincipal;

public class Main {

    public static void main(String[] args) {

        FilmeRepository filmeRepository = new FilmeRepository();
        GeneroRepository generoRepository = new GeneroRepository();
        AtorRepository atorRepository = new AtorRepository();

        TelaPrincipal telaPrincipal = new TelaPrincipal();

        new TelaPrincipalController(
            telaPrincipal,
            filmeRepository,
            generoRepository,
            atorRepository
        );

        telaPrincipal.setVisible(true);
    }
}