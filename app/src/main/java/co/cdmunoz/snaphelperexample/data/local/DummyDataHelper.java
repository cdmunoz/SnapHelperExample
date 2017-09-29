package co.cdmunoz.snaphelperexample.data.local;

import co.cdmunoz.snaphelperexample.R;
import co.cdmunoz.snaphelperexample.data.model.Candy;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;

@Singleton
public class DummyDataHelper {

  public DummyDataHelper() {
  }

  public List<Candy> getCandiesList() {
    List<Candy> candies = new ArrayList<>(11);
    candies.add(new Candy(0, R.drawable.bonbonbum, "Bon Bon Bum"));
    candies.add(new Candy(1, R.drawable.chocobreak, "Chocobreak"));
    candies.add(new Candy(2, R.drawable.bonbonbum_maracuya, "Bon Bon Bum Maracuya"));
    candies.add(new Candy(3, R.drawable.coffee_delight, "Coffee Delight"));
    candies.add(new Candy(4, R.drawable.frunas, "Frunas"));
    candies.add(new Candy(5, R.drawable.mix_gummy, "Mix Gummy"));
    candies.add(new Candy(6, R.drawable.nucita, "Nucita"));
    candies.add(new Candy(7, R.drawable.nucita_ice_cream, "Nucita Ice Cream"));
    candies.add(new Candy(8, R.drawable.pirulito, "Pirulito"));
    candies.add(new Candy(9, R.drawable.robin_hood_ice_cream, "Robin Hood Ice Cream"));
    candies.add(new Candy(10, R.drawable.tipitin, "Tipitin"));

    return candies;
  }
}
