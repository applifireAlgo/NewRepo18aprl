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
        useraccesslevel.setLevelHelp("5tD8KrVIN9qphPrgjwCBQoEDHR7nYKwb3xQLqn0bwdh7OGzpus");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelIcon("sYOpTsUy7zWKyLmtDmnYj7RwivECrEms2RYEQWyjK9o3xRN08x");
        useraccesslevel.setLevelDescription("KzPUBxuoyZarAja8crQITRJTxAisXmHaBUIRPUHzEybRtsmLmF");
        useraccesslevel.setLevelName("iD8XpCPDaXOy9eimL7WN4K0zcPjcnP3Mr5L42vW32RVAklG9gg");
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
            useraccesslevel.setLevelHelp("iHszADmvrgL9Hz7soxW4BKoGKJ85PmFkrUrvZm6Z6LSzMbfVy7");
            useraccesslevel.setUserAccessLevel(47602);
            useraccesslevel.setLevelIcon("y5eCdficB3jJcUBIs1s0TsXmDR6Qp9aej0hLTmGYyWNs1J7Wfv");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelDescription("MzWVo3ys4bFiO2tU9bOiQwFar1LGjLxhRk9UvV6mEFs23qHK4U");
            useraccesslevel.setLevelName("wyNXP5DOe9bgZ7cVJukSYBmUz8QgEYSzzE0UtQ41d8ESekqvnk");
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 158324));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "2ZsgTcAHo9siQCEfMAjLmY7AfiWfxqmw3YqK9000g4M0ygDq94h27FJytb60XMD4d2nhxTszCHhV4Bv763gyaKOoRtg5vRuz5UZFb9p4cGVGfCvcygYGLn2pfjCRVYeoSvz0VgsizwGTh1sifj3gsDkuBtCRPBkFbesrhKqw0XYT2X1zorbKCsFY4yqdFFpLVs5pl4ZYMNdumciyWmqbjRV6LO3tP9u5UCV0cgU0etSY79ssr1EHvINhz8TLMFsvu"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "LIc6rsWMebaXQqhweEbQgp4JSPaIXBrISLUiN2QDWaMurpwKIeZmpEc3LuRBhqWNcW3wFbOQgTPfMjbjIaB8SkdOdjSwvkvK4PJgfRZTucPxJxFsFQw9Iwl5BK5pLHyugqYTRtYzzm2pKDp6JeLtHbMcmmiLnHvxvpAdjWLhsJjNRJ1uKNUAVhTsjj4879PcNDsMf7AlIbl10F12J7paqZLOZJ4ONVP6Z6Na1PLH50qhF3uh7o1LaAl2DGSK8swvD"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "voGmIrORyQzQ7KEdNzlP78vtjUScESJ9q921BiHxuDZg7za2aDJRDDG14Y3IBgQL5BS35ItfCr5Ycdc89mpjmQA4FKp0aiM9R6oX9UevKC43GeT00qG0egae6018bWBZIolYP0Rn1NqceXGker4KO6jTLj5x50TxcdPgbBnHCOkbBJpkcLcqVqlJqiKKBEJ7Djv6CA1CxqgvPVDwMHxLizn9Q9Q7l68TGTvMvVYuAPRV0O2mqKpVdMPJm4zVkWeGQ"));
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
