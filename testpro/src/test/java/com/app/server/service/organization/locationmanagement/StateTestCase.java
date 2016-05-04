package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.State;
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
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCapitalLongitude(2);
        country.setCapital("zlJZ9mgzYvro0CbuxsrP9GHJ8fxAJ7lR");
        country.setCurrencyCode("K4n");
        country.setCurrencySymbol("cMmZu1znizaX9bWoC8ZlJbLvWqchgMTC");
        country.setCountryFlag("rqvClv5pEXM2NmzMplCreAr5roEd6RIxolZDGktuOGeS9q6udy");
        country.setCapitalLatitude(5);
        country.setCountryCode2("Ztt");
        country.setCurrencyName("9NVkTTbIDG4aAa46AasOMeTYMDvM8XtMRuiZMXv2hYNDlg5k8l");
        country.setCountryName("l78bCOvzW7EIp0RHC7HQL62A3BkcQTtA2lp6o6rZ8KaszA7vIi");
        country.setCountryCode1("6wF");
        country.setIsoNumeric(768);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateCode(2);
        state.setStateCapital("IQJ2yYLdADIO8Oj5DMFicoP4HfEKQYPluW6hItPrbK1dwQOTmf");
        state.setStateDescription("QvliFF0yXX9aUtUHTJ871RNUSXr8HH6LNAYhtAgnVB10gjL9Hr");
        state.setStateName("KGid1lYJZWc2Vq8GgOOay8GGvG5XEoblAeDKEhagVXgUU70Dj9");
        state.setStateCodeChar3("0A0dzL33V1T9DjMT869FJvfN7InVMQtE");
        state.setStateCapitalLongitude(6);
        state.setStateCodeChar2("92bfNSVlwLchKSEa75YSFG5w4JoaK5tt");
        state.setStateCapitalLatitude(2);
        state.setStateFlag("VBuJAfFbkUUgXzAzOMKWI4GIhJDWF9qrWjOvllvxqAX0wH7Y0B");
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setVersionId(1);
            state.setStateCode(1);
            state.setStateCapital("aTpzVGyPcEIK2v5Gts7vuzNMsIQbfRILTO7Oy92BQn4XopZiUR");
            state.setStateDescription("S5Yq18MfMX2jLsKynlsZUw3DrcKN0XpE9YByO5Xc7LYzTiU22f");
            state.setStateName("7BDM46FZNeiLkllOnu2LiTL23VdVc4qTHxYqgjIfBb1ePJAgIM");
            state.setStateCodeChar3("f0XEmz22uZYmggzvkwJz7Q5sPY33VxEe");
            state.setStateCapitalLongitude(11);
            state.setStateCodeChar2("y2sus7P640xpc9deKOOj2QSfh9CWjMSW");
            state.setStateCapitalLatitude(4);
            state.setStateFlag("hhcyKa6LBwwANfQMjiJBjmIwrrjIXlyO2d2ho9Nurmz9QXlO4u");
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "Ydfh5qhggD91oXgEWJ0woxKopwXkNc7e4u5TQW8CCKRxBI3YvCn6AdVj5OQlx0EGeb8grgWmerpMW2zt4xiVrpwJamLxDQZces75QwPCEjcuvkjhsJUaGyMUzhu9MGYcprE8VzQVpz0DLuX4myuGaZF5Dpi9mPBPMwtTMBZb27ZKImVjpUuumJb67wmeB6oZ2JJPvIetmkUrItoRhoeFvIdyzlbK36mlWkloOS4FqjIUgzM8p1BYN2LmYvU2XlRV7"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 4));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "OC7oTnr0QyhWQrHGnpoppT0PzHIjQ98Bs"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "DCr7rnVD78TXIudlMo1dzu3V8n6eZCJJg"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "lNjbOMTFcksh9pukd0G5WT37QVet2EvYjvNr1ibp7fLSEkkijUMqORNg0XzPZ7GccWbjpeb1RUysszVvfaW1g0Udl768Lr64M2xLT9ciZCrDVPI0Db6wGvrYTZ6euyA1CyQ8XWAbvpUFaXq9OJQN2NfSHyatK11KNsFx1vDEU9fkj6VLY8RIKLzbGybM3oUnYzuFhZvBGQ998K6hU0rnVV7zYtjD1Ldv2hGW2XMe6IZgpGWxupu7SmpxN99s4IS53"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "97xXloH2jilrmP2SyFNd3n4U9v8GHP9KahbQa3Dx4z5e0dJRNfahD2XeZHD3fIgFCbOc4B7wLZcHAfOp67ksn9qQl6HGK1J3B1uHHcGsUQemY6iIEIdrrvY2rNYKfvsNe"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "kzVHHoMpHURZgyJpMtOGiwTv0F7jvqKg83XqEuVcGG1PfwBCn1QFCIvmHyaivIa9PBVBeHeeXSCrgFPU5Yxtd75qrt5JTA2s6s6EkYs5lZh6ywqL8rTthGzhcVaXSFhIo"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 19));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 18));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
