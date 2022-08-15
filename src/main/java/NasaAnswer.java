import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;

public class NasaAnswer {
    String copyright;
    String date;
    String explanation;
    URI hdurl;
    String mediaType;
    String serviceVersion;
    String title;
    URI url;

    public NasaAnswer(
            @JsonProperty("copyright") String copyright,
            @JsonProperty("date") String date,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("hdurl") URI hdurl,
            @JsonProperty("media_type") String mediaType,
            @JsonProperty("service_version") String serviceVersion,
            @JsonProperty("title") String title,
            @JsonProperty("url") URI url
    ) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.mediaType = mediaType;
        this.serviceVersion = serviceVersion;
        this.title = title;
        this.url = url;
    }


    public URI getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "NasaAnswer:" +
                "\ncopyright = " + copyright +
                "\ndate = " + date +
                "\nexplanation = " + explanation +
                "\nhdurl = " + hdurl +
                "\nmediaType = " + mediaType +
                "\nserviceVersion = " + serviceVersion +
                "\ntitle = " + title +
                "\nurl = " + url +
                '\n';
    }
}
