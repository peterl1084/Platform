package com.vaadin.platform.data;

import java.util.List;
import java.util.Optional;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

class PlatformDataRetrieverService implements DataService {

    @Any
    @Inject
    private Instance<DtoDataRetriever<?>> dataRetrieverInstantiator;

    @Override
    public <DTO> Optional<DTO> getDto(Class<DTO> type, Object id) {
        DtoDataRetriever<DTO> dataRetriever = findRetrieverFor(type);
        return Optional.ofNullable(dataRetriever.getDto(type, id));
    }

    @Override
    public <DTO> List<DTO> getDtos(Class<DTO> type) {
        DtoDataRetriever<DTO> dataRetriever = findRetrieverFor(type);
        return dataRetriever.getDtos(type);
    }

    @Override
    public <DTO> List<DTO> getDtos(Class<DTO> type, int startIndex, int amount, String[] sortPropertyIds,
            SortDirection[] sortDirections) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <DTO> long getCount(Class<DTO> dtoType) {
        // TODO Auto-generated method stub
        return 0;
    }

    protected <DTO> DtoDataRetriever<DTO> findRetrieverFor(Class<DTO> type) {
        if (type == null) {
            throw new IllegalArgumentException("dtoType cannot be null");
        }

        Instance<DtoDataRetriever<?>> selectedInstantiator = dataRetrieverInstantiator.select(new DtoTypeLiteral(type));

        if (selectedInstantiator.isUnsatisfied()) {
            throw new DataRetrieverNotAvailable(type);
        }

        if (selectedInstantiator.isAmbiguous()) {
            throw new AmbiguousDataRetrieversAvailable(type);
        }

        return (DtoDataRetriever<DTO>) selectedInstantiator.get();
    }

}
