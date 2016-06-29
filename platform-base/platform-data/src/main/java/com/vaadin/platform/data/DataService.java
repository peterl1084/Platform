package com.vaadin.platform.data;

import java.util.List;
import java.util.Optional;

/**
 * DataService is used for reading data from various data sources.
 * 
 * @author Peter / Vaadin
 */
public interface DataService {

    public enum SortDirection {
        ASC,
        DESC;
    }

    /**
     * Finds an optional of single dto of given type that can be uniquely
     * identified with given id Object.
     * 
     * @param type
     * @param id
     * @return Optional of Dto matching given parameters.
     */
    <DTO> Optional<DTO> getDto(Class<DTO> type, Object id);

    /**
     * Finds all available dtos of given type.
     * 
     * @param type
     * @return list containing all available dtos or an empty list of no results
     *         were founds.
     */
    <DTO> List<DTO> getDtos(Class<DTO> type);

    /**
     * Finds all available dtos of given type between startIndex to startIndex +
     * amount. If DataService supports ordering the order of returned items can
     * be determined by sort properties and sort directions.
     * 
     * @param type
     * @param startIndex
     * @param amount
     *            how many items to read from start index onwards.
     * @param sortPropertyIds
     *            property names as string
     * @param sortDirections
     *            sort directions are applied on sortPropertyIds in given order
     *            by index
     * @return list containing all available dtos from given range in order of
     *         given sort properties
     */
    <DTO> List<DTO> getDtos(Class<DTO> type, int startIndex, int amount, String[] sortPropertyIds,
            SortDirection[] sortDirections);

    /**
     * @param dtoType
     * @return Total number of specific types of dtos available.
     */
    <DTO> long getCount(Class<DTO> dtoType);

}
