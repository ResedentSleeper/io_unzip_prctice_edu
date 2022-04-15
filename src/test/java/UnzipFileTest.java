import com.epam.autocode.io.unzip.UnzipFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class UnzipFileTest {

    UnzipFile unzip;

    @BeforeEach
    public void init() {
        unzip = new UnzipFile();
    }


    @ParameterizedTest
    @CsvSource(value = {
            "src/main/resources/archive1.zip, src/main/resources/out, src/main/resources/expected1.txt, src/main/resources/tmp.txt",
            "src/main/resources/archive3.zip, src/main/resources/out, src/main/resources/expected3.txt, src/main/resources/tmp.txt",
            "src/main/resources/archive2.zip, src/main/resources/out, src/main/resources/expected2.txt, src/main/resources/tmp.txt"
    })
    public void testFilesOut(String archive, String dir, String expected, String tmp) {
        try {
            PrintStream out = new PrintStream(tmp);
            System.setOut(out);
            unzip.unzip(archive, dir);

            String line, path;
            BufferedReader br1 = new BufferedReader(new FileReader(tmp));
            BufferedReader br2 = new BufferedReader(new FileReader(expected));

            assertTrue(IOUtils.contentEqualsIgnoreEOL(br1, br2));

            while ((line = br2.readLine()) != null) {
                path = dir + line;
                File tempFile = new File(path);
                assertTrue(tempFile.exists());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    @ParameterizedTest
    @NullSource
    void testNullSource(String null_1) {
        assertThrows(NullPointerException.class, () -> {
            unzip.unzip(null_1, null_1);
        });
    }


    @ParameterizedTest
    @EmptySource
    void testEmptySource(String empty_1) {
        assertThrows(FileNotFoundException.class, () -> {
            unzip.unzip(empty_1, empty_1);
        });
    }


    @AfterEach
    public void restoreSource() {
        try {
            FileUtils.deleteDirectory(new File("src/main/resources/out"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
