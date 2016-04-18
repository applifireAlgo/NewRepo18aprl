package com.app.server.service.organizationboundedcontext.contacts;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organizationboundedcontext.contacts.CoreContactsRepository;
import com.app.shared.organizationboundedcontext.contacts.CoreContacts;
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
import com.app.shared.organizationboundedcontext.location.Address;
import com.app.server.repository.organizationboundedcontext.location.AddressRepository;
import com.app.shared.organizationboundedcontext.location.AddressType;
import com.app.server.repository.organizationboundedcontext.location.AddressTypeRepository;
import com.app.shared.organizationboundedcontext.location.City;
import com.app.server.repository.organizationboundedcontext.location.CityRepository;
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

    private CoreContacts createCoreContacts(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Gender gender = new Gender();
        gender.setGender("HNnr8jLKf91NN3GU0s61ZrFF18DwZ26fHQWt1Sl7ZxyV9k8tvg");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setAlpha2("xr");
        language.setLanguageType("hdUacOlpI0RXwD4YQWHvzEuYwL1TJIjJ");
        language.setLanguage("glEn53JI5C7PoAOiajrdmIDQKjKrpoq9675O2TjFR6MkLKXeKT");
        language.setAlpha3("81A");
        language.setAlpha4("DDod");
        language.setLanguageDescription("7JtJWK0KOhgyYxr3pijefXVDxk1CFHKvcfq9HY4gJXeiSKARmP");
        language.setLanguageIcon("bUXRFrWGodODOKkTAP4ehgf4f2oVI9W10MqmbjGzXy9WaYmGzQ");
        language.setAlpha4parentid(2);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("Vu5s5rtfBHod95jvO8IIumC0CkL2K5MxeJNbX4uYkiSWMbhdKq");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("AYptIaQWB59V5opVtUKS1kCNxYq7FovPLWSbdS1Ixq5V5YFVxk");
        timezone.setCities("TkInC1avjnmbgRtxGoeBYW6zazpEVqjVALrYHdw95BRszW5DRj");
        timezone.setUtcdifference(5);
        timezone.setCountry("unb7mXYA9OaiOIVIeE8FNu8BhwIpJn9XZdrkC23BsHaqquUURU");
        timezone.setGmtLabel("Wd0uiSgBpKxAXy5LyH5O7x0xNLOrmEKIOg8uMi8YExG9V8ray8");
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeLastName("vofjcokXEtt1SQGjH4SPhJVopzoGwhXbxlOlx872CVqkgvsXxr");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1460975167922l));
        corecontacts.setPhoneNumber("efYrH4Gh7bkWz7ylMP94");
        corecontacts.setNativeMiddleName("RAGg6N2dW8D2wyZSjNEVyEV6mSizhn1YJpeYI9hgYg9PMp4xUB");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("jEsZOcDbpmLw6f6jsQlArS5KEFxvL9EA2otSYLpoFufMPja2Tc");
        corecontacts.setNativeTitle("ycsVQykOh7EW1l5y7XPdKqrjPGevhjWd14d6C57mVLXIKhhvYx");
        corecontacts.setAge(119);
        corecontacts.setEmailId("9l02zPp9O1oc7PprsvJ9ZWa5ccbQswXIVC6QhhAemtln9Ls4iM");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("L6KYPaW80o2l6rp63hzi6B3CwCLJl4KjtPNysv1rXZg6S9llQx");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeFirstName("WUO6MuS5QEVPkPoTNJxkzjnarSRA7KrMXzyW162dgsxxdn1Mhn");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setDateofbirth(new java.sql.Timestamp(1460975168126l));
        corecontacts.setFirstName("NMDWJRoRtwJxynXBk3sV7V0Qaf1vvFLsbivzjPrmEUv3WmLtFl");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("ZWlWiJhOVl");
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("0BXIw5mFv27wulvCUoLbXEAV76PqfTVYqXJcsBMwimdrGeWbCW");
        communicationtype.setCommTypeDescription("1yhMlCJJQepy1aoepswXSPxH15et3MO2wcrSzLG2pAqh8n4b93");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("XcywyMoicS07A7ItgWDi5ylHVKJPKwxQX8uz6mqxAmE22c3tMq");
        communicationgroup.setCommGroupName("BhqiiWyhrsA5Cm2m9AjrhkNtVP3chO8N2O9cBmKfyNvvJXzcfz");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeName("kJcIxp2NaMmDjUJoQK4wtDSHBbY1gz9CaezHKULTDZwsYAYN8y");
        communicationtype.setCommTypeDescription("TpzWYWKNVUlMkMUvZ4hjQiPBujDi4VhlDfHOD84gRu3uqmEA8A");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("oKZiC9vNEw");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress1("IIJ3aG8oqPZkh6lkfwCdcjqSjjthwMJQF6KdM9J93Vpcznky3v");
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("SohziqF3PcmBBN4xC13GkSsQG4uxmVquWEDOTlWxcWeL2esk5m");
        addresstype.setAddressTypeDesc("nLBTWQVzjeEqkw6lUl9xX8y4tUegT2wirdgOUhVvlKf8UZ9RjI");
        addresstype.setAddressType("miRImZizXlnlTNQYn015vVFKJDesUwJJozOX0NYqDbquIV5BUt");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityName("NujUMuemSE5vuDkEzZhDJx0pslrr8nFNkENSFLueO51DhVCaxa");
        State state = new State();
        state.setStateName("fJof82OeoY6p8vq3MkQPAcNyh36QpyoyURBoyjdyzECd5D3AXB");
        state.setStateDescription("R7XZIzCBx02GmCa6q7XfEp2WqNNUkun9uznAwF95tPBAGQ18GO");
        Country country = new Country();
        country.setCurrencyName("w24AYZBTM28IGTlnbcjLnHjyjWzuzRgEpruIPeOfLWgkqA6JQJ");
        country.setIsoNumeric(827);
        country.setCurrencySymbol("xJ7FyfRKSaNNdwpTzHg5ItHLntRhvtRk");
        country.setCountryName("CZjcTJMIRPSPEQ0Rovd5XginBvLAbcXE2BolotwfmZO0WKXS5Z");
        country.setCapitalLongitude(10);
        country.setCapital("gR24v1KxNEKT4b7O3ymTWAQn4gM8v1sh");
        country.setCountryFlag("3P0jBH9z3t95dEBnGPAqpzSp6sB6yLrgzZtB8N0tPiBg9EU4FO");
        country.setCapitalLatitude(6);
        country.setCountryCode2("tFI");
        country.setCountryCode1("1eJ");
        country.setCurrencyCode("Gqx");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("FzDwgAoi9jvReNWA2F4p2eZ4PcXzh8oHmqidIDvwrcRGhvzko0");
        state.setStateDescription("n1W5zYpDlNBsygvOfMfRTlwmjxlMrIq5fPaRXdTl1Cgn7DURuA");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar3("tNOvVCnYsTGJiGG3OUCtNExKKomiBBYt");
        state.setStateCapitalLongitude(9);
        state.setStateFlag("e9nsPMR2iDvBuDnGHKnAXHyugvXpFRW6PZAVFWj681NYaTF20p");
        state.setStateCapitalLatitude(5);
        state.setStateCodeChar2("I34xV9Gq7P7KmHu98nUQtxd7nsHrOIGP");
        state.setStateCapital("mmcaY2Q0nmkScF1sXVVQ2wn84nB6KToHGfbMNLE3B0qLmP0bqq");
        state.setStateCode(1);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityName("uGF3kq4oqiMZtDV52RInvDjeEyzg6vieSY1XKawHEy22UpSbW7");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("5OPOGtDZ2B9424dwz2lefXm338y7BiXM7pgJaqHamexmORINLm");
        city.setCityCodeChar2("CZgBvhIY82n0UIz0OY9OJGSV6JQckj8I");
        city.setCityCode(3);
        city.setCityLatitude(4);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("371hBrXV2PPgOyqiLKLFRW9nW6KgvkD3IPoFBvk9SApgXUMlUb");
        city.setCityLongitude(5);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddress1("tj9a0cMDa9k1COyriQ9m9fj2jcc8sYTSAcMwBVZKWpyai0lKnl");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("RCIW7agCOCNGZFU0uJo5Qqu595HWtrdprAYSo3LUrTHQEUHz8N");
        address.setLongitude("x1GJhpaorlsgRH4kgIDexth37gU5Dn69hXUChmHU21E9IvxvmM");
        address.setZipcode("ET1wnm");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        address.setAddress3("Wo8Sv935lIu7R7vbA3hbqqs3rXzp1KuGTGKzfPMtokB5wPUhhK");
        address.setLatitude("xrvNwR1pWm7rSwioCzKynQb5a2cSgwkF3ZRM831APuiOeQgy1q");
        address.setAddressLabel("gLe45XtQK3I");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
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
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

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
    private AddressRepository<Address> addressRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setNativeLastName("tRPZHUawYxkNRoyPOZmTkDBatjMoJJGhEeEtrS2Cp6UH5iH5tq");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1460975168474l));
            corecontacts.setPhoneNumber("14S5tKGLOWqzDv7inCch");
            corecontacts.setNativeMiddleName("VzY9AwTA6eGu12EdF06CI336QwHtwrcRwacDbJIW3hBIs9WHed");
            corecontacts.setMiddleName("hoSF6SBjpeRM30474XSS1MQrTxCpAgNm04Kt4Dj5kzjVU0y0P4");
            corecontacts.setNativeTitle("DpRAPGH8NpiOJduiUWlPelypWRDbiU7DgfRK8IkEARDnvSKPM6");
            corecontacts.setAge(104);
            corecontacts.setEmailId("PKrzWizmg834Z2eK4e4CvD7hyVb4u8NXFGjTz7rKjbAFOEDmZb");
            corecontacts.setVersionId(1);
            corecontacts.setLastName("nJQK0Av34m9855sV3klH2YBXVrqr9QUFgvM1HUu5XgIEOZrO0v");
            corecontacts.setNativeFirstName("JlE36lb6wmmYHFmrrJSbnid1jRsWoFqsgS6tIPutRRIKbJuesI");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1460975168654l));
            corecontacts.setFirstName("Sguh1urSTwaHyx51G4o8qjprLaET3Qg9F2uGCXp4iJLqBo0But");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
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
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
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
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
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
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "MHmPdyw2aAnRPQpFu7VMKQpwU5kv1VOOnG1pMjn6w0xCmS4ZYGYqqLGkXCaSRSASjF1nM04LKSKQIPcfsT0CNalFoLzMWUyNwUOtM0PbyznNwMaAy0KikxQ16cmEWCKn0PCKDUCaVHNISugAGnO7oSkOD5u4FexLxIGxkji7b4mqdqg1nwnVgM3fHtSAcdvSgTLBZ2p0cSb1kbaIxju6B3lXA6vWatUsgNXrM0pXRhLxlG6DMh2wHCumjuFUApjVf"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "roVBW8ISp7zXoiiBXs5ZMqamR3GkhyuCZ8FtTg9bseq5sBaSxLt05pw92CBRSb1QcS1mwE8h7d4dNb0vKgwUOzWb73ysCGZe7paaPgdsj6Yg5uy77pgmfBmaG1v0u7mC6Fn0N8PwiuDFjmGQY9xDDDkyHTCNHEk2MGjNsk3HpYreNFSwkLIrrhaWC8Q9Uj6WZh0eRaGc79EaAYJ2rr7JOoASM00m6QLHNO4yAFHyOQ706h8WNUJ2DEKmVYdm2KJuy"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "4P9oWdgF3j8aKhAsA7Ma32ZiLHTb0akC2xtRZYyy9E3v5y5qCTwx7azaFcFbZvrPKU9yL119pMoIoWnZsFxPyKcDtjvUDWixV9ltdQo70IozbmOT8AjiYM4OeR5bvTEImabP5ZZ0TRv4xormlh1cNF8W1o9SZtNaworbwjohxvk46y0ilawwOLBlPG05pRVDeUuqt0DOSZaWk89t1CHYL487R2NXrJoZTa3HY9s5vSh5fpeBNYgRw7jYWz7GpZim6"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "vJnvOSebaEmL2JqtY4ZZB3ywmtlxNWwjpincbhlKiEnPb3TSKywQAlWUjjFS6UqHp"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "hX9ruZ72ZEXSBSozG9SORLBiH9ARvepI9ErDn8meyiZcZqUoRK5TX5wqq8re7tzDUwsNCVvWgeViNkcFfr3OPf0ApjaOKCMxDykVEixyTVgcjeXt7pIyJYRANNA75hA4p49EFRJMIfeA3n05JpMf0ueK991hwBd62FIaJPvl8LKSOEbFbPCdXWSOP6WpvPwZ7RPIUYpqUApv3lPBXRyRq8amA7thKfQi5jksZ3fwObhyZ4z2Yg5c9PdkkB46BVlZa"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "Bl0grAHVGgn15q7f0y6IBLRGXMI3JXzfa2xBIfNGzt8K6pIDnib7XTyd4XtbPyNLn46j9qYpXUjDtHZY6tdVpkPdY1iRt5DEdqbMhtf6bzP0Q17IrGZO5tUyAUgf3LbXhU2qKUte2s9Av75uv5QqrDV6uWbnImuRiXEA4CvTTnTaAOUAEzeqdSqTJfrLt4TnfHLykutDDCsiOtY4jNRrnThpnXJmKP86XjYa1YxWNWyOOy3GSdMipbN6fTz34yEyj"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "pVVz2f6bp1QCMQA6mLxYTxka3arEg3dRwElm1fJhtm5pnSeCnyF88FBd9Wykvy4EtmwkNTtysN5dh2Ede9nSaLh5vDY9PsOmKDYORaceOPk6aWwUjCN62SAJUyVvfTQ49YO9YPpg8iVp4ymc53fe0FKwOL4qZmlGxyGvuoyqsWSu22WKD8xlWT0pXryKWTBNypgDELHFEobwGxWfNKJRG4qJLV4z9Q6bC9nBA7rzOvo17lkEkO4BMkoJ2JiTkCvIL"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 132));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "sFN8JbH4LgRmuUMslkCNsLkLZACsY3dNPeLCO7o8i0agIiY2aUWFnQoLhCKy1n0IDljKuKVV76MAA7g6EaJrb3OKGcp7bAHXYsLR7hLpatpUkFRrxkzoKhcTdjlmMUDK2Zz54T3EeqKHzwD8JTU5L9L4IqYW7iDmrPvpQnOBXWv6NjVfJ3QVbYrYBxcq7pRAQu9O33J1xsLKRFn6YmJApbRNwKfCrlTXiSPH5rBsnj2UIqcAAirvLmGQG3qrrR4pF"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "c1LlAT9OG6gO7V1bb0dJJ"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
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
