/*implemented by Yang Insu*/
package com.holub.database;

/***
 * HTML Exporter: 빌더 패턴 적용
 ***/
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

public class HTMLExporter implements Table.Exporter{

	private final Writer out;
	private int width;
	private int height;
	
	
	public HTMLExporter(Writer out) {
		this.out = out;
	}
	
	@Override
	public void startTable() throws IOException {
		// TODO Auto-generated method stub
        out.write("<!DOCTYPE html>\n");
        System.out.print("<!DOCTYPE html>\n");
        out.write("<html>\n");
        System.out.print("<html>\n");
	}

	@Override
	public void storeMetadata(String tableName, int width, int height, Iterator columnNames) throws IOException {
		// TODO Auto-generated method stub
		this.width = width;
		
        out.write("\t<head>\n");
        out.write("\t\t<title>");
        out.write(tableName == null ? "anonymous" : tableName );
        out.write("</title>\n");
        out.write("\t</head>\n");
        out.write("\t<body>\n");
        out.write("\t\t<table>\n");
        out.write("\t\t\t<tbody>\n");
        storeRow( columnNames );
	}

	@Override
	public void storeRow(Iterator data) throws IOException {
		// TODO Auto-generated method stub
		int i = width;
        if(data.hasNext()){
            out.write("\t\t\t\t<tr>");
        }
        while( data.hasNext() )
        {	Object datum = data.next();
            if( datum != null ){
                out.write("<td>");
                out.write( datum.toString() );
            }

            if( --i > 0 ){
                out.write("</td>");
            }
        }
        out.write("</tr>\n");
	}

	@Override
	public void endTable() throws IOException {
		// TODO Auto-generated method stub
        out.write("\t\t\t</tbody>\n");
        out.write("\t\t</table>\n");
        out.write("\t</body>\n");
        out.write("</html>\n");
	}

}
