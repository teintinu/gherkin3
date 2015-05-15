package minicuke.plugins.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pickles.Pickle;
import pickles.PickleLocation;
import pickles.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class HtmlCompiler {
    public List<Pickle> compile(InputStream html, Uri uri) {
        StackTraceElement stackTraceElement = uri.createStackTraceElement(new PickleLocation(0, 0));
        try {
            Document document = Jsoup.parse(html, "UTF-8", stackTraceElement.getFileName());
            Elements elements = document.select("[data-cucumber], [href^=cucumber://]");
            for (Element element : elements) {
                System.out.println(element);
                System.out.println(element.text());
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
