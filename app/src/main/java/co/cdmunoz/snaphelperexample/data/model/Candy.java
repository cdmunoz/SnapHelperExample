package co.cdmunoz.snaphelperexample.data.model;

public class Candy {

  private int id;
  private int candyResource;
  private String candyName;

  public Candy(int id, int candyResource, String candyName) {
    this.id = id;
    this.candyResource = candyResource;
    this.candyName = candyName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCandyResource() {
    return candyResource;
  }

  public void setCandyResource(int candyResource) {
    this.candyResource = candyResource;
  }

  public String getCandyName() {
    return candyName;
  }

  public void setCandyName(String candyName) {
    this.candyName = candyName;
  }

  @Override public String toString() {
    return "Candy{"
        + "id="
        + id
        + ", candyResource="
        + candyResource
        + ", candyName='"
        + candyName
        + '\''
        + '}';
  }
}
