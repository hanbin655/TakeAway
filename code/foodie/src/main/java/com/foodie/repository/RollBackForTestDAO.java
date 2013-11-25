package com.foodie.repository;

import com.foodie.model.Location;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jim.wu
 */
@Repository
public class RollBackForTestDAO extends BaseDAO {
    @Transactional
    public void shouldRollBack() {

        Location loc = new Location();
        loc.setCity("Quanzhou");
        pm.makePersistent(loc);
        throwException();
        Location loc2 = new Location();
        loc2.setCity("Xiamen");
        pm.makePersistent(loc2);
    }
    public void shouldNotRollBack() {

        Location loc = new Location();
        loc.setCity("Quanzhou");
        pm.makePersistent(loc);
        throwException();
        Location loc2 = new Location();
        loc2.setCity("Xiamen");
        pm.makePersistent(loc2);
    }

    public void throwException() {
        throw new RuntimeException();
    }
}
