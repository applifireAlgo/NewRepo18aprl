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
        CoreContacts corecontacts = new CoreContacts();
        Language language = new Language();
        language.setAlpha4parentid(5);
        language.setLanguageType("rvHRYPVxz4MXvp41NSFAhIe1IowRb4Iy");
        language.setLanguageIcon("t3BQ3ZaEiYcffvek62yQHcLtqQBXDIggLfB87rf8Ou8RdqHz43");
        language.setAlpha2("xM");
        language.setAlpha3("Hao");
        language.setAlpha4("yjry");
        language.setLanguage("0gmBIZfNN1qp4rfvVujGbzvQx8ajFoM1sodGujTwWFrDZgONry");
        language.setLanguageDescription("gYZIiVporZJRVhD6Z8nyf9HkW0dJZspIVPqtARuvKTciEHyGZJ");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setUtcdifference(3);
        timezone.setTimeZoneLabel("w8I5AedYazUTpnR9mzQLDdJXzF1ZdiNRCq5j8v0quEkP9JpWjE");
        timezone.setCities("KmqOPKBSsFWcemkHM6vYf5l5zNhBSdua24M2y2GZ83nkNDctSA");
        timezone.setGmtLabel("CS1r7olgjz4UOxolSyw2SIrTPuBlWIgnYzWZdzSwoNantjsZCA");
        timezone.setCountry("KBbZ4HCsYyDX3Nen7wrb5vBKPvCaOoDffU7CjDBMR4pe54weF7");
        Gender gender = new Gender();
        gender.setGender("CAH7nt7uBWbMwOhKrduBskC8mIrVHYR2VczYV2zNYXiDnbkShy");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("zrMzbdaNDgvVbVljeF1Y8kEgkpWEx6rnc2shKrRCMr6mCUR6Wy");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setDateofbirth(new java.sql.Timestamp(1461247758604l));
        corecontacts.setNativeMiddleName("eDsgjqs4Qji5qTyvWlOyVKM2XSUxLwUIHN4Tyt1BN40mnNvttK");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("4L7Q83UdOa8bOGQ4DQDKKsUl4npr5bHhNrpnG9BZK4CQiQnHLn");
        corecontacts.setLastName("alKwx0GgkYmyC1LLpoxeO5p3kUJhbHnP74qJUgDHaxz2YsSFHS");
        corecontacts.setNativeLastName("OFzimH2N6LUYGMvKfIKaIiBjESsA7tlZF6Wc9VNTk7m8vnNBdE");
        corecontacts.setNativeFirstName("VYSkh5RMCMR7xf0nBHsR0uURXabbuLLoTAP37Sud0fZ3J3WHG0");
        corecontacts.setEmailId("hwkismTzfMSZmdlVxE3azRurpElnTMyNYhYT1rSRBlCsNqtovX");
        corecontacts.setPhoneNumber("SLk7BkCg0SZDvq0Cg0Ik");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1461247758676l));
        corecontacts.setFirstName("acNZfU0mhzdu7vLtN0vmSJ2Yxk16xRr1oDhMQqXcbI39nbAAX0");
        corecontacts.setAge(15);
        corecontacts.setNativeTitle("4owEflaOB4XhVlY3398F9FZz0CrBxlTvTDxKNjiWxoWxHWAGZA");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress3("r8GXGXhMiA2pG4MHjb8jufD4CaufEqoVUKWBs4sdhaB64f8HoS");
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("G6bzxVIf4V4rqkwPYiZ9p36aXksXcae3HnlzaHoVae5twMLMFU");
        addresstype.setAddressTypeDesc("mlbMyX5ulE0xzoUd9bB7K5iPPydlUN7PHOalzErVyuO3tuvkGZ");
        addresstype.setAddressTypeIcon("KS8hMkJi03H0ZU3QTrKxgEsTJvmkadurlXhXzlH4PXsufhFYnR");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        State state = new State();
        state.setStateCode(1);
        state.setStateName("XiVxKC7ypx3GB5bxgMLbBSwZsWU8ajqUH3z4sdgPIPl79V9aG1");
        state.setStateCodeChar3("CfCwUMvJJGsySL9rbkzdvji2MYoeEQph");
        state.setStateCapital("vC7i2RmIBfnoWo8ABne3a5cKLtGdjtewqCANUN27SjfnTnRybK");
        state.setStateCapitalLongitude(5);
        state.setStateFlag("WRpdoAjafQRAqwlJLUfeAtW0GkcrEA6DfxX5Bs4klMIPOIxk0g");
        state.setStateCodeChar2("Jd4CZVAr7oYAaebH9OzoWxqEW3FQsgJd");
        Country country = new Country();
        country.setCapital("YYy1zmZcXCsMG44T0VKfDhhtmhjxMzHd");
        country.setCurrencyCode("Q5Y");
        country.setCountryName("lAnSHMAYdRU0zFYMejr8s1q3ZEQeU6zYtDp8n5M7Z7e96TYm50");
        country.setCurrencySymbol("agyfoAXf3WiYXpNwHK62W8pYBlMze26l");
        country.setCountryCode2("N0D");
        country.setCurrencyName("DORKewDvkEyR6PO3StFthxCJPcdkRIfUbdBI2ApYULvFM1YhjZ");
        country.setCapitalLatitude(8);
        country.setIsoNumeric(436);
        country.setCountryFlag("6qGgdcFDqC953dDle6P1oikOrTRDr5qoRIdliF9CjUtoaQl3kH");
        country.setCapitalLongitude(11);
        country.setCountryCode1("F6j");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCode(1);
        state.setStateName("1ZEyHcXJXu7qRUoHtfroojW26rYoJKKQaIeIsu1mDW6qYXscEH");
        state.setStateCodeChar3("LOFP971rSKQzxkA5oLUXUkPljSwh7Q6K");
        state.setStateCapital("ojZlhzgpY6z8ct3LL5SfhjT717N2VkAnG9ynPYF9xZdmXAAMMF");
        state.setStateCapitalLongitude(1);
        state.setStateFlag("HRYtA5DilkZ1frKdxM3CGrEDDy230zu5Muo8jMS7M06SlGFs8C");
        state.setStateCodeChar2("kv07zCXEPHe5t7GV3FeN7ptf8C2v295R");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(6);
        state.setStateDescription("CEoncFxmrAbK4BqavNJZtK9SY7Oqxz2mGKKVFqMFIBIr3Flsuo");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(3);
        city.setCityFlag("zQksDYFDx92D9RsyzDsWCOEvWRjmuEC1mA8QKpGtjNOpPtQWek");
        city.setCityDescription("6JhuUENrs1BqaGqVjOit5v29BVgo08n5zcprugatGwUQQ6tbe5");
        city.setCityLatitude(2);
        city.setCityName("fAintEZsKY7vTWmo2M4Lqn3JUxQEHqgpcBshHqgEex3X5gAUtT");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityLongitude(8);
        city.setCityCodeChar2("n9bAP19IowXqzELJNJXnQdCVgNIMYHsA");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddress3("nHfQPjsUZMNLS9vHA0lPZYWVHiaSPaOSXooJ9N13TlLBl7gISt");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("JTYU5UKd26g");
        address.setAddress1("Inu6LkLQbB7fCv8fRBZCpuLEHmiZPBpkCIZVRypnEqtvFGORWG");
        address.setLongitude("POWXmYAYsXIiyILK0YqGr49K12tQC4w2HAyIvQi7inW33e3tIv");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("3HgdyR");
        address.setAddress2("VVaZ3jgzBpBvMUtyWynl5gwaeMkuKHCi4eNnIziDfKY332tA4b");
        address.setLatitude("wGoCvnO3sCESngiZCt1BR9xXuhTrQCLioyCtED3eeaxcAcuHnh");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("79uGVOo40m");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("OGz6HOVyiDycLeSnuTTSRTUe8GFhpZt1h9qLxpGf0tTMtv7zkr");
        communicationgroup.setCommGroupName("eMlFeqmud44jXinDxLbh8TEhmpmuOIbFzJqPUHT4l7asI0Yofn");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("N7S3q2iRhT2UHUajHtvNmM1REhMINFjZe9AkvTboLBlqkN55jF");
        communicationtype.setCommTypeName("iBQzxRqdTLYvVZtlzLdSR0nz8Bv1BebONTptVwncoGWs9dalOh");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("phlfHdgok4VaIDXvWwYWKA3ssGttctepOU1JI2qTy0klr6fcA5");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("mdLrBNXWNw");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        User user = new User();
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1461247759012l));
        user.setPasswordAlgo("I7z1EFi0QpxcYAJKRxzlzfmcsnvMM5xy8ppOeFTrp8EXTEyuO0");
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainDescription("hgP4NFlAVWPJNiynmCr1OZh8Yyo3oeWXxZNJ72zxZOXm6o5oqM");
        useraccessdomain.setDomainName("ZzkrSoGEFoAgnjUcSqjzQTvYCuEhdfUJgiTQ8eMgXm3jLG5q8Z");
        useraccessdomain.setDomainHelp("6V4TK1Mv2U7G5twKZOeACkEKkEfx3iWrMiiK5sMBsHiS9foN2s");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("plqzxpmlr0n5eJtDNEECAoscFltzI5UvhKnNln1zeFu9JJMA9U");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelName("Q2AkCdB5kaPJSVNRRlZD1Tgn4wqXNHcRSREXjlvefxiw4na3iy");
        useraccesslevel.setLevelDescription("UNZzLx2V5EUpJVaWWzVFdiIBYuiqvEApGLTbdRs3MUWiAAWiF9");
        useraccesslevel.setLevelHelp("OysZtxKp5En9mjOVuXaf11k3PHsfzx9rXLLWIYGOvNyuzRkKYl");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelIcon("jouFFOHcylFwMb2OjcONd0edzCCGYPV26ZnoFI6FBMEVeyVlXx");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1461247759027l));
        user.setPasswordAlgo("SbluQmipeLgJkohYbNcYmbbjXaCDqMaAQn3mmTi1QBDrV6GKf5");
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setSessionTimeout(2146);
        user.setChangePasswordNextLogin(1);
        user.setIsDeleted(1);
        user.setUserAccessCode(57417);
        user.setAllowMultipleLogin(1);
        user.setMultiFactorAuthEnabled(1);
        user.setGenTempOneTimePassword(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setPasswordExpiryDate(new java.sql.Timestamp(1461247759075l));
        user.setIsLocked(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionDetails("lHLP90pCxX");
        question.setLevelid(9);
        question.setQuestionIcon("0iyzIhq4yRcWL5ABbinGxNrRYZkyrcBNKuf2CQ9WAngHhhFWKt");
        question.setQuestion("U0ywD47WNVszRj9HCAQWiYdrz8SOrlLsoA7WOn8LhVEvvqpLtW");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setUser(user);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey());
        passrecovery.setAnswer("nOuHOoFQ5EQRUg4WnVrfsBkOOkc0WJiARbSEJOi9l827NeYNBS");
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setUser(user);
        userdata.setOneTimePasswordExpiry(10);
        userdata.setOneTimePassword("at6aAtK9TYazaFhW9qOl8EK5v2qtAJcg");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1461247759262l));
        userdata.setLast5Passwords("QDogD7U7CAnqwbvVvMt12Pp7K9ppEUOHc6CWI8WlMjSMacz6lv");
        userdata.setPassword("WpOC8FSjJUDegZmUBcFHSjIBlNkakluSyk0IN7jhgMZV39rK3a");
        user.setUserData(userdata);
        Login login = new Login();
        login.setServerAuthImage("KOF7riiAzafG6gmQ2tXsl7fIVibvvNRl");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setServerAuthText("nB58i6O5j7SRkKte");
        user.setUserId(null);
        login.setUser(user);
        login.setLoginId("8IzNxpq8SX0d2mTCZ9Y48K4HUAdc4kiZsE1Rpx3oCzboa49t3S");
        login.setIsAuthenticated(true);
        login.setFailedLoginAttempts(3);
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
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

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

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setServerAuthImage("ZN3XJAnpqZOr6IH31LZy4Bjo6gvCADWy");
            login.setServerAuthText("vEltfJkHJI7Y9lvy");
            login.setLoginId("CZwO6XNiMt8pITKdWhn6Ldvu697SxEhhA2MW1jQbzCBd1ymj5d");
            login.setFailedLoginAttempts(7);
            login.setVersionId(1);
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "yxb05baxt8MKdCKggPYjjFJriVUsoq1V80nYAkifjbtDzP2ak0Eus6MBjTv6q0ftJo1u4g0rNgCgKcCP1emEIitWPokm7jnMDXRQmpxDZ6bmkbu141ufpXVLmoBuBBsijOgHAJ7Ue6BzVaSQ45hAVrQhqsWN4GYejQQp2ScjpvC1Il5TMrPUhVM4SC4XpYNp52JYCdNJD"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "yvE9vX5ezlLCRaJbXlhCcXRms7FR2bYsN"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "aVKM5ZarsvBKguMu7"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 19));
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
