package com.udea.serviagenda.dominio.service.model;

import com.udea.serviagenda.dominio.user.model.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "services")
@Getter
@Setter
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

    @JoinColumn(name = "employe_id")
    private Long employe;

    @Column(nullable = false, name = "creation_date")
    private Date creationDate;

    @Column(nullable = false)
    private Boolean isdelete;

    public Service(String description, double price, Long employe, Date creationDate) {

        this.description = description;
        this.price = price;
        this.employe = employe;
        this.creationDate = creationDate;
        this.isdelete = false;
    }

    public void setIsdelete(){
        this.isdelete=true;
    }
}
