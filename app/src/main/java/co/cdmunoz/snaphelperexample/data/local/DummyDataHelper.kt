package co.cdmunoz.snaphelperexample.data.local

import co.cdmunoz.snaphelperexample.R
import co.cdmunoz.snaphelperexample.data.model.Candy
import java.util.ArrayList

class DummyDataHelper {

  val candiesList: List<Candy>
    get() {
      val candies = ArrayList<Candy>(11)
      candies.add(Candy(0, R.drawable.bonbonbum, "Bon Bon Bum"))
      candies.add(Candy(1, R.drawable.chocobreak, "Chocobreak"))
      candies.add(Candy(2, R.drawable.bonbonbum_maracuya, "Bon Bon Bum Maracuya"))
      candies.add(Candy(3, R.drawable.coffee_delight, "Coffee Delight"))
      candies.add(Candy(4, R.drawable.frunas, "Frunas"))
      candies.add(Candy(5, R.drawable.mix_gummy, "Mix Gummy"))
      candies.add(Candy(6, R.drawable.nucita, "Nucita"))
      candies.add(Candy(7, R.drawable.nucita_ice_cream, "Nucita Ice Cream"))
      candies.add(Candy(8, R.drawable.pirulito, "Pirulito"))
      candies.add(Candy(9, R.drawable.robin_hood_ice_cream, "Robin Hood Ice Cream"))
      candies.add(Candy(10, R.drawable.tipitin, "Tipitin"))
      return candies
    }
}
