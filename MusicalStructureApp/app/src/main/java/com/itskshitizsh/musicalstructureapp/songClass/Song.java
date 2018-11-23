package com.itskshitizsh.musicalstructureapp.songClass;

public class Song {
    private String songTitle;
    private String songArtist;
    private String songAlbum;
    private boolean favorite;
    private String totalPlayTime;

    public Song(String songTitle, String songArtist, String songAlbum, boolean favorite, String totalPlayTime) {
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.songAlbum = songAlbum;
        this.favorite = favorite;
        this.totalPlayTime = totalPlayTime;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongAlbum() {
        return songAlbum;
    }

    public void setSongAlbum(String songAlbum) {
        this.songAlbum = songAlbum;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        favorite = favorite;
    }

    public String getTotalPlayTime() {
        return totalPlayTime;
    }

    public void setTotalPlayTime(String totalPlayTime) {
        this.totalPlayTime = totalPlayTime;
    }

    @Override
    public String toString() {
        return "song{" + "songTitle='" + songTitle + '\'' + ", songArtist='" + songArtist + '\'' + ", songAlbum='" + songAlbum + '\'' + ", favorite=" + favorite + ", totalPlayTime=" + totalPlayTime + '}';
    }
}
