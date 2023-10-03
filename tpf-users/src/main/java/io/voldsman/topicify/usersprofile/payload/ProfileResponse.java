package io.voldsman.topicify.usersprofile.payload;

import lombok.Data;

import java.util.List;

@Data
public class ProfileResponse {

    private String userId;

    private String username;
    private String avatarImage;
    private String coverImage;
    private String bio;
    private List<Link> links;
    private boolean isResourceOwner;

    @Data
    public static class Link {
        private String name;
        private String url;
    }
}
