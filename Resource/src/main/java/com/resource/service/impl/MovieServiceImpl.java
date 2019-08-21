package com.resource.service.impl;

import com.resource.dao.IMovieDao;
import com.resource.model.Movie;
import com.resource.service.IMovieService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service("movieService")
@Transactional
public class MovieServiceImpl implements IMovieService {

    @Resource
    private IMovieDao movieDao;

    @Override
    public List<Movie> selectAllMovie(String movieName,int pageIndex,int pageSize) {
        return this.movieDao.selectAllMovie(movieName,pageIndex,pageSize);
    }

    @Override
    public int selectMovieNum(String movieName) {
        return this.movieDao.selectMovieNum(movieName);
    }

    @Override
    public int selectMaxMovieId() {
        return this.movieDao.selectMaxMovieId();
    }

    @Override
    public int insertMovie(int movieId, String movieName, String movieType, String address, String director, String movieYear, String score, String movieDescribe, String picPath, String downloadPath) {
        return this.movieDao.insertMovie(movieId, movieName, movieType, address, director, movieYear, score, movieDescribe, picPath, downloadPath);
    }
}
