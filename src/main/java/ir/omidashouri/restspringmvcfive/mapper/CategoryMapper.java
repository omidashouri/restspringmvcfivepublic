package ir.omidashouri.restspringmvcfive.mapper;

import ir.omidashouri.restspringmvcfive.domain.Category;
import ir.omidashouri.restspringmvcfive.model.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
