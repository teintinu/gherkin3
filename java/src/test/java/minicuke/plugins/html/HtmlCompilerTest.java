package minicuke.plugins.html;

import org.junit.Test;
import pickles.Pickle;
import pickles.Uri;

import java.io.IOException;
import java.util.List;

public class HtmlCompilerTest {
    @Test
    public void compiles_pickles_from_html() throws IOException {
        HtmlCompiler htmlCompiler = new HtmlCompiler();
        Uri uri = Uri.fromThisMethod(1);
        List<Pickle> pickles = htmlCompiler.compile(getClass().getResource("test.html").openStream(), uri);
    }
}
