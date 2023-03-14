package com.alex.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "device_data")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Device implements Serializable {

    @Id
    @Setter(AccessLevel.NONE)
    @SequenceGenerator(name = "device_data_id_seq", sequenceName = "device_data_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_data_id_seq")
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private long id;

    @Column(name = "name")
    private String treeName;

    @Column(name = "modified")
    private Long modified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "top_level_device", referencedColumnName = "id")
    private Device topLevelDevice;

    @Cascade({CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH})
    @ManyToMany
    @JoinTable(name = "device_data_to_object_container",
        joinColumns = {@JoinColumn(name = "device_data_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "object_container_id", referencedColumnName = "id")})
    @BatchSize(size = 20)
    @OrderBy("level asc")
    public List<ObjectContainer> objectContainers;

    @PreUpdate
    protected void onUpdate() {
        System.out.println("onUpdate() was called for device " + treeName + ":" + super.hashCode());
    }
}
