package experiment;

import junit.framework.Assert;
import com.foodie.BaseSpringTest;
import com.foodie.model.Location;
import com.foodie.repository.RollBackForTestDAO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jdo.PersistenceManager;
import java.util.List;

/**
 * @author jim.wu
 */
public class TransactionalTest extends BaseSpringTest {
    @Autowired
    private RollBackForTestDAO rollBackForTestDAO;
    @Autowired
    private PersistenceManager pmf;

    @Before
    public void init() {
        super.init();
        System.out.println("TransactionalTest init");
    }

    @Test
    public void testRollBack() {
        try {
            rollBackForTestDAO.shouldRollBack();
            System.out.println(" t.commit()");
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Location> locations = (List<Location>) pmf.newQuery("select from " + Location.class.getName()).execute();
        System.out.println(locations.size());
        Assert.assertTrue(locations.isEmpty());
    }
    @Test
    public void testNotRollBack() {
        try {
            rollBackForTestDAO.shouldNotRollBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Location> locations = (List<Location>) pmf.newQuery("select from " + Location.class.getName()).execute();

        System.out.println(locations.size());
        Assert.assertTrue(locations.size() == 1);
    }


}
