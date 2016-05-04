package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.contactmanagement.CoreContacts;
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
import com.app.shared.organization.locationmanagement.Timezone;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.locationmanagement.Language;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.contactmanagement.Title;
import com.app.server.repository.organization.contactmanagement.TitleRepository;
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
import com.app.shared.organization.contactmanagement.CommunicationData;
import com.app.shared.organization.contactmanagement.CommunicationType;
import com.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;
import com.app.shared.organization.contactmanagement.CommunicationGroup;
import com.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Gender gender = new Gender();
        gender.setGender("tZskNsu9tEFEAjIkybRu16IIqS8nEu3Oq7I9ObXDYCzfAHM5pC");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("UB7s2H3JAPycKto54sfkRBtWMOF5XeVOZGfA6b0E3fqebMtI20");
        timezone.setUtcdifference(3);
        timezone.setCities("4mBPMO8c7Uvyp6JLmETfqkKdDCCDLmZUq52dcBihLtIx89pRP9");
        timezone.setCountry("2aNpxTOR5khY7JrzHCHhw5ZlVsFtDzCI8CfeNpMrvxH8u2wyzE");
        timezone.setTimeZoneLabel("jjxb10k0ZMFvkzN8zNbDuQLZ9mNz8SyJNu1aE7s2Uhg25qPKri");
        Language language = new Language();
        language.setAlpha3("0uN");
        language.setAlpha4("3Vij");
        language.setLanguageIcon("ZGvzaPkvSoYmLetceayZs37JNJSjbi4NiJEp3XmH3Yk2MgRE05");
        language.setLanguage("AqIRYBK7UBIbSNZK2tx5DiwoDHml3dxCeaUwUVndC1TmD2esb5");
        language.setAlpha2("qW");
        language.setLanguageDescription("mNsg1z0g4Pj9jm9gq2QzG1mox4uJd0N6TEYuj5DRnnNubAqbJu");
        language.setLanguageType("tpLHj60FWh5tbuAIZR9iP1gz2ioJt4gN");
        language.setAlpha4parentid(11);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("79Dip8XTbqKhnQuaSA4O9zKvpK56lN4FsPZKmqKnB4s0UfwEEc");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeMiddleName("qWmkqPi18m68h8YwkfO1lkueXM7MYkKK8ml1iJUMvXx0vkhW24");
        corecontacts.setAge(20);
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setPhoneNumber("G0UaS71iNKgToTXv6duM");
        corecontacts.setNativeLastName("FqWYKlm9EQpvb2JrbEGu1szOZR7J5Py3EVqUp74RlJaxiTZsQc");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1462363214816l));
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("gQrTqGVHBlbehUCF8pPnoV4Q0PSMLEPnZ1aOvXgogrYuDTaTP5");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("jWbiVa6XiJSB5d8SvM0PlL6SleXClcBPStqrp8oc5vaoOj6m1F");
        corecontacts.setEmailId("u3lNwZdXiMB5h8xwtsjXtAAdeXvd6Ej77Senq6oESWbvINhC2Z");
        corecontacts.setNativeTitle("gelsmWrjOI0h81E7Ik0Mhe7lSIuhUr27d5Ok9u2zYC5ArwKYWa");
        corecontacts.setFirstName("90HIrMoK3M7oRO1cef7aAhH01olsFVb3124NffNk6QnimTVzWk");
        corecontacts.setNativeFirstName("QBUunI9Vc0u0DiSFEx11zoOBSG49jJg3y5ge9m94wV5lIdZf7K");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1462363214936l));
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        Country country = new Country();
        country.setCapitalLongitude(3);
        country.setCapital("DkJgj66qCRp973VZ5VeDZEDbTATWogdP");
        country.setCurrencyCode("O8q");
        country.setCurrencySymbol("HGB26ZFMgwOkMRdOjBHsP0TQmnAo53uS");
        country.setCountryFlag("D3an3MAtu3M4ER1rM0EluZ5bB03dxzOzex2J8g2W0O6qWvLtQ6");
        country.setCapitalLatitude(7);
        country.setCountryCode2("xeb");
        country.setCurrencyName("yQDhSCsuT1ewIInakyXLxl6HeyHQCf3QGKJRMNokJsAGhGU27R");
        country.setCountryName("6x130JuNyctMlKTyD5E1azgVr3iMWIFrWkhWXBv1wOpmEHI2xP");
        country.setCountryCode1("QZE");
        country.setIsoNumeric(800);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("h2NQ0K02DksMIh4Nlt8xgwX0Og4ixy6SaQmSFN3frbi5Tf3Xrp");
        addresstype.setAddressType("zYvf9i3TMxdY4dYL9vr8dICdhqBgnTUWNDc878m2xwkw2kVTMk");
        addresstype.setAddressTypeDesc("nyKEQm6PdOnP6opOKyzM4R9WMeG0goHCmHsmcyTN4xSHNQS7V9");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        State state = new State();
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCode(2);
        state.setStateCapital("5dKFiO5cWsXxDCCmS4eQowcvMTyABt5iXpAT5lEuNg6QMs020m");
        state.setStateDescription("aCLhG2v6r3jOQm3tebPm0TaihE2vzJeVwz3Wtne7kcyQW5jpR6");
        state.setStateName("WAPKwQTnqJ1B1wGE2bv8QvvrDVVnZOzZWCju5obIXUoO6CWEe4");
        state.setStateCodeChar3("fNA4T44BCgWj3YjpLpOmeN5CPdk5k2jk");
        state.setStateCapitalLongitude(6);
        state.setStateCodeChar2("12w2VQUQfYIasWAz1lcqNcHjvCwT1lI9");
        state.setStateCapitalLatitude(1);
        state.setStateFlag("77pOcGSoy3GYdVtURErOeWGjC6JTsgjOOKOrEt37xXkiB4g1KU");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("UJXEeqYxTR7jh1GHFbdPUXT462yhoQGB");
        city.setCityDescription("H5mb6QPB9zFRShacPBLnSJo0oR9KJNa1QVTjSxhO3h1GoIFfCX");
        city.setCityCode(3);
        city.setCityLongitude(2);
        city.setCityFlag("C6VmSDt3copiDsAQXxSt2YYBPMrtW3wNjEnoyChtRAimi0AbwF");
        city.setCityName("DCPGRA17680ZIe95D17LFU8eN90IY35BfSk0zMUC7Ec3s0QyzX");
        city.setCityLatitude(8);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("yEWdOdXRBbv8pIw3sUPP5O2RKenKL4tzTqgcNMZsyPuOlz89N2");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("2JVrgG");
        address.setAddress2("WFMok06MKpsloY3wtoXiIjXjkHdpToUoCTPL5M4st6AaF731cm");
        address.setAddressLabel("okgpiuzpwFD");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("Wf5Z0fCQxrxl97brQz4cs9BUxIEqSwMCWfuPvLVQU2OjuvnjmH");
        address.setAddress3("U0OHQRWhGxi88X7QD7qplFR50On5ggmLrupRjQ79M8wGxS6Ig6");
        address.setAddress1("auKQhzIroenVbZzw0qpt1eeYLJ6DKbgr68xwAbHearloJyjLrh");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("LVXevRpio3S22mMrZfVlBrlhq9pCktj8vpA2CVlKPohdkpf2cG");
        communicationgroup.setCommGroupName("SSD2smerysaWfy97pSFNtcc0gkceqIx2ojis8Py4tlprNAQyB4");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("wXHH6jKNEIXbQj3gFWXbZUuKmMb7NZXKSh3SdWqkHFqfewvnT7");
        communicationtype.setCommTypeName("WjVBTNyHZs8P12HeT7nUryODvekWFnvfYmNUbtPUJwbYQ75Gc5");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey());
        communicationdata.setCommData("fYLFnFkhAs");
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

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
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setNativeMiddleName("suw2zIgUbgL4f49bI5V9SNE8vvoZ6fUm1EJiT8a464xNTkRMi2");
            corecontacts.setAge(95);
            corecontacts.setPhoneNumber("iXW3GV7AIdSoPQZbtLCZ");
            corecontacts.setNativeLastName("VAJdpHOZga6Gsf4NKGaYSAIcQum1CsqcEblipzfC6wvPHRlQRz");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1462363215320l));
            corecontacts.setMiddleName("W6A2Jb33q695DMsaDD8byVn6VXebhI5r6SQbqEXP820CpXBJ1B");
            corecontacts.setLastName("XRk7cgYK2ADRS71SwOVi7i74Fk7UyBbUsKj6kYlNfWzpTr2aPb");
            corecontacts.setEmailId("jJpjy2jHGpcVWKvMC02qImTTjvpsa40ogxpDnWfoEqrlYu4fNM");
            corecontacts.setNativeTitle("EcPTqCHveBW6jP6dIXhG9XqbfuO6gm5HwyV7n5MYbutxibVPhA");
            corecontacts.setFirstName("UyBeRYNjQuS18NBetgFYgxIGmzWcIi06vJOWX6gB124Cq9DchU");
            corecontacts.setNativeFirstName("8YmX5A8Abq3Ju1yhQVpTIMlaqknYZoI9C9ziSLsWH630r8Iydf");
            corecontacts.setVersionId(1);
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1462363215429l));
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "PiDuhS1pZjEaQhrzFZXNr9BooalHygkgJsc12wq50LYsKsgaJKKLx8OIqU0UPfw2NNcctDJDc1wpbMyVUzBGD9axQ5oNM8PA1UKDxvaJt721WK1Uahc0DeFfeEvdGrllh9sAVeD1FkRKf6AXz7fKE3iyRqh7eyqPLi4M0K5D6DA9xYtdbhl6iUMVrMkurtD8D4VMXl14dntbLIaggyYfeIbEMaU6iwJGlyW35AJogTGvkEK7uXns5LtvOFaMqwwP7"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "GoipZPf6UQnSnJUPF3Ig5rMwiWjeTlQVbXZeCGAZg2pB7K1c7G2R8F0ExJZsL0h1ENiKvNHgcQ1A4tsEKGMKyj6jD033SzikmwgeoOeJZze3uONTwKjwy6R22TwZ49A0Wz6GIv6j75kCV0NeoLzcYyTz3AUNRq7udKRJnGNfu0054S7T7Fl4ef95Eg6PcLMAbLQvxoH3lIgcvi9uA56SOB8bfkpkz0fDtCddAKtzwMRc8EMEe8CV8wSPvqdNoPoNo"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "axMcaF74RchbZb3VocOQYxRwLfnPllZTJ3V0C5Tsk9j2LHCwjBCag1PQ224IA66rYnbik9TiutrAJuryOstDawt2RswSBceSNSv6xOhxntXyHYWcqxkH8hGhSo4vRulPgFwmMmYyUkQ4bDz1VJdw9PlxWbljKEDWvKN8tBhOhAWC4oYZU5d9N62Ma8RWmP6p7KVIraeUHEqy0ZjzILKAoEExG4ku78h2IEfiEXsx9RSPgmaBpARMeTqmjLZ04yxLc"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "mEDwyCAV3trLqZB5bVHVTANCHFSsz2BBHpNU6YNK3K1HtfxxQV1DHFZhzFdT3CSQy"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "piurDb2wetOlK1aIgkDPjqcAdQjpndCpOtfHNPZmc12DfCAuPVBMyJm14KpJsNkXJ09V4Sl8voOdMG4nvAFW9Iejkml3VukTqj0jOrsHsttNsAMlVklriOuCoqx1p5sdLaxbQIUDoaJwHrwqItOrkwX0aZlF6v7KP1nw42mDMis8WFkcdjjCZKIsRJToDHW7IgYWdarwj83FOpeKzbYjLhDNMm0j4WyRuTiJdJc44kOmdbicKZ6JA8wu8xrSfh7UM"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "nCu3ohiTT1V0Ni6JZZaiaI8S4LpAZja3iuBl1q1BB50KlCLmR76a2klVFX2LsjBiqxQoBTwW5xTbHyE8XNzOl4OC4fn47oAyrtLQdjzEvX67yt4p5RD9Mi8IxRTRtyRI7GvxIJyqPvZPc9LkZCDnFn3EaSlkyNUvHJov7K38qaEUA3OHIV7ipOWBzozzNGobrzkX6hnYieWyfsUWOFeVpTGeTd4OO2I9j8aRUUABPiu1gWvKrTEwrXHni09e6RZlr"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "o88ExiID2vZ2Ne7uUtooRmFIdpZvCOGZ2sSpOuACm4L1iUfewSCSBe2Xu24JKryQcFeQkTSQ1Y54AS6rjbWkk3jgpaNl7hcM3jkH8mxzi0YqBI02DwDuqSgZKTJprRwXD0INtMqYV1kaM4Mnw90eB4cXDUCH1X7ZQPSYfVGl4iyFORwOo7bDxXGjlrVsKVzGZakgGuYeotudZJCSr2UzAq9q2ORbMw9pmbWPzbeS3WuNPqx5AotOWuw1Q4sgqesWo"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 232));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "ui5FPBQ8kyuzEM4nuglUxQSAbgZZXlzSM8lTNlUTxwDMBiUYwcDJq6yIPxx5VJs1Riz9H5dKkipgVZGmYUiHGrJERxAsQFtInnUV3xfYlEQcKePF1q4kQiTsJwhL2cmiD0H2pCj0MqV9b8vjJB0W8wjQRQnNZp2PXL6bBpX6HEtlfef1QkIj5EjgibMghNz5FrEkOdooCiR6tmXElTwZe7CCY53Z6adMoMHH3Mpv4QAD2CxtSlV7jehT7zFbGlK6y"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "xn9P8KvSPrf03bDZSB2H1"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
