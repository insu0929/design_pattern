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
	
	public HTMLExporter(Writer out) {
		this.out = out;
	}
	
	@Override
	public void startTable() throws IOException {
		// TODO Auto-generated method stub
		/*nothing to do*/
		
	}

	@Override
	public void storeMetadata(String tableName, int width, int height, Iterator columnNames) throws IOException {
		// TODO Auto-generated method stub
		out.write("<html><head><title>");
		out.write("tableName");
		out.write("</title></head><body><tables>");
		storeRow(columnNames);
	}

	@Override
	public void storeRow(Iterator data) throws IOException {
		// TODO Auto-generated method stub
		out.write("<tr>");
		while(data.hasNext()) {
			Object datum = data.next();
			if(datum != null) {
				out.write("<td>");
				out.write(datum.toString());
				out.write("<td>");
			}
			out.write("</tr>");
		}
	}

	@Override
	public void endTable() throws IOException {
		// TODO Auto-generated method stub
		out.write("</table></body></html><style>"
				+ " table{\r\n"
				+ " width: 100%;\r\n"
				+ " border:1px solid #444444;\r\n"
				+ "}\r\n"
				+ " th, td{\r\n"
				+ " border: 1px solid #444444;\r\n"
				+ " }\r\n"
				+ "</styles>");	
	}

}
