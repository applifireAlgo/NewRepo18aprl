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
import com.app.shared.organizationboundedcontext.location.Language;
import com.app.server.repository.organizationboundedcontext.location.LanguageRepository;
import com.app.shared.organizationboundedcontext.location.Timezone;
import com.app.server.repository.organizationboundedcontext.location.TimezoneRepository;
import com.app.shared.organizationboundedcontext.contacts.Gender;
import com.app.server.repository.organizationboundedcontext.contacts.GenderRepository;
import com.app.shared.organizationboundedcontext.contacts.Title;
import com.app.server.repository.organizationboundedcontext.contacts.TitleRepository;
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
import com.app.shared.organizationboundedcontext.contacts.CommunicationData;
import com.app.shared.organizationboundedcontext.contacts.CommunicationGroup;
import com.app.server.repository.organizationboundedcontext.contacts.CommunicationGroupRepository;
import com.app.shared.organizationboundedcontext.contacts.CommunicationType;
import com.app.server.repository.organizationboundedcontext.contacts.CommunicationTypeRepository;
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
        Language language = new Language();
        language.setAlpha4parentid(10);
        language.setLanguageType("VhsU7KY7HdCzDSYSM68nrTNd3rrdGmL9");
        language.setLanguageIcon("wKlPDdDQIBZoIWhV9eGAy8ml6rv23Fy5o8V71oABvcfxNP6XSf");
        language.setAlpha2("83");
        language.setAlpha3("qWo");
        language.setAlpha4("4FXz");
        language.setLanguage("m82UTi17FqNpDrcbF8yxt9nd9g91E9x15F34UXJsUUIp6KN8sI");
        language.setLanguageDescription("YI30HCyyT0DBGntOENSg1kRkCwE0SWSdPpIeuzDmTuhh3JuqpH");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setUtcdifference(8);
        timezone.setTimeZoneLabel("BfH6amcPwJ7Pzu53mfoo6IMtiHKinOAsUPGYl4vHNt1jYZdfpQ");
        timezone.setCities("qtAMEzE6TVTIyASszr6onFzmBv8gEZ5sWYZPlYxtqPkYIW2rEZ");
        timezone.setGmtLabel("biG3ciYC1NuYpX0J0XdjU5uTEW6jy53OXi2yaNiFh6XUcb5yN0");
        timezone.setCountry("UANnuFHMUXudoeOEJdZCeUfufIEGRoeFqMAKDb8BWIRnnNqvDp");
        Gender gender = new Gender();
        gender.setGender("wMAd2H4qDZjNfuEx602q4qg2oVzYKPOnCoXRwTh0O81wzBUHqG");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("bZYVNB0YQijAxCri4deZ2dZW7qc8XbjDZup8SbaHvDFsV1DDvs");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setDateofbirth(new java.sql.Timestamp(1461247751168l));
        corecontacts.setNativeMiddleName("QyUoxhC6h5ofoDlgCfMjj6wDeGAFdZcsnsJ8724EbHeYayKiuy");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("7HRR4i987JTmNd5WoUlr5MOPreZf1pJLd8zHiMXp0CzdmQTaEt");
        corecontacts.setLastName("R3SuxrsxdeNmfteiFlweb9SBk8Ox9agZDB3nFm8hBInawD7Dwe");
        corecontacts.setNativeLastName("RjiNC4nvId6zj9imDt3cdFos3iNuhUgAzq93GKEFYjzd6KOmsJ");
        corecontacts.setNativeFirstName("qLv7LkFBrOObRV1fVGhfWeDn0BY6BHTkoGqTWspYVKVPRR5E70");
        corecontacts.setEmailId("ZhhgiotvGMoCW7ka3JNskO7xebzvcVsG9SHyXyJGZM6vYPxqwJ");
        corecontacts.setPhoneNumber("4OrWn1LJQz4twflCtiSF");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1461247751252l));
        corecontacts.setFirstName("Xn8OxmhMroVdCNwUbn9p6l7xtDZkdy1JRyqiJOYLkPDCcIdx2i");
        corecontacts.setAge(15);
        corecontacts.setNativeTitle("lelaPPjeoO0sUKW8aGqzvPPstwaVtMx2WEQIDBPeb8xb5NaY6p");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress3("owt2ByBOamLv6KqZmL1bS4iGZEPmnW1Vj5p4JYsJYKUfeimobW");
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("edhSB9NWAwXLqmwL9X9HKgufWMzn1nnrXOvnNZjaLTrM5wwk0u");
        addresstype.setAddressTypeDesc("Z5Is9ZkpeH2wI6fqsV7iSD3A8UUMBqUnnnEnhWTIW2qHgOa7uy");
        addresstype.setAddressTypeIcon("pTvjXgC6RlFQ0wdsjjUVIXE15nSJD2BKQP6NSwsGGNoyb2t4FZ");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        State state = new State();
        state.setStateCode(2);
        state.setStateName("CUjhaymWyx1s87EjcXXiI7gb44XmfneWQvDCyY0H6nnEweZ6rq");
        state.setStateCodeChar3("e2UYOGPJBkj1pHngfIaHqhQs6X6cGcTA");
        state.setStateCapital("cuYa6Nnt3ZmOEjDNU8q1Gsa58LwF0jFarjQ3cMOxrw4WHMK3HK");
        state.setStateCapitalLongitude(1);
        state.setStateFlag("myoagTChWFWrXc4ISE9bPmYi25CMPtaj2hhvj0lTu6WkUZcgyO");
        state.setStateCodeChar2("94N4lYlKOC997z0l59HMxZuq8Xkmhi6W");
        Country country = new Country();
        country.setCapital("9SonwFyGJ75ShTNST1jIlEdboz2KVf23");
        country.setCurrencyCode("QQt");
        country.setCountryName("df2oItE5v8XDBpRafHPEMsueqiJL89Gxxwxv0nkFgkMeNgaLqW");
        country.setCurrencySymbol("krUDgOsqqQ7xl8LFXMF9dKub0fZnmM5L");
        country.setCountryCode2("r05");
        country.setCurrencyName("wdFk0GAK2sO3NfEOPT4zwou4uTVCOkymzi5ADUE7qHGTrvv8qm");
        country.setCapitalLatitude(1);
        country.setIsoNumeric(180);
        country.setCountryFlag("BXDvWLdBfruUugbP4Umpq1j0wHq3yoeXBSbzeJ070dB4IFG6eM");
        country.setCapitalLongitude(2);
        country.setCountryCode1("6T9");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCode(1);
        state.setStateName("gHvXx11FnSMPmSDI9gzctMt3mClFO2jzbQNadYxix5IyKLgQzq");
        state.setStateCodeChar3("2pa85cu0qwW5JgAju8Z69wnxoh9gJUVe");
        state.setStateCapital("RNmZgpn7GGLTUlTHado775Fxa4JRlC5jRg8MluFOZhCPUwJghI");
        state.setStateCapitalLongitude(4);
        state.setStateFlag("oQMFfFeaT3zaLJYDfor4YDoGgXhT4T7IS3wPs6HG0hHhanvrFY");
        state.setStateCodeChar2("GADRu83UdgkUHN5nQy639iDiyUAs70JP");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(10);
        state.setStateDescription("y7yGIfu6jDjuWYuIXHZ8pvf28Sguip5abOadhkTqs52Z1ZfnyS");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(1);
        city.setCityFlag("vkLK5AphnIcguXgqCBQMi0SkRTj1VIp2WQG2YuQZsgGI6BZqag");
        city.setCityDescription("QcW1CQQQCOAXxfBvXsnlG3CxqsxXVKKjHDoKEaVi6NJzMhSHGI");
        city.setCityLatitude(8);
        city.setCityName("JkWnjvQ7mnWUNkuYY1dcfr2b1Z9sVm4n7SVVfUrU0eogM7Nom9");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityLongitude(6);
        city.setCityCodeChar2("piFpVtgnsquaLyVdYb6fl015aXvQntpx");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddress3("BlXthY5PlC42o4gEZmxDYuuwc2No4Ba0s46OOdiIp1jxtCkney");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("WpzUoSEKypG");
        address.setAddress1("HoyUoyO327YC1DxI3QvSedehkulJVg4W4ZtbiCqOhxHzKpcHBU");
        address.setLongitude("I26niuTFVl7Dv5FMn9P2eJVAHwoXnCefxLlEQzXAcaFqU6CI7k");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("RZP8DO");
        address.setAddress2("gIwq4H9ULKsnDANVtElT8KOeMj9wFFPLizVIGuKkeaQwjVbEDO");
        address.setLatitude("pGmzk2dsurS5jKdUiEaBBU4snsiJosWUkqhB5TmP6Die9cB874");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("Z7O3KscVc2");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("qeCcVoMcMEhuLSKW76en1KwSYjYWlLcLOEOYcB0X7HeIoGfi9i");
        communicationgroup.setCommGroupName("0ACKfWnkr4ghG7ViR0COmIDuBdJeekle0iautwfYFdesBQTDoc");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("X3AZHx3fAuToHBJg9kiVnK4wn2HpwTudDIoxtYj2ks5DQSnEMS");
        communicationtype.setCommTypeName("Scgqr5KVeaSLa2BG2J1wslfUWo4HTkPGZYYMyNg00arYnGxdgU");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("Uaul3nMiMVD3kwdjHPoqf1qhzgGszdwnbh5ABxr4vMY06UMi84");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("s7vO3ZpTjv");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey());
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
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

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

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setVersionId(1);
            corecontacts.setDateofbirth(new java.sql.Timestamp(1461247751657l));
            corecontacts.setNativeMiddleName("RNLE6fver06kwHsV0tXuGedjLjpfPUeG4z9obhrVRzv7xKKfv8");
            corecontacts.setMiddleName("SnNDTlXfEUbEzMkLtp3MQRaNgLNT6CCfvA1SZJVq1aiLj02Mdl");
            corecontacts.setLastName("ZOb91EdJlJJ48Hc5CEziqAAUVF52tMep1iCkY2FP7d42zIrlFy");
            corecontacts.setNativeLastName("8KV8upjBQdE8LQo7haqSmIMgMxYrc6qW5TZ8aCAuMrCUCrZWvy");
            corecontacts.setNativeFirstName("rKVjEaigfJW8pFlGHPAIVD8CmuRN2uvb4vgUIA5LdeMu4JYeWa");
            corecontacts.setEmailId("mkQdRK7S0F1Udt5atXLT5xEoiO1UMIr1viZifERwImiguBNu99");
            corecontacts.setPhoneNumber("l8Alh4iCOAZvyOteYbWJ");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1461247751770l));
            corecontacts.setFirstName("FPyHjbFYH6ZNdA0lVIBmHVhNSELn3FB0bbaIm0ww4ibgYXAvvO");
            corecontacts.setAge(96);
            corecontacts.setNativeTitle("vhi64x5xhVcffBS6SXvwlQJpnN8ihcQU6TdvpmmPLjx6gFwCDC");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
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
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey"));
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "RgB5RdQdRAEivKBO7ROKPEa1iA53mLXrTYJwT8KPxSq9lWjl1BssnchtfsN9skgp55xRgXdXg5MZWpgM1OGDUtYVgTyKpLG8Cgi7Bm41Jrl2oReGCOCDWSLplWaJZYd2o6FjeUcMzJegcAcdaero4lnI2OKY7tdkC8CZEEBAC534ckqNWiRdAEsVtJrOAMpEgDhEzQNqbt5zS9hKC8KGGbKk2qFMBHLvf3Jew6HOjLDXLWGMXkIwd8MdKV8g3H3Pt"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "fbWtZlQQ3Dz9lcEXJHv20pC98JtBi9Ugs9ytzzHjyhljOeCyN7ZzvTVAfXyYoSZWIGcSiBFwkS6Qx5NUSrb3bwPg6FEkv3wTNx2YYR0BQB376Yv3f6YKkdyN0xr2xIHz8iHurRIkFJ2QgyyjJrCXaY7gdtFq1MgmJdCYbbPIbyiP3OHwESy7Dhce8EyphXZcRJYY8rElHAWBAae60zjfKfn5WRbIhRVdxtxppB0sZwTVSJLTjsFTjh9hiTujtB9LH"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "3eQsXnh53SvQQqf4kH2O7s6Rm99RpGA8JENRNNaEJ5vHgoFWY4f5tIu5UpkAKaroq1m946M5iuqJ5VnzDZTiq5sJCHUGtzyPzHsDQyfW3AXlD7el1FAE0bv2rqs7y9qG6XJJPqiMDo5BEzSdASu7MvNkRxbXV2HQ6Ntikq3oKFs9GBeZWEmVvmrEiUOsmWcxWL5sumutJS1WANuMbwiyIWaujY41ojrg0LB0zvROHJASG2JjNJsqKzONHBEp5wrcL"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "NAijbZaSjaOPgWsJAI8OtsVKIVSfrrYHeCDiMoRw8LtVtnA2aEIrkSwHCWWCcjvUi"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "8UTpTouBC4cowwqthCo5c19jArs0xMOR2XxB7ydLV4Dfp9txj6MrrAo2i2DP7RJkFAwDE85YaPeCOgWDiyDn5UmSBf6PvjLEMmODGJLUIkmJpVkHZXQVR7OYbPbZBTRfU3KCbHGT6t0FMf8B63nQjtXMdkkr7aaEoYMEJhUN9mEi3641mMGrJBLrnMMrKdka5qcmcPLV4JWcS2CK7y0oRb3iJcz8XoDnaCWKyz0apKAkGY5vWNchHeI7OJP8w8nG4"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "74KFR0LmU9yUhIbHHmtBKqpGPW5yWi4ArYfFxXUQ9Vb8Nd2LGAOkevhlZ2k0vMiUpB9G0EG6LBttDsTL5t62tOp6J53QcE1Xf5GQUsIJUyEh6S14e5DgH8gwutOXSRbHNqoOCdx3Kf2EVqQZdoJAv7f62P3tGxWLFyAMDrJfmXP8lmZJTjduCPVrEuVOA8D1dU1PtfM1ktgE1DLFOPuRr3SmxVs223veYhwnRCiuw9Xer6IyYmDd3As6bjKF27LKM"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "tZzISzikXDl6vlRFE0pHPxku1if3NeZpJHF7tGcqo2v39fINYTASoolTFk0LDo8r3hPVSQtFXEKj8g5qyGWSPTdm6iyHtGP0FnnJeXgrsyOlEHpGW01CbKf7UzNqCtYv8Xdeq2qMdgWFR92UzYx3jkXy7LLVxX6wWLPga9YuQm4InJye0FAdAqkVcqavwKavw1jwQrfr2IzVkgnsR7CHkPQU796uMzaaOOLTLPuYANbz5SUeOsXRpHk9J8VHj4eDY"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 248));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "iNLy040CFgPzd7YEqj7KTzf2hyilbldtYtRIFYVQQMrKkUvLukIe64QG6tlw0dk4Ij9JyPW57uMOaA5g0Brb0e2Zw96adW23aNQxJRAJwABuuOoKyHCFqy3BUzKfE11szVy6Xp3S3HDxWd2vvYrRLtPBbNNm14PsbIdtA1mO7ojuvWXXhduSi2jgRKT60KEpGM0namcXCJOpmIm4AkeYJoF36VeIhTp4Kmtb5sJNGAeufcvlPoaUWkNkEkkesvcz4"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "PUYSkP2UlKWXplGqtAXkn"));
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
