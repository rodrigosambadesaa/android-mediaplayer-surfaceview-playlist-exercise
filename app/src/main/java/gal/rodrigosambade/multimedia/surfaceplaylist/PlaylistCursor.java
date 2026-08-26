package gal.rodrigosambade.multimedia.surfaceplaylist;

final class PlaylistCursor {
    private final int size;
    private int index;

    PlaylistCursor(int size) {
        if (size <= 0) throw new IllegalArgumentException("size must be > 0");
        this.size = size;
    }

    int current() {
        return index;
    }

    int next() {
        index = (index + 1) % size;
        return index;
    }

    void reset() {
        index = 0;
    }
}
