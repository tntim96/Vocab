package translator;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class DownloaderTest {
    private Downloader downloader = new Downloader();

    @Test
    public void shouldEscapeTextInUrlAndAddCorrectLanguageEncodings() {
        String url = downloader.encodeText("the dog");
        String expected = "the%20dog";
        assertThat(url, equalTo(expected));
    }

    @Test
    public void shouldEscapeAccent() {
        String url = downloader.encodeText("le garçon");
        String expected = "le%20gar%C3%A7on";
        assertThat(url, equalTo(expected));
    }

    @Test
    public void shouldKeepAccent() throws Exception {
        String path = "/accent.txt";
        String json = downloader.readContent(getClass().getResourceAsStream(path));
        assertThat(json, equalTo("élève"));
    }
}
