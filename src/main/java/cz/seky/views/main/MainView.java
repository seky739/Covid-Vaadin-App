package cz.seky.views.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import cz.seky.views.area.AreaCrView;
import cz.seky.views.baNews.BaNewsView;
import cz.seky.views.sex.MaleFemaleView;
import cz.seky.views.cr.CrView;

/**
 * The main view is a top-level placeholder for other views.
 */
@JsModule("./styles/shared-styles.js")
@PWA(name = "COVID-TEST", shortName = "COVID-TEST")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
public class MainView extends AppLayout {

    //Button button = new Button("Refresh Data");

    private final Tabs menu;

    public MainView() {

       /* button.addClickListener(buttonClickEvent -> {
            MzcrDownload mzcrDownload = MzcrDownload.getInstance();
            GDataDownload gDataDownload = GDataDownload.getInstance();
            mzcrDownload.refresh();
            gDataDownload.refresh();
            UI.getCurrent().getPage().reload();
        });*/

        setPrimarySection(Section.DRAWER);
        addToNavbar(true, new DrawerToggle());
        //addToNavbar(button);
        menu = createMenuTabs();
        addToDrawer(menu);
    }

    private static Tabs createMenuTabs() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setId("tabs");
        tabs.add(getAvailableTabs());
        return tabs;
    }

    private static Tab[] getAvailableTabs() {
        final List<Tab> tabs = new ArrayList<>();
        tabs.add(createTab("Covid-ČR", CrView.class));
        tabs.add(createTab("Covid-Kraje", AreaCrView.class));
        tabs.add(createTab("Covid-Pohlaví", MaleFemaleView.class));
        tabs.add(createTab("BA-News", BaNewsView.class));

        return tabs.toArray(new Tab[tabs.size()]);
    }

    private static Tab createTab(String title, Class<? extends Component> viewClass) {
        return createTab(populateLink(new RouterLink(null, viewClass), title));
    }

    private static Tab createTab(Component content) {
        final Tab tab = new Tab();
        tab.add(content);
        return tab;
    }

    private static <T extends HasComponents> T populateLink(T a, String title) {
        a.add(title);
        return a;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        selectTab();
    }

    private void selectTab() {
        String target = RouteConfiguration.forSessionScope().getUrl(getContent().getClass());
        Optional<Component> tabToSelect = menu.getChildren().filter(tab -> {
            Component child = tab.getChildren().findFirst().get();
            return child instanceof RouterLink && ((RouterLink) child).getHref().equals(target);
        }).findFirst();
        tabToSelect.ifPresent(tab -> menu.setSelectedTab((Tab) tab));
    }
}
