package com.songlify.constants;

public class URLs {

    public static class ALBUM {
        public static final String BASE_URL = "/api/v1/albums";
        public static final String GET_BY_SINGER = "/singer/{singerId}";
        public static final String DELETE_SINGER_ALBUM = "singer/{singerId}/album/{albumId}";
    }

    public static class SONG {
        public static final String BASE_URL = "/api/v1/songs";
        public static final String GET_BY_SINGER = "/singer/{singerId}";
    }

    public static class SINGER {
        public static final String BASE_URL = "/api/v1/singers";
        public static final String GET_BY_ID = "/{singerId}";
    }
}
