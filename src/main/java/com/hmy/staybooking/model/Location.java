package com.hmy.staybooking.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import javax.persistence.Id;
import java.io.Serializable;

// Add the id and geopoint as the private field and the corresponding getters/setters to the Location class.
// Add the Elasticsearch related annotations so that we can create the mapping between the Location class and an Elasticsearch document.

// database name is loc
@Document(indexName = "loc")
public class Location implements Serializable {
    private static final long serialVersionUID = 1L;

    // primary key id
    @Id
    @Field(type = FieldType.Long)
    private Long id;

    @GeoPointField
    private GeoPoint geoPoint;

    public Location(Long id, GeoPoint geoPoint) {
        this.id = id;
        this.geoPoint = geoPoint;
    }

    public Long getId() {
        return id;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }
}
