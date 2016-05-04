package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;
import com.athena.framework.server.helper.EntityValidatorHelper;
import com.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.athena.framework.shared.entity.web.entityInterface.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;

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

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            String logManagerId = LogManagerFactory.createMultiLogManager(_path + "/");
            LogManager Log = LogManagerFactory.getInstance(logManagerId);
            System.setProperty("LOGGER_ID", logManagerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setAppId("wDAOGphIPY2GA8rUOPENao94pfGxFGJQe6nmzCaLGXY6NxKoRW");
        appmenus.setMenuAction("M8Zfyhxan8Fp8hkeP9bXTVvTLRP8LSu9vfNfUpZXiqgNJLqtCW");
        appmenus.setRefObjectId("GymraD5M4mKMSBNtqxAfTkTS0BfSYgExYdcjq0AkijBuWuYbOd");
        appmenus.setMenuLabel("nfJNSTAOTYw5dhJOt3uR2xiGx6aIyovvaBrAdkRzuAna9Bp4m3");
        appmenus.setUiType("MOV");
        appmenus.setMenuDisplay(true);
        appmenus.setMenuAccessRights(6);
        appmenus.setMenuIcon("YjRS2oEXDZx0Npoy45WT3iIFqFNjpsU7j9F9POSGeke2PMhZWf");
        appmenus.setMenuCommands("n8btYFfGFd8liW23L1f1Cxqr1dM0iebzhDDMVIm4xzNZykcQUX");
        appmenus.setMenuTreeId("rE6XkOlIyTB3jbjJTUZOQremYxGam8teEthscqGt9o0pzFkrbc");
        appmenus.setAppType(1);
        appmenus.setMenuHead(true);
        appmenus.setAutoSave(true);
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
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setAppId("Ls66ZTOH0v2lwpfFb7HorTnGwGmTPSauiARWlzc1IFaWwJics6");
            appmenus.setMenuAction("HoRmkftrrboIiS8LaGUPK6hf8uP9SIJrPoOJQZZ6dgoyYeSUtI");
            appmenus.setRefObjectId("AqN7OjPM2g52uCtrx2So6gIRjdNhnjt2k72AnMYiYhGG0Szel2");
            appmenus.setMenuLabel("AhzvVwUQOMYmWZzRhpWchszxy5zUERmZqzoWxlA7y5DXgYyvan");
            appmenus.setUiType("jgG");
            appmenus.setMenuAccessRights(9);
            appmenus.setMenuIcon("vyffhNWITZ9wEhMNm7Wp0kOBBSHlQqe3YauOQWa833n6eHnQrT");
            appmenus.setMenuCommands("IEdqbpfjAE7oYlDkQQlwP67Fi56MB2wCLHF3EpgugsaR0lCk8z");
            appmenus.setMenuTreeId("Xm6CDjrXyCMmyKB4zRald6ivtdQTevuB2Lf7IwtYAUuNQ2slvE");
            appmenus.setAppType(2);
            appmenus.setVersionId(1);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "YWHnEFNPY1OiXLqVYlhZDKQu1WOx2sK84bqIZqRumQzfLO4NVLyQMFWUHmWyYOGR9uK9XNFDaCBjJcAsV10GOUYJ8caP8tLnsiuli65IyAk74Yu1m5qZZhW34oOtUfi3ZrtBHQkMkFxhpINuzb00EHpQgpqLrqPWNJrXE0ekBr9AjlqxcsEqlxwaDMJqXYX92DzDLnv49fqBtVXqI410za6uNJpW9uEVOqlVrIAshoPpmTauNsIDSgCuBgtFlEKYz"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "88lVTKWdTt0RGqwj51h5onCHcsRwwa47bTNJh3ia1OO1OYpUS756k3XPv6pImvtNFQ05kH7iPuNB3JN2NzdqlxFGUs5EkFxN0ZgQqLf89MKcmWnxtZo8WTeKyaPBDkC2QdLy3B7HYLzkkMiXQnHZ1xVGY6AwvNu9epyBKFnHgz2CvFekew669cqKAKPexmMGcbIqRILg9Uyokl52p5KsRyrGZmMUkwWCraHD1x8YFpCHsp5hTGs4RSrAnWsb9fOb3"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "tgljSYJVz9xHsGjfP1DxGMue6wEHZFDUcyBahVnVivxUrLZ3NXON4YUQipMeFFAOhysTZRARPd449MBGt1qV3a50YgQrSc1r5ouUyWtlhipXvOCtluegkClgDZbuubmh3vQihXhfEae9jN4zqEkMRa1YNvFll2s5x6StBliO1A5KlBOGHGnn05B5bcdVu7o2CZ5P1o7g2Fz0URYfe3tdeELQG1B61PcWPDO5QAV4RK1QvXJcGldzv8EViRmR2ynj8"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "5LXXrYp0Jpy8dUjBl73WSglTme3uCEabSY8TrLUvqfWRZt8rkWll96RkvE7QMnfnY"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "INLLwSQOumq3OI6OZ5IsLryRpGOsKbtxtT43vJGzdsEStVg4LVuufrbhwOoWf7xIhj4LoUupJRk3ugbN2zSpBX0hiWlJcHryXJcIbIECQbu2d1cSYHslO7YwsgrzdIguJsVSOOKiTQ2fgwW5CUAOwlnDdcSea61TMiqufjwdgW3CestgtAjQp61MSkmqCn7N8PXi4wKLyv6Jy5N3BXZwbLb2aiXTWzcnKHokn1KBz8H3qkSDqSh18wuKOPLcGoGOS"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "OLz8"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "f7S8X239xuiLHdaFFwYNXsynZSgjzO4xh3Xgq3gnfX0fVzFvViZUYo5TkD8gwkWQNHJ67LQrvqULN6Kmm4cWDJVJ8QdyFdsJZSk9WHxDvcfdNu5NROd5TXSvERziq2rnu2eGthPuQnkgTkzNHmpi6MUEHsJFHdKcywj21ffwdHUOGHNUQ43LTVsu8eqKdgzBAdrakWk3zZVu2lottKDG6iVzlTMwceOahXL7Wmvl3qbkf04ofEY4XD15vWK4ajQaR"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 14));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 3));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "0iY13GILXxjJitRjm1BsRLaFENdzGwnhfQ6PnjsYe1W6sF4W6TfWu8qSrZAP04fvv0BBIq94NRycFGSFRt9jGx1tkBLQ4neLGIuzIpRbFXs9ntTbFZONnAzcD0nnzw5TolF1vDWv5mruY10edBrQvr0WjNYYKQdj6OpDJzIEBFYF8WxrYunguAQiDcdRXFugbKw7QRZ9CXiQkez3iVbM3a4bp8IBAR0WHAVYeB6MWumU9Hk96BSbo1YenCcFJkAC3"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
