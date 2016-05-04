package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelHelp("U7NupSBf6N59Jbculq5XRyLlgnU2VaX6RaoTZyTXxMvZiYPtwW");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelIcon("h3hlONO89DCxMwWATLacNx9AbMMgnYmQqFrGKLrel5oePDHNfx");
        useraccesslevel.setLevelDescription("nPKsbYBR5aXJ6aM428mwaul1YggsSrHU2k60AqjgAk69dIFkLs");
        useraccesslevel.setLevelName("50VYwTHVi6yfHctQKdbqcjN2gcptBPE6x6fUKT1DBi3cbD4Syq");
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
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelHelp("2koPywz4NRfZffYRF3zo1h4YvYNVnRgfoSgYj5M9DDNmjqLmcz");
            useraccesslevel.setUserAccessLevel(18027);
            useraccesslevel.setLevelIcon("AkWtJfJqsVCzbWhp46yE0LscD7nm1zpUAVIQf00YNkLzIRq1sh");
            useraccesslevel.setLevelDescription("Vn50qPioZmWdpopIB8PFrji1RHJbhwbkiW264tw2Xe49ZvT3oA");
            useraccesslevel.setLevelName("VP3rCoRfRnr4pTZs0pMYpHFHLBb5eospohkzxbJJzAvsN6ilFr");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 114579));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "2z9Ny4JML4LkPvSvpwBkE2fsGyUWJYTAPwkrAUkCINjfFBLOMmlMQjGmgpkHOitgjVKxckxmUUib8cs7wpJlUvGTzyp7XjOhionz4KQ9JVuMZbogk8yrmpx7o8DdFYWvZisZPtmeEGEVNVIIRSw5XmhXoYXfgMO4htawtoA6pjEEAy0g3ZjPzbcKKG8j7yQaq5Y2OGSfBhkXN7U2HUGpCgQwtv2KWFIcwYTDqipn96TZ84hhAdQr66aCD17s2WK1O"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "uc2xJuhCKwotRc85zbYv8tPqyt1joAn8wzBhHHxwF7tsK6WrInZ7561DsJRx1GVq5pJKX2lVDW699peR5XfQOvtnKGj8ECJJVmuII6vIzGRYrROoxPQaxWGJo630SsSnFGHHRo6uPEIBGvQ9eQIHz21jekphz0zJjcLTVaLCpGv6XwlJA8XTpLrCK2IpynbLCWju6MpTnWV7xUMnoDG9SRs58QsOVNfE2pfx1JMB53g2dnaoOCgHvmcrT3QCALdyU"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "0WxdLgvw0v4HB664QtZs1aalrPpKIokK1shEPvE16a33rkyKEvPNOZfV60YesChpvvEyY7f0qIwqwG2EyZcOEunqB6WXakj16OF4rfMKApGHYveKUZCq0S4qJANOflKkRhwAtLF9lm4RUtFsSOpQJ6gDw0gmfmnWaZomScNjhJaInF36pdB8YbEVULOHpviARhY1zLievTEV5l0no810eIS1P5Owa8FiKNz87JbRLQGwvnEAAJR7vmkpcbrc3m6pd"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
