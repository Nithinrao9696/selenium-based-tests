package net.kozon.selenium.example.test.framework.internet.tests;

import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import net.kozon.selenium.example.test.framework.common.docker.EnvironmentOnDocker;
import net.kozon.selenium.example.test.framework.common.tests.BaseTest;
import net.kozon.selenium.example.test.framework.common.utils.PageObjectTheInternetManager;
import net.kozon.selenium.example.test.framework.common.utils.UrlProvider;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Dariusz_Kozon on 17-May-17.
 */
public final class FileUploadTest extends BaseTest {

    private String url;
    private String containerId;
    private PageObjectTheInternetManager manager;
    private EnvironmentOnDocker environmentOnDocker;

    private static final String NAME_OF_FILE_FOR_UPLOAD = "test.html";
    private static final String PATH_TO_RESOURCE_FOR_UPLOAD = String.format("src/test/resources/%s", NAME_OF_FILE_FOR_UPLOAD);

    public FileUploadTest() {
        manager = new PageObjectTheInternetManager(webDriver);
        environmentOnDocker = new EnvironmentOnDocker();
    }

    @BeforeMethod
    private void startUp() throws InterruptedException, DockerCertificateException, DockerException {
        containerId = environmentOnDocker.startDockerClient();
        url = UrlProvider.DOCKER_INTERNET.getUrl();
    }

    @AfterMethod
    private void tearDown() {
        webDriver.quit();
        tearDownGridIfNeeded();
        Assertions.assertThat(environmentOnDocker.stopAndRemoveDockerClient(containerId)).isTrue();
    }

    @Test
    public void shouldUploadFile() {
        manager.getMainPage().loadPage(url);
        Assertions.assertThat(manager.getMainPage().isLoaded()).isTrue();
        manager.getMainPage().clickUploadLink();
        Assertions.assertThat(manager.getFileUploadPage().isLoaded()).isTrue();
        manager.getFileUploadPage()
                .uploadFile(PATH_TO_RESOURCE_FOR_UPLOAD)
                .makeUploadButtonScaled()
                .clickUpload();
        Assertions.assertThat(manager.getFileUploadPage().isFileUploaded(NAME_OF_FILE_FOR_UPLOAD)).isTrue();
    }
}
