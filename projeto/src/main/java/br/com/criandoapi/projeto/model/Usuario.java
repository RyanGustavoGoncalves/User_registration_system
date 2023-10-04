package br.com.criandoapi.projeto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotEmpty(message = "O nome é obrigatório!")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres!")
    @Column(name = "nome", length = 90, nullable = false) //lenght: quantidade de caracteres nullable: not null
    private String nome;
    @Email(message = "Insira um email válido!")
    @NotEmpty(message = "O email é obrigatório!")
    @Column(name = "email", length = 90, nullable = false)
    private String email;
    @NotEmpty(message = "A senha é obrigatório!")
    @Column(name = "senha", columnDefinition = "TEXT", nullable = false) //columDefinition: TEXT é o tipo da coluna no mysql
    private String senha;
    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
