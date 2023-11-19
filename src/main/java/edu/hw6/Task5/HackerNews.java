package edu.hw6.Task5;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings({"HideUtilityClassConstructor", "MagicNumber", "RegexpSinglelineJava"})
public class HackerNews {

    private static final String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String ITEM_URL_TEMPLATE = "https://hacker-news.firebaseio.com/v0/item/%d.json";

    public static long[] hackerNewsTopStories() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(TOP_STORIES_URL))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String[] storyIds = response.body().replaceAll("[\\[\\]\"]", "").split(",");
                long[] result = new long[storyIds.length];

                for (int i = 0; i < storyIds.length; i++) {
                    result[i] = Long.parseLong(storyIds[i].trim());
                }

                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new long[0];
    }

    public static String news(long id) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(String.format(ITEM_URL_TEMPLATE, id)))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String json = response.body();
                Pattern pattern = Pattern.compile("\"title\":\"(.*?)\"");
                Matcher matcher = pattern.matcher(json);

                if (matcher.find()) {
                    return matcher.group(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return "";
    }
}

