//implemented by YIS
package com.holub.database;
/***
 * HTML Exporter: ���� ���� ����
 ***/
import java.io.*;
import java.util.*;


public class XMLExporter implements Table.Exporter {

    private final Writer out;
    private String[] columns;
    int i = 0;

    public XMLExporter(Writer out) {
        this.out = out;
    }

    @Override
    public void startTable() throws IOException {
        out.write("<?xml version=\"1.0\"?>\n<root>\n");
    }

    @Override
    public void storeMetadata(String tableName, int width, int height, Iterator columnNames) throws IOException {
        out.write("\t<table name=\"");
        out.write(tableName.toString());
        out.write("\">\n");

        columns = new String[width];
        while (columnNames.hasNext()) {
            columns[i++] = columnNames.next().toString();
        }

    }

    @Override
    public void storeRow(Iterator data) throws IOException {
        i = 0;
        out.write("\t<row>\n");
        while (data.hasNext()) {
            Object rowData = data.next();

            out.write("\t\t<" + columns[i].toString() + ">");
            if (rowData != null)
                out.write(rowData.toString());
            out.write("</" + columns[i].toString() + ">\n");
            i++;
        }
        out.write("\t</row>\n");
    }

    @Override
    public void endTable() throws IOException {
        out.write("\t</table>\n</root>");
    }
}
