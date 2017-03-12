package krast.overwatchapi;

/**
 * Created by Turchyn on 27.12.2016.
 */

public class DataCore {
    private static DataCore instance;
    public Example data;

    private DataCore(){

    }

    private DataCore(Example data){
        this.data = data;
    }

    public static DataCore getInstance(){
        return instance;
    }

    public static void setInstance(Example data){
        instance = new DataCore(data);
    }


    public static void clearInstance(){
        instance = null;
    }
}
