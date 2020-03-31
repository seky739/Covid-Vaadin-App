package cz.seky.views.cr;

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
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.annotation.UIScope;
import cz.seky.backend.CrServiceDownload;
import cz.seky.views.main.MainView;


import java.util.Arrays;

@Route(value = "dashboard", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("COVID-INFO")
@CssImport("./styles/views/dashboard/dashboard-view.css")
@UIScope
@org.springframework.stereotype.Component
public class CrView extends Div {

    CrServiceDownload crServiceDownload = CrServiceDownload.getInstance();

    public CrView() {
        setId("tested");
        //add(getChartTested(), getChartInfected());//,getChartGoogle()); // AppLayout

        //System.out.println(mzcrDownload.getTestedData().toString())
        //
        add(createChart(crServiceDownload.getTestedData(), crServiceDownload.getTestedData2(), crServiceDownload.getTestedXaxisLabel(),"Testů za den","Testů celkem","Počet testů za celou ČR"));
    add(createChart(crServiceDownload.getInfectedData1(), crServiceDownload.getInfectedData2(), crServiceDownload.getTestedXaxisLabel(),"Počet nakažených za den","Počet nakažených celkem","Počet nakažených"));

        setHeight("60%");
        setWidth("60%");


    }


    private Component createChart(Integer[] data1,Integer[] data2,String[] xasisLabel,String name1,String name2,String title){
        ApexCharts apexCharts = new ApexCharts();
        Series<Integer> series = new Series<Integer>();
        series.setData(data1);
        series.setName(name1);


        Series<Integer> series2 = new Series<Integer>();
        series2.setData(data2);
        series2.setName(name2);

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
        titleSubtilte.setText(title);
        titleSubtilte.setAlign(Align.left);

        // Grid
        Grid grid = new Grid();
        Row row = new Row();
        row.setColors(Arrays.asList(new String[]{"#f3f3f3", "transparent"}));
        row.setOpacity(0.5);
        grid.setRow(row);

        // Xaxis
        XAxis xaxis = new XAxis();
        xaxis.setCategories(Arrays.asList(xasisLabel));

        // Tooltip
        Tooltip tooltip = new Tooltip();
        tooltip.setEnabled(true);

        // Include them all
        series.setType(SeriesType.line);
        series2.setType(SeriesType.line);
        apexCharts.setSeries(series, series2);
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

        return apexCharts;
    }


}

