package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {

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
                yearofbirth ="1989",
                dayofbirth = "09",
                firstsubject = "eco",
                fullsubject = "Economics",
                hobby = "Sports",
                state = "NCR",
                city = "Delhi",
                pic = "test.jpg";

        open("https://demoqa.com/automation-practice-form");

                $("#firstName").val(firstName);
                $("#lastName").val(lastName);
                $("#userEmail").val(userEmail);
                $("#genterWrapper").$(byText(gender)).click();
                $("#userNumber").val(userNumber);

                $("#dateOfBirthInput").click();
                $(".react-datepicker__month-select").selectOption(monthofbirth);
                $(".react-datepicker__year-select").selectOption(yearofbirth);
                $(".react-datepicker__day--0" + dayofbirth).click();

                $("#subjectsInput").val(firstsubject);
                $(".subjects-auto-complete__menu-list").$(byText(fullsubject)).click();

                $("#hobbiesWrapper").$(byText(hobby)).click();
                $("#uploadPicture").uploadFile(new File("src/test/resources/" + pic));
                $("#currentAddress").val(address);
                $("#state").scrollTo().click();
                $(byText(state)).click();
                $("#city").click();
                $(byText(city)).click();
                $("#submit").scrollTo().click();
                $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
                //Проверка формы на корректность заполнения
                $x("*//tr[1]/td[2]").shouldHave(text(firstName + " " + lastName));
                $x("*//tr[2]/td[2]").shouldHave(text(userEmail));
                $x("*//tr[3]/td[2]").shouldHave(text(gender));
                $x("*//tr[4]/td[2]").shouldHave(text(userNumber));
                $x("*//tr[5]/td[2]").shouldHave(text(dayofbirth + " " + monthofbirth + "," + yearofbirth));
                $x("*//tr[6]/td[2]").shouldHave(text(fullsubject));
                $x("*//tr[7]/td[2]").shouldHave(text(hobby));
                $x("*//tr[8]/td[2]").shouldHave(text(pic));
                $x("*//tr[9]/td[2]").shouldHave(text(address));
                $x("*//tr[10]/td[2]").shouldHave(text(state + " " + city));


    }

}
