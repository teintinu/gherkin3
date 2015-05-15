package minicuke.plugins.html;

import gherkin.GherkinLine;
import gherkin.GherkinLineSpan;
import gherkin.IGherkinLine;
import gherkin.Parser;
import gherkin.Token;
import gherkin.ast.Location;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class HtmlScanner implements Parser.ITokenScanner {

    private final Document document;
    private final Iterator<Element> elements;

    public HtmlScanner(InputStream html, String fileName) {
        try {
            document = Jsoup.parse(html, "UTF-8", fileName);
            elements = document.select("[data-cucumber], [href^=cucumber://]").iterator();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Token read() {
        final Element element = elements.next();
        Location location = new Location(0, 0); // TODO: Find a way to set line number
        IGherkinLine line = new IGherkinLine() {
            @Override
            public Integer indent() {
                return 0;
            }

            @Override
            public void detach() {
            }

            @Override
            public String getLineText(int indentToRemove) {
                return element.text();
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean startsWith(String prefix) {
                throw new UnsupportedOperationException();
            }

            @Override
            public String getRestTrimmed(int length) {
                throw new UnsupportedOperationException();
            }

            @Override
            public List<GherkinLineSpan> getTags() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean startsWithTitleKeyword(String keyword) {
                throw new UnsupportedOperationException();
            }

            @Override
            public List<GherkinLineSpan> getTableCells() {
                throw new UnsupportedOperationException();
            }
        };
        Token token = new Token(line, location);
        return token;
    }
}
