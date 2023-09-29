package com.fitshop.model.mapper;

import com.fitshop.model.binding.OrderBindingModel;
import com.fitshop.model.entity.OrderEntity;
import com.fitshop.model.service.OrderServiceModel;
import com.fitshop.model.view.OrderProfileViewModel;
import com.fitshop.model.view.OrderViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface OrderMapper {


    OrderViewModel mapFromEntityToViewModel(OrderEntity entity);

    OrderEntity mapFromServiceModelToEntity(OrderServiceModel serviceModel);

    @Mapping(target = "productBrandName", source = "product.brandName")
    OrderProfileViewModel mapFromEntityToProfileViewModel(OrderEntity entity);

    OrderServiceModel mapFromBindingModelToServiceModel(OrderBindingModel bindingModel);

}
