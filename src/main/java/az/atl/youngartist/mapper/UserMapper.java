package az.atl.youngartist.mapper;

import az.atl.youngartist.dao.entity.User;
import az.atl.youngartist.model.dto.UserDto;
import az.atl.youngartist.model.reguest.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User toEntity(UserRequest userRequest);
    UserDto toDto(User userentity);
}