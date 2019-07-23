package by.koshko.task01.dao.repository;

import by.koshko.task01.dao.factory.DaoFactory;
import by.koshko.task01.entity.BasicPlan;
import by.koshko.task01.entity.InternetPlan;
import by.koshko.task01.entity.Plan;
import by.koshko.task01.entity.SocialPlan;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class PlanRepositoryTest {

    private PlanRepository repository = DaoFactory.getDaoFactory()
            .getPlanRepository();
    private Plan plan1, plan2, plan3;
    private List<Plan> plans;

    @BeforeTest
    private void init() {
        plan1 = new BasicPlan();
        plan1.setPlanName("basic");
        plan2 = new InternetPlan();
        plan2.setPlanName("internet");
        plan3 = new SocialPlan();
        plan3.setPlanName("social");
        plans = Arrays.asList(plan2, plan3);
    }

    @Test
    public void testAddPlan() {
        repository.add(plan1);
        assertTrue(repository.fetchAll().contains(plan1));
        repository.add(plan2);
        assertTrue(repository.fetchAll().contains(plan2));
    }

    @Test
    public void testRemovePlan() {
        repository.remove(plan1);
        assertFalse(repository.fetchAll().contains(plan1));
        repository.remove(plan2);
        assertFalse(repository.fetchAll().contains(plan2));
    }

    @Test
    public void testAddList() {
        repository.add(plans);
        assertTrue(repository.fetchAll().containsAll(plans));
    }

    @Test
    public void testRemoveList() {
        repository.remove(plans);
        assertFalse(repository.fetchAll().contains(plans));
    }
}