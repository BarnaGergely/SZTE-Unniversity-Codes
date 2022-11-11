public abstract class FileSystemEntry {
    private Folder parent;
    private String name;

    public FileSystemEntry(Folder parent, String name) {
        this.parent = parent;
        this.name = name;

        if (parent != null) {
            parent.addChild(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract long size();

    public String fullPath(){
        // TODO
        return "/utvonal";
    }


}
