package com.vaadin.platform.data;

import java.util.List;

import com.vaadin.platform.data.DataService.SortDirection;

/**
 * DtoDataRetriever is the concrete mechanism for retrieving data from
 * particular data source. The DataRetrieverService will discover based on Dto
 * specific configuration which DataRetriever to use and will assign particular
 * retriever implementation for fetching the specific type of dto.
 * 
 * @author Peter / Vaadin
 */
public interface DtoDataRetriever<DTO> {

    DTO getDto(Class<DTO> type, Object id);

    List<DTO> getDtos(Class<DTO> type);

    List<DTO> getDtos(Class<DTO> type, int startIndex, int amount, String[] sortPropertyIds,
            SortDirection[] sortDirections);

    long getCount(Class<DTO> dtoType);
}
