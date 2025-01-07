package ace.charitan.media.internal.media.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;

public class MediaEnum {

    @AllArgsConstructor
    public static enum MediaType {
        VIDEO("VIDEO"),
        IMAGE("IMAGE");

        private String value;

        @JsonValue
        public String getValue() {
            return value;
        }

        @JsonCreator
        public static MediaType fromValue(String value) {
            for (MediaType media : values()) {
                String currentMedia = media.getValue();
                if (currentMedia.equals(value)) {
                    return media;
                }
            }

            // Return a response entity with a 400 Bad Request media
            throw new IllegalArgumentException("Invalid value for MediaType Enum: " + value);
        }
    }
}
