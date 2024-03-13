package cz.spsmb.dao;


import cz.spsmb.model.Gun;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class GunRepository implements PanacheRepository<Gun> {

    public List<Gun> listByName(String brand) {

        return find("brand", brand).list();
    }

    public Gun listById(Long id) {
        return findById(id);
    }

}