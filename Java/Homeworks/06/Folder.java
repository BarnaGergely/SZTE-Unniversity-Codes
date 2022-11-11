import java.util.Arrays;
public class Folder extends FileSystemEntry{
    private FileSystemEntry[] children;

    public Folder(Folder parent, String name) {
        super(parent, name);
    }

    public void addChild(FileSystemEntry parameter){
        if (this.children == null) {
            children = new FileSystemEntry[1];
            children[0] = parameter;

        }else {
            FileSystemEntry[] newChildren = Arrays.copyOf(children, children.length+1);
            newChildren[newChildren.length-1] = parameter;
            children = newChildren;
        }
    }

    public long size(){
        int osszeg = 0;
        for (int i = 0; i < children.length; i++) {
            osszeg += children[i].getName().length();
        }
        return osszeg;
    }
}
