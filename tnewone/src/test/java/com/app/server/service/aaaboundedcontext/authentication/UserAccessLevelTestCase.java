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
        useraccesslevel.setLevelName("kg4HaKN8bDHV0BSXAXCIzql7OCT466wcPJlc1PHkn2bAigbTDZ");
        useraccesslevel.setLevelDescription("lfCyrPRuxgbt3BTG2VwMYLHaDJErkz4OQSrCd5QWuRDxPZrPow");
        useraccesslevel.setLevelHelp("x4AhwTfSeATwgIwbn2tM9y4hBV9EqskVCxE8nBWt3oi9188siC");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelIcon("TDjASsbMVzPbQkpMNnjakqfM4U9mGTaPCNGKUSwBRP7qoE0CZC");
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
            useraccesslevel.setLevelName("Fi5wcPgVzQeSYP88HAFwIeKWBNhEx3gre8w5AunSV7nz5ZCxQh");
            useraccesslevel.setLevelDescription("emHuLtQk90cqZbSoEX4LUK9dpbxeFlB5UTUmaxnAEpzR93CId7");
            useraccesslevel.setLevelHelp("ShlzyuBJaPfkZkaPZOQksbnWDdayii1r75i93AlWu587wjzE3T");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setUserAccessLevel(11274);
            useraccesslevel.setLevelIcon("WrUFEki6rdFoxx7WPtLpPveu7T4JrbhvWxrQNrjq52T3lqp7Ps");
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 163757));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "5daRGXLnpmYORRvKUpeZiIY4NbKS7KFrWodhDFCJPbgn65vzh2H0aGSwlEkFRue15TMg3L3tPg1WdxQ3pYCZhK02kaRmdbSm92bFV8LKvvXow6sSL0kSKDbK2nI9nnbL9ndMZ8uCnz8XVsHpExrjQOH5ykLMjsKStXeRh8kRsUjlQFD3Vud5VvBHLx5DjMyIqThbz3CfRx4PLOzqFd3KrQIGKQpBloaQItoSFmZGbbVtdcUn3zfO08OsihUmriMYW"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "SfF3812oznDfVYjo6FuQLrDOkB8K5w2i4TY7JFbLy4ALtxPRkvmTo6bZtXkbaBe3MHFFuwgxwEgMjlXvM9ybYgGafsiMzA4kmbid6KohK6BFbI6b6jLuVvGoiqwGxV5eLNrrUhwPCniqZk7UZ3bmki3FmxvZw26PsG3FDShrIYZQgAU1YOeuWwDqNndicaYz3KpqYUPM8OIh0xaejRkNkv6pn8atQ4ZPy7ji3HcvRZPz6Bk4qd2TTBUltIR5TBUYA"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "VhEUUXwIVFG7e5ueMFuuJadQgjUNrFSmwYhVCI5cz5paUndZu12VIa5vnzlgkaYh6UTMygH0JmOm63LnZTxN8F5VRSXrj9FBHUXX4FWojWtRPLYXD3qEw5ZdAKTU9PDB9fAm3KrKTNVP5lw5Glev7ABoSz0SErO0KlorA3dYTTcEXEQRShsR7Dud4na6RH18NcCQudWjAKgG8NmYUDmrqnIyg0bGzQU98aBX1ON4GMNJs3A0hxRXEtNKe2FzLKQHZ"));
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
