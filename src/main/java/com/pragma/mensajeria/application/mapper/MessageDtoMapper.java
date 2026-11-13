package com.pragma.mensajeria.application.mapper;

import com.pragma.mensajeria.application.dto.request.MessageRequestDto;
import com.pragma.mensajeria.domain.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MessageDtoMapper {

    @Mapping(target = "destination", source = "destination")
    @Mapping(target = "text", source = "message")
    Message toDomain(MessageRequestDto requestDto);
}