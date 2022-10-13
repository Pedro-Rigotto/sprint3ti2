package model;

import java.time.LocalDate;

/**
 * Classe de tutorial e seus metodos. Contem id, texto, titulo, autor, data de criacao, data de publicacao, se foi publicado ou nao, link do video e categoria.
 * @author pedro
 *
 */
public class Tutorial {
    private int id;
    private String texto;
    private String titulo;
    private int autor;
    private LocalDate dataCriacao;
    private int publicado;
    private String video;
    private int categoria;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public int getAutor() {
        return autor;
    }
    public void setAutor(int autor) {
        this.autor = autor;
    }
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    public int getPublicado() {
        return publicado;
    }
    public void setPublicado(int publicado) {
        this.publicado = publicado;
    }
    public String getVideo() {
        return video;
    }
    public void setVideo(String video) {
        this.video = video;
    }
    public int getCategoria() {
        return categoria;
    }
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
    public Tutorial(int id, String texto, String titulo, int autor, LocalDate dataCriacao, int publicado,
            String video, int categoria) {
        super();
        this.id = id;
        this.texto = texto;
        this.titulo = titulo;
        this.autor = autor;
        this.dataCriacao = dataCriacao;
        this.publicado = publicado;
        this.video = video;
        this.categoria = categoria;
    }
    public Tutorial() {
        super();
        this.id = -1;
        this.texto = "";
        this.titulo = "";
        this.autor = -1;
        this.dataCriacao = LocalDate.now();
        this.publicado = 0;
        this.video = "";
        this.categoria = -1;
    }
    
    @Override
    public boolean equals(Object obj) {
        return (this.getId() == ((Tutorial) obj).getId());
    }
    
}
