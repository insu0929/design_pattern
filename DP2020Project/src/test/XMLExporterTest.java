package test;
//implemented by YIS
import com.holub.database.Table;
import com.holub.database.TableFactory;
import com.holub.database.XMLExporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * XML I mporter 와 XML Exporter . XML: 위와 유사하게 XML 태그를 이용한 importer 와
exporter 를 개발
 * This class is a test class for junit testing for implementation #2
 * **/

class XMLExporterTest {

    Table nameTable;
    Writer writer;
    XMLExporter xmlExporter;
    String[] col, row;
    String typeResult;
    String nameResult;
    String rowResult;
    String tableResult;
    

    @BeforeEach
    void init(){
    	writer  = new StringWriter();
    	xmlExporter = new XMLExporter(writer);
        col = new String[]{"first", "last", "addrId"};
        row = new String[]{"test1", "test1", "1"};
        nameTable = TableFactory.create("name", col);
        nameTable.insert(row);
        
        typeResult = "<?xml version=\"1.0\"?>\n<root>\n";
        nameResult =  "\t<table name=\"person\">\n";
        rowResult =  "\t<table name=\"person\">\n" +
                "\t<row>\n" +
                "\t\t<first>test1</first>\n" +
                "\t\t<last>test1</last>\n" +
                "\t\t<addrId>1</addrId>\n" +
                "\t</row>\n";
        
        tableResult = "<?xml version=\"1.0\"?>\n" +
                "<root>\n" +
                "\t<table name=\"person\">\n" +
                "\t<row>\n" +
                "\t\t<first>test1</first>\n" +
                "\t\t<last>test1</last>\n" +
                "\t\t<addrId>1</addrId>\n" +
                "\t</row>\n" +
                "\t</table>\n" +
                "</root>";
    }

    @Test
    @DisplayName("table type")
    void startTable() throws IOException {
        xmlExporter.startTable();
        assertEquals(writer.toString(), typeResult);
    }


    @Test
    @DisplayName("table name")
    void initTableName() throws IOException {
        xmlExporter.storeMetadata("person", 3, 1, Arrays.stream(col).iterator());
        assertEquals(writer.toString(), nameResult);
    }

    @Test
    @DisplayName("table row")
    void insertRow() throws IOException {
        xmlExporter.storeMetadata("person", 3, 1, Arrays.stream(col).iterator());
        xmlExporter.storeRow(Arrays.stream(row).iterator());
        assertEquals(writer.toString(), rowResult);
    }

    @Test
    @DisplayName("table result")
    void exportXML() throws IOException {
        xmlExporter.startTable();
        xmlExporter.storeMetadata("person", 3, 1, Arrays.stream(col).iterator());
        xmlExporter.storeRow(Arrays.stream(row).iterator());
        xmlExporter.endTable();
        assertEquals(writer.toString(), tableResult);
    }
}