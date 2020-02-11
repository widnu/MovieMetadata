package com.imdb.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.imdb.model.Crew;
import com.imdb.model.Movie;

/**
 * A CSV reader class
 * 
 * @author widnu
 *
 */
public class CSVReader {

	/**
	 * Read the csv file and map them to Movie object
	 * 
	 * @param filename
	 * @param movieList
	 */
	public void read(String filename, List<Movie> movieList) {

		InputStream res = CSVReader.class.getResourceAsStream("/" + filename);
		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new InputStreamReader(res))) {

			int lineCnt = 0;

			while ((line = br.readLine()) != null) {
				// skip the first line
				if (lineCnt == 0) {
					lineCnt++;
					continue;
				}

				// use comma as separator
				String[] fields = line.split(cvsSplitBy);

				movieList.add(mapMovieFields(fields));
				lineCnt++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Map array to movie object
	 * 
	 * @param fields
	 * @return movie with the related crew objects
	 */
	private Movie mapMovieFields(String[] fields) {
		Movie movie = new Movie();

		movie.setDuration(new Double(fields[3]));
		movie.setGross(new Double(fields[8]));
		movie.setTitle(fields[11]);
		movie.setVotedUser(Integer.parseInt(fields[12]));
		movie.setCastTotalFacebookLike(Integer.parseInt(fields[13]));

		movie.setFaceNumberInPoster(Integer.parseInt(fields[15]));
		movie.setImdbLink(fields[17]);
		movie.setReviewedUser(Integer.parseInt(fields[18]));
		movie.setLanguage(fields[19]);
		movie.setCountry(fields[20]);

		movie.setContentRating(fields[21]);
		movie.setBudget(new Double(fields[22]));
		movie.setYear(Integer.parseInt(fields[23]));
		movie.setImdbScore(Double.parseDouble(fields[25]));
		movie.setAspectRatio(Double.parseDouble(fields[26]));

		movie.setMovieFacebookLike(Integer.parseInt(fields[27]));

		// director
		movie.setDirector(new Crew(fields[1], Integer.valueOf(fields[4])));
		
		// actor 1 - 3
		movie.addCrewList(new Crew(fields[10], Integer.valueOf(fields[7])));
		movie.addCrewList(new Crew(fields[6], Integer.valueOf(fields[24])));
		movie.addCrewList(new Crew(fields[14], Integer.valueOf(fields[5])));

		if (fields[9] != null) {
			movie.setGenres(fields[9].split("[|]"));
		}

		if (fields[16] != null) {
			movie.setPlotKeywords(fields[16].split("[|]"));
		}

		return movie;
	}
}
