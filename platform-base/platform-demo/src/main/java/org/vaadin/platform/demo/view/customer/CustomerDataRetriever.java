package org.vaadin.platform.demo.view.customer;

import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.vaadin.platform.data.DataService.SortDirection;
import com.vaadin.platform.data.DtoDataRetriever;
import com.vaadin.platform.data.DtoType;

@ApplicationScoped
@DtoType(CustomerDTO.class)
public class CustomerDataRetriever implements DtoDataRetriever<CustomerDTO> {

    @Override
    public CustomerDTO getDto(Class<CustomerDTO> type, Object id) {
        CustomerDTO dto = new CustomerDTO();
        dto.setFirstName("Peter");
        dto.setLastName("Lehto");
        return dto;
    }

    @Override
    public List<CustomerDTO> getDtos(Class<CustomerDTO> type) {
        return Collections.singletonList(getDto(CustomerDTO.class, 0));
    }

    @Override
    public List<CustomerDTO> getDtos(Class<CustomerDTO> type, int startIndex, int amount, String[] sortPropertyIds,
            SortDirection[] sortDirections) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getCount(Class<CustomerDTO> dtoType) {
        // TODO Auto-generated method stub
        return 0;
    }

}
