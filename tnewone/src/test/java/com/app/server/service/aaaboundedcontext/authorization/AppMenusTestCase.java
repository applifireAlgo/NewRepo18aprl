package com.app.server.service.aaaboundedcontext.authorization;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.aaaboundedcontext.authorization.AppMenusRepository;
import com.app.shared.aaaboundedcontext.authorization.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuTreeId("HZTWu1kJEtFfLZrQXWXz4Evjq0apPoE8fYyUxQhYDHiIrYXblF");
        appmenus.setAppType(1);
        appmenus.setMenuCommands("c9Ik1KZ9aO3KwN7a6zfiXH8feclzs4D9ZSQZkMY1eEnOwRr7Ld");
        appmenus.setMenuHead(true);
        appmenus.setAppId("0BuD4HkQhACdn6SNmjCEUxnR7okIPISHHZQ3Am46DsFsQuAg7n");
        appmenus.setRefObjectId("nTZOeLCwe1BTk8IHJXUonTjcn7LGJ3eagwqUM3aIaD7iaoobxO");
        appmenus.setUiType("vPX");
        appmenus.setAutoSave(true);
        appmenus.setMenuAccessRights(11);
        appmenus.setMenuAction("y9NuMwmmsFqJcZAQpkm2JXAHBHQYnKspnCnUu86vFKFNtuHvZu");
        appmenus.setMenuIcon("bU48gOMl2JxHIFExglJjXFKFAIc49woklgT5tCtpVkHmZs2zyn");
        appmenus.setMenuDisplay(true);
        appmenus.setMenuLabel("imaWo1YeNIdroOPiPPuZKAhiHQyJ1F4miosnhbfqWwAHaSRk0W");
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setMenuTreeId("F4X0YZmoYvZDYhaj8fLdGgpfWwM7LchaCZMtTq1CotaJGhtFiD");
            appmenus.setAppType(2);
            appmenus.setMenuCommands("bbKbTSclUIq8pk8thi55fT9CAqpghNfaMa0CmnlAs88z7dW6M1");
            appmenus.setVersionId(1);
            appmenus.setAppId("EXTUpdj4dbtax7CPYnG5JW97xjUp1huFIXsVQfxh6AKSrpQAWH");
            appmenus.setRefObjectId("ZcVt7wvH8umcfgjvI67HFwEA7MSpAROzf6cyfq3Ca356R8tPIK");
            appmenus.setUiType("Mmh");
            appmenus.setMenuAccessRights(3);
            appmenus.setMenuAction("Ty7Jxewwm1ffpoxrQ5gtRhDR87Vbs7XrGTeKp2A7sFdN5i1TLf");
            appmenus.setMenuIcon("YSEZQGK3tY8oc6F43PkDU3qI74apZ4UshKJtkOvMp4mmILoVon");
            appmenus.setMenuLabel("94WyYdZ7l528cQTHTkuScSRdNEriBi3B5lRegEBzRjhhengoWB");
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "9eU1zea1qgcdCce3ZfzTht4YVTtZUGug4lGwhCKldjQ3HypzfhaV0pYPb6FxzCyC2vrTu6bbfpw5eURZbEnGEUZBWyZEb1zabLruZz5iyHFaVD6eg3WgcIF0nJJe0chOb2g3IAf4GTiqCS0Ncs9soRwksWrNDLgHDmUYYFcA8uWT80UEo5fMFxdwwBYLv1LsfVZmNhX3G5rzvMhwVihKJ0x3B81g6DHvxS3qRrGvW5GYGsz2IuPm9tc2C1Ypvjv16"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "WWyWFLhpNNBHvX1MJbxHUS6RGd8m55taB729INWOXqR8kTV0rYTQV65xGHTkKrvI4kS8uIYLc6IgaEgBmukMqptS7A178fGRlXKo4Big2nipKVm7shHD0WTQXq6OgFLCNZpD0zV8rCLFSeGtu1yRTTwCG2MBDBzHIjnFI03pBUjwbBSDV6dEUQ9oqLowJGkGhtwWfH3pffrjK78Ini76GebYcVIHj4scJ9fNjas5xjD1aJdZzT2HweknGIy23whq0"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "ZuSHElZb8B0GbwiR5scxeHAY8vJrpQ5MmcKjnGAffemRuAeeKmJ0KFpa2t0oLRqMBx13aFjpoKP1GvVsketfB6qGRKL3C4Hw4VNicenE1HthqMxUH0UHUN2JPIuiVmWO7oCExqetwqVGTq3Fm6ojmTfRcqZhXmgI7KTkAHrePxeklVy0rtoZlsT0nFCSHBcimKEE596oG2EQUOrSE6omrf58paABRer00TcX6BeW6wQmNGbPSAXuHRFpOLDwhBj68"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "DUCKSISnZANF61qKda3eUUxOGo6cvzHVw2JQJMLvpphkcarUsP81J2Bd9PzNpmVQ2"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "SlTRk2xxrsTwt8mYGucTkTW9Fe2HbwwJ4ZHslLR7PN9dLDoNVN3uErujCcTj4ZJku0mF36nhtcmjvgLlgk2FHl5M2OCEVRHPcP62KFHchOoC0maWzGarcJfirHJ0FK0xYlHm7MVQhLiS05hSs0uwGQnViIuvz3cikQ3kUkzP7UH4eX4GXnh5w0SFmRArg2oya5BAUHOPvGvveiW5wQw9tbYG9p86PYkeZ2fzzfqtbvn8zKqLSGXPSPbdrT8q5Nyom"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "zqQu"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "WlHpgqjpskc6lw3MY1a5bwJp03GPirwrzx37euxxiOGgIv4ettnOrT5N0eMnYejLqBsJ0RPo2GN1WPXU6WvuwAM3fjE1euuHSqGZjWexcdYPT3qN2SGmI68k169X3PO46GbhjMqLAWnGzQwFe7drul9kV38Oat1vA0q4J5pxYzLdtQ6B36IrVNP44L1wWoaxe8t65Ax6dwWCbfqvH1zNZxc2e9aV4YMLbwM8VtuBzYu6E1NqpoJVx2oNQlD1WB1Ev"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 19));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "pkxTreFAzt3Vo8yD62WO2qD7mISdySts6tXlvjpwbxXomzHWOa47OcvPIg68ewNEHxjWpqW1nP8M8HyLqr432ig3Zfrglqb1ce2SmH8k6c4AUxPCaxYd0T3DalDAIM3q876Cq4GT2mXRH2iGVFGKCY9oJHQ8c2WtlmhTfkcQz7peYOoG6KkwIqlcXj0likkRHB5DM2ph4plwR6iIJOUOu7Cdg0rilVA3xT6V8rjk2KzOFjy8OfeHec5G28Z0qXIQ8"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
