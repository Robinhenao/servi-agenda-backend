package com.udea.serviagenda.dominio.service.model;

import com.udea.serviagenda.dominio.user.model.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "services")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Service {

    @Id
    @Column(name = "id_service")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idService;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false, name = "creation_date")
    private Date creationDate;

    public Service(String description, double price, Date creationDate) {
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
    }
}
