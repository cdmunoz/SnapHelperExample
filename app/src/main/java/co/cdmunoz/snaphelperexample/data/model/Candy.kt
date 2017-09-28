package co.cdmunoz.snaphelperexample.data.model

class Candy(var id: Int, var candyResource: Int, var candyName: String?) {

  override fun toString(): String {
    return ("Candy{"
        + "id="
        + id
        + ", candyResource="
        + candyResource
        + ", candyName='"
        + candyName
        + '\''
        + '}')
  }
}
