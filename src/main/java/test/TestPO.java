package test;

public class TestPO {
    private Integer name;

    public TestPO(Integer name) {
        this.name = name;
    }

    public Integer getName() {
        return this.name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((TestPO) obj).getName());
    }

}
