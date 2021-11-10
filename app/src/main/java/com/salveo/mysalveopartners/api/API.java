package com.salveo.mysalveopartners.api;


import com.salveo.mysalveopartners.responsepojo.AddressResultsResponse;
import com.salveo.mysalveopartners.responsepojo.GetAddressResultResponse;
import com.salveo.mysalveopartners.responsepojo.PlacesResultsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {


    // https://maps.googleapis.com/maps/api/place/autocomplete/json?input=iluppaiyur&key=AIzaSyCVeEuZcqrs9phnrk1aNSpiJ57hb-V8hhE

    String BASE_URL = "https://maps.googleapis.com";
   // String key = "AIzaSyCVeEuZcqrs9phnrk1aNSpiJ57hb-V8hhE";
   public static String MAP_KEY = "AIzaSyAlvAK3lZepIaApTDbDZUNfO0dBmuP6h4A";

    @GET("/maps/api/place/autocomplete/json")
    Call<PlacesResultsResponse> getCityResults(@Query("input") String input, @Query("key") String key);



    @GET("/maps/api/geocode/json")
    Call<AddressResultsResponse> getaddressResults(@Query("address") String input, @Query("key") String key);


    @GET("/maps/api/geocode/json")
    Call<GetAddressResultResponse> getAddressResultResponseCall(@Query("latlng") String input, @Query("key") String key);
}