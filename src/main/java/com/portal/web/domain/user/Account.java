package com.portal.web.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@NamedQuery( name = "Account.getByName",
        query = "SELECT a FROM Account a WHERE a.name =: name")
@Table(name = "accounts", schema = "public")

public class Account {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long usrId;

    @Column(name = "name")
    private String name;

    @Column(name = "pass")
    private String pass;

    @Column(name = "game") //if column name differance with var name
    private String game;


    public Account(String name, String pass, String game) {
        this.name = name;
        this.pass = pass;
        this.game = game;
    }
}
