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
					// show seasons
					.whenRequest("/show/seasons.json/apiKey/277165")
					.reply("[{\"season\":1,\"episodes\":8,\"url\":\"http://trakt.tv/show/silicon-valley/season/1\"," +
					       "\"poster\":\"http://slurm.trakt.us/images/seasons/23332-1.16.jpg\"," +
					       "\"images\":{\"poster\":\"http://slurm.trakt.us/images/seasons/23332-1.16.jpg\"}}," +
					       "{\"season\":0,\"episodes\":1,\"url\":\"http://trakt.tv/show/silicon-valley/specials\"," +
					       "\"poster\":\"http://slurm.trakt.us/images/seasons/23332-0.16.jpg\"," +
					       "\"images\":{\"poster\":\"http://slurm.trakt.us/images/seasons/23332-0.16.jpg\"}}]")
					// show episodes
					.whenRequest("/show/season.json/apiKey/277165/1")
					.reply("[{\"season\":1,\"episode\":1,\"number\":1,\"tvdb_id\":4765079,\"title\":\"Minimum Viable " +
					       "Product\",\"overview\":\"Attending an elaborate launch party, Richard and his computer " +
					       "programmer friends - Big Head, Dinesh and Gilfoyle - dream of making it big. Instead, " +
					       "they're living in the communal Hacker Hostel owned by former programmer Erlich, who gets " +
					       "to claim ten percent of anything they invent there. When it becomes clear that Richard has" +
					       " developed a powerful compression algorithm for his website, Pied Piper, he finds himself " +
					       "courted by Gavin Belson, his egomaniacal corporate boss, who offers a $10 million buyout by " +
					       "his firm, Hooli. But Richard holds back when well-known investor Peter Gregory makes a " +
					       "counteroffer. \",\"first_aired\":1396846800,\"first_aired_iso\":\"2014-04-06T22:00:00-04:" +
					       "00\",\"first_aired_utc\":1396836000,\"url\":\"http://trakt.tv/show/silicon-valley/season/1/" +
					       "episode/1\",\"screen\":\"http://slurm.trakt.us/images/episodes/23332-1-1.16.jpg\",\"image" +
					       "s\":{\"screen\":\"http://slurm.trakt.us/images/episodes/23332-1-1.16.jpg\"},\"ratings\":{\"p" +
					       "ercentage\":79,\"votes\":1117,\"loved\":1074,\"hated\":43}},{\"season\":1,\"episode\":2,\"n" +
					       "umber\":2,\"tvdb_id\":4794504,\"title\":\"The Cap Table\",\"overview\":\"After a celebrator" +
					       "y party at the Hacker Hostel, Richard and Erlich learn that Peter Gregory won't pay up unti" +
					       "l they deliver a viable business plan that includes a slimmed-downed staff. A desperate Ric" +
					       "hard hires former Belson underling Jared, and they set about trying to trim the fat. While " +
					       "Gilfoyle and Dinesh prove essential, Big Head's place in the company is less certain.\",\"f" +
					       "irst_aired\":1397451600,\"first_aired_iso\":\"2014-04-13T22:00:00-04:00\",\"first_aired_utc" +
					       "\":1397440800,\"url\":\"http://trakt.tv/show/silicon-valley/season/1/episode/2\",\"screen" +
					       "\":\"http://slurm.trakt.us/images/episodes/23332-1-2.16.jpg\",\"images\":{\"screen\":\"htt" +
					       "p://slurm.trakt.us/images/episodes/23332-1-2.16.jpg\"},\"ratings\":{\"percentage\":80,\"vo" +
					       "tes\":955,\"loved\":929,\"hated\":26}},{\"season\":1,\"episode\":3,\"number\":3,\"tvdb_i" +
					       "d\":4794505,\"title\":\"Articles of Incorporation\",\"overview\":\"While Gavin Belson beg" +
					       "ins to hype Nucleus, a competing compression platform, Richard learns that the name Pied " +
					       "Piper is already registered to a sprinkler company, forcing him to negotiate. Meanwhile, " +
					       "Erlich goes on a vision quest for a new company name, and Peter Gregory proves elusive w" +
					       "hen one of his companies asks for money.\",\"first_aired\":1398056400,\"first_aired_iso\":" +
					       "\"2014-04-20T22:00:00-04:00\",\"first_aired_utc\":1398045600,\"url\":\"http://trakt.tv/show" +
					       "/silicon-valley/season/1/episode/3\",\"screen\":\"http://slurm.trakt.us/images/episodes/23" +
					       "332-1-3.16.jpg\",\"images\":{\"screen\":\"http://slurm.trakt.us/images/episodes/23332-1-3." +
					       "16.jpg\"},\"ratings\":{\"percentage\":81,\"votes\":878,\"loved\":867,\"hated\":11}},{\"se" +
					       "ason\":1,\"episode\":4,\"number\":4,\"tvdb_id\":4794506,\"title\":\"Fiduciary Duties\",\"ov" +
					       "erview\":\"At Peter's toga party, Richard drunkenly promises to make Erlich a board member" +
					       ", which he regrets the next morning. After being unassigned at Hooli, Big Head finds other" +
					       "s like him who have made careers out of doing nothing. Richard struggles to put Pied Pipe" +
					       "r's vision into words for a presentation without Erlich; later, he discovers an interestin" +
					       "g connection between Peter and Gavin Belson.\",\"first_aired\":1398661200,\"first_aired_i" +
					       "so\":\"2014-04-27T22:00:00-04:00\",\"first_aired_utc\":1398650400,\"url\":\"http://trakt." +
					       "tv/show/silicon-valley/season/1/episode/4\",\"screen\":\"http://slurm.trakt.us/images/epi" +
					       "sodes/23332-1-4.16.jpg\",\"images\":{\"screen\":\"http://slurm.trakt.us/images/episodes/2" +
					       "3332-1-4.16.jpg\"},\"ratings\":{\"percentage\":81,\"votes\":810,\"loved\":799,\"hated\":1" +
					       "1}},{\"season\":1,\"episode\":5,\"number\":5,\"tvdb_id\":4794507,\"title\":\"Signaling R" +
					       "isk\",\"overview\":\"Erlich taps a graffiti artist to design Pied Piper's logo, with pre" +
					       "dictably controversial results. Meanwhile, Richard learns he doesn't have much time to p" +
					       "repare for a live demonstration at TechCrunch Disrupt; Gavin Belson and Peter Gregory h" +
					       "ave an unexpected encounter; and Jared works on the company's efficiency. \",\"first_ai" +
					       "red\":1399266000,\"first_aired_iso\":\"2014-05-04T22:00:00-04:00\",\"first_aired_utc\":" +
					       "1399255200,\"url\":\"http://trakt.tv/show/silicon-valley/season/1/episode/5\",\"scre" +
					       "en\":\"http://slurm.trakt.us/images/episodes/23332-1-5.16.jpg\",\"images\":{\"scre" +
					       "en\":\"http://slurm.trakt.us/images/episodes/23332-1-5.16.jpg\"},\"ratings\":{\"perc" +
					       "entage\":83,\"votes\":774,\"loved\":771,\"hated\":3}},{\"season\":1,\"episode\":6,\"n" +
					       "umber\":6,\"tvdb_id\":4794508,\"title\":\"Third Party Insourcing\",\"overview\":\"Rich" +
					       "ard feels threatened when the team hires \\u201cThe Carver\\u201d a hacker with a notoriou" +
					       "s reputation, to help with Pied Piper\\u2019s cloud. Jared finds himself taken for a ride" +
					       " when he seeks out Peter Gregory\\u2019s signature. Erlich and Dinesh compete for the att" +
					       "ention of Tara, Gilfoyles visiting girlfriend. Later, Dinesh faces a sexual dilemma.\",\"" +
					       "first_aired\":1399870800,\"first_aired_iso\":\"2014-05-11T22:00:00-04:00\",\"first_aired_" +
					       "utc\":1399860000,\"url\":\"http://trakt.tv/show/silicon-valley/season/1/episode/6\",\"scr" +
					       "een\":\"http://slurm.trakt.us/images/episodes/23332-1-6.16.jpg\",\"images\":{\"screen\":\"h" +
					       "ttp://slurm.trakt.us/images/episodes/23332-1-6.16.jpg\"},\"ratings\":{\"percentage\":83,\"v" +
					       "otes\":727,\"loved\":720,\"hated\":7}},{\"season\":1,\"episode\":7,\"number\":7,\"tvdb_id\":4" +
					       "794509,\"title\":\"Proof of Concept\",\"overview\":\"At TechCrunch Disrupt, Richard fee" +
					       "ls the pressure to finish his demo, but finds himself distracted by a girl he dated brie" +
					       "fly, who\\u2019s now spreading rumors about him. Jared worries that Monica is taking his" +
					       " place in the company. Dinesh develops a crush on a girl at a neighboring booth. Erli" +
					       "ch\\u2019s scandalous past connection to one of the judges threatens Pied Piper\\u2019s " +
					       "chances.\",\"first_aired\":1400475600,\"first_aired_iso\":\"2014-05-18T22:00:00-04:00\",\"f" +
					       "irst_aired_utc\":1400464800,\"url\":\"http://trakt.tv/show/silicon-valley/season/1/episod" +
					       "e/7\",\"screen\":\"http://slurm.trakt.us/images/episodes/23332-1-7.16.jpg\",\"images\":{\"s" +
					       "creen\":\"http://slurm.trakt.us/images/episodes/23332-1-7.16.jpg\"},\"ratings\":{\"percenta" +
					       "ge\":85,\"votes\":753,\"loved\":750,\"hated\":3}},{\"season\":1,\"episode\":8,\"number\":8" +
					       ",\"tvdb_id\":4822932,\"title\":\"Optimal Tip-To-Tip Efficiency\",\"overview\":\"Poised to " +
					       "compete at TechCrunch Disrupt, the guys of Pied Piper become worried after an impressive p" +
					       "resentation by Gavin Belson. As Jared tries to pivot the company, Richard is inspired to " +
					       "make big changes at the last minute. \",\"first_aired\":1401685200,\"first_aired_iso\":\"20" +
					       "14-06-01T22:00:00-04:00\",\"first_aired_utc\":1401674400,\"url\":\"http://trakt.tv/show/si" +
					       "licon-valley/season/1/episode/8\",\"screen\":\"http://slurm.trakt.us/images/episodes/23332" +
					       "-1-8.16.jpg\",\"images\":{\"screen\":\"http://slurm.trakt.us/images/episodes/23332-1-8.16" +
					       ".jpg\"},\"ratings\":{\"percentage\":88,\"votes\":688,\"loved\":686,\"hated\":2}}]")
					// build
                    .build());
	}

}
