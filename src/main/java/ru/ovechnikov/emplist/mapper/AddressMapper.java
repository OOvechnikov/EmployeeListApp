package ru.ovechnikov.emplist.mapper;

import org.apache.ibatis.annotations.Mapper;
import ru.ovechnikov.emplist.api.request.UpdateRequest;

import java.util.List;

@Mapper
public interface AddressMapper {

    List<String> getRegionList();

    List<String> getDistrictList();

    void updateAddress(UpdateRequest request);

    void saveNewAddress(UpdateRequest request);

}
