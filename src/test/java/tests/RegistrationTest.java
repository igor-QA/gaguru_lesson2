package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Locale;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest extends TestBase {

    @BeforeAll
    static void setup() {

        Configuration.startMaximized = true;
    }

    @Test
    void randomRegistrationTest() {
        //Подключаем библиотеку для рандомного заполненения полей формы
        Faker faker = new Faker();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userEmail = fakeValuesService.bothify("????##@gmail.com");
        String userNumber = fakeValuesService.regexify("[0-9]{10}");
        String address = faker.address().fullAddress();

        String gender = "Male",
                monthofbirth = "November",
                yearofbirth = "1989",
                dayofbirth = "09",
                firstsubject = "eco",
                fullsubject = "Economics",
                hobby = "Sports",
                state = "NCR",
                city = "Delhi",
                pic = "test.jpg";

        open("https://demoqa.com/automation-practice-form");


        FormPage
                .inputFirstName(firstName)
                .inputLastName(lastName)
                .inputEmail(userEmail)
                .inputGender(gender)
                .inputUserNumber(userNumber)
                .inputDateBirthday(dayofbirth, monthofbirth, yearofbirth)
                .inputSubject(firstsubject, fullsubject)
                .selectHobby(hobby)
                .selectPic(pic)
                .inputAddress(address)
                .selectState(state)
                .selectCity(city)
                .clickSubmitBtn()
                //$("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
                .verifyHeaderValue();

        FormPage
                .verifyCorrectValues(firstName, lastName, userEmail, gender, userNumber, dayofbirth,
                        monthofbirth, yearofbirth, fullsubject, hobby, pic, address, state, city);


    }
}