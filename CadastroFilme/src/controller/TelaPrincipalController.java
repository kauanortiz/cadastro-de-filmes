package controller;

import repository.AtorRepository;
import repository.FilmeRepository;
import repository.GeneroRepository;
import view.AtorView;
import view.FilmeView;
import view.GeneroView;
import view.TelaPrincipal;

public class TelaPrincipalController {

    private TelaPrincipal view;

    private FilmeRepository filmeRepository;
    private GeneroRepository generoRepository;
    private AtorRepository atorRepository;

    public TelaPrincipalController(
            TelaPrincipal view,
            FilmeRepository filmeRepository,
            GeneroRepository generoRepository,
            AtorRepository atorRepository) {

        this.view = view;
        this.filmeRepository = filmeRepository;
        this.generoRepository = generoRepository;
        this.atorRepository = atorRepository;

        configurarEventos();
    }

    private void configurarEventos() {

        view.getFilmesButton().addActionListener(e -> abrirTelaFilmes());

        view.getGenerosButton().addActionListener(e -> abrirTelaGeneros());

        view.getAtoresButton().addActionListener(e -> abrirTelaAtores());
    }

    private void abrirTelaFilmes() {

        FilmeView filmeView = new FilmeView();

        new FilmeController(
            filmeView,
            filmeRepository,
            generoRepository,
            atorRepository
        );

        filmeView.setVisible(true);
    }

    private void abrirTelaGeneros() {

        GeneroView generoView = new GeneroView();

        new GeneroController(
            generoView,
            generoRepository,
            filmeRepository
        );

        generoView.setVisible(true);
    }

    private void abrirTelaAtores() {

        AtorView atorView = new AtorView();

        new AtorController(
            atorView,
            atorRepository,
            filmeRepository
        );

        atorView.setVisible(true);
    }
}