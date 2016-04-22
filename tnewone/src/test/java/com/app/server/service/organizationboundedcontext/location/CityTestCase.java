package com.app.server.service.organizationboundedcontext.location;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organizationboundedcontext.location.CityRepository;
import com.app.shared.organizationboundedcontext.location.City;
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
import com.app.shared.organizationboundedcontext.location.State;
import com.app.server.repository.organizationboundedcontext.location.StateRepository;
import com.app.shared.organizationboundedcontext.location.Country;
import com.app.server.repository.organizationboundedcontext.location.CountryRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CityTestCase extends EntityTestCriteria {

    @Autowired
    private CityRepository<City> cityRepository;

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

    private City createCity(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        State state = new State();
        state.setStateCode(2);
        state.setStateName("FFq7qXCbaz4ktJkDbQ5Caxh1fz1QvcZn3PaS2T0dmPf6vq7LMI");
        state.setStateCodeChar3("RoJQFBd4WYBlPhpARvLAZvW658UV12g7");
        state.setStateCapital("T1WUXz3zuIIKNhNuyd2fuaeIwxecLF1xyDRbJg5zgKUgQKB1jW");
        state.setStateCapitalLongitude(11);
        state.setStateFlag("zbQMmhQzTqH0r01XMK9nRbmumLnFIGr0veym3YwlrIKcPWiT1k");
        state.setStateCodeChar2("PnnRw7LLE924qRFDtaeJ0tHO1n5aBIzg");
        Country country = new Country();
        country.setCapital("Q1ay9Bgvc5J6MIYA6SO1yKtb4wgPUPXE");
        country.setCurrencyCode("Wz2");
        country.setCountryName("MQRNrcrixisYywUwgG5sPouzVq0ma1OeIGd59tFCEDzDvwfFNP");
        country.setCurrencySymbol("hhKm4nZwYkwNh1m7FKmdQ4X6xToMMGfE");
        country.setCountryCode2("s6a");
        country.setCurrencyName("rIl6a1ikz4qQUD0K4qiaRWxWNmEyAAO86oEiPVi4Xx7R5Kd9He");
        country.setCapitalLatitude(8);
        country.setIsoNumeric(513);
        country.setCountryFlag("XcX0rxSBuYWULy6LmTdD88xmMCW4u2mQ0Cuq1yvtiYVb7bI66a");
        country.setCapitalLongitude(10);
        country.setCountryCode1("ckX");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCode(2);
        state.setStateName("F9mvqF2RmbDv4pgEvL7cGIFDlcFEgQUrbsiwSRW2QvZZXdzfoL");
        state.setStateCodeChar3("hFbiMhUDXqFbGSTTn3rygP73IHhtkZnY");
        state.setStateCapital("UULY3YHUfdVtM0wSzyDcdY7utHtuIZ4hQ7sa5P7IdTUoLUr1WR");
        state.setStateCapitalLongitude(9);
        state.setStateFlag("fKvxB1JVOxVwpv0y9eV9IqesAyheobuNvXRTUDEKuCdGKzE9Im");
        state.setStateCodeChar2("gcDZGCyzmuwEsYxZMr6KlFfSiXiUxkbC");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(5);
        state.setStateDescription("jIbkuQZI776xTwLUI85BfpfBSU7N7WQn1t6TxFy9S2BZwwxMnp");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(3);
        city.setCityFlag("ss0HIDL7Sh5OdAZaob26cxPOQrUMc74IuxypLFeuK7UgNilDGK");
        city.setCityDescription("Ek7uFBWzAnpHlX2iLj0XhHcmu3rdEETtiVlgJ6JgxQfdKDzMvv");
        city.setCityLatitude(11);
        city.setCityName("8cF7qWkSIyieXfWt9Y3u9vYTcUKhl5HnfEDGtkSuWI9kOztl4k");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        city.setCityLongitude(8);
        city.setCityCodeChar2("FmcS1aAl5v6PH2pdJJEpwG5Xzgi5VtU9");
        city.setEntityValidator(entityValidator);
        return city;
    }

    @Test
    public void test1Save() {
        try {
            City city = createCity(true);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            city.isValid();
            cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            City city = cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
            city.setCityCode(3);
            city.setVersionId(1);
            city.setCityFlag("jIQWLmLC0KjKngJODKFN9U6FlI0wlzrzHJlk2x8zLHbHahaj6j");
            city.setCityDescription("sN6HVQsBpBKLgICXmnZtakQA6fCR0OR26MgHwEmtEeFjF5x6Rf");
            city.setCityLatitude(3);
            city.setCityName("wC1IRfxJiaz2kmIx4AT6DEBK9FOCSddPqahAqRFJy7C5OZyjwW");
            city.setCityLongitude(9);
            city.setCityCodeChar2("cdmtWuos0JWRVS8plzrUGsWsXJiyLYCu");
            city.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            cityRepository.update(city);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<City> listofstateId = cityRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<City> listofcountryId = cityRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCity(EntityTestCriteria contraints, City city) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            city.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            city.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            city.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            cityRepository.save(city);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "cityName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "cityName", "57yAbU7Y80oGHi5KpB7eKdftLbIisylS8g1oQ3GfsTC55uTVHu0hMwxMOP7a7IROQ6U1pEKtsHNHIxqIhWRobU4ZmzzoEbEak7a2xZ6woBtF1PTv1PKVx8TKEXIDsabirnP4UFKHHqi2zvTeNGxravXMfYSGu66XUrgLfD3qe5udayne7YpR1BKBZ6wmGh2vSWNctrdbXvzAEWy9FeyGd6mvS92sjvUej4BFm4eS2fHqEm8PFEfcXGao9m7gpLNGo"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "cityCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "cityCodeChar2", "rWENpXjYM8gTzh4HytynUeQOjhjtknZzP"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "cityCode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "cityCode", 6));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "cityDescription", "XfP9HvYGy1tZ6SPs2SNw0aheqPgdcFwVc2maNMu7J4yvZ9ioGeuIRAHObQoB128bt2I7Qp44XhQmZOEOJ7ZBVSjfarNMX9dNEFGVf3iJivR3SzjWvsnyFOjHmDOX18iCH"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "cityFlag", "MeE7XTj413W3yE3WpiYRMxdLgQN4xp844BZr642XilpsfYxg5sRW3Q9TXBwKhWdLoOaiKRu9zW4gUhyr7YwihatkFMJJJOjqtP8BEIlBgyOhSpPvC7wCHbt933V8xQN7T"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "cityLatitude", 21));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "cityLongitude", 17));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                City city = createCity(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = city.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 2:
                        city.setCityName(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 4:
                        city.setCityCodeChar2(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 6:
                        city.setCityCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 7:
                        city.setCityDescription(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 8:
                        city.setCityFlag(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 9:
                        city.setCityLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 10:
                        city.setCityLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
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
