package techsupport.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.UTILISATEUR;

    // Enumération pour le rôle de l'utilisateur (par exemple, ADMIN ou UTILISATEUR)
    public enum Role {
        ADMIN,
        UTILISATEUR
    }

    // Constructeur par défaut requis par JPA
    public Utilisateur() {}

    // Constructeur avec paramètres
    public Utilisateur(String nom, String email, String password, Role role) {
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role != null ? role : Role.UTILISATEUR; // Défaut à UTILISATEUR si null
    }
}
