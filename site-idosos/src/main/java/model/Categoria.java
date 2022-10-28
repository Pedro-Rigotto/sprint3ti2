package model;

/**
 * Classe de categorias e seus metodos. Contem id e nome.
 * @author André M, Pedro R
 * 
 */
public class Categoria {
    private int id;
    private String nome;
    private String supercategoria;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSupercategoria() {
        return supercategoria;
    }
    public void setSupercategoria(String supercategoria) {
        this.supercategoria = supercategoria;
    }
    public Categoria(int id, String nome, String supercategoria) {
        super();
        this.id = id;
        this.nome = nome;
        this.supercategoria = supercategoria;
    }
    public Categoria() {
        super();
        this.id = -1;
        this.nome = "";
        this.supercategoria = "";
    }
    
    @Override
    public boolean equals(Object obj) {
        return (this.getId() == ((Categoria) obj).getId());
    }
}
