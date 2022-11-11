public class SequentialNamer implements Namer{
    private int index = 1;

    public void rename(FileSystemEntry parameter){
        parameter.setName(parameter.getName()+"_"+index);
        index++;
    }
}
