package com.hmy.staybooking.repository;

import com.hmy.staybooking.model.Location;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


// LocationRepository extends ElasticsearchRepository instead of JpaRepository
// since Elasticsearch has a different query implementation than MySQL
// But similar to JpaRepository, LocationRepository also provides some basic query
// functions like find(), save() and delete().
// But since our service needs to support search based on Geolocation, we need to implement the search function ourselves.
@Repository
public interface LocationRepository extends ElasticsearchRepository<Location, Long>, CustomLocationRepository {

}

