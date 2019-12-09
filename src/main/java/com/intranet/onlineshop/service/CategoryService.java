package com.intranet.onlineshop.service;
import com.intranet.onlineshop.domain.models.service.CategoryServiceModel;
import java.util.List;

/**
 * Class used for declaring the business logic methods regarding the Category
 */
public interface CategoryService {

    /**
     * add category in the database through the category repository
     * @param categoryServiceModel the service model representing the category
     * @return returns a service model of the category
     */
    CategoryServiceModel addCategory(CategoryServiceModel categoryServiceModel);

    /**
     * find all categories
     * @return returns a list of all the categories in the database
     */
    List<CategoryServiceModel> findAllCategories();

    /**
     * find a category by id
     * @param id the category id
     * @return returns a service model of the category
     */
    CategoryServiceModel findCategoryById(String id);

    /**
     * edit category by id
     * @param id category id
     * @param categoryServiceModel service model of the category
     * @return returns a service model of the category
     */
    CategoryServiceModel editCategory(String id, CategoryServiceModel categoryServiceModel);

    /**
     * delete a category
     * @param id the category id
     * @return returns the deleted category
     */
    CategoryServiceModel deleteCategory(String id);
}
