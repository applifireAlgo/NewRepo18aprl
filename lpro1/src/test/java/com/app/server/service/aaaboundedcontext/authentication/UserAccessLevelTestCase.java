package com.app.server.service.aaaboundedcontext.authentication;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessLevelRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessLevel;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;
import com.athena.framework.server.helper.EntityValidatorHelper;
import com.athena.framework.server.test.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.Before;
import org.junit.After;
import com.athena.framework.shared.entity.web.entityInterface.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo(1, "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
    }

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("E5EYQgwSsbo7RG2VbIq3IpY3eknzoWCwlyWvQkBFtdfmSyzHFs");
        useraccesslevel.setLevelHelp("YwGVczbA673tJQW5fnGNT5CnevN23TEPTyw8uMz3eSKZqbxxYS");
        useraccesslevel.setLevelIcon("dcIuDELCDuIlnITMWBcyDxjaaG49oewzjlrHPfMiQSELqntvNm");
        useraccesslevel.setLevelName("E42NKC4sU7RjrOaiOgETRoqohNNzB1YHtwe0exCtKPQn9Bdr5H");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelDescription("h4zqO578eaWV607K4tJ5FFwLf9XK3e27K1prAnHNznLUyJ69fv");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelHelp("YxtRmbcxmAHo9Uj7fGE1bY8Y6qdNtxtKQuRlNIHO9S17W6WHdh");
            useraccesslevel.setLevelIcon("pCjRJP1cUosSYbHV6DaT8WEbuZ05ral6NnsArdlHq8zv0DdOuZ");
            useraccesslevel.setLevelName("7dwz5n8kdwa4x8lfOpXYBmcFXyp1hOPSDuMXZ9C8VXfWbXTAtl");
            useraccesslevel.setUserAccessLevel(92469);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 164250));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "TRLSnVVTRIUOQOY9YKtSSR5g6BI31xp3e44jQvfVcOuCJkSTXpdCPVlohKC4AYmu7u7hWB3UNG8PGnAuW61SWfnduqe6ehcGkSoVWKAWtUqIKeag74GDGrePahJqROszqcPcQVrpIs8KNMxHPOXPeHXLhOfuMY2htLZfZZWKdAyDWf1BmRoLPkarEDrOqre5XwvkRMFLOuNJtG8VqiasKUxbzU47dcaYmkdgfDDU6NIOnPwQ7L248mhWLrGUL25bw"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "ewu5m6i7v68uAxGZJQWyzdk8GJBjq8iDwweIKWNo9eo32WI6PxF8OxRH4WN7vFfvlzH5MkmvkGfJ9wfqwTz9UfNxvcxnFrIK9nEJcPFIu5L9uwUTfENhzuf0m78i7A76ae4lPB8ZaFRA4AP7gsWVNA56W3wydAzL8mxMAuDSClr9V5KWCnA79HdLuJMaNmhV3dQRrJo9Eku97xJm0vQaeIaHz6N4GipU67YL0SiwRqzBdCx3nRBGP8uavsBZmRFqF"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "bzIgHf5DI7LIihTZBVE9q1YKxvPZAM1v1L3JhM0tEi9J8gUAOT9JB91Fi9IOmOpJX27CVUQn6813A2G1Uso3EZwzzWdZn9mgx2mA9LudXyz9EDoeGN85ZkwlYrD5rlbcDuMyrC1twjRPYkHYChuLvghZpjgaqLKiVspbWxqNxT3tfDwlpJuA4uWp95NUfxwX9dD16Z02WtspVcgN6k3DSmWbQWa0QJ2Ij59N6ChfAlI2Q7hesFo4tOsrkBVwd5Lv7"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                }
            } catch (SpartanIncorrectDataException e) {
                e.printStackTrace();
            } catch (SpartanConstraintViolationException e) {
                e.printStackTrace();
            } catch (SpartanPersistenceException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
