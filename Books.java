public class Books {
    private int bookID;
    private String bookTitle, publishDate, authorName, authorActive, bookStatus;

    public Books(){
    }

    public Books(int bookID, String bookTitle, String publishDate, String authorName, String authorActive, String bookStatus) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.publishDate = publishDate;
        this.bookStatus = bookStatus;
        this.authorName = authorName;
        this.authorActive = authorActive;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorActive() {
        return authorActive;
    }

    public void setAuthorActive(String authorActive) {
        this.authorActive = authorActive;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

}
