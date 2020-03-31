package cz.seky.views.sex;

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
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cz.seky.backend.Covid19Download;
import cz.seky.views.main.MainView;

import java.util.Arrays;

@Route(value = "malefemale", layout = MainView.class)
@PageTitle("COVID-INFO")
public class MaleFemaleView extends Div {

Covid19Download covid19Download = Covid19Download.getInstance();

    public MaleFemaleView(){


add(covid19Download.getHeaderInfo());
//add(createChart(gDataDownload.getNumbersSexMale(),gDataDownload.getLabelsSexMale(),"Počet pozitivních případů u mužů","Počet pozitivních případů u mužů dle věku"));

        add(createChart(covid19Download.getNumbersSexMaleField(), covid19Download.getLabelsSexMaleField(),"Počet pozitivních případů u mužů","Počet pozitivních případů u mužů dle věku"));
        add(createChart(covid19Download.getNumbersSexFemaleField(), covid19Download.getLabelsSexFemaleField(),"Počet pozitivních případů u žen","Počet pozitivních případů u žen dle věku"));


    }


    private Component createChart(Integer[] data1, String[] xasisLabel,String name1,String title){
        ApexCharts apexCharts = new ApexCharts();
        Series<Integer> series = new Series<Integer>();
        series.setData(data1);
        series.setName(name1);


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
        series.setType(SeriesType.column);
        apexCharts.setSeries(series);
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
