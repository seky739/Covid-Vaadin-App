package cz.seky.views.area;

import com.github.appreciated.apexcharts.ApexCharts;
import com.github.appreciated.apexcharts.config.*;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.chart.Zoom;
import com.github.appreciated.apexcharts.config.grid.Row;
import com.github.appreciated.apexcharts.config.series.SeriesType;
import com.github.appreciated.apexcharts.config.stroke.Curve;
import com.github.appreciated.apexcharts.config.subtitle.Align;
import com.github.appreciated.apexcharts.helper.Series;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import cz.seky.backend.Covid19Download;
import cz.seky.views.main.MainView;

import java.util.Arrays;

@Route(value = "gspread", layout = MainView.class)
@org.springframework.stereotype.Component
@UIScope
@PageTitle("COVID-INFO")
public class AreaCrView extends Div {

    Covid19Download covid19Download = Covid19Download.getInstance();

    public AreaCrView() {
        add(new Label(covid19Download.getHeaderInfo()));
        add(getChartGoogle());

        setHeight("70%");
        setWidth("70%");
    }

    public Component getChartGoogle() {


        Integer[] data = new Integer[covid19Download.getNumbersArea().size()];
        String[] xaxisLabel = new String[covid19Download.getLabelsArea().size()];
        for (int i = 0; i < data.length; i++) {
            data[i] = covid19Download.getNumbersArea().get(i);
            xaxisLabel[i] = covid19Download.getLabelsArea().get(i);
        }


        ApexCharts apexCharts = new ApexCharts();
        Series<Integer> series = new Series<Integer>();
        series.setData(data);
        series.setName("Počet pozitivních případů v kraji");


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
        titleSubtilte.setText("Počet pozitivních nakažených dle krajů");
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
