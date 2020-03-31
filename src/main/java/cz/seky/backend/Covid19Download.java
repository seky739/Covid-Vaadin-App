package cz.seky.backend;


import java.util.ArrayList;
import java.util.List;


import com.google.gson.Gson;
import cz.seky.backend.objects.ApiAge;
import cz.seky.backend.objects.ApiMaster;
import cz.seky.backend.objects.ApiRegion;
import cz.seky.backend.objects.ApiSex;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
public class Covid19Download {


    private static Covid19Download instance;
    ApiMaster apiMaster;

    private List<Integer> numbersArea = new ArrayList<>();
    private List<String> labelsArea = new ArrayList<>();

    private Integer[] numbersSexMaleField,numbersSexFemaleField;
    private String[] labelsSexMaleField,labelsSexFemaleField;




    private List<Integer> numbersSexMale = new ArrayList<>();
    private List<String> labelsSexMale = new ArrayList<>();


    private List<Integer> numbersSexFemale = new ArrayList<>();
    private List<String> labelsSexFemale = new ArrayList<>();

    private String headerInfo="";

    public String getHeaderInfo() {
        return headerInfo;
    }

    public void setHeaderInfo(String headerInfo) {
        this.headerInfo = headerInfo;
    }

    public List<Integer> getNumbersArea() {
        return numbersArea;
    }


    public List<String> getLabelsArea() {
        return labelsArea;
    }

    private Covid19Download() {
    }


    public static Covid19Download getInstance() {
        if (instance == null) {
            instance = new Covid19Download();
            instance.getData();
        }
        return instance;
    }

    public ApiMaster getApiMaster() {
        return apiMaster;
    }

    public Integer[] getNumbersSexMaleField() {
        return numbersSexMaleField;
    }

    public Integer[] getNumbersSexFemaleField() {
        return numbersSexFemaleField;
    }

    public String[] getLabelsSexMaleField() {
        return labelsSexMaleField;
    }

    public String[] getLabelsSexFemaleField() {
        return labelsSexFemaleField;
    }

    public void refresh() {
        numbersArea.clear();
        labelsArea.clear();
        instance.getData();
    }

    private void getData() {
        HttpGet httpGet = HttpGet.getInstance();
        httpGet.callUrl("https://api.apify.com/v2/key-value-stores/K373S4uCFR9W1K8ei/records/LATEST?disableRedirect=true");

        Gson g = new Gson();


        apiMaster = g.fromJson(httpGet.callUrl("https://api.apify.com/v2/key-value-stores/K373S4uCFR9W1K8ei/records/LATEST?disableRedirect=true"), ApiMaster.class);


        headerInfo="            Poslední aktualizace   "+apiMaster.getLastUpdatedAtSource();
        for (ApiRegion apiRegion : apiMaster.getInfectedByRegion()
        ) {
            numbersArea.add(Integer.parseInt(apiRegion.getValue()));
            labelsArea.add(apiRegion.getName());
        }

        for (ApiSex apiSex : apiMaster.getInfectedByAgeSex()
        ) {
            if (apiSex.getSex().equals("muž")) {
                for (ApiAge apiAge : apiSex.getInfectedByAge()
                ) {
                    numbersSexMale.add(Integer.parseInt(apiAge.getValue()));
                    labelsSexMale.add(apiAge.getAge());
                }
            } else {
                for (ApiAge apiAge : apiSex.getInfectedByAge()
                ) {
                    numbersSexFemale.add(Integer.parseInt(apiAge.getValue()));
                    labelsSexFemale.add(apiAge.getAge());
                }
            }

        }
        prepareDataset();
    }

    private void prepareDataset(){


        numbersSexMaleField=new Integer[numbersSexMale.size()];
        numbersSexMaleField=numbersSexMale.toArray(numbersSexMaleField);

        numbersSexFemaleField=new Integer[numbersSexFemale.size()];
        numbersSexFemaleField=numbersSexFemale.toArray(numbersSexFemaleField);

        labelsSexMaleField=new String[labelsSexMale.size()];
        labelsSexMaleField=labelsSexMale.toArray(labelsSexMaleField);

        labelsSexFemaleField=new String[labelsSexFemale.size()];
        labelsSexFemaleField=labelsSexFemale.toArray(labelsSexFemaleField);

    }
}
