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
        gender.setGender("cbSwzc0keE30UqbsAEi82aEPmyCO33tsdG3bBMTuCiNqTyp7zh");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setAlpha2("wC");
        language.setLanguageType("Z3WtnG2iYgW2270yT1OUsI12t2nG1yW4");
        language.setLanguage("zS0KvPVIw4Cs4psfc4fEb5KhGZVH5xFJ4T9L86aTbBXSeQZi7p");
        language.setAlpha3("rZs");
        language.setAlpha4("NoCN");
        language.setLanguageDescription("64lq36LfzZ35ES6A61yyhvvO9M4u3Zd4SXYvDJnDi0CmTKmBRp");
        language.setLanguageIcon("WbSgJo7oMf8y9S2qGspTAE3VwgkeTq7c2JjU9AOBIr2Q8d7rKJ");
        language.setAlpha4parentid(6);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("0BFfWdW5ao23DyfXLoIym44dgx3TZmmd7ELSq5Qb4p1oLzLSaD");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("uE4Ic9zjhl7MIP2fBgCaPrbi7GVlyIAUH1qvpJj8FVxD13wbZC");
        timezone.setCities("4fXdgVjFS1Yo4tsvuZTGOogxB8gTf6FvzJML7uvzJ4EL07IDaW");
        timezone.setUtcdifference(8);
        timezone.setCountry("tlneCHHb1P02fABNVO4yOJ7R3Nz8sDIXpuCRA8KbWQYNp3F7ZJ");
        timezone.setGmtLabel("dJEpFl4zEzIna3duyDenWNLymqJGUdGWrk17lNbrUK0OsZXCzd");
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeLastName("VWLiRSYAwMdUNhzZWNfp6HJbHD5GEh4IZTUGIMkpuLAbk4Utqg");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1460977053114l));
        corecontacts.setPhoneNumber("7X8Okw9YrqpuRtuyxtWm");
        corecontacts.setNativeMiddleName("BKWWrSBKyW8IoXxfPPvf0Ulnw59NPKrKr1rvImXmdSjh7F8clb");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("EXCSTvHFTklXdOVL8jR4dlwm0bLIHeF8NWjQkEZnm519PjnUVR");
        corecontacts.setNativeTitle("hegXCW3yFIoBIBMKNv5M5mGrHd0xUqWFtqUkfVjMOGU8WiFYh4");
        corecontacts.setAge(41);
        corecontacts.setEmailId("XTY5hnppAAo8Q60CGEABOQrnlLxFOt83Yq5hJjAUNae1wwU3Mg");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("71Tx2ijuxUt6WMFuCS8aLo1f4YJh8GOcvaqbcSKWpqZRr5wTAw");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeFirstName("NBlDtECqdok5aifwzKzoWRYbgpY1ZmWtGghKltETLRV4onwlUE");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setDateofbirth(new java.sql.Timestamp(1460977053300l));
        corecontacts.setFirstName("e2WYNj1b4eOe7YXuQwwrXVxQVMWeOcAlpRUzDa6ii9LUci2sr2");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("WUoNTkh3aI");
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("dHP4VKPGUrT3x9cPBbeYxeve2Wz3laEAvmLzHBmvUdMgKRDFki");
        communicationtype.setCommTypeDescription("hnxErX9MCyWMLZk4rQVnz4mDm7ZIplxtt4fax3sHekO1TDbHC6");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("uGajxi9zyX6Sz7zkdkKc6YwT9dAa4XTrvuZZTsv808QBztpPD9");
        communicationgroup.setCommGroupName("1z7phW2BjQWsg4bXgDgPPelhYWrUnPnkc1zggk67bzwixhPDmT");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeName("WtM0fRyuxX2djFwmVEyXSZsQVC0S2XkdsIjDDZpq4E9DWo6zSO");
        communicationtype.setCommTypeDescription("yc90wkB04NycqRonmUNf9HCjHoKywlkZi855WEIuqiaWUWUN38");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("3z8oeKToOU");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress1("QMZpFyurmrn58ZPSaLzS29j72Ad9GG75QlEA5Hj57DOXAmCDMJ");
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("gmiPoxnRDnL5qaNJWJOJgKw5s6EbxJwMP6EkAxTL2szifvX0eE");
        addresstype.setAddressTypeDesc("nUI9f60pmMbMYZpksZvAnlOi5WJ9M80WUhzqMZwHX3bdxIE7SO");
        addresstype.setAddressType("a55tij7LYTgw3X3cTkjjCMEaanNVcSTRcgDQNh0llvyNd6VCv1");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityName("QqcLVvNfGbsjnrRSkrE11Jy233BXshPwWmEXeZA9PLTregSpMs");
        State state = new State();
        state.setStateName("K9EBsConW6AfinRBeWp9vwNBRWIi2CafCVehUT993VIlBG4eed");
        state.setStateDescription("4Pww7BbbbeCSFn8VkTsmFVfHXcwgK8udPULHn81SlmQYFpRHwe");
        Country country = new Country();
        country.setCurrencyName("h0nn7iZoyWpLZBfc0TZDFL1yF67qKpzlvpYXmYO7ngLUxf2Uak");
        country.setIsoNumeric(944);
        country.setCurrencySymbol("c08fNEXZlGSWorIC2cjFP681iLUQZ78K");
        country.setCountryName("OwihoQckibguhRFkPpewzxr39XhSHr8PleSiAdae7TSQuY7PCX");
        country.setCapitalLongitude(6);
        country.setCapital("Ap74N56Fyk1zzzsVjbI0vZS8zboTS7xS");
        country.setCountryFlag("KHyHF3uzpx3iHmEYzuQyZpwWSBon5RYFJFvzhA58xO013vzGYq");
        country.setCapitalLatitude(9);
        country.setCountryCode2("1DK");
        country.setCountryCode1("RdL");
        country.setCurrencyCode("7vb");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("t2PJ5Gwyhlve8KuR442lJ2OcoNnJgAcRq4dAoIZ7r40x2Rx5D5");
        state.setStateDescription("RIF00hXgoF823yZnM8jdUM80Fgp58HRMf6cobH83zHIXUUyng7");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar3("hIz35UiFwtiLnhSmCO1cmt4ZKDFRq72m");
        state.setStateCapitalLongitude(1);
        state.setStateFlag("TJlzB9MDdct4hJJ5Y4Bn4tmxXKPEl926wXW8IJV4hYVgrF1Wbz");
        state.setStateCapitalLatitude(9);
        state.setStateCodeChar2("POVUL3KhoYFvG66MgnrGLBlfyFgapRR7");
        state.setStateCapital("u6O9Gr9bCHlOD2vHnlbH79IX13nR0koLhluomOzo7NScrLBXzQ");
        state.setStateCode(1);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityName("gHcDD5lljBP6sFOdLc2rKC5XNj3tv5ytcfLgmrtCE1bPsrc6Nq");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("Mtl60sdg8VhmCKKYmthiKOcAfjDjNbFqbOdn4Uqo2KXgSkfnsI");
        city.setCityCodeChar2("uNILfo2PANtgon75SK4vqLG2EfkHugDj");
        city.setCityCode(1);
        city.setCityLatitude(9);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("G0hxSVOAlnE5xrMMTEcPdbCtVgqvlfcMrWNoHVaeM2B6YY34r9");
        city.setCityLongitude(4);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddress1("pB7Zr60raPR7nj6a7WBGuUHePQKVDuZZ1duqvaQYyaXXFAHVPM");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("vGFmBhhAwNhUEBoF5TU81UAUNBPKACKKp0VEacMwUUMHiuWphA");
        address.setLongitude("oi0tYqncYnf3fKfb7LMnDYni54rtRv9rqGvPcoPHUfH29a2Qg2");
        address.setZipcode("6ydQhr");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        address.setAddress3("mG9L6ln902Fsfzu6KzrBOG8bennKQZgsZhxKoT36S54p7UgPh6");
        address.setLatitude("D8t8jqNT6EZ7SU1kAThOnWke215ch9eyPTdHAmIjtwvUjN0ptL");
        address.setAddressLabel("IqDw4NLLIQi");
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
            corecontacts.setNativeLastName("uZ47LrIxgptvciLb1OyRGU6qhXVD1xJNuae9cD9wmghL5dyp2z");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1460977053674l));
            corecontacts.setPhoneNumber("qMEqsgJ4hV1vMhaRhRVq");
            corecontacts.setNativeMiddleName("EbynVSkDgB7enhyk0zq3DtsYrLOiHOOskcsbELXUeRfXST3ZJS");
            corecontacts.setMiddleName("juP2X9cOwLVpK8iXl4tkz1fF4GOJVZ7AlDXHstIlgqhAivP3U4");
            corecontacts.setNativeTitle("Zu3NKzCqx653mHGiHUEYTCiMVGW5X2BQNKRY8S8rbGAMZSSE0O");
            corecontacts.setAge(119);
            corecontacts.setEmailId("BY54urBIpIt9K8rpPLEKnhEWSeDNCzFyqoJRjyVYxedZIgtM6N");
            corecontacts.setVersionId(1);
            corecontacts.setLastName("fVn50VHv7qi9iLKyn7tn7Cj361SKXHb5eXObFAOxWimlNuIOQ0");
            corecontacts.setNativeFirstName("wkXtNR4rcB8D6SCSQXhMcognvZScDeZKW1RLncqhwmHLGciyQe");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1460977053828l));
            corecontacts.setFirstName("tnBuIbAn8yZbjAeOhs9kfzYDaQKL4AxG08YZWQRQDgMyT4vAek");
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "sjq1JYWBUEUOnJmxzjz2oInP6k4eB0AaH5MzI5VWEs9YKviImcOjfFZ02vRUTcwcJa2dfw3uhgHh7bZFJHGvvYHcKqVybMSzpkTYuBGf2pHOUzBYEC3DvdkwBM3LVmUeRtASoR2TvdAZFVNY9nc5986iQCyzgqziOqYzdFkszC2tLQBL4huhIyfRDI5OORL6KaQLpb2mFPwSvcTsFXAii5hgISBoQFkmyN3YRBX4tLeQH7WaAv7Cj9GNAefbFF6Nf"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "ehjcDmGZt1st6GpY4qAedibyyhrP7SyzhnmwiR5Ag68aVKkjzfXT3Da81S5Md7qx1hzx4RSTgs7vgc9wdcjInx71AK7g4n2bD4OOhIxkUkQY8Ao6IzYclh0uv6tIyyuwwcheSk1VfdcbH4cJZIr4fNEPRsmkduXLBaC1L9WRts0Jlp4yfcCAKawu6BPuf3CLYkA3AJunhlQkRl2jKAbUV4GW3s8uA8cp36JjzN9dKjtUn1ZkxBMxilnI92Wm5QCjf"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "jVjxnpQME9aPLm1XDNQV88STobq75DvhsjhoM172b0ScXE4fdIHwrwPU7d56AvlkkUMohpmRwc0C3CRNTjeP5wUqQwJQzXwJq0h6IC0rSUePe7p3eBLbtmRVOTILP6cIx84oMPVDxcsxUXnAYaZl7XsoS4LF2ZI2SCHVAJLMQ3AISlD8asFNdCImJHPXRLeGXDk74d2Anuxnt4V7deFz3k2OQkKvQAHSWFMg8PYExafT3x9TAt7DlCoxYJSzFhO6S"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "9mASWRx3YoQXRJfLFiDBRWVsdnG05BjKK8F4xChmANoKpnesvUIfi7RYY8alTdRv4"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "AEJlEd3J2ZcDqdQzPqg5u8fm0yuSUQ9f3sIBHSb3ddToXQQ8sXDjGckfbiNIzkvHVBLld1Bch9whmy26ymqdJMa1BEwOYNoWGUjeUiY8HmSQ7oSCpNslDDVAplRITZvjSKTf8YvIEBPD0SlpLcAMXJIvdvEwMwgI1EB1fWOefkxnvhY9zY4Uy39uzWHgaYz5huE146m8GNEC60eAvNx9LKVrUcUIaHrIh0qBwLR2nOVhiZNlKPvSwg3s1DChHK1ab"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "MTCjAYGsaxz7ngf3506xaGHKC93y6UDpnBgXvF3XYYgOHDE9FsNZnLvfa4LdxLdZGDXRRBt9z1fg2Bf1vbCqngMHQn4laUEGspvGJA8IDKnDaoia43WPRXGZypsMHTIP7wU7RDSssNz4trkjiiGrIdpN6EAhZTE0gAR8AMB0ic29R3ZxRYB6iadlISMtt9BAqx03P8jVWXxiHnSHAdMLCC221BUqrsPpPea5NuM1GiPVlUiDnxhqf4HKW3KP8eZz3"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "1IfaZRSGlCEbp3Iuc9k1iEzkio85QgTIdAFxM8ZjyDfUhvNjOb6PthC3MvQJGkHuSn0ds3W3Z343BpjSpmSGfI23p7qRm80wYj2uKtVi8SXZ0peoTrQVozXsH5NHBkH85CxP0lfwHRwfGbwejllhTJf18mMxJrCz8Y5odVtmSgYrPzJQlaecaC2GqDdMWuhDt5AiTmv4DMHNkBv2ML9SV2THsNIqdggwvek896LyYlcSx5NwPxHQB7ZVNob7RPexZ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 215));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "XsmrkmvgP6D6VngMssRjpsMFLjHZmhCF9TGcMGdrDjp2S2JqMGgMDA6ndIzyHv5O3EP8jUVcjlDzaYUgkjO5VIatdxA8kYg3mvZAuUVCkHBK5hXAYhhMnB4RyqVZDKOgncwkz09DwS0ACA82DwbY3NohQ2UGqN9LekZBoDjapKfYNvlMcAtnbiXk2cKHKYzqyZ5UQgNqga6fPpwKjhH8iaroV6UALZLnWKcigziLrNOoi5OsbzhDIIac2J8yJ0hLb"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "c5s6s9L5X46VNZIYXFNPx"));
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
