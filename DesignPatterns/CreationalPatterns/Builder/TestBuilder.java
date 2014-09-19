package Builder;

import java.util.*;

class WorkShop {
    //force the order of building process
    public void construct(HouseBuilder hb) {
        hb.buildFoundation();
        hb.buildFrame();
        hb.buildExterior();
        hb.buildInterior();
    }
}

//set steps for building a house
abstract class HouseBuilder {
    protected House house = new House();
    
    protected String showProgress() {
        return house.toString();
    }

    abstract public void buildFoundation();
    abstract public void buildFrame();
    abstract public void buildExterior();
    abstract public void buildInterior();
}

class OneStoryHouse extends HouseBuilder {
    
    public OneStoryHouse(String features) {
        house.setType(this.getClass() + " " + features);
    }
    public void buildFoundation() {
        //doEngineering()
        //doExcavating()
        //doPlumbingHeatingElectricity()
        //doSewerWaterHookUp()
        //doFoundationInspection()
        house.setProgress("foundation is done");
    }

    public void buildFrame() {
        //doHeatingPlumbingRoof()
        //doElectricityRoute()
        //doDoorsWindows()
        //doFrameInspection()
        house.setProgress("frame is done");
    }

    public void buildExterior() {
        //doOverheadDoors()
        //doBrickWorks()
        //doSidingsoffitsGutters()
        //doDrivewayGarageFloor()
        //doDeckRail()
        //doLandScaping()
        house.setProgress("Exterior is done");
    }

    public void buildInterior() {
        //doAlarmPrewiring()
        //doBuiltinVacuum()
        //doInsulation()
        //doDryWall()
        //doPainting()
        //doLinoleum()
        //doCabinet()
        //doTileWork()
        //doLightFixtureBlinds()
        //doCleaning()
        //doInteriorInspection()
        house.setProgress("Interior is under going");
    } 
}

class TwoStoryHouse extends HouseBuilder {
  
    public TwoStoryHouse(String features) {
        house.setType(this.getClass() + " " + features);
    }
    public void buildFoundation() {
        //doEngineering()
        //doExcavating()
        //doPlumbingHeatingElectricity()
        //doSewerWaterHookUp()
        //doFoundationInspection()
        house.setProgress("foundation is done");
    }

    public void buildFrame() {
        //doHeatingPlumbingRoof()
        //doElectricityRoute()
        //doDoorsWindows()
        //doFrameInspection()
        house.setProgress("frame is under construction");
    }

    public void buildExterior() {
        //doOverheadDoors()
        //doBrickWorks()
        //doSidingsoffitsGutters()
        //doDrivewayGarageFloor()
        //doDeckRail()
        //doLandScaping()
        house.setProgress("Exterior is waiting to start");
    }

    public void buildInterior() {
        //doAlarmPrewiring()
        //doBuiltinVacuum()
        //doInsulation()
        //doDryWall()
        //doPainting()
        //doLinoleum()
        //doCabinet()
        //doTileWork()
        //doLightFixtureBlinds()
        //doCleaning()
        //doInteriorInspection()
        house.setProgress("Interior is not started yet");
    }
}

class House {
    private String type = null;
    private List features = new ArrayList();

    public House() {

    }

    public House(String type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setProgress(String s) {
        features.add(s);
    }

    public String toString() {
        StringBuffer ff = new StringBuffer();
        String t = type.substring(6);
        ff.append(t + "\n ");
        for (int i = 0; i < features.size(); i ++) {
             ff.append(features.get(i) + "\n ");
        }
        return ff.toString();
    }
}

public class TestBuilder  {
  
    public static void main(String[] args) {
       
       HouseBuilder one = new OneStoryHouse("2 bedrooms, 2.5 baths, 2-car garage, 1500 sqft");
       HouseBuilder two = new TwoStoryHouse("4 bedrooms, 4 baths, 3-car garage, 5000 sqft");

       WorkShop shop = new WorkShop();
       shop.construct(one);
       shop.construct(two);
    
   
       System.out.println("Check house building progress: \n");
       System.out.println(one.showProgress());
       System.out.println(two.showProgress());
   }
}