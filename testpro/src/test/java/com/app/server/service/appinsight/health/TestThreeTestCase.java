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
import com.app.server.repository.appinsight.health.TestThreeRepository;
import com.app.shared.appinsight.health.TestThree;
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
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class TestThreeTestCase extends EntityTestCriteria {

    @Autowired
    private TestThreeRepository<TestThree> testthreeRepository;

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

    private TestThree createTestThree(Boolean isSave) throws Exception {
        Address address = new Address();
        Country country = new Country();
        country.setCapitalLongitude(2);
        country.setCapital("OOsUL0pv47PilQtcj5l42Atje4W1ntNt");
        country.setCurrencyCode("1O6");
        country.setCurrencySymbol("K2SpiyRPSS44QiL7RoXnUzblTwTyId50");
        country.setCountryFlag("Gx5UcKkGkMzxMSNOwxUHjOmIAw6jVAeEdiRJIxFvIhBZc4Iy9z");
        country.setCapitalLatitude(9);
        country.setCountryCode2("C0b");
        country.setCurrencyName("aoNSfmOt0i68Df38bM2cgh3vK00z8JnVlikDocK8AYOjY5Ojmy");
        country.setCountryName("hsBK6ftMVm5ZlX8DyYDODdnR9aSYdi1t3Yeg9dkIXew8pqIegc");
        country.setCountryCode1("Ki6");
        country.setIsoNumeric(525);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("tDtZ7cMODnp3N3izGOvvnaRG7fq5MocRRC58dXhSbpZNsQ8Ojv");
        addresstype.setAddressType("ibgQL2lnTQdT77WSO4etX1nxdWGiHLleyNmqvM8gFuXiB2vWRU");
        addresstype.setAddressTypeDesc("A7nGUJX3csRtjZjs1fAHFjUK9RAfTDsSlZDMr2sCnz2UfwPcVC");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        State state = new State();
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCode(2);
        state.setStateCapital("OqkOEQxVKJKvl8trwVdFwzlKD9VXPxgmjt3NSWVM9jHUi7UGe3");
        state.setStateDescription("lDW6SrH7ibXlDPgZUTLdQC7B2bijsyaBMXkpTpvz9ilKv9l5VU");
        state.setStateName("SD3yhymcs0ZDJQQmZ5IaY4mL0qPAnyGPIxuSPf170EqKrW1A1C");
        state.setStateCodeChar3("osxWjQso93cVLZKZ73aRCI3oTNDc6Ivy");
        state.setStateCapitalLongitude(6);
        state.setStateCodeChar2("yCkKVyfEF4FZQxzaitbnWVeYEWAPiNcS");
        state.setStateCapitalLatitude(7);
        state.setStateFlag("wCNLBe4uSRggYlsosocFo404u1MmxzHtYsCtNJoHiXJrowkGMU");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("P9XQkrXv5JlTqoFACoqbxPfQJIA94hCD");
        city.setCityDescription("YOZQARRDUjuWhC4BDKYoKdgGT9EeUcf9npO8YYpmQJndtbgX6B");
        city.setCityCode(2);
        city.setCityLongitude(11);
        city.setCityFlag("Y75kIHVE6JH5i6p8ljmsCDcfdIvtr5BaF9zfRS7bTvn0g4jzlJ");
        city.setCityName("5RS5kmp3yogfHxr8gM2cD6KuNDNGA8RgqU081P4SE9Z0xRmYN3");
        city.setCityLatitude(1);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("bbqpm3OFFfWUf2MOeLr54RyRVzky96j2Ep0e7XvoxVZ4RzSIQt");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("RwXaap");
        address.setAddress2("0yjOw6NPhERYynCqemGTKR1LWVHTSaVNuKndisZxJHs9Yis3Ai");
        address.setAddressLabel("Hvo4eHkMndw");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("IkuwCrUZRi3AzZGlDz7IdmSQ65aNeXJYpiQP2mqVkViL0NuN8x");
        address.setAddress3("4TSrWuW148BfNYvq1BBVmhC52P3FS6tdwQHOce8ap7B1gSjnZh");
        address.setAddress1("UTJCMH8QCnxIcfktkpRJFHcqO6zg4AsryilshqpWrAmTPkCAie");
        Gender gender = new Gender();
        gender.setGender("Qq45TTKG77t4FHvi5W1URGXMVDitieDvurvrShQuvsLS0ChtXk");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        TestThree testthree = new TestThree();
        testthree.setNnno("cu9FPTGNgXTt81Vr1iOL46HatZ5pFvUcPmWWYJYt700y0l91Mz");
        address.setAddressId(null);
        testthree.setAddress(isSave ? addressRepository.save(address) : address);
        if (isSave) {
            map.put("AddressPrimaryKey", address._getPrimarykey());
        }
        testthree.setGen((java.lang.String) GenderTest._getPrimarykey());
        testthree.setTtnm("4SWsQt5aRRQ5tkegxMhvIAgusy3658Fp6OZpUqFbeHWfvwWItj");
        testthree.setEntityValidator(entityValidator);
        return testthree;
    }

    @Test
    public void test1Save() {
        try {
            TestThree testthree = createTestThree(true);
            testthree.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            testthree.isValid();
            testthreeRepository.save(testthree);
            map.put("TestThreePrimaryKey", testthree._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("TestThreePrimaryKey"));
            TestThree testthree = testthreeRepository.findById((java.lang.String) map.get("TestThreePrimaryKey"));
            testthree.setNnno("hB7D0nqT8eZlRgLdjcuRbyzmE39V3G3nwxX3ObGbrhrV91HTcr");
            testthree.setVersionId(1);
            testthree.setTtnm("YdMMIjeIhC6fWfSSUMNYFTzNR0unGBnUuU0cJJnKy5zXf65CyM");
            testthree.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            testthreeRepository.update(testthree);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("TestThreePrimaryKey"));
            testthreeRepository.findById((java.lang.String) map.get("TestThreePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygen() {
        try {
            java.util.List<TestThree> listofgen = testthreeRepository.findByGen((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgen.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("TestThreePrimaryKey"));
            testthreeRepository.delete((java.lang.String) map.get("TestThreePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateTestThree(EntityTestCriteria contraints, TestThree testthree) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            testthree.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            testthree.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            testthree.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            testthreeRepository.save(testthree);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "ttnm", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "ttnm", "swSX46PJqUbzh59creWzhh8BCwgDT5p5g1uwQ6MuaA6gAlbLE8v7ikrmI3U9d6kJtyHZVSRbrk3A30QjQ50sp5WOR5ekqOs8UjSncwUEfTfg4eRej2miCOhvKAnHGlzEk9Pz3Eb1Bl9BERSW39vIaaMXQ81WMjDf4nJcXmIeTfp0XZl189teEK38eGPWH12PPDB7zWVDV88sfKkvFlFzaykyhl6ZdUCMBwpJngxwKstMTE5BnTyxGe15RMIO5ikD3"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "nnno", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "nnno", "elrHlVPXw1LZGtZYJ091vaD7lSEvfqaVGBqedtucohD3NLsyGYH3lA0Wsvq0M5I1HPlIWADuK9xv2R0yhzW1Z2yEAZAnT7llmOOHTrGCCcrbEFqwEEje0dqWkfIxu6Mk218ZVfh520Uhe7KzqiVBrgHKO4zWKP7kTDTEGypySqoHuP5c95PtNmVDgS8BnyaFBzWCEemcFxoaDM8bGNqonIr9gsK47FXvKzrIf7zfq7MOBDFFYb7lajPGqbMDOO5SV"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                TestThree testthree = createTestThree(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = testthree.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(testthree, null);
                        validateTestThree(contraints, testthree);
                        failureCount++;
                        break;
                    case 2:
                        testthree.setTtnm(contraints.getNegativeValue().toString());
                        validateTestThree(contraints, testthree);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(testthree, null);
                        validateTestThree(contraints, testthree);
                        failureCount++;
                        break;
                    case 4:
                        testthree.setNnno(contraints.getNegativeValue().toString());
                        validateTestThree(contraints, testthree);
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
