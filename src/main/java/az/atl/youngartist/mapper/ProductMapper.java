package az.atl.youngartist.mapper;

import az.atl.youngartist.dao.entity.Product;
import az.atl.youngartist.model.dto.ProductDetailDto;
import az.atl.youngartist.model.dto.ProductDto;
import az.atl.youngartist.model.reguest.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
        Product toEntity(ProductRequest productRequest);
        ProductDto toDto(Product product);
        ProductDetailDto toDetailDto(Product product);
}
