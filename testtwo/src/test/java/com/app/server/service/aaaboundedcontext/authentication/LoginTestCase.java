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
        user.setPasswordAlgo("DF3s6cIu6dq9HLVxEt8OAY6P8CqcJ2FeS4ReQatFOaoUO9zluz");
        user.setGenTempOneTimePassword(1);
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelHelp("wy7RI7pBLwsXjz8drYuLRBFon3fdfzvQFI7kp9IgYNMylHmbz3");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelIcon("mkmfofDuOhuByvCq2wplYd2vbEotEoxtt8wPmRlmhWi9iwrcvI");
        useraccesslevel.setLevelDescription("relAUmMWlrAVYUIQwFFXZbOQpY2qSgedktSoi8EsL72pFRfOnD");
        useraccesslevel.setLevelName("5IWhOT4jZdG0FbI0JxCylTXoQ3cjmVIhdcewcbIYb6Etv5ErMo");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainName("Ck1Nph6REGoTY4rffgzeHaAdK9axDjodAq84Q8oPDqf3MmTY9w");
        useraccessdomain.setDomainIcon("PQtpbdEWKBAd6R0Hcaz1IKDGDclaa788fB71RvG99vciecSvTv");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainDescription("PDGKI8CwDbBo1IU59WVInBl8SulVBcpv9FvB7cPq1N6YclWeiU");
        useraccessdomain.setDomainHelp("QVpUbOPjMTJefSh9Z8AtKxMj6tRCLgB7Y9DOomT8q7KdB4YoWJ");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        user.setPasswordAlgo("Yb5odjhLUL7OV7uvYlVk0FG21rjE4Lx8EZ4G7LNXv03e65QZwF");
        user.setGenTempOneTimePassword(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsDeleted(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1460975174878l));
        user.setMultiFactorAuthEnabled(1);
        user.setAllowMultipleLogin(1);
        user.setChangePasswordNextLogin(1);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsLocked(1);
        user.setSessionTimeout(2510);
        user.setUserAccessCode(55555);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1460975174917l));
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestion("1e5LSbCmOVZrshuv7Uh8ZagEc9ns7P7ZiHrKghvxHaw1as10fu");
        question.setQuestionDetails("0pCNAKT7A0");
        question.setLevelid(8);
        question.setQuestionIcon("JERwXwgzMNQxF8HwfwRjIuVBgzqLYznDFncGNSeBoj1UcZopj6");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setAnswer("9J9ban4bPspVT9H9pQrPQ25dYhmd2Y3kql2kNfe2sTwKShGZZu");
        passrecovery.setUser(user);
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setLast5Passwords("nAKieWSVfDBC8xyDkTMpOGQSFKXzJVx6e4YDDDe9r05dGig6XQ");
        userdata.setOneTimePassword("n3hqWqCTdSfoCVJA548dripMdICTXoAg");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1460975175095l));
        userdata.setPassword("n14pYtGiOjtCquYpj1zAyTLZfzrY5UT5wQRN4cFIXKWAblAMmr");
        userdata.setLast5Passwords("Bgp19wd88eJqdEYkC59rD2lIkpkscMp8zHrP53LXUSoHfDmwdh");
        userdata.setOneTimePassword("fdyFdsif3uWqEr9GRSvRj063wLde4VR3");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1460975175113l));
        userdata.setPassword("aii9BetjO1IVlKxurEDFiTUhe8VaQpb5uXWWL0lxnr0RrpJs3C");
        userdata.setUser(user);
        userdata.setOneTimePasswordExpiry(4);
        user.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeLastName("RYlkjQFvOqPk9pAbCSqAw9FVIvwO234yUv6tCZvHzCVOPxHpWP");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1460975175205l));
        corecontacts.setPhoneNumber("QMVepBNBYfZrGhC3DMoh");
        corecontacts.setNativeMiddleName("FtSeUF86gi6SBVazq4BfTUAeznFruZ4EK4kt5R2YNLerFGK42q");
        Gender gender = new Gender();
        gender.setGender("1yqP37XQprb9jXhsG16hqvKArQSQiKjI0CIIzvcGuexosk8Zdl");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setAlpha2("7A");
        language.setLanguageType("OpZSi9S8gHtiNoRyjhlRLP6wJ5oS6nk9");
        language.setLanguage("Hgw9AlqJLKmlGJyuIg0DGKNwXLuxgqYEfqoHMPWAkdNiVBsw97");
        language.setAlpha3("pAs");
        language.setAlpha4("7Nao");
        language.setLanguageDescription("CfmyRGhrFE2JEceFttS3l6zcsPZQZQ2Tg79TR0eiFnTpnsSX0l");
        language.setLanguageIcon("Cm2sCj3YIoLCdcZi8vUHBvASQWo4kJ6ggrpciOC73tCdp3Xt0B");
        language.setAlpha4parentid(9);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("VPiXV0Q13mgWVL8Cy21tlUH1PNiQUNSxx07meydPA8g4EF2ixi");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("RcxvWqdYuC0DHkkRM9DxjsKVTvEUFCaVIZEBwQONJ8uYngGADE");
        timezone.setCities("17OZMFjBnvILIeDtm4yDbSH3uh2jNBHsVkCOga6qav2NVQ5www");
        timezone.setUtcdifference(10);
        timezone.setCountry("Q9nrFiJKm0j90ac7StKaaRXNnEQ9PypJZCNxSC2EOVTPGD5OaL");
        timezone.setGmtLabel("8NxBJHMReUdAkBajJFeFgMOKNg22JFnuZgHUvPESTOkT3GzkfY");
        corecontacts.setNativeLastName("YWgrX6LPGA5AAGpyDvWQNAt3ogyRESkYiT5YlEGZB1p4JN0VWd");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1460975175225l));
        corecontacts.setPhoneNumber("dgnB7AMaipZ4XuhCS5sd");
        corecontacts.setNativeMiddleName("iUtyzpvNG25ZZ3DNNA7OlKG2RvunwGYW10oQWwoUuMnhGn9R4K");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("RR32NEcWLNZyQI9YF45uusPk78dDdWsNvkCII91Q2K9vofWJNN");
        corecontacts.setNativeTitle("PQkHS8DRvot4HS3ANGp3wFGGsMgN7iTZSvZYIPLgPhAyZmyJUi");
        corecontacts.setAge(96);
        corecontacts.setEmailId("bleFOXx8VFuVw8oplmTdimUr9F8D7Brh1llEMEg79IhXgQxHlM");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("to5MOBLoeAWzfOgJVUu67LbkZbOhW9QU04bE3R8MbE5LZuNAXA");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeFirstName("Sgu6xbczJUEt5LoJ4wAmLtSq6uVWJp0G8nILA8FqWEDCcs96nq");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setDateofbirth(new java.sql.Timestamp(1460975175349l));
        corecontacts.setFirstName("hdoOQfZEVBRDpOqPcdA9VTGmaQAzErx7plqkTwTEcDIcM5oH03");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("e3VvAl0ruA");
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("fpu3UHxxaF2cd4pc5dUhKfJWaOeHRx0pmUzlgDPTkSnVK02ipb");
        communicationtype.setCommTypeDescription("bfb4x4B5c3v80hSkB4mlOXsfiBKX6ueuslQRxBQjCwlwz97TqZ");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("HRplMoyL7HOSkPge5UAPKlbIV5nqfyNvJQrstGKGiHQoLQzzjh");
        communicationgroup.setCommGroupName("6lUBp0bkhHpSGMy8MeUYB8mxNcHkDBjqsmOp7Sv09sDfjXqeU7");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeName("GYweGMT35FBis3d6eqaPrXrhM9YotWjUPG0mnK0znSF8tJDJKA");
        communicationtype.setCommTypeDescription("4rYgaPAJaDjn7S52yZWbLfJ215VA40sb1mEy7c99CIxeBcomxG");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("m8XVm9zZeY");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress1("Izq6UYI1rIHmNeD5LDRCEv1TK3ARdOBMtm5eAJ1GzO8vq81TQi");
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("lcAJAqt0Ph5xLnMXd7Epnb0uRp6x3SCbL1KZIuN5TynNj9gDbm");
        addresstype.setAddressTypeDesc("e82HoSKpgBtkmu7L5cB22vOWsxjiBJxb7NmqwFQVmjQ2mtdgvg");
        addresstype.setAddressType("srb16emvcykfYSV5qRVBMizOxyla1p2aqfexVA1FFK2H8b5aug");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityName("cyyBas3bDv3X9F8uolThjd8owQBodVntREZztw3SHBlrFDjgqR");
        State state = new State();
        state.setStateName("EXnkjSlruN66vaQcj9his9jk1JAVXvQmmKjq9TfJkuYJmjAKys");
        state.setStateDescription("9JsgfA9BpZppy99PBVFYLkhAh4CIrTSCmnm3sMQBrKXiwSpmph");
        Country country = new Country();
        country.setCurrencyName("CWEzH3ph5GoEg7VGAXZwa6jHlA4I5iMqaV0HUvZFstJZnbqz55");
        country.setIsoNumeric(410);
        country.setCurrencySymbol("BOZkJOPo5lLHtA4mmqWZrFwNH8yrUpU1");
        country.setCountryName("KWnwncVaAkqyZlwkpWgrtoyliJRVB2UpJW47TMCS8eK0WUyjes");
        country.setCapitalLongitude(3);
        country.setCapital("k7FHUI8gcpIJI2l3OW57OP8A3b0ooQsK");
        country.setCountryFlag("5TzBFSIQvChF9nSncZdayJP63tS7q6kU26B84MULmcsVvOxOJi");
        country.setCapitalLatitude(10);
        country.setCountryCode2("OGZ");
        country.setCountryCode1("R2Z");
        country.setCurrencyCode("zCr");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("jQrzVjLXtLY61WTkCzlFtZxSOiDGoxoaxSutHp9CaOhoVphKdS");
        state.setStateDescription("LjxP6KMiEl54iajwGV0xVCVu1SOJb7DsEfys10ReHfbbK5DuAK");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar3("Cb7ZaJtsZn5NV6ytyuIqeNn7Sj08deAH");
        state.setStateCapitalLongitude(11);
        state.setStateFlag("ylxoeoRDviB3SDESyC0CfcsOvjaiomWoQJkGsTMSF6Al6vYvCG");
        state.setStateCapitalLatitude(11);
        state.setStateCodeChar2("O1gRisDt0ZeyJTXg8srXTYm89tFqNKZ9");
        state.setStateCapital("FolYgyHSdOAq6hY4bHr0ryL2WWUd6OM0qKsJ7WqswwukPwR4XR");
        state.setStateCode(1);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityName("vmWm6YIUtoPVtjQ15CnZeIGeJmcxG952TmFUEqonCRxAPy90GT");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("85cGUetNJnAdkvv6HVRZMuGo4HEVRHGo2BONK7eFvWQGiOhLyh");
        city.setCityCodeChar2("YDFvFgQMfZhL0XNxj5UsJdXLvVUxBibt");
        city.setCityCode(2);
        city.setCityLatitude(8);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("iusx9bElxeNe3MWU34M3AIkaMGROnCJjMGHDxhfOD9frIMpzZ8");
        city.setCityLongitude(10);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddress1("TWY69f5hcIYU3vO7iABvWjSIJ5o8ICwcwayUGHj88yRwkufvwT");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("r7jnmjFaENuAGIrqvDMlf8gGy1cQUMsx9ZadDZYLaJtHq9GAbD");
        address.setLongitude("fNxF28c6Do1HM5tR4vJKOcIoCgf09hLcDecHf9CioBGJEYnPLT");
        address.setZipcode("ALUSqT");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        address.setAddress3("e6Jxc5MH4BztNYbs9jdHjWPor165mFJB69jkxW577rpqyVmzpn");
        address.setLatitude("D3QfEX3Y7QXZiW7UfCgFFSN9w4Fw74royD3b2QkEaGuMy1sPHS");
        address.setAddressLabel("Muxcoa5ljHh");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        Login login = new Login();
        login.setIsAuthenticated(true);
        login.setServerAuthImage("AyVIfGK0d7qdEyWnB4zC25NCEBUwmsSi");
        login.setServerAuthText("EQxKwPt0QPaNPnoh");
        login.setLoginId("U3rUqGjDPt8B1uWZi4S8LWpaniC0Vx200gYykUAYnVSJNlNHLq");
        user.setUserId(null);
        login.setUser(user);
        login.setFailedLoginAttempts(4);
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
            login.setServerAuthImage("rOXgDQ2go0WjheywQTz3AWksaE9Qf8mg");
            login.setServerAuthText("UKNMehuyUoJgCVHF");
            login.setLoginId("l4u7iipVJmHzTKbAwcZ3rxLEixXBZnlBOmbVWOhKiefzC1yz05");
            login.setVersionId(1);
            login.setFailedLoginAttempts(7);
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "w7WoVbw0TudeaRh8Ks3qqGz2UxoBVivdkFlmPBLBBC9BgsIJZM4MXwaWhjopstplAVBGP3sdSr4cuvWCplh9a8ouiU2jqQoqgNSOmxfXVzzL3R8tbkWHSLc6yEMfDCLbDwkm50dNXyRb5orC6gY6MBOUzgrOUeuE7ipfMpDITgia9f1aLFQrMwskOYhwPwkh5otLOyjRK"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "U0mgza55gI85RroL5D8xlivZp0wWahchq"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "2ncQsdzvotqJQCnuJ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 17));
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
