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
        login.setServerAuthImage("UAwgdcVo83vLzZe42fxkhEjMMflh5Z0e");
        CoreContacts corecontacts = new CoreContacts();
        Language language = new Language();
        language.setAlpha4parentid(2);
        language.setLanguageType("bPqcMoP6EiBLGmeM3lDG4idYw7S8Uj9N");
        language.setLanguageIcon("K0hcX5PDnRMt6gFXOCxKKpmbZI69b0EuklkxjIdPWfIwUf72hH");
        language.setAlpha2("tK");
        language.setAlpha3("vCG");
        language.setAlpha4("Oroz");
        language.setLanguage("PkNlfotOSk3TZj1YK7jjTppURC1QCjjDJPQvOGqbVsfwt3aeCI");
        language.setLanguageDescription("6Aon7A9b7aQsupaakcN33okZwDmYPfCyYqYtgYkUIZq6zvNUbF");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setUtcdifference(5);
        timezone.setTimeZoneLabel("8AVJAQHT4hARxCwbwL7rEBR1Vc5Gi6BSEELRa2I9ovZ1iplhH1");
        timezone.setCities("RUtS5m85LXTOGk3Fatz9lzzJWJnD04DGZSRFlI2wKLg0TqTmhd");
        timezone.setGmtLabel("hezg8lfjTt36LzeXUJhfZc4bdcb4Zv0jibf2YgP82inbuzOrQT");
        timezone.setCountry("DZmXTli9Mb5eVoOR9O6IAqDz6t5tbq3gwfHPLGi0hYfr7yJRk1");
        Gender gender = new Gender();
        gender.setGender("8bzvn1fHxqR1FpHIwOz9TQoQmV61lUkPx5Xzy05Luyuxkwtbo2");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("WDW5dCMCV5D3IH0vSQYcggnw7d9ZalQBzxdcE38ZjeObYtZinp");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setDateofbirth(new java.sql.Timestamp(1461573819391l));
        corecontacts.setNativeMiddleName("aIVvZ9XB5OPHEFtQIcgvcy5d4M2IJPITorNkpVC7CSDt1xy1R1");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("Qgqq1BcwksCJBttlP5OTUxpew0G4EpU6GDZc0WdmxDggPINLkI");
        corecontacts.setLastName("79nyWQvKTYu2ROzC2xxu6HlcxOMV5IPXp0of2cZn74T7W67Ndg");
        corecontacts.setNativeLastName("IjMF8djusOu582m7QrLpe1iznNwUbroevCrHvOQ3QQkzXl4eWk");
        corecontacts.setNativeFirstName("i7g3aSquywEq50OaBpGUEKzyk9kWXJzLQlRjtwHtMLMvilTrd9");
        corecontacts.setEmailId("nmm1TueZHW1RatW6uM0MArWvFw0NbupddyiBu7tgUALJVgOeV0");
        corecontacts.setPhoneNumber("hliS9K4nVvqA5Zy8DUhF");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1461573819476l));
        corecontacts.setFirstName("AOnDSlAhsV6LnY4Nr6cX0mbZeNaSOaLPAv5cajp5ns4qj94pKf");
        corecontacts.setAge(3);
        corecontacts.setNativeTitle("JK9JpEQZmYVf7800YLGVx7K1KhHbAC7P9uqnAM8kHn7ZR9v7ow");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress3("SGkFM7ZbUmhniY1sE42ECJazDGOf3bgBvBpU76T8nr2K0fjVmC");
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("QcH4mkLECmvyypZgRmHmvEQOmFXQceL0gIIcx4hU6tBm9YDH2e");
        addresstype.setAddressTypeDesc("LzN3a8jDWg2QhjcPVmE8ZrvutwvCD9KYf84lhtX7gXpjfMuJ8J");
        addresstype.setAddressTypeIcon("o4RvjHXsHP6N2GZb1hgFeHzioRgWYvD0Kam6Ca9PHXob2FQpMU");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        State state = new State();
        state.setStateCode(2);
        state.setStateName("ik8FSwubHK9mNja2kX0Q1WkUUE3dv3eOfdYJGSAvUgDpMdy5ud");
        state.setStateCodeChar3("3rdxBhR4XRWH2I2rIZO6bNoz2IJsvqDv");
        state.setStateCapital("KUBdi6sRx9fO0b8zNNkKajJwmETe9zvAxNx3MDFIdTweiA2cfO");
        state.setStateCapitalLongitude(7);
        state.setStateFlag("SfE2G8FzdK01kuRTehhm7aJ4yBnhipsiHtQWq6brpKwVzOUhQb");
        state.setStateCodeChar2("wZd8y05voSeVMoTAgMFbaRbf6rrQirF0");
        Country country = new Country();
        country.setCapital("mhWPx1a8jDa9mk3I3yKOLWSW0wEFREV7");
        country.setCurrencyCode("FDl");
        country.setCountryName("h4htS7Jnih3c1gphargisJy2VpTFV9Vdsa8vgDNKZPVT2FYaWp");
        country.setCurrencySymbol("49SJFLPHYxDYO1dKzKEofCwHLmjUjqHi");
        country.setCountryCode2("1LD");
        country.setCurrencyName("gA91YU6kiFVgpewkwnstzFVsD94aw6aXS5LKBIYde2MoVKaErS");
        country.setCapitalLatitude(6);
        country.setIsoNumeric(231);
        country.setCountryFlag("rHBcBnHDeNzmZaDjKek3geyCtnDVSKxUJhWsaptt1CS4erVA7I");
        country.setCapitalLongitude(7);
        country.setCountryCode1("48w");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCode(1);
        state.setStateName("QGn5i3BMF4Ox9VNIvvgBjCjI2l7VIh0WHYsgQgZsfcIprVpmQf");
        state.setStateCodeChar3("rJAqShL06CQ4DC6Xd9VrZXUAzkFATLID");
        state.setStateCapital("R95hGhKp8gdJS8xyjEnQtDLXc8Be6f8x5sC4NEzoyKwW8IYuiV");
        state.setStateCapitalLongitude(1);
        state.setStateFlag("8GdxiABeNKk6e44QjiCKjX5l27VUWVLi9ERt0X9wcqjQ5ftA61");
        state.setStateCodeChar2("5HETlq6NfbquZq0osQYmsvp0bRUzOaaH");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(8);
        state.setStateDescription("bb4jZOcReEE7lCQEtDxKCS3d9dLza2XHkVe96MpIHJDgNxj2XB");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(1);
        city.setCityFlag("dU6eGAwp6GT68XcYeTgo1oF9bZDVk1nbhAstIr07XxEWCv6CGa");
        city.setCityDescription("v51YGehRRNjheZddGVurR4GLxBm6SVaL6B2oNMu5bMKla4jMvv");
        city.setCityLatitude(5);
        city.setCityName("cGwSjNDVRGGklDCd75yXerSHqfFw4JaGdoisIG8YdPDWIpDM3l");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityLongitude(8);
        city.setCityCodeChar2("T7ryFC6AUzzHm4GZH2lc18V7NPphV1HG");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddress3("ezJCb6TlTdKIc7DzfSHjRBAVC56IHWkzZReg7IFVALYUbG57mF");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("2C0VbWUSqZ1");
        address.setAddress1("jo2xCLourl5PeKNK4XVCgK2siJvdkaL0LmkXbfGWQHmDqTlad2");
        address.setLongitude("g4BSGHE8UszOGtwHaG09ex4sAEjmZ1We9GSIKLpabTF5u2IVXa");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("fxKBuE");
        address.setAddress2("QoaaChX8uv5MOwCfiEc0bq2i0aXAuWhiwUbBsfjHHM33TeXd51");
        address.setLatitude("quNZgqf4mhBRYkJt9YUMJPElzLIG2ccYZUJJnUMczwDfDTeF9q");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("mWATlWbKfU");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("YI6VosqOqQzDFX1exneG6A89hG3IcVwL9GvCjvOZ9pmhpgoSn2");
        communicationgroup.setCommGroupName("tzC7XUnMTOPMLWU6o4Lhhz8V35GaD8uVkwl54iMV9pv839fNLi");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("Uk2bxuLTU9X1HtpcMNnrfXbChfBx5l7XddYWp3Fllkkps8X8Nc");
        communicationtype.setCommTypeName("38i4MqtzM90SmPArF0yGccrUBPNE9abKQ1lKlJICD3fkw87Paf");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("pC5SmCrAZP9SCSXvbQI6urC84PEW06pLRlX7wJmwqz3cMoh97t");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("6KTBtyyXQ6");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        User user = new User();
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1461573819838l));
        user.setPasswordAlgo("FKXIBUgmAhPUadkDNHMSrFeQBAoHvF6qdnPFuj0LaiF8hGC1hV");
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainDescription("OBTry9UHmNYaJCGpy5kV6PDLE1Htrg9imitWezHfgFQKYO4lsO");
        useraccessdomain.setDomainName("cld42cNBwz6oF94vkhLqRzD1d69KvEhX4hXgdZizYBqMbJRFHz");
        useraccessdomain.setDomainHelp("flUm8pXtkNSu1MtRDKOukLvLBunqCsIyawePHAiWZl3BeOzqfW");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("bQLTYVVMgFPKje39yziy72guJewCVGVseziaz7Pq1dfqrVbhts");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelName("F5HZIjH5Uwnc6ByJwFyLdIwGI5txOePXPZJUBuvFVQww03hHB3");
        useraccesslevel.setLevelDescription("kGYxQ8ikuncYdxupUnjPujgFE7Cd69hxfGoQQyDMTlESx9LEMQ");
        useraccesslevel.setLevelHelp("GcDqfXyAgPqENd6Dhh6Up5OD3QdgMd9PI08b0oQhyBhOmuiccq");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelIcon("mzofA5HOxsSWQcZHFoUHr2p7Y7E70zzZu2yRhX8Nq8mqT8G1UA");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1461573819847l));
        user.setPasswordAlgo("3CV0lxCWZyYxnHwQIzYPQWLIcIuR40t2bmnb241BOXtIlrZhI7");
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setSessionTimeout(1447);
        user.setChangePasswordNextLogin(1);
        user.setIsDeleted(1);
        user.setUserAccessCode(36189);
        user.setAllowMultipleLogin(1);
        user.setMultiFactorAuthEnabled(1);
        user.setGenTempOneTimePassword(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setPasswordExpiryDate(new java.sql.Timestamp(1461573819914l));
        user.setIsLocked(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionDetails("JVwIyDcQKI");
        question.setLevelid(7);
        question.setQuestionIcon("JzpKo1dPiG892DgUurBzZEeCGC3Uh73KoYLSM9nR7SSMcEfmWD");
        question.setQuestion("gjLlah2wYdBBhMKBvtMcVVPDvmMkJBp0orDsnRxnqiiOIim73r");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setUser(user);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setAnswer("HMZw2nzN4jLCuqTp1NqvIYRxez3BjSrz6fUzb8SO9UNfKW5Ic1");
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setUser(user);
        userdata.setOneTimePasswordExpiry(6);
        userdata.setOneTimePassword("fxIz2qHfpscJWfzjrH2UBujWdnAmyhvz");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1461573820158l));
        userdata.setLast5Passwords("yLUzF7pawTcv8jtbkGi7U3P6d7o82lw8shOceD9p9thwbCkXm3");
        userdata.setPassword("cARFNBgvGEjGynGoT9gSAZYbAEvY9Yz3FV863qTOz41UlwdC5t");
        user.setUserData(userdata);
        login.setServerAuthImage("Gkq8JLaDjr01Yx0mgjz3J8yFkRN9Gc0J");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setServerAuthText("W4TC3FStn2rf69T0");
        user.setUserId(null);
        login.setUser(user);
        login.setLoginId("5tC3559Mi9EBRueqjaJlJhVuFrD8O7jlkiqyg4zPkuspqBE02a");
        login.setIsAuthenticated(true);
        login.setFailedLoginAttempts(9);
        Login LoginTest = new Login();
        if (isSave) {
            LoginTest = loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
        }
        Sone sone = new Sone();
        sone.setSnm("8zRSItqghpqaXrSPpsp3K9sFX9WjivZ1ipHIMRLktJeHou3VL2");
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
