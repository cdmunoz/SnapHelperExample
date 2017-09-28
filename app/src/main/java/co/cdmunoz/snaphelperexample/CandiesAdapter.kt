package co.cdmunoz.snaphelperexample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import co.cdmunoz.snaphelperexample.data.model.Candy
import java.util.ArrayList

class CandiesAdapter : RecyclerView.Adapter<CandiesAdapter.CandyHolder>() {

  private var candies: List<Candy> = ArrayList()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandyHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_candies_list, parent,
        false)
    return CandyHolder(itemView)
  }

  override fun onBindViewHolder(holder: CandyHolder, position: Int) {
    holder.bindCandy(candies[position])
  }

  override fun getItemCount(): Int {
    return candies.size
  }

  fun setCandies(candies: List<Candy>) {
    this.candies = candies
    notifyDataSetChanged()
  }

  internal inner class CandyHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
    @BindView(R.id.candyImage)
    var candyImage: ImageView? = null
    @BindView(R.id.candyName)
    var candyName: TextView? = null

    init {
      ButterKnife.bind(this, itemView)
    }

    fun bindCandy(candy: Candy) {
      candyImage!!.setImageResource(candy.candyResource)
      candyName!!.text = candy.candyName
    }
  }
}
