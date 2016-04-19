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
import com.app.shared.organizationboundedcontext.contacts.Title;
import com.app.server.repository.organizationboundedcontext.contacts.TitleRepository;
import com.app.shared.organizationboundedcontext.location.Timezone;
import com.app.server.repository.organizationboundedcontext.location.TimezoneRepository;
import com.app.shared.organizationboundedcontext.contacts.Gender;
import com.app.server.repository.organizationboundedcontext.contacts.GenderRepository;
import com.app.shared.organizationboundedcontext.location.Language;
import com.app.server.repository.organizationboundedcontext.location.LanguageRepository;
import com.app.shared.organizationboundedcontext.location.Address;
import com.app.server.repository.organizationboundedcontext.location.AddressRepository;
import com.app.shared.organizationboundedcontext.location.City;
import com.app.server.repository.organizationboundedcontext.location.CityRepository;
import com.app.shared.organizationboundedcontext.location.State;
import com.app.server.repository.organizationboundedcontext.location.StateRepository;
import com.app.shared.organizationboundedcontext.location.Country;
import com.app.server.repository.organizationboundedcontext.location.CountryRepository;
import com.app.shared.organizationboundedcontext.location.AddressType;
import com.app.server.repository.organizationboundedcontext.location.AddressTypeRepository;
import com.app.shared.organizationboundedcontext.contacts.CommunicationData;
import com.app.shared.organizationboundedcontext.contacts.CommunicationType;
import com.app.server.repository.organizationboundedcontext.contacts.CommunicationTypeRepository;
import com.app.shared.organizationboundedcontext.contacts.CommunicationGroup;
import com.app.server.repository.organizationboundedcontext.contacts.CommunicationGroupRepository;
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
        Title title = new Title();
        title.setTitles("QXXY3MYn34dFhGRr0C1thY6FCXUviILY3qWjlpq7pKKivIlCOO");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setCities("G1N3emOtI09c4S0aPRXwnisCxetxxI0wJFxJPHmh0qW2uwpUUO");
        timezone.setCountry("V64zY1k36SSjmZfW4wHKYFO9FiDYcMCpmcA2NolhtJxKplPpMG");
        timezone.setGmtLabel("P8yyk2TZC1gCqcvU3l3ZZMlkGt9KkPrJhefHSDcJ7Dn7k7pq6N");
        timezone.setUtcdifference(6);
        timezone.setTimeZoneLabel("zkHcKmQyG4xTGYOWWnN3qMWJZ083o3fla1SVlRB2r43e1NKT9k");
        Gender gender = new Gender();
        gender.setGender("1DsugIlaC2hi0KKspofwdG4lqBvsZn2rqocGSpACe7HsAUH16o");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setAlpha2("Oh");
        language.setAlpha3("4I7");
        language.setLanguageType("GdLJmokQDNJxLTiJ8hijyb9ne4hzgD0f");
        language.setAlpha4("Dbty");
        language.setLanguageIcon("eG66SqgV2nGS7WPj5H6HSq7s9hRTtiMw2COODuV7EYmBUsLipy");
        language.setLanguageDescription("of364EirPYnIYEnMKHmeCsw6McplzfoTuMjfoeo4NxhwBYEEYO");
        language.setLanguage("5KTVkRw0oKH0zvYncQhwL0jtmC6OB652AHUiJpyHNAF0VQaGTt");
        language.setAlpha4parentid(2);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeMiddleName("jz8J49UbKbkTIhzPtEDEW1yXXbDhOIkQTZVKl3Gz464Gwe7m6E");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("bg7mufAG5qBmzzQcbAnq39gsFg3yqsh1w9JlEC9yZdNGsmx3uC");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setFirstName("SHgCVM1qhia6I8juTxR6HLdg24KojNsqaxpxQ6KNWPsEtfYcyg");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeFirstName("0kAkoMv1M0wdvEtwLAeqKgOjDjcSI6mrnSTftez0WcCfmzaf1K");
        corecontacts.setLastName("5QtdAlGkSG4P2VeGh6CmUznx68HAbXOxUp6XJ5EzJMi57STMFF");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1461062628038l));
        corecontacts.setAge(93);
        corecontacts.setEmailId("ta1vD0twNhIY8D9eW93FT9ISv5jWUbHvwCxdzJh2l5HzNmYw88");
        corecontacts.setNativeTitle("yKKltQsfKQfdmWQ0UvtxWlQcYOn8in92HajIB5ZMl6UtLGaQix");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1461062628038l));
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLastName("CYvbLhtOKYgjVqZYSlriEZyFf5JW90Pk4660n3526PkGiDLUs4");
        corecontacts.setPhoneNumber("lEm5STdfcFVZSwnWA2El");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setLongitude("4QmVHte9mOC3DCOAOhcocioUVlYGucvyrUebNOLVu96ztxZL8c");
        City city = new City();
        city.setCityCode(1);
        city.setCityLongitude(9);
        city.setCityLatitude(6);
        State state = new State();
        state.setStateDescription("1CCqXc8F8M2bLNgdaVJrNOWouJfzQkcbCJvMpenOCwbux5EW8q");
        state.setStateCode(2);
        state.setStateCapital("STGUCX3gttce6oyL1Gd47h6zBUHrBiggqHwd6ZRz0DTIHay3Xf");
        state.setStateName("FJGsntDHiqHaLcfNfG90gujDwLXeLLy2HDhaqDNkPisD52tozY");
        state.setStateCodeChar2("lzRaxy7n08rR0iHWEJVucNQlMD8beWNN");
        state.setStateCapitalLongitude(2);
        state.setStateCodeChar3("sGMq6Ban4YvglkDHMJlGbb5Eu3uMaAkR");
        state.setStateCapitalLatitude(11);
        Country country = new Country();
        country.setCurrencyName("TlVE5mhZgLtUeNnkkmZDJuw7lsKlEqoQwIr6rtF7YaHMoZIDvi");
        country.setCountryCode1("MMR");
        country.setIsoNumeric(238);
        country.setCountryName("dS6y97e6n9SHRBGh1AOBmKRquZP60kkLnziNaduq9oh6iMG6IF");
        country.setCapital("vWQYGFzxAXGvT1PEeZI5jikeItk7gwmZ");
        country.setCapitalLatitude(10);
        country.setCurrencySymbol("jnI5VXBZofRbPbxl9yzIRh2A2tjyamma");
        country.setCurrencyCode("pqS");
        country.setCapitalLongitude(10);
        country.setCountryFlag("CfgAeW0KpAYLWCxn1vGbXcErS4YHdejEpWf2CGOwF7TOGJZVLb");
        country.setCountryCode2("8nR");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateDescription("7m38HFIjcrYN9dYx3bNDQWpZHQmh0k6j87xJ7vCfPFDPe6G8LJ");
        state.setStateCode(1);
        state.setStateCapital("IQoHIuuCaST365bxe04FEIy5hsj0HQ5mhYQ4uevEbYXrezysI3");
        state.setStateName("B3Dq5xhcJQ1wXGMUhuh6hiGGL5Hks2idt08m8SgiUbMQJHyivA");
        state.setStateCodeChar2("Z8rai3VY1eeVPwiiFdLGIbT1ZalVdYeX");
        state.setStateCapitalLongitude(11);
        state.setStateCodeChar3("s6HOsNKnTT9h967lR9Fz0deds5FUzXN4");
        state.setStateCapitalLatitude(2);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateFlag("yM1Ay4jpM2ToPICJC7kdH8TSGzGC48OVt5VDtLXnmPArgT3sJR");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityCode(3);
        city.setCityLongitude(3);
        city.setCityLatitude(4);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityName("n0QEiBonkiJCFGvZrlfMQBIk3GYgiSiuXwvKjQSwnea75uFGZg");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("hdANfsfaQg1uJ8gVqFUNVMc0uk5yzSVKtO94HjShShsLkIjIuy");
        city.setCityDescription("h5wYHMIrttQyZciXaejpCDckw5CCf5Z0qFTXpVQTFCheAwQDMv");
        city.setCityCodeChar2("eVax0MOg3wyumzENY5ut4kxnTUQaitRL");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("1S5mkYG9TBwIJwilm5cK2Rgq0zfxZXAXXs9s2Aj4PGj5Udg8p4");
        addresstype.setAddressType("8N2w4fA1kLB12SXuA0iL5tXJAjTugC0Mb6hjNHmstGUSB3CuLN");
        addresstype.setAddressTypeDesc("AbfOMrQayp4ZPrfXgfr2Hu8hU6RAnjCEem644AdBXaajbgAX3Q");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setLongitude("VqMaqMqJ4O6u1geNcV9hBHpiAxY3NzFwiRx2PBkxyLvMSqRtfx");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("RmFDFcR3Nwj3bPpr3gUzDKSokKscl8ft56QzgVZYU0NjtaxL9v");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("sAF1nL");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("BgvzgUfmdFn8NJkrYckkRDHoy3ZWRROB8XRgwQH2EzpB02ntCZ");
        address.setAddress1("zy1Jnvinsq9e4CBfvXPESeDB7a58ZkZYdrv0QPp2Wu8OPvqMIr");
        address.setAddressLabel("jIy4TKlTCLw");
        address.setAddress3("VHxk8nmcn6yOgDU2ECKnGLwLBflWgdQDzIpri4eH0zytcoUXq2");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeDescription("XrTd94oW1i5O9e4Dvc3Ru0hDtay7ubsRiDvUN7o7WWTGYUlZWV");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("1oQkoX2vZcRMdAj7DY6ZgzA58BzF23Yp4Uz1yCHN37eoZ8IZAC");
        communicationgroup.setCommGroupDescription("Dgjstnmo15wyt69xlQsAi650pPFCn9GRsnojCoA3HGcQFwfzoo");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeDescription("Kco3IdxOZTbsHpPg0dO1cFXKg7wxsGA3WFu1APRG7LlL7GyPuE");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("k8xYLlV4DbRNXUDyrtWIYHYgJC7mUI6u3HfFNUqttkxrk5xmMQ");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey());
        communicationdata.setCommData("KB2AT06i0k");
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
    private TitleRepository<Title> titleRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setNativeMiddleName("ElZ2Qa3yw97uAaucav3621EedIUZslg6smMhAoVDwsJQzeYb5U");
            corecontacts.setMiddleName("UOf4Wm1kWYGwWCsk18nKZSFlD0l5EOVsRE7IfhfTuh6LBlEh1v");
            corecontacts.setFirstName("GNSwya8IvBADNfSvDqzrBJxQiEV0FwvuCOo4HDOBB5kznMYW4r");
            corecontacts.setNativeFirstName("NsH8cKrmapr5sidXfQKhW213Mz93mFxEEdns2yOtCfSr5b2PHK");
            corecontacts.setLastName("6Gx6yiiFjdz5xIPjfgtKaFJo0QOMoya3jzh6vyZV7ByZuKPGe0");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1461062628501l));
            corecontacts.setAge(17);
            corecontacts.setEmailId("4SEv0GBTmullobhAF3Hq1DW3o1A9QwunaaUsXqRgyTI6k1Achy");
            corecontacts.setNativeTitle("D2H2KphzmYpkmTGi7ce26DDGic0bsN2Cpbq5BJw2VicX4HRa2a");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1461062628549l));
            corecontacts.setVersionId(1);
            corecontacts.setNativeLastName("13ZKvVzwy7UcUHGpNsG76f2YtPW5RzvMGjRBTVcbqKl8IlfboF");
            corecontacts.setPhoneNumber("Rbrk80a8xpRWxziMhm1R");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
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
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey"));
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "I7MXJ9HKME6UX7j1BF9EdTOr38vdvtPhAAX82l7re1JmqVP5KvbsyDvK1o3RTE5fKD5PCKBx0xXumRDPKFWnod7p7ZWalr5AjxoB106Hc1u0EYwwfzy34lGGaMEoZT4LIOw28x3SZCFjYRzinW3MSHXViqWGCtO2VY9vSRZaSdITSclzJquZCnQRtKqSuzAqD4WiyGQjDJK0eILUHHbyVjRlnOMm1AdYsJV1sYXXlgF1E6b0jUF9tlMt4qoNcaEKt"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "AQlagB1p1JQpYWE343AinfbvNqWTzt8rjDSCXVgPs1CWdh4dMw75Knk4mMblyXz9Lkjr7qVUoz3TvOsT7ZmAGQZzk1GIN9Q4k8tSxlIZXotcwkPxyEH7aNX1CRQydcx8XumAVdD8Qftd8Dfh88yx7zGBA0QCMrYjY0Ih4uN45VGCtRHQdb5Kzo6fIUqQ6d2CqdbxULXGmVwUJT4sxTOAdh64rNiU7sm1Q4mUmRChE1n1y0y3LY9J6VLJI0ECNbFiX"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "FHhGyW91EclQG8Ay9t0CzZ7eTvqaPRdw7hKitl4zApHn1O6Hwo4jKSityJhWJqNSBV3y7eA271Z1nzTAlIMdtFJaL1YGQsXYT2LatsSKd4m6fbE4M4oMdVRZyMKi0bYVlFGystSk4yUC4d02fNVmef0ScRjF8nCAO1WenzGmTYxk4qojtpanVrXcvfjLpH6YBAlkxzfK6jw0z2ULlC5Bdx9ljjAYu3n4swolGsCVJjW7yYbaxEg8PM63yDfTA8BQq"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "y9h1q600YYaTIEJaiGBWvprUNxkGJrFNHgFL6GUZvpnTthXlNSksOPuUgrWCCWHrL"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "KTJN8bH2AJBFntWT84kLiLkjncD90bzA1Pgrb08vELFIbWQIYpZMJrYabA8J07jeoMQMo1KttZ0EiWhhgs2h7rSdFGmX50zozyqFpK3xlV3XYT61A1aMdsYoiYAZ3jVRW0RvaArzD1H5TyOaUqPg7ooeUQuEVtHRSzKSMlXf9T5gK2O6cHwA4OsGlkvFsbygaRQvZ3Vkm5uamSUt5ZQc1enE0AWmleNXM0FZHpTgsd4hy69AHwQGEXJmnKMRaLEta"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "CgYq6tN3lyi53biNPNSyL0ToOidP1YygBmIGnj5gLNt33SxVOdWkd9OkRSfUFLYorycznjqXaOhLHkTLm7WspRjxATJomGpIARHrI9aI3E5wljT9NAj770qhpcy55Ru4iOb2T8zpmxr446G34xIgAt1pvODvjj2PdxFsOSHEmaUWV9M49HXHOVBzSwwhD7zZinCZUZOWte0Xn8TvclQdZSrLymDOrtjvdDoBseICTqL79ZbyTXgjKZjny1Ttsjqth"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "L52aXPeT5tHkyRx1O8zsARsK27jiUPLiRCiXYQEDyQSjIOUA3VodcKqrs5ueCrizSyNLnT24F6ufvajdNf59zwhv4YGIVikigbc4a0e4z2IHLTZZXOGjFnMQ3PCbFj5nkhpJvSTRXxBnKhCOpBGdBvZaD0Sqo45NXHqxqrrfUAtguL3ZyzyjBaKJFna0QNgQXwApqhE43r5meU2hZqTwfUizMHS223hfBNkoJyZYeks024RoNG6qFl9CnTLlgylYN"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 237));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "mtTng2w0nVIrJvqbytQn3h57x2UmZ1vRhBhW1COEXa7gA6ruxImrE9mkZeAGnWMup1nYezXXFwToOOTgsqtLo9QTOTUqXrTXzewIFbj0wAFklT7yDIdUDhl3fKRbexdsQgvmstwj2UxOvpOLOrToK5Lfc6o7fJeT4s7uUuhN1R6j5rYreeqhnSTsYn1gzdLe5Zuobrcrf7rLjWZv6LppKvZ5ui3ElZaHbQHAtvMM8l9I14rhQub84tSUACkPAkFiP"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "vuRl3opwFLj2LL6eyRpoW"));
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
