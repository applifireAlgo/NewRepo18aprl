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
        country.setCapitalLongitude(6);
        country.setCapital("rsnK1q760FApxjZ0KiFk4dBvsKCmCGZW");
        country.setCurrencyCode("e2x");
        country.setCurrencySymbol("9T0F4TVLplH5GTeLvhAr88A0WxBX8iki");
        country.setCountryFlag("71SaADgwszbRlqwq0LXsfoIQDXbUVIeqtCxtNLcO5bgjKwG9Bt");
        country.setCapitalLatitude(4);
        country.setCountryCode2("3v0");
        country.setCurrencyName("3A5m4djScSkLa5ugjWO2MEKCeVrq1RXuW9Im9mAw3j1b0L5E1y");
        country.setCountryName("ANazEBHlCsgmkas16IqzDoyLJWS1r5ec8SZb0Aaq0v4h6pdqQq");
        country.setCountryCode1("sct");
        country.setIsoNumeric(492);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("tsX32bJDAjgLbEXvYtut1mLKRtvCAVgItUJba7SXZ7OF1Gufiw");
        addresstype.setAddressType("DTCpi20ad9OXSztqNALMsC4W7lWtAMFy6x0yFo6voJjKOK7Qlm");
        addresstype.setAddressTypeDesc("AretdvqU4J1m4u4zSi3p7tMLfAqeHxjpeN8smWHmUwsbTw5hHr");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        State state = new State();
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCode(1);
        state.setStateCapital("mZKFO6Hq8XlUu5ChK8CD2TQDTvjOXTFWb05DFFlXjLAzOsed5z");
        state.setStateDescription("Pfpcr4q6fArsnaeI4XOuLNXyxsjcwDMtiHeTzdaF2aJuxiDoXK");
        state.setStateName("NsqBJBY6PRZHV8srRyj8ySwV7tyEpMgC2CXS03MHHc3Vpo8uMF");
        state.setStateCodeChar3("J1qOF3olmMgrjbL49cxQcWWCEBtl729e");
        state.setStateCapitalLongitude(10);
        state.setStateCodeChar2("l4baLWmnNexsTgOE6gHfuukmSpxfhUPZ");
        state.setStateCapitalLatitude(10);
        state.setStateFlag("6lPEOLEx4maZx3Cyq5OFvkEXWj6iA5UsYI1Yh87Tr9T0nyNkzh");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("roC7uLiUHtVsLZZf3AxciZc0Zw08JTmv");
        city.setCityDescription("YqP1VsqlS8nul6SxAjXORn5QeCG7YywbnTqTyot9TtuGqxUVQm");
        city.setCityCode(2);
        city.setCityLongitude(11);
        city.setCityFlag("edchMSbwbyHOxaY0taFxPz5d8m5nGoOIUUYLov0BTlT4aQXRQm");
        city.setCityName("C0njy1TsxGR3iMqAn4oYVa8yt8uYjP1xZTsD7EZRfXL8jlnkTb");
        city.setCityLatitude(7);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("b203E8dpKg2dUjofahAqb24yXs7qUzcpLiAplRDIuZN0e6uH1r");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("76kroa");
        address.setAddress2("BOuLLsYVcFwINpq8sqTGWXXHH85MlO2LmhLGktxn0Qyk9wWwcp");
        address.setAddressLabel("i0W8mR1SP6j");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("g1GJKFsthXRSXKKHYB5PrXM2Gkd1xUJZsnqSSUwe76oa4zLqzS");
        address.setAddress3("mxx0nlByjhE03PH0eN0DQlU2PS1cEBtH8Xp6yW1eh4S2lCbCo7");
        address.setAddress1("CcpKLyv3gsNWr6rlO8fg7Wk6HJarzRYwToTNdgWuV50os19lM2");
        Gender gender = new Gender();
        gender.setGender("sPs0Uj61byYCxq2J87nOpKQWsTU0MW7kvJdfBqRzt1euVgW0k4");
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
        testone.setTnm("kFAW5IEu8kvjxSl6ikDsN7nUsivVJtB1CxEQQ0Ge7SueBMGRWf");
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
            testone.setTnm("DDgJ44Y01oHXdlt6Zcen1yqPf7XO9p6BspX5TJch7Ui9r458Y4");
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "tnm", "sIcQZrOA0Sojd7iOQRxCcJkj9hi7EF3Ffy3y1UFRUZzIyr66xCDoj686QeRGh9hkjBFa20YuxPuaapZjye4xGTYEflccKIZFC8SEJiMiFSu6eyNGNqPOA1kNdoDbKdE0iWGNeEit9ySYPfoQOO2QCKhzUUmH3gzC7EaA4mQ5BfmMImqlrkH3tupqokKziRaBN4PMxXG9mywscxMfwqxMWw8DFD0oBgxhEVgjYTbtWpPwAbVQzU5hbGlZ1IVUV9quM"));
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
