package com.example.mapeamentoenergico;

import java.util.Objects;

public class User {
    private String name, email, senha;
    private int cpf;

    public User(String name, String email, String senha, int cpf) {
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getCpf() == user.getCpf();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCpf());
    }
}
