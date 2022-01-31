package org.hsh.crm.ms.infra.dal.repository;

import org.hsh.crm.ms.infra.bll.model.LocationModel;
import org.hsh.crm.ms.infra.bll.repository.LocationRepository;

import java.util.List;

public class FakeLocationRepositoryImpl implements LocationRepository {
    @Override
    public LocationModel get(Object id) {
        return null;
    }

    @Override
    public LocationModel getByKey(Long key) {
        return null;
    }

    @Override
    public LocationModel save(LocationModel model) {
        return null;
    }

    @Override
    public void remove(Object id) {

    }

    @Override
    public List<LocationModel> listAll() {
        return null;
    }
}
