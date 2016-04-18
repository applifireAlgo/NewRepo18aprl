package com.app.server.service.aaaboundedcontext.authentication;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.aaaboundedcontext.authentication.LoginRepository;
import com.app.shared.aaaboundedcontext.authentication.Login;
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
import com.app.shared.aaaboundedcontext.authentication.User;
import com.app.server.repository.aaaboundedcontext.authentication.UserRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessLevel;
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessLevelRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessDomain;
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessDomainRepository;
import com.app.shared.aaaboundedcontext.authentication.PassRecovery;
import com.app.shared.aaaboundedcontext.authentication.Question;
import com.app.server.repository.aaaboundedcontext.authentication.QuestionRepository;
import com.app.shared.aaaboundedcontext.authentication.UserData;
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
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        User user = new User();
        user.setPasswordAlgo("d1UjeEQOOOtOoGQ38CY14vnflJ9k2UCL2mkfzqUEMMyn4FgRfo");
        user.setGenTempOneTimePassword(1);
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelHelp("ZGoPzcrlQQOygK6tIQuo8V9CiuSR2HUmbjxRdBO7lzStLY41En");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelIcon("JtyrQ8wI9gpika39fV5gfjltKkm91nt2RzTZaiudh3D3ouJxSR");
        useraccesslevel.setLevelDescription("uUEirpKVpBtL4kRSgyzoRyvMKfS9M7bxqfmPnmroBYTgpE0OPu");
        useraccesslevel.setLevelName("ijVEUPGbOHmCF7VLf1ROZ7xoEjMMA6ZZTynJ18VpTVzvzR0ycg");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainName("iV3az8GHHrmEVGR0j4NJwHn29oOoaxX9VUFkfOS7hAyKphueks");
        useraccessdomain.setDomainIcon("hms1rZRsVA9pVjQAUTSKChxiJwzSqObbKKnnw7KmZpjteQ6wW5");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainDescription("sBnQyZjAfnRZWG3HBI0937rY5RiusLp8zxrdLdPiedN0vQgXeC");
        useraccessdomain.setDomainHelp("6IapAaDV3S4ruG2I9NuKSM2pyu7l7KnQRUpm01b3DOs16bq9Vb");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        user.setPasswordAlgo("VpKBuqdrhnpA2mpUi3VjkpH2DyvnbajWfYXbjn1D0HBHPGVsud");
        user.setGenTempOneTimePassword(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsDeleted(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1460977054222l));
        user.setMultiFactorAuthEnabled(1);
        user.setAllowMultipleLogin(1);
        user.setChangePasswordNextLogin(1);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsLocked(1);
        user.setSessionTimeout(65);
        user.setUserAccessCode(39820);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1460977054273l));
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestion("GVB59qOChg5bxwhMdoGG0tYxh5CZZyDSZEfTJQZwdAywUi4Dfe");
        question.setQuestionDetails("K7Ng0q4ipn");
        question.setLevelid(3);
        question.setQuestionIcon("XZLcSbTsStxJWQC9dTgP8Q7Uo3kF1D8l2bn8CFugsJQSxyJSED");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setAnswer("X9ioBxFeIL60iPJkxznFNTjJhKSP2HFw1f15rBQBfgwTtQjUNN");
        passrecovery.setUser(user);
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setLast5Passwords("WWFOywywZkFjPwqnEqekagXYCCXjqhxVLd17KlCq590VC9WAVA");
        userdata.setOneTimePassword("N4ENYY4FBUfQ2Ju4aKoUHBphGfHUJfNw");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1460977054497l));
        userdata.setPassword("hj09x9fG2ACy4d3xyfzVMSbAyiGmoCzMTt7Q4DKTRJdyOECLYy");
        userdata.setLast5Passwords("22bMMjTjbvhGNjM9PaAd58MUBeGHLlESPHD8fPgcNnZUUNJNY0");
        userdata.setOneTimePassword("ECDXAhWM8mTKDtL1FweRCGbqY1AhJt7O");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1460977054529l));
        userdata.setPassword("67W2bthuFV9R1ZyLxxt8QEnCB08m31owWz6TlJuTRW7ijiKojF");
        userdata.setUser(user);
        userdata.setOneTimePasswordExpiry(11);
        user.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeLastName("zUbE3k0epeilInKZnAblVO9iBOeEqk5IzwomG4Pj3InXd00jlz");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1460977054649l));
        corecontacts.setPhoneNumber("JP1AqYJOH6kpDsbNr2sW");
        corecontacts.setNativeMiddleName("SWgT7bnCAKKq54JVWyZiZM7xxt5x3kefRzH6gA7bAJNNudnlRW");
        Gender gender = new Gender();
        gender.setGender("92X6rGzJRNnNZV1wSWm0Bo6xOsEZVWXbd3Npquosg3YjBUqSKg");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setAlpha2("IQ");
        language.setLanguageType("BXDjRZk0ZEwadMFp41NX3ZrWBwlgEWqY");
        language.setLanguage("jCKPGrPVI7clcpnu1U66T3RlfvTAT1Xz3d3bquco0bH5izv2mQ");
        language.setAlpha3("1zo");
        language.setAlpha4("6kZW");
        language.setLanguageDescription("1Fni0yi0yx83XovTAi9BXF8IwIoYz9GceMfAfbakJFLNRlU0wJ");
        language.setLanguageIcon("1gtfiZmXGFMA8Bq1wFAbxAXFjm8GzBgA4u7OWWOrZ7kWYQeLhQ");
        language.setAlpha4parentid(6);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("wNq1k9GgsuytyiE5JOh87C0p9ziKyIIAEeglCejBgji5in8wGN");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("g56uKmrgRCdqFLnLu0j08Xg9UxrOmlp5drPsYxjNVXT4Z1PlQZ");
        timezone.setCities("5j19r7jNwxfzqyrlPdXLIq1yZL8prtIoDosLddohi4CtbbmvIH");
        timezone.setUtcdifference(9);
        timezone.setCountry("elYgz548NWxAmMfwMaetsiXhMa7V1UxrcLhvy86YQIiBoo3JDV");
        timezone.setGmtLabel("VmNoMTgRClIV98bQXvM8e5yE4PDuilhVoZRcSg0bXlFCz1MePE");
        corecontacts.setNativeLastName("fDP6iVzo8EC9YPtgRPrIsemjFPtwpETaKuegReXPFHBFkzAjIf");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1460977054670l));
        corecontacts.setPhoneNumber("OXe3OlO878Aq4tztJgaz");
        corecontacts.setNativeMiddleName("PTDuiwJ3IefzeAhVn1I2bESkE0YLzqjgOFAvqWAPhDOPnVOoMe");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("5usd9xc5ogfC138cqL0RgW388gBaumTsJdKLMP6aSkW4aXJD2d");
        corecontacts.setNativeTitle("1KxLqxOKZjd8ERQiZ3hvl8ZV0ISYFZx6Vvy484C3L7HuTZzOxa");
        corecontacts.setAge(77);
        corecontacts.setEmailId("e6zQe0mcM0IfCJTLOmn8xUirVgmlTqV1mocQuP6tMcQdZIB5Jl");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("qftLKr682JDjOWHJm57LaxEyccOws7PTv6kkoXWu1JhUVWnBdf");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeFirstName("vVWPGP6CMemsoghvSJyEnDNfi6bYahFrbYMnTxeAYRbGCoYPtA");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setDateofbirth(new java.sql.Timestamp(1460977054803l));
        corecontacts.setFirstName("IpA5IwdG44ogTaEGG4eVW7YlE3QU4DHV8Spb4ZTNO8SlKsYDFB");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("1pvXrqPKbz");
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("m8HqJZNWF37zzArBfOTZSPmJ96AdO4JlhJxKardpgqxPrIQ0gn");
        communicationtype.setCommTypeDescription("bAIgUkTo8QGExau6GCtcY8fxBziI2g14VDOl42KwHAMDwvVCXu");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("7lTfFBbNedSPl2F1AuXYGHgOMJ1lzGJKVhvn6Ch9STTtBgr30K");
        communicationgroup.setCommGroupName("KSzIkjxmvNdBaYV16un1zEo37YmvhgSKCx2rb83DsJXlpA1KO0");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeName("ByjpHo77nfY0TOLQfBjSw60nENG9QCjcwqj7STrXJFHm5nh7UG");
        communicationtype.setCommTypeDescription("nFt4zfv8nZNKREqSo2uzuXiLcTsXZNLnCKDIOK2h58M1n6YXnv");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("uCNfy0XOOp");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress1("IeJTYeoudPH7O5vfcA1vKMmL8kHfr2t6BQorczctfWmvsQodSV");
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("flcPRCMLYESOU87t0PZDdRb0CC2lljUr6bClHZO2PlZcKKdOQb");
        addresstype.setAddressTypeDesc("YrT2sJ0rsz1mWxEV0sjUivr1eiH1LIQY7EgVDSLRTN4TPX70MC");
        addresstype.setAddressType("EedpaJ8IgalS8CbG9PPPlJ1Gn6OyRjO7IejIORWYwHbGePxDD5");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityName("Y70FoWDZx3E7St29Qcsf2UhlyUPwWn7XXCGdTV7bC3f46SRTZg");
        State state = new State();
        state.setStateName("ZmHYMLVTCi2VNSYaEeG3IG5QXFbo3Cs67c3khZhjHsj7w6QMpE");
        state.setStateDescription("luXub03DJtvNNdI1ojKHWSsBGHYrcyUAwXlIku0jfVhkEXe1he");
        Country country = new Country();
        country.setCurrencyName("Lyhr39aAICw1z01FsAHZYqwpNqSxbbC7js4Sc6ykkV6I5FkeNo");
        country.setIsoNumeric(827);
        country.setCurrencySymbol("L4s1wGTt6YqIWs7A8PwMeZnjl2XWXNlC");
        country.setCountryName("OL2nunE71CBeAz1ABrSm4AlrJQ2crjzWYjfBBbxgucPBKoryXZ");
        country.setCapitalLongitude(5);
        country.setCapital("5pVHDL9qh8OVa1MyhPQbIts5CIuNm0zj");
        country.setCountryFlag("J3SunWK0nGEVGQbIG1doB47rFDWroadsR10kjypGzEKbZWCSx2");
        country.setCapitalLatitude(6);
        country.setCountryCode2("yUJ");
        country.setCountryCode1("p8o");
        country.setCurrencyCode("PPx");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("hVbw1xrVgBSvtieokCZ3LtJvGlBk8kF1yFdg2oMpnldeR8fj1V");
        state.setStateDescription("sjkUYf0T1vMZxLG24Q6Z5MZuLJ9OCIfGX2X0LPbt0RK1UfooYu");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar3("hrkOhc72cyfRoxTkcJUeBu4A0iqEL8Vp");
        state.setStateCapitalLongitude(6);
        state.setStateFlag("1q8z8fk6mufIYQo4ijON8FRdyAxa38uWXd4X3afXivsLZnKcpO");
        state.setStateCapitalLatitude(7);
        state.setStateCodeChar2("0wdI2vcbGdQLu8AUgXdARzfOAxGEyuRn");
        state.setStateCapital("EuOENCh8zrJfK1jo7dxKJCdRbx7sHgeuhciOPuaJc9OcnCSZ8q");
        state.setStateCode(1);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityName("SQIOYMVbnt6UnjVHg4utKhw48afKykiXbeKcW59EUhPa0zYcwD");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("T8vQxAHaICWT1Rglg7LNj4xWcaWPpJTEonamhqsedu1fPineCL");
        city.setCityCodeChar2("Okca05KXpyoVdD5fOQHV16SD0sFabTUN");
        city.setCityCode(3);
        city.setCityLatitude(11);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("V1q5FlYrqJoZiV0bsm6BFS5oa4alm9ro1sjbLD3cEEmN7Y5gXG");
        city.setCityLongitude(6);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddress1("gywopsnQmqBzD8XnZ1JiX2c9f1Zudo7qaaC9quVPr6FoVq2DV8");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("QoGarr3t4Lo5qU97CPa9HcE1450HvFoC451iy5gqHjqlCUak3X");
        address.setLongitude("ZIUBD7cy2qTN5PfGVQvXdX8by8vHzaQJYZBayob69VDYtvPPcv");
        address.setZipcode("VWLqrH");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        address.setAddress3("5ctrFZsIGlHDVGfFXWodP6WjDhaY01eXjB77zXZgBfNaKhSceu");
        address.setLatitude("8S66GmWrbxj2AGi6A8o0uHOZZeSxrnaVxATrUGjASjZZblsOuh");
        address.setAddressLabel("RmdTqEhOg7P");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        Login login = new Login();
        login.setIsAuthenticated(true);
        login.setServerAuthImage("BwJ6I3UZjCOMGtgq4sRw5E7HsOx3ZyIO");
        login.setServerAuthText("f55AQQI0zoSW7wen");
        login.setLoginId("X5TvNsZUL1uhzVx46PavCywKgXhIsYMLOyxbKT9BTAM4x0VT77");
        user.setUserId(null);
        login.setUser(user);
        login.setFailedLoginAttempts(3);
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

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
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setServerAuthImage("cD4JoBDxWwsWHFFfFPJjg2HyxHJyYzdY");
            login.setServerAuthText("wnAu68Y7WH2e32q6");
            login.setLoginId("MFNZpCKLZT5xdlFyfOOkhwfSkGaZ5vC4QIQgKxbYv2l30Fjibu");
            login.setVersionId(1);
            login.setFailedLoginAttempts(9);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "XloTIYTTASi7W3NTLae0gtss6KE9BXVYn7shXcrQZHzzRsaiX2HPh4yrUks5Q5rA5C3X7talikF451SwUjvZgBiPS0wEgw7zDzOAb7BadJZf3kYbel75U6q1fGA2ErULN0CHNIyFh59fjKsSe4kMHHIuB9z3vy4TdodbaJl83GdU5PWxsGvXVsQiL8EW8brVEyslovzw4"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "VUZsZYh9ZUt06gqxFKehXqkGiS3kYixBL"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "HGeN4Zyb0dw9TRsTW"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 22));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
