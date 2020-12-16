package test;
//implemented by YIS
import com.holub.database.XMLImporter;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * XML I mporter 와 XML Exporter . XML: 위와 유사하게 XML 태그를 이용한 importer 와
exporter 를 개발
 * This class is a test class for junit testing for implementation #2
 * **/
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class XMLImporterTest {

    private XMLImporter xmlImporter;
    private String[] col, row;

    @BeforeAll
    void init() throws IOException {
        xmlImporter = new XMLImporter(new FileReader(new File("C:\\Users\\insu0\\Downloads\\DP2020Project\\name.xml")));
        col = new String[]{"first", "last", "addrId"};
        row = new String[]{"Fred", "Flintstone", "1"};
    }


    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class ImportStart {
        @Test
        @DisplayName("tablename")
        void name() throws IOException {
            xmlImporter.startTable();
            assertEquals(xmlImporter.loadTableName(), "name");
        }

        @Test
        @DisplayName("table columns")
        void columns() throws IOException{
            Iterator target = Arrays.stream(col).iterator();
            Iterator expected = xmlImporter.loadColumnNames();

            while(expected.hasNext())
                assertEquals(expected.next().toString(), target.next().toString());
        }
    }

    @Test
    @DisplayName("row")
    void row() throws IOException {
        xmlImporter.startTable();
        Iterator target = xmlImporter.loadRow();
        Iterator expected = Arrays.stream(row).iterator();

        while(expected.hasNext())
            assertEquals(expected.next().toString(), target.next().toString());
    }
}
