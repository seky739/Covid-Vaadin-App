package cz.seky.views.tests;

import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.config.*;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.chart.Zoom;
import com.github.appreciated.apexcharts.config.grid.Row;
import com.github.appreciated.apexcharts.config.stroke.Curve;
import com.github.appreciated.apexcharts.config.subtitle.Align;
import com.github.appreciated.apexcharts.helper.Series;
import com.google.gson.Gson;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import cz.seky.backend.objects.MasterTested;
import cz.seky.backend.HttpGet;
import cz.seky.views.main.MainView;



import java.util.Arrays;

@Route(value = "tested", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Test in Czech Republic")
@CssImport("styles/views/dashboard/dashboard-view.css")
public class TestedView extends Div {

    Integer[] data;
    String[] xaxisLabel;


    public TestedView() {
        setId("tested");


        HttpGet httpGet=HttpGet.getInstance();
        String result=httpGet.callUrl("https://onemocneni-aktualne.mzcr.cz/api/v1/covid-19/testy.json");
        System.out.println(result);

        Gson g = new Gson();

        MasterTested dates = g.fromJson(result, MasterTested.class);
        System.out.println(dates.toString());

        add(new Label("Aktualizace provedena naposledy : "+dates.getModified()));
        add(new Label("           Zdroj : "+dates.getSource()));

        data=new Integer[dates.getData().length];
        xaxisLabel=new String[data.length];
        for (int i=0;i<data.length;i++) {
            data[i] = dates.getData()[i].getTestDay();
            xaxisLabel[i]=dates.getData()[i].getDatum();
        }



        add(getChart()); // AppLayout

    }


    public Component getChart(){

        ApexCharts apexCharts = new ApexCharts();
        Series<Integer> series = new Series<Integer>();
        series.setData(data);
        series.setName("Testů za den");

        // Chart
        Chart chart = new Chart();
        //chart.setHeight("350");
        chart.setType(Type.line);
        Zoom zoom = new Zoom();
        zoom.setEnabled(true);
        chart.setZoom(zoom);

        // Labels
        DataLabels dataLabels = new DataLabels();
        dataLabels.setEnabled(false);

        // Stroke
        Stroke stroke = new Stroke();
        stroke.setCurve(Curve.straight);

        // Title
        TitleSubtitle titleSubtilte = new TitleSubtitle();
        titleSubtilte.setText("Počet testů za den celkově za celou ČR");
        titleSubtilte.setAlign(Align.left);

        // Grid
        Grid grid = new Grid();
        Row row = new Row();
        row.setColors(Arrays.asList(new String[]{"#f3f3f3", "transparent"}));
        row.setOpacity(0.5);
        grid.setRow(row);

        // Xaxis
        XAxis xaxis = new XAxis();
        xaxis.setCategories(Arrays.asList(xaxisLabel));

        // Tooltip
        Tooltip tooltip = new Tooltip();
        tooltip.setEnabled(true);

        // Include them all
        apexCharts.setSeries(series);
        apexCharts.setChart(chart);
        apexCharts.setDataLabels(dataLabels);
        apexCharts.setStroke(stroke);
        apexCharts.setTitle(titleSubtilte);
        apexCharts.setGrid(grid);
        apexCharts.setXaxis(xaxis);
        apexCharts.setTooltip(tooltip);

        // Render them and include into the content

        return apexCharts;
    }





}

