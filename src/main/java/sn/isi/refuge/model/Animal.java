package sn.isi.refuge.model;

import jakarta.persistence.*;
        import jakarta.validation.constraints.NotBlank;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Column(nullable = false)
    private String nom;

    @NotBlank(message = "La catégorie est obligatoire")
    @Column(nullable = false)
    private String categorie;

    @Enumerated(EnumType.STRING)
    private Status status = Status.DISPONIBLE;

    public Animal() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}