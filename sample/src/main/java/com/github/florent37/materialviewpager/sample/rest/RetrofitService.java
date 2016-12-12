package com.github.florent37.materialviewpager.sample.rest;

import com.github.florent37.materialviewpager.sample.model.FlickrResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by raian on 12/12/16.
 */

public interface RetrofitService {

    //https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=586c3aefd785c48e6c332f220f7bae13&per_page=20&format=json&nojsoncallback=1
    //https://api.flickr.com/
    // services/rest/?method=flickr.photos.getRecent
    // &api_key=586c3aefd785c48e6c332f220f7bae13
    // &per_page=20
    // &format=json
    // &nojsoncallback=1

    @GET("/services/rest/?method=flickr.photos.getRecent")
    Observable<FlickrResult> getPhoto(@Query("method") String method,
                                      @Query("api_key") String apiKey,
                                      @Query("per_page")String perPage,
                                      @Query("format") String format,
                                      @Query("nojsoncallback") String callback);

}
