package com.fitshop.model.mapper;

import com.fitshop.model.binding.ProductAddBindingModel;
import com.fitshop.model.binding.ProductUpdateBindingModel;
import com.fitshop.model.entity.ProductEntity;
import com.fitshop.model.service.ProductAddServiceModel;
import com.fitshop.model.service.ProductUpdateServiceModel;
import com.fitshop.model.view.ProductDetailsViewModel;
import com.fitshop.model.view.ProductViewModel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductViewModel mapFromEntityToViewModel(ProductEntity entity);

    ProductDetailsViewModel mapFromEntityToDetailsViewModel(ProductEntity entity);

    ProductEntity mapFromAddServiceModelToEntity(ProductAddServiceModel addServiceModel);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "name", source = "name")
    void mapUpdateServiceModelToEntity(@MappingTarget ProductEntity entity, ProductUpdateServiceModel updateServiceModel);

    ProductAddServiceModel mapFromAddBindingModelToAddServiceModel(ProductAddBindingModel addBindingModel);

    ProductUpdateBindingModel mapFromEntityToUpdateBindingModel(ProductEntity entity);

    ProductUpdateServiceModel mapFromUpdateBindingModelToUpdateServiceModel(ProductUpdateBindingModel productUpdateBindingModel);

}
