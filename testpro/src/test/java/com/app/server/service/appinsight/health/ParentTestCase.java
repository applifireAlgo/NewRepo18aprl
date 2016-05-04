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
import com.app.server.repository.appinsight.health.ParentRepository;
import com.app.shared.appinsight.health.Parent;
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
import com.app.shared.appinsight.health.Child;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class ParentTestCase extends EntityTestCriteria {

    @Autowired
    private ParentRepository<Parent> parentRepository;

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

    private Parent createParent(Boolean isSave) throws Exception {
        Parent parent = new Parent();
        parent.setpName("fuPNSt84O0sgzDSpKy33feBt89e5EYMYnVC2pSOfMlYxFIt7cz");
        parent.setAge(2147483647);
        java.util.List<Child> listOfChild = new java.util.ArrayList<Child>();
        Child child = new Child();
        child.setCage(2147483647);
        child.setcName("05T5ZDuqwKs4AzWQDF7Cx5HLA8juJMrwznIPy0quhnNIGnaAit");
        Address address = new Address();
        Country country = new Country();
        country.setCapitalLongitude(8);
        country.setCapital("43hNJBj0goQ5gs2yBH22haiwyt4SLRF7");
        country.setCurrencyCode("GfL");
        country.setCurrencySymbol("Z6boLnNXXlNMxbTSjCm3tK2O4B65qSl2");
        country.setCountryFlag("eGCZ6hLwqtRhZDBHoIYR7ooTvhrdgdWCl0TepEmTXRrCEflHFH");
        country.setCapitalLatitude(5);
        country.setCountryCode2("b6Z");
        country.setCurrencyName("IdApuXHELFMTvYRSHvxPaqPBe74hCRD49BopGOLxwLc4PiwZWK");
        country.setCountryName("yBYL0tCjqV2equNT8gNIxnGkqbnN4b50wJF3ysiNmllbSHgV0s");
        country.setCountryCode1("UMa");
        country.setIsoNumeric(465);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("wSymPDwEMkmICxM2g5xAuNE47d3usa1TsRc1Ju792qYD5hzjaf");
        addresstype.setAddressType("AjjGoJd5WvaCnBmU9qSxOZ68dXwiXNd91EakdTtuLW29Y8MNvo");
        addresstype.setAddressTypeDesc("nPaI2Usm7f5G6uyjSWKpBz7U2UKOFQgvCklMuNwnmQTwJtxzCg");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        State state = new State();
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCode(2);
        state.setStateCapital("OEofmOARuxtvZNxF2JyQSYQPObJqcFtJOzdnpLQxGVskmVleOm");
        state.setStateDescription("mIAJEHoG4xZp7msRfDSwCkE8HctIBTlSh1tQ9btceAw51bgaLi");
        state.setStateName("IkP6h5KdKj0Q6IK7djPRzhlzBOlG7cEWfOr5bivbsRVxQcawYN");
        state.setStateCodeChar3("bKlLL7vCdT6oWBykVRhpP1xX27SeJM1Q");
        state.setStateCapitalLongitude(3);
        state.setStateCodeChar2("R1XQqIm6wEoGnbpksKieJ3YWNGgUbzv5");
        state.setStateCapitalLatitude(1);
        state.setStateFlag("IKhQMql4R7iu3uYRKSPmBgtIBWJIZwShtgwtHuu0lYDLP5IhCB");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("ZijxlFdvWwxjvcOQHahVJvcBNGyNweL4");
        city.setCityDescription("JYXbRDucdop13EvjDWwmLL3iYLEgsNxXoPLJsT4lNqUx4ye5zQ");
        city.setCityCode(1);
        city.setCityLongitude(6);
        city.setCityFlag("hUiWnZSODp2RTpe1ZaHgXoi78b9Ken0m016UDVOl270MnFeQTL");
        city.setCityName("iUTyLpp1Rp0h3paMNYb6mABZgiGsxvThB69Dl81p4UbX8Z6dcv");
        city.setCityLatitude(3);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("EmwIrEaHFy12vI7jOKpySKGkL3MFiS8EjuRc4IG7ZyaHYNjBGU");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("dbFQT6");
        address.setAddress2("jKfFvLnGG2Ew2iSZO5SJgVR3rEhW6lyd7Zd8zUKp0HSb3EzPmu");
        address.setAddressLabel("ugsgsMf5SyH");
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        address.setLongitude("pg3KiT9SWvH71RO03jMkS02NSNMw6sM8kdQKUWPVxf9zssYCRG");
        address.setAddress3("fDpbvBNnEhMABDb9TIrN3sdkmNG1hEyaRBhetlvS2NEeXsdMMr");
        address.setAddress1("O5zzVRPccYu0zZc0rLcyXSCUYtxuE8a5ijMVtgh80WSaip46Of");
        child.setCage(2147483647);
        child.setcName("G7DtKpKuPIKWKIpsM6OQDOCISICmuMsnwSfXsu89aR29yCJQJR");
        child.setAddress(isSave ? addressRepository.save(address) : address);
        if (isSave) {
            map.put("AddressPrimaryKey", address._getPrimarykey());
        }
        child.setParent(parent);
        listOfChild.add(child);
        parent.addAllChild(listOfChild);
        parent.setEntityValidator(entityValidator);
        return parent;
    }

    @Test
    public void test1Save() {
        try {
            Parent parent = createParent(true);
            parent.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            parent.isValid();
            parentRepository.save(parent);
            map.put("ParentPrimaryKey", parent._getPrimarykey());
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

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("ParentPrimaryKey"));
            Parent parent = parentRepository.findById((java.lang.String) map.get("ParentPrimaryKey"));
            parent.setpName("NgZ5b2ldB7OVeChu1RdXGqGyriRuzVEgSIJz6CGDNWT9EPUf4t");
            parent.setVersionId(1);
            parent.setAge(2147483647);
            parent.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            parentRepository.update(parent);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("ParentPrimaryKey"));
            parentRepository.findById((java.lang.String) map.get("ParentPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("ParentPrimaryKey"));
            parentRepository.delete((java.lang.String) map.get("ParentPrimaryKey")); /* Deleting refrenced data */
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateParent(EntityTestCriteria contraints, Parent parent) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            parent.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            parent.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            parent.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            parentRepository.save(parent);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "pName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "pName", "WGRzdgXFyvsRWlbCaaNL8beGp1vfRXdXgJFriCr2WYEDReuoWUHZuwaefzPildKKZqR32HpnZEHUgqRn5hlpaaGnamMl5InOPHqNwe5MR9NmRM9rU3eq0yDwUwtipRthRLmR02PCVA75v10ddXpG3f3tCyHuLUsOs6k10VzzPPPJlEyDGcfaYOm6ZUeGDMGbDRFnz0jPx3N2TwNVAi2kyKxMsjjMbXtickfBQfPUahps6QFk9KnCc2QizypQZcWfV"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "age", null));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Parent parent = createParent(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = parent.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(parent, null);
                        validateParent(contraints, parent);
                        failureCount++;
                        break;
                    case 2:
                        parent.setpName(contraints.getNegativeValue().toString());
                        validateParent(contraints, parent);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(parent, null);
                        validateParent(contraints, parent);
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
