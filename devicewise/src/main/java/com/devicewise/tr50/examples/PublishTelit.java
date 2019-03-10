package com.devicewise.tr50.examples;

import com.devicewise.tr50.api.params.DwOpenAlarmParam;
import com.devicewise.tr50.api.params.DwOpenThingDefProperty;
import com.devicewise.tr50.api.response.session.DwOpenSessionInfo;
import com.devicewise.tr50.clients.DwHttpClient;
import com.devicewise.tr50.exception.DwOpenException;

import java.io.IOException;
import java.util.ArrayList;

import static com.devicewise.tr50.examples.HttpConnectSample.HTTP_URL;
import static com.devicewise.tr50.examples.HttpConnectSample.PASSWORD;
import static com.devicewise.tr50.examples.HttpConnectSample.USER;

public class PublishTelit {

    private static final String UNIT = "Nursery";//"Growing"//"Watering";

    public static final String THING_KEY = "uconnect_arm";

    public enum NU_Sensors {
        U1_LIGHT1,
        U1_LIGHT2,
        U1_FAN1,
        U1_FAN2,
        U1_CO2_INJ,
        U1_UP_SPRAY,
        U1_DOWN_SPRAY,
        U2_LIGHT1,
        U2_LIGHT2,
        U2_FAN1,
        U2_FAN2,
        U2_CO2_INJ,
        U2_UP_SPRAY,
        U2_DOWN_SPRAY
    }


    public enum GU_Sensors {
        GU_AIR_SENSOR_VOC,
        GU_AIR_SENSOR_ATMO_PRESSURE,
        //        GU_AIR_CO2_INJ,//backlog calib co2
        GU_AIR_CO2_SENSOR,//
        GU_A_C_TEMP,//air sensor
        GU_A_C_HUM,//air sensor
        //        GU_WATER_VALVE,
        GU_WATER_FLOW,//
        //        GU_AIR_VALVE,
        GU_TOWER_SWITCH,//
        /*GU_A_C_FAN1,
        GU_A_C_FAN3,*/
        GU_A_C_WATER,//backlog average calc
        GU_WASTE_WATER_TANK,//backlog average calc
//        GU_TOWER1_LIGHT_1,
//        GU_TOWER2_LIGHT_1,
//        GU_TOWER3_LIGHT_1,
//        GU_TOWER4_LIGHT_1,
//        GU_TOWER1_LIGHT_2,
//        GU_TOWER2_LIGHT_2,
//        GU_TOWER3_LIGHT_2,
//        GU_TOWER4_LIGHT_2,
//        GU_AUX_220V,
    }

    public enum WU_Sensors {

        MANUAL_WATERING_CLEAN,
        MANUAL_WASTE_CLEAN,
        PH_NUTRIENT,
        //        PH_PUMP,//
        EC_NUTRIENT,//
        //        EC_PUMP1,
//        EC_PUMP2,
//        EC_PUMP3,
//        EC_PUMP4,
        OZONE_SENSOR,
        WATER_LEVEL,
        //        WATER_FLUSH,//
        WASTE_EC,
        WASTE_PH,
        WATERING_PUMP,
        WATERING_TANK_TEMP,
        WASTE_TANK_TEMP,
        WASTE_TANK_FLOAT,
        OZONE_TUBE_TEMP,
        TREATED_WATER_FLOW,
        NUTRIENT_WATER_FLOW,
        AIR_SENSOR_VOC,
        AIR_SENSOR_ATMO_PRESSURE,
        AIR_CO2_SENSOR,//
        AIR_TEMP,
        AIR_HUM
    }

    public static void main(String[] args) throws IOException, DwOpenException {

        int ret = 0;
        DwHttpClient client = new DwHttpClient();

        ret = client.initialize(HTTP_URL);
        if (ret == 0)
            ret = client.authenticate(USER, PASSWORD);

        if (ret == 0) {

            DwOpenSessionInfo session = new DwOpenSessionInfo();
            client.getWorker().Session().orgSwitch("ROKEHA", session);

            client.getWorker().Session().info(session);

            if (session.isSuccess()) {
                client.getWorker().ThingDef().update(THING_KEY/*"test_alarm_publish"*/, null, true, true
                        , null, getAlarms(), null, null, null, session);
//                client.getWorker().ThingDef().delete(/*THING_KEY*/"test_alarm_publish", getAlarmsDel(),null,null
//                        ,null, null,session);

                System.out.println(session.getJsonResponse());
            }
        } else {
            System.out.println("Failed To Connect");
        }
    }

