package cz.seky.backend;

import com.github.appreciated.apexcharts.ApexCharts;
import com.google.gson.Gson;
import com.vaadin.flow.component.html.Label;
import cz.seky.backend.objects.Infected;
import cz.seky.backend.objects.MasterTested;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
public class CrServiceDownload {

    private static CrServiceDownload instance;

    Integer[] testedData;
    Integer[] testedData2;
    String[] testedXaxisLabel;
    Label header;

    ApexCharts apexCharts;

    Integer[] infectedData1;
    Integer[] infectedData2;
    String[] infectedXaxisLabel;

    private CrServiceDownload() {
    }

    public ApexCharts getApexCharts() {
        return apexCharts;
    }

    public static CrServiceDownload getInstance() {
        if (instance == null) {
            instance = new CrServiceDownload();
           // instance.getData();
            instance.prepareFirstChart();
            instance.prepareSecondChart();
        }
        return instance;
    }

    public void refresh(){
        instance.prepareFirstChart();
        instance.prepareSecondChart();
    }


    private void prepareFirstChart(){
        HttpGet httpGet=HttpGet.getInstance();
        String result=httpGet.callUrl("https://onemocneni-aktualne.mzcr.cz/api/v1/covid-19/testy.json");

        Gson g = new Gson();

        MasterTested dates = g.fromJson(result, MasterTested.class);

        header=new Label("Aktualizace provedena naposledy : "+dates.getModified()+"           Zdroj : "+dates.getSource());

        testedData=new Integer[dates.getData().length];
        testedData2=new Integer[dates.getData().length];
        testedXaxisLabel=new String[testedData.length];
        for (int i=0;i<testedData.length;i++) {
            testedData[i] = dates.getData()[i].getTestDay();
            testedData2[i]=dates.getData()[i].getTestFull();
            testedXaxisLabel[i]=dates.getData()[i].getDatum();
        }




    }

    private void prepareSecondChart(){



        HttpGet httpGet = HttpGet.getInstance();
        String result = httpGet.callUrl("https://onemocneni-aktualne.mzcr.cz/api/v1/covid-19/nakaza.json");
        //System.out.println(result);

        Gson g = new Gson();

        Infected[] data = g.fromJson(result, Infected[].class);
        // System.out.println(Arrays.toString(data));
        infectedData1 = new Integer[data.length];
        infectedData2 = new Integer[data.length];
        infectedXaxisLabel = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            infectedData1[i] = data[i].getPocetDen();
            infectedData2[i] = data[i].getPocetCelkem();
            infectedXaxisLabel[i] = data[i].getDatum();
        }
    }


    public Integer[] getTestedData() {
        return testedData;
    }

    public Integer[] getTestedData2() {
        return testedData2;
    }

    public String[] getTestedXaxisLabel() {
        return testedXaxisLabel;
    }

    public Label getHeader() {
        return header;
    }

    public Integer[] getInfectedData1() {
        return infectedData1;
    }

    public Integer[] getInfectedData2() {
        return infectedData2;
    }

    public String[] getInfectedXaxisLabel() {
        return infectedXaxisLabel;
    }
}
