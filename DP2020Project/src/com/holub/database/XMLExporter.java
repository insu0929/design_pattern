//implemented by YIS
package com.holub.database;
/***
 * HTML Exporter: ���� ���� ����
 ***/
import java.io.*;
import java.util.*;

public class XMLExporter implements Table.Exporter
{	private final Writer out;
    private 	  int	 width;
    private String tableName;
    private String[] columnNames;
    public XMLExporter( Writer out )
    {	this.out = out;
    }

    public void storeMetadata( String tableName,
                               int width,
                               int height,
                               Iterator columnNames ) throws IOException

    {	this.width = width;
        this.tableName = tableName;
        this.columnNames = new String[width];

        for(int i = 0; columnNames.hasNext(); i++){
            this.columnNames[i] = columnNames.next().toString();
        }

        out.write("<" + this.tableName +">\n");
        System.out.print("<" + this.tableName +">\n");
    }

    public void storeRow( Iterator data ) throws IOException
    {	int i = 0;

        while( data.hasNext() )
        {	Object datum = data.next();
            if( datum != null ){
                out.write("\t\t<" + this.columnNames[i] + ">");
                System.out.print("\t\t<" + this.columnNames[i] + ">");
                out.write( datum.toString() );
                System.out.print(datum.toString());
            }

            if( i < width ){
                out.write("</" + this.columnNames[i] + ">\n");
                System.out.print("</" + this.columnNames[i] + ">\n");
                i++;
            }
        }
    }

    public void startTable() throws IOException {
        out.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        System.out.print("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
    }
    public void endTable()   throws IOException {
        out.write("</" + this.tableName +" >\n");
        System.out.print("</" + this.tableName +" >\n");
    }
} 
