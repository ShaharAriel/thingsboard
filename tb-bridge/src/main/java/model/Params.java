package model;

import com.devicewise.tr50.api.params.DwOpenPropertyParam;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Params extends DwOpenPropertyParam {

    private String name;
    private String thingKey;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getThingKey() {
        return thingKey;
    }

    public void setThingKey(String thingKey) {
        this.thingKey = thingKey;
    }

    public double getState() {
        return getValue();
    }

    public void setState(double state) {
        setValue(state);
    }
}
