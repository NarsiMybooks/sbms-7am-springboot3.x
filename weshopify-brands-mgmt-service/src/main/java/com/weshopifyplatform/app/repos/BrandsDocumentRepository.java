package com.weshopifyplatform.app.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.weshopifyplatform.app.entities.Brands;

public interface BrandsDocumentRepository extends MongoRepository<Brands, String> {

}
