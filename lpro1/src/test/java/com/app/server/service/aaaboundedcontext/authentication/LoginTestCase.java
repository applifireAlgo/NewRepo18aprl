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
        corecontacts.setNativeMiddleName("iGc1aJjFhvJoADkbTvUZN9klDzbS02YAodssiENXIPjwv5LM67");
        Title title = new Title();
        title.setTitles("3qIor4zdTzcfblofbCkSGL7ZWPtN4xIaslYzFyzCHSXh8QaAMA");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setCities("piqKI1Ce7mC8r0t6XNt3GIf9U6DkHEYxj007e3PhXbPUU3aNUm");
        timezone.setCountry("1PPOOmr52pZe8UIw2Cx6SIH0zaICmZSkwd9d3WR0bivN1nGa5Q");
        timezone.setGmtLabel("rk0FmxzC9KAFtVuJAY8kyz7W3b06PSWcZWPkzYOS6uwPptqR04");
        timezone.setUtcdifference(7);
        timezone.setTimeZoneLabel("F00Nxmj7FfHyStyahwfxPryvPXmQNvdckCmPynerqJNAkkbFK1");
        Gender gender = new Gender();
        gender.setGender("V0r9zz7WtlQJ6Y8jyf9ypbLNvRSxG7UNHrsRo7AeeH84G6Hg71");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setAlpha2("FJ");
        language.setAlpha3("5ba");
        language.setLanguageType("PAs59qRH4XKgdzzB8pr0graQlSxL5HU2");
        language.setAlpha4("R3Tv");
        language.setLanguageIcon("DvRjYeGrKS54DrVS4NMjNce9slVzq4vzDS5U4PnywEkWZRACvQ");
        language.setLanguageDescription("AdXl2wJgaPwKlpxeV3741JoriYegM5k7zJhoHR5gR3fJRbyFyP");
        language.setLanguage("Nu9J22pjANLbugxyldMJXvQr70OpIyRxCafjTU85pGFrot7AFe");
        language.setAlpha4parentid(1);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        corecontacts.setNativeMiddleName("LkmVInaI2cvRfGNhZWeYQdAb4mf8x84i6Fm2dIH87Eu9XQHPMY");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("rpDcqSC1mdSIwyYwk6hUPg1NgY0Bnpsa48zsLyYPEEVX2J91H7");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setFirstName("V9GWgSpDr2WEfZ1QmpBRtIdDJGEDGfeVo3W9mszVzR6IepxT6E");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeFirstName("MYWdtjWhNXuCMUTZJ7bw2gXl0fM1AAyt7fpGx0btNsTCR2iHP0");
        corecontacts.setLastName("gcPUKBPxmHRsrd1x8n904MApTp2zJPaLNhgWOwwXH6feJ201N5");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1461062628910l));
        corecontacts.setAge(49);
        corecontacts.setEmailId("Ay3NOLj5TPmRFhf8fVVd22nyw9lNRBpbBeCAVolFqEMSPBVGw3");
        corecontacts.setNativeTitle("K9nxAp4ti8OKkmlvqh1DxJvoNMkGXZMPyO59QvEMbGd2s4WRvt");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1461062628911l));
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLastName("S2waIEYpdU6va1Yun4nXTKUMGIF0XFpjrT3qUYo8Ug285OT3Fa");
        corecontacts.setPhoneNumber("fGNl3O2K4lFn78hXfBOJ");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setLongitude("xvnv7ELp4Ihm9byhrFkdz1WQhgFotaw7Ot7CmFaMuqNrt7WA0K");
        City city = new City();
        city.setCityCode(3);
        city.setCityLongitude(8);
        city.setCityLatitude(2);
        State state = new State();
        state.setStateDescription("6MvGqvTJ7cfvam4mhVL7KgU8MESV79O5rwPRytRonwhf2OHZwX");
        state.setStateCode(2);
        state.setStateCapital("j5cKlVcrmCNT6zynuJ7F0DBowBQB6Ee4kKaN35deNEwvz4SN9P");
        state.setStateName("8UMS60X1e2krZ2v1VDymrCRH1yFnMh3JrV7HL2qj3S65AUwExl");
        state.setStateCodeChar2("tvZq47SDVXtf9E2ZuPS24Q4WGDrwbnvA");
        state.setStateCapitalLongitude(9);
        state.setStateCodeChar3("ZZYdkp9DMDAxmXuuOkPKoiHeFzV4nG2X");
        state.setStateCapitalLatitude(2);
        Country country = new Country();
        country.setCurrencyName("5gJwXaShd1VACSjXOTg8Ez0Kp8eq64D1TI6fsBUEExH7JTTeUt");
        country.setCountryCode1("T31");
        country.setIsoNumeric(742);
        country.setCountryName("Vy3OiBmq7krvgt7KgDed4CyTNNaPiVgRTnyHIQlsoKd5KGupLV");
        country.setCapital("WZCa7Kq6saAQfdTwFedhDgTgDmXY44cH");
        country.setCapitalLatitude(1);
        country.setCurrencySymbol("Usnt7JbcUbpuAquVrb9EAcDN8qoyo99w");
        country.setCurrencyCode("ETo");
        country.setCapitalLongitude(10);
        country.setCountryFlag("oFEa3gFG5lh3Abv5TQiKr6HSeNpHcCKr0FQZWC2XBOIKIyoedG");
        country.setCountryCode2("XCk");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateDescription("SXtHlRGvRALcmv7XcynvbR8nUOH3n5EfdjQZBqNwZEGBIyEVtx");
        state.setStateCode(1);
        state.setStateCapital("dhCWa6bMKvNrSSDA97iudlEgWUX8npx3T8MlmM6s5eFntpszDf");
        state.setStateName("BxPQTPx24rsQWsQ64q6i6VFGKD7XMY51oOPSq9S5DWx0ciU3vy");
        state.setStateCodeChar2("168FfTaPuTUMD13h34qgW04Jckc1hIg5");
        state.setStateCapitalLongitude(1);
        state.setStateCodeChar3("URwXWlOJrcx2ZkNYBqaHxrlwV2RLWbVT");
        state.setStateCapitalLatitude(7);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateFlag("ZT6nUS4Tk8Hnk7QYWu7ZWRebIvIAPsztOQTioXQRAZbB3ylNdZ");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityCode(3);
        city.setCityLongitude(6);
        city.setCityLatitude(9);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityName("Hg3ZzmPvc35kHSFM637Cnb8U2fPO1fpzWndiy6KWDnFM37WB12");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("I24A3lHsdWIF0MVgvWfYp8qw7BvtgBbcMu32g0JTN9HT3Y8lw2");
        city.setCityDescription("990p0hw1ZR52VoxLzpvre6dU1Eef6IUWrug3i8r9NtdYV0Dapt");
        city.setCityCodeChar2("eGPtyWxqECYEgFJjSWFxvkTrT1SGjMTL");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("i9tPveaPDfD0b1R6B8dKfxeGfCLn7OjKIMAd5TQId9ilw8uZib");
        addresstype.setAddressType("DLPO6UzrRAvYMIuplCT9olqqDofVQI4Z4uBgj5Z9F3LfeR9uka");
        addresstype.setAddressTypeDesc("Zki3eWRb7YLLGHLyOnDewMmTD3J4E6j1TRWPkYwSfVBYzMf8Pe");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setLongitude("eYNg1ZN1qvYrVGNibIWP1T9eErWRQPtOL3iqOMrIYFoUtNzpdq");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("2iPVlRPxdKevRbyCrZ7CWLxhSi9y6hVwvZFEyP19Gt7CndbjLH");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("7XH3q7");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("YFiOdDhaHrOvS0xk2ZhL3FX4R9YQbTdVbvmyvKNh8fFmuWdWMQ");
        address.setAddress1("s9Fnfcyi45pgObeH95X6QLCpZyFjzswbvaPzPpFhv2jMASn7Qr");
        address.setAddressLabel("ccFSB2UIsP9");
        address.setAddress3("QmVQ9iWcnVWyFwdWEYR7B0BKpECEKL8ydkqpFpYFHe1hsvCyZD");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeDescription("jmVmuhElDAQx6OY86DoYgMG7N3HyYfOSjgl5QpHVWlFJwZs5eI");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("ViSwZyRLNKb6dUd2acb48QOtaDMjul9Hyjv4Lg2Ek1YQsyCgRG");
        communicationgroup.setCommGroupDescription("yAd04oPKRm7Q3af5T5XHDsOsUdSk4ujFHTT6LmS4WVBhrS3opn");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeDescription("MRcgVWCqO2iupuDnFiTcvmsXm0P0IvTK9bePhB6CUs205zVQTa");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("rVtgzSMS21d4iN7I00SAdrgIZ0BcJYfPcIeLHCwvV1pJgVq6pE");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("Zs6AggwrLX");
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        User user = new User();
        user.setUserAccessCode(719);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1461062629308l));
        user.setIsLocked(1);
        user.setGenTempOneTimePassword(1);
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("cFL2SRtvqcDwq2Se1XmgxyAHaFDfA8CXv5Qb642ImXefEY8Dff");
        useraccesslevel.setLevelHelp("LCMaqnC8ROn6wEGqKB8py6h44OoUcV94NuaPRi3dzuww6Af1Xd");
        useraccesslevel.setLevelIcon("Ay38vq2HCUaRNev5HBHGrVeI1uUEm22vs9lU4ZP99u9b4eM9pQ");
        useraccesslevel.setLevelName("MXAvweP1aQ8IhZgKTr1a4SHnln84hglwylUghgPhxsiaKLzA1g");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("d3VVr4JFD2ovlHWK64WzocxbnBYTiO5Z6v2QFFmMfz2ZIwnIfZ");
        useraccessdomain.setDomainIcon("LZdZpPS2zqEti9uz2FtlT2D5xdmZnC7ERUjY9735YE9sXKctY1");
        useraccessdomain.setDomainName("vjRQogxYh8Uu9jXgWEBiOy7FtKXP0vR8Pwa0qFMevyJh3aNpKn");
        useraccessdomain.setDomainDescription("VVQxaStbQc0aR69Riav3xNWOrsY6JpXkfylz8C9VKZxBmMCbuv");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        user.setUserAccessCode(27177);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1461062629327l));
        user.setIsLocked(1);
        user.setGenTempOneTimePassword(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setChangePasswordNextLogin(1);
        user.setIsDeleted(1);
        user.setMultiFactorAuthEnabled(1);
        user.setAllowMultipleLogin(1);
        user.setSessionTimeout(2425);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1461062629356l));
        user.setPasswordAlgo("LgaOpf3oCREpr2FZcmgKj3b4j2UcJP9zqtg7Uh4wpkzuTbrVyE");
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionIcon("k2IolpqAOwEFxQmjUzA2zW7LLHdOzyinnwoyYaDrMz4HHSO2hH");
        question.setLevelid(8);
        question.setQuestionDetails("QWLBU28G6N");
        question.setQuestion("LGbtpiS2DBo2SrAJMSPrE0wYo1VJqCE5iHEpMUTBvqy3gULbmT");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setUser(user);
        passrecovery.setAnswer("WI1nDKEpTMxlOELH402XciH0P4We84vmRv5Aub8tLMdp2jSn2Q");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey());
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setUser(user);
        userdata.setOneTimePassword("6RKu1yvdrVxjpSGAGdnUEI3phLAYd8nn");
        userdata.setPassword("FeRVq9auLU6hMsTSKj6aTcFiev2YDAMFeIPXBEe625l1FayY79");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1461062629632l));
        userdata.setOneTimePasswordExpiry(6);
        userdata.setLast5Passwords("ur6Evtx3pYw3EuwVBR00ooZRIOiCmyPOxvXsUmrHZucMBKx1X9");
        user.setUserData(userdata);
        Login login = new Login();
        login.setLoginId("rh8XhCoAJ37vVRhyEXKFWWBASv2YU29hfjcRLHeDDsIQPjNOYA");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setIsAuthenticated(true);
        login.setServerAuthText("LnTFMxdYtR3yHwrX");
        user.setUserId(null);
        login.setUser(user);
        login.setServerAuthImage("Bem9KfJrvxyTN47okRkUfsUH6kS75fOU");
        login.setFailedLoginAttempts(4);
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
            login.setLoginId("cJrtQDgWqFdahO3FvyQ4wd9JBfM75ZlAlQVsvCDhJkZ7uBsSmE");
            login.setVersionId(1);
            login.setServerAuthText("VknqQG3gtuSPevir");
            login.setServerAuthImage("ZHkaZUAguBkr0dMkllR4l0iAnrbtN08r");
            login.setFailedLoginAttempts(2);
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
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
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
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "U4btek5cY6cuXfeURPtrTe0CTXrsqjEp0jnf6Qg0qh2N8ioBVHmw51bhKlOoWikjxeGEaNhdhqHqX0rseIjxpfKVwtlq6pHgWr0fmLim29vJ3M7UUXGJLMoryiW9iy5ui66aOgRunedeM6JMhdcT6eLwRdQAEePdzH9Dt8kTEAm9acFyHe9pTTwjphcswGv6YgsiEJYSl"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "SXCKA6jhwSXO8gj2WPr92iqNziNHEz6uk"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "TMehMXFgF4R9RJd8F"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 12));
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
