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
        appmenus.setMenuIcon("DetiFjMZQfXY8eDMC9QKr2fsVcEMZ1HRHCh2511S9UUmy5HHNE");
        appmenus.setAppType(1);
        appmenus.setMenuCommands("LCy2qnh5qJcir5vKjEZbGkuGFkKrPcavblxT8mLhs5lE100DPW");
        appmenus.setRefObjectId("n0SQSJJ05KP2CtO0Cj3YTUvsmCf3WDdIy96kOLEVFvbckKox86");
        appmenus.setMenuDisplay(true);
        appmenus.setAutoSave(true);
        appmenus.setAppId("ZzqnHKuUu7oppPZa8egXeOpRpw316scdoMUuL1x6bPxgAw6GI4");
        appmenus.setMenuTreeId("yvwV7T1B2kOFMXDcwvb8OStZCkF0HS3pFmpqmIJfGll4gAG78A");
        appmenus.setUiType("Bxq");
        appmenus.setMenuHead(true);
        appmenus.setMenuLabel("t2kLzg8gLK6rHvzwG8ToVBuAaeY5r66bG0lmc5bLE5K45oKOWD");
        appmenus.setMenuAccessRights(8);
        appmenus.setMenuAction("MpsI6gX2UCzv8sfvyGrUKIqirHGQAcDegjCL0GGUtK1I1icqNk");
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
            appmenus.setMenuIcon("6Kj9E832xml2CXjhz7ASS8GMJhFdpaXnbWH6Yk7Ea1N6yyN7VW");
            appmenus.setAppType(2);
            appmenus.setMenuCommands("aPBbwncJPBziO8a2hFh5PFw11S9RMCuayM52igEoh5piCS337f");
            appmenus.setRefObjectId("UFNqKsC2QeecE21J6N3U4VlE5QFilLzB5Mz54JPMuo948CkR7R");
            appmenus.setVersionId(1);
            appmenus.setAppId("JUsOMSHWlk8gbLlVrJpswVkEHBOXe5fKK4hLgpc0QuydYyqTzv");
            appmenus.setMenuTreeId("I52RTOEEsTJjSfdLsTJ7MIGVXamk4bl6nGM1RMy6oqXoWxh3TQ");
            appmenus.setUiType("Zb6");
            appmenus.setMenuLabel("351FojhB35b8JwL3nxE5lq8gsdRi53qNGG5b3iQv1fHWOgnv4F");
            appmenus.setMenuAccessRights(2);
            appmenus.setMenuAction("Ztyn9inATGxBGLj7bz1nR9jpreHuI147mmaRH386ET0zGfOY4V");
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "La1ZKPCglcKrFDGYWle6tlMFIuGjyMOlYbOC8f3fpzSHDfUn49en3G329wWdmq2jYtIxF9FtUF9SboFQGlpHDgkWonUvf0BNxb3dfrDFl3BkONzWWDzREmRDoAI35zicyFl1zQ93T7mQg6BPrtyeg5syzHU6SO02Ls4ZHGKQYVoFrw32We8XX5GJpUyoNOOVZtN6ctGReu3K6AkP3Ddh2viVqwcoV4f6Ej234SoyCcLOU9MX2spd8YQp3bCf0UKim"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "3Yn4yEZfvmREmGI1zyXSqWKVJqgesUlGEVTzBDrPfwyYf9t1kvOwQZe4rSIsdo0VzRtzsqkSKDSsqa9lXwnsdpf2TU7tUD5PIX6DuOe7ZsRVaDVttm8XlC7A96m7IBMShJi7mCHa5MoUUMmBqmc8njiXNIGnCJONi87PlX35obnHZMWg22VrQ4ihvb5cK9nSjTTob6uBe81MyOQxwt5kKGfgjl6aoQkEnGaVks4jRNQhHo28wSHgodfSptx3XUcRB"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "1FjRcArTrGGbGi1zzcUp234qjB4v6jHkqsYwP6XNacBb1qDJ8muLzBL7E2MgjnRyIg2p6QCB5UBnI44CDvtrLhDDK4DQkfBvGSpKnRCBuzZoiBouuBQWnSWe3BwSQhedNYZq7o3gfNTSlvORn55M8GfirWgBqfPpoRGWgxWiOCXGI2aK8Au2nTZBE1ZqRvDob4Jl3hacpv7rDktbrguhfbQdOZfzYNvbm6E3G2FKk6l19HMZU1CMBYwVVvNtaZoSl"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "mMfe790zuHOAmXkUdKbA2Na1A0xEgsdHxGc1wB561s4dNOyFffNXTkLE0AHLcjGKO"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "oYlkeUH0Bj3UxwuosTc8Go5gkocp1gvTJod0iawCCP3NFcbC3Bwjh7JVljAb6zsB3bmu9Emn3JMw3uHBY69Srgs6VhvgqnG763NYTVBgzvvpZmSWGb85L8RMo8ibVRtQQdhsXYWGd53XHJI96KNqDYH2XEMEK1dFxYVqAzTIyibCSdNNkKJEhxU2vFOHfReAjy0ZccJRUBE2y4Hz3C6fGfDNkZoYwtOYNqA5pg8wtctbpFYRrqevd8MnkdLTQGdGe"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "HPIX"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "OHE2M3ll2YuXHMaoYTyA4vbYPDmYP87FgjBW2MT2sXngAiDL1okXaXm6zqZf10o6n6kVFvBcjxr0EUSXUnUUuGn2IidZWS6awQwt1Iv5RifuLX064ghvcxV2YjZmommqM5VF5oVncg0QeaMLLLo23lNnJHh2MdGMpNceUZrYEHL2WHi3zWys2Rm8U2sKH0EofiLNY2m4OFGZ9I62S5MkcWIf6N17WWLX4EKkmBkF0TG20yLwe0J0mM4Cg6Wt4JP3b"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 15));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 3));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "e4T9A5cfv4dYhnB6ntiAkC4wYagTJz3aBQRVURl9uel1lsuqAT0aisvUH4a5pu1LRmlL46Yg8X1iTo7u2WibvpR2WRbfqJTF5MRWJDWBK3Dq7dqTVfOBhVampAhyR3WuyEJSQf6eSjY9QrNfYp52PTqxaTRRz6nwmfPGcMyXaNYUG7szRUeA1Sl0rDSvUHcv2ahRSx40S5bA9TRLtNw84pnK17WYWml0qBTIIV2Te2hBMx9xyxJbwfILPStggB5Yy"));
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
