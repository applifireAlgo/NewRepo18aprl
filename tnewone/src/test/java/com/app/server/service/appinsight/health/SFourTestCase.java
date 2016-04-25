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
import com.app.server.repository.appinsight.health.SFourRepository;
import com.app.shared.appinsight.health.SFour;
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
import com.app.shared.aaaboundedcontext.authentication.Login;
import com.app.server.repository.aaaboundedcontext.authentication.LoginRepository;
import com.app.shared.organizationboundedcontext.contacts.CoreContacts;
import com.app.server.repository.organizationboundedcontext.contacts.CoreContactsRepository;
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
import com.app.shared.aaaboundedcontext.authentication.User;
import com.app.server.repository.aaaboundedcontext.authentication.UserRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessDomain;
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessDomainRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessLevel;
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessLevelRepository;
import com.app.shared.aaaboundedcontext.authentication.PassRecovery;
import com.app.shared.aaaboundedcontext.authentication.Question;
import com.app.server.repository.aaaboundedcontext.authentication.QuestionRepository;
import com.app.shared.aaaboundedcontext.authentication.UserData;
import com.app.shared.appinsight.health.Sone;
import com.app.server.repository.appinsight.health.SoneRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class SFourTestCase extends EntityTestCriteria {

    @Autowired
    private SFourRepository<SFour> sfourRepository;

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

    private SFour createSFour(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Login login = new Login();
        login.setServerAuthImage("ArCTmc4jV4bdgbVAfUPbJOOF0marckFq");
        CoreContacts corecontacts = new CoreContacts();
        Language language = new Language();
        language.setAlpha4parentid(2);
        language.setLanguageType("ELNT3IjR61D5wH85jHTba9684tGkw9Ge");
        language.setLanguageIcon("FYLmodiSDGOV8hsh8Hg8UoxnhCXYTlJPIV2eamZFB9GifiMHA4");
        language.setAlpha2("0z");
        language.setAlpha3("gNZ");
        language.setAlpha4("xKxw");
        language.setLanguage("ULqkICKFmFdKSLdd3vQCrnBKgdUYVu5H0RBHGTDDKxcnib2FIf");
        language.setLanguageDescription("4o0pxsXCMkk7OvJcdArJVdvEQ0ABpGneZa4kM2fNFNCjGTWkpP");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setUtcdifference(3);
        timezone.setTimeZoneLabel("jyNfN36VMyhLDaih0hjCUcJwPRhahH1ZAJTcjQJn3OZ7NZuKSp");
        timezone.setCities("VNTwF7H4sRbcaZF7aYIH2pzfDp13Bkd9bwWdgu8w2aySSuOldP");
        timezone.setGmtLabel("loY0YwcMoUePkZRoGD7dcDLMH74EhymVdpdlywuzJYLvWCzekD");
        timezone.setCountry("NejclevXPe2iMNnl3DZHJuG9gHDjIkxH0ArWquEyvctUwDQwYm");
        Gender gender = new Gender();
        gender.setGender("7jRraIx1xPXj75U7m8A76vss0Ylj5Wy6Ay0mTheXQvTwx5U9qT");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("TiDnp9aTZOKO5ejG0oPcHDfFybyDLfOECm1qz1SzSxl0ac34AO");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setDateofbirth(new java.sql.Timestamp(1461574930967l));
        corecontacts.setNativeMiddleName("oriBd22BsJsBfdeScPfCiN4GBZOJyeW2bnX6yhLVS0MU5pbfn4");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("tcJQOdrQCUVoLL1ZeiQgNuKSGiRt5ValFmXlurgfFl7FamiL6P");
        corecontacts.setLastName("5t4q5QmyHSx3khSRDTfgVeKbOR9W4ufVFgdkRIG0MuWFPf10it");
        corecontacts.setNativeLastName("FrifLlLT4Y9ivRVqcuxFIUiKB6L9ishd4kT1hh9fy06wuWCHrg");
        corecontacts.setNativeFirstName("clXZ7vIG3DCFd4LtybUBSrl0RSEDzzeWeHMUuIJ1fsqoXanBGG");
        corecontacts.setEmailId("BUdOxCfFjGZ26koPPw2FrSEmMYzagupfnne4pAJiTABc1LFcz8");
        corecontacts.setPhoneNumber("mLlNlZw76ceCKDyJ0I8f");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1461574931051l));
        corecontacts.setFirstName("B1nWAG3kxKgc1ExYmfvyx17FTykZDxqQIvncXG9JXHLf6jdjjw");
        corecontacts.setAge(49);
        corecontacts.setNativeTitle("ikzoI75HgPOUHutXAtD2NndeZaGf1wgJn3o9iJijtIOs2n201k");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress3("jIzSvsZaLcZVmvDvti94uYrJEEDMT6Hcd5S7oK4B9lto0mItxN");
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("BIm3bLbxy5gm8pJubo5GVqJ9ZC4kJtVyCHlSJxlhxzVDXZZ5Fw");
        addresstype.setAddressTypeDesc("MznhLVwRPpHrGkOsUMwhUCtTT3ZXvXuVfNp5rWepFLZIaUdPG1");
        addresstype.setAddressTypeIcon("hpAcRD1o7oLn3fx1jIMI2oquUYQLkhBPigcCshfrigBGsaCvm7");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        State state = new State();
        state.setStateCode(2);
        state.setStateName("TWAfTVcMOQcvrtpTNLahKyuWVwQngDwWca2WJJp1qsumhg1Q7o");
        state.setStateCodeChar3("JDl3YqushSCm6G7Txrr5nrR1O3bPqKF0");
        state.setStateCapital("adIFx2guoLKCSvrJUmWKbZDVRyaUnOT50FTF7Gds6O4t72deG2");
        state.setStateCapitalLongitude(5);
        state.setStateFlag("poeepzBZFRwBXLAN04uUoaIWZ8NxE2y4Etgu7yJLv70FrK7AhL");
        state.setStateCodeChar2("Bfx2T1MwXcqZAcYsB5dGaZKIXIuAMz1r");
        Country country = new Country();
        country.setCapital("5izCUkbDVIhVZlvcWVHGAtI1bZIsNv6h");
        country.setCurrencyCode("thv");
        country.setCountryName("b0cU2gaPLpVovQEut796yNUxMY0lQAUDX6AGoocyF83z3HjHeB");
        country.setCurrencySymbol("IPRjuz9tXHm54jQEcm1uG3fnyozmSRIk");
        country.setCountryCode2("lGS");
        country.setCurrencyName("XKjObuVGHuRW0ab17pOvio0Z5QpQYcVuIQjrbXxxxVh7Dmb29x");
        country.setCapitalLatitude(6);
        country.setIsoNumeric(470);
        country.setCountryFlag("Xir2l3efaCgXiSFLxWL1Rv3ZbLL3BMbYUUhd3VsSIWXMLOoN4n");
        country.setCapitalLongitude(4);
        country.setCountryCode1("vOh");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCode(1);
        state.setStateName("W9G0tuYkcxc3nLF1K7f1ikQ3bjpfUOTBztwmPUAhB9VQauinyd");
        state.setStateCodeChar3("V1icDgiy660Zqga6DEK9CmxPVkEBumen");
        state.setStateCapital("ZuOVG6RizRILKbpePuOIi8cjwCQwqwaPzNfEll9IEVflQFtx4s");
        state.setStateCapitalLongitude(8);
        state.setStateFlag("0F5ZN5J0Ua8CJVwN59RQCqbS66jZqRNCQkoWwY03FYxglHtMsz");
        state.setStateCodeChar2("A4mch7TWJpWlZHR1eHn9nd4ScGuHdVBd");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(6);
        state.setStateDescription("EmTcTgIsPO5tLn9xNhEaQFxNu45w4YJ8BLlabrahqetZYm8HdL");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(2);
        city.setCityFlag("YeNJh8PIlyXQWz3mgfrqYp0gpUdxkKIgbmMejPiTYwNKvAE2mv");
        city.setCityDescription("Cpg7th0oyhPo5wm2ZXVtYFkdOuyFlQimS33udOhrET36E3ufpI");
        city.setCityLatitude(7);
        city.setCityName("gxXTsgi8rrEyipxgeSiMdfCJONN7JhopLeLEghRaqwG7n8fqb0");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityLongitude(4);
        city.setCityCodeChar2("MDEmGBY7wmlRwowBvmDFiYcr6wFJqSjP");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddress3("tNFNtuNcOlo28AkXQkiSQ0xBHBIDMWz3JHxD4I3mJ2Ikunua7S");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("WPszzDEfxx9");
        address.setAddress1("pXK1eYl0TM6O9HoU4CetU4hmJsXHhT35lD6oN5EArRlMuDy1Ge");
        address.setLongitude("2MiCvXt2Hu83qRqXQq6rcwZBLsVQ8jD3QJtd1Q0P1lbDmTXIM4");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("vlCXSq");
        address.setAddress2("tvxgFwyoyIUSovA1LP0rvGuEozIlGUBO642XRZQthWLzo3lcGk");
        address.setLatitude("dNwpAehaQwyXWSYlad3RXNkCIZedwXnP9JzyY2cHb51732z7nw");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("6m9vbCzBPK");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("ggNvNtMwIcQCshEvjZBuan2hALv4Vl4tNogwvh21bf4DcGx30x");
        communicationgroup.setCommGroupName("PWqe8gfKlnUBZ8HCiev4XnlE5sN9rBchnqUEoOgNVIESQ3uEXS");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("Es90wdU9CyUc7zVR3hyBIX7IQcvSi7cLxZ9YNvz8dmeFDb0No2");
        communicationtype.setCommTypeName("4Yh2jUG2zZ3MhI9S8Hif90aRiXUO1n5PnPRfOnh0anX0Pytn2E");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("BlxyOrMO2wO6nNpuq1RPOoK6Gw3dEltmQkB3QeBJpWmTYLGOGu");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("zCo7cnTuOT");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        User user = new User();
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1461574931406l));
        user.setPasswordAlgo("D1bC3xACsI3H59TFuWbiUKcQ4itSlj05s4qC0upgizCBJ7xSYk");
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainDescription("hAcLnisakOMGotYcm7V5R6VE6sAw4aO14wIP8N9V366zPw3oQl");
        useraccessdomain.setDomainName("rXEv2jqINIX5gigM6FJxYLOHHNe9MXPW9OgCgjgG610bLkOYck");
        useraccessdomain.setDomainHelp("v1ou29J4LWUynPa8sKHiL9Fk6Lh6SumvQaoaHMP9NSKNaDrdDp");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("uJXT6sZOF7nbGcllHllm1PQCFaIveEQmVGJMm5FwUpfbhSvhUS");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelName("NV65XBAHpwUQpCTafdTuTwf8Kqw0yE43CSi8Jo84xjcBSHn2tb");
        useraccesslevel.setLevelDescription("C8UDI23lnVGnS0606eeJgRMz0XMM0FSFq0CuVxOtIcGhSmHOI4");
        useraccesslevel.setLevelHelp("irV0nFU6jqgqnR75orvoYu9cxqn4lQSROtzw897pSrrBwSFXrV");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelIcon("3JA1gqNEVcROAS1Zp1bBOTAigHlNaRFRg45R4YrwFnMUuencrH");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1461574931413l));
        user.setPasswordAlgo("QhPMyL8rphMwVzRe3z5iJtaMG2AGE7SUqvK6nLLaCYS0KCqAGl");
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setSessionTimeout(157);
        user.setChangePasswordNextLogin(1);
        user.setIsDeleted(1);
        user.setUserAccessCode(20973);
        user.setAllowMultipleLogin(1);
        user.setMultiFactorAuthEnabled(1);
        user.setGenTempOneTimePassword(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setPasswordExpiryDate(new java.sql.Timestamp(1461574931468l));
        user.setIsLocked(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionDetails("fp1arQSMvw");
        question.setLevelid(7);
        question.setQuestionIcon("WSgHhvO5ythehlYpW6N9uTIPQ64idQyGnbxuHr2rkICyk8lUOc");
        question.setQuestion("tqWPeMXFXD5VkyrzZRRO1nf7zwgLACARcK2oMwtMX7hue5g6UO");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setUser(user);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setAnswer("dbXtmlUfPglNouXeR2FjOyQ2oP9bc37QzHYq9iowInqVKJ2AeX");
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setUser(user);
        userdata.setOneTimePasswordExpiry(10);
        userdata.setOneTimePassword("SEjZDPL6HemvESmvUGRKpwXqswzKINU1");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1461574931641l));
        userdata.setLast5Passwords("8PcvBxc9IGQXOQ0CH7cXjDmTsCpeh8yNTVClOfigbwd8CJWYHM");
        userdata.setPassword("aBSI92wM1N19TFRObPN7r2FXTNucQe3zbH24ElGJRSlNGUeclU");
        user.setUserData(userdata);
        login.setServerAuthImage("PmJEfswyKqDBMxNjSAH2aOGTbouk6fSv");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setServerAuthText("BF6ks6MPXKlDy9Hh");
        user.setUserId(null);
        login.setUser(user);
        login.setLoginId("ws6RJK7b2VjKTGZ5TYWJLoquExiM8i2djj35S7J0VBNe79IqgE");
        login.setIsAuthenticated(true);
        login.setFailedLoginAttempts(10);
        Login LoginTest = new Login();
        if (isSave) {
            LoginTest = loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
        }
        Sone sone = new Sone();
        sone.setSnm("X8kdc9NDsF8AMC3exIbRDFGnVumBURy43E7Ss6ykxKiyBIlYla");
        Sone SoneTest = new Sone();
        if (isSave) {
            SoneTest = soneRepository.save(sone);
            map.put("SonePrimaryKey", sone._getPrimarykey());
        }
        SFour sfour = new SFour();
        sfour.setSdfs((java.lang.String) LoginTest._getPrimarykey()); /* ******Adding refrenced table data */
        sfour.setFs((java.lang.String) UserTest._getPrimarykey()); /* ******Adding refrenced table data */
        sfour.setSds((java.lang.String) CoreContactsTest._getPrimarykey()); /* ******Adding refrenced table data */
        sfour.setSffdf((java.lang.String) SoneTest._getPrimarykey()); /* ******Adding refrenced table data */
        sfour.setDfd((java.lang.String) TitleTest._getPrimarykey());
        sfour.setEntityValidator(entityValidator);
        return sfour;
    }

    @Test
    public void test1Save() {
        try {
            SFour sfour = createSFour(true);
            sfour.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            sfour.isValid();
            sfourRepository.save(sfour);
            map.put("SFourPrimaryKey", sfour._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private LoginRepository<Login> loginRepository;

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private SoneRepository<Sone> soneRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("SFourPrimaryKey"));
            SFour sfour = sfourRepository.findById((java.lang.String) map.get("SFourPrimaryKey"));
            sfour.setVersionId(1);
            sfour.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            sfourRepository.update(sfour);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("SFourPrimaryKey"));
            sfourRepository.findById((java.lang.String) map.get("SFourPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBysdfs() {
        try {
            java.util.List<SFour> listofsdfs = sfourRepository.findBySdfs((java.lang.String) map.get("LoginPrimaryKey"));
            if (listofsdfs.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByfs() {
        try {
            java.util.List<SFour> listoffs = sfourRepository.findByFs((java.lang.String) map.get("UserPrimaryKey"));
            if (listoffs.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBysds() {
        try {
            java.util.List<SFour> listofsds = sfourRepository.findBySds((java.lang.String) map.get("CoreContactsPrimaryKey"));
            if (listofsds.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBysffdf() {
        try {
            java.util.List<SFour> listofsffdf = sfourRepository.findBySffdf((java.lang.String) map.get("SonePrimaryKey"));
            if (listofsffdf.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBydfd() {
        try {
            java.util.List<SFour> listofdfd = sfourRepository.findByDfd((java.lang.String) map.get("TitlePrimaryKey"));
            if (listofdfd.size() == 0) {
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
            org.junit.Assert.assertNotNull(map.get("SFourPrimaryKey"));
            sfourRepository.delete((java.lang.String) map.get("SFourPrimaryKey")); /* Deleting refrenced data */
            soneRepository.delete((java.lang.String) map.get("SonePrimaryKey")); /* Deleting refrenced data */
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
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

    private void validateSFour(EntityTestCriteria contraints, SFour sfour) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            sfour.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            sfour.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            sfour.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            sfourRepository.save(sfour);
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
