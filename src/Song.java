public class Song {
    private String songName;
    private String Artist;
    private int ID;
    private String Genre;
    private String Year;

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public Song(String songName, String artist, int ID, String genre, String year) {
        this.songName = songName;
        this.Artist = artist;
        this.ID = ID;
        this.Genre = genre;
        this.Year = year;
    }

    @Override
    public String toString() {
        String st = "Song Name: "+ songName + "\n";
        st += "Artist: "+ Artist + "\n";
        st += "ID: "+ ID + "\n";
        st += "Genre: "+ Genre + "\n";
        st += "Year: "+ Year + "\n";
        return st;
    }
}
