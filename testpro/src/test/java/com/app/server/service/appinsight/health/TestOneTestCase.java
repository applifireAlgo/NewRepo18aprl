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
import com.app.server.repository.appinsight.health.TestOneRepository;
import com.app.shared.appinsight.health.TestOne;
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
public class TestOneTestCase extends EntityTestCriteria {

    @Autowired
    private TestOneRepository<TestOne> testoneRepository;

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

    private TestOne createTestOne(Boolean isSave) throws Exception {
        Address address = new Address();
        Country country = new Country();
        country.setCapitalLongitude(8);
        country.setCapital("ISe830XTxfyzzswEdR1S3YOSQz5wrn6a");
        country.setCurrencyCode("TaN");
        country.setCurrencySymbol("47Cb4YcNriSPfMB1nQiUkYzFBENC4sgy");
        country.setCountryFlag("WE0BIJhucrBPR230nZ1NT7HmVDj3rL7tnD1qAPfkAwimEoMgpr");
        country.setCapitalLatitude(10);
        country.setCountryCode2("W3R");
        country.setCurrencyName("dIY5EdGVo9EXsYrgzNF27yzFmu9OJVZYRFVex9qXm7G6vzZLoM");
        country.setCountryName("JdQHmW1xzCQtt9UuXQTEOkOLvXlnjgyBtvu66RlrNxGtuvGWVr");
        country.setCountryCode1("AsH");
        country.setIsoNumeric(756);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("5llHJzo13Jz25p6N2kic7EyV999fSRgcWfFjtVjTr12FlS6xKH");
        addresstype.setAddressType("co99e8sPQSLTSQ7r9WXr51s94CDHh12bFShVUzkLmCWm855M81");
        addresstype.setAddressTypeDesc("dQ26h0XZEFGLvrX8p29zAjHT9ImQ1wYPTB34QxcPEkAKmHKzLb");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        State state = new State();
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCode(1);
        state.setStateCapital("gSI5MxNjc9sB7u72cvbuO0RQDZB1G7ooo5OMSeTSBW16jhJHO0");
        state.setStateDescription("USS9PcF1pAK1I8XMH1f898fj6oTYFenDUYqOHHR4svlFPgyMmH");
        state.setStateName("6rkatdr2wxX2GYu8VSuT5OqolwqargLFRAfubwnnh31izjRF6t");
        state.setStateCodeChar3("cmuN32BkKaTRBrX9yI42vLd2ltnsz2jN");
        state.setStateCapitalLongitude(2);
        state.setStateCodeChar2("36SFxW11rSex8ig0UH83BZLkDfKTAgEb");
        state.setStateCapitalLatitude(11);
        state.setStateFlag("8XjgiO4JkMJHwtn5vntbnQGb1C3532EAx9vtNRhEPKuyMXbB9O");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("nrHiw3mEiTeLTPR3tk5Q7fDnZbSPrVbO");
        city.setCityDescription("6IucMC9TyPk5Dk3Ld90Yo7hzD2QdOY3wgaR3NtQZudsy41M1ZW");
        city.setCityCode(2);
        city.setCityLongitude(5);
        city.setCityFlag("dxbH8Bd8U8PTegT4bvJr3ro3lnr5DGXlP08bUkC4yYTUxgzrbi");
        city.setCityName("Nb0GxWuuew2tj1qPtZ5Ua21svfmr7LUlfTbL5T7Z8KE14xsFHG");
        city.setCityLatitude(4);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("ybo0ByXJdnxZUiLnod4dKEdNcMEvxXODf2YcCgpmWWbIpyz99e");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("FQ0KBB");
        address.setAddress2("nbNCNwQmSsGkSYreG5T8tqBc5ZMX3aCWYmed64voMNqeSiJKow");
        address.setAddressLabel("ea9WuOJa2ag");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("7stODKJelTus7vkI10wxU961ThFJLugW7FGTTxa8SzwcyuAySj");
        address.setAddress3("F04K28KNrciWF8r0LKqPtFi38uYx51UlmDuDuYBhxT4LR4QDHo");
        address.setAddress1("kkcd7TZ3oH9Hdz68cQMSkSIUvfkYnH5prawTmmnTLmeUcuxCUv");
        Gender gender = new Gender();
        gender.setGender("75MdlIBZ3o0warJStqZxfGQLkjUDCFd8Q07fM42vYOwAG1YsC3");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        TestOne testone = new TestOne();
        address.setAddressId(null);
        testone.setAddress(isSave ? addressRepository.save(address) : address);
        if (isSave) {
            map.put("AddressPrimaryKey", address._getPrimarykey());
        }
        testone.setTno(2147483647);
        testone.setTnm("584nobHEorlnBzdsVWYAae7s02c7FOfG9AsJmFbxfi8EKI9Jxm");
        testone.setGen((java.lang.String) GenderTest._getPrimarykey());
        testone.setEntityValidator(entityValidator);
        return testone;
    }

    @Test
    public void test1Save() {
        try {
            TestOne testone = createTestOne(true);
            testone.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            testone.isValid();
            testoneRepository.save(testone);
            map.put("TestOnePrimaryKey", testone._getPrimarykey());
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
            org.junit.Assert.assertNotNull(map.get("TestOnePrimaryKey"));
            TestOne testone = testoneRepository.findById((java.lang.String) map.get("TestOnePrimaryKey"));
            testone.setTno(2147483647);
            testone.setVersionId(1);
            testone.setTnm("9dNkiFsdb76tlwCWWghd3uSfPXDItkXEdjs6FyZ94EbGBWy6Fw");
            testone.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            testoneRepository.update(testone);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("TestOnePrimaryKey"));
            testoneRepository.findById((java.lang.String) map.get("TestOnePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygen() {
        try {
            java.util.List<TestOne> listofgen = testoneRepository.findByGen((java.lang.String) map.get("GenderPrimaryKey"));
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
            org.junit.Assert.assertNotNull(map.get("TestOnePrimaryKey"));
            testoneRepository.delete((java.lang.String) map.get("TestOnePrimaryKey")); /* Deleting refrenced data */
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

    private void validateTestOne(EntityTestCriteria contraints, TestOne testone) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            testone.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            testone.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            testone.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            testoneRepository.save(testone);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "tnm", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "tnm", "GEjwLkVRzBLdRAeDqQrP0wdYVeOUyNWGP3DDCNgfOIo8ZjAoRCN12LvYEGv4Ip1yK0CUeTlJB8d3hIZX0Edqp3frBmUMVMonU9JtnuALkpjX16bcLJoNQLfbIC5J7LnnNYA98Gfs1mRb2EM7IuvuhPtoD8usgqwG9D6Q2qLnRYESgPFXLks7rSqMNoUra3WZR40XHDAOC1eNDywql6exn64E8MKsQnZ1n3JjAKWtOxYVHAFOsElphGJ5doyzbsfip"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "tno", null));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                TestOne testone = createTestOne(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = testone.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(testone, null);
                        validateTestOne(contraints, testone);
                        failureCount++;
                        break;
                    case 2:
                        testone.setTnm(contraints.getNegativeValue().toString());
                        validateTestOne(contraints, testone);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(testone, null);
                        validateTestOne(contraints, testone);
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
