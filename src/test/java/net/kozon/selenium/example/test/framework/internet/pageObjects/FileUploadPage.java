package net.kozon.selenium.example.test.framework.internet.pageObjects;

import net.kozon.selenium.example.test.framework.common.pageObjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Created by Dariusz_Kozon on 17-May-17.
 */
public final class FileUploadPage extends BasePage<FileUploadPage> {

    @Override
    protected FileUploadPage getThis() {
        return this;
    }

    @FindBy (id = "file-upload")
    private WebElement fileUploadController;

    @FindBy (id = "file-submit")
    private WebElement uploadButton;

    private String fileUploadConfirmation = "//div[@id='uploaded-files' and contains(text(), '%s')]";

    public FileUploadPage(WebDriver webDriver) {
        super(webDriver);
    }

    public FileUploadPage uploadFile(String filePath) throws URISyntaxException {
        File f = new File(filePath);
        System.out.println(f.getAbsoluteFile());
        fileUploadController.sendKeys(f.getAbsolutePath());
        return getThis();
    }

    public FileUploadPage clickUpload() {
        customWait.clickElement(uploadButton);
        return getThis();
    }

    public FileUploadPage makeUploadButtonScaled() {
        makeElementScaled(uploadButton);
        return getThis();
    }

    public boolean isFileUploaded(String fileName) {
        //example with element Changed in DOM after upload (element is not existing until DOM reload)
        return customWait.isElementPresent(By.xpath(String.format(fileUploadConfirmation, fileName)));
    }

    @Override
    public boolean isLoaded() {
        return customWait.isElementVisible(fileUploadController);
    }
}
