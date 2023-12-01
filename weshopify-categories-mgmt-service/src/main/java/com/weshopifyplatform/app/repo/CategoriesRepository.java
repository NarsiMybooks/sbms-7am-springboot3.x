/**
 * 
 */
package com.weshopifyplatform.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.weshopifyplatform.app.entities.Categories;

/**
 * 
 */
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

	@Query("from Categories c where c.parent.id=:parentCatId")
	List<Categories> findAllChildsOfAParent(@Param("parentCatId") int catId);
}
