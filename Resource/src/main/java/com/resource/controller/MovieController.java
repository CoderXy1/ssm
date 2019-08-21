package com.resource.controller;

import com.resource.jsoup.movieJsoup;
import com.resource.model.Movie;
import com.resource.service.IMovieService;
import com.resource.service.IPicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @Resource
    private IMovieService movieService;

    @RequestMapping("/selectMovieNum")
    @ResponseBody
    public int selectMovieNum(@RequestParam("movieName")String movieName) {
        return this.movieService.selectMovieNum(movieName);
    }

    //@RequestParam("movieName")String movieName, @RequestParam("movieType")String movieType, @RequestParam("address")String address,
    //@RequestParam("director")String director, @RequestParam("movieYear")String movieYear, @RequestParam("score")String score,
    //@RequestParam("movieDescribe")String movieDescribe, @RequestParam("picPath")String picPath, @RequestParam("downloadPath")String downloadPath
    @RequestMapping("/insertMovie")
    @ResponseBody
    public int insertMovie() throws Exception{

        String movieName,movieType,address,director,movieYear,score,movieDescribe,picPath,downloadPath;
        int movieId = selectMaxMovieId() + 1;
        List<Movie> list = movieJsoup.getText(21100 + movieId);
        for (int i=0;i < list.size();i++){
            movieId = selectMaxMovieId() + 1;
            movieName = list.get(i).getMovieName();
            movieType = list.get(i).getMovieType();
            address = list.get(i).getAddress();
            director = list.get(i).getDirector();
            movieYear = list.get(i).getMovieYear();
            score = list.get(i).getScore();
            movieDescribe = list.get(i).getMovieDescribe();
            picPath = list.get(i).getPicPath();
            downloadPath = list.get(i).getDownloadPath();
            this.movieService.insertMovie(movieId, movieName, movieType, address, director, movieYear, score, movieDescribe, picPath, downloadPath);
            Thread.sleep(1000);
        }

        return 1;
    }

    @RequestMapping("/selectMaxMovieId")
    @ResponseBody
    public int selectMaxMovieId(){

        return this.movieService.selectMaxMovieId();

    }

    @RequestMapping("/selectAllMovie")
    @ResponseBody
    public List<Movie> selectAllMovie(@RequestParam("movieName")String movieName,@RequestParam("pageIndex")int pageIndex,@RequestParam("pageSize")int pageSize){

        return this.movieService.selectAllMovie(movieName, pageIndex, pageSize);

    }

}
