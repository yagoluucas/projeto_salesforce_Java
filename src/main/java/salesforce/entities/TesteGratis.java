package salesforce.entities;


public class TesteGratis extends _BaseEntity {
    private String nome;
    private String sobrenome;
    private String cargo;
    private String email;
    private String telefone;
    private Pais pais;
    private Idioma idioma;
    private PorteEmpresa porteEmpresa;

    public TesteGratis(){};

    public TesteGratis(String nome, String sobrenome, String cargo, String email, String telefone, Pais pais, Idioma idioma, PorteEmpresa porteEmpresa) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cargo = cargo;
        this.email = email;
        this.telefone = telefone;
        this.pais = pais;
        this.idioma = idioma;
        this.porteEmpresa = porteEmpresa;
    }

    public TesteGratis(int id, String nome, String sobrenome, String cargo, String email, String telefone, Pais pais, Idioma idioma, PorteEmpresa porteEmpresa) {
        super(id);
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cargo = cargo;
        this.email = email;
        this.telefone = telefone;
        this.pais = pais;
        this.idioma = idioma;
        this.porteEmpresa = porteEmpresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public PorteEmpresa getPorteEmpresa() {
        return porteEmpresa;
    }

    public void setPorteEmpresa(PorteEmpresa porteEmpresa) {
        this.porteEmpresa = porteEmpresa;
    }

    @Override
    public String toString() {
        return "TesteGratis{" +
                "nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", cargo='" + cargo + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", pais=" + pais +
                ", idioma=" + idioma +
                ", porteEmpresa=" + porteEmpresa +
                '}';
    }
}
