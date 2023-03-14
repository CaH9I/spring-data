package com.alex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "object_container")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ObjectContainer implements Serializable {

    @Id
    @SequenceGenerator(name = "object_container_id_seq", sequenceName = "object_container_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "object_container_id_seq")
    private Long id;

    @Column(name = "name")
    @EqualsAndHashCode.Include
    private String name;

    @Column(name = "level")
    private Long level;

    public ObjectContainer(String name, Long level) {
        this.name = name;
        this.level = level;
    }
}
