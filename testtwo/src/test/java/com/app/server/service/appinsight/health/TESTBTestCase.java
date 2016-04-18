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
import com.app.server.repository.appinsight.health.TESTBRepository;
import com.app.shared.appinsight.health.TESTB;
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
import com.app.shared.organizationboundedcontext.location.Country;
import com.app.server.repository.organizationboundedcontext.location.CountryRepository;
import com.app.shared.organizationboundedcontext.location.Address;
import com.app.server.repository.organizationboundedcontext.location.AddressRepository;
import com.app.shared.organizationboundedcontext.location.AddressType;
import com.app.server.repository.organizationboundedcontext.location.AddressTypeRepository;
import com.app.shared.organizationboundedcontext.location.City;
import com.app.server.repository.organizationboundedcontext.location.CityRepository;
import com.app.shared.organizationboundedcontext.location.State;
import com.app.server.repository.organizationboundedcontext.location.StateRepository;
import com.app.shared.organizationboundedcontext.contacts.CoreContacts;
import com.app.server.repository.organizationboundedcontext.contacts.CoreContactsRepository;
import com.app.shared.organizationboundedcontext.contacts.Gender;
import com.app.server.repository.organizationboundedcontext.contacts.GenderRepository;
import com.app.shared.organizationboundedcontext.location.Language;
import com.app.server.repository.organizationboundedcontext.location.LanguageRepository;
import com.app.shared.organizationboundedcontext.contacts.Title;
import com.app.server.repository.organizationboundedcontext.contacts.TitleRepository;
import com.app.shared.organizationboundedcontext.location.Timezone;
import com.app.server.repository.organizationboundedcontext.location.TimezoneRepository;
import com.app.shared.organizationboundedcontext.contacts.CommunicationData;
import com.app.shared.organizationboundedcontext.contacts.CommunicationType;
import com.app.server.repository.organizationboundedcontext.contacts.CommunicationTypeRepository;
import com.app.shared.organizationboundedcontext.contacts.CommunicationGroup;
import com.app.server.repository.organizationboundedcontext.contacts.CommunicationGroupRepository;
import com.app.shared.appinsight.health.TestOne;
import com.app.server.repository.appinsight.health.TestOneRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class TESTBTestCase extends EntityTestCriteria {

    @Autowired
    private TESTBRepository<TESTB> testbRepository;

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

    private TESTB createTESTB(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Country country = new Country();
        country.setCurrencyName("JZ8nNdM3oGIMSa0pDnw23l23L3jxJDrcqYtZmbkRLPyA5ewwmo");
        country.setIsoNumeric(638);
        country.setCurrencySymbol("qufUpvF8RIiGHJa3KFtxgZE1Mrrtz9XK");
        country.setCountryName("cSpHn2Fesp7iAbGbZeg1TfsUqIG9Zb7x8QfAGIYnvHtV34GpC3");
        country.setCapitalLongitude(3);
        country.setCapital("Nz9nCRqO9v2qn74vpKs5lm02MLNhbtpM");
        country.setCountryFlag("TOVB2YdLwIAECbVycXcF1nTpgtoCKHUCubxcuZ6GFqtkSZ9E2e");
        country.setCapitalLatitude(3);
        country.setCountryCode2("iyp");
        country.setCountryCode1("V7L");
        country.setCurrencyCode("hVx");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        Address address = new Address();
        address.setAddress1("ueG4PKS3QOKSNE3I0WZ4WFgcr6b7nPPLcXXQeDzeKW3y3xkop1");
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("1qCvCRow5WUl7CBhULoSdNySHSepIendi9hf5BYri2mow5nWCQ");
        addresstype.setAddressTypeDesc("6mymSGhv35XPF27QelrZQ5CVWOfEukbUwibUJqOlws0lbJqdBh");
        addresstype.setAddressType("LwyArjsoqgEYMKFr2dV0yRiWbWsIuQzbKHMsgn0k1aJdifXOJ4");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityName("6xNTY3adz9sNdic5coqLDb8epLWTpddcFyM40c5YuIlzDqvBWd");
        State state = new State();
        state.setStateName("fKErl1uA8iiYOfwdHjlIkwggYzsufWVUaIWWlZ8navo9x9A3hq");
        state.setStateDescription("SaebQTNFCHcd1irHKFhpxFoE3aoTwdulUbysvWkQzvuPw1SpDW");
        state.setStateName("4iEOwW9FfRDouGlCAS8C5I4FeiOsdVwfVl9HUZWNgCZea183Ic");
        state.setStateDescription("jCLNpn8CkhbhDs4kvOjR9YBDJxXDfjj1rscjA3xUBHrHdSJ8GJ");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar3("lOnbPNpk5jzFGbh4uKfypOgmnNqxdQTj");
        state.setStateCapitalLongitude(1);
        state.setStateFlag("3XINSoEBaLQiKhsjUnXeoUqRBAcYbIG7quBwjwOyxTAph634PX");
        state.setStateCapitalLatitude(1);
        state.setStateCodeChar2("iojhuBUgDA5VA5iVarzHCVSp9iLHKFV8");
        state.setStateCapital("vDJShqQZp4morwFhDb2NF0VnvAfeTvYOn1TVl4HwC4QSuqtQw5");
        state.setStateCode(2);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityName("WvFbphi8UzrImMlxREqHUXH0een5Jn1MDHooabtnrHFthnsCaR");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("HRgl1cEEmxJ23Zi77baGCBG9RlXvd0EmbIbGqGqrYgwd21BDXl");
        city.setCityCodeChar2("lmew5rDJbKDcHHGuYI67j4LbswbPf5e8");
        city.setCityCode(3);
        city.setCityLatitude(8);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("EKbubybBl3uMotAAKDNYdRbNCEL5oXTwrqxl24Ut4bvypY4QMt");
        city.setCityLongitude(1);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddress1("JHHZ8lsuDoy9UDakwRE3FUyYyicxvdAt8RCMbxEQK1lbDnZjHo");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("dvAlAFUIbzQVFibEX3qBR7JRVHuf0770UtutUr5adwfqMTAYYH");
        address.setLongitude("FW3NzmPDPd3Q2ulqRMQwdeRGzCkXTaidZJqW7VAMQiwUV2EmGf");
        address.setZipcode("9YOjqv");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("MsQ5TJLDxTlrZUsYI514cibHFQRgdRqoKLUOXrWv1fyb8ifU96");
        address.setLatitude("ZyiMevhU6noOKtAwxoWcLUs4SOUvUh0aiEYSinMMyevCH6jUCG");
        address.setAddressLabel("NimVsS2TWXw");
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeLastName("OlTDbne8QFBjmLTBEzbdrDJoZ26q5WGNriTdlnlQqWijnJpspj");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1460977060314l));
        corecontacts.setPhoneNumber("AHj1oaGw2S09VZUCBUcP");
        corecontacts.setNativeMiddleName("r17rp3hYCYlzes12hINUubhpkIJZpqKcCnvKB9Zf7SptOV6Vs4");
        Gender gender = new Gender();
        gender.setGender("yCy4EC1nB0t5tPHAv8zg5SmMnT72plTWnI99ykn6lWdyAfvRtl");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setAlpha2("4D");
        language.setLanguageType("Z88h1XxF3yEv7T5i9urKO7YrMYh98n9Z");
        language.setLanguage("C6aaAvBtpJY2piEutQNzl53FBeE942M4nY5sDitRRdeov3B1xE");
        language.setAlpha3("48Y");
        language.setAlpha4("LOLr");
        language.setLanguageDescription("jhbfPCuNjiyaI4oqV90nZOWGENAPBHPwneqjW9dtWPYEeJMC9e");
        language.setLanguageIcon("sj1R5UlDQ2pG4PXCnnJBbmhb1DvTuwORtjTwJHOobcqYgrMg3A");
        language.setAlpha4parentid(10);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("21MFSNGPlpU9WZfFBEdQNqL3o5QgOJzQXqwgUOpW1vDKcKyhD3");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("Jek08u3ugGVYMOLilD6eb3EBdDQ01hDMhRW34dgArFykLqiMTV");
        timezone.setCities("SmTNElmnwN8jyLXeKXfl6EF33BvUXjYXXjkUX9JY8Xyu7pn3ii");
        timezone.setUtcdifference(3);
        timezone.setCountry("ggxDLMbisaHN1OqfIB6gZ9W0f6QPy1YhWL6YkZ70J7bryDQGO7");
        timezone.setGmtLabel("z6jM26VBoi56eMBrdmtLjtaYMkmOkTIi0KW97rpciAVDS3dZr8");
        corecontacts.setNativeLastName("p8SChDgBhIqce7CUSOEU2YGWD0ZT53JA6iT8DvQHtUCZzMVTla");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1460977060330l));
        corecontacts.setPhoneNumber("SVxknktObkUpjb6yJPDI");
        corecontacts.setNativeMiddleName("DQ2XxnOmujCBF6HrFbketesqkIaq1zpfUk44EJbXL24iX4iUJ2");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("HsvGDW7YBgrBQslGZ5dKiWaeGOYeIf0VWNf9YyjL844mm0Daij");
        corecontacts.setNativeTitle("KViXUgCPnn8MbU1GKlb6zHjZXBdEJ4FLn7KLNDCtYd9MCghczJ");
        corecontacts.setAge(20);
        corecontacts.setEmailId("q9BxOenUkyV0JqTx0mLtcUi3y49EmvkFSAnl4Nsy7g6KNNpa0u");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("d6WFhVzHFaokip7NZt6BPdKnCxUS3I5bj888hYF8lscijhZXoD");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeFirstName("LtCjxXkUsilW6w144Bu9aHFjxQSrmdgPaaxZgvt3tAjq76oHSl");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setDateofbirth(new java.sql.Timestamp(1460977060450l));
        corecontacts.setFirstName("4SfOG6Q21eQcS5jpkySOJrEflAWI4ExoBWDu7cbeGtCLkJC7Wj");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("XCse9iMJn9");
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("Qbxo4Bxjb1UBs1nimCTQrjkIsjrwWc5rO9tjOYdcDIbqvv0xDS");
        communicationtype.setCommTypeDescription("b8wTItIkRqNGKeZbUnCnBeFdzOhWGc4V7OfJtgiGJUFUB97EXZ");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("RiBZv7tXRSAKpgfGDMCNQHDoKQ32G3zz5mrHG3SWDTVpakw2t7");
        communicationgroup.setCommGroupName("FF08IR8HGEaRcWI3Cbx6zAaSSTC9Tle1IwpNxZUb0Apng4lRln");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeName("WQ56EKVTNWAzGFsA2k8UHOgiajSFucuxoPcdA027Dg0R8cbkf1");
        communicationtype.setCommTypeDescription("D3xkVAePX4uGaXViQkHLlXTmcVuDS8UhKYMj1AcuknFdw8qvp4");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("3Cd4xU4Meu");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        TestOne testone = new TestOne();
        testone.setSasa("CnaNWWXuP6cZDv6PHPOCABqu9XdeEgfx2Wl3BKZiz7pHY7ds2a");
        TESTB testb = new TESTB();
        testb.setBc((java.lang.String) CountryTest._getPrimarykey());
        address.setAddressId(null);
        testb.setAddress(isSave ? addressRepository.save(address) : address);
        if (isSave) {
            map.put("AddressPrimaryKey", address._getPrimarykey());
        }
        corecontacts.setContactId(null);
        testb.setCoreContacts(isSave ? corecontactsRepository.save(corecontacts) : corecontacts);
        if (isSave) {
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        }
        testone.setRrrr(null);
        testb.setTestOne(isSave ? testoneRepository.save(testone) : testone);
        if (isSave) {
            map.put("TestOnePrimaryKey", testone._getPrimarykey());
        }
        testb.setEntityValidator(entityValidator);
        return testb;
    }

    @Test
    public void test1Save() {
        try {
            TESTB testb = createTESTB(true);
            testb.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            testb.isValid();
            testbRepository.save(testb);
            map.put("TESTBPrimaryKey", testb._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private TestOneRepository<TestOne> testoneRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("TESTBPrimaryKey"));
            TESTB testb = testbRepository.findById((java.lang.String) map.get("TESTBPrimaryKey"));
            testb.setVersionId(1);
            testb.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            testbRepository.update(testb);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("TESTBPrimaryKey"));
            testbRepository.findById((java.lang.String) map.get("TESTBPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBybc() {
        try {
            java.util.List<TESTB> listofbc = testbRepository.findByBc((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofbc.size() == 0) {
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
            org.junit.Assert.assertNotNull(map.get("TESTBPrimaryKey"));
            testbRepository.delete((java.lang.String) map.get("TESTBPrimaryKey")); /* Deleting refrenced data */
            testoneRepository.delete((java.lang.String) map.get("TestOnePrimaryKey")); /* Deleting refrenced data */
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateTESTB(EntityTestCriteria contraints, TESTB testb) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            testb.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            testb.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            testb.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            testbRepository.save(testb);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
    }
}
