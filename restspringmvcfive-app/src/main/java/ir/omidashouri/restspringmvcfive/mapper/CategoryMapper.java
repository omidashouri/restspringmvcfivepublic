package ir.omidashouri.restspringmvcfive.mapper;

import ir.omidashouri.restspringmvcfive.domain.Category;
import ir.omidashouri.restspringmvcfive.model.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mappings({
            @Mapping(source= "id",target = "id"),
            @Mapping(source ="name",target ="name"),
            @Mapping(source = "categoryUrl",target = "categoryUrl")
    })
    CategoryDTO categoryToCategoryDTO(Category category);

    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "name",target = "name"),
            @Mapping(source = "categoryUrl",target = "categoryUrl")
    })
    Category categoryDtoToCategory(CategoryDTO categoryDTO);
}
