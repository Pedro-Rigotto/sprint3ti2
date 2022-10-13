package model;

import java.time.LocalDate;
/**
 * Classe de comentario e seus metodos. Contem id, descricao, data de publicacao, se foi publicado ou nao, autor, e id do tutorial.
 * @author pedro
 *
 */
public class Comentario {
    private int id;
    private String descricao;
    private LocalDate dataPublicacao;
    private int publicado;
    private int autor;
    private int tutorial;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }
    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
    public int getPublicado() {
        return publicado;
    }
    public void setPublicado(int publicado) {
        this.publicado = publicado;
    }
    public int getAutor() {
        return autor;
    }
    public void setAutor(int autor) {
        this.autor = autor;
    }
    public int getTutorial() {
        return tutorial;
    }
    public void setTutorial(int tutorial) {
        this.tutorial = tutorial;
    }
    public Comentario(int id, String descricao, LocalDate dataPublicacao, int publicado, int autor, int tutorial) {
        super();
        this.id = id;
        this.descricao = descricao;
        this.dataPublicacao = dataPublicacao;
        this.publicado = publicado;
        this.autor = autor;
        this.tutorial = tutorial;
    }
    public Comentario() {
        super();
        this.id = -1;
        this.descricao = "";
        this.dataPublicacao = LocalDate.now();
        this.publicado = 0;
        this.autor = -1;
        this.tutorial = -1;
    }
    
    @Override
    public boolean equals(Object obj) {
        return (this.getId() == ((Tutorial) obj).getId());
    }
}
