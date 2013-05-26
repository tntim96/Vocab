package translator;

import org.junit.After;
import org.junit.Test;

import java.io.File;

import static java.util.Locale.ENGLISH;
import static java.util.Locale.FRENCH;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DownloaderIntegrationTest {
    private Downloader downloader = new Downloader();
    File file = new File("test.mp3");

    @After
    public void tearDown() {
        file.delete();
    }

    @Test
    public void testDownloadMp3English() throws Exception {
        downloader.downloadMp3("cat", ENGLISH, file);
        assertThat(file.length()>1024, equalTo(true));
    }

    @Test
    public void testDownloadMp3French() throws Exception {
        downloader.downloadMp3("chat", FRENCH, file);
        assertThat(file.length() > 1024, equalTo(true));
    }
}
