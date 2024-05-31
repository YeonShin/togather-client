package com.project.togather.home;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostInfoItem {
    private int id;
    private String postThumbnailImageUrl;
    private String title;
    private String category;
    private long elapsedTime;
    private int maxPartyMemberNum;
    private int currentPartyMemberNum;
    private boolean likedState;
    private int likedCnt;
}