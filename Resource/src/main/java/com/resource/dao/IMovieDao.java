package com.resource.dao;

import com.resource.model.Movie;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IMovieDao {

    List<Movie> selectAllMovie(@Param("movieName")String movieName,@Param("pageIndex")int pageIndex,@Param("pageSize")int pageSize);

    int selectMaxMovieId();

    int selectMovieNum(@Param("movieName")String movieName);

    int insertMovie(@Param("movieId") int movieId,@Param("movieName") String movieName,@Param("movieType") String movieType,@Param("address") String address,
                    @Param("director")String director, @Param("movieYear")String movieYear, @Param("score")String score,
                    @Param("movieDescribe")String movieDescribe, @Param("picPath")String picPath, @Param("downloadPath")String downloadPath);


}
