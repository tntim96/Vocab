package translator;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.net.URLEncoder;
import java.util.Locale;

import static java.lang.String.format;
import static java.util.Locale.ENGLISH;

public class Downloader {
    private static String mp3Url = "http://translate.google.com/translate_tts?ie=UTF-8&q=%s&tl=%s&total=1&idx=0&textlen=4";

    private HttpClient client = new DefaultHttpClient();

    String readContent(InputStream is) {
        try {
//            return IOUtils.toString(is, "Cp1252");
            return IOUtils.toString(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    String encodeText(String text) {
        try {
            return URLEncoder.encode(text, "UTF-8").replace("+","%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void downloadMp3(String text, Locale locale, File file) {
//        System.out.println(String.format("Download text '%s', locale = '%s'", text, locale.getDisplayName()));
        HttpUriRequest httpUriRequest = new HttpGet(format(mp3Url, encodeText(text), locale));
        FileOutputStream fos = null;
        try {
            HttpResponse response = client.execute(httpUriRequest);
            fos = new FileOutputStream(file);
            IOUtils.copy(response.getEntity().getContent(), fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(fos);
        }
    }
}
