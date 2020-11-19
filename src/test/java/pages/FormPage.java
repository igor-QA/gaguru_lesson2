package pages;

import java.io.File;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FormPage {

    public FormPage inputFirstName (String firstName) {
        $("#firstName").val(firstName);
        return this;
    }
    public FormPage inputLastName (String lastName) {
        $("#lastName").val(lastName);
        return this;
    }
    public FormPage inputEmail (String userEmail) {
        $("#userEmail").val(userEmail);
        return this;
    }
    public FormPage inputGender (String gender) {
        $("#genterWrapper").$(byText(gender)).click();
        return this;
    }
    public FormPage inputUserNumber (String userNumber) {
        $("#userNumber").val(userNumber);
        return this;
    }
    public FormPage inputDateBirthday(String dayofbirth, String monthofbirth, String yearofbirth) {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthofbirth);
        $(".react-datepicker__year-select").selectOption(yearofbirth);
        $(".react-datepicker__day--0" + dayofbirth).click();

        return this;
    }
    public FormPage inputSubject(String firstsubject, String fullsubject) {
        $("#subjectsInput").val(firstsubject);
        $(".subjects-auto-complete__menu-list").$(byText(fullsubject)).click();
        return this;
    }
    public FormPage selectHobby(String hobby){
        $("#hobbiesWrapper").$(byText(hobby)).click();
        return this;
    }
    public FormPage selectPic(String pic) {
        $("#uploadPicture").uploadFile(new File("src/test/resources/" + pic));
        return this;
    }
    public FormPage inputAddress(String address) {
        $("#currentAddress").val(address);
        return this;
    }
    public FormPage selectState(String state){
        $("#state").scrollTo().click();
        $(byText(state)).click();
        return this;
    }
    public FormPage selectCity(String city) {
        $("#city").click();
        $(byText(city)).click();
        return this;
    }
    public FormPage clickSubmitBtn() {
        $("#submit").scrollTo().click();
        return this;
    }
    public void verifyValue (){
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

    }
    public void verifyCorrectValues (String firstName, String lastName, String userEmail, String gender, String userNumber,
                                     String dayofbirth, String monthofbirth, String yearofbirth, String fullsubject,
                                     String hobby, String pic, String address, String state, String city) {

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



