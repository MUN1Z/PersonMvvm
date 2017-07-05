package net.felipemuniz.personmvvm.shared.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Muniz on 05/07/2017.
 */
public class ConnectionDetector {

    private Context context;

    /**
     * This method is used to initialize the ConnectionDetector
     * @param context This is the context of activity
     */
    public ConnectionDetector(Context context){
        this.context = context;
    }

    /**
     * Method utilized for check the conection of internet
     * @return boolean Thi is boolean value for internet connection
     */
    public boolean isConnectingToInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}