package com.Alvolante.Backend.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;



public class UsuarioEntity {
    // Atributos
    private long idUsuario;
    private String rut;
    private String nameParam;
    private String email;
    private String phone;
    @JsonFormat
    private Date birthdate;
    private String password;
    private String role;

    // Constructor completo
    public UsuarioEntity(long idUsuario, String rut, String nameParam, String email, String phone, Date birthdate, String password, String role) {
        this.idUsuario = idUsuario;
        this.rut = rut;
        this.nameParam = nameParam;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.password = password;
        this.role = role;
    }

    // Constructor sin ID (para inserciones)
    public UsuarioEntity(String rut, String nameParam, String email, String phone, Date birthdate, String password, String role) {
        this.rut = rut;
        this.nameParam = nameParam;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
        this.password = password;
        this.role = role;
    }

    // Constructor vacío
    public UsuarioEntity() {

    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNameParam() {
        return nameParam;
    }

    public void setNameParam(String nameParam) {
        this.nameParam = nameParam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}