    private static DwOpenThingDefProperty[] getProp() {

        ArrayList<DwOpenThingDefProperty> props = new ArrayList<>();

        DwOpenThingDefProperty prop = new DwOpenThingDefProperty();
        prop.setKey("test");
        prop.setName("Test");
        prop.setUnit("%");

        props.add(prop);


        return props.toArray(new DwOpenThingDefProperty[0]);
    }

    private static DwOpenAlarmParam[] getPropAlarms() {


        ArrayList<DwOpenAlarmParam> alarms = new ArrayList<>();

        for (NU_Sensors sensors : NU_Sensors.values()) {


            DwOpenAlarmParam alarm_cpu_usage = new DwOpenAlarmParam();
            final String name = String.format("%s_%s", UNIT, sensors.name());
            alarm_cpu_usage.setKey(name.toLowerCase());
            alarm_cpu_usage.setName(name);

            DwOpenAlarmParam.AlarmState OK = new DwOpenAlarmParam.AlarmState();
            OK.setName("OK");
            OK.setColor("#00FF22");

            DwOpenAlarmParam.AlarmState Warning = new DwOpenAlarmParam.AlarmState();
            Warning.setName("Warning");
            Warning.setColor("#E8F25E");

            DwOpenAlarmParam.AlarmState Error = new DwOpenAlarmParam.AlarmState();
            Error.setName("Error");
            Error.setColor("#253FE6");

            DwOpenAlarmParam.AlarmState Error_plus = new DwOpenAlarmParam.AlarmState();
            Error_plus.setName("Error plus");
            Error_plus.setColor("#E82121");

            DwOpenAlarmParam.AlarmState Error_minus = new DwOpenAlarmParam.AlarmState();
            Error_minus.setName("Error minus");
            Error_minus.setColor("#DB5F39");

            DwOpenAlarmParam.AlarmState Warning_minus = new DwOpenAlarmParam.AlarmState();
            Warning_minus.setName("Warning minus");
            Warning_minus.setColor("#bcc449ff");

            DwOpenAlarmParam.AlarmState Warning_plus = new DwOpenAlarmParam.AlarmState();
            Warning_plus.setName("Warning plus");
            Warning_plus.setColor("#e9f817ff");

            ArrayList<DwOpenAlarmParam.AlarmState> states = new ArrayList<>();
            states.add(OK);//first is 0
            states.add(Warning);//first is 0
            states.add(Error);//first is 0
            states.add(Error_plus);//first is 0
            states.add(Error_minus);//first is 0
            states.add(Warning_plus);//first is 0
            states.add(Warning_minus);

            alarm_cpu_usage.setStates(states);

            alarms.add(alarm_cpu_usage);
        }
        return alarms.toArray(new DwOpenAlarmParam[0]);
    }


    private static DwOpenAlarmParam[] getAlarms() {


        ArrayList<DwOpenAlarmParam> alarms = new ArrayList<>();

        for (NU_Sensors sensors : NU_Sensors.values()) {

            DwOpenAlarmParam alarm_cpu_usage = new DwOpenAlarmParam();
            final String name = String.format("%s_%s", UNIT, sensors.name());
            alarm_cpu_usage.setKey(name.toLowerCase());
            alarm_cpu_usage.setName(name);

            DwOpenAlarmParam.AlarmState stateOn = new DwOpenAlarmParam.AlarmState();
            stateOn.setName("ON");
            stateOn.setColor("#00FF22");

            DwOpenAlarmParam.AlarmState stateOff = new DwOpenAlarmParam.AlarmState();
            stateOff.setName("OFF");
            stateOff.setColor("#FF0000");


            ArrayList<DwOpenAlarmParam.AlarmState> states = new ArrayList<>();
            states.add(stateOff);//first is 0
            states.add(stateOn);
            alarm_cpu_usage.setStates(states);

            alarms.add(alarm_cpu_usage);
        }
        return alarms.toArray(new DwOpenAlarmParam[0]);
    }

    private static String[] getAlarmsDel() {


        ArrayList<String> alarms = new ArrayList<>();

        for (NU_Sensors sensors : NU_Sensors.values()) {


            final String name = String.format("%s_%s", UNIT, sensors.name());

            alarms.add(name.toLowerCase());
        }
        return alarms.toArray(new String[0]);
    }

}
