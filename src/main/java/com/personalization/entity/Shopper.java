package com.personalization.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


@Entity
@Table(name = "shopper",schema = "public")
@Data
public class Shopper {

    @Id
    @Column(name = "shopper_id", nullable = false)
    private String shopperId;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated;
}
