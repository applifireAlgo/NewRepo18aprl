package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.app.shared.appbasicsetup.usermanagement.Login;
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
import com.app.shared.organization.contactmanagement.CoreContacts;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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
import com.app.shared.appbasicsetup.usermanagement.User;
import com.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.app.shared.appbasicsetup.usermanagement.Question;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.UserData;

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

    private Login createLogin(Boolean isSave) throws Exception {
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeMiddleName("BA78uJ9RKZKr6wybhxvNkmk2Cla4stjAYCn2jm4D0yvaRHLgjt");
        corecontacts.setAge(106);
        Gender gender = new Gender();
        gender.setGender("wfKk4q2NgtnFwdlVh6pyTzSzPORooE3mFl25t8qTTqPu1TLEMs");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("UTvJch0OEeDby3m15kXoJGW7ZbeBzdkZJ5KZ1etruDOtyy33dw");
        timezone.setUtcdifference(11);
        timezone.setCities("pK4QDj4gQHfR6v0L14Ote2ZfKeN7iWBwqrexaQ7hKC3FPRLx2m");
        timezone.setCountry("fnPP3rRbILnpRtA2OFqFVgq5Ctp2DI1sP7BbdJKpmILTO9OkuM");
        timezone.setTimeZoneLabel("9Eg5S88VFCP3VPptMuPOgHmRdq7CZ5ji3HrPnEO4erBwdQriEE");
        Language language = new Language();
        language.setAlpha3("8wH");
        language.setAlpha4("DBu7");
        language.setLanguageIcon("bi3M2HE20Oc6ixy7bpEAu13yKk7o1y5NVB1hGXkNyY3fuSPZ67");
        language.setLanguage("ugnWm8kuwMwGs3ugO2PbkvmJFo0ZuElNp1GsZ8ai9NaglqUnN1");
        language.setAlpha2("Sk");
        language.setLanguageDescription("m0RjBTDyu2mnWHvtqQPSLnvRbHvqbnmFgYzIOQhMxLUVsg7G9s");
        language.setLanguageType("jwir3x8mbRRh9xj06dhPm0SlXDdIVNVO");
        language.setAlpha4parentid(9);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("4tteT5xOxyKkOBQqmMr0ZPKVreKx6QME0rXMA97bUXt3lQiiLl");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        corecontacts.setNativeMiddleName("CXZstRTYsLZOWg6i8YutGRjOBs5Jz0jt41OKXbryK2uGVT08Yu");
        corecontacts.setAge(51);
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setPhoneNumber("iZGzXljuMpn267DcR7Tp");
        corecontacts.setNativeLastName("5bMx7aGnTo9zu8dJkk26FickxDKAfeQt7laM6nxJW3KsNzopFM");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1462363221499l));
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("gQN6v3ABHESsOTppoRWZURvNDLgCLj2FQ6XsIbjvuZlhCyAZZg");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("sstH7nmx2NdglIsRemX0FQ3uN4Mi2ox9QYNVH3DA7j6OYZEySi");
        corecontacts.setEmailId("pJFkZpaErork3USFDlA8hCsdJz0TPNPqaEH4P3lW1JFQUJfMhU");
        corecontacts.setNativeTitle("JSoopCrBlXvLLVknKaSWbT9ngeebtmn1AZajL6zqc8j2cPCgj9");
        corecontacts.setFirstName("zGMiJJprwCIc7oC0YF5MszFcoeCJ36KxP3rknN0foZAN4RTrWM");
        corecontacts.setNativeFirstName("Je4oiKq6hH3yTUZt3IYzlly4loCfvvjcmXO3IGI7PyhGgmBQuF");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1462363221616l));
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        Country country = new Country();
        country.setCapitalLongitude(6);
        country.setCapital("2MHOOSDUrY0aS3vFA5FjDLJWmVH7ujBa");
        country.setCurrencyCode("hha");
        country.setCurrencySymbol("4UuuQ3MqGTBS8xV3ToZpSajXD7RhO5Yg");
        country.setCountryFlag("QZYz3Wa0TlB0vUTKjWDtH8QoUd63egLpjF9oxBzlXznUazPOv0");
        country.setCapitalLatitude(7);
        country.setCountryCode2("vcu");
        country.setCurrencyName("UjICFr905NGZjX5jWLnwScVzzWYHVPF236iFtNzXC07om2h9bU");
        country.setCountryName("WIP5DzDpzzJ9n2nkUDs1PTUYKw4nD429uuNmTXJm3LJjk7Sl7y");
        country.setCountryCode1("dWC");
        country.setIsoNumeric(312);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("VKRsHLMoRKOv5V8AbagNRMtaAoXA6qQLMhbiLEmzhICHQcVdcl");
        addresstype.setAddressType("G5vR2oi30lgoAmUcXmi7DQydfWGghXKgh4MZjNC47ehX7MoPKt");
        addresstype.setAddressTypeDesc("q7HxzAr7FgVKeFM3KXShSyEs7ISx8K6507pYUi3tPb2UdAc80S");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        State state = new State();
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCode(1);
        state.setStateCapital("UqoeiWvhcQuBPYWBgsqp0UMbLXUnB5FIRCiCbJ0gS5bs3NeLHA");
        state.setStateDescription("vbjyZf9I6ZLz7RW4Z7I17vGNK0B40iQQmKs41s6jJAaEti5bkZ");
        state.setStateName("uYmMraj3o9uA8jGmHkSGq6kwn7DaiBP4Zz95cwbedllfueEPAr");
        state.setStateCodeChar3("I6wa2AToo4ilrABcLPo3Cc9uyFkDDkzq");
        state.setStateCapitalLongitude(11);
        state.setStateCodeChar2("RtrAEOUF5FeNQXhhYGlrdh70RdvWSNug");
        state.setStateCapitalLatitude(1);
        state.setStateFlag("B75eJ3IoYCGMisrxf7n83QqIz4uNdxrDudRDMpOz0xq7ATtEAJ");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("CtTUIJLYXTeaEGVFZ8jNNPiflmhCLVXp");
        city.setCityDescription("oaA2Q1csHDmzQZBDY36vnGf8liWikjwgoGvA0XSsSVkKbGieGy");
        city.setCityCode(3);
        city.setCityLongitude(11);
        city.setCityFlag("8kfBbnnlBU5FvqyXCao8ItdzW7FMbLoAeuDwohICRDYbshJ1BZ");
        city.setCityName("O42lUpjczzbZjS4Ox0W1NbsEpfWeg7D9EDdtKhnXWNA5qW0k34");
        city.setCityLatitude(7);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("law2763GgHlj4VaOJNTmLsznilDnIRZmddJLbE2HW7KWpvu4zL");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("D6L1cC");
        address.setAddress2("k8BbJ3IFJiPcgOqdtFVYP44unc6PQni00tMUKlLDTr1bbZ3rFt");
        address.setAddressLabel("Ctej7CQf0t1");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("muE0O6SNLwV0BHr48u3ZDbUswOIqDqA3HY5mH8KqYrTHWHVOw0");
        address.setAddress3("Nv2fV4k5iXP5th3m9LDGkkwL16DJqCg29Eg12T1z5c2hSqWbwl");
        address.setAddress1("Xc6y5GtZa5wu3h1Gc7AVtN5QV4x7CGLc5hOdPkeHzz7VOSkhsM");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("tu8fKTzh7lY3KOeSxdEi1YlxPHri2pYWVnspzKI9jeobb1tg6Y");
        communicationgroup.setCommGroupName("MpKFVqHN7MpSljhLLgQErG9BLs3j93KHnxHSRedOS6gGbLAxWG");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("b9PEqcs8giLdnd4MOt8KZJKVVSnMipNVYRoUX4Sw4Cg0y9fR7y");
        communicationtype.setCommTypeName("17Qldyb0FA4kcOZohZ5t6K8ezY1CCEnlbrDd70szNGNpAaS20n");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("1o6LukK9x6");
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        User user = new User();
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelHelp("I9peHYr2fbWVAhq9EzRtX9xXROAxUAfXpdxkwzRMj9b08LZEm7");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelIcon("HvSgR2JpGxeLtMIHov6l50PGHfyqLe5M3TTwe482vDwvn2ldT0");
        useraccesslevel.setLevelDescription("w1VA9u9c2bdOL9wCJhtoWxxlVLf0ARF5ZrgvbosEr9ZNMrfMms");
        useraccesslevel.setLevelName("jVjH88ll3UviSGeXBtCCwswl2aUeRI70h8CqvFefBDp4gg04rt");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainHelp("ib9DUyZwG0auZYjyYMXeOdcdlXmm16dtNmS8TvwanYcDysujtj");
        useraccessdomain.setDomainName("6t8d2dOlWjYaR0xV4e0211s74W5GZ4wWrEOak43p0yQZ2H0Wjo");
        useraccessdomain.setDomainDescription("QlxQM4X5OzYYU0TuFNXsRttx59dIOhqOFFuffibKk2ratKnHWq");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("j8KT6kYzXr048UEjOgfnRAGCEsLKfnUUEV7RIUubY7vjLbQoHy");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setChangePasswordNextLogin(1);
        user.setPasswordAlgo("GLxuvPteU4pWjIt8PgacLStbM6lLNh75Rt3ZFx9dqSeUbg6Osb");
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsLocked(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1462363222087l));
        user.setIsDeleted(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1462363222087l));
        user.setUserAccessCode(2777);
        user.setSessionTimeout(763);
        user.setAllowMultipleLogin(1);
        user.setGenTempOneTimePassword(1);
        user.setMultiFactorAuthEnabled(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestion("a9VC222zYdm2kG3Kv6iQj1VtmBoxDn4xZ5GOEdIZbHwlYDGI8F");
        question.setLevelid(11);
        question.setQuestionIcon("v4P1P1wZ3xlGnnqOijaxFCNTmLKAonvQjnYv5LjGkPrHfuL51m");
        question.setQuestionDetails("4UIeiTrAOw");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setUser(user);
        passrecovery.setAnswer("xdAwmItozY6KlR6rWnImRegYT35MZ7tX14nCXx2ac2JMA6pOzb");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey());
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePasswordExpiry(1);
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1462363222263l));
        userdata.setOneTimePasswordExpiry(3);
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1462363222287l));
        userdata.setUser(user);
        userdata.setPassword("fjYw30AI4JjgWCJGniWN2j06FeindOCRrbBbXxepHsai1VpBrQ");
        userdata.setLast5Passwords("CclGKOhyEQoqsyJ4ELs98bPicyGzpmzel7U11xqJgrDeuabEnZ");
        userdata.setOneTimePassword("9GENXBqLKHyxLdqdgkhswlZIpjRPBWL2");
        user.setUserData(userdata);
        Login login = new Login();
        login.setFailedLoginAttempts(1);
        login.setServerAuthText("dQ9llRee1KsQef62");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setIsAuthenticated(true);
        user.setUserId(null);
        login.setUser(user);
        login.setServerAuthImage("6NWHWvGWl4QW1xCGQ6QiHmMyBshLAeSU");
        login.setLoginId("IPATFuJLIfPXIbTeoW36by3G8Gqe0q1AKxIUzgyyakjeEqmkhL");
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
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setFailedLoginAttempts(10);
            login.setServerAuthText("Hk5TN46DIddGBGsT");
            login.setVersionId(1);
            login.setServerAuthImage("qHa1eYxko1sENpg2QfmjJ8A9DWE0s8rf");
            login.setLoginId("loiDy6Ql6XaSyz64DVkKIRDRcShpJ0WGjnaIGmegOE2RU6VRuU");
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
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
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
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

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "P0xYhwdSufaII7aYY8xHqziOL5833qRbrbZ3EF6XGthwqZqKfPYaAbeanWkbO2CsAUZ2JVt2LQXu0J3YTLk7lqQRkj9KTIa6dnLgK6ykfQ9iW7QWKn60C3cPKp6PswBFKvoa7oYdhi7O1ZDEiJQihKPAxmSJ2tx7KAFaW0v1pQETiOCN1APurAPCqcASpNkc9PyFf3DVj"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "c5v8dxpUQTiv0KqKB7gQL0aD2MSEu2SmO"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "BlstUoSEsFhrqR9I1"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 19));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
