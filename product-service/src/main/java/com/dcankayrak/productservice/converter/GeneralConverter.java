package com.dcankayrak.productservice.converter;

import com.dcankayrak.productservice.dto.response.ProductListResponseDto;
import com.dcankayrak.productservice.entity.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GeneralConverter {
    private final ModelMapper mapper;

    public <T,D> List<D> convertEntitiesToTargetEntity(List<T> list,Class<D> convertTo){
        List<D> resultList = new ArrayList<>();
        for (T item:list) {
            resultList.add(convertEntityToTargetEntity(item,convertTo));
        }
        return resultList;
    }

    public <T,D> D convertEntityToTargetEntity(T entity,Class<D> convertTo){
        return mapper.map(entity,convertTo);
    }
}
