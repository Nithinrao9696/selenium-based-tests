package net.kozon.selenium.internet.pages;

import net.kozon.selenium.common.pages.BasePage;
import net.kozon.selenium.framework.tools.utils.CustomWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class MainPage extends BasePage<MainPage> {

    private CustomWait customWait;

    @FindBy(xpath = "//h1[contains(text(), 'Welcome to the-internet')]")
    private WebElement theInternetHeader;

    @FindBy(xpath = "//a[contains(text(), 'File Upload')]")
    private WebElement fileUploadLink;

    @FindBy(xpath = "//a[contains(text(), 'Drag and Drop')]")
    private WebElement dragAndDropLink;

    public MainPage(WebDriver webDriver) {
        super(webDriver);
        customWait = new CustomWait(webDriver);
    }

    @Override
    protected MainPage getThis() {
        return this;
    }

    public MainPage clickUploadLink() {
        customWait.clickElement(fileUploadLink);
        return getThis();
    }

    public MainPage clickDragAndDropLink() {
        dragAndDropLink.click();
        return getThis();
    }

    @Override
    public boolean isLoaded() {
        return customWait.isElementVisible(theInternetHeader);
    }
}
