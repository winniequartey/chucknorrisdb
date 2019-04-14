package net.djomeda.tutorial.mutualssl.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import net.djomeda.tutorial.mutualssl.json.Element;
import java.io.Serializable;
import java.util.Map;

@Repository
public interface ChucknorrisdbRepository extends MongoRepository<Element, Serializable> {
}
