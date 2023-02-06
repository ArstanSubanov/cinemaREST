package com.arstansubanov.cinematica.services.impl;

import com.arstansubanov.cinematica.dto.MovieDTO;
import com.arstansubanov.cinematica.dto.MovieSessionDTO;
import com.arstansubanov.cinematica.mapper.MovieMapper;
import com.arstansubanov.cinematica.models.Movie;
import com.arstansubanov.cinematica.repository.MovieRepository;
import com.arstansubanov.cinematica.requests.MovieByIdAndDateRequest;
import com.arstansubanov.cinematica.responses.*;
import com.arstansubanov.cinematica.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final MovieSessionService movieSessionService;
    private final CinemaService cinemaService;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper, @Lazy MovieSessionService movieSessionService, @Lazy CinemaService cinemaService) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.movieSessionService = movieSessionService;
        this.cinemaService = cinemaService;
    }


    @Override
    public List<MovieDTO> findAll() {
        return movieRepository.findAll().stream().map(movieMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public MovieDTO findById(int id) {
        return movieMapper.convertToDto(getMovieById(id));
    }

    @Override
    @Transactional
    public void save(MovieDTO movieDTO) {
        movieRepository.save(movieMapper.convertToModel(movieDTO));
    }

    @Override
    @Transactional
    public void delete(int id) {
        movieRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(int id, MovieDTO movieDTO) {
        Movie movieToUpdate = getMovieById(id);
        Movie movie = movieMapper.convertToModel(movieDTO);
        movie.setId(id);
        movie.setCreatedAt(movieToUpdate.getCreatedAt());
        movieRepository.save(movie);

    }

    @Override
    public List<MovieDTO> getAllActiveMovies() {
        return movieRepository.findMovieByActiveTrue().stream().map(movieMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public MovieResponse getMovieById(MovieByIdAndDateRequest movieByIdAndDateRequest) {
        List<MovieSessionDTO> movieSessionDTOS = movieSessionService.getMovieSessionByMovie(movieByIdAndDateRequest.getMovieId());
        System.out.println(movieSessionDTOS);
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setMovie(movieMapper.convertToDto(getMovieById(movieByIdAndDateRequest.getMovieId())));
//        List<CinemaResponse> cinemaResponses = new ArrayList<>();
//
//        movieSessionDTOS.forEach(movieSessionDTO -> {
//            CinemaResponse cinemaResponse = new CinemaResponse();
//            cinemaResponse.setName(movieSessionDTO.getHall().getCinema().getName());
//            List<HallDTO> hallDTOS = hallService.findHallDtoByCinema(movieSessionDTO.getHall().getCinema().getId())
//                    .stream()
//                    .filter(hallDTO -> hallDTO.getId()==movieSessionDTO.getHall().getId())
//                    .collect(Collectors.toList());
//            List<HallResponse> hallResponses = new ArrayList<>();
//            hallDTOS.forEach(hallDTO -> {
//                HallResponse hallResponse = new HallResponse();
//                hallResponse.setName(hallDTO.getName());
//                List<MovieSessionResponse> movieSessionResponses = new ArrayList<>();
//                MovieSessionResponse movieSessionResponse = new MovieSessionResponse();
//                movieSessionResponse.setDate(movieSessionDTO.getDate());
//                movieSessionResponse.setTime(movieSessionDTO.getTime());
//                List<PriceResponse> priceResponses = priceService.getPriceByMovieSession(movieSessionDTO);
//                movieSessionResponse.setPrices(priceResponses);
//                movieSessionResponses.add(movieSessionResponse);
//                hallResponse.setMovieSessions(movieSessionResponses);
//                hallResponses.add(hallResponse);
//            });
//            cinemaResponse.setHalls(hallResponses);
//            cinemaResponses.add(cinemaResponse);
//        });


        movieResponse.setCinemas(cinemaService.getCinemaResponseList(movieSessionDTOS));
        return movieResponse;
    }

    private Movie getMovieById(int id){
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.orElse(null);
    }
}
