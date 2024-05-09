package com.udea.serviagenda.dominio.citatation.model;


import com.udea.serviagenda.dominio.service.model.Service;
import com.udea.serviagenda.dominio.user.model.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "citation")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Citation {

    @Id
    @Column(name = "id_citation")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Date date;

    @OneToOne
    @JoinColumn(nullable = false)
    private User client;

    @OneToOne
    @JoinColumn(nullable = false)
    private Service service;

    @Column(nullable = false, name = "date_creation")
    private Date dateCreation;

    @Column(nullable = false)
    private Boolean isAvailable;

    public Citation(Date date, User client, Service service, Date dateCreation) {
        this.date = date;
        this.client = client;
        this.service = service;
        this.dateCreation = dateCreation;
        this.isAvailable = true;
    }

}
