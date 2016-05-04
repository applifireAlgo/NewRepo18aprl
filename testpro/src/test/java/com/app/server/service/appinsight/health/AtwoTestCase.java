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
import com.app.server.repository.appinsight.health.AtwoRepository;
import com.app.shared.appinsight.health.Atwo;
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
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AtwoTestCase extends EntityTestCriteria {

    @Autowired
    private AtwoRepository<Atwo> atwoRepository;

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

    private Atwo createAtwo(Boolean isSave) throws Exception {
        Gender gender = new Gender();
        gender.setGender("ab02cTYLqSxyf27thIIBCqmHgPXMb0ZeBdL6U6DHsHzC7p3jTe");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Atwo atwo = new Atwo();
        atwo.setGfg("KsQY0Ld94PQaGlReoKB3SjdEGFZvLVJU04f4uelH3QKW607fjB");
        atwo.setGen((java.lang.String) GenderTest._getPrimarykey());
        atwo.setEntityValidator(entityValidator);
        return atwo;
    }

    @Test
    public void test1Save() {
        try {
            Atwo atwo = createAtwo(true);
            atwo.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            atwo.isValid();
            atwoRepository.save(atwo);
            map.put("AtwoPrimaryKey", atwo._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AtwoPrimaryKey"));
            Atwo atwo = atwoRepository.findById((java.lang.String) map.get("AtwoPrimaryKey"));
            atwo.setVersionId(1);
            atwo.setGfg("4ApahEzfbhisdvvA1WgF5D6pJrnrXWytN3uZgcga6oafj3uaeO");
            atwo.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            atwoRepository.update(atwo);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygen() {
        try {
            java.util.List<Atwo> listofgen = atwoRepository.findByGen((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgen.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AtwoPrimaryKey"));
            atwoRepository.findById((java.lang.String) map.get("AtwoPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AtwoPrimaryKey"));
            atwoRepository.delete((java.lang.String) map.get("AtwoPrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAtwo(EntityTestCriteria contraints, Atwo atwo) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            atwo.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            atwo.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            atwo.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            atwoRepository.save(atwo);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "gfg", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "gfg", "krztvsHPt13k5OLC3vQM7fw0JeKd7wCo8IC3eTjZt7MnpecIljdA5XJ9ytm7ljTd33lbdiiljuLX8URnsxGsMROFHBZgOnL5ZiJCFKA1m18NEPUl4COULE7LYN0Y1Dmvnoilqxh2NakJou0HIeMEJvE9RLE3vnnD3nLZVOVRS5aWXNwvkydcEvdm3Z3pMJMPdA7o7aoIbcj9ATQfA2h7XmtNefCyBCxcEup82Wtj8Z6EOysFn2l8VJ0tgv8czVBwd"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Atwo atwo = createAtwo(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = atwo.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(atwo, null);
                        validateAtwo(contraints, atwo);
                        failureCount++;
                        break;
                    case 2:
                        atwo.setGfg(contraints.getNegativeValue().toString());
                        validateAtwo(contraints, atwo);
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
