package tests.day01;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.Browser;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class aboutBrowserContext {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
       BrowserContext context =  browser.newContext();
        Page page = context.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        page.getByLabel("E-Mail Address").type("testuser9988@gmail.com");
        page.getByLabel("Password").type("123456");
        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Login")).click();
        Locator myAccount = page.getByText("Edit your account information");
        assertThat(myAccount).isVisible();
        Page newTab = page.context().newPage();
        newTab.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/account");
        assertThat(myAccount).isVisible();
        newTab.close();
        context.close();


        BrowserContext context2 =  browser.newContext();
        Page userPage = context2.newPage();
        userPage.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/account77");

        BrowserType firefox = playwright.firefox();
        Page fp = firefox.launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();

        playwright.close();

        // testuser9988@gmail.com
        //123456

    }
}
