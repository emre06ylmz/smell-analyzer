package com.eylmz.master.sonar.client.util;

import org.eclipse.egit.github.core.Comment;
import org.eclipse.egit.github.core.Label;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class GithubParserUtil {

    public static boolean checkIssueType(List<Label> labels, String key, String value) {
        String labelValue = key.toLowerCase() + ": " + value.toLowerCase();
        for (Label label : labels) {
            if (label.getName().toLowerCase().equals(labelValue)) {
                return true;
            }
        }
        return false;
    }

    public static void getCloserCommitOfIssue(List<Comment> comments) {
        for (Comment comment : comments) {
            if (comment.getBody().contains("closed this in")) {
                comment.getBody();
            }
        }
    }

    public static String getRelatedCommit(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[data-hovercard-type=commit]");

            for (Element link : links) {
                String href = link.attr("href");
                URI uri = new URI(href);
                String path = uri.getPath();
                String commitId = path.substring(path.lastIndexOf('/') + 1);
                System.out.println(commitId);
                return commitId;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

}
