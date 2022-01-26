package ru.ovechnikov.emplist.mapper;

import org.apache.ibatis.annotations.Mapper;
import ru.ovechnikov.emplist.api.request.UpdateRequest;

@Mapper
public interface WorkTimeMapper {

    void updateWorkTime(UpdateRequest request);

    void saveNewWorkTime(UpdateRequest request);

}
