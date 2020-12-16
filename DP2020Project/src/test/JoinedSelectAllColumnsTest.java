package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.holub.database.ConcreteTable;
import com.holub.database.Cursor;
import com.holub.database.Selector;
import com.holub.database.Table;
import com.holub.*;


/**
 * MUST Implementation #3:  select * from address, name where address.addrId = name.addrId
 * This class is a test class for junit testing for implementation #3
 * The format of the join select function is select(where, requestedColumns, other)
 * **/
public class JoinedSelectAllColumnsTest {

	Table table1;
	Table table2;
	
	Selector where;
	String[] requestedColumns;
	Table[] otherTables;
	
	Table resultTable;
	
	String[] testColumns;
	
	@BeforeEach
	void init() {
		String[] nameColumns = new String[] {"first", "last", "addrId"};
		String[] addrColumns = new String[] {"addrId", "street", "city", "state", "zip"};
		
		table1 = new ConcreteTable("name" , nameColumns);
		table2 = new ConcreteTable("adress", addrColumns);
		where = Selector.ALL;;
		otherTables = new Table[] {table1};
		resultTable = table2.select(where, requestedColumns, otherTables);
		 testColumns = new String[] {"zip", "last", "city", "street", "addrId", 
					"state", "first"};
	}
	
	@Test
	void test() {
		//createTestData();
		Cursor rows = resultTable.rows();

		
		for(int i = 0 ; i < rows.columnCount(); i++) {
			assertEquals(testColumns[i], rows.columnName(i));
		}
		
	}

}
