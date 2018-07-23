package uca.ruiz.antonio.tfgapp.data.api.model;

/**
 * Created by toni on 08/06/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uca.ruiz.antonio.tfgapp.data.api.mapping.Authority;

public class User implements Serializable {

    private final static long serialVersionUID = -7074548215409497555L;
    protected long id;
    @SerializedName("username")
    @Expose
    protected String username;
    @SerializedName("password")
    @Expose
    protected String password;
    @SerializedName("firstname")
    @Expose
    protected String firstname;
    @SerializedName("lastname")
    @Expose
    protected String lastname;
    @SerializedName("email")
    @Expose
    protected String email;
    @SerializedName("authorities")
    @Expose
    protected List<Authority> authorities = null;
    @SerializedName("enabled")
    @Expose
    protected Boolean enabled;
    @SerializedName("lastPasswordResetDate")
    @Expose
    protected Date lastPasswordResetDate;
    @SerializedName("permisos")
    @Expose
    protected ArrayList<Boolean> permisos = new ArrayList <>(3);
    @SerializedName("dni")
    @Expose
    protected String dni;
    @SerializedName("nacimiento")
    @Expose
    protected Date nacimiento;

    public User() {
    }

    public User(String username, String firstname, String lastname, String email,
                ArrayList<Boolean> permisos, String dni, Date nacimiento) {
        this.username = username;
        this.password = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.enabled = false;
        this.permisos = permisos;
        this.dni = dni;
        this.nacimiento = nacimiento;
    }

    public User(String username, String firstname, String lastname, String email,
                ArrayList<Boolean> permisos, String dni, Date nacimiento, Boolean enabled) {
        this.username = username;
        this.password = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.permisos = permisos;
        this.dni = dni;
        this.nacimiento = nacimiento;
        this.enabled = enabled;
    }

    public User(String username, String firstname, String lastname, String email, List<Authority> authorities,
                ArrayList<Boolean> permisos, String dni, Date nacimiento) {
        this.username = username;
        this.password = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.authorities = authorities;
        this.enabled = false;
        this.lastPasswordResetDate = new Date();
        this.permisos = permisos;
        this.dni = dni;
        this.nacimiento = nacimiento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastnameComaAndFirstname() {
        return this.lastname + ", " + this.firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Boolean> getPermisos() {
        return permisos;
    }

    public void setPermisos(ArrayList<Boolean> permisos) {
        this.permisos = permisos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }
}
