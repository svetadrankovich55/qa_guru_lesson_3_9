package tests;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class TestFirstForm extends TestBase {
    @Test
    @DisplayName("Проверка успешного заполнения формы регистрации")
    void successfulFillFormTest() {
        Faker faker = new Faker();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userEmail = fakeValuesService.bothify("????##@gmail.com");
        String userNumber = fakeValuesService.regexify("[0-9]{10}");
        String currentAddress = faker.address().fullAddress();

        String gender = "Female";
        String subject = "English";
        String dayOfBirth = "10";
        String monthOfBirth = "May";
        String yearOfBirth = "1988";
        String hobby1 = "Sports";
        String hobby2 = "Reading";
        String hobby3 = "Music";
        String picture = "test.jpg";
        String state = "Rajasthan";
        String city = "Jaipur";

        File file = new File("src/test/resources/test.jpg");

        step("Открыть форму регистрации студента ", () -> {
            open("https://demoqa.com/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });

        step("Заполнить форму регистрации студента ", () -> {
            $("#firstName").val(firstName);
            $("#lastName").val(lastName);
            $("#userEmail").val(userEmail);
            $("#genterWrapper").$(byText(gender)).click();
            $("#userNumber").val(userNumber);
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption(monthOfBirth);
            $(".react-datepicker__year-select").selectOption(yearOfBirth);
            $(".react-datepicker__day--0"+dayOfBirth).click();
            $("#subjectsInput").click();
            $("#subjectsInput").setValue(subject);
            $$("#react-select-2-option-0").findBy(text(subject)).click();
            $(byText(hobby1)).click();
            $(byText(hobby2)).click();
            $(byText(hobby3)).click();
            $("#uploadPicture").uploadFile(file);
            $("#currentAddress").scrollTo().setValue(currentAddress);
            $(byText("Select State")).click();
            $(byText("Rajasthan")).click();
            $(byText("Select City")).click();
            $(byText("Jaipur")).click();
            $("#submit").click();
        });

        step("Проверить успешность заполнения формы регистрации", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $x("//td[text()='Student Name']/following::td[1]").shouldHave(text(firstName + " " + lastName));
            $x("//td[text()='Student Email']/following::td[1]").shouldHave(text(userEmail));
            $x("//td[text()='Gender']/following::td[1]").shouldHave(text(gender));
            $x("//td[text()='Mobile']/following::td[1]").shouldHave(text(userNumber));
            $x("//td[text()='Date of Birth']/following::td[1]").shouldNot(empty);
            $x("//td[text()='Date of Birth']").parent().shouldHave(text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
            $x("//td[text()='Subjects']/following::td[1]").shouldHave(text(subject));
            $x("//td[text()='Hobbies']/following::td[1]").shouldNot(empty);
            $x("//td[text()='Hobbies']").parent().shouldHave(text(hobby1 + ", " + hobby2 + ", " + hobby3));
            $x("//td[text()='Picture']/following::td[1]").shouldNot(empty);
            $x("//td[text()='Picture']").parent().shouldHave(text(picture));
            $x("//td[text()='Address']/following::td[1]").shouldHave(text(currentAddress));
            $x("//td[text()='State and City']/following::td[1]").shouldNot(empty);
            $x("//td[text()='State and City']").parent().shouldHave(text(state + " " + city));
        });

    }

    @Test
    @DisplayName("Проверка успешного заполнения формы регистрации без указания текущего адреса")
    void successfulFillFormTestWithoutCurrentAddress() {
        Faker faker = new Faker();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userEmail = fakeValuesService.bothify("????##@gmail.com");
        String userNumber = fakeValuesService.regexify("[0-9]{10}");


        String gender = "Female";
        String subject = "English";
        String dayOfBirth = "10";
        String monthOfBirth = "May";
        String yearOfBirth = "1988";
        String hobby1 = "Sports";
        String hobby2 = "Reading";
        String hobby3 = "Music";
        String picture = "test.jpg";
        String state = "Rajasthan";
        String city = "Jaipur";

        File file = new File("src/test/resources/test.jpg");

        step("Открыть форму регистрации студента ", () -> {
            open("https://demoqa.com/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });

        step("Заполнить форму регистрации студента без указания текущего адреса", () -> {
            $("#firstName").val(firstName);
            $("#lastName").val(lastName);
            $("#userEmail").val(userEmail);
            $("#genterWrapper").$(byText(gender)).click();
            $("#userNumber").val(userNumber);
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption(monthOfBirth);
            $(".react-datepicker__year-select").selectOption(yearOfBirth);
            $(".react-datepicker__day--0"+dayOfBirth).click();
            $("#subjectsInput").click();
            $("#subjectsInput").setValue(subject);
            $$("#react-select-2-option-0").findBy(text(subject)).click();
            $(byText(hobby1)).click();
            $(byText(hobby2)).click();
            $(byText(hobby3)).click();
            $("#uploadPicture").uploadFile(file);
            $(byText("Select State")).click();
            $(byText("Rajasthan")).click();
            $(byText("Select City")).click();
            $(byText("Jaipur")).click();
            $("#submit").click();
        });

        step("Проверить успешность заполнения формы регистрации без указания текущего адреса", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $x("//td[text()='Student Name']/following::td[1]").shouldHave(text(firstName + " " + lastName));
            $x("//td[text()='Student Email']/following::td[1]").shouldHave(text(userEmail));
            $x("//td[text()='Gender']/following::td[1]").shouldHave(text(gender));
            $x("//td[text()='Mobile']/following::td[1]").shouldHave(text(userNumber));
            $x("//td[text()='Date of Birth']/following::td[1]").shouldNot(empty);
            $x("//td[text()='Date of Birth']").parent().shouldHave(text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
            $x("//td[text()='Subjects']/following::td[1]").shouldHave(text(subject));
            $x("//td[text()='Hobbies']/following::td[1]").shouldNot(empty);
            $x("//td[text()='Hobbies']").parent().shouldHave(text(hobby1 + ", " + hobby2 + ", " + hobby3));
            $x("//td[text()='Picture']/following::td[1]").shouldNot(empty);
            $x("//td[text()='Picture']").parent().shouldHave(text(picture));
            $x("//td[text()='Address']/following::td[1]").shouldHave(empty);
            $x("//td[text()='State and City']/following::td[1]").shouldNot(empty);
            $x("//td[text()='State and City']").parent().shouldHave(text(state + " " + city));
        });

    }

}
