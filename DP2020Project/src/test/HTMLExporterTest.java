package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.holub.database.HTMLExporter;
import com.holub.database.Table;
import com.holub.database.TableFactory;

public class HTMLExporterTest {

	Table table1;
	Writer writer;
	HTMLExporter htmlExporter;
	
	String[] row;
	String[] col;
	
	String testResult1;
	String testResult2;
	
	@BeforeEach
	void init() {
		writer = new StringWriter();
		htmlExporter = new HTMLExporter(writer);
		row = new String[] {"first", "last", "addrId"};
		col = new String[] {"test1", "test1", "1"};
		table1 = TableFactory.create("name", col);
		table1.insert(row);
		testResult1 = "<!DOCTYPE html>\r\n" + 
				"<html>\r\n";
		
		testResult2 = "<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"	<head>\r\n" + 
				"		<title>name</title>\r\n" + 
				"	</head>\r\n" + 
				"	<body>\r\n" + 
				"		<table>\r\n" + 
				"			<tbody>\r\n" + 
				"				<tr><td>test1</td><td>test1</td><td>1</tr>\r\n" + 
				"				<tr><td>first</td><td>last</td><td>addrId</tr>\r\n" + 
				"			</tbody>\r\n" + 
				"		</table>\r\n" + 
				"	</body>\r\n" + 
				"</html>\r\n" + 
				"";
	}
	@DisplayName("table type")
    @Test
    void startTable() throws IOException {
       htmlExporter.startTable();
       assertEquals(writer.toString(), testResult1);
       System.out.println(writer.toString());
       System.out.println("here");
    }
    
    @DisplayName("table result")
    @Test
    void exportHTML() throws IOException {
        htmlExporter.startTable();
        htmlExporter.storeMetadata("name", 3, 1, Arrays.stream(col).iterator());
        htmlExporter.storeRow(Arrays.stream(row).iterator());
        htmlExporter.endTable();
      
        
        assertEquals(writer.toString(), testResult2);
    }

    

    
}
