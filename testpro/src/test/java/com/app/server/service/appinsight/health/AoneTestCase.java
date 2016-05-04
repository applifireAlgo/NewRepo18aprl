package com.app.server.service.appinsight.health;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appinsight.health.AoneRepository;
import com.app.shared.appinsight.health.Aone;
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
public class AoneTestCase extends EntityTestCriteria {

    @Autowired
    private AoneRepository<Aone> aoneRepository;

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

    private Aone createAone(Boolean isSave) throws Exception {
        Aone aone = new Aone();
        aone.setAno("amCMgnGaaFLbdmlA55FDNWp8vJwZapUZBgmYxFGuUGINC9hkZR");
        aone.setAaa("Vpa6yrUvqECviZoGLEH6lkyJOTatiC6Qj3E9Hw5SKg4wv8Xaaa");
        aone.setEntityValidator(entityValidator);
        return aone;
    }

    @Test
    public void test1Save() {
        try {
            Aone aone = createAone(true);
            aone.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            aone.isValid();
            aoneRepository.save(aone);
            map.put("AonePrimaryKey", aone._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AonePrimaryKey"));
            Aone aone = aoneRepository.findById((java.lang.String) map.get("AonePrimaryKey"));
            aone.setAno("HeUCOE8HyF18TVRQojJyq8HDBeIJBHleeFjTfJwwupBlkHDIJ2");
            aone.setVersionId(1);
            aone.setAaa("3f35BX2rLnJFecM6E0YCQqHg2heVaFPV9iKA0Q2sABrd2iSagQ");
            aone.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            aoneRepository.update(aone);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AonePrimaryKey"));
            aoneRepository.findById((java.lang.String) map.get("AonePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AonePrimaryKey"));
            aoneRepository.delete((java.lang.String) map.get("AonePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAone(EntityTestCriteria contraints, Aone aone) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            aone.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            aone.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            aone.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            aoneRepository.save(aone);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "aaa", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "aaa", "qggfQyTRufU7ZHL8xxR0lYlQlkt8n9WEX2wjScT1vh3SvzCIBNuSQY3FDB7S15VGC8Tk2XBUILjQ1D5GKfjaNi1C7t3wnrRAzCkpav2FhPsJy5F3U28zKM6HAfVe7qJT2V18Jvyhj3rYpO531LYfHZ6XqEX8iKKcPcqa4XjE2bD6cFjRn3fbpgIyJrm1uSskWQDEExEqCSYZVmpo9cnPyhzW93aJLRCF2cqqusA06iiWgjMXhKjOgiJQvuVPA3dMH"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "ano", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "ano", "dYnVZO7MnFfe6shbnRVlQLSlBj15s887LEmVhdJbcN8UFvczdwEdianTt2asNBsGFk5lsFoABTgMeRhdCiL98zczZPcqLNcExUEk6tXg6W7aefCnixoThkI9q5oiEPEcbk5P3ZP01XEm62MZA1fAA7OuDoxQ5HV0tm6BpSr08sN30V64eYQzB0HrhyDG1Ru8orHGy5tflGYqqgLCmr17YGY0X7q9amK0Hh8TnEa7v6lx4mtNDRbBM7sGaXs5caOqI"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Aone aone = createAone(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = aone.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(aone, null);
                        validateAone(contraints, aone);
                        failureCount++;
                        break;
                    case 2:
                        aone.setAaa(contraints.getNegativeValue().toString());
                        validateAone(contraints, aone);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(aone, null);
                        validateAone(contraints, aone);
                        failureCount++;
                        break;
                    case 4:
                        aone.setAno(contraints.getNegativeValue().toString());
                        validateAone(contraints, aone);
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
