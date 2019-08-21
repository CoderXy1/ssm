package com.resource.service;

import com.resource.model.Movie;

import java.util.List;

public interface IMovieService {

    List<Movie> selectAllMovie(String movieName,int pageIndex,int pageSize);

    int selectMovieNum(String movieName);

    int selectMaxMovieId();

    int insertMovie(int movieId, String movieName, String movieType, String address,String director, String movieYear, String score, String movieDescribe, String picPath, String downloadPath);

}
