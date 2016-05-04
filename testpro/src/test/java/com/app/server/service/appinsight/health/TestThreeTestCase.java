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
        country.setCapitalLongitude(7);
        country.setCapital("mvc24tJ7EY3xPb2i9oXNG8vL9HPATBgg");
        country.setCurrencyCode("SQV");
        country.setCurrencySymbol("8w74EkeHaqmAMajyfcYGWTOsXHfvWgR6");
        country.setCountryFlag("O385PGKeHVuEsQtjpmCbyWkRsOjed8bmQnSiWssUKoSGuAd2dh");
        country.setCapitalLatitude(10);
        country.setCountryCode2("81k");
        country.setCurrencyName("LVNsIEbPnoP2bWbhZm8P8MP8fX6FzYCEnhro9dOQN4B75GJhpm");
        country.setCountryName("xard7QmaYYNYAY1qbujccdCfWfhs7FhQr27ckOWg94gH4xWJBs");
        country.setCountryCode1("uam");
        country.setIsoNumeric(801);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("WOGjxLJm2okRBbMp1ygFDHjHBYDpKyr51tUvysfIfn5Ra6I1ku");
        addresstype.setAddressType("pHNgrdow84Jx0LeDnK9JHdIG8w4X0butFhpkNaNnE5KjhVvBxo");
        addresstype.setAddressTypeDesc("FrSDUttOcHzbMscPyPhtFFEV0dthomMXwxdvTUu7UZPDHwEddF");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        State state = new State();
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCode(1);
        state.setStateCapital("4zVvOV6ivBF7VjCDnwXJPakSV6GvVrt5ghhWyFcowqmtpSSa6W");
        state.setStateDescription("izVQsuRRAXVWyqvTM72j5s29InocvnrnTCFecFlQGORecNFhII");
        state.setStateName("iwsDfr3ymwLlh5azjRKOLGqNV7hQ84UE68G71MlZygYldO1qW3");
        state.setStateCodeChar3("ILuu31gpb9ebD01b1CS5RpgJ24lqdo9y");
        state.setStateCapitalLongitude(7);
        state.setStateCodeChar2("TIsCjkMV0ouq9v1GNY1dwrCcaVw3X3S5");
        state.setStateCapitalLatitude(8);
        state.setStateFlag("35Xk3bhrxLj3L4ekcTOSTIAwFu9AmwObFGqu0gxOMe5Vs8Q3Y1");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("pNwB14OEoms0YL7Ha2GWJIPzfA7BuZLf");
        city.setCityDescription("kJpQYcoqTvbXNy3jDvEEwTIbY0CFY5QuBSwwbsT0Rrhga9wGB2");
        city.setCityCode(3);
        city.setCityLongitude(7);
        city.setCityFlag("wuW1TaUvCFQtterCVPAohghLiZKxlh9lv8rrjFAeZsyfhSDAl0");
        city.setCityName("tZK8SESar2cOsR7rxrYwdLnKWk0RRAONkOar8x3UrjWpjbFgHE");
        city.setCityLatitude(3);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("Xxd36RgwTI6WocMYsgwsYCZVeaNdQyUZ34clPrQQKLdNo4Gkru");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("YGClCc");
        address.setAddress2("KJhXCFhyVXRME00JGBujPOhrMYECa5Mj5C5XuHoG7w5819BSRZ");
        address.setAddressLabel("gtelRSFWvJ0");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("bp9IU4jV2l2CDovd4YDXUIyQIm5mdVD0iHKJRvNj2LL1s5oU0z");
        address.setAddress3("6pTlP4XPDZ8vtd1Q8HaC0lMJDxppR8UwqR49GsT86AQsUsVY1m");
        address.setAddress1("HcW8ddOVLOkmDzyxyQxBGj8n3xiiCtLUHET6exaucRoeICgnPX");
        Gender gender = new Gender();
        gender.setGender("VeRKYdUfpb3CkQG7diMFoBnrKqC6M2PG91EzCzu3GJHF5408mR");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        TestThree testthree = new TestThree();
        testthree.setNnno("u4KOPtchdEYnOhMqZLfUyzAPNbnPoLlbsYWn2DYwDxL9GCgXAQ");
        address.setAddressId(null);
        testthree.setAddress(isSave ? addressRepository.save(address) : address);
        if (isSave) {
            map.put("AddressPrimaryKey", address._getPrimarykey());
        }
        testthree.setGen((java.lang.String) GenderTest._getPrimarykey());
        testthree.setTtnm("ODNScPNDARVQaMzn0pKeTpyOl2OAl9yPrMxdMQjmRfDbeiQrxM");
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
            testthree.setNnno("ROUWV47Xza9Ls5rc8hPXUjUEy0PSwv7CQb9ydIis7NIBXi2ZQ9");
            testthree.setVersionId(1);
            testthree.setTtnm("Sz3JaW45b9kW9JbHMddpIqHESFHC7ICxuIdVBaJP06LLxIwk1d");
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "ttnm", "G5mLTCxmCkDxZ03V62UeXeeeVrrROrAPTDJc1jdAUnJf9fbVOa38C6HenIKlHHp4clwm3jI2PvVPN3mQwCVqgLZbXlrdkomDTg46s4AMVpEJ5UuX3rMMzz2WdB2nWqrvn5wP1Z3HpYRJVKVWETVf3TS3UyXVRJ4tjpbJyrWasZrAUTrLDfYDET2PhVrIP9fUXT5hTjhBO1qAOwBREGPkZA8dOdcw4XcYPj3xxbNTnEcEmvyHmYR6IUXdogav5n5od"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "nnno", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "nnno", "9PBKbc1fw0t4ScJm4ZH5NbaclW7yvllTiqiaXRgayVs06Qd6NKsNCpwmJOqVaUapKwE7FC1Kh04pH7jaB9idt16n8hWk87UONI45MBqCqeBe9JsQZbLfaJOfZDIlo0nd5ONgBYItCPOHOjjqWjgjjXsEFUSm00kLtFcIls0HPzv4pbmYbXepcZVGwtm0sXumwxY9gqlqx7oeH7b3uJPqknxfnoe9mIIaF1dhSEGD9M3Xjj6uyMOhKpqn7vCHKa9R8"));
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
