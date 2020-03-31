package cz.seky.views.baNews;

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
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import cz.seky.backend.Covid19Download;
import cz.seky.views.main.MainView;

import java.util.Arrays;

@Route(value = "banews", layout = MainView.class)
@PageTitle("COVID-INFO")
public class BaNewsView extends Div {

    Covid19Download covid19Download = Covid19Download.getInstance();

    public BaNewsView() {
        VerticalLayout verticalLayout =new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        verticalLayout.add(new Label(covid19Download.getHeaderInfo()));
        verticalLayout.add(new Label("      Počet celkově vyléčeno      "+covid19Download.getApiMaster().getFromBabisNewspapers().getTotalCured()));
        verticalLayout.add(new Label("      Počet celkové umrtí      "+covid19Download.getApiMaster().getFromBabisNewspapers().getTotalDeaths()));
        verticalLayout.add(new Label("      Počet celkově nakaženo      "+covid19Download.getApiMaster().getFromBabisNewspapers().getTotalInfected()));
        verticalLayout.add(new Label("      Počet celkově otestováno      "+covid19Download.getApiMaster().getFromBabisNewspapers().getTotalTested()));

        add(verticalLayout);
    }


}
