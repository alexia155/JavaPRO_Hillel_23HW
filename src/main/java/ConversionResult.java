public class ConversionResult {
    private String oldFileName;
    private String newFileName;
    private long duration;
    private long oldSize;
    private long newSize;
    private boolean error;
    public ConversionResult(String oldFileName, String newFileName, long duration, long oldSize, long newSize, boolean error) {
        this.oldFileName = oldFileName;
        this.newFileName = newFileName;
        this.duration = duration;
        this.oldSize = oldSize;
        this.newSize = newSize;
        this.error = error;
    }
    public String getOldFileName() {
        return oldFileName;
    }

    public void setOldFileName(String oldFileName) {
        this.oldFileName = oldFileName;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getOldSize() {
        return oldSize;
    }

    public void setOldSize(long oldSize) {
        this.oldSize = oldSize;
    }
    public long getNewSize() {
        return newSize;
    }

    public void setNewSize(long newSize) {
        this.newSize = newSize;
    }
    public boolean isError() {
        return error;
    }
    public void setError(boolean error) {
        this.error = error;
    }
}