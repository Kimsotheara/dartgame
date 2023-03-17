package com.dartgame.dartboardgame.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title_description")
    private String titleDesc;

//    @DateTimeFormat(pattern="dd-MM-yyyy HH:MM")
    private Date timeToPlay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamer_user_id", nullable = false)
    private GamerUser gamerUser;

    @JsonIgnore
    @OneToMany(mappedBy = "game",fetch = FetchType.LAZY)
    private Set<GameDetail> gameDetails;

}
