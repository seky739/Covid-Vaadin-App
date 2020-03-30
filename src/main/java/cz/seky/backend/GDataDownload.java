package cz.seky.backend;


import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;


import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;


public class GDataDownload {


    private List<Integer> numbers=new ArrayList<>();
    private List<String> labels=new ArrayList<>();


    public List<Integer> getNumbers() {
        return numbers;
    }



    public List<String> getLabels() {
        return labels;
    }



    public GDataDownload() {
        File spreadsheet = new File("test.ods");
        String url = ("https://docs.google.com/spreadsheets/d/10TMEdU8kxqdlbyGr7jLD4ioCmived2CnB6DKS8WELm0/export?format=ods");

        Executor executor = Executor.newInstance();
        try {
            executor.execute(Request.Get(new URI(url)).connectTimeout(10000000)).saveContent(spreadsheet);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        Sheet sheet = null;
        try {
            sheet = SpreadSheet.createFromFile(spreadsheet).getSheet(1);
            //Get row count and column count
            int nColCount = sheet.getColumnCount();
            int nRowCount = sheet.getRowCount();

            //System.out.println("Rows :" + nRowCount);
            //System.out.println("Cols :" + nColCount);
            //Iterating through each row of the selected sheet
            MutableCell cell = null;
            for (int nRowIndex = 1; nRowIndex < 14; nRowIndex++) {
                //Iterating through each column
                int nColIndex = 1;
                for (; nColIndex < 3; nColIndex++) {
                    cell = sheet.getCellAt(nColIndex, nRowIndex);
                    if (nColIndex==1){
                        labels.add(cell.getValue().toString());
                    }else {
                        numbers.add(Integer.parseInt(cell.getValue().toString()));
                    }
                    //System.out.print(cell.getValue() + " ");
                }
                //System.out.println();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }


}
