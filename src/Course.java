
public class Course { // simple course class to reduce cuppling and make the process easier
    private String name;
    private int fileOrder;

    public Course(String name, int fileOrder){
        this.name = name;
        this.fileOrder = fileOrder;
    }



    public int getOrder(){
        return fileOrder;
    }

    public String getName() {
        return name;
    }

}
