package cz.seky.views.tests;

import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.config.*;
import com.github.appreciated.apexcharts.config.builder.ChartBuilder;
import com.github.appreciated.apexcharts.config.builder.LegendBuilder;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.chart.Zoom;
import com.github.appreciated.apexcharts.config.chart.builder.ZoomBuilder;
import com.github.appreciated.apexcharts.config.grid.Row;
import com.github.appreciated.apexcharts.config.legend.HorizontalAlign;
import com.github.appreciated.apexcharts.config.series.SeriesType;
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
import cz.seky.backend.objects.Infected;
import cz.seky.backend.objects.MasterTested;
import cz.seky.backend.HttpGet;
import cz.seky.views.main.MainView;


import java.util.ArrayList;
import java.util.Arrays;

@Route(value = "dashboard", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("COVID-INFO")
@CssImport("./styles/views/dashboard/dashboard-view.css")
public class TestedView extends Div {



    public TestedView() {
        setId("tested");
        add(getChartTested(),getChartInfected()); // AppLayout

    }


    public Component getChartTested(){
        Integer[] data;
        Integer[] data2;
        String[] xaxisLabel;


        HttpGet httpGet=HttpGet.getInstance();
        String result=httpGet.callUrl("https://onemocneni-aktualne.mzcr.cz/api/v1/covid-19/testy.json");

        Gson g = new Gson();

        MasterTested dates = g.fromJson(result, MasterTested.class);

        add(new Label("Aktualizace provedena naposledy : "+dates.getModified()));
        add(new Label("           Zdroj : "+dates.getSource()));

        data=new Integer[dates.getData().length];
        data2=new Integer[dates.getData().length];
        xaxisLabel=new String[data.length];
        for (int i=0;i<data.length;i++) {
            data[i] = dates.getData()[i].getTestDay();
            data2[i]=dates.getData()[i].getTestFull();
            xaxisLabel[i]=dates.getData()[i].getDatum();
        }

        ApexCharts apexCharts = new ApexCharts();
        Series<Integer> series = new Series<Integer>();
        series.setData(data);
        series.setName("Testů za den");


        Series<Integer> series2 = new Series<Integer>();
        series2.setData(data2);
        series2.setName("Testů celkem");

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
        titleSubtilte.setText("Počet testů za celou ČR");
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
        series.setType(SeriesType.line);
        series2.setType(SeriesType.line);
        apexCharts.setSeries(series,series2);
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

    public Component getChartInfected(){


        Integer[] data1;
        Integer[] data2;
        String[] xaxisLabel;

        HttpGet httpGet=HttpGet.getInstance();
        String result=httpGet.callUrl("https://onemocneni-aktualne.mzcr.cz/api/v1/covid-19/nakaza.json");
        //System.out.println(result);

        Gson g = new Gson();

        Infected[] data=g.fromJson(result,Infected[].class);
        // System.out.println(Arrays.toString(data));
        data1=new Integer[data.length];
        data2=new Integer[data.length];
        xaxisLabel=new String[data.length];
        for (int i=0;i<data.length;i++) {
            data1[i] =data[i].getPocetDen();
            data2[i]=data[i].getPocetCelkem();
            xaxisLabel[i]=data[i].getDatum();
        }
        ApexCharts apexCharts=new ApexCharts();

        Series<Integer> series = new Series<>();
        series.setData(data1);
        series.setName("Počet potvzených za den");

        Series<Integer> series2 = new Series<>();
        series2.setData(data2);
        series2.setName("Počet potvrzených celkem");


        // Labels

        DataLabels dataLabels = new DataLabels();
        dataLabels.setEnabledOnSeries(new ArrayList<Double>());
        dataLabels.setEnabled(false);

        // Stroke
        Stroke stroke = new Stroke();
        stroke.setCurve(Curve.straight);

        // Title
        TitleSubtitle titleSubtilte = new TitleSubtitle();
        titleSubtilte.setText("Počet pozitivních případů");
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
        series.setType(SeriesType.column);
        series2.setType(SeriesType.line);

        apexCharts.setSeries(series,series2);
        apexCharts.setLegend(LegendBuilder.get().withHorizontalAlign(HorizontalAlign.center).build());
        apexCharts.setChart(ChartBuilder.get()
                .withType(Type.line)
                .withZoom(ZoomBuilder.get()
                        .withEnabled(true)
                        .build())
                .build());

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

