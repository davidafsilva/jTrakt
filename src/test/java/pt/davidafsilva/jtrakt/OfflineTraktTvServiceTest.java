package pt.davidafsilva.jtrakt;

import pt.davidafsilva.jtrakt.client.ClientBuilder;

public class OfflineTraktTvServiceTest extends TraktTvServiceTest {

	@Override
	TraktTvService setupService() {
		return TraktTestServiceFactory.INSTANCE.createTvService(
				new ClientBuilder()
					// search show
					.whenRequest("/search/shows.json/apiKey")
                    .reply("[{\"title\":\"Silicon Valley\",\"year\":2014," +
                          "\"url\":\"http://trakt.tv/show/silicon-valley\",\"first_aired\":1396767600," +
                          "\"country\":\"United States\",\"overview\":\"In the high-tech gold rush of " +
                          "modern Silicon Valley, the people most qualified to succeed are the least " +
                          "capable of handling success. \\n\\nA comedy partially inspired by Mike " +
                          "Judge's own experiences as a Silicon Valley engineer in the late 1980s. \"," +
                          "\"runtime\":30,\"network\":\"HBO\",\"air_day\":\"Sunday\"," +
                          "\"air_time\":\"10:00pm\",\"certification\":\"TV-14\"," +
                          "\"imdb_id\":\"tt2575988\",\"tvdb_id\":277165,\"tvrage_id\":33759," +
                          "\"ended\":false,\"images\":{\"poster\":\"http://slurm.trakt" +
                          ".us/images/posters/23332.14.jpg\",\"fanart\":\"http://slurm.trakt" +
                          ".us/images/fanart/23332.14.jpg\",\"banner\":\"http://slurm.trakt" +
                          ".us/images/banners/23332.14.jpg\"},\"ratings\":{\"percentage\":81," +
                          "\"votes\":541,\"loved\":508,\"hated\":33},\"genres\":[\"Comedy\", \"Comedy\"]," +
                          "\"seasons\":[{\"season\":1,\"episodes\":[{\"season\":1,\"number\":8}," +
                          "{\"season\":1,\"number\":7},{\"season\":1,\"number\":6},{\"season\":1," +
                          "\"number\":5},{\"season\":1,\"number\":4},{\"season\":1,\"number\":3}," +
                          "{\"season\":1,\"number\":2},{\"season\":1,\"number\":1}]},{\"season\":0," +
                          "\"episodes\":[{\"season\":0,\"number\":1}]}]}]")
                    // get genres
					.whenRequest("/genres/shows.json/apiKey")
					.reply("[{\"name\":\"Action\",\"slug\":\"action\"},{\"name\":\"Adventure\"," +
					       "\"slug\":\"adventure\"},{\"name\":\"Animation\",\"slug\":\"animation\"}," +
					       "{\"name\":\"Children\",\"slug\":\"children\"},{\"name\":\"Comedy\",\"slug\":\"comedy\"}," +
					       "{\"name\":\"Documentary\",\"slug\":\"documentary\"},{\"name\":\"Drama\"," +
					       "\"slug\":\"drama\"},{\"name\":\"Fantasy\",\"slug\":\"fantasy\"},{\"name\":\"Game Show\"," +
					       "\"slug\":\"game-show\"},{\"name\":\"Home and Garden\",\"slug\":\"home-and-garden\"}," +
					       "{\"name\":\"Mini Series\",\"slug\":\"mini-series\"},{\"name\":\"News\",\"slug\":\"news\"}," +
					       "{\"name\":\"No Genre\",\"slug\":\"none\"},{\"name\":\"Reality\",\"slug\":\"reality\"}," +
					       "{\"name\":\"Science Fiction\",\"slug\":\"science-fiction\"},{\"name\":\"Soap\"," +
					       "\"slug\":\"soap\"},{\"name\":\"Special Interest\",\"slug\":\"special-interest\"}," +
					       "{\"name\":\"Sport\",\"slug\":\"sport\"},{\"name\":\"Talk Show\",\"slug\":\"talk-show\"}," +
					       "{\"name\":\"Western\",\"slug\":\"western\"}]")
					// show summary
					.whenRequest("/show/summary.json/apiKey/277165")
					.reply("{\"title\":\"Silicon Valley\",\"year\":2014,\"url\":\"http://trakt.tv/show/silicon-valley\"," +
					       "\"first_aired\":1396846800,\"first_aired_iso\":\"2014-04-06T22:00:00-04:00\",\"first_aired_utc\":" +
					       "1396836000,\"country\":\"United States\",\"overview\":\"In the high-tech gold rush of modern " +
					       "Silicon Valley, the people most qualified to succeed are the least capable of handling success. " +
					       "\\n\\nA comedy partially inspired by Mike Judge's own experiences as a Silicon Valley engineer in " +
					       "the late 1980s. \",\"runtime\":30,\"status\":\"Continuing\",\"network\":\"HBO\",\"air_day\":\"Sunday\"" +
					       ",\"air_day_utc\":\"Sunday\",\"air_time\":\"10:00pm\",\"air_time_utc\":\"7:00pm\",\"certification\":" +
					       "\"TV-14\",\"imdb_id\":\"tt2575988\",\"tvdb_id\":277165,\"tvrage_id\":33759,\"last_updated\":1402018005" +
					       ",\"poster\":\"http://slurm.trakt.us/images/posters/23332.16.jpg\",\"images\":{\"poster\":" +
					       "\"http://slurm.trakt.us/images/posters/23332.16.jpg\",\"fanart\":\"http://slurm.trakt.us/images" +
					       "/fanart/23332.16.jpg\",\"banner\":\"http://slurm.trakt.us/images/banners/23332.16.jpg\"}," +
					       "\"top_watchers\":[{\"plays\":44,\"username\":\"phillipuniverse\",\"protected\":false," +
					       "\"full_name\":null,\"gender\":null,\"age\":\"\",\"location\":null,\"about\":null,\"joined\":" +
					       "0,\"avatar\":\"http://slurm.trakt.us/images/avatar-large.jpg\",\"url\":\"http://trakt.tv/user" +
					       "/phillipuniverse\"},{\"plays\":23,\"username\":\"mtv20\",\"protected\":false,\"full_name\":null" +
					       ",\"gender\":null,\"age\":\"\",\"location\":null,\"about\":null,\"joined\":0,\"avatar\":\"http:" +
					       "//slurm.trakt.us/images/avatar-large.jpg\",\"url\":\"http://trakt.tv/user/mtv20\"},{\"plays\":" +
					       "22,\"username\":\"fyrabanks\",\"protected\":false,\"full_name\":null,\"gender\":null,\"age\":" +
					       "\"\",\"location\":null,\"about\":null,\"joined\":0,\"avatar\":\"http://slurm.trakt.us/images/" +
					       "avatar-large.jpg\",\"url\":\"http://trakt.tv/user/fyrabanks\"},{\"plays\":22,\"username\":" +
					       "\"roboyle3\",\"protected\":false,\"full_name\":null,\"gender\":null,\"age\":\"\",\"location\":" +
					       "null,\"about\":null,\"joined\":0,\"avatar\":\"http://slurm.trakt.us/images/avatar-large.jpg\"," +
					       "\"url\":\"http://trakt.tv/user/roboyle3\"},{\"plays\":21,\"username\":\"PoohBear666\",\"protected\"" +
					       ":false,\"full_name\":null,\"gender\":null,\"age\":\"\",\"location\":null,\"about\":null," +
					       "\"joined\":0,\"avatar\":\"http://slurm.trakt.us/images/avatar-large.jpg\"," +
					       "\"url\":\"http://trakt.tv/user/PoohBear666\"},{\"plays\":19,\"username\":\"Vaelek\"," +
					       "\"protected\":false,\"full_name\":null,\"gender\":null,\"age\":\"\",\"location\":null," +
					       "\"about\":null,\"joined\":0,\"avatar\":\"http://slurm.trakt.us/images/avatar-large.jpg\"," +
					       "\"url\":\"http://trakt.tv/user/Vaelek\"},{\"plays\":19,\"username\":\"blogloudly\"," +
					       "\"protected\":false,\"full_name\":null,\"gender\":null,\"age\":\"\",\"location\":null," +
					       "\"about\":null,\"joined\":0,\"avatar\":\"http://slurm.trakt.us/images/avatar-large.jpg\"," +
					       "\"url\":\"http://trakt.tv/user/blogloudly\"},{\"plays\":18,\"username\":\"Maaniac\"," +
					       "\"protected\":false,\"full_name\":null,\"gender\":null,\"age\":\"\",\"location\":null," +
					       "\"about\":null,\"joined\":0,\"avatar\":\"http://slurm.trakt.us/images/avatar-large.jpg\"," +
					       "\"url\":\"http://trakt.tv/user/Maaniac\"},{\"plays\":18,\"username\":\"heartbraden\"," +
					       "\"protected\":false,\"full_name\":null,\"gender\":null,\"age\":\"\",\"location\":null," +
					       "\"about\":null,\"joined\":0,\"avatar\":\"http://slurm.trakt.us/images/avatars/63619.2" +
					       ".jpg\",\"url\":\"http://trakt.tv/user/heartbraden\"},{\"plays\":18," +
					       "\"username\":\"lowinfinity\",\"protected\":false,\"full_name\":null,\"gender\":null," +
					       "\"age\":\"\",\"location\":null,\"about\":null,\"joined\":0,\"avatar\":\"http://slurm.trakt" +
					       ".us/images/avatar-large.jpg\",\"url\":\"http://trakt.tv/user/lowinfinity\"},{\"plays\":17," +
					       "\"username\":\"suavelizard\",\"protected\":false,\"full_name\":null,\"gender\":null," +
					       "\"age\":\"\",\"location\":null,\"about\":null,\"joined\":0,\"avatar\":\"http://slurm.trakt" +
					       ".us/images/avatar-large.jpg\",\"url\":\"http://trakt.tv/user/suavelizard\"},{\"plays\":16," +
					       "\"username\":\"triryche\",\"protected\":false,\"full_name\":null,\"gender\":null," +
					       "\"age\":\"\",\"location\":null,\"about\":null,\"joined\":0,\"avatar\":\"http://slurm.trakt" +
					       ".us/images/avatars/8344.4.jpg\",\"url\":\"http://trakt.tv/user/triryche\"},{\"plays\":16," +
					       "\"username\":\"steffz\",\"protected\":false,\"full_name\":null,\"gender\":null," +
					       "\"age\":\"\",\"location\":null,\"about\":null,\"joined\":0,\"avatar\":\"http://slurm.trakt" +
					       ".us/images/avatar-large.jpg\",\"url\":\"http://trakt.tv/user/steffz\"},{\"plays\":16," +
					       "\"username\":\"atomjack\",\"protected\":false,\"full_name\":null,\"gender\":null," +
					       "\"age\":\"\",\"location\":null,\"about\":null,\"joined\":0,\"avatar\":\"http://slurm.trakt" +
					       ".us/images/avatars/235434.1.jpg\",\"url\":\"http://trakt.tv/user/atomjack\"}]," +
					       "\"top_episodes\":[{\"plays\":3464,\"season\":1,\"number\":1," +
					       "\"title\":\"Minimum Viable Product\",\"url\":\"http://trakt" +
					       ".tv/show/silicon-valley/season/1/episode/1\",\"first_aired\":1396846800," +
					       "\"first_aired_iso\":\"2014-04-06T22:00:00-04:00\",\"first_aired_utc\":1396836000}," +
					       "{\"plays\":3052,\"season\":1,\"number\":2,\"title\":\"The Cap Table\"," +
					       "\"url\":\"http://trakt.tv/show/silicon-valley/season/1/episode/2\"," +
					       "\"first_aired\":1397451600,\"first_aired_iso\":\"2014-04-13T22:00:00-04:00\"," +
					       "\"first_aired_utc\":1397440800},{\"plays\":2799,\"season\":1,\"number\":3," +
					       "\"title\":\"Articles of Incorporation\",\"url\":\"http://trakt" +
					       ".tv/show/silicon-valley/season/1/episode/3\",\"first_aired\":1398056400," +
					       "\"first_aired_iso\":\"2014-04-20T22:00:00-04:00\",\"first_aired_utc\":1398045600}," +
					       "{\"plays\":2651,\"season\":1,\"number\":4,\"title\":\"Fiduciary Duties\"," +
					       "\"url\":\"http://trakt.tv/show/silicon-valley/season/1/episode/4\"," +
					       "\"first_aired\":1398661200,\"first_aired_iso\":\"2014-04-27T22:00:00-04:00\"," +
					       "\"first_aired_utc\":1398650400},{\"plays\":2557,\"season\":1,\"number\":5," +
					       "\"title\":\"Signaling Risk\",\"url\":\"http://trakt" +
					       ".tv/show/silicon-valley/season/1/episode/5\",\"first_aired\":1399266000," +
					       "\"first_aired_iso\":\"2014-05-04T22:00:00-04:00\",\"first_aired_utc\":1399255200}," +
					       "{\"plays\":2416,\"season\":1,\"number\":7,\"title\":\"Proof of Concept\"," +
					       "\"url\":\"http://trakt.tv/show/silicon-valley/season/1/episode/7\"," +
					       "\"first_aired\":1400475600,\"first_aired_iso\":\"2014-05-18T22:00:00-04:00\"," +
					       "\"first_aired_utc\":1400464800},{\"plays\":2358,\"season\":1,\"number\":6," +
					       "\"title\":\"Third Party Insourcing\",\"url\":\"http://trakt" +
					       ".tv/show/silicon-valley/season/1/episode/6\",\"first_aired\":1399870800," +
					       "\"first_aired_iso\":\"2014-05-11T22:00:00-04:00\",\"first_aired_utc\":1399860000}," +
					       "{\"plays\":1958,\"season\":1,\"number\":8,\"title\":\"Optimal Tip-To-Tip Efficiency\"," +
					       "\"url\":\"http://trakt.tv/show/silicon-valley/season/1/episode/8\"," +
					       "\"first_aired\":1401685200,\"first_aired_iso\":\"2014-06-01T22:00:00-04:00\"," +
					       "\"first_aired_utc\":1401674400},{\"plays\":40,\"season\":0,\"number\":1," +
					       "\"title\":\"Making of Silicon Valley\",\"url\":\"http://trakt" +
					       ".tv/show/silicon-valley/specials/episode/1\",\"first_aired\":1396846800," +
					       "\"first_aired_iso\":\"2014-04-06T22:00:00-04:00\",\"first_aired_utc\":1396836000}]," +
					       "\"ratings\":{\"percentage\":82,\"votes\":652,\"loved\":616,\"hated\":36}," +
					       "\"stats\":{\"watchers\":4443,\"plays\":21263,\"scrobbles\":18794," +
					       "\"scrobbles_unique\":3774,\"checkins\":2469,\"checkins_unique\":734,\"collection\":0," +
					       "\"collection_unique\":0},\"people\":{\"actors\":[{\"name\":\"Thomas Middleditch\"," +
					       "\"character\":\"Richard\",\"images\":{\"headshot\":\"http://slurm.trakt" +
					       ".us/images/avatar-large.jpg\"}},{\"name\":\"T.J. Miller\",\"character\":\"Erlich\"," +
					       "\"images\":{\"headshot\":\"http://slurm.trakt.us/images/avatar-large.jpg\"}}," +
					       "{\"name\":\"Kumail Nanjiani\",\"character\":\"Dinesh\"," +
					       "\"images\":{\"headshot\":\"http://slurm.trakt.us/images/avatar-large.jpg\"}}," +
					       "{\"name\":\"Zach Woods\",\"character\":\"Jared\",\"images\":{\"headshot\":\"http://slurm" +
					       ".trakt.us/images/avatar-large.jpg\"}},{\"name\":\"Amanda Crew\",\"character\":\"Monica\"," +
					       "\"images\":{\"headshot\":\"http://slurm.trakt.us/images/people/12646.jpg\"}}," +
					       "{\"name\":\"Christopher Evan Welch\",\"character\":\"Peter Gregory\"," +
					       "\"images\":{\"headshot\":\"http://slurm.trakt.us/images/avatar-large.jpg\"}}," +
					       "{\"name\":\"Josh Brener\",\"character\":\"Big Head\"," +
					       "\"images\":{\"headshot\":\"http://slurm.trakt.us/images/avatar-large.jpg\"}}," +
					       "{\"name\":\"Martin Starr\",\"character\":\"Gilfoyle\"," +
					       "\"images\":{\"headshot\":\"http://slurm.trakt.us/images/avatar-large.jpg\"}}," +
					       "{\"name\":\"Matt Ross\",\"character\":\"Gavin Belson\"," +
					       "\"images\":{\"headshot\":\"http://slurm.trakt.us/images/avatar-large.jpg\"}}]}," +
					       "\"genres\":[\"Comedy\"]}")
					// build
                    .build());
	}

}
